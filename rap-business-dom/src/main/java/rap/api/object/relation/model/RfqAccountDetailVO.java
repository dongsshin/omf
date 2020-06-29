/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : RfqAccountDetailVO.java
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
public class RfqAccountDetailVO extends BusinessRelationObjectVO {
    private String        assetYn                                           ;
    private String        accountCategory                                   ;
    private String        useYn                                             ;


    public void    setAssetYn(String assetYn){
        this.assetYn = assetYn;
    }
    public void    setAccountCategory(String accountCategory){
        this.accountCategory = accountCategory;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getAssetYn(){
        return assetYn;
    }
    public String getAccountCategory(){
        return accountCategory;
    }
    public String getUseYn(){
        return useYn;
    }
}

