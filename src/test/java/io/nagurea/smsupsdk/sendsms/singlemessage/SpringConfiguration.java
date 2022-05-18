package io.nagurea.smsupsdk.sendsms.singlemessage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Only here as an example of how the service could be used with Spring
 */
@Configuration
public class SpringConfiguration {

    private static final String ROOT_URL = "http://localhost:4242";

    @Bean
    SingleMessageService unitMessageService(){
        return new SingleMessageService(ROOT_URL);
    }

}
