/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : BusinessObject.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.object.model.BusinessObjectRootVO;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.BaseFoundationUtil;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.util.foundation.FoundationValidationUtil;
import com.rap.omc.util.foundation.RevisionUtil;


/**
 * <pre>
 * Class : BusinessObject
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class BusinessObject extends BusinessObjectRoot {
    private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObject.class);

    /**
     * 
     * @return
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString(){
        return "BusinessObject [toString()=" + super.toString() + "]";
    }
    /**
     * @param vo
     */
    public BusinessObject(BusinessObjectVO vo) {
        super(vo);
    }
    /**
     * 
     * @param obid obid
     */
    public BusinessObject(String obid) {
        super(obid);
    }
    /**
     * 
     * @param obid obid
     * @param withOutData outData를 포함할 것인지 여부
     */
    
    public BusinessObject(String obid, boolean withOutData) {
        super(obid,withOutData);
    }
    @Override
    public BusinessObjectVO getVo(){
        return (BusinessObjectVO)super.getVo();
    }

    @Override
    public void initialize(){
        super.initialize();

        initializeBusinessObject();
    }
    public void initializeBusinessObject(){
        if (getVo().getRevision() == null) {
            getVo().setRevision(getDefaultRevision());
        }
        getVo().setFlags(getVo().getFlags() | OmcSystemConstants.OBJROOT_FLAG_Revisible);
    }

    protected void validateForRevise(Map<String, Object> map){}
    protected void preProcessForRevise(Map<String, Object> map){}
    protected final BusinessObjectVO reviseCore(Map<String, Object> map){
        BusinessObjectVO oldVO = (BusinessObjectVO)getVo();
        BusinessObjectVO newVO = (BusinessObjectVO)DomUtil.cloneBean(oldVO);
        
        BusinessObject   oldDom = (BusinessObject)DomUtil.toDom(oldVO);
        BusinessObject   newDom = (BusinessObject)DomUtil.toDom(newVO);
        
        String oldObid  = oldVO.getObid();
        String nextObid = NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_OBID);

        oldVO.setPreviousObid(nextObid);
        newDom.reviseTo(oldObid, nextObid);
        oldDom.update();
        newDom.getVo().setStates(newDom.getFirstState().getStateName());
        newDom.create();
        this.copyRelationWithRelationshipRule(this,newVO,true,map);
        return newDom.getVo();
    }
    private void reviseTo(String prevObid, String nextObid){
    
        String nextRevision = RevisionUtil.getNextRevisionNo(getVo().getLifeCycle(), getVo().getRevision());
        getVo().setRevision(nextRevision);
        getVo().setObid(nextObid);
        getVo().setPreviousObid("1");
        getVo().setNextObid(prevObid);
        getVo().setStates(getDefaultStates());
        initVariable();
    }
    @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        BusinessObjectVO businessObjectSoruceVO = this.getVo();  
        if(this.isFirst()){
            if(!this.isLast()){
                BusinessObject nextDom = new BusinessObject(businessObjectSoruceVO.getPreviousObid());
                nextDom.getVo().setNextObid("1");
                nextDom.modify();
            }
        }else{
            if(this.isLast()){
                BusinessObject previousDom = new BusinessObject(businessObjectSoruceVO.getNextObid());
                previousDom.getVo().setPreviousObid("1");
                previousDom.modify();
            }else{
                BusinessObject previousDom = new BusinessObject(businessObjectSoruceVO.getNextObid());
                previousDom.getVo().setPreviousObid(businessObjectSoruceVO.getPreviousObid());
                previousDom.modify();
                
                BusinessObject nextDom = new BusinessObject(businessObjectSoruceVO.getPreviousObid());
                nextDom.getVo().setNextObid(businessObjectSoruceVO.getNextObid());
                nextDom.modify();
            }
        }
    }
    protected void postProcessForRevise(Map<String, Object> map){
        if("true".equals(map.get(GlobalConstants.REVISE_OPTION_COPYFILE))){
            BusinessObjectVO newVo = (BusinessObjectVO) map.get(OmcApplicationConstants.MAP_API_REVISE_NEW_OBJECT);
            List<FilesVO> fileList = super.getReleatedFiles();
            BusinessObject newDom = DomUtil.toDom(newVo);  
            if(!NullUtil.isNone(fileList)){
                newDom.copyFiles(fileList);
            }
        }
    }
    /**
     * Revise
     * this.getOutDataAttributeValue(OmcApplicationConstants.MAP_API_REVISE_NEW_OBJECT)로 Revised되어진 신규 Object를 찾을 수 있다.
     * 화일을 Copy해야하는 경우 map.put(GlobalConstants.REVISE_OPTION_COPYFILE,"true");로 하면 됨.
     * @param map
     * @return Old VO
     */
    @SuppressWarnings("unchecked")
    public final <T> T revise(Map<String, Object> map){
        if(NullUtil.isNone(map))  map = new HashMap<String, Object>();
        this.validateForRevise(map);
        this.preProcessForRevise(map);
        BusinessObjectVO newVo = reviseCore(map);
        map.put(OmcApplicationConstants.MAP_API_REVISE_NEW_OBJECT, newVo);
        this.postProcessForRevise(map);
        return (T)newVo;
    }
    @Override
    protected final void CloneCorePre(BusinessObjectRootVO newVO){
        super.CloneCorePre(newVO);
        ((BusinessObjectVO)newVO).setPreviousObid("1");
        ((BusinessObjectVO)newVO).setNextObid("1");
        ((BusinessObjectVO)newVO).setRevision(RevisionUtil.getNextRevisionNo(newVO.getLifeCycle(),""));
    }
    /**
     * Revise
     * @return Old VO
     * @see BusinessObject#revise(Map)
     */
    public final <T> T  revise(){
        Map<String, Object> map = new HashMap<String, Object>();
        return this.revise(map);
    }
    @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        BusinessObjectVO target = (BusinessObjectVO)CommonServiceUtil.getObject(getVo().getObid());
        if (!target.getPreviousObid().equals("1")) {
            BusinessObjectVO previousObj = (BusinessObjectVO)CommonServiceUtil.getObject(target.getPreviousObid());
            if (!NullUtil.isNull(previousObj)) {
                previousObj.setNextObid(target.getNextObid());
                CommonServiceUtil.updateObject(previousObj);

                BusinessObject boDom = (BusinessObject)DomUtil.toDom(previousObj);
                boDom.update();
            }
        }
        if (!target.getNextObid().equals("1")) {
            BusinessObjectVO nextObj = (BusinessObjectVO)CommonServiceUtil.getObject(target.getNextObid());
            if (!NullUtil.isNull(nextObj)) {
                nextObj.setPreviousObid(target.getPreviousObid());

                BusinessObject boDom = (BusinessObject)DomUtil.toDom(nextObj);
                boDom.update();
            }
        }
    }
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
    }
    /**
     * VO 정보를 기준으로 Object를 생성한다. (DB 변경작업)
     * name, policy, revision 정보는 전달인자로 받은 값을 사용한다.
     *
     * @param names
     * @param lifeCycle lifeCycle (ex, EC Part Policy)
     * @param revision
     */
    public final void createObject(String names, String lifeCycle, String revision){
        Map<String, Object> map = new HashMap<String, Object>();
        this.createObject(names,lifeCycle,revision,map);
    }
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        initialize();
        FoundationValidationUtil.checkInstantiable(getVo().getClassName());
    }
    public final void createObject(String name, String policy, String revision, Map<String, Object> map){
        this.validateForCreate(map);
        this.preProcessForCreate(map);
        super.create();
        this.postProcessForCreate(map);
    }
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newRevision,String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        BusinessObjectVO inputVO = getVo();
        if (!NullUtil.isNone(newClassName)) {
            inputVO.setRevision(newRevision);
        }
    }

    /**
     * className, Name, LifeCycle, revision, state 값을 변경한다.
     *
     * @param newClassName
     * @param newName
     * @param newLifeCycle
     * @param newRevision
     * @param newStates
     */
    public final void change(String newClassName, String newName, String newLifeCycle, String newRevision, String newStates){
        validateForChange(newClassName, newName, newLifeCycle, newStates, null);
        preProcessForChange(newClassName, newName, newLifeCycle, newRevision, newStates, null);
        super.change();
        postProcessForChange(newClassName, newName, newLifeCycle, newStates, null);
    }

    /**
     * 
     *
     * @param newClassName
     * @param newName
     * @param newLifeCycle
     * @param newRevision
     * @param newStates
     */
    public final void change(String newClassName, String newName, String newLifeCycle, String newRevision,String newStates, Map<String, Object> map){
        validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        preProcessForChange(newClassName, newName, newLifeCycle, newRevision, newStates, map);
        super.change();
        postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
    }
    
    protected String getDefaultRevision(){
        String nextRevision = RevisionUtil.getNextRevisionNo(getVo().getLifeCycle(), getVo().getRevision());
        return nextRevision;
    }

    /**
     * First Object인지 Return
     *
     * @return
     */
    public final boolean isFirst(){
        if (getVo().getNextObid().equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Latest Object인지 Return
     *
     * @return
     */
    public final boolean isLast(){
        if (getVo().getPreviousObid().equals("1")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 자기 자신의 first Revision Object를 Return
     *
     * @return
     */
    public final BusinessObjectVO getFirstRevision(){
        if (isFirst()) {
            return getVo();
        } else {
            List<BusinessObjectVO> rslt = RevisionUtil.getLastRevision(getVo().getObid());
            if(NullUtil.isNone(rslt)) return null;
            if(rslt.size() != 1) throw new FoundationException("[Foundation]Revsion Chain invalid");
            return rslt.get(0);
        }
    }

    /**
     * 자기 자신의 last Revision Object를 Return 
     *
     * @return BusinessObjectVO
     */
    @SuppressWarnings("unchecked")
    public final <T extends BusinessObjectVO> T getLastRevision(){
        if (isLast()) {
            return (T)getVo();
        } else {
            List<BusinessObjectVO> rslt = RevisionUtil.getLastRevision(getVo().getObid());
            if(NullUtil.isNone(rslt)) return null;
            if(rslt.size() != 1) throw new FoundationException("[Foundation]Revsion Chain invalid");
            return (T)rslt.get(0);
        }
    }

    /**
     * 자기 자신의 Next BusinessObject를 return(previousObid로 검색)
     *
     * @return BusinessObjectVO
     */
    public final <T extends BusinessObjectVO> T getNextRevision(){
        if (!NullUtil.isNone(getVo().getPreviousObid()) && !getVo().getPreviousObid().equals("1")) {
            return(CommonServiceUtil.getObject(getVo().getPreviousObid()));
        }
        return null;
    }
    /**
     * 자기 자신의 Previous BusinessObject를 return(nextObid로 검색)
     *
     * @return BusinessObjectVO
     */
    public final <T extends BusinessObjectVO> T getPreviousRevision(){
        if (!NullUtil.isNone(getVo().getNextObid()) && !getVo().getNextObid().equals("1")) {
            return(CommonServiceUtil.getObject(getVo().getNextObid()));
        }
        return null;
    }
    /**
     * Revision Chain상에 있는 모든 BusinessObject의 List를 Return
     *
     * @return BusinessObject의 List
     */
    public final <T extends BusinessObjectVO> List<T> getRevisions(){
        List<BusinessObjectVO> rslt = RevisionUtil.getRevisions(getVo().getObid());
        return(revisionSort(rslt));
    }
    @SuppressWarnings("unchecked")
    private <T extends BusinessObjectVO> List<T> revisionSort(List<BusinessObjectVO> voList){
        List<T> rslt = new ArrayList<T>();
        BusinessObjectVO tempVO = null;
        for(BusinessObjectVO vo : voList ){
            if(vo.getNextObid().equals("1")) tempVO = vo;
        }
        if(tempVO == null) return rslt;
        rslt.add((T)tempVO);
        while(true){
            BusinessObjectVO foundVo = revisionSort(voList,tempVO);
            if(foundVo == null) break;
            rslt.add((T)foundVo);
            tempVO = foundVo;
        }
        return rslt;
    }
    /**
     * nameList에 대한 Business Object List를 Return함.
     *
     * @param className Class Name
     * @param nameList name List
     * @return Business Object List
     */
    @SuppressWarnings("unchecked")
    public static final <T> List<T> getBusinessLatestObjectList(String className,List<String> nameList){
        List<String> obidList = BaseFoundationUtil.getBusinessLatestObjectList(className,nameList);
        if(NullUtil.isNone(obidList)) return new ArrayList<T>();
        List<T> list = new ArrayList<T>();
        for(String obid : obidList){
            BusinessObject dom = new BusinessObject(obid);
            list.add((T)dom.getVo());  
        }
        return list;
    }
    private BusinessObjectVO revisionSort(List<BusinessObjectVO> voList, BusinessObjectVO findVo){
        for(BusinessObjectVO vo : voList ){
            if(vo.getNextObid().equals(findVo.getObid())) return vo;
        }
        return null;
    }
    /**
     * Business Object를 Return함.
     *
     * @param className Class Name
     * @param names names
     * @param revision revision
     * @return Business Object VO
     */
    @SuppressWarnings("unchecked")
    public static final <T> T  findBusinessObject(String className, String names, String revision){
        List<String> obidList = BaseFoundationUtil.getBusinessObjects(className,names,revision);
        if(NullUtil.isNone(obidList)) return null;
        if(obidList.size() > 1) throw new FoundationException("");
        BusinessObject dom = new BusinessObject(obidList.get(0));
        return ((T)dom.getVo());
    }
    /**
     * 
     *
     * @return
     */
    public final String getPreviousObid(){
        return this.getVo().getPreviousObid();
    }
    /**
     * 
     *
     * @return
     */
    public final String getNextObid(){
        return this.getVo().getNextObid();
    }
    /**
     * 
     *
     * @return
     */
    public final String getRevision(){
        return this.getVo().getRevision();
    }

}
