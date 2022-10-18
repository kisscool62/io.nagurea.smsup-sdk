package io.nagurea.smsupsdk.apierrors.apierror;

import io.nagurea.smsupsdk.common.configuration.LocalHost4242;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Only here as an example of how the service could be used with Spring
 */
@Configuration
public class SpringConfiguration implements LocalHost4242 {

    @Bean
    GetAPIErrorService getAPIErrorService(){
        return new GetAPIErrorService(ROOT_URL);
    }

}
