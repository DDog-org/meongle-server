package ddog.persistence.rdb.jpa.repository;

import ddog.persistence.rdb.jpa.entity.PetJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetJpaRepository extends JpaRepository<PetJpaEntity, Long> {

    Optional<PetJpaEntity> findByPetId(Long petId);

    void deleteByPetId(Long petId);

}
