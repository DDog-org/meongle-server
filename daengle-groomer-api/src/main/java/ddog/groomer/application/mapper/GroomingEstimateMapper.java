package ddog.groomer.application.mapper;

import ddog.domain.estimate.EstimateStatus;
import ddog.domain.estimate.GroomingEstimate;
import ddog.domain.groomer.Groomer;
import ddog.domain.pet.Pet;
import ddog.domain.user.User;
import ddog.groomer.presentation.estimate.dto.EstimateDetail;
import ddog.groomer.presentation.estimate.dto.EstimateInfo;
import ddog.groomer.presentation.estimate.dto.CreatePendingEstimateReq;

import java.time.LocalDateTime;

public class GroomingEstimateMapper {

    public static EstimateInfo.General.Content mapToGeneralContent(GroomingEstimate estimate, User user, Pet pet) {
        return EstimateInfo.General.Content.builder()
                .id(estimate.getEstimateId())
                .imageUrl(user.getImageUrl())
                .nickname(user.getNickname())
                .proposal(estimate.getProposal())
                .significant(pet.getSignificant())
                .reservedDate(estimate.getReservedDate())
                .build();
    }

    public static EstimateInfo.Designation.Content mapToDesignationContent(GroomingEstimate estimate, User user, Pet pet) {
        return EstimateInfo.Designation.Content.builder()
                .id(estimate.getEstimateId())
                .imageUrl(user.getImageUrl())
                .nickname(user.getNickname())
                .proposal(estimate.getProposal())
                .significant(pet.getSignificant())
                .reservedDate(estimate.getReservedDate())
                .build();
    }

    public static EstimateDetail mapToEstimateDetail(GroomingEstimate estimate, User user, Pet pet) {
        return EstimateDetail.builder()
                .userImageUrl(user.getImageUrl())
                .nickname(user.getNickname())
                .address(estimate.getAddress())
                .reservedDate(estimate.getReservedDate())
                .proposal(estimate.getProposal())
                .petId(pet.getPetId())
                .petImageUrl(pet.getImageUrl())
                .petName(pet.getName())
                .age(pet.getAge())
                .weight(pet.getWeight())
                .significant(pet.getSignificant())
                .desiredStyle(estimate.getDesiredStyle())
                .requirements(estimate.getRequirements())
                .overallOpinion(estimate.getOverallOpinion())
                .build();
    }

    public static GroomingEstimate createPendingEstimate(
            CreatePendingEstimateReq request,
            Groomer groomer,
            GroomingEstimate estimate) {
        return GroomingEstimate.builder()
                .parentId(estimate.getEstimateId())
                .userId(estimate.getUserId())
                .petId(estimate.getPetId())
                .groomerId(groomer.getAccountId())
                .address(estimate.getAddress())
                .reservedDate(request.getReservedDate())
                .requirements(estimate.getRequirements())
                .status(EstimateStatus.PENDING)
                .proposal(estimate.getProposal())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .desiredStyle(estimate.getDesiredStyle())
                .overallOpinion(request.getOverallOpinion())
                .build();
    }

}
