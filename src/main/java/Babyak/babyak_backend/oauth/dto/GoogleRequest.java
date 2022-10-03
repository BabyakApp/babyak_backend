package Babyak.babyak_backend.oauth.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class GoogleRequest {

    private String loginUrl = "https://accounts.google.com/o/oauth2/v2/auth";

    @Value("${spring.security.oauth2.client.registration.google.redirect-uri}")
    private String callbackUrl;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String clientSecret;

    @Value("${spring.security.oauth2.client.registration.google.scope}")
    private String dataAccessScope;

    private ObjectMapper objectMapper;

    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", dataAccessScope);
        params.put("response_type", "code");
        params.put("client_id", clientId);
        params.put("redirect_uri", callbackUrl);

        // parameter를 형식에 맞춰 구성
        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));
        String redirectURL = loginUrl + "?" + parameterString;
        System.out.println("redirectURL = " + redirectURL);

        return redirectURL;

        /*
        https://accounts.google.com/o/oauth2/v2/auth?scope=profile&response_type=code&client_id="할당받은id"
        &redirect_uri="access token 처리"
        로 Redirect URL을 생성하는 로직
         */
    }

    public ResponseEntity<String> requestAccessToken(String code) {
        String GOOGLE_TOKEN_REQUEST_URL = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", callbackUrl);
        params.put("grant_type", "authorization_code");

        ResponseEntity<String> responseEntity = restTemplate.
                postForEntity(GOOGLE_TOKEN_REQUEST_URL, params, String.class);

        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity;
        }
        return null;
    }

    public GoogleOauthToken getAccessToken(ResponseEntity<String> response) throws JsonProcessingException {
        System.out.println("response.getBody() = " + response.getBody());
        GoogleOauthToken googleOauthToken = objectMapper.readValue(response.getBody(), GoogleOauthToken.class);
        return googleOauthToken;
    }

    public ResponseEntity<String> requestUserInfo(GoogleOauthToken googleOauthToken) {
        String GOOGLE_USERINFO_REQUEST_URL = "https://www.googleapis.com/oauth2/v1/userinfo";

        // header에 accessToken 담기
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorizaion", "Bearer " + googleOauthToken.getAccessToken());

        // HttpEntity 생성해 헤더를 담아서 restTemplate으로 구글과 통신
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .exchange(GOOGLE_USERINFO_REQUEST_URL, HttpMethod.GET, request, String.class);

        System.out.println("response.getBody() = " + response.getBody());
        return response;
    }

    public GoogleUser getUserInfo(ResponseEntity<String> userInfo) throws JsonProcessingException {
        GoogleUser googleUser = objectMapper.readValue(userInfo.getBody(), GoogleUser.class);
        return googleUser;
    }

}
