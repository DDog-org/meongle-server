package ddog.daengleserver.infrastructure;

import ddog.daengleserver.infrastructure.po.VetJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VetJpaRepository extends JpaRepository<VetJpaEntity, Long> {

    Optional<VetJpaEntity> findByVetId(Long accountId);

    VetJpaEntity getByVetId(Long accountId);
}
