/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BgtInvestDiversionVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.budget.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class BgtInvestDiversionVO extends BusinessObjectMasterVO {
    private String        adjYmd                                            ;
    private Integer       sno                                               ;
    private String        adjRson                                           ;
    private String        adjFg                                             ;
    private String        fromExeAdjYyyymm                                  ;
    private BigDecimal    fromExeAdjAmt                                      = new BigDecimal(0);
    private String        fromRecAdjYyyymm                                  ;
    private BigDecimal    fromRecAdjAmt                                      = new BigDecimal(0);
    private String        toExeAdjYyyymm                                    ;
    private BigDecimal    toExeAdjAmt                                        = new BigDecimal(0);
    private String        toRecAdjYyyymm                                    ;
    private BigDecimal    toRecAdjAmt                                        = new BigDecimal(0);
    private String        isAddCut                                          ;


    public void    setAdjYmd(String adjYmd){
        this.adjYmd = adjYmd;
    }
    public void    setSno(Integer sno){
        this.sno = sno;
    }
    public void    setAdjRson(String adjRson){
        this.adjRson = adjRson;
    }
    public void    setAdjFg(String adjFg){
        this.adjFg = adjFg;
    }
    public void    setFromExeAdjYyyymm(String fromExeAdjYyyymm){
        this.fromExeAdjYyyymm = fromExeAdjYyyymm;
    }
    public void    setFromExeAdjAmt(BigDecimal fromExeAdjAmt){
        this.fromExeAdjAmt = fromExeAdjAmt;
    }
    public void    setFromRecAdjYyyymm(String fromRecAdjYyyymm){
        this.fromRecAdjYyyymm = fromRecAdjYyyymm;
    }
    public void    setFromRecAdjAmt(BigDecimal fromRecAdjAmt){
        this.fromRecAdjAmt = fromRecAdjAmt;
    }
    public void    setToExeAdjYyyymm(String toExeAdjYyyymm){
        this.toExeAdjYyyymm = toExeAdjYyyymm;
    }
    public void    setToExeAdjAmt(BigDecimal toExeAdjAmt){
        this.toExeAdjAmt = toExeAdjAmt;
    }
    public void    setToRecAdjYyyymm(String toRecAdjYyyymm){
        this.toRecAdjYyyymm = toRecAdjYyyymm;
    }
    public void    setToRecAdjAmt(BigDecimal toRecAdjAmt){
        this.toRecAdjAmt = toRecAdjAmt;
    }
    public void    setIsAddCut(String isAddCut){
        this.isAddCut = isAddCut;
    }
    public String getAdjYmd(){
        return adjYmd;
    }
    public Integer getSno(){
        return sno;
    }
    public String getAdjRson(){
        return adjRson;
    }
    public String getAdjFg(){
        return adjFg;
    }
    public String getFromExeAdjYyyymm(){
        return fromExeAdjYyyymm;
    }
    public BigDecimal getFromExeAdjAmt(){
        return fromExeAdjAmt;
    }
    public String getFromRecAdjYyyymm(){
        return fromRecAdjYyyymm;
    }
    public BigDecimal getFromRecAdjAmt(){
        return fromRecAdjAmt;
    }
    public String getToExeAdjYyyymm(){
        return toExeAdjYyyymm;
    }
    public BigDecimal getToExeAdjAmt(){
        return toExeAdjAmt;
    }
    public String getToRecAdjYyyymm(){
        return toRecAdjYyyymm;
    }
    public BigDecimal getToRecAdjAmt(){
        return toRecAdjAmt;
    }
    public String getIsAddCut(){
        return isAddCut;
    }
}

