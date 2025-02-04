package ddog.domain.payment.port;

import ddog.domain.payment.Order;

import java.util.Optional;

public interface OrderPersist {
    Order save(Order order);
    Optional<Order> findByOrderUid(String orderUid);
    void delete(Order order);
    Optional<Order> findByIdempotencyKey(String idempotencyKey);
}
