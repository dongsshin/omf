/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignRequestsContentsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.requests.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class DesignRequestsContentsVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        contentItemTypeCode                               ;
    private String        contentItemCode                                   ;
    private String        contentItemValue                                  ;
    private Integer       sortOrderNo                                       ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setContentItemTypeCode(String contentItemTypeCode){
        this.contentItemTypeCode = contentItemTypeCode;
    }
    public void    setContentItemCode(String contentItemCode){
        this.contentItemCode = contentItemCode;
    }
    public void    setContentItemValue(String contentItemValue){
        this.contentItemValue = contentItemValue;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getContentItemTypeCode(){
        return contentItemTypeCode;
    }
    public String getContentItemCode(){
        return contentItemCode;
    }
    public String getContentItemValue(){
        return contentItemValue;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
}

