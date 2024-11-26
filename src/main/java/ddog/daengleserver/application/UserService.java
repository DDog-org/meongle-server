package ddog.daengleserver.application;

import ddog.daengleserver.application.repository.*;
import ddog.daengleserver.domain.Account;
import ddog.daengleserver.domain.Pet;
import ddog.daengleserver.domain.User;
import ddog.daengleserver.domain.estimate.CareEstimate;
import ddog.daengleserver.domain.estimate.GroomingEstimate;
import ddog.daengleserver.presentation.dto.request.JoinUserWithPet;
import ddog.daengleserver.presentation.dto.request.JoinUserWithoutPet;
import ddog.daengleserver.presentation.estimate.dto.response.CareEstimateDetails;
import ddog.daengleserver.presentation.estimate.dto.response.EstimateInfo;
import ddog.daengleserver.presentation.estimate.dto.response.GroomingEstimateDetails;
import ddog.daengleserver.presentation.estimate.dto.response.UserAndPetsInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PetRepository petRepository;
    private final GroomingEstimateRepository groomingEstimateRepository;
    private final CareEstimateRepository careEstimateRepository;

    @Transactional
    public void createUserWithoutPet(JoinUserWithoutPet request) {
        Account accountToSave = Account.create(request.getEmail(), request.getRole());
        Account savedAccount = accountRepository.save(accountToSave);
        User user = User.createWithoutPet(savedAccount.getAccountId(), request);
        userRepository.save(user);
    }

    @Transactional
    public void createUserWithPet(JoinUserWithPet request) {
        Account accountToSave = Account.create(request.getEmail(), request.getRole());
        Account savedAccount = accountRepository.save(accountToSave);
        Pet pet = Pet.toJoinPetInfo(savedAccount.getAccountId(), request);
        User user = User.createWithPet(savedAccount.getAccountId(), request, pet);
        petRepository.save(pet);
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public Boolean hasNickname(String nickname) {
        return !userRepository.hasNickname(nickname);
    }

    @Transactional(readOnly = true)
    public UserAndPetsInfo getUserAddressAndPetsInfoById(Long accountId) {
        User user = userRepository.findById(accountId);
        return user.findAddressAndPetsInfo();
    }

    @Transactional(readOnly = true)
    public EstimateInfo findEstimateInfos(Long userId) {
        User user = userRepository.findById(userId);
        List<Pet> pets = user.getPets();

        List<EstimateInfo.PetInfo> petInfos = new ArrayList<>();
        for (Pet pet : pets) {
            List<GroomingEstimate> groomingEstimates = groomingEstimateRepository.findGroomingEstimatesByPetId(pet.getPetId());
            List<EstimateInfo.PetInfo.Grooming> groomingInfos = GroomingEstimate.toInfos(groomingEstimates);

            List<CareEstimate> careEstimates = careEstimateRepository.findCareEstimatesByPetId(pet.getPetId());
            List<EstimateInfo.PetInfo.Care> careInfos = CareEstimate.toInfos(careEstimates);

            petInfos.add(EstimateInfo.PetInfo.builder()
                    .petName(pet.getPetName())
                    .petImage(pet.getPetImage())
                    .groomingEstimates(groomingInfos)
                    .careEstimates(careInfos)
                    .build());
        }
        return EstimateInfo.builder()
                .petInfos(petInfos)
                .build();
    }

    @Transactional(readOnly = true)
    public GroomingEstimateDetails getGroomingEstimateDetails(Long groomingEstimateId) {
        GroomingEstimate groomingEstimate = groomingEstimateRepository.getByGroomingEstimateId(groomingEstimateId);
        return groomingEstimate.getGroomingEstimateDetails();
    }

    @Transactional(readOnly = true)
    public CareEstimateDetails getCareEstimateDetails(Long careEstimateId) {
        CareEstimate careEstimate = careEstimateRepository.getByCareEstimateId(careEstimateId);
        return careEstimate.getCareEstimateDetails();
    }
}
