/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AffiliateUnitVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.organization.model;


import rap.api.object.organization.model.OrganizationsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class AffiliateUnitVO extends OrganizationsVO {
    private String        useFlag                                           ;


    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

