package io.nagurea.smsupsdk.common.status;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResponseStatus {

    OK(1, "OK"),
    AUTHENTICATION_ERROR(-1, "Erreur d'authentification"),
    XML_ERROR(-2, "Erreur XML"),
    INSUFFICIENT_CREDIT(-3, "Pas assez de crédits"),
    DATE_DELAY_INCORRECT(-4, "Delai de date incorrect"),
    CONTACT_LIST_ERROR(-5, "Erreur dans la liste de contacts"),
    JSON_ERROR(-6, "Erreur JSON"),
    DATA_ERROR(-7, "Erreur de données"),
    MODERATED_CAMPAIGN(-8, "Votre campagne est actuellement modérée"),
    UNKNOWN_ERROR(-99, "Erreur inconnue"),
    INCONSISTENT_ERROR(-1000, "Erreur incohérente"); //invented by myself

    private final int code;
    private final String description;

    public static ResponseStatus findByCode(int status) {
        final ResponseStatus[] values = values();
        for (ResponseStatus value : values) {
            if(value.code == status){
                return value;
            }
        }
        return INCONSISTENT_ERROR;
    }
}
