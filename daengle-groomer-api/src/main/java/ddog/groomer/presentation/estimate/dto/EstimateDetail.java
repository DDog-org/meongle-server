package ddog.groomer.presentation.estimate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ddog.domain.estimate.Proposal;
import ddog.domain.pet.Weight;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class EstimateDetail {

    private String userImageUrl;
    private String nickname;
    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime reservedDate;
    private Proposal proposal;

    private Long petId;
    private String petImageUrl;
    private String petName;
    private int age;
    private Weight weight;
    private String significant;
    private String desiredStyle;
    private String requirements;
    private String overallOpinion;

}
