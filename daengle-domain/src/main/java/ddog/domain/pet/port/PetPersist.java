package ddog.domain.pet.port;

import ddog.domain.pet.Pet;

import java.util.Optional;

public interface PetPersist {
    Pet save(Pet pet);

    Optional<Pet> findByPetId(Long accountId);

    void deletePetById(Long petId);
}