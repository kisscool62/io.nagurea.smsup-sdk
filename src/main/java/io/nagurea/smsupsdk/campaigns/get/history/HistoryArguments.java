package io.nagurea.smsupsdk.campaigns.get.history;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.nagurea.smsupsdk.common.http.SMSUpURLEncoder.encode;
import static io.nagurea.smsupsdk.helper.json.typeadapter.SMSUP_FORMATS.SMSUP_DATE_FORMAT;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

/**
 * Helper class for optional Arguments. These are not mandatory for UnitMessage action.
 */
@Builder
public class HistoryArguments {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(SMSUP_DATE_FORMAT);

    private static final String START_NAME = "start";
    private static final String LENGTH_NAME = "length";
    private static final String DATESTART_NAME = "date_start";
    private static final String DATEEND_NAME = "date_end";
    private static final String SMSMIN_NAME = "sms_min";
    private static final String SMSMAX_NAME = "sms_max";

    /**
     * You can define the start record for pagination. Default 0
     */
    @Getter private final Integer start;

    /**
     * You can define the number of records to retrieve per request. Default 100. Maximum 1000
     */
    @Getter private final Integer length;

    /**
     * Add a filter to retrieve campaigns of which send date is after this date.
     */
    @SerializedName("date_start")
    @Getter private final LocalDateTime dateStart;

    /**
     * Add a filter to retrieve campaigns of which send date is before this date.
     */
    @SerializedName("date_end")
    @Getter private final LocalDateTime dateEnd;

    /**
     * Add a filter to retrieve campaigns that have a minimum amount of SMS
     */
    @SerializedName("sms_min")
    @Getter private final Integer smsMin;

    /**
     * Add a filter to retrieve campaigns that have a maximum amount of SMS
     */
    @SerializedName("sms_max")
    @Getter private final Integer smsMax;

    public boolean hasAtLeastOneArgument() {
        return start != null ||
               length != null ||
               dateStart != null ||
               dateEnd != null ||
               smsMin != null ||
               smsMax != null;
    }

    public String toUrl(){
        String url = "";
        url += addArgument(url, START_NAME, this.start);
        url += addArgument(url, LENGTH_NAME, this.length);
        url += addArgument(url, DATESTART_NAME, this.dateStart);
        url += addArgument(url, DATEEND_NAME, this.dateEnd);
        url += addArgument(url, SMSMIN_NAME, this.smsMin);
        url += addArgument(url, SMSMAX_NAME, this.smsMax);
        return url;
    }

    private String addArgument(final String url, final String parameterName, final Integer parameterValue) {
        String stringParameterValue = "";
        if(parameterValue != null){
            stringParameterValue = parameterValue.toString();
        }
        return addArgument(url, parameterName, stringParameterValue);
    }

    private String addArgument(final String url, final String parameterName, final LocalDateTime parameterValue) {
        String stringParameterValue = "";
        if(parameterValue != null){
            stringParameterValue = parameterValue.format(DATE_TIME_FORMATTER);
        }
        return addArgument(url, parameterName, stringParameterValue);
    }

    private String addArgument(final String url, final String parameterName, final String parameterValue) {
        String urlPartStart = "";
        if(isNotEmpty(parameterValue)){
            if("".equals(url)) {
                urlPartStart += "?";
            }else{
                urlPartStart = "&";
            }
            return urlPartStart + parameterName + "=" + encode(parameterValue);
        }
        return "";
    }


}
