/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : HasNotificationUserVO.java
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
public class HasNotificationUserVO extends BusinessRelationObjectVO {
    private String        importantYn                                        = "N";
    private String        mailYn                                             = "N";
    private String        readYn                                             = "N";
    private String        deleteYn                                           = "N";
    private String        useYn                                              = "Y";


    public void    setImportantYn(String importantYn){
        this.importantYn = importantYn;
    }
    public void    setMailYn(String mailYn){
        this.mailYn = mailYn;
    }
    public void    setReadYn(String readYn){
        this.readYn = readYn;
    }
    public void    setDeleteYn(String deleteYn){
        this.deleteYn = deleteYn;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getImportantYn(){
        return importantYn;
    }
    public String getMailYn(){
        return mailYn;
    }
    public String getReadYn(){
        return readYn;
    }
    public String getDeleteYn(){
        return deleteYn;
    }
    public String getUseYn(){
        return useYn;
    }
}

