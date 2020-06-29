/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcOQLStackTraceInfo.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2016. 7. 19.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.api.oql.utility;
/**
 * <pre>
 * Class : OmcOQLStackTraceInfo
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class OmcOQLStackTraceInfo {
    private static final int CLIENT_CODE_STACK_INDEX;
    private static final int CURRENT_PROCESS_OFFSET = 2;
    private static final int INVOKE_PROCESS_OFFSET = 3;
    static {
        int i = 0;
        for (StackTraceElement ste: Thread.currentThread().getStackTrace())
        {
            i++;
            if (ste.getClassName().equals(OmcOQLStackTraceInfo.class.getName()))
            {
                break;
            }
        }
        CLIENT_CODE_STACK_INDEX = i;
    }
    public static String getCurrentMethodInfo()
    {
        return((new StringBuffer()).append("[").append(getCurrentClassName()).append("].").append(getCurrentMethodName()).append("()...").append(getCurrentFileName()).toString());
    }
    public static String getInvokingMethodInfo()
    {
        return((new StringBuffer()).append("[").append(getInvokingClassName()).append("].").append(getInvokingMethodName()).append("()...").append(getInvokingFileName()).toString());
    }
    public static String getInvokingMethodInfo(String message)
    {
        return((new StringBuffer()).append("[").append(getInvokingClassName()).append("].").append(getInvokingMethodName()).append("(){").append(message).append("}...").append(getInvokingFileName()).toString());
    }
    public static String getCurrentMethodName()
    {
        return getCurrentMethodName(CURRENT_PROCESS_OFFSET);
    }

    private static String getCurrentMethodName(int offset)
    {
        return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getMethodName();
    }

    public static String getCurrentClassName()
    {
        return getCurrentClassName(CURRENT_PROCESS_OFFSET);
    }

    private static String getCurrentClassName(int offset)
    {
    return Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getClassName();
    }

    public static String getCurrentFileName()
    {
        return getCurrentFileName(CURRENT_PROCESS_OFFSET);
    }

    private static String getCurrentFileName(int offset)
    {
        String filename = Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getFileName();
        int lineNumber = Thread.currentThread().getStackTrace()[CLIENT_CODE_STACK_INDEX + offset].getLineNumber();

        return filename + ":" + lineNumber;
    }

    public static String getInvokingMethodName()
    {
        return getInvokingMethodName(INVOKE_PROCESS_OFFSET); 
    }

    private static String getInvokingMethodName(int offset)
    {
        return getCurrentMethodName(offset + 1);
    }

    public static String getInvokingClassName()
    {
        return getInvokingClassName(INVOKE_PROCESS_OFFSET); 
    }

    private static String getInvokingClassName(int offset)
    {
        return getCurrentClassName(offset + 1);
    }

    public static String getInvokingFileName()
    {
        return getInvokingFileName(INVOKE_PROCESS_OFFSET); 
    }

    private static String getInvokingFileName(int offset)
    {
        return getCurrentFileName(offset + 1);
    }

    public static String getCurrentMethodNameFqn()
    {
        return getCurrentMethodNameFqn(CURRENT_PROCESS_OFFSET);
    }

    private static String getCurrentMethodNameFqn(int offset)
    {
        String currentClassName = getCurrentClassName(offset + 1);
        String currentMethodName = getCurrentMethodName(offset + 1);

        return currentClassName + "." + currentMethodName ;
    }

    public static String getCurrentFileNameFqn()
    {
        String CurrentMethodNameFqn = getCurrentMethodNameFqn(1);
        String currentFileName = getCurrentFileName(1);

        return CurrentMethodNameFqn + "(" + currentFileName + ")";
    }

    public static String getInvokingMethodNameFqn()
    {
        return getInvokingMethodNameFqn(INVOKE_PROCESS_OFFSET);
    }

    private static String getInvokingMethodNameFqn(int offset)
    {
        String invokingClassName = getInvokingClassName(offset + 1);
        String invokingMethodName = getInvokingMethodName(offset + 1);

        return invokingClassName + "." + invokingMethodName;
    }

    public static String getInvokingFileNameFqn()
    {
        String invokingMethodNameFqn = getInvokingMethodNameFqn(2);
        String invokingFileName = getInvokingFileName(2);

        return invokingMethodNameFqn + "(" + invokingFileName + ")";
    }
}
