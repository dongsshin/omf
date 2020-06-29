/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : PartWBSSTVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.partproject.model;


import com.rap.omc.api.object.model.BusinessObjectVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class PartWBSSTVO extends BusinessObjectVO {
    private String        partClass1                                        ;
    private String        partClass2                                        ;
    private String        partClass3                                        ;
    private String        partDevGrade                                      ;
    private String        useYn                                             ;
    private Integer       st                                                ;
    private String        prodProjectGubun                                  ;


    public void    setPartClass1(String partClass1){
        this.partClass1 = partClass1;
    }
    public void    setPartClass2(String partClass2){
        this.partClass2 = partClass2;
    }
    public void    setPartClass3(String partClass3){
        this.partClass3 = partClass3;
    }
    public void    setPartDevGrade(String partDevGrade){
        this.partDevGrade = partDevGrade;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public void    setSt(Integer st){
        this.st = st;
    }
    public void    setProdProjectGubun(String prodProjectGubun){
        this.prodProjectGubun = prodProjectGubun;
    }
    public String getPartClass1(){
        return partClass1;
    }
    public String getPartClass2(){
        return partClass2;
    }
    public String getPartClass3(){
        return partClass3;
    }
    public String getPartDevGrade(){
        return partDevGrade;
    }
    public String getUseYn(){
        return useYn;
    }
    public Integer getSt(){
        return st;
    }
    public String getProdProjectGubun(){
        return prodProjectGubun;
    }
}

