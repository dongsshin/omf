/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : CodeMasterVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class CodeMasterVO extends BusinessObjectMasterVO {
    private Boolean       usecache                                           = (Boolean)false;
    private String        codeMasterScope                                   ;
    private String        authType                                          ;


    public void    setUsecache(Boolean usecache){
        this.usecache = usecache;
    }
    public void    setCodeMasterScope(String codeMasterScope){
        this.codeMasterScope = codeMasterScope;
    }
    public void    setAuthType(String authType){
        this.authType = authType;
    }
    public Boolean getUsecache(){
        return usecache;
    }
    public String getCodeMasterScope(){
        return codeMasterScope;
    }
    public String getAuthType(){
        return authType;
    }
}

