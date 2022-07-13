package io.nagurea.smsupsdk.contacts.blacklist;

import io.nagurea.smsupsdk.common.configuration.LocalHost4242;
import io.nagurea.smsupsdk.contacts.insert.InsertContactInListService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Only here as an example of how the service could be used with Spring
 */
@Configuration
public class SpringConfiguration implements LocalHost4242 {

    @Bean
    AddContactToBlacklistService blacklistService(){
        return new AddContactToBlacklistService(ROOT_URL);
    }

}
