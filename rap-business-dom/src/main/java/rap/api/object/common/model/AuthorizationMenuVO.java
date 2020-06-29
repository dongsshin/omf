/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AuthorizationMenuVO.java
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
public class AuthorizationMenuVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        useYn                                             ;
    private String        crudFlag                                          ;
    private String        orgRange                                          ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setCrudFlag(String crudFlag){
        this.crudFlag = crudFlag;
    }
    public void    setOrgRange(String orgRange){
        this.orgRange = orgRange;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getCrudFlag(){
        return crudFlag;
    }
    public String getOrgRange(){
        return orgRange;
    }
}

