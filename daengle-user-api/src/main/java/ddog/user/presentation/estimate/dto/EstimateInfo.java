package ddog.user.presentation.estimate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import ddog.domain.estimate.Proposal;
import ddog.domain.groomer.enums.GroomingKeyword;
import ddog.domain.vet.enums.CareKeyword;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class EstimateInfo {

    @Getter
    @Builder
    public static class Grooming {

        private List<Content> estimates;

        @Getter
        @Builder
        public static class Content {
            private Long id;
            private String name;
            private int daengleMeter;
            private String imageURL;
            private String shopName;
            private List<GroomingKeyword> keywords;

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            private LocalDateTime reservedDate;
        }
    }

    @Getter
    @Builder
    public static class Care {

        private List<Content> estimates;

        @Getter
        @Builder
        public static class Content {
            private Long id;
            private String name;
            private int daengleMeter;
            private String imageURL;
            private List<CareKeyword> keywords;

            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
            private LocalDateTime reservedDate;
        }
    }

}