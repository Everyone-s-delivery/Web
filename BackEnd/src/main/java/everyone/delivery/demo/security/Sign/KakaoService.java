package everyone.delivery.demo.security.Sign;

import everyone.delivery.demo.security.Sign.dtos.KakaoResponseDto;
import everyone.delivery.demo.security.Sign.dtos.KakaoTokenDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
@Slf4j
public class KakaoService {

    RestTemplate restTemplate;

    public KakaoTokenDto getAccessTokenByCode(String code) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", "<REST API>");
        params.add("redirect_uri", "<Redirect_URI>" + "/oauth/kakao/callback");
        params.add("code", code);
        params.add("client_secret", "<Client_secret");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, headers);

        // Access Token
        return restTemplate.exchange(
                "https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                request,
                KakaoTokenDto.class
        ).getBody();
    }

    public KakaoResponseDto getAccountByAccessToken(KakaoTokenDto kakaoTokenDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + kakaoTokenDto.getAccess_token());
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);

        return restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                request,
                KakaoResponseDto.class
        ).getBody();
    }
}
