/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProjectDocumentFolderVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.document.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProjectDocumentFolderVO extends BusinessObjectMasterVO {
    private String        groupCode                                         ;
    private String        projectCode                                       ;
    private String        categoryCode                                      ;
    private String        upperCategoryCode                                 ;


    public void    setGroupCode(String groupCode){
        this.groupCode = groupCode;
    }
    public void    setProjectCode(String projectCode){
        this.projectCode = projectCode;
    }
    public void    setCategoryCode(String categoryCode){
        this.categoryCode = categoryCode;
    }
    public void    setUpperCategoryCode(String upperCategoryCode){
        this.upperCategoryCode = upperCategoryCode;
    }
    public String getGroupCode(){
        return groupCode;
    }
    public String getProjectCode(){
        return projectCode;
    }
    public String getCategoryCode(){
        return categoryCode;
    }
    public String getUpperCategoryCode(){
        return upperCategoryCode;
    }
}

