package ddog.persistence.rdb.jpa.entity;

import ddog.domain.pet.Pet;
import ddog.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Users")
public class UserJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private Long accountId;
    private String username;
    private String phoneNumber;
    private String nickname;
    private String imageUrl;
    private String address;
    private String email;

    @OneToMany
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    private List<PetJpaEntity> pets;

    public static UserJpaEntity from(User user) {
        return UserJpaEntity.builder()
                .userId(user.getUserId())
                .accountId(user.getAccountId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .nickname(user.getNickname())
                .imageUrl(user.getImageUrl())
                .address(user.getAddress())
                .email(user.getEmail())
                .pets(UserJpaEntity.fromPetModel(user.getPets()))
                .build();
    }

    public User toModel() {
        return User.builder()
                .userId(userId)
                .accountId(accountId)
                .username(username)
                .phoneNumber(phoneNumber)
                .nickname(nickname)
                .imageUrl(imageUrl)
                .address(address)
                .email(email)
                .pets(toPetModel())
                .build();
    }

    private List<Pet> toPetModel() {
        List<Pet> petModel = new ArrayList<>();
        for (PetJpaEntity pet : pets) {
            petModel.add(pet.toModel());
        }
        return petModel;
    }

    private static List<PetJpaEntity> fromPetModel(List<Pet> petModel) {
        List<PetJpaEntity> pets = new ArrayList<>();
        for (Pet pet : petModel) {
            pets.add(PetJpaEntity.from(pet));
        }
        return pets;
    }
}
