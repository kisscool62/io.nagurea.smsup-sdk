package io.nagurea.smsupsdk.sendsms.campaignwithlist.body;

import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class SMS {
    private final Message message;
    private final Set<ListId> lists;
}
