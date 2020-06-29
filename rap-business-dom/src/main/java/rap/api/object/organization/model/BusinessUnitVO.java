/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BusinessUnitVO.java
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
public class BusinessUnitVO extends OrganizationsVO {
    private String        companyCode                                       ;


    public void    setCompanyCode(String companyCode){
        this.companyCode = companyCode;
    }
    public String getCompanyCode(){
        return companyCode;
    }
}

