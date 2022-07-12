package io.nagurea.smsupsdk.contacts.remove;

import io.nagurea.smsupsdk.common.configuration.LocalHost4242;
import io.nagurea.smsupsdk.lists.delete.DeleteListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Only here as an example of how the service could be used with Spring
 */
@Configuration
public class SpringConfiguration implements LocalHost4242 {

    @Bean
    RemoveContactService removeContactService(){
        return new RemoveContactService(ROOT_URL);
    }

}
