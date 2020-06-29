package com.rap.omc.foundation.access.service;

import java.util.Set;

/**
 * <pre>
 * Class : AccessService
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
public interface AccessService {
    public Set<String> getRoleList(String userId);
    public boolean hasRole(String role, String userId);
    public void txnUpdateIAMRole(String userId, String newRoles, String basedRoles);
}
