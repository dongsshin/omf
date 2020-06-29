/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectUserTimeCardVO.java
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
public class ProjectUserTimeCardVO extends BusinessRelationObjectVO {
    private Integer       dgr                                               ;
    private String        yearMonth                                         ;
    private Integer       mhD01                                              = 0;
    private Integer       mhD02                                              = 0;
    private Integer       mhD03                                              = 0;
    private Integer       mhD04                                              = 0;
    private Integer       mhD05                                              = 0;
    private Integer       mhD06                                              = 0;
    private Integer       mhD07                                              = 0;
    private Integer       mhD08                                              = 0;
    private Integer       mhD09                                              = 0;
    private Integer       mhD10                                              = 0;
    private Integer       mhD11                                              = 0;
    private Integer       mhD12                                              = 0;
    private Integer       mhD13                                              = 0;
    private Integer       mhD14                                              = 0;
    private Integer       mhD15                                              = 0;
    private Integer       mhD16                                              = 0;
    private Integer       mhD17                                              = 0;
    private Integer       mhD18                                              = 0;
    private Integer       mhD19                                              = 0;
    private Integer       mhD20                                              = 0;
    private Integer       mhD21                                              = 0;
    private Integer       mhD22                                              = 0;
    private Integer       mhD23                                              = 0;
    private Integer       mhD24                                              = 0;
    private Integer       mhD25                                              = 0;
    private Integer       mhD26                                              = 0;
    private Integer       mhD27                                              = 0;
    private Integer       mhD28                                              = 0;
    private Integer       mhD29                                              = 0;
    private Integer       mhD30                                              = 0;
    private Integer       mhD31                                              = 0;
    private String        saveFlag                                          ;
    private Float         mm                                                ;


    public void    setDgr(Integer dgr){
        this.dgr = dgr;
    }
    public void    setYearMonth(String yearMonth){
        this.yearMonth = yearMonth;
    }
    public void    setMhD01(Integer mhD01){
        this.mhD01 = mhD01;
    }
    public void    setMhD02(Integer mhD02){
        this.mhD02 = mhD02;
    }
    public void    setMhD03(Integer mhD03){
        this.mhD03 = mhD03;
    }
    public void    setMhD04(Integer mhD04){
        this.mhD04 = mhD04;
    }
    public void    setMhD05(Integer mhD05){
        this.mhD05 = mhD05;
    }
    public void    setMhD06(Integer mhD06){
        this.mhD06 = mhD06;
    }
    public void    setMhD07(Integer mhD07){
        this.mhD07 = mhD07;
    }
    public void    setMhD08(Integer mhD08){
        this.mhD08 = mhD08;
    }
    public void    setMhD09(Integer mhD09){
        this.mhD09 = mhD09;
    }
    public void    setMhD10(Integer mhD10){
        this.mhD10 = mhD10;
    }
    public void    setMhD11(Integer mhD11){
        this.mhD11 = mhD11;
    }
    public void    setMhD12(Integer mhD12){
        this.mhD12 = mhD12;
    }
    public void    setMhD13(Integer mhD13){
        this.mhD13 = mhD13;
    }
    public void    setMhD14(Integer mhD14){
        this.mhD14 = mhD14;
    }
    public void    setMhD15(Integer mhD15){
        this.mhD15 = mhD15;
    }
    public void    setMhD16(Integer mhD16){
        this.mhD16 = mhD16;
    }
    public void    setMhD17(Integer mhD17){
        this.mhD17 = mhD17;
    }
    public void    setMhD18(Integer mhD18){
        this.mhD18 = mhD18;
    }
    public void    setMhD19(Integer mhD19){
        this.mhD19 = mhD19;
    }
    public void    setMhD20(Integer mhD20){
        this.mhD20 = mhD20;
    }
    public void    setMhD21(Integer mhD21){
        this.mhD21 = mhD21;
    }
    public void    setMhD22(Integer mhD22){
        this.mhD22 = mhD22;
    }
    public void    setMhD23(Integer mhD23){
        this.mhD23 = mhD23;
    }
    public void    setMhD24(Integer mhD24){
        this.mhD24 = mhD24;
    }
    public void    setMhD25(Integer mhD25){
        this.mhD25 = mhD25;
    }
    public void    setMhD26(Integer mhD26){
        this.mhD26 = mhD26;
    }
    public void    setMhD27(Integer mhD27){
        this.mhD27 = mhD27;
    }
    public void    setMhD28(Integer mhD28){
        this.mhD28 = mhD28;
    }
    public void    setMhD29(Integer mhD29){
        this.mhD29 = mhD29;
    }
    public void    setMhD30(Integer mhD30){
        this.mhD30 = mhD30;
    }
    public void    setMhD31(Integer mhD31){
        this.mhD31 = mhD31;
    }
    public void    setSaveFlag(String saveFlag){
        this.saveFlag = saveFlag;
    }
    public void    setMm(Float mm){
        this.mm = mm;
    }
    public Integer getDgr(){
        return dgr;
    }
    public String getYearMonth(){
        return yearMonth;
    }
    public Integer getMhD01(){
        return mhD01;
    }
    public Integer getMhD02(){
        return mhD02;
    }
    public Integer getMhD03(){
        return mhD03;
    }
    public Integer getMhD04(){
        return mhD04;
    }
    public Integer getMhD05(){
        return mhD05;
    }
    public Integer getMhD06(){
        return mhD06;
    }
    public Integer getMhD07(){
        return mhD07;
    }
    public Integer getMhD08(){
        return mhD08;
    }
    public Integer getMhD09(){
        return mhD09;
    }
    public Integer getMhD10(){
        return mhD10;
    }
    public Integer getMhD11(){
        return mhD11;
    }
    public Integer getMhD12(){
        return mhD12;
    }
    public Integer getMhD13(){
        return mhD13;
    }
    public Integer getMhD14(){
        return mhD14;
    }
    public Integer getMhD15(){
        return mhD15;
    }
    public Integer getMhD16(){
        return mhD16;
    }
    public Integer getMhD17(){
        return mhD17;
    }
    public Integer getMhD18(){
        return mhD18;
    }
    public Integer getMhD19(){
        return mhD19;
    }
    public Integer getMhD20(){
        return mhD20;
    }
    public Integer getMhD21(){
        return mhD21;
    }
    public Integer getMhD22(){
        return mhD22;
    }
    public Integer getMhD23(){
        return mhD23;
    }
    public Integer getMhD24(){
        return mhD24;
    }
    public Integer getMhD25(){
        return mhD25;
    }
    public Integer getMhD26(){
        return mhD26;
    }
    public Integer getMhD27(){
        return mhD27;
    }
    public Integer getMhD28(){
        return mhD28;
    }
    public Integer getMhD29(){
        return mhD29;
    }
    public Integer getMhD30(){
        return mhD30;
    }
    public Integer getMhD31(){
        return mhD31;
    }
    public String getSaveFlag(){
        return saveFlag;
    }
    public Float getMm(){
        return mm;
    }
}

