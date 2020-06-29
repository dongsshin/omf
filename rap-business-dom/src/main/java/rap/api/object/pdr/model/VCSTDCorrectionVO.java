/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : VCSTDCorrectionVO.java
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
public class VCSTDCorrectionVO extends BusinessObjectMasterVO {
    private String        vcStdVersion                                      ;
    private String        vcDevSeparate                                     ;
    private String        vcMatrixTitle                                     ;
    private String        vcMatrixType                                      ;
    private Float         vcMatrixCorrection                                ;


    public void    setVcStdVersion(String vcStdVersion){
        this.vcStdVersion = vcStdVersion;
    }
    public void    setVcDevSeparate(String vcDevSeparate){
        this.vcDevSeparate = vcDevSeparate;
    }
    public void    setVcMatrixTitle(String vcMatrixTitle){
        this.vcMatrixTitle = vcMatrixTitle;
    }
    public void    setVcMatrixType(String vcMatrixType){
        this.vcMatrixType = vcMatrixType;
    }
    public void    setVcMatrixCorrection(Float vcMatrixCorrection){
        this.vcMatrixCorrection = vcMatrixCorrection;
    }
    public String getVcStdVersion(){
        return vcStdVersion;
    }
    public String getVcDevSeparate(){
        return vcDevSeparate;
    }
    public String getVcMatrixTitle(){
        return vcMatrixTitle;
    }
    public String getVcMatrixType(){
        return vcMatrixType;
    }
    public Float getVcMatrixCorrection(){
        return vcMatrixCorrection;
    }
}

