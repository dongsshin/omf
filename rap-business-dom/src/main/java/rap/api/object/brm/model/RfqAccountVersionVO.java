/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : RfqAccountVersionVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.brm.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class RfqAccountVersionVO extends BusinessObjectMasterVO {
    private String        version                                           ;
    private String        beforeVersion                                     ;
    private String        currentYn                                         ;
    private String        contents                                          ;
    private String        confirmYn                                         ;


    public void    setVersion(String version){
        this.version = version;
    }
    public void    setBeforeVersion(String beforeVersion){
        this.beforeVersion = beforeVersion;
    }
    public void    setCurrentYn(String currentYn){
        this.currentYn = currentYn;
    }
    public void    setContents(String contents){
        this.contents = contents;
    }
    public void    setConfirmYn(String confirmYn){
        this.confirmYn = confirmYn;
    }
    public String getVersion(){
        return version;
    }
    public String getBeforeVersion(){
        return beforeVersion;
    }
    public String getCurrentYn(){
        return currentYn;
    }
    public String getContents(){
        return contents;
    }
    public String getConfirmYn(){
        return confirmYn;
    }
}

