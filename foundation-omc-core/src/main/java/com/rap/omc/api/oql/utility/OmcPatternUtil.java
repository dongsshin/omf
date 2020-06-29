package com.rap.omc.api.oql.utility;


public class OmcPatternUtil {
    public static String makeSelectPattern(String alias, String attribute){
        return((new StringBuffer(alias)).append(".").append(OmcFoundationConstant.OQL_SELECT_ATTR_LEFT).append(attribute).append(OmcFoundationConstant.OQL_SELECT_ATTR_RIGHT).toString());
    }
}
