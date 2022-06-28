package io.nagurea.smsupsdk.helper;

import io.nagurea.smsupsdk.common.exception.TextWithoutShortPatternException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShortLinkHelper {
    private static final String SHORT_PATTERN = "<-short->";
    private static final Pattern shortLinkPattern = Pattern.compile(SHORT_PATTERN);

    public static void checkLinkListAndTextConsistent(List<String> links, String text){
        final Matcher matcher = shortLinkPattern.matcher(text);
        final long countMatch = matcher.results().count();
        if(countMatch != links.size()){
            throw TextWithoutShortPatternException.newTextWithoutShortPatternException(text,SHORT_PATTERN );
        }
    }
}
