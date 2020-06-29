package com.rap.util;

import rap.api.object.organization.dom.Company;
import rap.api.object.organization.model.CompanyVO;

public class BusinessCommonConstants {
    // User Property
    public static final String USER_PROP_BUSINESS_UNIT = "Business Unit Responsibility";
    public static final String USER_PROP_AFFILIATE_UNIT = "Affiliate Responsibility";
    public static final String USER_PROP_SITE = "Manufacturing Responsibility";
    public static final String USER_PROP_RESEARCH_CENTER = "Research Responsibility";
    public static final String USER_PROP_DIVISION = "Design Responsibility";
    public static final String USER_PROP_DAYLIGHT_SAVINGS = "Daylight Savings";
    public static final String USER_PROP_TIME_ZONE = "Time Zone";
    public static final String USER_PROP_LOCALE = "Locale";
    public static final String USER_PROP_MANAGEMENTGROUP = "Management Group";
    
    public static final String DELIM_1 = ":";
    public static final String DELIM_1_S = "\\:";
    public static final String DELIM_2 = ",";
    public static final String DELIM_2_S = "\\,";
    public static final CompanyVO MAIN_COMPANY_VO = Company.getMainCompany();
    public static final String MAIN_COMPANY_NAME = MAIN_COMPANY_VO.getNames();
}