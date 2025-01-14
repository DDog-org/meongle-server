package ddog.user.application.mapper;

import ddog.domain.payment.Reservation;
import ddog.domain.review.CareReview;
import ddog.user.presentation.review.dto.request.UpdateCareReviewInfo;
import ddog.user.presentation.review.dto.request.PostCareReviewInfo;

import java.time.LocalDateTime;

public class CareReviewMapper {

    public static CareReview createBy(Reservation reservation, PostCareReviewInfo postCareReviewInfo) {
        return CareReview.builder()
                .reservationId(reservation.getReservationId())
                .reviewerId(reservation.getCustomerId())
                .revieweeName(reservation.getRecipientName())
                .shopName(reservation.getShopName())
                .starRating(postCareReviewInfo.getStarRating())
                .content(postCareReviewInfo.getContent())
                .createdAt(LocalDateTime.now())
                .imageUrlList(postCareReviewInfo.getImageUrlList())
                .vetId(reservation.getRecipientId())
                .careKeywordList(postCareReviewInfo.getCareKeywordList())
                .build();
    }

    public static CareReview modifyBy(CareReview careReview, UpdateCareReviewInfo updateCareReviewInfo) {
        return CareReview.builder()
                .careReviewId(careReview.getCareReviewId())
                .reservationId(careReview.getReservationId())
                .reviewerId(careReview.getReviewerId())
                .vetId(careReview.getVetId())
                .revieweeName(careReview.getRevieweeName())
                .shopName(careReview.getShopName())
                .starRating(updateCareReviewInfo.getStarRating())
                .content(updateCareReviewInfo.getContent())
                .createdAt(careReview.getCreatedAt())
                .modifiedTime(LocalDateTime.now())
                .imageUrlList(updateCareReviewInfo.getImageUrlList())
                .careKeywordList(updateCareReviewInfo.getCareKeywordList())
                .build();
    }
}
