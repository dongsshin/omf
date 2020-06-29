/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesignSpecFinishVO.java
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
public class DesignSpecFinishVO extends BusinessObjectMasterVO {
    private String        orgCode                                           ;
    private String        upItemCode                                        ;
    private String        itemCode                                          ;
    private String        upMtrCode                                         ;
    private String        mtrCode                                           ;
    private String        molCode                                           ;
    private String        finCode                                           ;
    private String        specDetail                                        ;
    private String        maker                                             ;
    private String        userFileObid                                      ;
    private String        imageFileObid                                     ;
    private String        iconFileObid                                      ;
    private String        comments                                          ;
    private String        useYn                                             ;


    public void    setOrgCode(String orgCode){
        this.orgCode = orgCode;
    }
    public void    setUpItemCode(String upItemCode){
        this.upItemCode = upItemCode;
    }
    public void    setItemCode(String itemCode){
        this.itemCode = itemCode;
    }
    public void    setUpMtrCode(String upMtrCode){
        this.upMtrCode = upMtrCode;
    }
    public void    setMtrCode(String mtrCode){
        this.mtrCode = mtrCode;
    }
    public void    setMolCode(String molCode){
        this.molCode = molCode;
    }
    public void    setFinCode(String finCode){
        this.finCode = finCode;
    }
    public void    setSpecDetail(String specDetail){
        this.specDetail = specDetail;
    }
    public void    setMaker(String maker){
        this.maker = maker;
    }
    public void    setUserFileObid(String userFileObid){
        this.userFileObid = userFileObid;
    }
    public void    setImageFileObid(String imageFileObid){
        this.imageFileObid = imageFileObid;
    }
    public void    setIconFileObid(String iconFileObid){
        this.iconFileObid = iconFileObid;
    }
    public void    setComments(String comments){
        this.comments = comments;
    }
    public void    setUseYn(String useYn){
        this.useYn = useYn;
    }
    public String getOrgCode(){
        return orgCode;
    }
    public String getUpItemCode(){
        return upItemCode;
    }
    public String getItemCode(){
        return itemCode;
    }
    public String getUpMtrCode(){
        return upMtrCode;
    }
    public String getMtrCode(){
        return mtrCode;
    }
    public String getMolCode(){
        return molCode;
    }
    public String getFinCode(){
        return finCode;
    }
    public String getSpecDetail(){
        return specDetail;
    }
    public String getMaker(){
        return maker;
    }
    public String getUserFileObid(){
        return userFileObid;
    }
    public String getImageFileObid(){
        return imageFileObid;
    }
    public String getIconFileObid(){
        return iconFileObid;
    }
    public String getComments(){
        return comments;
    }
    public String getUseYn(){
        return useYn;
    }
}

