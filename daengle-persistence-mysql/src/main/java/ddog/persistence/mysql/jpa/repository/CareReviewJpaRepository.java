package ddog.persistence.mysql.jpa.repository;

import ddog.persistence.mysql.jpa.entity.CareReviewJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareReviewJpaRepository extends JpaRepository<CareReviewJpaEntity, Long> {
    List<CareReviewJpaEntity> findByReviewerId(Long reviewerId);
}
