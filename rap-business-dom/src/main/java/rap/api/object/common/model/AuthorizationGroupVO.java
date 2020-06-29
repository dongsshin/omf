/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AuthorizationGroupVO.java
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
public class AuthorizationGroupVO extends BusinessObjectMasterVO {
    private String        module                                            ;
    private String        useYn                                             ;
    private String        engTitles                                         ;
    private String        businessUnitCode                                  ;


    public void    setModule(String module){
        this.module = module;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setEngTitles(String engTitles){
        this.engTitles = engTitles;
    }
    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public String getModule(){
        return module;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getEngTitles(){
        return engTitles;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
}

