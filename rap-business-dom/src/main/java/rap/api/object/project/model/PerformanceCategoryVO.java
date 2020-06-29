/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PerformanceCategoryVO.java
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
public class PerformanceCategoryVO extends BusinessObjectMasterVO {
    private String        yyyy                                              ;
    private String        uppperCategoryCode                                ;
    private Integer       sequences                                          = 0;
    private String        mainCategory                                      ;


    public void    setYyyy(String yyyy){
        this.yyyy = yyyy;
    }
    public void    setUppperCategoryCode(String uppperCategoryCode){
        this.uppperCategoryCode = uppperCategoryCode;
    }
    public void    setSequences(Integer sequences){
        this.sequences = sequences;
    }
    public void    setMainCategory(String mainCategory){
        this.mainCategory = mainCategory;
    }
    public String getYyyy(){
        return yyyy;
    }
    public String getUppperCategoryCode(){
        return uppperCategoryCode;
    }
    public Integer getSequences(){
        return sequences;
    }
    public String getMainCategory(){
        return mainCategory;
    }
}

