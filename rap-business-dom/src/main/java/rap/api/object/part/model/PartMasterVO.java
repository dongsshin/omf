/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.part.model;


import rap.api.object.part.model.PartMasterRootVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartMasterVO extends PartMasterRootVO {
    private String        classCode                                         ;


    public void    setClassCode(String classCode){
        this.classCode = classCode;
    }
    public String getClassCode(){
        return classCode;
    }
}

