package ddog.mungleserver.infrastructure.auth.service;

import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoSocialService {

    private static final String KAKAO_TOKEN_INFO_URL = "https://kapi.kakao.com/v2/user/me";

    public HashMap<String, Object> getKakaoUserInfo(String kakaoAccessToken){
        try {
            HttpURLConnection kakaoServerConnection = getKakaoServerConnectionForTokenInfo(kakaoAccessToken);
            if (kakaoServerConnection.getResponseCode() == 200) {
                return getKakaoUserInfoResponse(kakaoServerConnection);
            }
            /* 추후에 RESPONSE_CODE_ERROR 으로 변경 예정 */
            throw new RuntimeException();
        } catch (IOException e) {
            /* 추후에 FAILED_TO_RETRIEVE_KAKAO_USER_INFO 으로 변경 예정 */
            throw new RuntimeException();
        }
    }

    private HashMap<String, Object> getKakaoUserInfoResponse(HttpURLConnection kakaoServerConnection) throws IOException{
        String responseBody = readResponse(kakaoServerConnection);
        log.info("ResponseBody: {}", responseBody);

        JsonElement jsonElement = JsonParser.parseString(responseBody);
        String email = jsonElement.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
        String nickname = jsonElement.getAsJsonObject().get("properties").getAsJsonObject().get("nickname").getAsString();

        HashMap<String, Object> kakaoUserInfo = new HashMap<>();
        kakaoUserInfo.put("email", email);
        kakaoUserInfo.put("nickname", nickname);
        return kakaoUserInfo;
    }

    private String readResponse(HttpURLConnection kakaoServerConnection) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(kakaoServerConnection.getInputStream()));
        String responseBodyInput = "";
        StringBuilder responseBody = new StringBuilder();
        while ((responseBodyInput = br.readLine()) != null) {
            responseBody.append(responseBodyInput);
        }
        br.close();
        return responseBody.toString();
    }

    private static HttpURLConnection getKakaoServerConnectionForTokenInfo(String kakaoAccessToken) throws IOException {
        URL url = new URL(KAKAO_TOKEN_INFO_URL);
        HttpURLConnection kakaoServerConnection = (HttpURLConnection) url.openConnection();
        kakaoServerConnection.setRequestMethod("GET");
        kakaoServerConnection.setRequestProperty("Authorization", "Bearer " + kakaoAccessToken);
        return kakaoServerConnection;
    }
}
