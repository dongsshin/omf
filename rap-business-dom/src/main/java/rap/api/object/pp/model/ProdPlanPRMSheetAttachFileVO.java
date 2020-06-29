/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProdPlanPRMSheetAttachFileVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.pp.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProdPlanPRMSheetAttachFileVO extends BusinessObjectMasterVO {
    private String        prmFileId                                         ;
    private String        prmSheetId                                        ;
    private String        fileId                                            ;
    private String        deleteYn                                          ;


    public void    setPrmFileId(String prmFileId){
        this.prmFileId = prmFileId;
    }
    public void    setPrmSheetId(String prmSheetId){
        this.prmSheetId = prmSheetId;
    }
    public void    setFileId(String fileId){
        this.fileId = fileId;
    }
    public void    setDeleteYn(String deleteYn){
        this.deleteYn = deleteYn;
    }
    public String getPrmFileId(){
        return prmFileId;
    }
    public String getPrmSheetId(){
        return prmSheetId;
    }
    public String getFileId(){
        return fileId;
    }
    public String getDeleteYn(){
        return deleteYn;
    }
}

