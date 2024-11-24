package ddog.daengleserver.infrastructure;

import ddog.daengleserver.infrastructure.po.GroomerJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroomerJpaRepository extends JpaRepository<GroomerJpaEntity, Long> {

    Optional<GroomerJpaEntity> findByGroomerId(Long accountId);

    GroomerJpaEntity getByGroomerId(Long accountId);
}