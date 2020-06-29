/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : LegacyLinkVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class LegacyLinkVO extends BusinessObjectMasterVO {
    private String        systemName                                        ;
    private String        systemUrl                                         ;
    private String        systemDescription                                 ;
    private String        company                                           ;
    private String        division                                          ;


    public void    setSystemName(String systemName){
        this.systemName = systemName;
    }
    public void    setSystemUrl(String systemUrl){
        this.systemUrl = systemUrl;
    }
    public void    setSystemDescription(String systemDescription){
        this.systemDescription = systemDescription;
    }
    public void    setCompany(String company){
        this.company = company;
    }
    public void    setDivision(String division){
        this.division = division;
    }
    public String getSystemName(){
        return systemName;
    }
    public String getSystemUrl(){
        return systemUrl;
    }
    public String getSystemDescription(){
        return systemDescription;
    }
    public String getCompany(){
        return company;
    }
    public String getDivision(){
        return division;
    }
}

