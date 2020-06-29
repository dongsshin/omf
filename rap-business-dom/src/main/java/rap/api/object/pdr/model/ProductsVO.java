/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProductsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pdr.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProductsVO extends BusinessObjectMasterVO {
    private String        type                                              ;
    private String        useFlag                                           ;


    public void    setType(String type){
        this.type = type;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public String getType(){
        return type;
    }
    public String getUseFlag(){
        return useFlag;
    }
}

