/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : ProductProjectsVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.project.model;


import rap.api.object.project.model.ProjectsVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class ProductProjectsVO extends ProjectsVO {
    private String        country                                           ;
    private String        salesRegion                                       ;
    private String        category                                          ;
    private String        devCostYyyyddmm                                   ;
    private String        devCostEndYyyyddmm                                ;


    public void    setCountry(String country){
        this.country = country;
    }
    public void    setSalesRegion(String salesRegion){
        this.salesRegion = salesRegion;
    }
    public void    setCategory(String category){
        this.category = category;
    }
    public void    setDevCostYyyyddmm(String devCostYyyyddmm){
        this.devCostYyyyddmm = devCostYyyyddmm;
    }
    public void    setDevCostEndYyyyddmm(String devCostEndYyyyddmm){
        this.devCostEndYyyyddmm = devCostEndYyyyddmm;
    }
    public String getCountry(){
        return country;
    }
    public String getSalesRegion(){
        return salesRegion;
    }
    public String getCategory(){
        return category;
    }
    public String getDevCostYyyyddmm(){
        return devCostYyyyddmm;
    }
    public String getDevCostEndYyyyddmm(){
        return devCostEndYyyyddmm;
    }
}

