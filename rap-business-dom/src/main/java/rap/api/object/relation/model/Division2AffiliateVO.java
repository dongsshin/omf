/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Division2AffiliateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.relation.model;


import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class Division2AffiliateVO extends BusinessRelationObjectVO {
    private String        useYn                                              = "Y";
    private String        devYn                                              = "Y";
    private String        prodYn                                             = "Y";


    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setDevYn(String devYn){
        this.devYn = devYn;
    }
    public void    setProdYn(String prodYn){
        this.prodYn = prodYn;
    }
    public String getUseYn(){
        return useYn;
    }
    public String getDevYn(){
        return devYn;
    }
    public String getProdYn(){
        return prodYn;
    }
}

