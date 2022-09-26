package io.nagurea.smsupsdk.accountmanaging.dataretention.update.body;

import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.CampaignRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.ListRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.MessageRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.SurveyRetention;
import io.nagurea.smsupsdk.accountmanaging.dataretention.update.body.retentiontime.common.ValidRetention;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@Getter
public class DataRetentionInfo {

   @Valid
   private final MessageRetention message;

   @Valid
   private final SurveyRetention survey;

   @Valid
   private final ListRetention list;

   @Valid
   private final CampaignRetention campaign;

   public boolean isValid(){
      return isValid(message) && isValid(survey) && isValid(list) && isValid(campaign);
   }

   public boolean isValid(ValidRetention retention){
      return retention == null || retention.isValid();
   }


}
