/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessObjectRoot.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2014. 12. 23. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.api.util.general.ObjectChangeLogManagement;
import com.rap.omc.api.util.omc.CacheUtil;
import com.rap.omc.api.util.omc.FileFoundationUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.FoundationConstants;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ObjectTableVO;
import com.rap.omc.foundation.common.model.KeyInfo;
import com.rap.omc.foundation.file.model.FCSParameterVO;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.model.FileServiceURLVO;
import com.rap.omc.foundation.lifecycle.model.StateInfo;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.framework.exception.BranchException;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.temp.model.ActivityValidationResultVO;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.schema.util.OmcUniqueIDGenerator;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.foundation.LifeCycleTriggerUtil;
import com.rap.omc.util.foundation.LifeCycleUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.file.FCSServerLocationUtil;
import com.rap.omc.util.file.HttpClientUtil;
import com.rap.omc.util.file.OmcFileConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.util.foundation.FoundationValidationUtil;

/**
 * <pre>
 * Class : BusinessObjectRoot
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
public class BusinessObjectRoot extends ObjectRoot {

    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObjectRoot.class);

    @Override
    public String toString(){
        return "BusinessObjectRoot [toString()=" + super.toString() + "]";
    }

    /**
     * @param vo
     */
    public BusinessObjectRoot(BusinessObjectRootVO vo) {
        super(vo);
    }
    public BusinessObjectRoot(String obid) {
        super(obid);
    }
    public BusinessObjectRoot(String obid, boolean withOutData) {
        super(obid,withOutData);
    }
    @Override
    public BusinessObjectRootVO getVo(){
        return (BusinessObjectRootVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();

        initializeBusinessObjectRoot();
    }

    public void initializeBusinessObjectRoot(){
        LOGGER.debug("initVO() from BusinessObjectRoot");

        if (NullUtil.isNone(getVo().getNames())) {
            getVo().setNames(getDefaultName());
        }

        if (NullUtil.isNone(getVo().getLifeCycle())) {
            getVo().setLifeCycle(getDefaultPolicy());
        }

        if (NullUtil.isNone(getVo().getTitles())) {
            throw new FoundationException("omc.error.class.attr.required", new Object[] { "Titles" });
        }

        if (NullUtil.isNone(getVo().getStates())) {
            getVo().setStates(getDefaultStates());
        }

        getVo().setFlags(getVo().getFlags() | OmcSystemConstants.OBJROOT_FLAG_BO);
    }
    /**
     * 
     * 화면 Display를 위해서 만들어진 Mehtod. Workflow에서 Display되어지는 것을 바꾸고자 할 때 Override해서 사용하면 됨.
     * @return
     */
    public String getCommonTitlesForDisplay(){
        return this.getTitles();
    }
    /**
     * 
     *
     * @return
     */
    public String getCommonTitlesForDisplay(String locale){
        return this.getTitles();
    }
    /**
     * Activity를 Complete하는 Method
     *
     * @return
     */
    public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO){
        completeWBSActivity(inboxTaskVO,false);
    }
    /**
     * inbox에 대한 WBS Activity Parameter를 Return함. 실제 구현 내용은 하위 부분에 있음.
     *
     * @return
     */
    public Map<String,String> getParameterForWorkflow(BusinessObjectRootVO inboxTaskVO){
        return new HashMap<String,String>();
    }
    /**
     * Activity를 Complete하는 Method
     *
     * @param inboxTaskVO
     * @param skipWarning
     */
    public void completeWBSActivity(BusinessObjectRootVO inboxTaskVO, boolean skipWarning){
        if(!this.isKindOf("WBSItems")){
            throw new FoundationException("[Foundation]Called Method( completeWBSActivity) is not supported for '" + this.getClassName() + "'");
        }
        List<ActivityValidationResultVO> list = validateCompleteWBSActivity(inboxTaskVO);
        ariseError(list,skipWarning);
    }
    /**
     * WBS 모듈에서 해당 Activity에 대한 Complete에 대한 에러 발생 Method
     *
     * @param list
     * @param skipWarning Warning은 Skip할지 여부
     */
    public static void ariseError(List<ActivityValidationResultVO> list, boolean skipWarning){
        StringBuffer errorMessage= new StringBuffer();
        if(!NullUtil.isNone(list)){
            boolean isOk = true;
            for(ActivityValidationResultVO vo : list){
                if(!vo.getErrorLevel().equals(ActivityValidationResultVO.RESULTCODE.success)) {
                    if(skipWarning){
                        if(!vo.getErrorLevel().equals(ActivityValidationResultVO.RESULTCODE.warning)) {
                            isOk= false;
                            errorMessage.append("<br> - Item:" + vo.getErrorItem()).append("<br> - Error Contents:" + vo.getErrorContents()).append("<br> - Guide:" + vo.getActionGuide());
                        }
                    }else{
                        isOk= false;
                        errorMessage.append("<br> - Item:" + vo.getErrorItem()).append("<br> - Error Contents:" + vo.getErrorContents()).append("<br> - Guide:" + vo.getActionGuide());
                    }
                 }
            }
            if(!isOk) throw new ApplicationException(errorMessage.toString().substring(4));
        }
    }
    
    
    /**
     * WBS 모듈에서 해당 Activity에 대한 Complete이 가능하지 Check하기 위한 것
     * 일반 모듈에서는 사용하지 않음.
     *
     * @param inboxTaskVO Inbox Object
     * @return
     */
    public List<ActivityValidationResultVO> validateCompleteWBSActivity(BusinessObjectRootVO inboxTaskVO){
        return new ArrayList<ActivityValidationResultVO>();
    }
    /**
     * 
     *
     * @return
     */
    protected String getDefaultPolicy(){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(getVo().getClassName());
        if (!NullUtil.isNull(classInfo)) {
            if (!classInfo.getDefaultPolicy().equals(GlobalConstants.DUMMY)) {
                return classInfo.getDefaultPolicy();
            } else {
                throw new FoundationException("api.object.error.policy.dummy");
            }
        }
        throw new FoundationException("omc.error.classInfo.nodata");
    }

    protected String getDefaultName(){
        return NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_GLOBAL);
    }

    protected String getDefaultStates(){
        return LifeCycleUtil.getDefaultState(getVo().getLifeCycle());
    }
    /**
     * Object에 대한 Default State을 Return함.
     *
     * @return
     */
    public String getDefaultStore(){
        FcsLocationVO fileStoreVO = FCSServerLocationUtil.getCurrentStoreForLifeCycle(this.getVo().getLifeCycle());
        return fileStoreVO.getStoreName();
    }
    /**
     * Object에 대한 Default Location을 Return함.
     *
     * @return FcsLocationVO
     */
    public FcsLocationVO getDefaultLocation(){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);  
        return FCSServerLocationUtil.getCurrentLocationForUser(getDefaultStore(), userId);
    }

    protected void validateForCreate(Map<String, Object> map){
        LOGGER.debug(">> 1. validateForCreate(map) -- {}", getVo().getObid());

        initialize();

        FoundationValidationUtil.checkInstantiable(getVo().getClassName());

        // TODO : 필요한 로직 추가
    }

    protected void preProcessForCreate(Map<String, Object> map){
        LOGGER.debug(">> 2. preProcessForCreate(name, policy, map) -- {}", getVo().getObid());
        if (!NullUtil.isNone(this.getVo().getLifeCycle())) {
        	FoundationValidationUtil.checkAllowedLifeCycle(getVo().getClassName(), this.getVo().getLifeCycle());
        }
        if (!NullUtil.isNone(getVo().getStates())) {
            FoundationValidationUtil.checkLifeCycleAndState(getVo().getLifeCycle(), getVo().getStates());
        }
    }
    protected void postProcessForCreate(Map<String, Object> map){}

    /**
     * VO 정보를 기준으로 Object를 생성한다. (DB 변경작업)
     *
     * @see omc.api.object.dom.ObjectRoot#createObject()
     */
    @Override
    public final void createObject(){
        BusinessObjectRootVO vo = getVo();
        if (vo instanceof BusinessObjectVO) {
            BusinessObjectVO newVo = (BusinessObjectVO)getVo();
            BusinessObject boDom = (BusinessObject)DomUtil.toDom(newVo);
            boDom.createObject(newVo.getNames(), newVo.getLifeCycle(), newVo.getRevision());
        } else {
            this.createObject(vo.getNames(), vo.getLifeCycle());
        }
    }
    /**
     * Create Object
     * 
     * @param map Parameter
     * @see omc.api.object.dom.ObjectRoot#createObject(java.util.Map)
     */
    @Override
    public final void createObject(Map<String, Object> map){
        BusinessObjectRootVO vo = getVo();

        if (vo instanceof BusinessObjectVO) {
            BusinessObjectVO newVo = (BusinessObjectVO)getVo();
            BusinessObject boDom = (BusinessObject)DomUtil.toDom(newVo);
            boDom.createObject(newVo.getNames(), newVo.getLifeCycle(), newVo.getRevision(), map);
        } else {
            this.createObject(vo.getNames(), vo.getLifeCycle(), map);
        }
    }
    /**
     * VO 정보를 기준으로 Object를 생성한다.
     * name, policy 정보는 전달인자로 받은 값을 사용한다.
     *
     * @param name name
     * @param policy lifeCycle (ex, EC Part Policy)
     */
    public final void createObject(String name, String lifeCycle){
        Map<String, Object> map = new HashMap<String, Object>();
        this.createObject(name,lifeCycle,map);
    }
    /**
     * VO 정보를 기준으로 Object를 생성한다.
     *
     * @param name
     * @param lifeCycle
     * @param map
     */
    public final void createObject(String name, String lifeCycle, Map<String, Object> map){
        LOGGER.debug("<< createObject() from {} --> {}", this.getClass(), getVo().getObid());
        this.getVo().setNames(name);
        this.getVo().setLifeCycle(lifeCycle);
        this.validateForCreate(map);
        this.preProcessForCreate(map);
        LOGGER.debug(">> 3. create obejct -- {}", getVo().getObid());
        super.create();
        this.postProcessForCreate(map);
    }
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates,
            Map<String, Object> map){
        LOGGER.debug(">> 1. validateForChange(newClassName, newName, newLifeCycle, newStates) -- {}", getVo().getObid());
    }

    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        BusinessObjectRootVO inputVO = getVo();
        String oldClassName = inputVO.getClassName();
        if (!NullUtil.isNone(newClassName)) {
            inputVO.setClassName(newClassName);
            // 변경 전 class 이름을 ThreadLocal 변수에 임시 저장함
            KeyInfo keyInfo = BaseFoundationUtil.getKeyInfo(inputVO);
            if (NullUtil.isNull(keyInfo)){
                throw new FoundationException("omc.error.keyInfo.nodata");
            }
        }
        if (!NullUtil.isNone(newName)) {
            inputVO.setNames(newName);
        }

        if (!NullUtil.isNone(newLifeCycle)) {
            if (!NullUtil.isNone(inputVO.getClassName())) {
                FoundationValidationUtil.checkAllowedLifeCycle(inputVO.getClassName(), newLifeCycle);
            } else {
                BusinessObjectRootVO storedDbInfo = CommonServiceUtil.getObject(inputVO.getObid());
                if (NullUtil.isNull(storedDbInfo) || NullUtil.isNone(storedDbInfo.getClassName())){
                    throw new FoundationException("omc.error.validate.allowedLifeCycle", new Object[] { "ClassName has not information",
                                newLifeCycle });
                }
                inputVO.setClassName(storedDbInfo.getClassName());
                FoundationValidationUtil.checkAllowedLifeCycle(inputVO.getClassName(), inputVO.getLifeCycle());
            }
            inputVO.setLifeCycle(newLifeCycle);
        } else {
            if (!NullUtil.isNone(inputVO.getClassName()) && !NullUtil.isNone(inputVO.getLifeCycle())) {
                FoundationValidationUtil.checkAllowedLifeCycle(inputVO.getClassName(), inputVO.getLifeCycle());
            }
        }

        if (!NullUtil.isNone(newStates)) {
            if (!NullUtil.isNone(inputVO.getLifeCycle())) {
                FoundationValidationUtil.checkLifeCycleAndState(inputVO.getLifeCycle(), newStates);
            } else {
                BusinessObjectRootVO storedDbInfo = CommonServiceUtil.getObject(inputVO.getObid());
                if (NullUtil.isNull(storedDbInfo) || NullUtil.isNone(storedDbInfo.getLifeCycle())) throw new FoundationException(
                        "omc.error.validate.lifeCycleAndState", new Object[] { "LifeCycle has not information",
                                newLifeCycle });
                inputVO.setClassName(storedDbInfo.getClassName());
                inputVO.setLifeCycle(storedDbInfo.getLifeCycle());
                FoundationValidationUtil.checkLifeCycleAndState(inputVO.getLifeCycle(), newStates);
            }
            inputVO.setStates(newStates);
        } else {
            if (!NullUtil.isNone(inputVO.getLifeCycle()) && !NullUtil.isNone(inputVO.getStates())) {
                FoundationValidationUtil.checkLifeCycleAndState(inputVO.getLifeCycle(), inputVO.getStates());
            }
        }
        if (!NullUtil.isNone(newClassName) && !newClassName.equals(oldClassName)) {
            _changeRelatedClassName(inputVO,newClassName);
            CacheUtil.evictCache("objectKeyTable", this.getObid());
        }
    }
    private void _changeRelatedClassName(BusinessObjectRootVO vo, String newClassName){
        List<ObjectTableVO> bbjectTableList = CommonServiceUtil.getObjectTableForClassChange(vo);
        
        List<ObjectTableVO> updatObjectTableFromList = new ArrayList<ObjectTableVO>();
        List<ObjectTableVO> updatObjectTableToList = new ArrayList<ObjectTableVO>();
        
        for(ObjectTableVO objectTableVO : bbjectTableList){
            if(objectTableVO.getIsFrom() == 1){
                updatObjectTableFromList.add(objectTableVO);
            }else if(objectTableVO.getIsFrom() == 0){
                updatObjectTableToList.add(objectTableVO);
            }
        }
        Map<String,Object> map = new HashMap<String,Object>();
        if(!NullUtil.isNone(updatObjectTableFromList)){
            map.put("list", updatObjectTableFromList);
            map.put("newClassName", newClassName);
            CommonServiceUtil.updateRelationForClassChangeFrom(map);
        }
        if(!NullUtil.isNone(updatObjectTableToList)){
            map.put("list", updatObjectTableToList);
            map.put("newClassName", newClassName);
            CommonServiceUtil.updateRelationForClassChangeTo(map);
        }
    }
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates,
            Map<String, Object> map){
        LOGGER.debug(">> 4. postProcessForChange(newClassName, newName, newLifeCycle, newStates) -- {}", getVo()
                .getObid());
    }
    /**
     * className, Name, LifeCycle, state 값을 변경한다.
     *
     * @param newClassName
     * @param newName
     * @param newLifeCycle
     * @param newStates
     */
    public final void change(String newClassName, String newName, String newLifeCycle, String newStates){
        LOGGER.debug("<< change() from {} --> {}", this.getClass(), getVo().getObid());

        validateForChange(newClassName, newName, newLifeCycle, newStates, null);

        preProcessForChange(newClassName, newName, newLifeCycle, newStates, null);

        LOGGER.debug(">> 3. change -- {}", getVo().getObid());
        super.change();

        postProcessForChange(newClassName, newName, newLifeCycle, newStates, null);
    }

    /**
     * className, Name, LifeCycle, state 값을 변경한다.
     *
     * @param newClassName New class name
     * @param newName new names
     * @param newLifeCycle New Life Cycle
     * @param newStates New State
     * @param map Change 시 사용되어지는 Parameter
     * 
     */
    public final void change(String newClassName, String newName, String newLifeCycle, String newStates,Map<String, Object> map){
        validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        super.change();
        postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
    }
    
    protected void validateForClone(Map<String, Object> map){}
    protected void preProcessForClone(Map<String, Object> map){}
    private final BusinessObjectRootVO CloneCore(String newName,Map<String, Object> map){
        BusinessObjectRootVO oldVO = (BusinessObjectRootVO)getVo();
        BusinessObjectRootVO newVO = (BusinessObjectRootVO)DomUtil.cloneBean(oldVO);
        initializeForClone(newVO);
        newVO.setNames(newName);
        CloneCorePre(newVO);
        BusinessObjectRoot   newDom = (BusinessObject)DomUtil.toDom(newVO);
        newDom.getVo().setStates(newDom.getFirstState().getStateName());
        newDom.createObject();
        this.copyRelationWithRelationshipRule(this,newVO,false,map);
        return newDom.getVo();
    }
    private void initializeForClone(BusinessObjectRootVO newVO){
        newVO.setLocker("1");
        newVO.setCheckouter("1");
        Date date = null;
        newVO.setCheckouted(date);
        newVO.setOwner("1");
    }
    protected void CloneCorePre(BusinessObjectRootVO newVO){}
    protected void postProcessForClone(Map<String, Object> map){
        if("true".equals(map.get(GlobalConstants.REVISE_OPTION_COPYFILE))){
            BusinessObjectRootVO newVo = (BusinessObjectRootVO) map.get("newVo");
            List<FilesVO> fileList = this.getReleatedFiles();
            BusinessObjectRoot newDom = DomUtil.toDom(newVo);  
            if(!NullUtil.isNone(fileList)){
                newDom.copyFiles(fileList);
            }
        }
    }
    /**
     * <pre>
     * 새로운 이름으로 Object를 Clone함
     *
     *  String projectCode = pjt.getNewPlanProjectCode(planYear);
     *  BizPlanProjectVO projectVO = (BizPlanProjectVO)projectDom.cloneObject(projectCode);
     *  </pre>
     *  
     * @param newName 생성될 New Object의 Names
     * @param map Parameter
     * 생성되어진 New Object는 this.getOutAttribute("newVo")로 찾을 수 있음.
     * 화일도 Copy하기 위해서는 map.put(GlobalConstants.REVISE_OPTION_COPYFILE,"true")하면 됨.
     * @return 현재 자신의 VO
     */
    public final BusinessObjectRootVO cloneObject(String newName,Map<String, Object> map){
        if(NullUtil.isNone(map)) map = new HashMap<String, Object>();
        this.validateForClone(map);
        this.preProcessForClone(map);
        BusinessObjectRootVO newVo = CloneCore(newName,map);
        map.put("newVo", newVo);
        this.postProcessForClone(map);
        return newVo;
    }
    /**
     * 새로운 이름으로 Object를 Clone함
     *  
     * @param newName 생성될 New Object의 Names
     * @return 현재 자신의 VO
     * @see BusinessObjectRoot#cloneObject(String, Map)
     */
    public final BusinessObjectRootVO cloneObject(String newName){
        Map<String, Object> map = new HashMap<String, Object>();
        return this.cloneObject(newName,map);
    }
    /**
     * LifeCycle, state 값을 변경한다.
     *
     * @param newLifeCycle
     * @param newStates
     */
    public final void changePolicyAndState(String newLifeCycle, String newStates){
        BusinessObjectRootVO searchVO = getVo();
        if (!NullUtil.isNone(searchVO.getObid())) {
            BusinessObjectRootVO inputVO = new BusinessObjectRootVO();
            inputVO.setObid(searchVO.getObid());
            inputVO.setClassName(searchVO.getClassName());
            if (!NullUtil.isNone(newLifeCycle)) {
                FoundationValidationUtil.checkAllowedLifeCycle(inputVO.getClassName(), newLifeCycle);
                inputVO.setLifeCycle(newLifeCycle);
            }

            if (!NullUtil.isNone(newStates)) {
                FoundationValidationUtil.checkLifeCycleAndState(newLifeCycle, newStates);
                inputVO.setStates(newStates);
            }
            super.change(inputVO, false);
        }
    }
    protected final void copyRelationWithRelationshipRule(ObjectRoot inOldDom, ObjectRootVO newVO, final boolean isRevise, Map<String, Object> map){
        long replicateRule = OmcSystemConstants.RELATION_RULE_RevReplicate;
        long floatRule     = OmcSystemConstants.RELATION_RULE_RevFloat;
        if(!isRevise){
            replicateRule = OmcSystemConstants.RELATION_RULE_CloneReplicate;
            floatRule     = OmcSystemConstants.RELATION_RULE_CloneFloat;
        }
        List<BusinessRelationObjectVO> fromTargetList = inOldDom.getFromRelationShips(GlobalConstants.FLAG_TYPE_ALL);
        if (!NullUtil.isNone(fromTargetList)) {
            for (BusinessRelationObjectVO fromTargetVO : fromTargetList) {
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(fromTargetVO.getClassName());
                if (!NullUtil.isNull(classInfo)) {
                    if ((classInfo.getRevisionRuleTo() & replicateRule) == replicateRule) {
                        BusinessRelationObjectVO newFromTargetVO = (BusinessRelationObjectVO)DomUtil.cloneBean(fromTargetVO);
                        String newRelObid = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_OBID);
                        newFromTargetVO.setObid(newRelObid);
                        newFromTargetVO.setToObid(newVO.getObid());

                        FoundationValidationUtil.checkRelationCardinality(newFromTargetVO, classInfo);
                        BusinessRelationObject newDom = (BusinessRelationObject)DomUtil.toDom(newFromTargetVO);
                        newDom.initVariable();
                        newDom.createObject(map);
                        BusinessRelationObject oldDom = (BusinessRelationObject)DomUtil.toDom(fromTargetVO);
                        this.copyRelationWithRelationshipRule(oldDom,newDom.getVo(),isRevise,map);
                    } else if ((classInfo.getRevisionRuleTo() & floatRule) == floatRule) {
                        fromTargetVO.setToObid(newVO.getObid());
                        FoundationValidationUtil.checkRelationCardinality(fromTargetVO, classInfo);
                        BusinessRelationObject oldDom = (BusinessRelationObject)DomUtil.toDom(fromTargetVO);
                        oldDom.update();
                        CommonServiceUtil.changeRelationObjectWithKeyTable(fromTargetVO);
                    } else {
                        // none
                    }
                }
            }
        }
        List<BusinessRelationObjectVO> toTargetList = inOldDom.getToRelationShips(GlobalConstants.FLAG_TYPE_ALL);
        if (!NullUtil.isNone(toTargetList)) {
            for (BusinessRelationObjectVO toTargetVO : toTargetList) {
                ClassInfo classInfo = ClassInfoUtil.getClassInfo(toTargetVO.getClassName());
                if (!NullUtil.isNull(classInfo)) {
                    if ((classInfo.getRevisionRuleFrom() & replicateRule) == replicateRule) {
                        BusinessRelationObjectVO newToTargetVO = (BusinessRelationObjectVO)DomUtil.cloneBean(toTargetVO);
                        String newRelObid = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_OBID);
                        newToTargetVO.setObid(newRelObid);
                        newToTargetVO.setFromObid(newVO.getObid());
                        FoundationValidationUtil.checkRelationCardinality(newToTargetVO, classInfo);
                        BusinessRelationObject newDom = (BusinessRelationObject)DomUtil.toDom(newToTargetVO);
                        newDom.initVariable();
                        newDom.createObject(map);
                        BusinessRelationObject oldDom = (BusinessRelationObject)DomUtil.toDom(toTargetVO);
                        this.copyRelationWithRelationshipRule(oldDom,newDom.getVo(),isRevise,map);

                    } else if ((classInfo.getRevisionRuleFrom() & floatRule) == floatRule) {
                        toTargetVO.setFromObid(newVO.getObid());
                        FoundationValidationUtil.checkRelationCardinality(toTargetVO, classInfo);
                        BusinessRelationObject oldDom = (BusinessRelationObject)DomUtil.toDom(toTargetVO);
                        oldDom.update();
                        CommonServiceUtil.changeRelationObjectWithKeyTable(toTargetVO);
                    } else {
                        // none
                    }
                }
            }
        }
    }
    protected void validateForCheckIn(String fileAddType, FilesVO fileVO){
        LOGGER.debug(">> 1. validateForCheckIn -- {}", getVo().getObid());
        if (NullUtil.isNull(fileVO)) throw new FoundationException("omc.error.validate.nullfile");
        vlidateInputFlieVO(fileVO);
        if(!StrUtil.in(fileAddType,GlobalConstants.FILE_MODE_LIST)) throw new FoundationException("omc.error.validate.filemode",fileAddType);
        if(!StrUtil.in(fileVO.getRecordMode(),GlobalConstants.FILE_RECOMODE_LIST)) throw new FoundationException("omc.error.validate.fileRecodeModeInvalid");
        
        if (this.isLocked())  throw new FoundationException("omc.error.validate.lock");

        String policy = getVo().getLifeCycle();
        if (policy == null) {
            BusinessObjectRoot bo = DomUtil.toDom(getVo().getObid());
            policy = bo.getVo().getLifeCycle();
        }
        if(fileAddType.equals(GlobalConstants.FILE_USERINPUT)){
           if(fileVO.getRecordMode().equals("C")){
               if (!LifeCycleUtil.checkAllowedFormat(policy, fileVO.getFileFormat())) throw new FoundationException("omc.error.validate.fileFormat");
               if (!FileFoundationUtil.isAllowedSuffix(fileVO, policy))  throw new FoundationException("omc.error.validate.allowedSuffix");
           }
        }else{
            if (!LifeCycleUtil.checkAllowedFormat(policy, fileVO.getFileFormat())) throw new FoundationException("omc.error.validate.fileFormat");
            if (!FileFoundationUtil.isAllowedSuffix(fileVO, policy))  throw new FoundationException("omc.error.validate.allowedSuffix");
        }
    }
    protected void preProcessForCheckIn(boolean lockFlag, String fileAddType, FilesVO file){
        if (NullUtil.isNone(file.getTitles())) {
            file.setTitles(file.getUserFileName());
        }
        if (NullUtil.isNone(file.getFileTimestamp())) {
            file.setFileTimestamp(OmcUniqueIDGenerator.getObid());
        }
        file.setFromObid(getVo().getObid());
        if(GlobalConstants.FILE_APPEND.equals(fileAddType) || GlobalConstants.FILE_REPLACE.equals(fileAddType)){
            file.setFileStore(getDefaultStore());
            file.setFileLocation(getDefaultLocation().getLocationName());
        }else if(GlobalConstants.FILE_MODIFY.equals(fileAddType)){
            if (NullUtil.isNone(file.getFileStore())) {
                file.setFileStore(getDefaultStore());
            }
            if (NullUtil.isNone(file.getFileLocation())) {
                file.setFileLocation(getDefaultLocation().getLocationName());
            }
        }
        if (lockFlag == true) super.lock(ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID));
    }
    private final void coreProcessForCheckIn(String fileAddType, FilesVO file){
        if(GlobalConstants.FILE_USERINPUT.equals(fileAddType)){
            if(!NullUtil.isNone(file.getRecordMode())){
                if(file.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_CREATE)){
                    createFile(file);
                }else if(file.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_DELETE)){
                    deleteFile(file);
                }else if(file.getRecordMode().equals(GlobalConstants.FILE_RECOMODE_UPDATE)){
                    //DB Data만 수정되어짐. 
                    Files fileDom = (Files)DomUtil.toDom(file);
                    fileDom.modifyObject();
                }
            }
        }else if(GlobalConstants.FILE_APPEND.equals(fileAddType)) {
            createFile(file);
        }else if(GlobalConstants.FILE_REPLACE.equals(fileAddType)) {
            deleteFile();
            createFile(file);
        }else if(GlobalConstants.FILE_MODIFY.equals(fileAddType)) {
            modifyFile(file);
        }else if(GlobalConstants.FILE_DELETE.equals(fileAddType)) {
            deleteFile(file);
        }
    }
    /**
     * <pre>
     * Physical 화일을 Copy함.
     * List<FilesVO> files = revisedProjectsDom.getReleatedFiles();
     * if(!files.isEmpty())  projectsDom.copyFiles(files);
     * </pre>
     *
     * @param fileList Source FilesVO List
     */
    public void copyFiles(List<FilesVO> fileList){
        copyFiles(fileList,new HashMap<String,Object>());
    }
    /**
     * Physical 화일을 Copy함.
     *
     * @param sourceFileList Source FilesVO List
     * @param map
     * @see BusinessObjectRoot#copyFiles(List)
     */
    public void copyFiles(List<FilesVO> sourceFileList,Map<String,Object> map){
        validateForCopyFileCore(sourceFileList,map);
        List<FilesVO> cloneFileList = copyFileCore(sourceFileList,map);
        preProcessForCopyFile(sourceFileList,cloneFileList,map);
        checkInFiles(false,GlobalConstants.FILE_USERINPUT,cloneFileList);
        postProcessForCopyFile(sourceFileList,cloneFileList,map);
    }
    protected void postProcessForCheckIn(String fileAddType, FilesVO file){}
    /**
     * <pre>
     * busineObject와 연관된 파일리스트 checkIn 처리 메소드
     * </pre>
     *
     * @param lockFlag lock여부
     * @param fileAddType file 추가 방법(replace or append)
     * @param fileList fileList
     * @see BusinessObjectRoot#checkInFiles(boolean, String, List)
     */
    public final void checkInFiles(boolean lockFlag, String fileAddType, List<FilesVO> fileList){
        validateForCheckIn(fileAddType,fileList);
        for(FilesVO file : fileList){
            preProcessForCheckIn(lockFlag, fileAddType, file);
        }
        for(FilesVO file : fileList){
            coreProcessForCheckIn(fileAddType, file);
        }
        for(FilesVO file : fileList){
            postProcessForCheckIn(fileAddType, file);
        }
        ObjectChangeLogManagement.createChangeLog(GlobalConstants.CHANGING_CATEGORY_API_CHECKIN_FILE, getVo().getObid(),
                EtcUtil.getNames(getVo()), getVo().getClassName(), null, null, null, null, ThreadLocalUtil.getString(ThreadLocalUtil.KEY.remoteAddr, GlobalConstants.NO_REMOTE_ADDR),
                FileFoundationUtil.parseFileForLogging(fileAddType, fileList));
    }
    /**
     * Business Logic으로 인해 화일을 관리하는 Option을 제공(하나의 VO)
     *
     * @param lockFlag
     * @param fileAddType GlobalConstants.FILE_APPEND, GlobalConstants. FILE_REPLACE, GlobalConstants. FILE_MODIFY, GlobalConstants. FILE_DELETE
     * @param fileVO
     * @see BusinessObjectRoot#checkInFiles(boolean, String, List)
     */
    public final void checkInFile(boolean lockFlag, String fileAddType, FilesVO fileVO){
        List<FilesVO> fileList = new ArrayList<FilesVO>();
        fileList.add(fileVO);
        checkInFiles(lockFlag,fileAddType,fileList);
    }
    /**
     * <pre>
     * WEB에서 Input되어진 FileVO로 Check In하는 경우 사용(VO List)
     *
     {@code
        List<FilesVO> newFileList = new ArrayList<FilesVO>();
        List<FilesVO> copyFileList = new ArrayList<FilesVO>();
        for (FilesVO fileVO : fileList) {
            if(!NullUtil.isNone(fileVO.getRecordMode())){
                if((GlobalConstants.FILE_RECOMODE_DELETE).equals(fileVO.getRecordMode())){
                }else if((GlobalConstants.FILE_RECOMODE_CREATE).equals(fileVO.getRecordMode())){
                    newFileList.add(fileVO);
                }else {
                    copyFileList.add(fileVO);
                }
            } else {
                newFileList.add(fileVO);
            }
        }
        if (newFileList.size() > 0) consult.checkInFiles( GlobalConstants.FILE_UNLOCK, newFileList );
        if (copyFileList.size() > 0) consult.copyFiles(copyFileList ); 
     }
     *
     *
     * </pre>
     * @param lockFlag
     * @param fileList
     */
    public final void checkInFiles(boolean lockFlag, List<FilesVO> fileList){
        checkInFiles(lockFlag,GlobalConstants.FILE_USERINPUT,fileList);
    }
    /**
     * WEB에서 Input되어진 FileVO로 Check In하는 경우 사용(하나의 VO)
     *
     * @param lockFlag
     * @param fileVO
     */
    public final void checkInFile(boolean lockFlag, FilesVO fileVO){
        checkInFile(lockFlag,GlobalConstants.FILE_USERINPUT,fileVO);
    }
    /**
     * Main Server 혹은 Client(예: I/F Damon)에서 FCS Server로 Physical File을 만들어 Check In하는 경우 사용
     * fileVOList에는 실제 작업한 Physical 화일의 Path정보가 있어야 함.
     * 실제 Physical 화일을 FCS서버로 CheckIn후 Meta정보를 Business Object에 Check In함.
     * 응용에서 작업시 임시 작업 Directory는 FmsControl.getFmsPrivateDir()로 Return되어지는 Directory를 사용하도록 함.
     * Physical 화일을 Check Out할 경우에는 Files DOM Object를 가지고 개별적으로 Check Out하면 됨.
     * 
     * @param fileVOList fileVOList의 각화일에는 User File명으로 실제 화일이 되어있어야 함.(User 화일명이 중복되지 않도록 주의)
     * @param tempUploadDir 실제 작업을 수행한 작업 Directory
     */
    public final void checkInFiles(boolean lockFlag, String fileAddType, List<FilesVO> fileVOList, List<File> fileList){
        validateForCheckIn(fileAddType,fileVOList);
        for(FilesVO fileVO : fileVOList){
            preProcessForCheckIn(lockFlag, fileAddType, fileVO);
        }
        List<FilesVO> checkInedFileVOList = HttpClientUtil.checkInFileFromServer(this.getVo(),fileVOList,fileList);
        for(FilesVO fileVO : checkInedFileVOList){
            coreProcessForCheckIn(fileAddType, fileVO);
        }
        for(FilesVO fileVO : checkInedFileVOList){
            postProcessForCheckIn(fileAddType, fileVO);
        }
        for(File file:fileList){
            file.delete();
        }
        fileList.clear();
        ObjectChangeLogManagement.createChangeLog(GlobalConstants.CHANGING_CATEGORY_API_CHECKIN_FILE, getVo().getObid(),
                EtcUtil.getNames(getVo()), getVo().getClassName(), null, null, null, null, ThreadLocalUtil.getString(ThreadLocalUtil.KEY.remoteAddr, GlobalConstants.NO_REMOTE_ADDR),
                FileFoundationUtil.parseFileForLogging(fileAddType, checkInedFileVOList));
    }
    /**
     * <pre>
     * 화일을 Check In함.
     {@code
       file = new File(halfFileName);
       imageDOM.checkInFile(GlobalConstants.FILE_UNLOCK, GlobalConstants.FILE_APPEND, "generic", "designHalf", file);
     }
     * </pre>
     * @param lockFlag
     * @param fileVO
     * @param tempUploadDir
     * @see BusinessObjectRoot#checkInFiles(boolean, String, List, List)
     */
    public final void checkInFile(boolean lockFlag, String fileAddType, String fileFormat, String assignedType, File file){
        List<FilesVO> fileVOList = new ArrayList<FilesVO>();
        FilesVO filesVO = new FilesVO();
        filesVO.setFromObid(this.getObid());
        filesVO.setRecordMode("C");
        filesVO.setAssignedType(assignedType);
        filesVO.setFileFormat(fileFormat);
        filesVO.setUserFileName(file.getName());
        filesVO.setTitles(file.getName());
        fileVOList.add(filesVO);
        List<File> fileList = new ArrayList<File>();
        fileList.add(file);
        this.checkInFiles(lockFlag, fileAddType, fileVOList,fileList);
    }
    /**
     * <pre>
     * busineObject와 연관된 파일 checkIn 처리 메소드
     * </pre>
     *
     * @param lockFlag lock여부
     * @param fileAddType file 추가 방법(replace or append)
     * @param file fileVO
     * @return
     */
    protected void validateForCheckOut(Map<String, Object> map){}
    protected void preProcessForCheckOut(Map<String, Object> map){}
    protected void postProcessForCheckOut(Map<String, Object> map){}
   
    public final List<FilesVO> checkOutFile(){
        return this.getReleatedFiles(true);
    }
    public final List<FilesVO> checkOutFile(String fileFormat){
        return this.getReleatedFiles(fileFormat,"",true);
    }
    public final List<FilesVO> checkOutFile(String fileFormat,String assignedType){
        return this.getReleatedFiles(fileFormat,assignedType,true);
    }
    public final List<FilesVO> checkOutFile(FilesVO fileVO){
        return this.getReleatedFiles(fileVO,true);
    }
    public final List<FilesVO> getReleatedFiles(String fileFormat,String assignedType,boolean incluedServiceURL){
        FilesVO fileVO = new FilesVO();
        fileVO.setFromObid(this.getVo().getObid());
        if(!StrUtil.isEmpty(fileFormat)) fileVO.setFileFormat(fileFormat);
        if(!StrUtil.isEmpty(assignedType)) fileVO.setAssignedType(assignedType);
        List<FilesVO> fileList = getReleatedFiles(fileVO,incluedServiceURL);
        return fileList;
    }
    public final List<FilesVO> getReleatedFiles(String fileFormat,String assignedType){
        return getReleatedFiles(fileFormat,assignedType,false);
    }
    public final List<FilesVO> getReleatedFiles(String fileFormat){
        return(getReleatedFiles(fileFormat,""));
    }
    public final List<FilesVO> getReleatedFiles(){
        return getReleatedFiles("","");
    }
    public final List<FilesVO> getReleatedFiles(boolean incluedServiceURL){
        return getReleatedFiles("","",incluedServiceURL);
    }
    public final List<FilesVO> getReleatedFiles(FilesVO fileVO){
        return getReleatedFileCore(fileVO,false,false);
    }
    public final List<FilesVO> getReleatedFiles(FilesVO fileVO,boolean incluedServiceURL){
        return getReleatedFileCore(fileVO,incluedServiceURL,false);
    }
    private final List<FilesVO> getReleatedFileCore(FilesVO fileVO, boolean incluedServiceURL, boolean incluedFCSInfo){
        List<FilesVO> fileVOList = FileFoundationUtil.getReleatedFile(fileVO);
        for(FilesVO vo : fileVOList){
            vo.setRecordMode(GlobalConstants.FILE_RECOMODE_EQUAL);
            if(incluedFCSInfo || incluedServiceURL){
                FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithFile(vo);
                if(incluedFCSInfo){
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileLocationPhysicalPath, fileServiceURLVO.getFileRootPath());
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileServiceUrl, fileServiceURLVO.getServiceUrl());                    
                }
                if(incluedServiceURL){
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKOUT_URL, fileServiceURLVO.getServiceCheckOutUrl());
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKIN_URL, fileServiceURLVO.getServiceCheckOutUrl());
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKOUT_FROMFMSTOSERVER_URL, fileServiceURLVO.getServiceCheckOutFromServerUrl());
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKIN_FROMFMSTOSERVER_URL, fileServiceURLVO.getServiceCheckInFromServerUrl());
                    vo.getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKOUT_ALL_URL, fileServiceURLVO.getServiceCheckOutAllUrl());
                }
            }
        }
        return fileVOList;
    }
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        this.deleteFile();
    }
    protected void validateForPromote(Map<String, Object> map){}
    protected void preProcessForPromote(Map<String, Object> map){}
    protected void postProcessForPromote(Map<String, Object> map){}
    public final void promote(){
        Map<String, Object> map = new HashMap<String, Object>();
        promote(map);
    }
    public final void promote(Map<String, Object> map){
        validateForPromote(map);
        preProcessForPromote(map);
        String fromStates = getVo().getStates();
        StateInfo toStates = null;
        try {
            toStates = LifeCycleUtil.getTargetState(getVo(), "Promote");
        } catch (BranchException e) {
            LOGGER.debug(e.getMessage());
            return;
        }
        LifeCycleTriggerUtil.executeCheckTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.PROMOTE);
        LifeCycleTriggerUtil.executeActionPreTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.PROMOTE);
        LifeCycleTriggerUtil.executeActionTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.PROMOTE);
        this.change(null, null, null, toStates.getStateName());
        insertRouteLog(fromStates, toStates, "Promote");
        LifeCycleTriggerUtil.executeActionPostTrigger(this, fromStates, toStates.getStateName(),LifeCycleTriggerUtil.Direction.PROMOTE);
        postProcessForPromote(map);
    }

    public final List<ObjectRootVO> getWorkflowRouteVOListByStates(String states /* Define, In Process, ... */){
        if (NullUtil.isNone(states)) throw new IllegalArgumentException();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]",
                GlobalConstants.OQL_OPERATOR_EQUAL, states);

        return this.getRelatedObjects(FoundationConstants.RELCLASS_WORKFLOWOBJECTROUTE,
                FoundationConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_TO, null,                                   // selectPattern
                wherePatternBuf.toString(),             // wherePattern
                paramPatternBuf.toString(),             // parameterPattern
                false,                                  // bInclude
                false,                                  // bResultUnique
                0,                                      // objectLimit
                1);                                    // findDepth
    }
    /**
     * routeState(Working, 1st Processing, ... ) 별 WorkflowRoute VO 목록
     *
     * @param routeState
     * @return
     */
    public final List<ObjectRootVO> getWorkflowRouteVOListByRouteState(String routeState){
        if (NullUtil.isNone(routeState)) throw new IllegalArgumentException();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@REL.[routeState]",
                GlobalConstants.OQL_OPERATOR_EQUAL, routeState);

        return this.getRelatedObjects(FoundationConstants.RELCLASS_WORKFLOWOBJECTROUTE,
                FoundationConstants.BIZCLASS_WORKFLOWROUTE, 
                GlobalConstants.FLAG_TYPE_TO, 
                null,
                wherePatternBuf.toString(),
                paramPatternBuf.toString(),
                false,
                false,
                0,
                1);
    }

    /**
     * WorkflowRoute VO 목록
     *
     * @return
     */
    public final <T> List<T> getWorkflowRouteVOList(){
        return this.getRelatedObjects(FoundationConstants.RELCLASS_WORKFLOWOBJECTROUTE,
                FoundationConstants.BIZCLASS_WORKFLOWROUTE, GlobalConstants.FLAG_TYPE_TO);

    }

    protected void validateForDemote(Map<String, Object> map){}

    protected void preProcessForDemote(Map<String, Object> map){}

    protected void postProcessForDemote(Map<String, Object> map){
        LOGGER.debug(">> 2. postProcessForPromote -- {}", getVo().getObid());
    }
    public final void demote(){
        Map<String, Object> map = new HashMap<String, Object>();
        demote(map);
    }
    public final void demote(Map<String, Object> map){
        validateForDemote(map);
        preProcessForDemote(map);
        String fromStates = getVo().getStates();
        StateInfo toStates = LifeCycleUtil.getTargetState(getVo(), "Demote");
        LifeCycleTriggerUtil.executeCheckTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.DEMOTE);
        LifeCycleTriggerUtil.executeActionPreTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.DEMOTE);
        LifeCycleTriggerUtil.executeActionTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.DEMOTE);
        this.change(null, null, null, toStates.getStateName());
        insertRouteLog(fromStates, toStates, "Demote");
        LifeCycleTriggerUtil.executeActionPostTrigger(this, fromStates, toStates.getStateName(),LifeCycleTriggerUtil.Direction.DEMOTE);
        postProcessForDemote(map);
    }

    private void insertRouteLog(String fromStates, StateInfo toStates, String direction){
        if (LifeCycleUtil.checkWorkflowLifeCycleObject(this)) {
            LifeCycleUtil.createRoutingLog(this, fromStates, toStates.getStateName(), direction);
        }
    }

    /**
     * Workflow Detail URL 정보를 조회한다.
     *
     * @param obid
     * @return
     */
    public static final String getWorkflowUrl(String obid){
        String resultUrl = LifeCycleUtil.getWorkflowUrlForObject(obid);
        return resultUrl;
    }

    /**
     * Modify Approval - Done 수행 시 Validation
     *
     * @param inputParams
     */
    public void preProcessUpdate(Map<String, Object> inputParams) {
        LOGGER.debug(">> 1. preProcessUpdate -- {}", getVo().getObid());
    }

    public void preProcessApproval(Map<String, String> inputParams) {
        LOGGER.debug(">> 1. preProcessApproval -- {}", getVo().getObid());
    }


    /**
     * className에 대한 icon 정보를 조회
     *
     * @param className
     * @return
     */
    public static final String getClassIcon(String className){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
        String resultUrl = classInfo.getClassIconReal();
        return resultUrl;
    }

    /**
     * obid에 대한 icon 정보를 조회
     *
     * @return
     */
    public final String getIcon(){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(getVo().getClassName());
        String resultUrl = classInfo.getClassIconReal();
        return resultUrl;
    }

    /**
     * className에 대한 Small Icon 정보를 조회
     *
     * @param className
     * @return
     */
    public static final String getClassIconSmall(String className){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
        String resultUrl = classInfo.getClassIconSmallReal();
        return resultUrl;
    }

    /**
     * obid에 대한 Small Icon 정보를 조회
     *
     * @return
     */
    public final String getIconSmall(){
        ClassInfo classInfo = ClassInfoUtil.getClassInfo(getVo().getClassName());
        String resultUrl = classInfo.getClassIconSmallReal();
        return resultUrl;
    }

    protected void validateForWithdraw(Map<String, Object> map){}

    protected void preProcessForWithdraw(Map<String, Object> map){}

    protected void postProcessForWithdraw(Map<String, Object> map){}

    public final void withdraw(){
        Map<String, Object> map = new HashMap<String, Object>();
        withdraw(map);
    }
    public final void withdraw(Map<String, Object> map)
    {
        validateForWithdraw(map);
        preProcessForWithdraw(map);
        String fromStates = getVo().getStates();
        StateInfo toStates = LifeCycleUtil.getTargetState(getVo(), "Withdrawal");

        LifeCycleTriggerUtil.executeCheckTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.DEMOTE);
        LifeCycleTriggerUtil.executeActionPreTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.DEMOTE);
        LifeCycleTriggerUtil.executeActionTrigger(this, toStates.getStateName(), LifeCycleTriggerUtil.Direction.DEMOTE);
        this.change(null, null, null, toStates.getStateName());
        insertRouteLog(fromStates, toStates, "Withdrawal");
        LifeCycleTriggerUtil.executeActionPostTrigger(this, fromStates, toStates.getStateName(),
                LifeCycleTriggerUtil.Direction.DEMOTE);
        postProcessForWithdraw(map);
    }
    /**
     * State List 조회
     *
     * @return
     */
    public final List<StateInfo> getStateList() {
        return LifeCycleUtil.getLifeCycleInfo(getVo().getLifeCycle()).getStateList();
    }
    /**
     * 
     *
     * @return
     */
    public final StateInfo getLastState() {
        return LifeCycleUtil.getLifeCycleInfo(getVo().getLifeCycle()).getLastState();
    }
    /**
     * 
     *
     * @return
     */
    public final StateInfo getFirstState() {
        return LifeCycleUtil.getLifeCycleInfo(getVo().getLifeCycle()).getFirstState();
    }
    /**
     * 
     *
     * @param direction
     * @return
     */
    public final StateInfo getTargetState(String direction) {
        return LifeCycleUtil.getTargetState(getVo(), direction);
    }
    
    /**
     * 
     *
     * @param stateName
     * @return
     */
    public final StateInfo getStateByStateName(String stateName) {
        return LifeCycleUtil.getLifieCycleStateByStateName(getVo().getLifeCycle(), stateName);
    }
    
    /**
     * 
     *
     * @return
     */
    public final List<String> getStateListByStateName() {
        return LifeCycleUtil.getLifeCycleStateStringListByName(getVo().getLifeCycle());
    }
    
    /**
     * 
     *
     * @return
     */
    public final ObjectRootVO getFirstWorkflowRouteVOList() {
        ObjectRootVO rtnObjectRootVO = null;
        StateInfo firstState = getFirstState();
        if(!NullUtil.isNull(firstState)) {
             List<ObjectRootVO> rtnObjectRootVOList = getWorkflowRouteVOListByRouteState(firstState.getStateName());
             if(!NullUtil.isNone(rtnObjectRootVOList)) {
                 rtnObjectRootVO = rtnObjectRootVOList.get(0);
             }
        }
        return rtnObjectRootVO;
    }

    void createFile(FilesVO file){
        Files fileDom = (Files)DomUtil.toDom(file);
        fileDom.initialize();
        fileDom.createObject();
        fileDom.modifyObject();
    }
    private void modifyFile(FilesVO file){
        if (GlobalConstants.FILE_RECOMODE_CREATE.equals(file.getRecordMode())) {
            createFile(file);
        } else if (GlobalConstants.FILE_RECOMODE_UPDATE.equals(file.getRecordMode())) {
            Files fileExistDom = (Files)DomUtil.toDom(file.getObid());
            if(!(file.getFilePath().equals(fileExistDom.getVo().getFilePath()))){
                FileFoundationUtil.deleteFile(fileExistDom.getVo());
            }
            file.setFileTimestamp(OmcUniqueIDGenerator.getObid());
            Files fileDom = (Files)DomUtil.toDom(file);
            fileDom.modifyObject();
        } else if (GlobalConstants.FILE_RECOMODE_DELETE.equals(file.getRecordMode())) {
            deleteFile(file);
        }
    }
    private void deleteFile(){
        List<FilesVO> deletefileList = this.getReleatedFiles();
        if(!NullUtil.isNone(deletefileList)){
            for (FilesVO deletefile : deletefileList) {
                deleteFile(deletefile);
            }
        }
    }
    private void deleteFile(FilesVO file){
        Files deleteDom = DomUtil.toDom(file.getObid());
        FileFoundationUtil.deleteFile(deleteDom.getVo());
        Files fileDom = DomUtil.toDom(file.getObid());
        fileDom.getVo().setFromObid(fileDom.getVo().getFromObid() + ":" + GlobalConstants.FILE_DELETE_ING_STATUS);
        fileDom.modifyObject();
    }
    private void vlidateInputFlieVO(FilesVO fileVO){
        if(StrUtil.isEmpty(fileVO.getRecordMode())) throw new FoundationException("omc.error.validate.filerecodemode");
    }
    private void validateForCheckIn(String fileAddType,List<FilesVO> fileList){
        if (NullUtil.isNone(fileList)) throw new FoundationException("omc.error.validate.nullfile");
        for(FilesVO file : fileList){
            validateForCheckIn(fileAddType,file);
        }
    }
    private List<FilesVO> copyPhysicalFile(List<FilesVO> fileList)throws FoundationException{
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        FcsLocationVO storeVO    = FCSServerLocationUtil.getCurrentStoreForLifeCycle(this.getVo().getLifeCycle());
        FcsLocationVO locationVO = FCSServerLocationUtil.getCurrentLocationForUser(storeVO.getStoreName(), userId);
        List<FCSParameterVO> copiedList = HttpClientUtil.copyFileInFCSServer(fileList, locationVO.getLocationName()) ;
        List<FilesVO> cloneFileList = getCopiedFileVOList(fileList,copiedList,locationVO);
        return cloneFileList;
    }
    private List<FilesVO> getCopiedFileVOList(List<FilesVO> sourceFilesVOList, List<FCSParameterVO> copiedList,FcsLocationVO locationVO){
        List<FilesVO> copiledFileVOList = new ArrayList<FilesVO>();
        for(FilesVO fileVO : sourceFilesVOList){
            FilesVO copiedFilesVO = (FilesVO)DomUtil.copy(fileVO);
            copiedFilesVO.setObid(null);
            copiedFilesVO.setColumns(null);
            copiedFilesVO.setSql(null);
            copiedFilesVO.setOutData(new HashMap<String,Object>());
            String communicationUniqueId = (String)fileVO.getOutData().get(OmcFileConstants.C_FCS_COMMUNICATION_UNIQUE_ID);
            for(FCSParameterVO fcsParameterVO : copiedList){
                if(fcsParameterVO.getCommunicationUniqueId().equals(communicationUniqueId)){
                    copiedFilesVO.setFilePath(fcsParameterVO.getTargetFilePath());
                    copiedFilesVO.setSysFileName(fcsParameterVO.getTargetFileName());
                    copiedFilesVO.setSizes(fcsParameterVO.getTargetFileSize());
                    copiedFilesVO.setFileStore(locationVO.getStoreName());
                    copiedFilesVO.setFileLocation(locationVO.getLocationName());
                    copiedFilesVO.setRecordMode("C");
                    copiledFileVOList.add(copiedFilesVO);
                }
            }
        }
        return copiledFileVOList;
    }
    private void validateForCopyFileCore(List<FilesVO> fileList,Map<String,Object> map){
        if (NullUtil.isNone(fileList)) throw new FoundationException("api.object.error.file.nodata");
        for (FilesVO file : fileList) {
            if (NullUtil.isNone(file.getObid())) throw new FoundationException("api.object.error.file.obidNull");
        }
        validateForCopyFile(fileList,map);
    }
    protected void validateForCopyFile(List<FilesVO> fileList,Map<String,Object> map){
        ;
    }
    private List<FilesVO> copyFileCore(List<FilesVO> sourceFileList, Map<String,Object> map){
        return copyPhysicalFile(sourceFileList);
    }
    protected void preProcessForCopyFile(List<FilesVO> sourceFileList, List<FilesVO> cloneFileList, Map<String,Object> map){
        ;
    }
    protected void postProcessForCopyFile(List<FilesVO> sourceFileList, List<FilesVO> cloneFileList, Map<String,Object> map){
        ;
    }
    public String getLifeCycle(){
        return this.getVo().getLifeCycle();
    }
    public String getStates(){
        return this.getVo().getStates();
    }
    public String getNames(){
        return this.getVo().getNames();
    }

    public String getTitles(){
        return this.getVo().getTitles();
    }
    public String getDescriptions(){
        return this.getVo().getDescriptions();
    }
    public String getBranchTo(){
        return this.getVo().getBranchTo();
    }
    /**
     * Approval에서 self reject이 가능한지 여부
     *
     * @param params
     * @return
     */
    public Boolean isAvailableSelfReject(Map<String, Object> params) {
        return false;
    }
    
    public Map<String, Object> getCommonInfo(){
        return new HashMap<String, Object>();
    }
}
