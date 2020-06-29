/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : MCRoyaltyCheckVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class MCRoyaltyCheckVO extends BusinessObjectMasterVO {
    private String        region                                            ;
    private String        country                                           ;
    private String        plId                                              ;
    private Date          royaltyCheckDate                                  ;
    private String        royaltyCheckYn                                    ;


    public void    setRegion(String region){
        this.region = region;
    }
    public void    setCountry(String country){
        this.country = country;
    }
    public void    setPlId(String plId){
        this.plId = plId;
    }
    public void    setRoyaltyCheckDate(Date royaltyCheckDate){
        this.royaltyCheckDate = royaltyCheckDate;
    }
    public void    setRoyaltyCheckDate(String    royaltyCheckDate){
        this.royaltyCheckDate = this.omcConvertStr2Date(royaltyCheckDate);
    }
    public void    setRoyaltyCheckYn(String royaltyCheckYn){
        this.royaltyCheckYn = royaltyCheckYn;
    }
    public String getRegion(){
        return region;
    }
    public String getCountry(){
        return country;
    }
    public String getPlId(){
        return plId;
    }
    public Date getRoyaltyCheckDate(){
        return royaltyCheckDate;
    }
    public String getRoyaltyCheckYn(){
        return royaltyCheckYn;
    }
}

