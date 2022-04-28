package everyone.delivery.demo.security.Sign.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KakaoResponseDto {

    private int id;
    private Boolean has_signed_up;
    private String connected_at;
    private String synched_at;

    @Data
    private class Properties {
        private String nickname;
        private String profile_image;
        private String thumbnail_image;
    }

    @Data
    private class KakaoAccount {
        private Boolean profile_needs_agreement;
        private Profile profile;
        private Boolean email_needs_agreement;
        private Boolean is_email_valid;
        private Boolean is_email_verified;
        private String email;

        @Data
        private class Profile {
            private String nickname;
//            private String thumbnail_image_url;
//            private String profile_image_url;
//            private Boolean is_default_image;
        }
    }
}
