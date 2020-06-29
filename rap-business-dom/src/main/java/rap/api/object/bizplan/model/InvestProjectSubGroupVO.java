/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : InvestProjectSubGroupVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class InvestProjectSubGroupVO extends BusinessObjectMasterVO {
    private String        yyyy                                              ;
    private String        corporationCode                                   ;
    private String        projectGroupCode                                  ;
    private String        upProjectGroupCode                                ;
    private Integer       levelCode                                         ;
    private String        projectGroupKorName                               ;
    private String        projectGroupEngName                               ;
    private Integer       sorting                                           ;
    private String        useYn                                             ;


    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setCorporationCode(String corporationCode){
        this.corporationCode = corporationCode;
    }
    public void    setProjectGroupCode(String projectGroupCode){
        this.projectGroupCode = projectGroupCode;
    }
    public void    setUpProjectGroupCode(String upProjectGroupCode){
        this.upProjectGroupCode = upProjectGroupCode;
    }
    public void    setLevelCode(Integer levelCode){
        this.levelCode = levelCode;
    }
    public void    setProjectGroupKorName(String projectGroupKorName){
        this.projectGroupKorName = projectGroupKorName;
    }
    public void    setProjectGroupEngName(String projectGroupEngName){
        this.projectGroupEngName = projectGroupEngName;
    }
    public void    setSorting(Integer sorting){
        this.sorting = sorting;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getCorporationCode(){
        return corporationCode;
    }
    public String getProjectGroupCode(){
        return projectGroupCode;
    }
    public String getUpProjectGroupCode(){
        return upProjectGroupCode;
    }
    public Integer getLevelCode(){
        return levelCode;
    }
    public String getProjectGroupKorName(){
        return projectGroupKorName;
    }
    public String getProjectGroupEngName(){
        return projectGroupEngName;
    }
    public Integer getSorting(){
        return sorting;
    }
    public String getUseYn(){
        return useYn;
    }
}

