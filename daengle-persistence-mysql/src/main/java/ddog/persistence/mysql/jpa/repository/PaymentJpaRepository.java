package ddog.persistence.mysql.jpa.repository;


import ddog.persistence.mysql.jpa.entity.PaymentJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PaymentJpaRepository extends JpaRepository<PaymentJpaEntity, Long> {
    Optional<PaymentJpaEntity> findByPaymentId(Long paymentId);
    Optional<PaymentJpaEntity> findByIdempotencyKey(String idempotencyKey);
    Optional<List<PaymentJpaEntity>> findByPayerId(Long payerId);
}
