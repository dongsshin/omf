/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : BgtDiversionVO.java
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
public class BgtDiversionVO extends BusinessObjectMasterVO {
    private String        diverYmd                                          ;
    private String        diverFg                                           ;
    private String        diverRson                                         ;
    private BigDecimal    diverAmt                                           = new BigDecimal(0);
    private String        giverDeptCd                                       ;
    private String        giverPjtCd                                        ;
    private String        giverAcctCd                                       ;
    private String        giverYyyymm                                       ;
    private BigDecimal    giverAmt                                           = new BigDecimal(0);
    private String        receiveDeptCd                                     ;
    private String        receivePjtCd                                      ;
    private String        receiveAcctCd                                     ;
    private String        receiveYyyymm                                     ;
    private BigDecimal    receiveAmt                                         = new BigDecimal(0);
    private String        bmsReflYn                                         ;
    private String        consCd                                            ;
    private String        headerId                                          ;
    private String        headerSno                                         ;
    private Integer       lineId                                            ;
    private Integer       dgr                                               ;
    private String        giverInvCd                                        ;
    private Integer       giverInvSeq                                       ;
    private BigDecimal    giverInvAmt                                        = new BigDecimal(0);
    private String        receiveInvCd                                      ;
    private Integer       receiveInvSeq                                     ;
    private BigDecimal    receiveInvAmt                                      = new BigDecimal(0);
    private String        consultYn                                         ;


    public void    setDiverYmd(String diverYmd){
        this.diverYmd = diverYmd;
    }
    public void    setDiverFg(String diverFg){
        this.diverFg = diverFg;
    }
    public void    setDiverRson(String diverRson){
        this.diverRson = diverRson;
    }
    public void    setDiverAmt(BigDecimal diverAmt){
        this.diverAmt = diverAmt;
    }
    public void    setGiverDeptCd(String giverDeptCd){
        this.giverDeptCd = giverDeptCd;
    }
    public void    setGiverPjtCd(String giverPjtCd){
        this.giverPjtCd = giverPjtCd;
    }
    public void    setGiverAcctCd(String giverAcctCd){
        this.giverAcctCd = giverAcctCd;
    }
    public void    setGiverYyyymm(String giverYyyymm){
        this.giverYyyymm = giverYyyymm;
    }
    public void    setGiverAmt(BigDecimal giverAmt){
        this.giverAmt = giverAmt;
    }
    public void    setReceiveDeptCd(String receiveDeptCd){
        this.receiveDeptCd = receiveDeptCd;
    }
    public void    setReceivePjtCd(String receivePjtCd){
        this.receivePjtCd = receivePjtCd;
    }
    public void    setReceiveAcctCd(String receiveAcctCd){
        this.receiveAcctCd = receiveAcctCd;
    }
    public void    setReceiveYyyymm(String receiveYyyymm){
        this.receiveYyyymm = receiveYyyymm;
    }
    public void    setReceiveAmt(BigDecimal receiveAmt){
        this.receiveAmt = receiveAmt;
    }
    public void    setBmsReflYn(String bmsReflYn){
        this.bmsReflYn = bmsReflYn;
    }
    public void    setConsCd(String consCd){
        this.consCd = consCd;
    }
    public void    setHeaderId(String headerId){
        this.headerId = headerId;
    }
    public void    setHeaderSno(String headerSno){
        this.headerSno = headerSno;
    }
    public void    setLineId(Integer lineId){
        this.lineId = lineId;
    }
    public void    setDgr(Integer dgr){
        this.dgr = dgr;
    }
    public void    setGiverInvCd(String giverInvCd){
        this.giverInvCd = giverInvCd;
    }
    public void    setGiverInvSeq(Integer giverInvSeq){
        this.giverInvSeq = giverInvSeq;
    }
    public void    setGiverInvAmt(BigDecimal giverInvAmt){
        this.giverInvAmt = giverInvAmt;
    }
    public void    setReceiveInvCd(String receiveInvCd){
        this.receiveInvCd = receiveInvCd;
    }
    public void    setReceiveInvSeq(Integer receiveInvSeq){
        this.receiveInvSeq = receiveInvSeq;
    }
    public void    setReceiveInvAmt(BigDecimal receiveInvAmt){
        this.receiveInvAmt = receiveInvAmt;
    }
    public void    setConsultYn(String consultYn){
        this.consultYn = consultYn;
    }
    public String getDiverYmd(){
        return diverYmd;
    }
    public String getDiverFg(){
        return diverFg;
    }
    public String getDiverRson(){
        return diverRson;
    }
    public BigDecimal getDiverAmt(){
        return diverAmt;
    }
    public String getGiverDeptCd(){
        return giverDeptCd;
    }
    public String getGiverPjtCd(){
        return giverPjtCd;
    }
    public String getGiverAcctCd(){
        return giverAcctCd;
    }
    public String getGiverYyyymm(){
        return giverYyyymm;
    }
    public BigDecimal getGiverAmt(){
        return giverAmt;
    }
    public String getReceiveDeptCd(){
        return receiveDeptCd;
    }
    public String getReceivePjtCd(){
        return receivePjtCd;
    }
    public String getReceiveAcctCd(){
        return receiveAcctCd;
    }
    public String getReceiveYyyymm(){
        return receiveYyyymm;
    }
    public BigDecimal getReceiveAmt(){
        return receiveAmt;
    }
    public String getBmsReflYn(){
        return bmsReflYn;
    }
    public String getConsCd(){
        return consCd;
    }
    public String getHeaderId(){
        return headerId;
    }
    public String getHeaderSno(){
        return headerSno;
    }
    public Integer getLineId(){
        return lineId;
    }
    public Integer getDgr(){
        return dgr;
    }
    public String getGiverInvCd(){
        return giverInvCd;
    }
    public Integer getGiverInvSeq(){
        return giverInvSeq;
    }
    public BigDecimal getGiverInvAmt(){
        return giverInvAmt;
    }
    public String getReceiveInvCd(){
        return receiveInvCd;
    }
    public Integer getReceiveInvSeq(){
        return receiveInvSeq;
    }
    public BigDecimal getReceiveInvAmt(){
        return receiveInvAmt;
    }
    public String getConsultYn(){
        return consultYn;
    }
}

