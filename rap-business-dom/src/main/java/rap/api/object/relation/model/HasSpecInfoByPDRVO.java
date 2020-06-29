/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasSpecInfoByPDRVO.java
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
public class HasSpecInfoByPDRVO extends BusinessRelationObjectVO {
    private String        itemName                                          ;
    private String        item1stValue                                      ;
    private String        item2ndValue                                      ;
    private String        categoryName                                      ;
    private String        workdelimitercode                                 ;
    private Integer       spechistno                                        ;
    private String        userInputType                                     ;
    private String        itemCode                                          ;
    private String        valueCode                                         ;
    private String        itemTypeCode                                      ;
    private Integer       itemOrderValue                                    ;
    private String        categoryCode                                      ;
    private Integer       categoryOrderValue                                ;
    private String        previousItem1stValue                              ;
    private String        previousItem2ndValue                              ;


    public void    setItemName(String itemName){
        this.itemName = itemName;
    }
    public void    setItem1stValue(String item1stValue){
        this.item1stValue = item1stValue;
    }
    public void    setItem2ndValue(String item2ndValue){
        this.item2ndValue = item2ndValue;
    }
    public void    setCategoryName(String categoryName){
        this.categoryName = categoryName;
    }
    public void    setWorkdelimitercode(String workdelimitercode){
        this.workdelimitercode = workdelimitercode;
    }
    public void    setSpechistno(Integer spechistno){
        this.spechistno = spechistno;
    }
    public void    setUserInputType(String userInputType){
        this.userInputType = userInputType;
    }
    public void    setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    public void    setValueCode(String valueCode){
        this.valueCode = valueCode;
    }
    public void    setItemTypeCode(String itemTypeCode){
        this.itemTypeCode = itemTypeCode;
    }
    public void    setItemOrderValue(Integer itemOrderValue){
        this.itemOrderValue = itemOrderValue;
    }
    public void    setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode;
    }
    public void    setCategoryOrderValue(Integer categoryOrderValue){
        this.categoryOrderValue = categoryOrderValue;
    }
    public void    setPreviousItem1stValue(String previousItem1stValue){
        this.previousItem1stValue = previousItem1stValue;
    }
    public void    setPreviousItem2ndValue(String previousItem2ndValue){
        this.previousItem2ndValue = previousItem2ndValue;
    }
    public String getItemName(){
        return itemName;
    }
    public String getItem1stValue(){
        return item1stValue;
    }
    public String getItem2ndValue(){
        return item2ndValue;
    }
    public String getCategoryName(){
        return categoryName;
    }
    public String getWorkdelimitercode(){
        return workdelimitercode;
    }
    public Integer getSpechistno(){
        return spechistno;
    }
    public String getUserInputType(){
        return userInputType;
    }
    public String getItemCode(){
        return itemCode;
    }
    public String getValueCode(){
        return valueCode;
    }
    public String getItemTypeCode(){
        return itemTypeCode;
    }
    public Integer getItemOrderValue(){
        return itemOrderValue;
    }
    public String getCategoryCode(){
        return categoryCode;
    }
    public Integer getCategoryOrderValue(){
        return categoryOrderValue;
    }
    public String getPreviousItem1stValue(){
        return previousItem1stValue;
    }
    public String getPreviousItem2ndValue(){
        return previousItem2ndValue;
    }
}

