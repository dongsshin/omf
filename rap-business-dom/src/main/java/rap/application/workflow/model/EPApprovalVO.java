/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : EPApprovalVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 6. 5.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;




/**
 * <pre>
 * Class : EPApprovalVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class EPApprovalVO {
    private Integer lgepId;
    private Integer seq;
    private String command;
    private Integer systemId     =  163;
    private String systemPk;
    private Integer no;
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private Integer n1;
    private Integer n2;
    private String sabun1;
    private String sabun2;
    private Date date1;
    private Date date2;
    private String url1;
    private String url2;
    private String transferFlag;
    private String msg;
    private Date creationDate;
    private Date transferDate;
    private String taskId;
    
    /**
     * 
     * 
     * @return the seq
     */
    public Integer getSeq(){
        return seq;
    }
    
    /**
     * 
     * 
     * @param seq the seq to set
     */
    public void setSeq(Integer seq){
        this.seq = seq;
    }
    
    /**
     * 
     * 
     * @return the command
     */
    public String getCommand(){
        return command;
    }
    
    /**
     * 
     * 
     * @param command the command to set
     */
    public void setCommand(String command){
        this.command = command;
    }
    
    /**
     * 
     * 
     * @return the systemId
     */
    public Integer getSystemId(){
        return systemId;
    }
    
    /**
     * 
     * 
     * @param systemId the systemId to set
     */
    public void setSystemId(Integer systemId){
        this.systemId = systemId;
    }
    
    /**
     * 
     * 
     * @return the systemPk
     */
    public String getSystemPk(){
        return systemPk;
    }
    
    /**
     * 
     * 
     * @param systemPk the systemPk to set
     */
    public void setSystemPk(String systemPk){
        this.systemPk = systemPk;
    }
    
    /**
     * 
     * 
     * @return the no
     */
    public Integer getNo(){
        return no;
    }
    
    /**
     * 
     * 
     * @param no the no to set
     */
    public void setNo(Integer no){
        this.no = no;
    }
    
    /**
     * 
     * 
     * @return the c1
     */
    public String getC1(){
        return c1;
    }
    
    /**
     * 
     * 
     * @param c1 the c1 to set
     */
    public void setC1(String c1){
        this.c1 = c1;
    }
    
    /**
     * 
     * 
     * @return the c2
     */
    public String getC2(){
        return c2;
    }
    
    /**
     * 
     * 
     * @param c2 the c2 to set
     */
    public void setC2(String c2){
        this.c2 = c2;
    }
    
    /**
     * 
     * 
     * @return the c3
     */
    public String getC3(){
        return c3;
    }
    
    /**
     * 
     * 
     * @param c3 the c3 to set
     */
    public void setC3(String c3){
        this.c3 = c3;
    }
    
    /**
     * 
     * 
     * @return the c4
     */
    public String getC4(){
        return c4;
    }
    
    /**
     * 
     * 
     * @param c4 the c4 to set
     */
    public void setC4(String c4){
        this.c4 = c4;
    }
    
    /**
     * 
     * 
     * @return the c5
     */
    public String getC5(){
        return c5;
    }
    
    /**
     * 
     * 
     * @param c5 the c5 to set
     */
    public void setC5(String c5){
        this.c5 = c5;
    }
    
    /**
     * 
     * 
     * @return the n1
     */
    public Integer getN1(){
        return n1;
    }
    
    /**
     * 
     * 
     * @param n1 the n1 to set
     */
    public void setN1(Integer n1){
        this.n1 = n1;
    }
    
    /**
     * 
     * 
     * @return the n2
     */
    public Integer getN2(){
        return n2;
    }
    
    /**
     * 
     * 
     * @param n2 the n2 to set
     */
    public void setN2(Integer n2){
        this.n2 = n2;
    }
    
    /**
     * 
     * 
     * @return the sabun1
     */
    public String getSabun1(){
        return sabun1;
    }
    
    /**
     * 
     * 
     * @param sabun1 the sabun1 to set
     */
    public void setSabun1(String sabun1){
        this.sabun1 = sabun1;
    }
    
    /**
     * 
     * 
     * @return the sabun2
     */
    public String getSabun2(){
        return sabun2;
    }
    
    /**
     * 
     * 
     * @param sabun2 the sabun2 to set
     */
    public void setSabun2(String sabun2){
        this.sabun2 = sabun2;
    }
    
    /**
     * 
     * 
     * @return the date1
     */
    public Date getDate1(){
        return date1;
    }
    
    /**
     * 
     * 
     * @param date1 the date1 to set
     */
    public void setDate1(Date date1){
        this.date1 = date1;
    }
    
    /**
     * 
     * 
     * @return the date2
     */
    public Date getDate2(){
        return date2;
    }
    
    /**
     * 
     * 
     * @param date2 the date2 to set
     */
    public void setDate2(Date date2){
        this.date2 = date2;
    }
    
    /**
     * 
     * 
     * @return the url1
     */
    public String getUrl1(){
        return url1;
    }
    
    /**
     * 
     * 
     * @param url1 the url1 to set
     */
    public void setUrl1(String url1){
        this.url1 = url1;
    }
    
    /**
     * 
     * 
     * @return the url2
     */
    public String getUrl2(){
        return url2;
    }
    
    /**
     * 
     * 
     * @param url2 the url2 to set
     */
    public void setUrl2(String url2){
        this.url2 = url2;
    }
    
    /**
     * 
     * 
     * @return the transferFlag
     */
    public String getTransferFlag(){
        return transferFlag;
    }
    
    /**
     * 
     * 
     * @param transferFlag the transferFlag to set
     */
    public void setTransferFlag(String transferFlag){
        this.transferFlag = transferFlag;
    }
    
    /**
     * 
     * 
     * @return the msg
     */
    public String getMsg(){
        return msg;
    }
    
    /**
     * 
     * 
     * @param msg the msg to set
     */
    public void setMsg(String msg){
        this.msg = msg;
    }
    
    /**
     * 
     * 
     * @return the creationDate
     */
    public Date getCreationDate(){
        return creationDate;
    }
    
    /**
     * 
     * 
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }
    
    /**
     * 
     * 
     * @return the transferDate
     */
    public Date getTransferDate(){
        return transferDate;
    }
    
    /**
     * 
     * 
     * @param transferDate the transferDate to set
     */
    public void setTransferDate(Date transferDate){
        this.transferDate = transferDate;
    }
    
    /**
     * 
     * 
     * @return the taskId
     */
    public String getTaskId(){
        return taskId;
    }
    
    /**
     * 
     * 
     * @param taskId the taskId to set
     */
    public void setTaskId(String taskId){
        this.taskId = taskId;
    }

    public void setDate1(String date1) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(date1.length() == 19){ format.append(" HH:mm:ss"); }else if(date1.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.date1= transFormat.parse(date1);}
    public void setDate2(String date2) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(date2.length() == 19){ format.append(" HH:mm:ss"); }else if(date2.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.date2= transFormat.parse(date2);}
    public void setCreationDate(String creationDate) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(creationDate.length() == 19){ format.append(" HH:mm:ss"); }else if(creationDate.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.creationDate= transFormat.parse(creationDate);}
    public void setTransferDate(String transferDate) throws ParseException { StringBuffer format = new StringBuffer("yyyy-MM-dd"); if(transferDate.length() == 19){ format.append(" HH:mm:ss"); }else if(transferDate.length() == 16){ format.append(" HH:mm"); } SimpleDateFormat transFormat = new SimpleDateFormat(format.toString()); this.transferDate= transFormat.parse(transferDate);}

    
    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "EPApprovalVO [seq=" + seq + ", command=" + command + ", systemId=" + systemId + ", systemPk="
                + systemPk + ", no=" + no + ", c1=" + c1 + ", c2=" + c2 + ", c3=" + c3 + ", c4=" + c4 + ", c5=" + c5
                + ", n1=" + n1 + ", n2=" + n2 + ", sabun1=" + sabun1 + ", sabun2=" + sabun2 + ", date1=" + date1
                + ", date2=" + date2 + ", url1=" + url1 + ", url2=" + url2 + ", transferFlag=" + transferFlag
                + ", msg=" + msg + ", creationDate=" + creationDate + ", transferDate=" + transferDate + ", taskId="
                + taskId + ", getSeq()=" + getSeq() + ", getCommand()=" + getCommand() + ", getSystemId()="
                + getSystemId() + ", getSystemPk()=" + getSystemPk() + ", getNo()=" + getNo() + ", getC1()=" + getC1()
                + ", getC2()=" + getC2() + ", getC3()=" + getC3() + ", getC4()=" + getC4() + ", getC5()=" + getC5()
                + ", getN1()=" + getN1() + ", getN2()=" + getN2() + ", getSabun1()=" + getSabun1() + ", getSabun2()="
                + getSabun2() + ", getDate1()=" + getDate1() + ", getDate2()=" + getDate2() + ", getUrl1()="
                + getUrl1() + ", getUrl2()=" + getUrl2() + ", getTransferFlag()=" + getTransferFlag() + ", getMsg()="
                + getMsg() + ", getCreationDate()=" + getCreationDate() + ", getTransferDate()=" + getTransferDate()
                + ", getTaskId()=" + getTaskId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    public Integer getLgepId(){
        return lgepId;
    }

    public void setLgepId(Integer lgepId){
        this.lgepId = lgepId;
    }
}
