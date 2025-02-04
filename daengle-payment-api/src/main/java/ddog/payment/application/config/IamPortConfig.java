package ddog.payment.application.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.siot.IamportRestClient.IamportClient;

@Configuration
public class IamPortConfig {

    private final Dotenv dotenv = Dotenv.configure()
            //.directory(System.getProperty("user.dir"))  // 현재 작업 디렉토리에서 .env 파일 로드
            .ignoreIfMissing() // CI 빌드 테스트 통과
            .load();

    @Bean
    public IamportClient iamportClient() {
        String apiKey = dotenv.get("IAMPORT_API_KEY");
        String secretKey = dotenv.get("IAMPORT_SECRET_KEY");
        return new IamportClient(apiKey, secretKey);
    }
}
