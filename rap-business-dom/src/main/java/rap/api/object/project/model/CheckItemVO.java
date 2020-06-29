/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CheckItemVO.java
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
public class CheckItemVO extends BusinessObjectMasterVO {
    private String        itemName                                          ;
    private String        itemNameEng                                       ;
    private Integer       sortOrderNo                                       ;
    private String        useFlag                                           ;
    private String        commonFlag                                        ;
    private String        subtractPointFlag                                 ;


    public void    setItemName(String itemName){
        this.itemName = itemName;
    }
    public void    setItemNameEng(String itemNameEng){
        this.itemNameEng = itemNameEng;
    }
    public void    setSortOrderNo(Integer sortOrderNo){
        this.sortOrderNo = sortOrderNo;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setCommonFlag(String commonFlag){
        this.commonFlag = commonFlag;
    }
    public void    setSubtractPointFlag(String subtractPointFlag){
        this.subtractPointFlag = subtractPointFlag;
    }
    public String getItemName(){
        return itemName;
    }
    public String getItemNameEng(){
        return itemNameEng;
    }
    public Integer getSortOrderNo(){
        return sortOrderNo;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getCommonFlag(){
        return commonFlag;
    }
    public String getSubtractPointFlag(){
        return subtractPointFlag;
    }
}

