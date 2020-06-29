/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OemProjectTemplateVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OemProjectTemplateVO extends BusinessObjectMasterVO {
    private Integer       sequences                                          = 1;
    private String        oemActivityName                                   ;
    private String        oemDocumentName                                   ;
    private String        oemName                                           ;


    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setOemActivityName(String oemActivityName){
        this.oemActivityName = oemActivityName;
    }
    public void    setOemDocumentName(String oemDocumentName){
        this.oemDocumentName = oemDocumentName;
    }
    public void    setOemName(String oemName){
        this.oemName = oemName;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getOemActivityName(){
        return oemActivityName;
    }
    public String getOemDocumentName(){
        return oemDocumentName;
    }
    public String getOemName(){
        return oemName;
    }
}

