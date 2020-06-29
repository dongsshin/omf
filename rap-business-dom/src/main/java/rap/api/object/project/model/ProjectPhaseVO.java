/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectPhaseVO.java
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
public class ProjectPhaseVO extends BusinessObjectMasterVO {
    private String        codeForSystemControl                              ;
    private String        nameForSystemControl                              ;


    public void    setCodeForSystemControl(String codeForSystemControl){
        this.codeForSystemControl = codeForSystemControl;
    }
    public void    setNameForSystemControl(String nameForSystemControl){
        this.nameForSystemControl = nameForSystemControl;
    }
    public String getCodeForSystemControl(){
        return codeForSystemControl;
    }
    public String getNameForSystemControl(){
        return nameForSystemControl;
    }
}

