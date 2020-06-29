package com.rap.omc.foundation.vomap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.rap.omc.util.NullUtil;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.FoundationException;
/**
 * 
 * <pre>
 * Class : VOMapDB
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class VOMapDB {
    Map<String,ObjectRootVO> objectVOMap;
    Map<String,String> obidMap;
    public VOMapDB() {
        super();
        objectVOMap = new HashMap<String,ObjectRootVO>();
        obidMap = new HashMap<String,String>();
    }
    public void putObjectToMap(ObjectRootVO vo) {
        this.objectVOMap.put(vo.getObid(), vo);
    }
    public void putObjectToMap(List<? extends ObjectRootVO> voList) {
        for(ObjectRootVO vo : voList) this.objectVOMap.put(vo.getObid(), vo);
    }

    public void putObjectToMap(ObjectRootVO... vo) {
        int len = vo.length;
        for(int i = 0; i < len; i++) this.objectVOMap.put(vo[i].getObid(), vo[i]);
    }
    public void registerMapDB(ObjectRootVO vo1, ObjectRootVO vo2) {
        this.obidMap.put(vo1.getObid(), vo2.getObid());
        this.obidMap.put(vo2.getObid(), vo1.getObid());
        this.objectVOMap.put(vo1.getObid(), vo1);
        this.objectVOMap.put(vo2.getObid(), vo2);
    }
    public void registerMapDB(String obid1, String obid2) {
        registerMapDB(obid1,obid2,false);
    }
    public void registerMapDB(String obid1, String obid2, boolean isOneSide) {
        this.obidMap.put(obid1, obid2);
        if(isOneSide) return;
        this.obidMap.put(obid2, obid1);
    }
    public void registerMapDB(List<? extends ObjectRootVO> voList) {
        for(ObjectRootVO vo : voList){
            String obid = (String)vo.getOutDataAttributeValue(GlobalConstants.MAP_OUPDATA_OLD_OBJECT_KEY);
            if(NullUtil.isNone(obid)) throw new FoundationException("Not Mapped Object!!!!");
            this.obidMap.put(vo.getObid(), obid);
            this.obidMap.put(obid, vo.getObid());
        }
    }
    public void registerMapDB(ObjectRootVO vo) {
        String obid = (String)vo.getOutDataAttributeValue(GlobalConstants.MAP_OUPDATA_OLD_OBJECT_KEY);
        if(NullUtil.isNone(obid)) throw new FoundationException("Not Mapped Object!!!!");
        this.obidMap.put(vo.getObid(), obid);
        this.obidMap.put(obid, vo.getObid());
    }
    @SuppressWarnings("unchecked")
    public <T> T getMapVO(String obid) {
        return (T)objectVOMap.get(obidMap.get(obid));
    }
    @SuppressWarnings("unchecked")
    public void replaceObject(String attrList, List<? extends ObjectRootVO> ... voListArray) {
        String[] attrArray = attrList.split(Pattern.quote(":"));
        if(attrArray.length != voListArray.length) throw new FoundationException("Input Parameter Not Matched!!!");
        for(int i = 0; i < voListArray.length; i ++){
            String[] eachAttrArray = attrArray[i].split(",");
            for(ObjectRootVO vo : voListArray[i]){
                for(int j = 0; j < eachAttrArray.length; j++){
                    String obid = "";
                    if(eachAttrArray[j].indexOf("from") != -1){
                        obid = (String)vo.getAttribute("fromObid");
                    }else if (eachAttrArray[j].indexOf("to") != -1){
                        obid = (String)vo.getAttribute("toObid");
                    }else{
                        throw new FoundationException("Input Parameter Not Matched!!!");
                    }
                    if(StrUtil.isEmpty(obid)) throw new FoundationException("Input Parameter Not Matched!!!");
                    if(eachAttrArray[j].indexOf("Class") == -1){
                        String newObid = obidMap.get(obid);
                        if(StrUtil.isEmpty(newObid)) throw new FoundationException("Input Parameter Not Matched!!!");
                        vo.setAttributeValue(eachAttrArray[j], newObid);
                    }else{
                        ObjectRootVO mappedVO = objectVOMap.get(obidMap.get(obid));
                        if(NullUtil.isNull(mappedVO)) throw new FoundationException("Input Parameter Not Matched!!!");
                        vo.setAttributeValue(eachAttrArray[j], mappedVO.getClassName());
                    }
                }
            }
        }
    }
}
