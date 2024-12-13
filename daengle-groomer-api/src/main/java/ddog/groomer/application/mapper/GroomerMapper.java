package ddog.groomer.application.mapper;

import ddog.domain.groomer.Groomer;
import ddog.domain.groomer.License;
import ddog.groomer.presentation.account.dto.UpdateInfoReq;
import ddog.groomer.presentation.account.dto.ProfileInfo;
import ddog.groomer.presentation.account.dto.SignUpReq;

import java.util.List;

public class GroomerMapper {

    public static Groomer create(Long accountId, SignUpReq request, List<License> licenses) {
        return Groomer.builder()
                .accountId(accountId)
                .daengleMeter(0)
                .imageUrl("")
                .name(request.getName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .detailAddress(request.getDetailAddress())
                .shopName(request.getShopName())
                .businessLicenses(request.getBusinessLicenses())
                .licenses(licenses)
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
                .shopName(groomer.getShopName())
                .introduction(request.getIntroduction())
                .businessLicenses(groomer.getBusinessLicenses())
                .licenses(groomer.getLicenses())
                .build();
    }
}
