package Babyak.babyak_backend.oauth.service;

import Babyak.babyak_backend.oauth.component.GoogleOauth;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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


   public String getUserInfo(String code) {
       JsonNode token = requestAccessToken(code);

       String accessToken = token.get("access_token").toString();
       int expiresIn = token.get("expires_in").asInt();
       String idToken = token.get("id_token").asText();

       System.out.println("idToken: " + idToken);
       String requestURL = "https://oauth2.googleapis.com/tokeninfo?id_token=" + idToken;
       System.out.println("requestURL: " + requestURL);
       String email = "";

       try {
           URL url = new URL(requestURL);
           HttpURLConnection conn = (HttpURLConnection) url.openConnection();
           conn.setRequestMethod("GET");
           conn.setDoOutput(true);

           int responseCode = conn.getResponseCode();
           System.out.println("responseCode: " + responseCode);

           BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
           String line = "";
           String result = "";

           while ((line = br.readLine()) != null) {
               result += line;
           }

           System.out.println("responseBody: " + result);
           JsonObject object = JsonParser.parseString(result).getAsJsonObject();
           email = object.get("email").getAsString();

       } catch (IOException e) {
           e.printStackTrace();
       }

       return email;
   }


   public JsonNode requestAccessToken(String code) {
       return googleOauth.requestAccessToken(code);
   }


}
