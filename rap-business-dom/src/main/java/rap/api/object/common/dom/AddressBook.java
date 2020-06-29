/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AddressBook.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.BusinessRelationObject;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.annotation.SCRequestDataset;
import com.rap.omc.util.NullUtil;

import rap.api.object.common.model.AddressBookVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.application.constants.ApplicationSchemaConstants;


public class AddressBook extends BusinessObjectMaster {
    public AddressBook(String obid){
        super(obid);
    }
    public AddressBook(AddressBookVO vo){
        super(vo);
    }
    @Override
    public AddressBookVO getVo(){
        return (AddressBookVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeAddressBook();
    }
    public void initializeAddressBook(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "AddressBook[toString()=" + super.toString() + "]";
    }


    @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

   @Override
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        /*code below*/

    }

   @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void validateForModify(Map<String, Object> map){
        super.validateForModify(map);
        /*code below*/

    }

   @Override
    protected void preProcessForModify(Map<String, Object> map){
        super.preProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void postProcessForModify(Map<String, Object> map){
        super.postProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void validateForWithdraw(Map<String, Object> map){
        super.validateForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void preProcessForWithdraw(Map<String, Object> map){
        super.preProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void postProcessForWithdraw(Map<String, Object> map){
        super.postProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void validateForDemote(Map<String, Object> map){
        super.validateForDemote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDemote(Map<String, Object> map){
        super.preProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDemote(Map<String, Object> map){
        super.postProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void validateForPromote(Map<String, Object> map){
        super.validateForPromote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForPromote(Map<String, Object> map){
        super.preProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForPromote(Map<String, Object> map){
        super.postProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void validateForClone(Map<String, Object> map){
        super.validateForClone(map);
        /*code below*/

    }

   @Override
    protected void preProcessForClone(Map<String, Object> map){
        super.preProcessForClone(map);
        /*code below*/

    }

   @Override
    protected void postProcessForClone(Map<String, Object> map){
        super.postProcessForClone(map);
        /*code below*/

    }

   /**
    * �ּҷ� ��� ��ȸ
    */
   public static List<AddressBookVO> retrieveAddressBookList(String userId){       
       // Where ���� ����
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       List<AddressBookVO> userAddressBookList = new ArrayList<AddressBookVO>();
       Users user = Users.getUsers(userId);
      if ( !NullUtil.isNull(user) ) {
           userAddressBookList = user.getRelatedObjects(
                           ApplicationSchemaConstants.RELCLASS_HASADDRESSBOOK,
                           ApplicationSchemaConstants.BIZCLASS_ADDRESSBOOK,
                           GlobalConstants.FLAG_TYPE_TO,
                           "SortBy@this.[titles]",
                           wherePatternBuf.toString(),
                           paramPatternBuf.toString(),
                           false, false, 0, 0
           );
       }

       return userAddressBookList;
   }
   
   /**
    * �ּҷ� ����
    */
   public void createAddressBook(Users user){
       this.getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_ADDRESSBOOK);
       this.getVo().setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
       this.getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
       this.getVo().setNames(NameGeneratorUtil.generateUniqueName("DSS-TEST"));
       this.getVo().setDescriptions(this.getVo().getTitles());

       this.createObject();
       
       if ( !StrUtil.isEmpty(this.getVo().getObid()) ) {
           this.addFromObject(ApplicationSchemaConstants.RELCLASS_HASADDRESSBOOK, user.getVo(), new HashMap<String, Object>());
       }
   }
   
   /**
    * �ּҷ� ����
    */
   public void updateAddressBook( AddressBookVO addressVo ){
       //this.getVo().setObid(addressVo.getObid());
       this.getVo().setTitles(addressVo.getTitles());
       this.getVo().setDescriptions(addressVo.getTitles());
       
       this.modifyObject();
   }
   
   /**
    * �ּҷ� ����� ����
    */
   public static void saveAddressBookUser( @SCRequestDataset( "adressList" ) AddressBook address, @SCRequestDataset( "userList" ) List<UsersVO> userList ){
       for (int inx = 0; inx < userList.size(); inx++) {
           address.addToObject(ApplicationSchemaConstants.RELCLASS_ADDRESSBOOKUSER, userList.get(inx), null);
       }
   }

   /**
    * �ּҷ� ����� ����
    */
   public static void deleteAddressBookUser( @SCRequestDataset( "userList" ) List<BusinessRelationObjectVO> userList ){
       for (int inx = 0; inx < userList.size(); inx++) {
           BusinessRelationObject userRel = (BusinessRelationObject)DomUtil.toDom(userList.get(inx).getObid());
           userRel.deleteObject();
       }
   }
}

