package ddog.domain.estimate.port;

import ddog.domain.estimate.CareEstimate;
import ddog.domain.estimate.EstimateStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CareEstimatePersist {
    CareEstimate save(CareEstimate careEstimate);

    Optional<CareEstimate> findByEstimateId(Long careEstimateId);

    List<CareEstimate> findCareEstimatesByPetId(Long petId);

    List<CareEstimate> findCareEstimatesByAddress(String address);

    List<CareEstimate> findCareEstimatesByVetId(Long vetId);

    void updateStatusWithParentId(EstimateStatus estimateStatus, Long parentId);

    Page<CareEstimate> findByPetIdAndPageable(Long petId, Pageable pageable);
}
