/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : UserSessionUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 8. 23.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.util;

import java.util.Set;

import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.user.service.UsersService;

/**
 * <pre>
 * Class : UserSessionUtil
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class UserServiceUtil {
    private UsersService usersService;
    private static UserServiceUtil cInstance;
    
    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static UserServiceUtil getInstance(){
        if (cInstance == null) {
            cInstance = new UserServiceUtil();
            cInstance.usersService = (UsersService)SpringFactoryUtil.getBean("usersService");
        }
        return cInstance;
    }
    public static boolean hasRole(String roleCode){
        return getInstance().usersService.hasRole(roleCode);
    }
    public static boolean hasGroup( String groupCode ){
        return getInstance().usersService.isAssignedGroup( groupCode );
    }
    public static boolean hasAuth( String authName ){
        return getInstance().usersService.hasAuth( authName );
    }
    public static boolean hasAuth( String authName, String divisionCode ){
        return getInstance().usersService.hasAuth( authName, divisionCode );
    }
    public static boolean hasAuthExceptDivision( String authName ){
        return getInstance().usersService.hasAuthExceptDivision( authName );
    }
    public static Set<String> getRoleSet(){
        return getInstance().usersService.getRoleSet();
    }
    public static Set<String> getGroupSet(){
        return getInstance().usersService.getGroupSet();
    }
    public static Set<String> getAccessibleMenuSet(){
        return getInstance().usersService.getAccessibleMenuSet();
    }
    public static Set<String> getManagementRoleSet(){
        return getInstance().usersService.getManagementRoleSet();
    }
    public static String getUserId(){
        return getInstance().usersService.getUserId();
    }
}
