/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : DomUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 7. yongsik1.kim Initial
 * ===========================================
 */
package com.rap.omc.api.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// import org.springframework.beans.BeanUtils;
import org.apache.commons.beanutils.BeanUtils;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.ObjectRootVO;
import com.rap.omc.dataaccess.paging.model.PagingEntity;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.util.NullUtil;


/**
 * <pre>
 * Class : DomUtil
 * Description : TODO
 * </pre>
 * 
 * @author yongsik1.kim
 */
public class DomUtil {

    /**
     * Dom이나 VO 클래스를 복제한다.
     * 
     * @param from
     * @return
     */
    public static Object cloneBean(Object from){
        Object to = null;
        try {
            to = BeanUtils.cloneBean(from);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
            throw new FoundationException("omc.error.util.domNotCreated",e);
        }

        return to;
    }

    /**
     * VO를 기반으로 Dom Class를 생성한다.
     *
     * @param vo
     * @return
     */
    public static <T extends ObjectRoot> T toDom(ObjectRootVO vo){
        return makeDom(vo);
    }

    /**
     * obid에 해당하는 VO 정보를 DB에서 조회하여, VO를 생성한 뒤 생성된 VO를 기반으로 Dom Class를 생성한다.
     * (outData에 locker, owner 등의 추가 정보를 같이 조회함)
     *
     * @param obid
     * @return
     */
    public static <T extends ObjectRoot> T toDom(String obid){
        if (NullUtil.isNone(obid)) { throw new FoundationException("api.object.warn.obid"); }
        ObjectRootVO vo = CommonServiceUtil.getObjectWithOutData(obid);
        if (NullUtil.isNull(vo)) { throw new FoundationException("api.object.warn.obid"); }

        return makeDom(vo);
    }
    
    /**
     * obid에 해당하는 VO 정보를 DB에서 조회하여, VO를 생성한 뒤
     * 생성된 VO를 기반으로 Dom Class를 생성한다.
     *
     * @param obid
     * @param bWithOutData outData에 locker, owner 등의 추가 정보를 같이 조회한다.
     * @return
     */
    public static <T extends ObjectRoot> T toDom(String obid, boolean bWithOutData){
        if (NullUtil.isNone(obid)) { throw new FoundationException("api.object.warn.obid"); }
        
        ObjectRootVO vo = null;
        if (bWithOutData) {
            vo = CommonServiceUtil.getObjectWithOutData(obid);
        } else {
            vo = CommonServiceUtil.getObject(obid);
        }
        
        if (NullUtil.isNull(vo)) { throw new FoundationException("api.object.warn.obid"); }

        return makeDom(vo);
    }

    @SuppressWarnings("unchecked")
    private static <T extends ObjectRoot> T makeDom(ObjectRootVO vo){
        Class<? extends ObjectRootVO> clazz = vo.getClass();
        String packageName = clazz.getPackage().getName();
        int index = packageName.lastIndexOf('.');
        String domName = packageName.substring(0, index) + ".dom." + clazz.getSimpleName().replace("VO", "");

        Class<T> domClass = null;
        try {
            domClass = (Class<T>)Class.forName(domName);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

        Constructor<T> ctor = null;
        try {
            ctor = domClass.getDeclaredConstructor(clazz);
        } catch (NoSuchMethodException | SecurityException e1) {
            e1.printStackTrace();
        }

        T obj = null;
        try {
            obj = ctor.newInstance(vo);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
            e1.printStackTrace();
        }

        return obj;
    }
    
    public static Object copy(Object orig) {
        Object obj = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bos);
            out.writeObject(orig);
            out.flush();
            out.close();
            ObjectInputStream in = new ObjectInputStream(
                new ByteArrayInputStream(bos.toByteArray()));
            obj = in.readObject();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        return obj;
    }
    public static void copyPagingEntity(PagingEntity from, PagingEntity to){
        to.setCustomRowSize(from.getCustomRowSize());
        to.setDefaultRowSize(from.getDefaultRowSize());
        to.setOrderBy(from.getOrderBy());
        to.setPageSize(from.getPageSize());
        if(from.getTargetRow() == 0 ){
            to.setTargetRow(1);
            from.setTargetRow(1);
        }else{
            to.setTargetRow(from.getTargetRow());
        }
        to.setRowSize(from.getRowSize());
    }
}
