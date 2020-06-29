package com.rap.omc.api.oql.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;

import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.util.OmcSystemConstants;
public class OmcComUtility {
    private final static char DOT = '.';
    private final static char SLASH = '/';
    private final static String CLASS_SUFFIX = ".class";
    private final static String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the given '%s' package exists?";

    private static final String DATE_FORMAT_DEFAULT = OmcSystemConstants.OMC_JAVA_DATE_LOG;
    private static final int SPACE_COUNT = 10;
	public static void logWrite(String message, Integer processLevel, boolean  testMode) {
		if (testMode){
		    System.out.println((new SimpleDateFormat(DATE_FORMAT_DEFAULT)).format(new Date()) + OmcStringUtility.LPAD(" ",SPACE_COUNT+(processLevel*2)," ") + OmcOQLStackTraceInfo.getInvokingMethodInfo(message) + OmcFoundationConstant.newline);
		}
	}
    public static void logWrite(int data, Integer processLevel, boolean  testMode) {
        if (testMode){
            logWrite(((Integer)data).toString(),processLevel,testMode);
        }
    }
	public static void logWriteStart(Integer processLevel, boolean  testMode) {
		if (testMode){
		    System.out.println((new SimpleDateFormat(DATE_FORMAT_DEFAULT)).format(new Date()) + OmcStringUtility.LPAD(" ",SPACE_COUNT+(processLevel*2)," ") + OmcOQLStackTraceInfo.getInvokingMethodInfo("Started") + OmcFoundationConstant.newline);
		}
	}
	public static void logWriteEnd(Integer processLevel, boolean  testMode) {
		if (testMode){
		    System.out.println((new SimpleDateFormat(DATE_FORMAT_DEFAULT)).format(new Date()) + OmcStringUtility.LPAD(" ",SPACE_COUNT+(processLevel*2)," ") + OmcOQLStackTraceInfo.getInvokingMethodInfo("Ended") + OmcFoundationConstant.newline);
		}
	}
	public static void error(String message) {
			throw new FoundationException(message);
	}
    public final static List<Class<?>> find(final String scannedPackage) {
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        final String scannedPath = scannedPackage.replace(DOT, SLASH);
        final Enumeration<URL> resources;
        try {
            resources = classLoader.getResources(scannedPath);
        } catch (IOException e) {
            throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage), e);
        }
        final List<Class<?>> classes = new LinkedList<Class<?>>();
        while (resources.hasMoreElements()) {
            final File file = new File(resources.nextElement().getFile());
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }	

	private final static List<Class<?>> find(final File file, final String scannedPackage) {
        final List<Class<?>> classes = new LinkedList<Class<?>>();
        if (file.isDirectory()) {
            for (File nestedFile : file.listFiles()) {
                classes.addAll(find(nestedFile, scannedPackage));
            }
        } else if (file.getName().endsWith(CLASS_SUFFIX) && !file.getName().contains("$")) {

            final int beginIndex = 0;
            final int endIndex = file.getName().length() - CLASS_SUFFIX.length();
            final String className = file.getName().substring(beginIndex, endIndex);
            try {
                final String resource = scannedPackage + DOT + className;
                classes.add(Class.forName(resource));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }


	
	
}
