/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rap.omc.foundation.ui.model;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.rap.omc.framework.exception.ApplicationException;



/**
 *
 * @author inhan.choi
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class SysMenuDataVO  {
    @Override
    public String toString() {
        return this.getPnames();
    }
    private String  mode            = "";
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    private String defaultPkinds    = "Command";
    public String getDefaultPkinds() {
        return defaultPkinds;
    }
    public void setDefaultPkinds(String defaultPkinds) {
        this.defaultPkinds = defaultPkinds;
    }

    private String parentobj = "";

    public String getParentobj() {
        return parentobj;
    }

    public void setParentobj(String parentobj) {
        this.parentobj = parentobj;
    }


    private String  pmoduleName     = "";
    private String  pnames          = "";
    private String  pkinds          = "";
    private int     psorting        = 0;
    private String  psubName        = "";
    private String  pisHidden       = "";
    private String  plabel          = "";
    private String  pcallingType    = "";
    private String  plinkHref       = "";
    private String  plinkAlt        = "";
    private String  pimage          = "";
    private String  paccessExpression = "";
    private String  pdescriptions   = "";
    private String  powner          = "";
    private String  obid            = "";
    private String  subObid         = "";

    private String  kind            = "";
    private String  pdisplayedNames = "";
    private String  subObject       = "";
    private String  purl            = "";
    private String  palt            = "";
    private int     pheight         = 0;


    public String getPmoduleName() {
        return pmoduleName;
    }

    public void setPmoduleName(String pmoduleName) {
        this.pmoduleName = pmoduleName;
    }

    public String getPnames() {
        return pnames;
    }

    public void setPnames(String pnames) {
        this.pnames = pnames;
    }

    public String getPkinds() {
        return pkinds;
    }

    public void setPkinds(String pkinds) {
        this.pkinds = pkinds;
    }

    public int getPsorting() {
        return psorting;
    }

    public void setPsorting(int psorting) {
        this.psorting = psorting;
    }

    public String getPsubName() {
        return psubName;
    }

    public void setPsubName(String psubName) {
        this.psubName = psubName;
    }

    public String getPisHidden() {
        return pisHidden;
    }

    public void setPisHidden(String pisHidden) {
        this.pisHidden = pisHidden;
    }

    public String getPlabel() {
        return plabel;
    }

    public void setPlabel(String plabel) {
        this.plabel = plabel;
    }

    public String getPcallingType() {
        return pcallingType;
    }

    public void setPcallingType(String pcallingType) {
        this.pcallingType = pcallingType;
    }

    public String getPlinkHref() {
        return plinkHref;
    }

    public void setPlinkHref(String plinkHref) {
        this.plinkHref = plinkHref;
    }

    public String getPlinkAlt() {
        return plinkAlt;
    }

    public void setPlinkAlt(String plinkAlt) {
        this.plinkAlt = plinkAlt;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public String getPaccessExpression() {
        return paccessExpression;
    }

    public void setPaccessExpression(String paccessExpression) {
        this.paccessExpression = paccessExpression;
    }

    public String getPdescriptions() {
        return pdescriptions;
    }

    public void setPdescriptions(String pdescriptions) {
        this.pdescriptions = pdescriptions;
    }

    public String getPowner() {
        return powner;
    }

    public void setPowner(String powner) {
        this.powner = powner;
    }

    public String getObid() {
        return obid;
    }

    public void setObid(String obid) {
        this.obid = obid;
    }

    public String getSubObid() {
        return subObid;
    }

    public void setSubObid(String subObid) {
        this.subObid = subObid;
    }

    public Map<String, Object> toMap() {
        Map resultMap = new HashMap();
        try {
            Method[] methods = this.getClass().getDeclaredMethods();
            for (Method method : methods ) {
                String methodName = method.getName();
                if (   methodName.startsWith("get") ) {
                    String key  = methodName.substring(3);
                    key         = key.substring(0, 1).toLowerCase() + key.substring(1);
                    resultMap.put(  key   , method.invoke(this, new Object[] {} ) );
                }
            }
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
        return resultMap;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getPdisplayedNames() {
        return pdisplayedNames;
    }

    public void setPdisplayedNames(String pdisplayedNames) {
        this.pdisplayedNames = pdisplayedNames;
    }

    public String getSubObject() {
        return subObject;
    }

    public void setSubObject(String subObject) {
        this.subObject = subObject;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPalt() {
        return palt;
    }

    public void setPalt(String palt) {
        this.palt = palt;
    }

    public int getPheight() {
        return pheight;
    }

    public void setPheight(int pheight) {
        this.pheight = pheight;
    }



    @Override
    public Object clone() {
        SysMenuDataVO vo = new SysMenuDataVO();
        vo.setPmoduleName	( this.getPmoduleName	() );
        vo.setPnames		( this.getPnames         () );
        vo.setPkinds		( this.getPkinds         () );
        vo.setPsorting		( this.getPsorting		() );
        vo.setPsubName		( this.getPsubName		() );
        vo.setPisHidden		( this.getPisHidden		() );
        vo.setPlabel		( this.getPlabel         () );
        vo.setPcallingType	( this.getPcallingType	() );
        vo.setPlinkHref		( this.getPlinkHref		() );
        vo.setPlinkAlt		( this.getPlinkAlt		() );
        vo.setPimage		( this.getPimage         () );
        vo.setPaccessExpression	( this.getPaccessExpression	() );
        vo.setPdescriptions	( this.getPdescriptions	() );
        vo.setPowner		( this.getPowner         () );
        return vo;
    }
}
