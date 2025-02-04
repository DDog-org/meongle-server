package ddog.groomer.application.mapper;

import ddog.domain.groomer.Groomer;
import ddog.domain.groomer.License;
import ddog.groomer.presentation.account.dto.ProfileInfo;
import ddog.groomer.presentation.account.dto.SignUpReq;
import ddog.groomer.presentation.account.dto.UpdateInfoReq;

import java.util.ArrayList;
import java.util.List;

public class GroomerMapper {

    public static Groomer create(Long accountId, SignUpReq request, List<License> licenses, Long shopId) {
        return Groomer.builder()
                .accountId(accountId)
                .daengleMeter(50)
                .introduction(null)
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .imageUrl("")
                .email(request.getEmail())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .shopId(shopId)
                .shopName(request.getShopName())
                .introduction(null)
                .businessLicenses(request.getBusinessLicenses())
                .licenses(licenses)
                .badges(null)
                .keywords(new ArrayList<>())
                .build();
    }

    public static ProfileInfo mapToProfileInfo(Groomer groomer) {
        return ProfileInfo.builder()
                .imageUrl(groomer.getImageUrl())
                .name(groomer.getName())
                .badges(groomer.getBadges())
                .shopId(groomer.getShopId())
                .shopName(groomer.getShopName())
                .introduction(groomer.getIntroduction())
                .daengleMeter(groomer.getDaengleMeter())
                .build();
    }

    public static ProfileInfo.UpdatePage mapToUpdatePage(Groomer groomer, List<ProfileInfo.UpdatePage.LicenseDetail> details) {
        return ProfileInfo.UpdatePage.builder()
                .image(groomer.getImageUrl())
                .instagramId(groomer.getInstagramId())
                .name(groomer.getName())
                .phoneNumber(groomer.getPhoneNumber())
                .email(groomer.getEmail())
                .introduction(groomer.getIntroduction())
                .licenses(details)
                .build();
    }

    public static Groomer updateWithUpdateInfoReq(Groomer groomer, UpdateInfoReq request) {
        return Groomer.builder()
                .groomerId(groomer.getGroomerId())
                .accountId(groomer.getAccountId())
                .daengleMeter(groomer.getDaengleMeter())
                .instagramId(request.getInstagramId())
                .name(groomer.getName())
                .phoneNumber(groomer.getPhoneNumber())
                .imageUrl(request.getImage())
                .email(groomer.getEmail())
                .address(groomer.getAddress())
                .detailAddress(groomer.getDetailAddress())
                .shopId(groomer.getShopId())
                .shopName(groomer.getShopName())
                .introduction(request.getIntroduction())
                .businessLicenses(groomer.getBusinessLicenses())
                .licenses(groomer.getLicenses())
                .badges(groomer.getBadges())
                .keywords(groomer.getKeywords())
                .build();
    }

    public static Groomer updateShopId(Groomer groomer, Long shopId) {
        return Groomer.builder()
                .groomerId(groomer.getGroomerId())
                .accountId(groomer.getAccountId())
                .daengleMeter(groomer.getDaengleMeter())
                .instagramId(groomer.getInstagramId())
                .name(groomer.getName())
                .phoneNumber(groomer.getPhoneNumber())
                .imageUrl(groomer.getImageUrl())
                .email(groomer.getEmail())
                .address(groomer.getAddress())
                .shopName(groomer.getShopName())
                .introduction(groomer.getIntroduction())
                .businessLicenses(groomer.getBusinessLicenses())
                .licenses(groomer.getLicenses())
                .shopId(shopId)
                .build();
    }
}
