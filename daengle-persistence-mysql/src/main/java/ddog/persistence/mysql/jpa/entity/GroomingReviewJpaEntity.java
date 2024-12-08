package ddog.persistence.mysql.jpa.entity;

import ddog.domain.review.GroomingReview;
import ddog.domain.review.enums.GroomingKeywordReview;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "GroomingReviews")
public class GroomingReviewJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groomingReviewId;
    private Long reservationId;
    private Long groomerId;
    private Long reviewerId;
    private Long revieweeId;
    private Long starRating;
    private String content;
    private LocalDateTime createTime;

    @ElementCollection // 이미지 URL 리스트
    @CollectionTable(name = "grooming_review_image_url_list", joinColumns = @JoinColumn(name = "grooming_review_id"))
    @Column(name = "image_url_list")
    private List<String> imageUrlList;

    @ElementCollection // 키워드 리스트
    @CollectionTable(name = "grooming_review_keyword_list", joinColumns = @JoinColumn(name = "grooming_review_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "keyword_list")
    private List<GroomingKeywordReview> groomingKeywordReviewList;

    public static GroomingReviewJpaEntity from(GroomingReview groomingReview) {
        return GroomingReviewJpaEntity.builder()
                .groomingReviewId(groomingReview.getGroomingReviewId())
                .groomerId(groomingReview.getGroomerId())
                .reservationId(groomingReview.getReservationId())
                .reviewerId(groomingReview.getReviewerId())
                .revieweeId(groomingReview.getGroomerId())
                .starRating(groomingReview.getStarRating())
                .content(groomingReview.getContent())
                .createTime(groomingReview.getCreateTime())
                .imageUrlList(groomingReview.getImageUrlList())
                .groomingKeywordReviewList(groomingReview.getGroomingKeywordReviewList())
                .build();
    }

    public GroomingReview toModel() {
        return GroomingReview.builder()
                .groomingReviewId(groomingReviewId)
                .groomerId(groomerId)
                .reservationId(reservationId)
                .reviewerId(reviewerId)
                .groomerId(revieweeId)
                .starRating(starRating)
                .content(content)
                .createTime(createTime)
                .imageUrlList(imageUrlList)
                .groomingKeywordReviewList(groomingKeywordReviewList)
                .build();
    }
}