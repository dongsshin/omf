/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : Notification.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.BusinessObjectMaster;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.dataaccess.paging.model.PagingList;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.NotificationVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.UsersVO;
import rap.application.constants.ApplicationSchemaConstants;


public class Notification extends BusinessObjectMaster {
    public Notification(String obid){
        super(obid);
    }
    public Notification(NotificationVO vo){
        super(vo);
    }
    @Override
    public NotificationVO getVo(){
        return (NotificationVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeNotification();
    }
    public void initializeNotification(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "Notification[toString()=" + super.toString() + "]";
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
    * �˸� ��� ��ȸ
    */
   public static List<NotificationVO> retrieveNotificationList(NotificationVO notifiVo){
       // Where ���� ����
       StringBuffer wherePatternBuf = new StringBuffer();
       StringBuffer paramPatternBuf = new StringBuffer();
       
       if( !StrUtil.isEmpty(notifiVo.getNames()) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                   GlobalConstants.OQL_OPERATOR_EQUAL, notifiVo.getNames());
       }

       if( !StrUtil.isEmpty(notifiVo.getTitles()) ){
           StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[titles]",
                   GlobalConstants.OQL_OPERATOR_LIKE, notifiVo.getTitles() + '%');
       }
       
       // �⺻ �˻����� ��뿩�� �ʵ� : useYn = 'Y'
       StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[useYn]",
               GlobalConstants.OQL_OPERATOR_EQUAL, "Y");

       return ObjectRoot.findObjects(
               ApplicationSchemaConstants.BIZCLASS_NOTIFICATION, // className
               GlobalConstants.FLAG_TYPE_ALL, //namePattern, 
               GlobalConstants.FLAG_TYPE_ALL, //revisionPattern, 
               GlobalConstants.FLAG_TYPE_ALL, // selectPattern
               wherePatternBuf.toString(),    // wherePattern,         
               paramPatternBuf.toString(),    // parameterPattern, 
               false,                         //expandType
               0
       );
   }
   
   /**
    * �˸� ����
    */
   public NotificationVO createNotification(){
       this.getVo().setClassName(ApplicationSchemaConstants.BIZCLASS_NOTIFICATION);
       this.getVo().setStates(ApplicationSchemaConstants.STATE_WITHOUT_STATE_EXISTS);
       this.getVo().setLifeCycle(ApplicationSchemaConstants.LIFECYCLE_WITHOUT_STATE);
       this.getVo().setNames(NameGeneratorUtil.generateUniqueName("DSS-TEST"));

       this.createObject();
       
       return this.getVo();
   }

   
   /**
    * �˸� ����
    */
   public void deleteNotification(){
       this.deleteObject();
   }
   /**
    * User�� ���� Notifacion Count Return
    *
    * @param userObid User Obid
    * @return
    */
   public static int getNotificationCountForUserByObid(String userObid){
       return getNotificationCountForUserByObid(userObid,false);
   }
   /**
    * User�� ���� Notifacion Count Return
    *
    * @param userObid User Obid
    * @param includeRead �̹� ���� ���� �������� ����
    * @return
    */
   public static int getNotificationCountForUserByObid(String userObid, boolean includeRead){
       return getNotificationCountForUserCore(userObid,includeRead);
   }
   /**
    * User�� ���� Notifacion Count Return
    *
    * @param userId User Obid
    * @return
    */
   public static int getNotificationCountForUserByName(String userId){
       return getNotificationCountForUserByName(userId,false);
   } 
   /**
    * 
    *
    * @param userId User Obid
    * @param includeRead �̹� ���� ���� �������� ����
    * @return
    */
   public static int getNotificationCountForUserByName(String userId, boolean includeRead){
       UsersVO usrsVO = BusinessObjectMaster.findBusinessObjectMaster(ApplicationSchemaConstants.BIZCLASS_USERS, userId);
       if(NullUtil.isNull(usrsVO)) return 0;
       return getNotificationCountForUserCore(usrsVO.getObid(),includeRead);
   } 
   /**
    * User�� ���� Notifacion Count Return�ϴ� Core���α׷�(public���� �ٲ��� ������)
    * �ڡڡڡ� Copy & Paste�� ���� �ű� Method������ ������. �ڡڡڡ�
    *
    * @param userObid User Obid
    * @param includeRead �̹� ���� ���� �������� ����
    * @return
    */
   private static int getNotificationCountForUserCore(String userObid, boolean includeRead){
       StringBuffer wherePattern = new StringBuffer();
       StringBuffer paramPattern = new StringBuffer();
       PagingEntity searcInfo = new PagingEntity();
       Users userDom = new Users(userObid);
       if(!includeRead) StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[readYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@REL.[deleteYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");
       StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[useYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "Y");  // 20180418 ������ �� ����
       
       searcInfo.setRowSize(1);
       List<NotificationVO> result = userDom.getRelatedObjectsPaging(ApplicationSchemaConstants.RELCLASS_HASNOTIFICATIONUSER, ApplicationSchemaConstants.BIZCLASS_NOTIFICATION, GlobalConstants.FLAG_TYPE_FROM, "", wherePattern.toString(), paramPattern.toString(), searcInfo);
       return ((PagingList<NotificationVO>)result).getRows();
   }
}

