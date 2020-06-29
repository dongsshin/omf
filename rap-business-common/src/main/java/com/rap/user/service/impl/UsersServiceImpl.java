/**
 * ===========================================
	 * System Name : LGE GPDM Project
 * Program ID : UsersServiceImpl.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 15. hyeyoung.park Initial
 * ===========================================
 */
package com.rap.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.api.util.general.RelationShip;
import com.rap.omc.api.util.general.UserManagementUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.user.model.UserInfoVO;
import com.rap.omc.foundation.user.model.UserSession;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.user.model.UsersProfileVO;
import com.rap.user.model.UsersSearchVO;
import com.rap.user.service.UsersService;
import com.rap.util.BusinessCommonConstants;

import rap.api.object.organization.dom.Users;
import rap.api.object.organization.model.AbstractUsersVO;
import rap.api.object.organization.model.DivisionUnitVO;
import rap.api.object.organization.model.UsersVO;
import rap.api.object.project.model.WBSActivitiesVO;
import rap.api.object.project.model.WBSJobActivityVO;
import rap.api.object.relation.model.MembersVO;
import rap.application.constants.ApplicationSchemaConstants;


/**
 * <pre>
 * Class : UsersServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UserSession userSession;

//	@Resource(name = "commonDao")
//	private CommonDao commonDao;
//
//    @Resource( name = "organizationsService" )
//    private OrganizationsService organizationsService;
//
//    @Resource( name = "personalDataService" )
//    private PersonalDataService personalDataService;
//
//    @Resource(name="accessService")
//    private AccessService accessService;
//
//    @Resource(name="desktopService")
//    private DesktopService desktopService;
//
//    @Resource(name = "idGenerateService")
//    private IdGenerateService idGenerateService;
//
//    @Resource(name="mailService")
//    MailService mailService;

    /**
     * 사용자 검색
     * @param searchInfo
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#retrieveUserList(lge.gpdm.api.object.common.model.PagingUsersVO)
     */
    
    @Override
    public List<UsersVO> retrieveUserList( UsersSearchVO searchInfo ) {
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        StringBuffer searchCondition = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(NULLIF(@this.[inOffiStatFlag],''),'C')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "T");
        
        if( searchInfo != null && !searchInfo.getIsIncludeInactiveUser() ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, "Active");
        }
//        
//        if( searchInfo != null && !StrUtil.isEmpty(searchInfo.getDescriptions()) ){
//            if( searchInfo.getDescriptions().contains(PlmConstants.DELIM_2) ){
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[descriptions]", GlobalConstants.OQL_OPERATOR_IN, searchInfo.getDescriptions());
//                searchCondition.append( "descriptions:" + searchInfo.getDescriptions() );
//            }
//            else{
//                StringUtil.constructWherePattern(wherePattern, paramPattern, "UPPER(@this.[searchingForUser])", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + (NullUtil.isNone(searchInfo.getDescriptions()) ? searchInfo.getDescriptions(): searchInfo.getDescriptions().toUpperCase()) + GlobalConstants.FLAG_TYPE_ALL);
//                searchCondition.append( "searchingForUser:" + searchInfo.getDescriptions() );
//            }
//        }
        if( searchInfo != null && !StrUtil.isEmpty(searchInfo.getEmailAddress()) ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[emailAddress]", GlobalConstants.OQL_OPERATOR_LIKE, searchInfo.getEmailAddress() + GlobalConstants.FLAG_TYPE_ALL);
            if( searchCondition.length() > 0 ){
                searchCondition.append( "," );
            }
            searchCondition.append( "emailAddress:" + searchInfo.getEmailAddress() );
        }
        if( searchInfo != null && !StrUtil.isEmpty(searchInfo.getHrDepartmentKor()) ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrDepartmentKor]", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + searchInfo.getHrDepartmentKor() + GlobalConstants.FLAG_TYPE_ALL);
            if( searchCondition.length() > 0 ){
                searchCondition.append( "," );
            }
            searchCondition.append( "hrDepartmentKor:" + searchInfo.getHrDepartmentKor() );
        }
        if( searchInfo != null && !StrUtil.isEmpty(searchInfo.getHrDepartmentEng()) ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrDepartmentEng]", GlobalConstants.OQL_OPERATOR_LIKE, GlobalConstants.FLAG_TYPE_ALL + searchInfo.getHrDepartmentEng() + GlobalConstants.FLAG_TYPE_ALL);
            if( searchCondition.length() > 0 ){
                searchCondition.append( "," );
            }
            searchCondition.append( "hrDepartmentEng:" + searchInfo.getHrDepartmentEng() );
        }
        if( searchInfo != null && !StrUtil.isEmpty(searchInfo.getHrDepartmentCode()) ){
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrDepartmentCode]", GlobalConstants.OQL_OPERATOR_IN, searchInfo.getHrDepartmentCode());
            if( searchCondition.length() > 0 ){
                searchCondition.append( "," );
            }
            searchCondition.append( "hrDepartmentCode:" + searchInfo.getHrDepartmentCode() );
        }
        String leaderYn = searchInfo.getLeaderYn();
        if( searchInfo != null && !StrUtil.isEmpty(leaderYn) ){
            if ( "NN".equals(leaderYn) ) {
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[leaderYn]", GlobalConstants.OQL_OPERATOR_EQUAL, "N");
            } else if ( "Y".equals(leaderYn) ) {
                StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[leaderYn]", GlobalConstants.OQL_OPERATOR_EQUAL, leaderYn);
            }
  
            if( searchCondition.length() > 0 ){
                searchCondition.append( "," );
            }
            searchCondition.append( "leaderYn:" + searchInfo.getLeaderYn() );
        }
        
        StringUtil.constructSelectPattern(selectPattern, "SortBy@this.[titles]");
        List<UsersVO> result = new ArrayList<UsersVO>();
        if ( !searchInfo.getIsPaging() ) {
            result = ObjectRoot.findObjects(
                    ApplicationSchemaConstants.BIZCLASS_USERS,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    selectPattern.toString(),
                    wherePattern.toString(),
                    paramPattern.toString(),
                    false,
                    0);
        } else {
            result = ObjectRoot.findObjectPagingList( ApplicationSchemaConstants.BIZCLASS_USERS, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    GlobalConstants.FLAG_TYPE_ALL, 
                    selectPattern.toString(), 
                    wherePattern.toString(),
                    paramPattern.toString(),
                    false, 
                    searchInfo);
        }
        return result;
    }

    /**
     * 사용자 상세정보 조회
     * @param userId
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getUserInfo(java.lang.String)
     */
    @Override
    public UsersVO getUserInfo(String userId) {
        UsersVO usersVO = ObjectRoot.findObject( ApplicationSchemaConstants.BIZCLASS_USERS, userId, true );
        return usersVO;
    }

    /**
     * 사용자 정보 수정
     * @param usersProfileVO
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#txnUpdateUserInfo(lgcns.rnd.application.user.model.UsersProfileVO)
     */
    @Override
    public Users txnUpdateUserInfo(UsersProfileVO usersProfileVO) {
        UserManagementUtil.setUserProperty( usersProfileVO.getNames(), BusinessCommonConstants.USER_PROP_DIVISION, usersProfileVO.getDivision() );
        UserManagementUtil.setUserProperty( usersProfileVO.getNames(), BusinessCommonConstants.USER_PROP_MANAGEMENTGROUP, usersProfileVO.getManagementGroup() );
        UserManagementUtil.setUserProperty( usersProfileVO.getNames(), BusinessCommonConstants.USER_PROP_DAYLIGHT_SAVINGS, usersProfileVO.getDaylightSavings().toString() );
        UserManagementUtil.setUserProperty( usersProfileVO.getNames(), BusinessCommonConstants.USER_PROP_AFFILIATE_UNIT, usersProfileVO.getAffiliate() );
        if( usersProfileVO.getTimeZone() != null && !usersProfileVO.getTimeZone().isEmpty()){
            UserManagementUtil.setUserProperty( usersProfileVO.getNames(), BusinessCommonConstants.USER_PROP_TIME_ZONE, usersProfileVO.getTimeZone() );
        }
        return new Users( usersProfileVO.getObid() );
    }

    /**
     * GPDM 사용자 권한 삭제
     * @param userObid
     * @see lgcns.rnd.application.user.service.UsersService#txnDeleteUserAuth(java.lang.String)
     */
    @Override
    public void txnDeleteUserAuth(String userObid) {
//        Users userDom = new Users( userObid );
//        UsersVO userVO = userDom.getVo();
//
//        if( ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE.equals(userVO.getStates()) ){
////            // Inactive 처리 되기 전에 조회
////            String businessUnitCode = UserManagementUtil.getUserProperty( userVO.getNames(), PlmConstants.USER_PROP_BUSINESS_UNIT );
////            String divisionUnitCode = UserManagementUtil.getUserProperty( userVO.getNames(), PlmConstants.USER_PROP_DIVISION );
//
//            // 1. Inactive 처리
//            userDom.changePolicyAndState(userDom.getVo().getLifeCycle(), ApplicationSchemaConstants.STATE_PERSON_INACTIVE);
//            UserManagementUtil.inActiviateUser( userVO.getNames() );    // PSYSUSER Inactive 처리
//
//            // 2. Site 삭제
//            List<BusinessRelationObjectVO> plantRelList = userDom.getRelationship(ApplicationSchemaConstants.RELCLASS_PLANTUNIT2USERS, ApplicationSchemaConstants.BIZCLASS_PLANTUNIT, GlobalConstants.FLAG_TYPE_FROM);
//            if( plantRelList != null && plantRelList.size() > 0 ){
//                for( int jnx = 0; jnx < plantRelList.size(); jnx++ ){
//                    RelationShip.disconnect( plantRelList.get(jnx) );
//                }
//            }
//            List<BusinessRelationObjectVO> memberList = userDom.getRelationship(ApplicationSchemaConstants.RELCLASS_MEMBERS, ApplicationSchemaConstants.BIZCLASS_PLANTUNIT, GlobalConstants.FLAG_TYPE_FROM);
//            if( memberList != null && memberList.size() > 0 ){
//                for( int jnx = 0; jnx < memberList.size(); jnx++ ){
//                    RelationShip.disconnect( memberList.get(jnx) );
//                }
//            }
//
//            // 5. IAM Role 삭제
//            Set<String> roleList = accessService.getRoleList( userVO.getNames() );
//            if( roleList != null && roleList.size() > 0 ){
//                boolean hasIAMRole = false;
//                for(String role : roleList){
//                    if(StrUtil.in(role,PlmConstants.IAM_ROLE_LIST)){
//                        hasIAMRole = true;break;
//                    }
//                }
//                if( hasIAMRole ){
//                    accessService.txnUpdateIAMRole(userVO.getNames(), null, StrUtil.convertArrayToString(PlmConstants.IAM_ROLE_LIST));
//                }
//            }
//            // 6. Desktop Object 삭제
//            desktopService.txnDeleteDesktopObjectForUser( userDom );
//
//            // 7. GPDM_PERSON USE_FLAG 변경 추가 VC내용
//            commonDao.update("IAM.updateInactivePerson", userVO.getNames());
//        }
//        else{
//            throw new ApplicationException( "Select active user!" );
//        }
    }

    /**
     * 사용자 Portal 화면 초기화
     * 
     * @param userList
     * @param targetModule
     * @see lgcns.rnd.application.user.service.UsersService#txnInitializeUserPortal(java.util.List, java.lang.String)
     */
    @Override
    public void txnInitializeUserPortal(List<UsersVO> userList, String targetModule) {
//        if( userList != null && userList.size() > 0 ){
//            for( int inx = 0; inx < userList.size(); inx++ ){
//                txnInitializeUserPortal( userList.get(inx).getObid(), targetModule );
//            }
//        }
    }

    /**
     * 사용자 Portal 화면 초기화
     * 
     * @param userObid
     * @param targetModule
     * @see lgcns.rnd.application.user.service.UsersService#txnInitializeUserPortal(java.lang.String, java.lang.String)
     */
    @Override
    public void txnInitializeUserPortal(String userObid, String targetModule) {
//        if( StrUtil.isEmpty(userObid) ){
//            throw new ApplicationException( "UserObid is null." );
//        }
//        else{
//            Users userDom = new Users( userObid );
//            
//            // Active 사용자인 경우만 수행
//            if( userDom.getVo().getStates().equals( ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE ) ){
//                String userId = userDom.getVo().getNames();
//                desktopService.txnDeleteDesktopObjectForUser( userDom, targetModule );
//                desktopService.txnCreateDefaultDesktopWidgetForUser( userId, targetModule );
//            }
//        }
    }

    /**
     *
     * @param dataList
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#deleteInactiveUser(java.util.List)
     */
    @Override
    public HashMap<String, Object> deleteInactiveUser(List<UserInfoVO> dataList) {
        HashMap<String, Object> mapResult = new HashMap<String, Object>();
        StringBuffer message = new StringBuffer();
        int successCount = 0;

        for( int inx = 0; inx < dataList.size(); inx++ ){
            try{
                // User 조회
                UsersVO usersVO = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, dataList.get(inx).getUserId(), false);
                if( usersVO != null && ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_INACTIVE.equals(usersVO.getStates()) ){
                    Users deleteDom = new Users( usersVO.getObid() );
                    deleteDom.deleteObject();
                    successCount++;
                }
            }
            catch( Exception e ){
                e.printStackTrace();
                continue;
            }
        }
        if (StrUtil.isEmpty(message.toString())) {
            message.append( "Success!" );
        }
        mapResult.put( "message", message.toString() );
        mapResult.put( "successCount", successCount );
        return mapResult;
    }

    /**
     * 사용자 권한 회수 (Excel Import)
     * @param dataList
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#deleteUserAuth(java.util.List)
     */
    @Override
    public HashMap<String, Object> deleteUserAuth(List<UsersVO> dataList) {
        HashMap<String, Object> mapResult = new HashMap<String, Object>();
        StringBuffer message = new StringBuffer();
        int successCount = 0;

        for( int inx = 0; inx < dataList.size(); inx++ ){
            try{
                if( !StrUtil.isEmpty( dataList.get(inx).getObid() ) ){
                    this.txnDeleteUserAuth( dataList.get(inx).getObid() );
                    successCount++;
                }
            }
            catch( Exception e ){
                e.printStackTrace();
                continue;
            }
        }

        if (StrUtil.isEmpty(message.toString())) {
            message.append( "Success!" );
        }

        mapResult.put( "message", message.toString() );
        mapResult.put( "successCount", successCount );

        return mapResult;
    }
  
    /**
     * Session 정보에서 해당 Role있는지 Check함.
     * 
     * @param roleCode
     * @param userId
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#hasRole(java.lang.String, java.lang.String)
     */
    @Override
    public boolean hasRole(String roleCode){
        return userSession.getRoleSet().contains(roleCode);
    }
    
    @Override
    public boolean hasGroup(String group){
        return userSession.getGroupSet().contains(group);
    }
   
    /**
     * 
     * @param authName
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#hasAuth(java.lang.String)
     */
    @Override
    public boolean hasAuth(String authName, String divisionCode){
        String authInfo = divisionCode + "." + authName;
        return userSession.getManagementRoleSet().contains(authInfo);
    }
    
    /**
     * 
     * @param authName
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#hasAuth(java.lang.String)
     */
    @Override
    public boolean hasAuthExceptDivision(String authName){
        String authInfo = "." + authName;
        boolean result = false;
        
        Iterator<String> it = userSession.getManagementRoleSet().iterator(); 
        while(it.hasNext() && !result) {
            if(it.next().endsWith(authInfo)){
                result = true;
            }
        }        
                
        return result;
    }
    
    /**
     * 
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getRoleSet()
     */
    @Override
    public Set<String> getRoleSet(){
        return userSession.getRoleSet();
    }
    /**
     * 
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getGroupSet()
     */
    @Override
    public Set<String> getGroupSet(){
        return userSession.getGroupSet();
    }
    /**
     * 
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getAccessMenuSet()
     */
    @Override
    public Set<String> getAccessibleMenuSet(){
        return userSession.getAccessibleMenuSet();
    }

    /**
     * 
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getManagementRoleSet()
     */
    @Override
    public Set<String> getManagementRoleSet(){
        return userSession.getManagementRoleSet();
    }

    /**
     * 
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getUserId()
     */
    @Override
    public String getUserId(){
        return userSession.getUserId();
    }
    
    /**
     * 
     * @param groupCode
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#isAssignedGroup(java.lang.String)
     */
    @Override
    public boolean isAssignedGroup(String groupCode){
        return userSession.getGroupSet().contains(groupCode);
    }
    /**
     * 
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#getMyJobActivities()
     */
    @Override
    public List<WBSJobActivityVO> getMyJobActivities(boolean notCompletedOnly){
        String userId = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.userId, GlobalConstants.NO_USER_ID);
        return Users.getUsers(userId).getMyJobActivityList(notCompletedOnly);
    }
    
    public List<UsersVO> retriveUserDivisionMappingList(UsersSearchVO searchInfo) {
        StringBuffer selectPatternBuf = new StringBuffer();
        StringBuffer fromPatternBuf = new StringBuffer();
        StringBuffer wherePatternBuf = new StringBuffer();
        StringBuffer paramPatternBuf = new StringBuffer();
        
        fromPatternBuf.append("<this>ThisConnectedWithTo<[Members]@MB>+");
        fromPatternBuf.append("<[Members]@MB>FromConnectedWithThis<[DivisionUnit]@DV>+");
        
        StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);

        if ( !StrUtil.isEmpty(searchInfo.getNames()) ) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getNames());
        }
        
        if ( !StrUtil.isEmpty(searchInfo.getDivisions()) ) {
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@DV.[names]", GlobalConstants.OQL_OPERATOR_IN, searchInfo.getDivisions());
        }
        
        StringUtil.constructSelectPattern(selectPatternBuf, "@MB.[obid] relationObid");
        StringUtil.constructSelectPattern(selectPatternBuf, "IFNULL(getUserInfo(@MB.[modifier],'T'),@MB.[modifier]) relModifier");
        StringUtil.constructSelectPattern(selectPatternBuf, "omcConvertUtcToLocal(@MB.[modified]) relModified");
        StringUtil.constructSelectPattern(selectPatternBuf, "@DV.[*]");
        StringUtil.constructSelectPattern(selectPatternBuf, "CONCAT(@DV.[titles], '(', @DV.[names], ')') dv_divisionDesc");
        StringUtil.addSortByPattern(selectPatternBuf, "@DV.[titles], @this.[titles]");

        List<UsersVO> result = ObjectRoot.searchObjects(
                ApplicationSchemaConstants.BIZCLASS_USERS,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                selectPatternBuf.toString(),
                fromPatternBuf.toString(),
                wherePatternBuf.toString(),
                paramPatternBuf.toString(),
                false,
                0);
                
        return result;
    }

    /**
     * 
     * @param createList
     * @param deleteList
     * @see lgcns.rnd.application.user.service.UsersService#txnUpdateUserDivisionMappingList(java.util.List)
     */
    @Override
    public void txnUpdateUserDivisionMappingList(List<MembersVO> createList, List<MembersVO> deleteList) {
        if ( createList.size() > 0 ) {
            MembersVO existVO = new MembersVO();
            for ( MembersVO vo : createList ) {
                existVO = checkExistMember(vo.getFromObid(), vo.getToObid());
                if ( existVO == null ) {
                    RelationShip.connect( ApplicationSchemaConstants.RELCLASS_MEMBERS, vo.getFromObid(), vo.getToObid(), null );
                }
            }
        }
        
        if ( deleteList.size() > 0 ) {
            ObjectRoot.deleteObjectSet(deleteList);
        }
    }
    public MembersVO checkExistMember(String divisionObid, String userObid) {
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[fromObid]", GlobalConstants.OQL_OPERATOR_IN, divisionObid);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[toObid]", GlobalConstants.OQL_OPERATOR_IN, userObid);
        
        MembersVO existVO = new MembersVO();
        existVO = ObjectRoot.findObject(
                ApplicationSchemaConstants.RELCLASS_MEMBERS, 
                GlobalConstants.FLAG_TYPE_ALL, 
                GlobalConstants.FLAG_TYPE_ALL, 
                GlobalConstants.FLAG_TYPE_ALL, 
                wherePattern.toString(), 
                paramPattern.toString(), 
                false);
        return existVO;
    }

    /**
     * 사용자 Migration 수행
     * @param dataList
     * @return
     * @see lgcns.rnd.application.user.service.UsersService#excelImportUsers(java.util.List)
     */
    @Override
    public HashMap<String, Object> excelImportUsers(List<UsersVO> dataList){
        HashMap<String, Object> mapResult = new HashMap<String, Object>();
        StringBuffer message = new StringBuffer();
        int successCount = 0;
        int failCount = 0;
//        
//        if( !NullUtil.isNone(dataList) ){
//            Users createDom = null;
//            UsersVO createVo = null;
//            String userId = "";
//            String searchingForUserStr = "";
//            for( int inx = 0; inx < dataList.size(); inx++ ){
//                try{
//                    createVo = dataList.get( inx );
//                    userId = createVo.getNames();
//                    
//                    // 중복체크
//                    UsersVO existVo = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userId, false);
//                    if( NullUtil.isNull(existVo) ){
//                        // Create PSYSUSER
//                        HashMap<String, Object> searchInfo = new HashMap<String, Object>();
//                        searchInfo.put("empNo", userId);
//                        HashMap<String,Object> result = commonDao.select("HR.retrieveSysUserCheck", searchInfo);
//                        if(NullUtil.isNull(result)) {
//                            UserManagementUtil.createUser(userId, GlobalConstants.SYSKEY_KIND_NAME_USER, userId, createVo.getTitles(), "LG", "", "");
//                        }
//                        
//                        // Data setting for search
//                        searchingForUserStr = createVo.getDescriptions();
//                        searchingForUserStr += " " + createVo.getGradeName();
//                        searchingForUserStr += " " + createVo.getMailId();
//                        createVo.setSearchingForUser( searchingForUserStr );
//    
//                        // Create PTUSER
//                        createVo.setClassName( ApplicationSchemaConstants.BIZCLASS_USERS );
//                        createVo.setLifeCycle( ApplicationSchemaConstants.LIFECYCLE_PERSON );
//                        createVo.setStates( ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE );
//                        
//                        createDom = DomUtil.toDom( createVo );
//                        createDom.createObject();
//                        
//                        successCount++;
//                    }
//                    else{
//                        failCount++;
//                    }
//                }
//                catch( Exception e ){
//                    failCount++;
//                    e.printStackTrace();
//                    continue;
//                }
//            }
//        }
//
//        if (StrUtil.isEmpty(message.toString())) {
//            message.append( "Success!" );
//        }

        mapResult.put( "message", message.toString() );
        mapResult.put( "successCount", successCount );
        mapResult.put( "failCount", failCount );
        
        return mapResult;
    }
    
    public List<UsersVO> getUserProperty(UsersSearchVO searchInfo) {
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer paramPattern = new StringBuffer();
        
        StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[states]", GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
        StringUtil.constructWherePattern(wherePattern, paramPattern, "IFNULL(NULLIF(@this.[inOffiStatFlag],''),'C')", GlobalConstants.OQL_OPERATOR_NOT_EQUAL, "T");
        if ( !StrUtil.isEmpty(searchInfo.getNames()) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[names]", GlobalConstants.OQL_OPERATOR_EQUAL, searchInfo.getNames());
        }
        if ( !StrUtil.isEmpty(searchInfo.getHrDepartmentCode()) ) {
            StringUtil.constructWherePattern(wherePattern, paramPattern, "@this.[hrDepartmentCode]", GlobalConstants.OQL_OPERATOR_IN, searchInfo.getHrDepartmentCode());
        }
        
        StringUtil.constructSelectPattern(selectPattern, "To[HasAuthorizationGroup].From.titles authGroupTitle");
        List<UsersVO> userList = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_USERS, selectPattern.toString(), wherePattern.toString(), paramPattern.toString());
        for ( UsersVO vo : userList ) {
            HashMap<String, Object> outData = new HashMap<String, Object>();
            outData = vo.getOutData();
            outData.put("division", UserManagementUtil.getUserProperty(vo.getNames(), BusinessCommonConstants.USER_PROP_DIVISION));
            outData.put("businessUnit", UserManagementUtil.getUserProperty(vo.getNames(), BusinessCommonConstants.USER_PROP_BUSINESS_UNIT));
            outData.put("affiliate", UserManagementUtil.getUserProperty(vo.getNames(), BusinessCommonConstants.USER_PROP_AFFILIATE_UNIT));
            outData.put("timeZone", UserManagementUtil.getUserProperty(vo.getNames(), BusinessCommonConstants.USER_PROP_TIME_ZONE));
            Users user = DomUtil.toDom(vo);
            List<DivisionUnitVO> divisionList = user.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_MEMBERS, ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT, GlobalConstants.FLAG_TYPE_FROM);
            if ( !NullUtil.isNone(divisionList) && divisionList.size() > 0 ) {
                outData.put("divisionName", divisionList.get(0).getNames());
            }
            vo.setOutData(outData);
        }
        
        return userList;
    }
    
    public void modifyUserProperty(UsersVO userVO, HashMap<String, Object> propertyParams) {
        if ( !NullUtil.isNone((String)propertyParams.get("division")) ) {
            UserManagementUtil.setUserProperty( userVO.getNames(), BusinessCommonConstants.USER_PROP_DIVISION, (String)propertyParams.get("division") );
        }
        if ( !NullUtil.isNone((String)propertyParams.get("businessUnitCode")) ) {
            UserManagementUtil.setUserProperty( userVO.getNames(), BusinessCommonConstants.USER_PROP_BUSINESS_UNIT, (String)propertyParams.get("businessUnitCode") );
        }
        if ( !NullUtil.isNone((String)propertyParams.get("affiliate")) ) {
            UserManagementUtil.setUserProperty( userVO.getNames(), BusinessCommonConstants.USER_PROP_AFFILIATE_UNIT, (String)propertyParams.get("affiliate") );
        }
        if ( !NullUtil.isNone((String)propertyParams.get("timeZone")) ) {
            UserManagementUtil.setUserProperty( userVO.getNames(), BusinessCommonConstants.USER_PROP_TIME_ZONE, (String)propertyParams.get("timeZone") );
        }
    }
    
    /**
     * Admin > 관리자용 엑셀 업로드 > User Property Setting Excel Import
     *
     * @param dataList
     * @return
     */
    public HashMap<String, Object> excelImportUserProperty(UsersVO userVO, HashMap<String, Object> propertyParams) {
        HashMap<String, Object> mapResult = new HashMap<String, Object>();
        StringBuffer message = new StringBuffer();
        
        if( !NullUtil.isNull(userVO) ){
            try {
                UsersVO existVo = ObjectRoot.findObject(ApplicationSchemaConstants.BIZCLASS_USERS, userVO.getNames(), false);
                if ( !NullUtil.isNull(existVo) ) {
                    modifyUserProperty(userVO, propertyParams);
                }
                else {
                    message.append("Not exists user.");
                }
            }
            catch ( Exception e ) {
                e.printStackTrace();
                if ( !StrUtil.isEmpty(message.toString()) ) {
                    message.append(new StringBuffer(e.getMessage()));
                } else {
                    message = new StringBuffer(e.getMessage());
                }
            }
        }
        mapResult.put( "message", message.toString() );
        return mapResult;
    }

	@Override
	public boolean hasAuth(String authName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void txnUpdateSessionPlant(String userId, String preferredSite) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UsersVO retrieveUserInfoByMailId(String mailId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UsersVO retrieveUserInfoByEmpNo(String empNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AbstractUsersVO> retrieveAbstractUserList(UsersSearchVO searchInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WBSActivitiesVO> getMyActivities(Set<String> classFilter, boolean isDelayedOnly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WBSActivitiesVO> getMyActivities(Set<String> classFilter, boolean isDelayedOnly,
			boolean onlyPrevActivityComplete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void txnCopyUserDivisionMappingList(String copyType, String fromObid, String toObid) {
		// TODO Auto-generated method stub
		
	}
}