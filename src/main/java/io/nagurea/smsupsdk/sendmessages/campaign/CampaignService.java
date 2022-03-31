package io.nagurea.smsupsdk.sendmessages.campaign;

import io.nagurea.smsupsdk.common.post.POSTSMSUpService;

public class CampaignService extends POSTSMSUpService {

    protected CampaignService(String rootUrl) {
        super(rootUrl);
    }

    public CampaignResponse send(){
        //TODO build and return response
        return CampaignResponse.builder().build();
    }
}
