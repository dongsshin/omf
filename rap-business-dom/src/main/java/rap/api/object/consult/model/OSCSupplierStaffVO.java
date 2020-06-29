/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : OSCSupplierStaffVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.consult.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;


@SuppressWarnings("serial")
public class OSCSupplierStaffVO extends BusinessObjectMasterVO {
    private String        regNo                                             ;
    private String        chgrNm                                            ;
    private String        functionNm                                        ;
    private String        relationshipYn                                    ;
    private String        relationship                                      ;
    private String        attribute1                                        ;
    private String        attribute2                                        ;
    private String        attribute3                                        ;
    private String        attribute4                                        ;
    private String        attribute5                                        ;


    public void    setRegNo(String regNo){
        this.regNo = regNo;
    }
    public void    setChgrNm(String chgrNm){
        this.chgrNm = chgrNm;
    }
    public void    setFunctionNm(String functionNm){
        this.functionNm = functionNm;
    }
    public void    setRelationshipYn(String relationshipYn){
        this.relationshipYn = relationshipYn;
    }
    public void    setRelationship(String relationship){
        this.relationship = relationship;
    }
    public void    setAttribute1(String attribute1){
        this.attribute1 = attribute1;
    }
    public void    setAttribute2(String attribute2){
        this.attribute2 = attribute2;
    }
    public void    setAttribute3(String attribute3){
        this.attribute3 = attribute3;
    }
    public void    setAttribute4(String attribute4){
        this.attribute4 = attribute4;
    }
    public void    setAttribute5(String attribute5){
        this.attribute5 = attribute5;
    }
    public String getRegNo(){
        return regNo;
    }
    public String getChgrNm(){
        return chgrNm;
    }
    public String getFunctionNm(){
        return functionNm;
    }
    public String getRelationshipYn(){
        return relationshipYn;
    }
    public String getRelationship(){
        return relationship;
    }
    public String getAttribute1(){
        return attribute1;
    }
    public String getAttribute2(){
        return attribute2;
    }
    public String getAttribute3(){
        return attribute3;
    }
    public String getAttribute4(){
        return attribute4;
    }
    public String getAttribute5(){
        return attribute5;
    }
}

