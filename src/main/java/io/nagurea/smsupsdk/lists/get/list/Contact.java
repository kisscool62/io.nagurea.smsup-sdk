package io.nagurea.smsupsdk.lists.get.list;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class Contact {

    /**
     * Ex: 5a0331bffc5886074551ce97
     */
    private final String id;

    /**
     * Ex: 41781234567 in E164 format
     */
    private final String destination;

    /**
     * Louis
     */
    private final String info1;

    /**
     * de Broglie
     */
    private final String info2;

    /**
     * 1892
     */
    private final String info3;

    /**
     * Dieppe
     */
    private final String info4;

}
