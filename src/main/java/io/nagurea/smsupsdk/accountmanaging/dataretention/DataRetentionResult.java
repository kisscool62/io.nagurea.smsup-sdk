package io.nagurea.smsupsdk.accountmanaging.dataretention;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode
public class DataRetentionResult {

    private final String message;
    private final String list;
    private final String survey;
    private final String campaign;

    @Builder
    public DataRetentionResult(String message, String list, String survey, String campaign) {
        super();
        this.message = message;
        this.list = list;
        this.survey = survey;
        this.campaign = campaign;
    }
}
