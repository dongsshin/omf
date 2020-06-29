package com.rap.omc.foundation.access.model;

/**
 * <pre>
 * Class : RoleVO
 * Description : TODO
 * </pre>
 * 
 * @author youngmi.won
 */
public class RoleVO {
    private String obid;
    private String names;
    private String descriptions;
    private String userId;

    public String getObid() {
        return obid;
    }

    public void setObid(String obid) {
        this.obid = obid;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
