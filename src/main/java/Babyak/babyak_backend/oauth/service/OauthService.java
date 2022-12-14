package Babyak.babyak_backend.oauth.service;

import Babyak.babyak_backend.oauth.component.GoogleOauth;
import Babyak.babyak_backend.oauth.dto.GoogleLoginResponse;
import Babyak.babyak_backend.user.entity.User;
import Babyak.babyak_backend.user.repository.BlockQuerydslRepository;
import Babyak.babyak_backend.user.repository.UserQuerydslRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
@Slf4j
public class OauthService {

   private final GoogleOauth googleOauth;
   private final HttpServletResponse response;
   private final UserQuerydslRepository userQuerydslRepository;
   private final BlockQuerydslRepository blockQuerydslRepository;

   public void request() {
       String redirectURL = googleOauth.getOauthRedirectURL();

       try {
           response.sendRedirect(redirectURL);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }


   public GoogleLoginResponse getUserEmail(String code) {
       JsonNode token = requestAccessToken(code);

       //String accessToken = token.get("access_token").toString();
       //int expiresIn = token.get("expires_in").asInt();
       String idToken = token.get("id_token").asText();
       
       //System.out.println("idToken: " + idToken);
       String requestURL = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
       //System.out.println("requestURL: " + requestURL);
       String email = "";

       try {
           URL url = new URL(requestURL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setDoOutput(true);

           int responseCode = conn.getResponseCode();
           //System.out.println("responseCode: " + responseCode);

           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           String line = "";
           String result = "";

           while ((line = br.readLine()) != null) {
               result += line;
           }

           //System.out.println("responseBody: " + result);
           JsonObject object = JsonParser.parseString(result).getAsJsonObject();
           email = object.get("email").getAsString();

       } catch (IOException e) {
           e.printStackTrace();
       }

       int index = email.indexOf("@");
       String domain = email.substring(index + 1);
       //System.out.println("domain: " + domain);

       // ????????? ?????? ?????? ??????
       if (!domain.equals("ewhain.net")) {
            return GoogleLoginResponse.builder()
                    .email(email)
                    .isEwha(false)
                    .isRegistered(null)
                    .isBlocked(null)
                    .build();
       }
       // ????????? ????????? ??????
       else {
           // ?????? ?????? ??????
           // ?????? ????????? ??????
           if (userQuerydslRepository.findByEmail(email) != null) {

               log.info("??????: " + blockQuerydslRepository.findByEmail(email).getUser().getEmail());
               // ?????? ?????? ??????
               if (blockQuerydslRepository.findByEmail(email) != null) {
                   return GoogleLoginResponse.builder()
                           .email(email)
                           .isEwha(true)
                           .isRegistered(true)
                           .isBlocked(true)
                           .build();
               }

               return GoogleLoginResponse.builder()
                       .email(email)
                       .isEwha(true)
                       .isRegistered(true)
                       .isBlocked(false)
                       .build();
           }
           // ???????????? ?????? ??????
           else {
               return GoogleLoginResponse.builder()
                       .email(email)
                       .isEwha(true)
                       .isRegistered(false)
                       .isBlocked(null)
                       .build();
           }
       }
   }


   public JsonNode requestAccessToken(String code) {
       return googleOauth.requestAccessToken(code);
   }


}
