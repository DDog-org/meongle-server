package ddog.domain.groomer.port;

import ddog.domain.groomer.Groomer;

import java.util.Optional;

public interface GroomerPersist {

    Optional<Groomer> findByAccountId(Long accountId);

    Groomer save(Groomer newGroomer);

    Optional<Groomer> findByGroomerId(Long groomerId);

}
