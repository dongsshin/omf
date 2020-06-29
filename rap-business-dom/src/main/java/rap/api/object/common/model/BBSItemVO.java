/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BBSItemVO.java
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
public class BBSItemVO extends BusinessObjectMasterVO {
    private String        bbsType                                           ;
    private String        module                                            ;
    private String        itemType                                          ;
    private Date          publishStartDate                                  ;
    private Date          publishEndDate                                    ;
    private String        realCreator                                       ;
    private String        keyword                                           ;
    private String        obidUpper                                          = "Y";
    private Integer       visitedCount                                       = 0;
    private String        businessUnitCode                                  ;
    private String        bbsContents                                       ;
    private String        divisions                                         ;
    private String        divisionCode                                      ;
    private String        searchType                                        ;


    public void    setBbsType(String bbsType){
        this.bbsType = bbsType;
    }
    public void    setModule(String module){
        this.module = module;
    }
    public void    setItemType(String itemType){
        this.itemType = itemType;
    }
    public void    setPublishStartDate(Date publishStartDate){
        this.publishStartDate = publishStartDate;
    }
    public void    setPublishStartDate(String    publishStartDate){
        this.publishStartDate = this.omcConvertStr2Date(publishStartDate);
    }
    public void    setPublishEndDate(Date publishEndDate){
        this.publishEndDate = publishEndDate;
    }
    public void    setPublishEndDate(String    publishEndDate){
        this.publishEndDate = this.omcConvertStr2Date(publishEndDate);
    }
    public void    setRealCreator(String realCreator){
        this.realCreator = realCreator;
    }
    public void    setKeyword(String keyword){
        this.keyword = keyword;
    }
    public void    setObidUpper(String obidUpper){
        this.obidUpper = obidUpper;
    }
    public void    setVisitedCount(Integer visitedCount){
        this.visitedCount = visitedCount;
    }
    public void    setBusinessUnitCode(String businessUnitCode){
        this.businessUnitCode = businessUnitCode;
    }
    public void    setBbsContents(String bbsContents){
        this.bbsContents = bbsContents;
    }
    public void    setDivisions(String divisions){
        this.divisions = divisions;
    }
    public void    setDivisionCode(String divisionCode){
        this.divisionCode = divisionCode;
    }
    public void    setSearchType(String searchType){
        this.searchType = searchType;
    }
    public String getBbsType(){
        return bbsType;
    }
    public String getModule(){
        return module;
    }
    public String getItemType(){
        return itemType;
    }
    public Date getPublishStartDate(){
        return publishStartDate;
    }
    public Date getPublishEndDate(){
        return publishEndDate;
    }
    public String getRealCreator(){
        return realCreator;
    }
    public String getKeyword(){
        return keyword;
    }
    public String getObidUpper(){
        return obidUpper;
    }
    public Integer getVisitedCount(){
        return visitedCount;
    }
    public String getBusinessUnitCode(){
        return businessUnitCode;
    }
    public String getBbsContents(){
        return bbsContents;
    }
    public String getDivisions(){
        return divisions;
    }
    public String getDivisionCode(){
        return divisionCode;
    }
    public String getSearchType(){
        return searchType;
    }
}

