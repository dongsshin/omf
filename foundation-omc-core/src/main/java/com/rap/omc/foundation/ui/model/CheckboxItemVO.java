/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rap.omc.foundation.ui.model;

import org.apache.commons.lang.StringUtils;

/**
 *
 * @author inhan.choi
 */
public class CheckboxItemVO {
    String objectName = "";
    String objectLabel = "";
    String objectValue = "";
    boolean objectChecked = false;
    int    order       = 0;

    public CheckboxItemVO() {
        objectName = "";
        objectLabel = "";
        objectValue = "";
        objectChecked = false;
    }

    public CheckboxItemVO(String objectName, String objectLabel, String objectValue, String objectChecked) {
        this.objectName = objectName;
        this.objectLabel = objectLabel;
        this.objectValue = objectValue;
        this.objectChecked = StringUtils.isEmpty(objectChecked) || objectChecked.toUpperCase().equals("FALSE") ? false : true;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getObjectLabel() {
        return objectLabel;
    }

    public void setObjectLabel(String objectLabel) {
        this.objectLabel = objectLabel;
    }

    public Object getObjectValue() {
        return objectValue;
    }

    public void setObjectValue(String objectValue) {
        this.objectValue = objectValue;
    }

    public void setObjectChecked(boolean objectChecked) {
        this.objectChecked = objectChecked;
    }
    public boolean getObjectChecked() {
        return objectChecked;
    }

}
