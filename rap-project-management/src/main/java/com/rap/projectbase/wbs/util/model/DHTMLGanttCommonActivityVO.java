/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : DHTMLGanttActivityVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 7. 24.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.projectbase.wbs.util.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.NullUtil;

/**
 * <pre>
 * Class : DHTMLGanttActivityVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public abstract class DHTMLGanttCommonActivityVO extends DHTMLGanttActivityVO{
    protected List<ActivityNameVO> convertToActivityNameList(String activityList){
        List<ActivityNameVO> list = new ArrayList<ActivityNameVO>();
        if(StrUtil.isEmpty(activityList)) return list;
        if(activityList.equals("None")) return list;
        String activityArray[] = activityList.split(Pattern.quote("^~^"));
        for(int i = 0; i < activityArray.length ; i++){
            if(!StrUtil.isEmpty(activityArray[i])){
                String nameArray[] = activityArray[i].split(Pattern.quote("^+^"));
                if(nameArray.length == 4){
                    list.add(new ActivityNameVO(nameArray[0],nameArray[1],nameArray[2],nameArray[3]));
                }
            }
        }
        return list;
    }
    protected String convertToDisplayedNameList(List<ActivityNameVO> list){
        String displayedNameList = "";
        if(NullUtil.isNone(list)) return "None";
        String locale = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.locale, OmcSystemConstants.OMC_LOCALE_LANG_EN);
        for(ActivityNameVO vo : list){
            if(!StrUtil.isEmpty(displayedNameList)) displayedNameList = displayedNameList + ", ";
           if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
               displayedNameList = displayedNameList + vo.getActivityNameKor() + "(" + vo.getDependencyType() + ")";
           }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
               displayedNameList = displayedNameList + vo.getActivityNameChi() + "(" + vo.getDependencyType() + ")";
           }else{
               displayedNameList = displayedNameList + vo.getActivityNameEng() + "(" + vo.getDependencyType() + ")";
           }
        }
        return displayedNameList;
    }
    protected String convertToDisplayedRoleNameList(String roleList){
        String displayedNameList = "";
        if(StrUtil.isEmpty(roleList)) return displayedNameList;
        if(roleList.equals("None")) return displayedNameList;
        
        String roleArray[] = roleList.split(Pattern.quote("^~^"));
        String locale = ThreadLocalUtil.getLocale();
        for(int i = 0; i < roleArray.length ; i++){
            if(!StrUtil.isEmpty(displayedNameList)) displayedNameList = displayedNameList + ",";
            String nameArray[] = roleArray[i].split(Pattern.quote("^+^"));
            if(nameArray.length == 4){
                if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_KO)){
                    displayedNameList = nameArray[1];
                }else if(locale.equals(OmcSystemConstants.OMC_LOCALE_LANG_CH)){
                    displayedNameList = nameArray[2];
                }else{
                    displayedNameList = nameArray[0];
                }
            }
        }
        return displayedNameList;
    }
    protected String convertToDisplayedActivityOwnerList(String activityOwnerList){
        String displayedNameList = "";
        if(StrUtil.isEmpty(activityOwnerList)) return displayedNameList;
        if(activityOwnerList.equals("None")) return displayedNameList;
        
        String ownerArray[] = activityOwnerList.split(Pattern.quote("^~^"));
        String locale = ThreadLocalUtil.getLocale();
        for(int i = 0; i < ownerArray.length ; i++){
            if(!StrUtil.isEmpty(displayedNameList)) displayedNameList = displayedNameList + ",";
            String nameArray[] = ownerArray[i].split(Pattern.quote("^+^"));
            if(nameArray.length == 2){
                displayedNameList = nameArray[0];
            }
        }
        return displayedNameList;
    }
}
