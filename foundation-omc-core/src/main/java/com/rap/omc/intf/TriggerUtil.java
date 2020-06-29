/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonUtils.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 10. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.intf;

import java.util.List;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.util.spring.SpringFactoryUtil;

/**
 * <pre>
 * Class : TriggerUtil
 * Description : 怨듯넻 �쑀�떥由ы떚
 * </pre>
 * 
 * @author s_dongsshin
 */
public class TriggerUtil {
    private TriggerUtilService  triggerUtilService;
    private static TriggerUtil fInstance;
    /**
     * Singleton�쓣 援ъ꽦�맂 getInstance() method
     */
    private synchronized static TriggerUtil getInstance(){
        if (fInstance == null) {
            fInstance = new TriggerUtil();
            fInstance.triggerUtilService = (TriggerUtilService)SpringFactoryUtil.getBean("triggerUtilService");
        }
        return fInstance;
    }
    public static void createDataByTrigger(List<IfPlmDataByTriggerVO> triggerList){
        for(IfPlmDataByTriggerVO vo : triggerList){
            getInstance().triggerUtilService.createDataByTrigger(vo);
        }
    }
    public static void createDataByTrigger(IfPlmDataByTriggerVO triggerVO){
        getInstance().triggerUtilService.createDataByTrigger(triggerVO);
    }
    public static void createDataByTriggerPjtCommonAll(IfPlmDataByTriggerVO triggerVO){
        getInstance().triggerUtilService.createDataByTriggerPjtCommonAll(triggerVO);
    }
    public static void createDataByTriggerPjtModel(IfPlmDataByTriggerVO triggerVO){
        getInstance().triggerUtilService.createDataByTriggerPjtModel(triggerVO);
    }
    public static IfPlmDataByTriggerVO createDataByTriggerVO(BusinessObjectRootVO bo, String targetSystem, String interfaceId){
        return setIfPlmDataByTrigger(bo, targetSystem, interfaceId, "", "", "");
    }   
    public static IfPlmDataByTriggerVO createDataByTriggerVO(BusinessObjectRootVO bo, String targetSystem, String interfaceId, String attr1){
        return setIfPlmDataByTrigger(bo, targetSystem, interfaceId, attr1, "", "");
    }   
    public static IfPlmDataByTriggerVO createDataByTriggerVO(BusinessObjectRootVO bo, String targetSystem, String interfaceId, String attr1, String attr2){
        return setIfPlmDataByTrigger(bo, targetSystem, interfaceId, attr1, attr2, "");
    }
    public static IfPlmDataByTriggerVO createDataByTriggerVO(BusinessObjectRootVO bo, String targetSystem, String interfaceId, String attr1, String attr2, String attr3){
        return setIfPlmDataByTrigger(bo, targetSystem, interfaceId, attr1, attr2, attr3);
    }
    public static IfPlmDataByTriggerVO createDataByTriggerVO(BusinessRelationObjectVO ro, String targetSystem, String interfaceId){
        return setIfPlmDataByTrigger(ro, targetSystem, interfaceId, "", "", "");
    }
    public static IfPlmDataByTriggerVO createDataByTriggerVO(BusinessRelationObjectVO ro, String targetSystem, String interfaceId, String attr1){
        return setIfPlmDataByTrigger(ro, targetSystem, interfaceId, attr1, "", "");
    }   
    private static IfPlmDataByTriggerVO setIfPlmDataByTrigger(BusinessObjectRootVO bo, String targetSystem, String interfaceId ){
        //IfGpdmDataByTriggerVO 추가
        IfPlmDataByTriggerVO trVO = new IfPlmDataByTriggerVO();
        trVO.setObjectId(bo.getObid());
        trVO.setObjectType(bo.getClassName());
        trVO.setObjectName(bo.getNames());
        if( bo instanceof BusinessObjectVO ){
            trVO.setObjectRevision( ((BusinessObjectVO)bo).getRevision() );
        }
        else{
            trVO.setObjectRevision("-");
        }
        trVO.setCurrentStatus(bo.getStates());
        trVO.setUserId(bo.getCreator());
        trVO.setTargetSystem(targetSystem);
        trVO.setInterfaceId(interfaceId);
        trVO.setAttribute1("");
        trVO.setAttribute2("");
        trVO.setAttribute3("");
	    return trVO;
    }

    private static IfPlmDataByTriggerVO setIfPlmDataByTrigger(BusinessObjectRootVO bo, String targetSystem, String interfaceId, String states ){
        //IfGpdmDataByTriggerVO 추가
        IfPlmDataByTriggerVO trVO = new IfPlmDataByTriggerVO();
        trVO.setObjectId(bo.getObid());
        trVO.setObjectType(bo.getClassName());
        trVO.setObjectName(bo.getNames());
        if( bo instanceof BusinessObjectVO ){
            trVO.setObjectRevision( ((BusinessObjectVO)bo).getRevision() );
        }
        else{
            trVO.setObjectRevision("-");
        }
        trVO.setCurrentStatus(states);
        trVO.setUserId(bo.getCreator());
        trVO.setTargetSystem(targetSystem);
        trVO.setInterfaceId(interfaceId);
        trVO.setAttribute1("");
        trVO.setAttribute2("");
        trVO.setAttribute3("");
        return trVO;
    }
    
    private static IfPlmDataByTriggerVO setIfPlmDataByTrigger(BusinessObjectRootVO bo, String targetSystem, String interfaceId, String attr1, String attr2, String attr3  ){
        //IfGpdmDataByTriggerVO 추가
        IfPlmDataByTriggerVO trVO = new IfPlmDataByTriggerVO();
        trVO.setObjectId(bo.getObid());
        trVO.setObjectType(bo.getClassName());
        trVO.setObjectName(bo.getNames());
        if( bo instanceof BusinessObjectVO ){
            trVO.setObjectRevision( ((BusinessObjectVO)bo).getRevision() );
        }
        else{
            trVO.setObjectRevision("-");
        }
        trVO.setCurrentStatus(bo.getStates());
        trVO.setUserId(bo.getCreator());
        trVO.setTargetSystem(targetSystem);
        trVO.setInterfaceId(interfaceId);
        trVO.setAttribute1(attr1);
        trVO.setAttribute2(attr2);
        trVO.setAttribute3(attr3);
        return trVO;
    }
    private static IfPlmDataByTriggerVO setIfPlmDataByTrigger(BusinessRelationObjectVO ro, String targetSystem, String interfaceId, String attr1, String attr2, String attr3  ){
        //IfGpdmDataByTriggerVO 추가
        IfPlmDataByTriggerVO trVO = new IfPlmDataByTriggerVO();
        trVO.setObjectId(ro.getObid());
        trVO.setObjectType(ro.getClassName());
        trVO.setObjectName(ro.getFromClass() + "."+ ro.getFromObid() + "~" + ro.getToClass() + "." + ro.getToObid()) ;
        trVO.setObjectRevision( ro.getFromObid() );
        trVO.setCurrentStatus(ro.getToObid());
        trVO.setUserId(ro.getCreator());
        trVO.setTargetSystem(targetSystem);
        trVO.setInterfaceId(interfaceId);
        trVO.setAttribute1(attr1);
        trVO.setAttribute2(attr2);
        trVO.setAttribute3(attr3);
        return trVO;
    }
    private static IfPlmDataByTriggerVO setIfPlmDataByTrigger(BusinessObjectRootVO bo, String targetSystem, String interfaceId, int seq, String attr1, String attr2, String attr3  ){
        IfPlmDataByTriggerVO trVO = new IfPlmDataByTriggerVO();
        trVO.setObjectId(bo.getObid());
        trVO.setObjectType(bo.getClassName());
        trVO.setObjectName(bo.getNames());
        if( bo instanceof BusinessObjectVO ){
            trVO.setObjectRevision( ((BusinessObjectVO)bo).getRevision() );
        }
        else{
            trVO.setObjectRevision("-");
        }
        trVO.setSeq(seq);
        trVO.setCurrentStatus(bo.getStates());
        trVO.setUserId(bo.getCreator());
        trVO.setTargetSystem(targetSystem);
        trVO.setInterfaceId(interfaceId);
        trVO.setAttribute1(attr1);
        trVO.setAttribute2(attr2);
        trVO.setAttribute3(attr3);
        return trVO;
    }
    
}
