/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rap.omc.foundation.ui.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author inhan.choi
 */
public class CommandButtonAccessVO {
    String commandName  = "";
    String loginUserId  = "";
    String obid         = "";
    String contextObid  = "";
    String callingPosition = "";
    int    procLevel    = 1;
    String inputTestMode = "FALSE";

    String isAccessable  = "";
    String isVisible  = "";

    public String getIsAccessable() {
        return isAccessable;
    }

    public void setIsAccessable(String isAccessable) {
        this.isAccessable = isAccessable;
    }

    public String getIsVisible() {
        return isVisible;
    }

    public void setIsVisible(String isVisible) {
        this.isVisible = isVisible;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getLoginUserId() {
        return loginUserId;
    }

    public void setLoginUserId(String loginUserId) {
        this.loginUserId = loginUserId;
    }

    public String getObid() {
        return obid;
    }

    public void setObid(String obid) {
        this.obid = obid == null ? "" : obid;
    }

    public String getContextObid() {
        return contextObid;
    }

    public void setContextObid(String contextObid) {
        this.contextObid = contextObid == null ? "" : contextObid;
    }

    public Map toMap ( ) {
        Map result = new HashMap();
        result.put ( "commandName"      , commandName       );
        result.put ( "loginUserId"      , loginUserId       );
        result.put ( "obid"             , obid              );
        result.put ( "contextObid"      , contextObid       );
        result.put ( "callingPosition"  , callingPosition   );
        result.put ( "procLevel"        , procLevel         );
        result.put ( "inputTestMode"    , inputTestMode     );
        return result;
    }

    public String getCallingPosition() {
        return callingPosition;
    }

    public void setCallingPosition(String callingPosition) {
        this.callingPosition = callingPosition == null ? "" : callingPosition;
    }

    public int getProcLevel() {
        return procLevel;
    }

    public void setProcLevel(int procLevel) {
        this.procLevel = procLevel;
    }

    public String getInputTestMode() {
        return inputTestMode;
    }

    public void setInputTestMode(String inputTestMode) {
        this.inputTestMode = inputTestMode;
    }


}
