package ddog.daengleserver.application;

import ddog.daengleserver.application.repository.CareEstimateRepository;
import ddog.daengleserver.application.repository.GroomingEstimateRepository;
import ddog.daengleserver.application.repository.UserRepository;
import ddog.daengleserver.domain.account.Pet;
import ddog.daengleserver.domain.account.User;
import ddog.daengleserver.domain.estimate.CareEstimate;
import ddog.daengleserver.domain.estimate.GroomingEstimate;
import ddog.daengleserver.presentation.estimate.dto.response.CareEstimateDetail;
import ddog.daengleserver.presentation.estimate.dto.response.EstimateInfo;
import ddog.daengleserver.presentation.estimate.dto.response.GroomingEstimateDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GroomingEstimateRepository groomingEstimateRepository;
    private final CareEstimateRepository careEstimateRepository;

    @Transactional(readOnly = true)
    public EstimateInfo findEstimateInfo(Long accountId) {
        User user = userRepository.findByAccountId(accountId);
        List<Pet> pets = user.getPets();

        List<EstimateInfo.PetInfo> petInfos = new ArrayList<>();
        for (Pet pet : pets) {
            List<GroomingEstimate> groomingEstimates = groomingEstimateRepository.findGroomingEstimatesByPetId(pet.getPetId());
            List<EstimateInfo.PetInfo.Grooming> groomingInfos = GroomingEstimate.toInfos(groomingEstimates);

            List<CareEstimate> careEstimates = careEstimateRepository.findCareEstimatesByPetId(pet.getPetId());
            List<EstimateInfo.PetInfo.Care> careInfos = CareEstimate.toInfos(careEstimates);

            petInfos.add(EstimateInfo.PetInfo.builder()
                    .name(pet.getPetName())
                    .image(pet.getPetImage())
                    .groomingEstimates(groomingInfos)
                    .careEstimates(careInfos)
                    .build());
        }
        return EstimateInfo.builder()
                .petInfos(petInfos)
                .build();
    }

    @Transactional(readOnly = true)
    public GroomingEstimateDetail getGroomingEstimateDetail(Long groomingEstimateId) {
        GroomingEstimate groomingEstimate = groomingEstimateRepository.getByGroomingEstimateId(groomingEstimateId);
        return groomingEstimate.getGroomingEstimateDetail();
    }

    @Transactional(readOnly = true)
    public CareEstimateDetail getCareEstimateDetail(Long careEstimateId) {
        CareEstimate careEstimate = careEstimateRepository.getByCareEstimateId(careEstimateId);
        return careEstimate.getCareEstimateDetail();
    }
}