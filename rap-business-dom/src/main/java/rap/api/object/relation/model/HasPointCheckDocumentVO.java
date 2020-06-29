/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasPointCheckDocumentVO.java
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
public class HasPointCheckDocumentVO extends BusinessRelationObjectVO {
    private String        itemTitleKr                                       ;
    private String        itemTitleEn                                       ;


    public void    setItemTitleKr(String itemTitleKr){
        this.itemTitleKr = itemTitleKr;
    }
    public void    setItemTitleEn(String itemTitleEn){
        this.itemTitleEn = itemTitleEn;
    }
    public String getItemTitleKr(){
        return itemTitleKr;
    }
    public String getItemTitleEn(){
        return itemTitleEn;
    }
}

