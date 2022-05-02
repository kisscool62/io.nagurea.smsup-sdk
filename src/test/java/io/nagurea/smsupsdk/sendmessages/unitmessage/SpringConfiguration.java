package io.nagurea.smsupsdk.sendmessages.unitmessage;

import io.nagurea.smsupsdk.sendmessages.campaign.CampaignService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Only here as an exemple of how the service could be used with Spring
 */
@Configuration
public class SpringConfiguration {

    private static final String ROOT_URL = "http://localhost:4242";

    @Bean
    UnitMessageService unitMessageService(){
        return new UnitMessageService(ROOT_URL);
    }

    @Bean
    CampaignService campaignService(){
        return new CampaignService(ROOT_URL);
    }

}
