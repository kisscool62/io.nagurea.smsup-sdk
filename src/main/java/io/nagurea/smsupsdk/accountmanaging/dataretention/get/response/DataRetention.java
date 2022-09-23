package io.nagurea.smsupsdk.accountmanaging.dataretention.get.response;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode
public class DataRetention {

    private final String message;
    private final String list;
    private final String survey;
    private final String campaign;

    @Builder
    public DataRetention(String message, String list, String survey, String campaign) {
        super();
        this.message = message;
        this.list = list;
        this.survey = survey;
        this.campaign = campaign;
    }
}
