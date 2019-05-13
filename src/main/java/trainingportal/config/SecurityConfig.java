package trainingportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import trainingportal.security.UserSecurity;
import trainingportal.security.UserSecurityImpl;

@Configuration
public class SecurityConfig {
    @Bean
    public UserSecurity userSecurity(){
        return new UserSecurityImpl();
    }
}
