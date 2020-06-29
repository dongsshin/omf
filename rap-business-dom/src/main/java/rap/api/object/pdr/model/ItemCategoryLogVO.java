/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ItemCategoryLogVO.java
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
public class ItemCategoryLogVO extends BusinessObjectMasterVO {
    private String        fromObid                                          ;
    private String        opType                                            ;
    private Integer       categoryOrderValue                                ;
    private Integer       rowItemQty                                        ;
    private String        useFlag                                           ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;
    private String        categoryType                                      ;


    public void    setFromObid(String fromObid){
        this.fromObid = fromObid;
    }
    public void    setOpType(String opType){
        this.opType = opType;
    }
    public void    setCategoryOrderValue(Integer categoryOrderValue){
        this.categoryOrderValue = categoryOrderValue;
    }
    public void    setRowItemQty(Integer rowItemQty){
        this.rowItemQty = rowItemQty;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public void    setCategoryType(String categoryType){
        this.categoryType = categoryType;
    }
    public String getFromObid(){
        return fromObid;
    }
    public String getOpType(){
        return opType;
    }
    public Integer getCategoryOrderValue(){
        return categoryOrderValue;
    }
    public Integer getRowItemQty(){
        return rowItemQty;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
    public String getCategoryType(){
        return categoryType;
    }
}

