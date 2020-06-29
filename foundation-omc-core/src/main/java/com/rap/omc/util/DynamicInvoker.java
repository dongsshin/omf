/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rap.omc.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.rap.omc.framework.exception.ApplicationException;



/**
 *
 * @author inhan.choi
 */
public class DynamicInvoker {
    /**
     * Return Class Array of Parameter Object Array
     * @param paramObjs
     * @return
     * @
     */
	@SuppressWarnings("rawtypes")
	private static Class[] getClasses(Object[] paramObjs)  {
		Class[] paramObjClass = new Class[paramObjs.length];
		for (int i=0; i<paramObjClass.length; i++) {
			Object paramObj = paramObjs[i];
			paramObjClass[i] = paramObj.getClass();
		}
		return paramObjClass;
	}
    /**
     * Get Constructor Class <br>
     * Maybe, not used other source. used only this source
     * @param mainObj       Current Object (ex) this
     * @param pkgName       Package Name
     * @param className     Class Name
     * @param paramObjs     Parameter Array of Constructor Method
     * @return
     * @
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected static Constructor getConstructor(Object mainObj, String pkgName, String className, Object[] paramObjs)  {
        Constructor result = null;
        try {
            ClassLoader loader = mainObj.getClass().getClassLoader();
            Class clazz = loader.loadClass( pkgName + "." + className);
            result =  clazz.getDeclaredConstructor(getClasses( paramObjs) );
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
        return result;
	}
	@SuppressWarnings("rawtypes")
	protected static Class getConstructorDefault(Object mainObj, String pkgName, String className)  {
        Class clazz = null;
        try {
            ClassLoader loader = mainObj.getClass().getClassLoader();
            clazz = loader.loadClass( pkgName + "." + className);
        } catch (Exception e) {
            throw new ApplicationException ( e ) ;
        }
        return clazz;
	}
    /**
     * Return Constructed Object
     * @param mainObj       Current Object (ex) this
     * @param pkgName       Package Name
     * @param className     Class Name
     * @param paramObjs     Parameter Array of Constructor Method
     * @return
     * @
     */
	public static Object getNewInstance(Object mainObj, String pkgName, String className, Object[] paramObjs)  {
		Object result = null;
        try {
            result = getConstructor(mainObj, pkgName, className, paramObjs).newInstance( paramObjs );
        } catch (Exception e) {
            throw new ApplicationException ( e );
        }
        return result;
	}
	public static Object getNewInstance(Object mainObj, String fullName, Object[] paramObjs)  {
        Object result = null;
        try {
            String pkgName = fullName.substring(0, fullName.lastIndexOf("."));
            String className = fullName.substring(fullName.lastIndexOf(".")+1, fullName.length());
            result = getConstructor(mainObj, pkgName, className, paramObjs).newInstance( paramObjs );
        } catch (Exception e) {
            throw new ApplicationException (e) ;
        }
        return result;
	}
	public static Object getNewInstanceDefault(Object mainObj, String fullName)  {
        Object result = null;
        try {
            String pkgName = fullName.substring(0, fullName.lastIndexOf("."));
            String className = fullName.substring(fullName.lastIndexOf(".")+1, fullName.length());
            result = getConstructorDefault(mainObj, pkgName, className).newInstance();
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
        return result;
	}
    /**
     * Find Method
     * @param clazz             Class of Object (ex) obj.getClass()
     * @param methodName        Method Name
     * @param paramClasses      Parameter Array for Method
     * @return
     * @
     */
	@SuppressWarnings("rawtypes")
	public static Method findMethod (Class clazz, String methodName, Class[] paramClasses)  {
		Method method = null;
        if (clazz == null) {
            throw new ApplicationException (" Cannot found method : " + methodName);
        }
		Method[] methods = clazz.getDeclaredMethods();
		for (Method tMethod : methods) {
			if (methodName.equals(tMethod.getName()) && tMethod.getParameterTypes().length == paramClasses.length) {
				method = tMethod;
				break;
			}
		}
		if (method == null) {
			method = findMethod (clazz.getSuperclass(), methodName, paramClasses);
		}
		return method;
	}
    /**
     * Exeucte Method of Object
     * @param obj           Object
     * @param methodName    Method Name
     * @param paramObjs     Arguments of Method
     * @return              Return Value
     * @
     */
	public static Object invokeMethod (Object obj, String methodName, Object[] paramObjs )  {
        Object result = null;
        try {
            Method method = findMethod (obj.getClass(), methodName, getClasses(paramObjs) );
            result =  method.invoke(obj, paramObjs);
        } catch (Exception e) {
            throw new ApplicationException (e);
        }
        return result;
	}
    /**
     * Execute Setter Method of VO
     * @param obj               VO Object
     * @param variableName      Variable Name
     * @param value             Value
     * @
     */
	public static void invokeSetterMethod (Object obj, String variableName, Object[] values )  {
		invokeMethod(obj, "set"
															+ variableName.substring(0, 1).toUpperCase()
															+ variableName.substring(1,variableName.length())
														, values
						);
	}
    /**     *
     * Excute Getter Method of VO
     * @param obj               VO Object
     * @param variableName      Variable Name
     * @param value             Value
     * @
     */
	public static Object invokeGetterMethod (Object obj, String variableName, Object[] values )  {
		return invokeMethod(obj, "get"
															+ variableName.substring(0, 1).toUpperCase()
															+ variableName.substring(1)
														, values
						);
	}
}
