package io.nagurea.smsupsdk.lists.create;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Only here as an example of how the service could be used with Spring
 */
@Configuration
public class SpringConfiguration {

    private static final String ROOT_URL = "http://localhost:4242";

    @Bean
    CreateListService campaignService(){
        return new CreateListService(ROOT_URL);
    }

}
