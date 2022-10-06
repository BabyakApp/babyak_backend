package Babyak.babyak_backend.oauth.service;

import Babyak.babyak_backend.oauth.component.GoogleOauth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class OauthService {

   private final GoogleOauth googleOauth;
   private final HttpServletResponse response;

   public void request() {
       String redirectURL = googleOauth.getOauthRedirectURL();

       try {
           response.sendRedirect(redirectURL);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

   public String requestAccessToken(String code) {
       return googleOauth.requestAccessToken(code);
   }


}
