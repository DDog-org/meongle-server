package ddog.persistence.rdb.jpa.entity;

import ddog.domain.vet.Day;
import ddog.domain.vet.Vet;
import ddog.domain.vet.enums.CareBadge;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Vets")
public class VetJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vetId;
    private Long accountId;
    private String email;
    private int daengleMeter;
    private String name;
    private String imageUrl;

    @ElementCollection // 휴무일 리스트
    @CollectionTable(name = "vet_image_urls", joinColumns = @JoinColumn(name = "vet_id"))
    @Column(name = "image_url")
    private List<String> imageUrlList = new ArrayList<>();

    private String address;
    private String detailAddress;
    private String phoneNumber;
    private String introduction;
    private LocalTime startTime;
    private LocalTime endTime;

    @ElementCollection // 휴무일 리스트
    @CollectionTable(name = "vet_closed_days", joinColumns = @JoinColumn(name = "vet_id"))
    @Column(name = "day")
    @Enumerated(EnumType.STRING)
    private List<Day> closedDays;

    @ElementCollection // 자격증 URL 리스트
    @CollectionTable(name = "vet_licenses", joinColumns = @JoinColumn(name = "vet_id"))
    @Column(name = "license_url")
    private List<String> licenses;

    @ElementCollection // 뱃지 리스트
    @CollectionTable(name = "care_badges", joinColumns = @JoinColumn(name = "vet_id"))
    @Column(name = "care_badge")
    @Enumerated(EnumType.STRING)
    private List<CareBadge> badges;

    @OneToMany
    @JoinColumn(name = "account_id", referencedColumnName = "accountId")
    private List<VetKeywordJpaEntity> keywords;

    public static VetJpaEntity from(Vet vet) {
        return VetJpaEntity.builder()
                .vetId(vet.getVetId())
                .accountId(vet.getAccountId())
                .email(vet.getEmail())
                .daengleMeter(vet.getDaengleMeter())
                .name(vet.getName())
                .imageUrl(vet.getImageUrl())
                .imageUrlList(vet.getImageUrlList())
                .address(vet.getAddress())
                .detailAddress(vet.getDetailAddress())
                .phoneNumber(vet.getPhoneNumber())
                .introduction(vet.getIntroduction())
                .startTime(vet.getStartTime())
                .endTime(vet.getEndTime())
                .closedDays(vet.getClosedDays())
                .licenses(vet.getLicenses())
                .badges(vet.getBadges())
                .keywords(vet.getKeywords().stream()
                        .map(VetKeywordJpaEntity::from)
                        .toList())
                .build();
    }

    public Vet toModel() {
        return Vet.builder()
                .vetId(vetId)
                .accountId(accountId)
                .email(email)
                .daengleMeter(daengleMeter)
                .name(name)
                .imageUrl(imageUrl)
                .imageUrlList(imageUrlList)
                .address(address)
                .detailAddress(detailAddress)
                .phoneNumber(phoneNumber)
                .introduction(introduction)
                .startTime(startTime)
                .endTime(endTime)
                .closedDays(closedDays)
                .licenses(licenses)
                .badges(badges)
                .keywords(keywords.stream()
                        .map(VetKeywordJpaEntity::toModel)
                        .toList())
                .build();
    }
}
