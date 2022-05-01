package io.nagurea.smsupsdk.sendmessages.unitmessage;

import io.nagurea.smsupsdk.sendmessages.campaign.CampaignService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    private static final String ROOT_URL = "http://localhost";

    @Bean
    UnitMessageService unitMessageService(){
        return new UnitMessageService(ROOT_URL);
    }

    @Bean
    CampaignService campaignService(){
        return new CampaignService(ROOT_URL);
    }

}
