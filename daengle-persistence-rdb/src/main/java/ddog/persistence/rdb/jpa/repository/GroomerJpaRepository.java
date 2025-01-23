package ddog.persistence.rdb.jpa.repository;

import ddog.domain.groomer.enums.GroomingBadge;
import ddog.persistence.rdb.jpa.entity.GroomerJpaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface GroomerJpaRepository extends JpaRepository<GroomerJpaEntity, Long> {

    Optional<GroomerJpaEntity> findByAccountId(Long accountId);

    Optional<GroomerJpaEntity> findByGroomerId(Long groomerId);

    @Query("SELECT g.groomerId FROM Groomers g " +
            "WHERE (:address IS NULL OR :address = '' OR g.address LIKE CONCAT('%', :address, '%')) " +
            "AND (:name IS NULL OR :name = '' OR g.name LIKE CONCAT('%', :name, '%')) " +
            "AND (:badge IS NULL OR :badge MEMBER OF g.badges)")
    Page<Long> findPagedGroomerIds(
            @Param("address") String address,
            @Param("name") String name,
            @Param("badge") GroomingBadge badge,
            Pageable pageable
    );

    @Query("SELECT DISTINCT g FROM Groomers g " +
            "LEFT JOIN FETCH g.badges b " +
            "WHERE g.groomerId IN :ids")
    List<GroomerJpaEntity> findGroomersWithDetails(@Param("ids") List<Long> ids);

    void deleteByAccountId(Long accountId);
}
