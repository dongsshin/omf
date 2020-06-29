package com.rap.omc.util.core;


/*      */ import java.io.BufferedInputStream;
/*      */ import java.io.BufferedOutputStream;
/*      */ import java.io.BufferedReader;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.FileFilter;
/*      */ import java.io.FileInputStream;
/*      */ import java.io.FileNotFoundException;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.FileReader;
/*      */ import java.io.FileWriter;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.InputStreamReader;
/*      */ import java.io.OutputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.UnsupportedEncodingException;
/*      */ import java.io.Writer;
/*      */ import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
/*      */ import java.util.GregorianCalendar;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.regex.Matcher;
/*      */ import java.util.regex.Pattern;
/*      */ import java.util.zip.CRC32;
/*      */ import java.util.zip.Checksum;

/*      */ import org.slf4j.Logger;
/*      */ import org.slf4j.LoggerFactory;
/*      */ import org.springframework.util.StringUtils;

import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;
import com.rap.omc.util.core.fileFilter.DirectoryFilter;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
/*      */ public class FileUtil
/*      */ {

    /*   88 */ private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);


	private static final long BUFFER_SIZE = 1024L;

    /*   95 */ private static final String NEW_LINE = System.getProperty("line.separator");

    /*      */
    /*  100 */ public static final Pattern DOS_SEPERATOR = Pattern.compile("\\\\");

    /*      */
    /*  105 */ public static final Pattern LAST_SEPERATOR = Pattern.compile("/$");

    /*      */
    /*  110 */ static final char FILE_SEPARATOR = File.separatorChar;

    /*      */ static final char ACCESS_READ = 82;

    /*      */ static final char ACCESS_SYS = 83;

    /*      */ static final char ACCESS_HIDE = 72;

    /*      */ static final int MAX_STR_LEN = 1024;

    /*      */
    /*      */ public static boolean acceptFileName(String filename, String allowPattern, String denyPattern)
    /*      */ {
        /*  136 */ if ((!("*".equals(allowPattern))) && (!("*".equals(denyPattern))))
            throw new IllegalArgumentException("One of allowPattern/denyPattern must be set as '*'.");
        /*      */
        /*  138 */ if (("*".equals(allowPattern)) && ("*".equals(denyPattern)))
            throw new IllegalArgumentException("Both allowPattern and denyPattern cannot be set as '*'.");
        /*      */
        /*  140 */ if ("*".equals(allowPattern)) {
            /*  141 */ Pattern pattern = PatternUtil.compileWildcardPattern(denyPattern.replaceAll(" ", ""));
            /*  142 */ Matcher matcher = pattern.matcher(filename);
            /*  143 */ if (matcher.matches()) return false;
            /*  144 */ } else if ("*".equals(denyPattern)) {
            /*  145 */ Pattern pattern = PatternUtil.compileWildcardPattern(allowPattern.replaceAll(" ", ""));
            /*  146 */ Matcher matcher = pattern.matcher(filename);
            /*  147 */ if (!(matcher.matches())) return false;
            /*      */ }
        /*  149 */ return true;
        /*      */ }

    /*      */
    /*      */ public static long getFileSize(File[] files)
    /*      */ {
        /*  159 */ long size = 0L;
        /*      */
        /*  161 */ for (int i = 0; i < files.length; ++i) {
            /*  162 */ File file = files[i];
            /*  163 */ if (file.exists()) {
                /*  164 */ size += file.length();
                /*      */ }
            /*      */ }
        /*  167 */ return size;
        /*      */ }

    /*      */
    /*      */ public static String getFileNameWithoutExtension(String filename)
    /*      */ {
        /*  177 */ int index = filename.lastIndexOf(".");
        /*  178 */ String name = (index == -1) ? filename : filename.substring(0, index);
        /*  179 */ return name;
        /*      */ }

    /*      */
    /*      */ public static String getFilePathWithoutExtension(String filePath, String extension)
    /*      */ {
        /*  190 */ int index = filePath.indexOf("." + extension);
        /*  191 */ String name = (index == -1) ? filePath : filePath.substring(0, index);
        /*  192 */ return name;
        /*      */ }

    /*      */
    /*      */ public static String getFilePath(String fullpath)
    /*      */ {
        /*  205 */ String fullFilePath = fullpath;
        /*  206 */ if (fullFilePath == null) return null;
        /*  207 */ fullFilePath = DOS_SEPERATOR.matcher(fullFilePath).replaceAll("/");
        /*  208 */ int pos = fullFilePath.lastIndexOf("/");
        /*  209 */ if (pos > -1) {
            /*  210 */ return fullFilePath.substring(0, pos + 1);
            /*      */ }
        /*  212 */ return "./";
        /*      */ }

    /*      */
    /*      */ public static String getCompleteLeadingSeperator(String fullpath)
    /*      */ {
        /*  224 */ if (fullpath == null) return null;
        /*  225 */ String completeFullPath = DOS_SEPERATOR.matcher(fullpath).replaceAll("/");
        /*      */
        /*  227 */ if ((!(completeFullPath.endsWith(File.separator))) && (!(completeFullPath.endsWith("/")))) {
            /*  228 */ return completeFullPath + "/";
            /*      */ }
        /*  230 */ return completeFullPath;
        /*      */ }

    /*      */
    /*      */ public static String getRemoveLeadingSeperator(String fileName)
    /*      */ {
        /*  242 */ String removeFileName = fileName;
        /*  243 */ if (removeFileName == null) return null;
        /*  244 */ removeFileName = DOS_SEPERATOR.matcher(removeFileName).replaceAll("/");
        /*  245 */ removeFileName = LAST_SEPERATOR.matcher(removeFileName).replaceAll("");
        /*  246 */ return removeFileName;
        /*      */ }

    /*      */
    /*      */ public static String converterSizeFormat(long filesize)
    /*      */ {
        /*  265 */ return converterSizeFormat(filesize, "#,###.00 ");
        /*      */ }

    /*      */
    /*      */ public static String converterSizeFormat(long filesize, String format)
    /*      */ {
        /*  278 */ double size = filesize;
        /*  279 */ String tail = "byte";
        /*      */
        /*  281 */ if ((1048576L > filesize) && (filesize >= 1024L)) {
            /*  282 */ size /= 1024.0D;
            /*  283 */ tail = "KB";
            /*  284 */ } else if ((1073741824L > filesize) && (filesize >= 1048576L)) {
            /*  285 */ size /= 1048576.0D;
            /*  286 */ tail = "MB";
            /*  287 */ } else if ((1099511627776L > filesize) && (filesize >= 1073741824L)) {
            /*  288 */ size /= 1073741824.0D;
            /*  289 */ tail = "GB";
            /*  290 */ } else if (filesize >= 1099511627776L) {
            /*  291 */ size /= 1099511627776.0D;
            /*  292 */ tail = "TB";
            /*      */ }
        /*      */
        /*  295 */ return new DecimalFormat(format).format(size) + tail;
        /*      */ }

    /*      */
    /*      */ public static long getCRC32Value(File file)/*      */ throws IOException
    /*      */ {
        /*  306 */ Checksum crc = new CRC32();
        /*  307 */ BufferedInputStream bins = null;
        /*      */ try {
            /*  309 */ bins = new BufferedInputStream(new FileInputStream(file));
            /*  310 */ byte[] buffer = new byte[2048];
            /*  311 */ int length = 0;
            /*      */
            /*  313 */ while ((length = bins.read(buffer)) >= 0)
                /*  314 */ crc.update(buffer, 0, length);
            /*      */ }
        /*      */ finally
        /*      */ {
            /*  318 */ if (bins != null) {
                /*      */ try {
                    /*  320 */ bins.close();
                    /*      */ } catch (IOException ex) {
                    /*  322 */ LOGGER.error(ex.getMessage());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*  326 */ return crc.getValue();
        /*      */ }

    /*      */
    /*      */ public static boolean touch(File file)
    /*      */ {
        /*  336 */ FileOutputStream fos = null;
        /*      */ try {
            /*  338 */ fos = new FileOutputStream(file);
            /*      */ }
        /*      */ catch (Exception e) {
            /*  341 */ return false;
            /*      */ } finally {
            /*  343 */ if (fos != null) {
                /*      */ try {
                    /*  345 */ fos.flush();
                    /*      */ } catch (Exception e) {
                    /*  347 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ try {
                    /*  350 */ fos.close();
                    /*      */ } catch (Exception e) {
                    /*  352 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*  356 */ return true;
        /*      */ }

    @SuppressWarnings("resource")
	static boolean compareBinary(File a, File b) throws IOException {
        if(a.length() != b.length()){
            return false;
        }

        final int BLOCK_SIZE = 128;
        InputStream aStream = new FileInputStream(a);
        InputStream bStream = new FileInputStream(b);
        byte[] aBuffer = new byte[BLOCK_SIZE];
        byte[] bBuffer = new byte[BLOCK_SIZE];
        while (true) {
            int aByteCount = aStream.read(aBuffer, 0, BLOCK_SIZE);
            bStream.read(bBuffer, 0, BLOCK_SIZE);
            if (aByteCount < 0) {
                return true;
            }
            if (!Arrays.equals(aBuffer, bBuffer)) {
                return false;
            }
        }
    }
    

    /*      */
    /*      */ public static boolean isEmpty(File file)
    /*      */ {
        /*  403 */ if (file.isDirectory()) {
            /*  404 */ String[] files = file.list();
            /*  405 */ return ((files == null) || (files.length == 0));
            /*      */ }
        /*  407 */ return (file.length() == 0L);
        /*      */ }

    /*      */
    /*      */ public static boolean isEmptyDirectory(File file)
    /*      */ {
        /*  418 */ if (!(file.isDirectory())) return false;
        /*  419 */ String[] files = file.list();
        /*  420 */ return ((files == null) || (files.length == 0));
        /*      */ }

    /*      */
    /*      */ public static String readFile(String fileName)
    /*      */ {
        /*  430 */ String retStr = "";
        /*  431 */ File file = new File(fileName);
        /*  432 */ FileReader freader = null;
        /*  433 */ BufferedReader breader = null;
        /*      */ try {
            /*  435 */ freader = new FileReader(file);
            /*  436 */ breader = new BufferedReader(freader);
            /*      */
            /*  438 */ StringBuilder buff = new StringBuilder();
            /*  439 */ String line = breader.readLine();
            /*  440 */ while (line != null) {
                /*  441 */ buff.append(line + NEW_LINE);
                /*  442 */ line = breader.readLine();
                /*      */ }
            /*  444 */ retStr = buff.toString();
            /*      */ } catch (Exception e1) {
            /*  446 */ LOGGER.error(e1.getMessage(), e1);
            /*      */ } finally {
            /*  448 */ if (breader != null) {
                /*      */ try {
                    /*  450 */ breader.close();
                    /*      */ } catch (IOException e) {
                    /*  452 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*  455 */ if (freader != null) {
                /*      */ try {
                    /*  457 */ freader.close();
                    /*      */ } catch (IOException e) {
                    /*  459 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*  463 */ return retStr;
        /*      */ }

    /*      */
    /*      */ public static String readFile(String fileName, String charsetName)
    /*      */ {
        /*  474 */ String retStr = "";
        /*  475 */ File file = new File(fileName);
        /*  476 */ FileInputStream fis = null;
        /*  477 */ InputStreamReader isr = null;
        /*  478 */ BufferedReader breader = null;
        /*      */ try
        /*      */ {
            /*  481 */ fis = new FileInputStream(file);
            /*  482 */ isr = new InputStreamReader(fis, charsetName);
            /*  483 */ breader = new BufferedReader(isr);
            /*      */
            /*  485 */ StringBuilder buff = new StringBuilder();
            /*  486 */ String line = breader.readLine();
            /*  487 */ while (line != null) {
                /*  488 */ buff.append(line + NEW_LINE);
                /*  489 */ line = breader.readLine();
                /*      */ }
            /*  491 */ retStr = buff.toString();
            /*      */ } catch (Exception e1) {
            /*  493 */ LOGGER.error(e1.getMessage(), e1);
            /*      */ } finally {
            /*  495 */ if (breader != null) {
                /*      */ try {
                    /*  497 */ breader.close();
                    /*      */ } catch (IOException e) {
                    /*  499 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*  502 */ if (isr != null) {
                /*      */ try {
                    /*  504 */ isr.close();
                    /*      */ } catch (IOException e) {
                    /*  506 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*  509 */ if (fis != null) {
                /*      */ try {
                    /*  511 */ fis.close();
                    /*      */ } catch (IOException e) {
                    /*  513 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*  517 */ return retStr;
        /*      */ }

    /*      */
    /*      */ public static void saveFile(String fileName, String content)
    /*      */ {
        /*  527 */ File file = new File(fileName);
        /*  528 */ FileWriter fwriter = null;
        /*  529 */ BufferedWriter bwriter = null;
        /*      */ try {
            /*  531 */ fwriter = new FileWriter(file);
            /*  532 */ bwriter = new BufferedWriter(fwriter);
            /*  533 */ bwriter.write(content);
            /*      */ } catch (Exception e1) {
            /*  535 */ LOGGER.error(e1.getMessage(), e1);
            /*      */ } finally {
            /*  537 */ if (bwriter != null) {
                /*      */ try {
                    /*  539 */ bwriter.close();
                    /*      */ } catch (IOException e) {
                    /*  541 */ LOGGER.error(e.getMessage());
                    /*      */ }
                /*      */ }
            /*  544 */ if (fwriter != null)/*      */ try {
                /*  546 */ fwriter.close();
                /*      */ } catch (IOException e) {
                /*  548 */ LOGGER.error(e.getMessage());
                /*      */ }
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static List<File> listFilesWithFileFilter(File folder, FileFilter filter, boolean recursive)
    /*      */ {
        /*  563 */ List result = new ArrayList();
        /*      */
        /*  565 */ if (!(folder.isDirectory())) return result;
        /*  566 */ File[] files = folder.listFiles(filter);
        /*  567 */ result.addAll(Arrays.asList(files));
        /*  568 */ if (recursive) {
            /*  569 */ File[] folders = folder.listFiles(new DirectoryFilter());
            /*  570 */ for (int idx = 0; (folders != null) && (idx < folders.length); ++idx) {
                /*  571 */ result.addAll(listFilesWithFileFilter(folders[idx], filter, recursive));
                /*      */ }
            /*      */ }
        /*  574 */ return result;
        /*      */ }

    /*      */
    /*      */ public static File[] listFilesWithFileFilter(File folder, FileFilter filter)
    /*      */ {
        /*  585 */ List result = listFilesWithFileFilter(folder, filter, true);
        /*  586 */ Object[] objects = result.toArray();
        /*      */
        /*  588 */ File[] files = new File[objects.length];
        /*  589 */ System.arraycopy(objects, 0, files, 0, objects.length);
        /*      */
        /*  591 */ return files;
        /*      */ }

    /*      */
    /*      */ public static HashMap<String, File> getFilesWithExtension(String rootPath, String extension)
    /*      */ {
        /*  602 */ File rootDirectory = getRootDirectory(rootPath);
        /*  603 */ HashMap fileMap = new HashMap();
        /*      */
        /*  605 */ setFileMapByExtension(fileMap, rootDirectory, "", extension);
        /*  606 */ return fileMap;
        /*      */ }

    /*      */
    /*      */ private static File getRootDirectory(String rootPath)
    /*      */ {
        /*  616 */ File rootDirectory = new File(rootPath);
        /*      */
        /*  618 */ if ((rootDirectory == null) || (!(rootDirectory.exists())))
            throw new IllegalArgumentException("The file(" + rootPath + ") does not exist.");
        /*      */
        /*  620 */ if (!(rootDirectory.isDirectory()))
            throw new IllegalArgumentException("The file(" + rootPath + ") isn't a directory.");
        /*      */
        /*  622 */ return rootDirectory;
        /*      */ }

    /*      */
    /*      */ private static void setFileMapByExtension(HashMap<String, File> fileMap, File parent, String keyHeader,
            String extension)
    /*      */ {
        /*  634 */ if (!(parent.canRead())) throw new IllegalStateException(
                "You don't have permission to read the directory(" + parent.getAbsolutePath() + ").");
        /*      */
        /*  636 */ File[] list = parent.listFiles();
        /*  637 */ String newHeader = "";
        /*  638 */ int inx = 0;
        for (int count = list.length; inx < count; ++inx) {
            /*  639 */ File subFile = list[inx];
            /*  640 */ if (subFile.isDirectory()) {
                /*  641 */ newHeader = keyHeader + "/" + subFile.getName();
                /*  642 */ setFileMapByExtension(fileMap, subFile, newHeader, extension);
                /*      */ }
            /*  644 */ else if (subFile.getName().toLowerCase().endsWith(extension)) {
                /*  645 */ String fName = subFile.getName();
                /*  646 */ fName = fName.substring(0, fName.toLowerCase().lastIndexOf(extension));
                /*  647 */ newHeader = keyHeader + "/" + fName;
                /*  648 */ fileMap.put(newHeader, subFile);
                /*      */ }
            /*      */ }
        /*      */ }

    
    /*      */
    /*      */ public static String getRelativePath(File parent, File child)
    /*      */ {
        Path pathAbsolute = Paths.get(child.getAbsolutePath());
        Path pathBase = Paths.get(parent.getAbsolutePath());
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return pathRelative.toString();
    }

    /*      */
    /*      */ public static boolean isChild(File parent, File child)
    /*      */ {
        /*  689 */ File parentFile = parent;
        /*  690 */ File childFile = child;
        /*      */ try {
            /*  692 */ if ((parentFile == null) || (childFile == null)) return false;
            /*  693 */ parentFile = parentFile.getCanonicalFile();
            /*  694 */ childFile = childFile.getCanonicalFile();
            /*  695 */ if (parentFile.equals(childFile)) return false;
            /*  696 */ File file = childFile.getParentFile();
            /*  697 */ while (file != null) {
                /*  698 */ if (file.equals(parentFile)) return true;
                /*  699 */ file = file.getParentFile();
                /*      */ }
            /*      */ } catch (Exception e) {
            /*  702 */ LOGGER.error(e.getMessage(), e);
            /*      */ }
        /*  704 */ return false;
        /*      */ }

    /*      */
    /*      */ public static File replaceExtension(File file, String newExtension)/*      */ throws IOException
    /*      */ {
        /*  716 */ if (!(file.exists())) throw new FileNotFoundException("file not found : " + file.getAbsolutePath());
        /*  717 */ return new File(getFileNameWithoutExtension(file.getAbsolutePath()) + "." + newExtension);
        /*      */ }

    /*      */
    /*      */ public static int compareSize(File source, File target)
    /*      */ {
        /*  729 */ int result = 0;
        /*      */
        /*  731 */ if ((source.exists()) && (target.exists()) && (source.isFile()) && (target.isFile()))
        /*      */ {
            /*  733 */ long size1 = source.length();
            /*  734 */ long size2 = target.length();
            /*      */
            /*  736 */ if (size1 > size2)/*  737 */ result = 1;
            /*  738 */ else if (size1 < size2)/*  739 */ result = -1;
            /*      */ }
        /*      */ else {
            /*  742 */ throw new IllegalArgumentException("Input parameters(" + source.getAbsolutePath() + " or "
                    + target.getAbsolutePath() + ") are not files or does not exist.");
            /*      */ }
        /*      */
        /*  746 */ return result;
        /*      */ }

    /*      */
    /*      */ public static int compareLastModifiedDate(File source, File target)
    /*      */ {
        /*  758 */ int result = 0;
        /*      */
        /*  760 */ if ((source.exists()) && (target.exists()) && (source.isFile()) && (target.isFile()))
        /*      */ {
            /*  762 */ long date1 = source.lastModified();
            /*  763 */ long date2 = target.lastModified();
            /*      */
            /*  765 */ if (date1 > date2)/*  766 */ result = 1;
            /*  767 */ else if (date1 < date2)/*  768 */ result = -1;
            /*      */ }
        /*      */ else {
            /*  771 */ throw new IllegalArgumentException("Input parameters(" + source.getAbsolutePath() + " or "
                    + target.getAbsolutePath() + ") are not files or does not exist.");
            /*      */ }
        /*      */
        /*  775 */ return result;
        /*      */ }

    /*      */
    /*      */ public static List<String> getFileListByLastModifiedDate(File directory, String modifiedDate)
    /*      */ {
        /*  786 */ List list = new ArrayList();
        /*      */ try {
            /*  788 */ SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            /*  789 */ list = getFileListByLastModifiedDate(directory, dateFormat.parse(modifiedDate));
            /*      */ } catch (ParseException e) {
            /*  791 */ throw new IllegalArgumentException(
                    "Format of date(" + modifiedDate + ") is not valid. Valid date format is yyyyMMdd.");
            /*      */ }
        /*  793 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getFileListByLastModifiedDate(File directory, Date modifiedDate)
    /*      */ {
        /*  804 */ List list = new ArrayList();
        /*      */
        /*  806 */ if ((directory.exists()) && (directory.isDirectory())) {
            /*  807 */ File[] files = directory.listFiles();
            /*  808 */ list = getSubFilesByModifiedDate(files, modifiedDate);
            /*      */ } else {
            /*  810 */ throw new IllegalArgumentException(
                    "The input parameter(" + directory.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /*  814 */ return list;
        /*      */ }

    /*      */
    /*      */ private static List<String> getSubFilesByModifiedDate(File[] fileArray, Date modifiedDate)
    /*      */ {
        /*  826 */ List list = new ArrayList();
        /*      */
        /*  828 */ for (int inx = 0; inx < fileArray.length; ++inx) {
            /*  829 */ if (fileArray[inx].isDirectory()) {
                /*  830 */ File[] files = fileArray[inx].listFiles();
                /*  831 */ list.addAll(getSubFilesByModifiedDate(files, modifiedDate));
                /*      */ } else {
                /*  833 */ long date = fileArray[inx].lastModified();
                /*      */
                /*  835 */ GregorianCalendar updtDateCal = new GregorianCalendar();
                /*  836 */ updtDateCal.setTime(modifiedDate);
                /*  837 */ GregorianCalendar lastUpdtDateCal = new GregorianCalendar();
                /*  838 */ lastUpdtDateCal.setTimeInMillis(date);
                /*      */
                /*  841 */ if ((updtDateCal.get(1) == lastUpdtDateCal.get(1))
                        && (updtDateCal.get(2) == lastUpdtDateCal.get(2))
                        && (updtDateCal.get(5) == lastUpdtDateCal.get(5))) {
                    /*  842 */ list.add(fileArray[inx].getAbsolutePath());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */
        /*  847 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getFileListByLastModifiedDate(File directory, String modifiedDateFrom,
            String modifiedDateTo)
    /*      */ {
        /*  859 */ List list = new ArrayList();
        /*      */ try {
            /*  861 */ SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            /*  862 */ list = getFileListByLastModifiedDate(directory, dateFormat.parse(modifiedDateFrom),
                    dateFormat.parse(modifiedDateTo));
            /*      */ } catch (ParseException e) {
            /*  864 */ throw new IllegalArgumentException("Format of fromDate(" + modifiedDateFrom
                    + ") or updated toDate(" + modifiedDateTo + ") is not valid. Valid date format is yyyyMMdd.");
            /*      */ }
        /*      */
        /*  867 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getFileListByLastModifiedDate(File directory, Date modifiedDateFrom,
            Date modifiedDateTo)
    /*      */ {
        /*  879 */ List list = new ArrayList();
        /*      */
        /*  881 */ if ((directory.exists()) && (directory.isDirectory())) {
            /*  882 */ File[] files = directory.listFiles();
            /*  883 */ list = getSubFilesByModifiedDate(files, modifiedDateFrom, modifiedDateTo);
            /*      */ } else {
            /*  885 */ throw new IllegalArgumentException(
                    "The input parameter(" + directory.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /*  889 */ return list;
        /*      */ }

    /*      */
    /*      */ private static List<String> getSubFilesByModifiedDate(File[] fileArray, Date modifiedDateFrom,
            Date modifiedDateTo)
    /*      */ {
        /*  902 */ List list = new ArrayList();
        /*      */
        /*  904 */ for (int inx = 0; inx < fileArray.length; ++inx) {
            /*  905 */ if (fileArray[inx].isDirectory()) {
                /*  906 */ File[] files = fileArray[inx].listFiles();
                /*  907 */ list.addAll(getSubFilesByModifiedDate(files, modifiedDateFrom, modifiedDateTo));
                /*      */ } else {
                /*  909 */ long date = fileArray[inx].lastModified();
                /*  910 */ GregorianCalendar lastUpdtDateCal = new GregorianCalendar();
                /*  911 */ lastUpdtDateCal.setTimeInMillis(date);
                /*      */
                /*  914 */ GregorianCalendar updtFromCal = new GregorianCalendar();
                /*  915 */ updtFromCal.setTime(modifiedDateFrom);
                /*  916 */ updtFromCal.set(updtFromCal.get(1), updtFromCal.get(2), updtFromCal.get(5), 0, 0, 0);
                /*      */
                /*  919 */ GregorianCalendar updtToCal = new GregorianCalendar();
                /*  920 */ updtToCal.setTime(modifiedDateTo);
                /*  921 */ updtToCal.set(updtToCal.get(1), updtToCal.get(2), updtToCal.get(5), 0, 0, 0);
                /*  922 */ updtToCal.add(5, 1);
                /*      */
                /*  924 */ if ((lastUpdtDateCal.equals(updtFromCal))
                        || ((lastUpdtDateCal.after(updtFromCal)) && (lastUpdtDateCal.before(updtToCal)))) {
                    /*  925 */ list.add(fileArray[inx].getAbsolutePath());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */
        /*  930 */ return list;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByName(File directory, String fileName)
    /*      */ {
        /*  942 */ boolean result = false;
        /*      */
        /*  944 */ if ((directory.exists()) && (directory.isDirectory()))
        /*      */ {
            /*  946 */ File[] fileArray = directory.listFiles();
            /*      */
            /*  948 */ for (int inx = 0; inx < fileArray.length; ++inx) {
                /*  949 */ if (fileArray[inx].isDirectory()) {
                    /*  950 */ result = isExistingFileByName(fileArray[inx], fileName);
                    /*      */ }
                /*  952 */ else if (fileArray[inx].getName().equals(fileName)) {
                    /*  953 */ result = true;
                    /*      */ }
                /*      */
                /*  956 */ if (result)/*      */ break;
                /*      */ }
            /*      */ }
        /*      */ else {
            /*  961 */ throw new IllegalArgumentException(
                    "The input parameter(" + directory.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /*  964 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByExtension(File directory, String extension)
    /*      */ {
        /*  976 */ boolean result = false;
        /*      */
        /*  978 */ if ((directory.exists()) && (directory.isDirectory()))
        /*      */ {
            /*  980 */ File[] fileArray = directory.listFiles();
            /*      */
            /*  982 */ for (int inx = 0; inx < fileArray.length; ++inx) {
                /*  983 */ if (fileArray[inx].isDirectory()) {
                    /*  984 */ result = isExistingFileByExtension(fileArray[inx], extension);
                    /*      */ }
                /*  986 */ else if (extension.equals(getExtension(fileArray[inx].getName()))) {
                    /*  987 */ result = true;
                    /*      */ }
                /*      */
                /*  990 */ if (result)/*      */ break;
                /*      */ }
            /*      */ }
        /*      */ else {
            /*  995 */ throw new IllegalArgumentException(
                    "The input parameter(" + directory.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /*  998 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByModifiedDate(File directory, String modifiedDateFrom,
            String modifiedDateTo)
    /*      */ {
        /* 1010 */ boolean result = false;
        /*      */ try
        /*      */ {
            /* 1013 */ SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            /* 1014 */ result = isExistingFileByModifiedDate(directory, dateFormat.parse(modifiedDateFrom),
                    dateFormat.parse(modifiedDateTo));
            /*      */ } catch (ParseException e) {
            /* 1016 */ throw new IllegalArgumentException("Format of fromDate(" + modifiedDateFrom
                    + ") or updated toDate(" + modifiedDateTo + ") is not valid. Valid date format is yyyyMMdd.");
            /*      */ }
        /*      */
        /* 1019 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByModifiedDate(File directory, Date modifiedDateFrom,
            Date modifiedDateTo)
    /*      */ {
        /* 1033 */ boolean result = false;
        /*      */
        /* 1035 */ if ((directory.exists()) && (directory.isDirectory())) {
            /* 1036 */ File[] fileArray = directory.listFiles();
            /* 1037 */ for (int inx = 0; inx < fileArray.length; ++inx) {
                /* 1038 */ if (fileArray[inx].isDirectory()) {
                    /* 1039 */ result = isExistingFileByModifiedDate(fileArray[inx], modifiedDateFrom, modifiedDateTo);
                    /*      */ } else {
                    /* 1041 */ long date = fileArray[inx].lastModified();
                    /* 1042 */ GregorianCalendar lastUpdtDateCal = new GregorianCalendar();
                    /* 1043 */ lastUpdtDateCal.setTimeInMillis(date);
                    /*      */
                    /* 1046 */ GregorianCalendar updtFromCal = new GregorianCalendar();
                    /* 1047 */ updtFromCal.setTime(modifiedDateFrom);
                    /* 1048 */ updtFromCal.set(updtFromCal.get(1), updtFromCal.get(2), updtFromCal.get(5), 0, 0, 0);
                    /*      */
                    /* 1051 */ GregorianCalendar updtToCal = new GregorianCalendar();
                    /* 1052 */ updtToCal.setTime(modifiedDateTo);
                    /* 1053 */ updtToCal.set(updtToCal.get(1), updtToCal.get(2), updtToCal.get(5), 0, 0, 0);
                    /* 1054 */ updtToCal.add(5, 1);
                    /*      */
                    /* 1056 */ if ((lastUpdtDateCal.equals(updtFromCal))
                            || ((lastUpdtDateCal.after(updtFromCal)) && (lastUpdtDateCal.before(updtToCal)))) {
                        /* 1057 */ result = true;
                        /*      */ }
                    /*      */ }
                /* 1060 */ if (result)/*      */ break;
                /*      */ }
            /*      */ }
        /*      */ else {
            /* 1065 */ throw new IllegalArgumentException(
                    "The input parameter(" + directory.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /* 1068 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileBySize(File directory, long sizeFrom, long sizeTo)
    /*      */ {
        /* 1081 */ boolean result = false;
        /*      */
        /* 1083 */ if ((directory.exists()) && (directory.isDirectory())) {
            /* 1084 */ File[] fileArray = directory.listFiles();
            /* 1085 */ for (int inx = 0; inx < fileArray.length; ++inx) {
                /* 1086 */ if (fileArray[inx].isDirectory()) {
                    /* 1087 */ result = isExistingFileBySize(fileArray[inx], sizeFrom, sizeTo);
                    /*      */ } else {
                    /* 1089 */ long size = fileArray[inx].length();
                    /* 1090 */ if ((size >= sizeFrom * 1024L) && (size <= sizeTo * 1024L)) {
                        /* 1091 */ result = true;
                        /*      */ }
                    /*      */ }
                /* 1094 */ if (result)/*      */ break;
                /*      */ }
            /*      */ }
        /*      */ else {
            /* 1099 */ throw new IllegalArgumentException(
                    "The input parameter(" + directory.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /* 1102 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String getLastModifiedDate(String filePath)
    /*      */ {
        /* 1112 */ File file = new File(filePath);
        /* 1113 */ String result = getLastModifiedDate(file);
        /* 1114 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String getLastModifiedDate(File file)
    /*      */ {
        /* 1124 */ return getLastModifiedDate(file, "yyyyMMdd");
        /*      */ }

    /*      */
    /*      */ public static String getLastModifiedDate(File file, String format)
    /*      */ {
        /* 1135 */ String result = "";
        /*      */
        /* 1137 */ if (file.exists()) {
            /* 1138 */ long date = file.lastModified();
            /* 1139 */ SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
            /* 1140 */ result = dateFormat.format(new Date(date));
            /*      */ } else {
            /* 1142 */ throw new IllegalArgumentException(
                    "The input parameter(" + file.getAbsolutePath() + ") does not exist.");
            /*      */ }
        /*      */
        /* 1146 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingDirectory(String targetDirectoryPath)
    /*      */ {
        /* 1158 */ if ((targetDirectoryPath == null) || (targetDirectoryPath.equals(""))) return false;
        /*      */
        /* 1160 */ boolean result = false;
        /* 1161 */ File dir = null;
        /*      */
        /* 1163 */ dir = new File(targetDirectoryPath);
        /* 1164 */ if ((dir.exists()) && (dir.isDirectory())) {
            /* 1165 */ result = true;
            /*      */ }
        /*      */
        /* 1168 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingDirectoryByModifiedDate(String targetDirectoryPath, String fromDate,
            String toDate)
    /*      */ {
        /* 1182 */ if ((StringUtils.isEmpty(targetDirectoryPath)) || (StringUtils.isEmpty(fromDate))
                || (StringUtils.isEmpty(toDate)))
            return false;
        /*      */
        /* 1184 */ boolean result = false;
        /* 1185 */ String lastModifyedDate = "";
        /* 1186 */ File dir = null;
        /*      */
        /* 1188 */ dir = new File(targetDirectoryPath);
        /*      */
        /* 1190 */ lastModifyedDate = getLastModifiedDate(dir);
        /* 1191 */ if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate))
                && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
            /* 1192 */ result = true;
            /*      */ }
        /*      */
        /* 1195 */ return result;
        /*      */ }

    /*      */
    /*      */ public static List<String> getDirectoryList(String baseDirectoryPath, String fromDate, String toDate)
    /*      */ {
        /* 1209 */ if ((NullUtil.isNone(baseDirectoryPath)) || (NullUtil.isNone(fromDate)) || (NullUtil.isNone(toDate)))
            return new ArrayList();
        /*      */
        /* 1211 */ File dir = null;
        /* 1212 */ File childFile = null;
        /*      */
        /* 1214 */ String subDirPath = "";
        /* 1215 */ List childResult = null;
        /* 1216 */ List result = new ArrayList();
        /*      */
        /* 1218 */ dir = new File(baseDirectoryPath);
        /*      */
        /* 1220 */ String[] subDirList = dir.list();
        /*      */
        /* 1222 */ if (subDirList != null) {
            /* 1223 */ for (int i = 0; i < subDirList.length; ++i)
            /*      */ {
                /* 1225 */ subDirPath = baseDirectoryPath + "/" + subDirList[i];
                /* 1226 */ childFile = new File(subDirPath);
                /* 1227 */ if (childFile.isDirectory()) {
                    /* 1228 */ String lastModifyedDate = getLastModifiedDate(childFile);
                    /* 1229 */ if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate))
                            && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
                        /* 1230 */ result.add(baseDirectoryPath + "/" + subDirList[i]);
                        /*      */ }
                    /* 1232 */ childResult = getDirectoryList(baseDirectoryPath + "/" + subDirList[i], fromDate,
                            toDate);
                    /*      */
                    /* 1234 */ for (int j = 0; j < childResult.size(); ++j) {
                        /* 1235 */ result.add((String)childResult.get(j));
                        /*      */ }
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */
        /* 1241 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isAuthorityRead(String filePath)
    /*      */ {
        /* 1253 */ if ((filePath == null) || (filePath.equals(""))) return false;
        /*      */
        /* 1255 */ File file = null;
        /* 1256 */ boolean result = false;
        /*      */
        /* 1258 */ file = new File(filePath);
        /* 1259 */ if (file.exists()) {
            /* 1260 */ result = file.canRead();
            /*      */ }
        /*      */
        /* 1263 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isAuthorityWrite(String filePath)
    /*      */ {
        /* 1275 */ if ((filePath == null) || (filePath.equals(""))) return false;
        /*      */
        /* 1277 */ File file = new File(filePath);
        /* 1278 */ boolean result = false;
        /*      */
        /* 1280 */ if (file.exists()) {
            /* 1281 */ result = file.canWrite();
            /*      */ }
        /*      */
        /* 1284 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String getName(String filePath)
    /*      */ {
        /* 1296 */ if ((filePath == null) || (filePath.equals(""))) return "";
        /*      */
        /* 1298 */ File file = new File(filePath);
        /* 1299 */ String result = "";
        /*      */
        /* 1301 */ if (file.exists()) {
            /* 1302 */ result = file.getName();
            /*      */ }
        /*      */
        /* 1305 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByName(String directory, String file)
    /*      */ {
        /* 1318 */ boolean result = false;
        /*      */
        /* 1321 */ String drctry = directory.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1322 */ File srcDrctry = new File(drctry);
        /*      */
        /* 1325 */ if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
        /*      */ {
            /* 1328 */ File[] fileArray = srcDrctry.listFiles();
            /* 1329 */ List list = getSubFilesByName(fileArray, file);
            /* 1330 */ if ((list != null) && (list.size() > 0)) {
                /* 1331 */ result = true;
                /*      */ }
            /*      */ }
        /*      */
        /* 1335 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByExtension(String directory, String extension)
    /*      */ {
        /* 1348 */ boolean result = false;
        /*      */
        /* 1351 */ String drctry = directory.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1352 */ File srcDrctry = new File(drctry);
        /*      */
        /* 1355 */ if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
        /*      */ {
            /* 1358 */ File[] fileArray = srcDrctry.listFiles();
            /* 1359 */ List list = getSubFilesByExtension(fileArray, extension);
            /* 1360 */ if ((list != null) && (list.size() > 0)) {
                /* 1361 */ result = true;
                /*      */ }
            /*      */ }
        /*      */
        /* 1365 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileByModifiedDate(String directory, String modifiedDateFrom,
            String modifiedDateTo)
    /*      */ {
        /* 1379 */ boolean result = false;
        /*      */
        /* 1382 */ String drctry = directory.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1383 */ File srcDrctry = new File(drctry);
        /*      */
        /* 1386 */ if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
        /*      */ {
            /* 1389 */ File[] fileArray = srcDrctry.listFiles();
            /* 1390 */ List list = getSubFilesByModifiedDate(fileArray, modifiedDateFrom, modifiedDateTo);
            /* 1391 */ if ((list != null) && (list.size() > 0)) {
                /* 1392 */ result = true;
                /*      */ }
            /*      */ }
        /*      */
        /* 1396 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFileBySize(String directory, long sizeFrom, long sizeTo)
    /*      */ {
        /* 1410 */ boolean result = false;
        /*      */
        /* 1413 */ String drctry = directory.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1414 */ File srcDrctry = new File(drctry);
        /*      */
        /* 1417 */ if ((srcDrctry.exists()) && (srcDrctry.isDirectory()))
        /*      */ {
            /* 1420 */ File[] fileArray = srcDrctry.listFiles();
            /* 1421 */ List list = getSubFilesBySize(fileArray, sizeFrom, sizeTo);
            /* 1422 */ if ((list != null) && (list.size() > 0)) {
                /* 1423 */ result = true;
                /*      */ }
            /*      */ }
        /*      */
        /* 1427 */ return result;
        /*      */ }

    /*      */
    /*      */ public static List<String> getSubFilesByAll(File[] fileArray)
    /*      */ {
        /* 1438 */ List list = new ArrayList();
        /*      */
        /* 1440 */ for (int i = 0; i < fileArray.length; ++i)
        /*      */ {
            /* 1442 */ if (fileArray[i].isDirectory()) {
                /* 1443 */ File[] tmpArray = fileArray[i].listFiles();
                /* 1444 */ list.addAll(getSubFilesByAll(tmpArray));
                /*      */ }
            /*      */ else {
                /* 1447 */ list.add(fileArray[i].getAbsolutePath());
                /*      */ }
            /*      */ }
        /*      */
        /* 1451 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getSubFilesByName(File[] fileArray, String file)
    /*      */ {
        /* 1463 */ ArrayList list = new ArrayList();
        /*      */
        /* 1465 */ for (int i = 0; i < fileArray.length; ++i)
        /*      */ {
            /* 1467 */ if (fileArray[i].isDirectory()) {
                /* 1468 */ File[] tmpArray = fileArray[i].listFiles();
                /* 1469 */ list.addAll(getSubFilesByName(tmpArray, file));
                /*      */ }
            /* 1472 */ else if (fileArray[i].getName().equals(file)) {
                /* 1473 */ list.add(fileArray[i].getAbsolutePath());
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 1478 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getSubFilesByExtension(File[] fileArray, String extensio)
    /*      */ {
        /* 1490 */ ArrayList list = new ArrayList();
        /*      */
        /* 1492 */ for (int i = 0; i < fileArray.length; ++i)
        /*      */ {
            /* 1494 */ if (fileArray[i].isDirectory()) {
                /* 1495 */ File[] tmpArray = fileArray[i].listFiles();
                /* 1496 */ list.addAll(getSubFilesByExtension(tmpArray, extensio));
                /*      */ }
            /* 1499 */ else if (fileArray[i].getName().indexOf(extensio) != -1) {
                /* 1500 */ list.add(fileArray[i].getAbsolutePath());
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 1505 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getSubFilesByModifiedDate(File[] fileArray, String modifiedDateFrom,
            String modifiedDateTo)
    /*      */ {
        /* 1518 */ ArrayList list = new ArrayList();
        /*      */
        /* 1520 */ for (int i = 0; i < fileArray.length; ++i)
        /*      */ {
            /* 1522 */ if (fileArray[i].isDirectory()) {
                /* 1523 */ File[] tmpArray = fileArray[i].listFiles();
                /* 1524 */ list.addAll(getSubFilesByModifiedDate(tmpArray, modifiedDateFrom, modifiedDateTo));
                /*      */ }
            /*      */ else
            /*      */ {
                /* 1528 */ long date = fileArray[i].lastModified();
                /* 1529 */ SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
                /* 1530 */ String lastUpdtDate = dateFormat.format(new Date(date));
                /*      */
                /* 1532 */ if ((Integer.parseInt(lastUpdtDate) >= Integer.parseInt(modifiedDateFrom))
                        && (Integer.parseInt(lastUpdtDate) <= Integer.parseInt(modifiedDateTo))) {
                    /* 1533 */ list.add(fileArray[i].getAbsolutePath());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */
        /* 1538 */ return list;
        /*      */ }

    /*      */
    /*      */ public static List<String> getSubFilesBySize(File[] fileArray, long sizeFrom, long sizeTo)
    /*      */ {
        /* 1551 */ ArrayList list = new ArrayList();
        /*      */
        /* 1553 */ for (int i = 0; i < fileArray.length; ++i)
        /*      */ {
            /* 1555 */ if (fileArray[i].isDirectory()) {
                /* 1556 */ File[] tmpArray = fileArray[i].listFiles();
                /* 1557 */ list.addAll(getSubFilesBySize(tmpArray, sizeFrom, sizeTo));
                /*      */ }
            /*      */ else
            /*      */ {
                /* 1561 */ long size = fileArray[i].length();
                /*      */
                /* 1563 */ if ((size >= sizeFrom * 1024L) && (size <= sizeTo * 1024L)) {
                    /* 1564 */ list.add(fileArray[i].getAbsolutePath());
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */
        /* 1569 */ return list;
        /*      */ }

    /*      */
    /*      */ public static String createDirectory(String directoryPath)
    /*      */ {
        /* 1579 */ File file = new File(directoryPath);
        /* 1580 */ String result = "";
        /* 1581 */ if (!(file.exists())) {
            /* 1582 */ file.mkdirs();
            /* 1583 */ result = file.getAbsolutePath();
            /*      */ }
        /* 1585 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String createNewFile(String filePath)
/*      */   {
/* 1598 */     if ((filePath == null) || (filePath.equals(""))) return "";
/*      */ 
/* 1600 */     File file = new File(filePath);
/* 1601 */     String result = "";
/*      */     try {
/* 1603 */       if (file.exists()) {
/* 1604 */         result = filePath;
/* 1605 */         return result;
/*      */       }
/* 1607 */       new File(file.getParent()).mkdirs();
/* 1608 */       if (file.createNewFile())
/* 1609 */         result = file.getAbsolutePath();
/*      */     }
/*      */     catch (IOException e)
/*      */     {
/* 1613 */       throw new IllegalStateException(e);
/*      */     }
/*      */ 
/* 1616 */      return result;
/*      */   }

    /*      */
    /*      */ private static String deletePath(String filePath)
    /*      */ {
        /* 1626 */ File file = new File(filePath);
        /* 1627 */ String result = "";
        /*      */
        /* 1629 */ if (file.exists()) {
            /* 1630 */ result = file.getAbsolutePath();
            /* 1631 */ if (!(file.delete())) {
                /* 1632 */ result = "";
                /*      */ }
            /*      */ }
        /* 1635 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String deleteDirectory(String directoryDeletePath)
    /*      */ {
        /* 1647 */ if ((directoryDeletePath == null) || (directoryDeletePath.equals(""))) return "";
        /* 1648 */ String result = "";
        /* 1649 */ File dir = new File(directoryDeletePath);
        /* 1650 */ if (dir.isDirectory()) {
            /* 1651 */ String[] files = dir.list();
            /*      */
            /* 1653 */ for (int i = 0; i < files.length; ++i) {
                /* 1654 */ File file = new File(directoryDeletePath + FILE_SEPARATOR + files[i]);
                /* 1655 */ if (file.isFile())
                /*      */ {
                    /* 1657 */ file.delete();
                    /*      */ }
                /*      */ else {
                    /* 1660 */ deleteDirectory(directoryDeletePath + FILE_SEPARATOR + files[i]);
                    /*      */ }
                /*      */ }
            /*      */
            /* 1664 */ result = deletePath(directoryDeletePath);
            /*      */ } else {
            /* 1666 */ result = "";
            /*      */ }
        /*      */
        /* 1669 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String deleteFile(String fileDeletePath)
    /*      */ {
        /* 1681 */ if ((fileDeletePath == null) || (fileDeletePath.equals(""))) return "";
        /* 1682 */ String result = "";
        /* 1683 */ File file = new File(fileDeletePath);
        /* 1684 */ if (file.isFile())/* 1685 */ result = deletePath(fileDeletePath);
        /*      */ else {
            /* 1687 */ result = "";
            /*      */ }
        /*      */
        /* 1690 */ return result;
        /*      */ }

    /*      */
    /*      */ public static List<List<String>> getFileListSplitByChars(String parFile, String parChar, int parField)
            /*      */ throws IOException
    /*      */ {
        /* 1704 */ List parResult = new ArrayList();
        /*      */
        /* 1707 */ String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1708 */ File file = new File(parFile1);
        /* 1709 */ BufferedReader br = null;
        /*      */ try
        /*      */ {
            /* 1712 */ if ((!(file.exists())) || (!(file.isFile()))) {
                /*      */ return parResult;
                /*      */ }
            /* 1715 */ br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            /* 1716 */ StringBuilder strBuff = new StringBuilder();
            /* 1717 */ String line = br.readLine();
            /* 1718 */ while (line != null) {
                /* 1719 */ if (line.length() < 1024) {
                    /* 1720 */ strBuff.append(line);
                    /*      */ }
                /* 1722 */ line = br.readLine();
                /*      */ }
            /*      */
            /* 1726 */ String[] strArr = StringUtil.split(strBuff.toString(), parChar);
            /*      */
            /* 1729 */ int filedCnt = 1;
            /* 1730 */ ArrayList arr = null;
            /* 1731 */ for (int i = 0; i < strArr.length; ++i)
            /*      */ {
                /* 1733 */ if (parField != 1) {
                    /* 1734 */ if (filedCnt % parField == 1) {
                        /* 1735 */ arr = new ArrayList();
                        /* 1736 */ if (strArr[i] != null) {
                            /* 1737 */ arr.add(strArr[i]);
                            /*      */ }
                        /* 1739 */ if (i == strArr.length - 1)/* 1740 */ parResult.add(arr);
                        /*      */ }
                    /* 1742 */ else if (filedCnt % parField == 0) {
                        /* 1743 */ if (strArr[i] != null) {
                            /* 1744 */ arr.add(strArr[i]);
                            /*      */ }
                        /* 1746 */ parResult.add(arr);
                        /*      */ } else {
                        /* 1748 */ if (strArr[i] != null) {
                            /* 1749 */ arr.add(strArr[i]);
                            /*      */ }
                        /* 1751 */ if (i == strArr.length - 1)/* 1752 */ parResult.add(arr);
                        /*      */ }
                    /*      */ }
                /*      */ else {
                    /* 1756 */ arr = new ArrayList();
                    /* 1757 */ if (strArr[i] != null) {
                        /* 1758 */ arr.add(strArr[i]);
                        /*      */ }
                    /* 1760 */ parResult.add(arr);
                    /*      */ }
                /* 1762 */ ++filedCnt;
                /*      */ }
            /*      */ }
        /*      */ catch (IOException ex) {
            /* 1766 */ throw new IllegalStateException(ex);
            /*      */ } finally {
            /* 1768 */ if (br != null) {
                /* 1769 */ br.close();
                /*      */ }
            /*      */ }
        /*      */
        /* 1773 */ return parResult;
        /*      */ }

    /*      */
    /*      */ public static List<List<String>> getFileListSplitBySize(String parFile, int[] parLen, int parLine)
            /*      */ throws IOException
    /*      */ {
        /* 1788 */ List parResult = new ArrayList();
        /*      */
        /* 1791 */ String parFile1 = parFile.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1792 */ File file = new File(parFile1);
        /* 1793 */ BufferedReader br = null;
        /*      */ try
        /*      */ {
            /* 1796 */ if ((!(file.exists())) || (!(file.isFile()))) {
                /*      */ return parResult;
                /*      */ }
            /* 1799 */ br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            /* 1800 */ String[] strArr = new String[parLine];
            /* 1801 */ String line = br.readLine();
            /* 1802 */ int readCnt = 0;
            /* 1803 */ while ((line != null) && (readCnt < parLine)) {
                /* 1804 */ if (line.length() <= 1024) {
                    /* 1805 */ strArr[(readCnt++)] = line;
                    /*      */ }
                /* 1807 */ line = br.readLine();
                /*      */ }
            /*      */
            /* 1811 */ for (int i = 0; i < strArr.length; ++i) {
                /* 1812 */ String text = strArr[i];
                /* 1813 */ ArrayList arr = new ArrayList();
                /* 1814 */ int idx = 0;
                /* 1815 */ boolean result = false;
                /* 1816 */ for (int j = 0; j < parLen.length; ++j) {
                    /* 1817 */ if (!(result)) {
                        /* 1818 */ String split = "";
                        /* 1819 */ if (text.length() < idx + parLen[j]) {
                            /* 1820 */ split = text.substring(idx, text.length());
                            /* 1821 */ result = true;
                            /*      */ } else {
                            /* 1823 */ split = text.substring(idx, idx + parLen[j]);
                            /*      */ }
                        /* 1825 */ arr.add(split);
                        /* 1826 */ idx += parLen[j];
                        /*      */ }
                    /*      */ }
                /* 1829 */ label282: parResult.add(arr);
                /*      */ }
            /*      */ }
        /*      */ catch (IOException e) {
            /* 1833 */ throw new IllegalStateException(e);
            /*      */ } finally {
            /* 1835 */ if (br != null) {
                /* 1836 */ br.close();
                /*      */ }
            /*      */ }
        /*      */
        /* 1840 */ return parResult;
        /*      */ }

    /*      */
    /*      */ public static boolean compareSize(String compareFile1, String compareFile2)
    /*      */ {
        /* 1853 */ boolean result = false;
        /*      */
        /* 1856 */ String cmprFile11 = compareFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1857 */ String cmprFile22 = compareFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1858 */ File file1 = new File(cmprFile11);
        /* 1859 */ File file2 = new File(cmprFile22);
        /*      */
        /* 1862 */ if ((file1.exists()) && (file2.exists()) && (file1.isFile()) && (file2.isFile()))
        /*      */ {
            /* 1865 */ long size1 = file1.length();
            /*      */
            /* 1868 */ long size2 = file2.length();
            /*      */
            /* 1871 */ if (size1 == size2) {
                /* 1872 */ result = true;
                /*      */ }
            /*      */ }
        /*      */
        /* 1876 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean compareLastModifiedDate(String compareFile1, String compareFile2)
    /*      */ {
        /* 1889 */ boolean result = false;
        /*      */
        /* 1892 */ String cmprFile11 = compareFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1893 */ String cmprFile22 = compareFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1894 */ File file1 = new File(cmprFile11);
        /* 1895 */ File file2 = new File(cmprFile22);
        /*      */
        /* 1898 */ if ((file1.exists()) && (file2.exists()) && (file1.isFile()) && (file2.isFile()))
        /*      */ {
            /* 1901 */ long date1 = file1.lastModified();
            /* 1902 */ SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            /* 1903 */ String lastUpdtDate1 = dateFormat1.format(new Date(date1));
            /*      */
            /* 1906 */ long date2 = file2.lastModified();
            /* 1907 */ SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            /* 1908 */ String lastUpdtDate2 = dateFormat2.format(new Date(date2));
            /*      */
            /* 1911 */ if (lastUpdtDate1.equals(lastUpdtDate2)) {
                /* 1912 */ result = true;
                /*      */ }
            /*      */ }
        /*      */
        /* 1916 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean compareContent(String compareFile1, String compareFile2)
            /*      */ throws IOException
    /*      */ {
        /* 1929 */ String cmprFile11 = compareFile1.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1930 */ String cmprFile22 = compareFile2.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 1931 */ File file1 = new File(cmprFile11);
        /* 1932 */ File file2 = new File(cmprFile22);
        /*      */
        /* 1934 */ return compareContent(file1, file2);
        /*      */ }

    /*      */
    /*      */ private static boolean compareContent(File compareFile1, File compareFile2)/*      */ throws IOException
    /*      */ {
        /* 1947 */ boolean result = false;
        /*      */
        /* 1949 */ BufferedReader br1 = null;
        /* 1950 */ BufferedReader br2 = null;
        /*      */ try
        /*      */ {
            /* 1953 */ if ((!(compareFile1.exists())) || (!(compareFile2.exists())) || (!(compareFile1.isFile()))
                    || (!(compareFile2.isFile())))
                /*      */ return false;
            /* 1955 */ ArrayList cmprText1 = new ArrayList();
            /* 1956 */ ArrayList cmprText2 = new ArrayList();
            /*      */
            /* 1959 */ br1 = new BufferedReader(new InputStreamReader(new FileInputStream(compareFile1)));
            /* 1960 */ String line1 = br1.readLine();
            /* 1961 */ while (line1 != null) {
                /* 1962 */ if (line1.length() < 1024) {
                    /* 1963 */ cmprText1.add(line1);
                    /*      */ }
                /* 1965 */ line1 = br1.readLine();
                /*      */ }
            /*      */
            /* 1969 */ br2 = new BufferedReader(new InputStreamReader(new FileInputStream(compareFile2)));
            /* 1970 */ String line2 = br2.readLine();
            /* 1971 */ while (line2 != null) {
                /* 1972 */ if (line2.length() <= 1024) {
                    /* 1973 */ cmprText2.add(line2);
                    /*      */ }
                /* 1975 */ line2 = br2.readLine();
                /*      */ }
            /*      */
            /* 1979 */ boolean isWrong = false;
            /* 1980 */ for (int i = 0; i < cmprText1.size(); ++i) {
                /* 1981 */ if (!(isWrong)) {
                    /* 1982 */ String text1 = (String)cmprText1.get(i);
                    /* 1983 */ String text2 = (String)cmprText2.get(i);
                    /*      */
                    /* 1985 */ if (!(text1.equals(text2))) {
                        /* 1986 */ isWrong = true;
                        /*      */ }
                    /*      */ }
                /*      */ }
            /*      */
            /* 1991 */ if (isWrong) return true;
            /*      */ }
        /*      */ catch (IOException ex)
        /*      */ {
            /* 1996 */ throw new IllegalStateException(ex);
            /*      */ }
        /*      */ finally {
            /* 1999 */ if (br1 != null) {
                /* 2000 */ br1.close();
                /*      */ }
            /* 2002 */ if (br2 != null) {
                /* 2003 */ br2.close();
                /*      */ }
            /*      */ }
        /*      */
        /* 2007 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFile(String source, String target)
    /*      */ {
        /* 2020 */ boolean result = false;
        /*      */
        /* 2023 */ String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 2024 */ File srcFile = new File(src);
        /*      */
        /* 2027 */ String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /*      */ try
        /*      */ {
            /* 2031 */ if (srcFile.exists())
            /*      */ {
                /* 2034 */ tar = createNewFile(tar);
                /* 2035 */ File tarFile = new File(tar);
                /*      */
                /* 2037 */ result = copyFile(srcFile, tarFile);
                /*      */ }
            /*      */ } catch (IOException ex) {
            /* 2040 */ throw new IllegalStateException(ex);
            /*      */ }
        /*      */
        /* 2043 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFiles(String[] source, String target)
    /*      */ {
        /* 2056 */ boolean result = true;
        /*      */
        /* 2059 */ for (int i = 0; i < source.length; ++i) {
            /* 2060 */ String src = source[i].replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
            /* 2061 */ File chkFile = new File(src);
            /* 2062 */ if (!(chkFile.exists())) {
                /* 2063 */ return result;
                /*      */ }
            /*      */ }
        /*      */
        /* 2067 */ String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /*      */
        /* 2070 */ for (int j = 0; j < source.length; ++j)
        /*      */ {
            /* 2072 */ if (!(result)) {
                /*      */ continue;
                /*      */ }
            /* 2075 */ File chkFile = new File(source[j]);
            /* 2076 */ String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
            /*      */ try
            /*      */ {
                /* 2080 */ tarTemp = createNewFile(tarTemp);
                /* 2081 */ File tarFile = new File(tarTemp);
                /*      */
                /* 2084 */ result = copyFile(chkFile, tarFile);
                /*      */ }
            /*      */ catch (IOException ex) {
                /* 2087 */ throw new IllegalStateException(ex);
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 2094 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFileByExtension(String source, String extension, String target)
    /*      */ {
        /* 2108 */ boolean result = true;
        /*      */
        /* 2111 */ String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 2112 */ File srcFile = new File(src);
        /*      */
        /* 2115 */ if ((srcFile.exists()) && (srcFile.isDirectory()))
        /*      */ {
            /* 2117 */ String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
            /*      */
            /* 2120 */ File[] fileArray = srcFile.listFiles();
            /* 2121 */ List list = getSubFilesByExtension(fileArray, extension);
            /*      */
            /* 2124 */ for (int i = 0; i < list.size(); ++i) {
                /* 2125 */ if (!(result))/*      */ continue;
                /* 2127 */ String abspath = (String)list.get(i);
                /*      */
                /* 2130 */ File chkFile = new File(abspath);
                /* 2131 */ String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
                /*      */ try
                /*      */ {
                    /* 2135 */ tarTemp = createNewFile(tarTemp);
                    /* 2136 */ File tarFile = new File(tarTemp);
                    /*      */
                    /* 2139 */ result = copyFile(chkFile, tarFile);
                    /*      */ }
                /*      */ catch (IOException ex) {
                    /* 2142 */ throw new IllegalStateException(ex);
                    /*      */ }
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 2148 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFilesByModifiedDate(String source, String modifiedDateFrom,
            String modifiedDateTo, String target)
    /*      */ {
        /* 2163 */ boolean result = true;
        /*      */
        /* 2166 */ String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 2167 */ File srcFile = new File(src);
        /*      */
        /* 2170 */ if ((srcFile.exists()) && (srcFile.isDirectory()))
        /*      */ {
            /* 2172 */ String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
            /*      */
            /* 2175 */ File[] fileArray = srcFile.listFiles();
            /* 2176 */ List list = getSubFilesByModifiedDate(fileArray, modifiedDateFrom, modifiedDateTo);
            /*      */
            /* 2179 */ for (int i = 0; i < list.size(); ++i)
            /*      */ {
                /* 2181 */ if (!(result)) {
                    /*      */ continue;
                    /*      */ }
                /* 2184 */ String abspath = (String)list.get(i);
                /*      */
                /* 2187 */ File chkFile = new File(abspath);
                /* 2188 */ String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
                /*      */ try
                /*      */ {
                    /* 2192 */ tarTemp = createNewFile(tarTemp);
                    /* 2193 */ File tarFile = new File(tarTemp);
                    /*      */
                    /* 2196 */ result = copyFile(chkFile, tarFile);
                    /*      */ }
                /*      */ catch (IOException ex) {
                    /* 2199 */ throw new IllegalStateException(ex);
                    /*      */ }
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 2205 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFilesBySize(String source, long sizeFrom, long sizeTo, String target)
    /*      */ {
        /* 2220 */ boolean result = true;
        /*      */
        /* 2223 */ String src = source.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /* 2224 */ File srcFile = new File(src);
        /*      */
        /* 2227 */ if ((srcFile.exists()) && (srcFile.isDirectory()))
        /*      */ {
            /* 2229 */ String tar = target.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
            /*      */
            /* 2232 */ File[] fileArray = srcFile.listFiles();
            /* 2233 */ List list = getSubFilesBySize(fileArray, sizeFrom, sizeTo);
            /*      */
            /* 2235 */ for (int i = 0; i < list.size(); ++i)
            /*      */ {
                /* 2237 */ if (!(result))/*      */ continue;
                /* 2239 */ String abspath = (String)list.get(i);
                /*      */
                /* 2242 */ File chkFile = new File(abspath);
                /*      */
                /* 2244 */ String tarTemp = tar + FILE_SEPARATOR + chkFile.getName();
                /*      */ try
                /*      */ {
                    /* 2248 */ tarTemp = createNewFile(tarTemp);
                    /* 2249 */ File tarFile = new File(tarTemp);
                    /*      */
                    /* 2251 */ result = copyFile(chkFile, tarFile);
                    /* 2252 */ label199: if (result)/*      */ break label199;
                    /*      */ }
                /*      */ catch (IOException ex)
                /*      */ {
                    /* 2257 */ throw new IllegalStateException(ex);
                    /*      */ }
                /*      */ }
            /*      */
            /*      */ }
        /*      */
        /* 2263 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFile(File source, File target)/*      */ throws IOException
    /*      */ {
        /* 2277 */ boolean result = false;
        /* 2278 */ FileInputStream fis = null;
        /* 2279 */ FileOutputStream fos = null;
        /*      */ try
        /*      */ {
            /* 2282 */ fis = new FileInputStream(source);
            /*      */
            /* 2285 */ File targetFile = target;
            /* 2286 */ if (targetFile.isDirectory()) {
                /* 2287 */ targetFile = new File(targetFile.getAbsolutePath() + "/" + source.getName());
                /*      */ }
            /* 2289 */ fos = new FileOutputStream(targetFile);
            /* 2290 */ byte[] buffer = new byte[1024];
            /* 2291 */ if ((fis != null) && (fos != null)) {
                /* 2292 */ int i = fis.read(buffer);
                /* 2293 */ while (i != -1) {
                    /* 2294 */ fos.write(buffer, 0, i);
                    /* 2295 */ i = fis.read(buffer);
                    /*      */ }
                /*      */ }
            /* 2298 */ result = true;
            /*      */ } catch (IOException ex) {
            /* 2300 */ throw new IllegalStateException(ex);
            /*      */ } finally {
            /* 2302 */ if (fis != null) {
                /* 2303 */ fis.close();
                /*      */ }
            /* 2305 */ if (fos != null) {
                /* 2306 */ fos.close();
                /*      */ }
            /*      */ }
        /*      */
        /* 2310 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyFile(String source, File target)/*      */ throws IOException
    /*      */ {
        /* 2322 */ boolean result = false;
        /*      */
        /* 2324 */ FileWriter fileWriter = null;
        /*      */ try {
            /* 2326 */ if (!(target.exists())) {
                /* 2327 */ target.createNewFile();
                /*      */ }
            /* 2329 */ fileWriter = new FileWriter(target);
            /* 2330 */ fileWriter.write(source);
            /* 2331 */ result = true;
            /*      */ } finally {
            /* 2333 */ if (fileWriter != null) {
                /* 2334 */ fileWriter.close();
                /*      */ }
            /*      */ }
        /*      */
        /* 2338 */ return result;
        /*      */ }

    /*      */
    /*      */ public static String getDirectoryParent(String file)
    /*      */ {
        /* 2349 */ String parentDirectoryName = "";
        /* 2350 */ String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /*      */
        /* 2352 */ File srcFile = new File(src);
        /* 2353 */ if (srcFile.exists()) {
            /* 2354 */ parentDirectoryName = srcFile.getParent();
            /*      */ }
        /*      */
        /* 2357 */ return parentDirectoryName;
        /*      */ }

    /*      */
    /*      */ public static String getFileName(String file)
    /*      */ {
        /* 2368 */ String fileName = "";
        /* 2369 */ String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /*      */
        /* 2371 */ File srcFile = new File(src);
        /* 2372 */ if (srcFile.exists()) {
            /* 2373 */ fileName = srcFile.getName();
            /*      */ }
        /*      */
        /* 2376 */ return fileName;
        /*      */ }

    /*      */
    /*      */ public static long getSize(String file)
    /*      */ {
        /* 2387 */ long size = 0L;
        /* 2388 */ String src = file.replace('\\', FILE_SEPARATOR).replace('/', FILE_SEPARATOR);
        /*      */
        /* 2390 */ File srcFile = new File(src);
        /* 2391 */ if (srcFile.exists()) {
            /* 2392 */ size = srcFile.length();
            /*      */ }
        /*      */
        /* 2395 */ return size;
        /*      */ }

    /*      */
    /*      */ public static boolean copyDirectory(String originalDirectoryPath, String targetDirectoryPath)
    /*      */ {
        /* 2408 */ if ((originalDirectoryPath == null) || (originalDirectoryPath.equals(""))
                || (targetDirectoryPath == null) || (targetDirectoryPath.equals("")))
            return false;
        /* 2409 */ boolean result = false;
        /* 2410 */ File dir = new File(originalDirectoryPath);
        /*      */
        /* 2412 */ if ((dir.exists()) && (dir.isDirectory()))
        /*      */ {
            /* 2415 */ String targetDirPath1 = createDirectory(targetDirectoryPath);
            /* 2416 */ if (targetDirPath1.equals("")) {
                /* 2417 */ result = false;
                /*      */ } else {
                /* 2419 */ File targetDir = new File(targetDirPath1);
                /* 2420 */ targetDir.mkdirs();
                /*      */
                /* 2422 */ String[] originalFileList = dir.list();
                /*      */
                /* 2424 */ if (originalFileList.length > 0) {
                    /* 2425 */ for (int i = 0; i < originalFileList.length; ++i) {
                        /* 2426 */ File subF = new File(originalDirectoryPath + FILE_SEPARATOR + originalFileList[i]);
                        /* 2427 */ if (subF.isFile())
                        /*      */ {
                            /* 2429 */ result = copyFile(originalDirectoryPath + FILE_SEPARATOR + originalFileList[i],
                                    targetDir.getAbsolutePath() + FILE_SEPARATOR + originalFileList[i]);
                            /*      */ }
                        /*      */ else
                            /* 2432 */ result = copyDirectory(originalDirectoryPath + "/" + originalFileList[i],
                                    targetDirPath1 + "/" + originalFileList[i]);
                        /*      */ }
                    /*      */ }
                /*      */ else {
                    /* 2436 */ result = true;
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */
        /* 2441 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyDirectory(String originalDirectoryPath, String targetDirectoryPath,
            String fromDate, String toDate)
    /*      */ {
        /* 2456 */ if ((originalDirectoryPath == null) || (originalDirectoryPath.equals(""))) return false;
        /* 2457 */ if ((targetDirectoryPath == null) || (targetDirectoryPath.equals(""))) return false;
        /* 2458 */ if ((fromDate == null) || (fromDate.equals(""))) return false;
        /* 2459 */ if ((toDate == null) || (toDate.equals(""))) return false;
        /* 2460 */ boolean result = false;
        /* 2461 */ File dir = null;
        /*      */
        /* 2463 */ dir = new File(originalDirectoryPath);
        /* 2464 */ boolean isInCondition = false;
        /* 2465 */ String lastModifyedDate = getLastModifiedDate(dir);
        /* 2466 */ if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate))
                && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
            /* 2467 */ isInCondition = true;
            /*      */ }
        /*      */
        /* 2471 */ if ((dir.exists()) && (dir.isDirectory()) && (isInCondition))
        /*      */ {
            /* 2474 */ String targetDirPath1 = createDirectory(targetDirectoryPath);
            /* 2475 */ if (targetDirPath1.equals("")) {
                /* 2476 */ result = false;
                /*      */ } else {
                /* 2478 */ File targetDir = new File(targetDirPath1);
                /* 2479 */ targetDir.mkdirs();
                /*      */
                /* 2481 */ String[] originalFileList = dir.list();
                /*      */
                /* 2483 */ if (originalFileList.length > 0) {
                    /* 2484 */ for (int i = 0; i < originalFileList.length; ++i) {
                        /* 2485 */ File subF = new File(originalDirectoryPath + FILE_SEPARATOR + originalFileList[i]);
                        /* 2486 */ if (subF.isFile())
                        /*      */ {
                            /* 2488 */ result = copyFile(originalDirectoryPath + FILE_SEPARATOR + originalFileList[i],
                                    targetDir.getAbsolutePath() + FILE_SEPARATOR + originalFileList[i]);
                            /*      */ }
                        /*      */ else
                            /* 2491 */ result = copyDirectory(originalDirectoryPath + "/" + originalFileList[i],
                                    targetDirPath1 + "/" + originalFileList[i]);
                        /*      */ }
                    /*      */ }
                /*      */ else {
                    /* 2495 */ result = true;
                    /*      */ }
                /*      */ }
            /*      */ }
        /*      */ else
        /*      */ {
            /* 2501 */ result = false;
            /*      */ }
        /*      */
        /* 2504 */ return result;
        /*      */ }

    /*      */
    /*      */ public static long getDirectorySize(String targetDirectoryPath)
    /*      */ {
        /* 2515 */ File file = new File(targetDirectoryPath);
        /* 2516 */ long size = 0L;
        /* 2517 */ size = getDirectorySize(file);
        /* 2518 */ return size;
        /*      */ }

    /*      */
    /*      */ public static String getExtension(String fileName)
    /*      */ {
        /* 2528 */ if (fileName == null) return null;
        /* 2529 */ return fileName.substring(fileName.lastIndexOf(".") + 1);
        /*      */ }

    /*      */
    /*      */ public static boolean isExistingFile(String filePath)
    /*      */ {
        /* 2539 */ if (filePath == null) return false;
        /* 2540 */ File file = new File(filePath);
        /* 2541 */ return file.exists();
        /*      */ }

    /*      */
    /*      */ public static boolean existValidFile(String filePath)
    /*      */ {
        /* 2551 */ if (filePath == null) return false;
        /* 2552 */ File file = new File(filePath);
        /* 2553 */ return ((file.exists()) && (file.isFile()));
        /*      */ }

    /*      */
    /*      */ public static byte[] readFile(File file)
    /*      */ {
        /* 2563 */ InputStream is = null;
        /* 2564 */ ByteArrayOutputStream baos = new ByteArrayOutputStream();
        /*      */ try {
            /* 2566 */ is = new FileInputStream(file);
            /*      */
            /* 2568 */ byte[] buff = new byte[512];
            /* 2569 */ int size = -1;
            /*      */ while (true) {
                /* 2571 */ size = is.read(buff);
                /* 2572 */ if (size == -1) {
                    /*      */ break;
                    /*      */ }
                /* 2575 */ baos.write(buff, 0, size);
                /*      */ }
            /* 2577 */ return baos.toByteArray();
            /*      */ }
        /*      */ catch (IOException e)
        /*      */ {
        	return null;
            /*      */ }
        /*      */ finally {
            /* 2583 */ if (is != null) try {
                /* 2584 */ is.close();
                /*      */ } catch (Exception ex) {
                /* 2586 */ LOGGER.error(ex.getMessage());
                /*      */ }
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void saveFile(File file, byte[] contents)
    /*      */ {
        /* 2598 */ OutputStream os = null;
        /*      */ try {
            /* 2600 */ if (!(file.getParentFile().exists())) {
                /* 2601 */ file.getParentFile().mkdirs();
                /*      */ }
            /* 2603 */ os = new FileOutputStream(file);
            /* 2604 */ os.write(contents);
            /*      */ }
        /*      */ catch (Exception e) {
            /* 2607 */ throw new IllegalStateException(e);
            /*      */ } finally {
            /* 2609 */ if (os != null) try {
                /* 2610 */ os.close();
                /*      */ } catch (Exception ex) {
                /* 2612 */ LOGGER.error(ex.getMessage());
                /*      */ }
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static boolean copyDirectory(File originalDirectory, File targetDirectory)
            /*      */ throws IOException
    /*      */ {
        /* 2628 */ boolean result = false;
        /*      */
        /* 2630 */ if ((originalDirectory.exists()) && (originalDirectory.isDirectory()))
        /*      */ {
            /* 2632 */ targetDirectory.mkdirs();
            /*      */
            /* 2634 */ String[] originalFileList = originalDirectory.list();
            /* 2635 */ if (originalFileList.length > 0)
                /* 2636 */ for (int inx = 0; inx < originalFileList.length; ++inx) {
                /* 2637 */ File subF = new File(originalDirectory.getAbsolutePath() + FILE_SEPARATOR + originalFileList[inx]);
                /* 2638 */ File out = new File(targetDirectory.getAbsolutePath() + FILE_SEPARATOR + originalFileList[inx]);
                /*      */
                /* 2640 */ if (subF.isFile()) {
                /* 2641 */ copyFile(subF, out);
                /* 2642 */ result = true;
                /*      */ } else {
                /* 2644 */ result = copyDirectory(subF, out);
                /*      */ }
                /*      */
                /* 2647 */ if (!(result)) return false;
                /*      */ }
            /*      */ else/* 2650 */ result = true;
            /*      */ }
        /*      */ else {
            /* 2653 */ throw new IllegalArgumentException(
                    FileUtil.class.getName() + "-copyDirectory(File originalDirectory, File targetDirectory) ▶ "
                            + "The input parameter(" + originalDirectory.getAbsolutePath() + ") does not exist.");
            /*      */ }
        /*      */
        /* 2656 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean copyDirectory(File originalDirectory, File targetDirectory, String fromDate,
            String toDate)/*      */ throws IOException
    /*      */ {
        /* 2671 */ boolean inCondition = false;
        /*      */
        /* 2673 */ String lastModifyedDate = getLastModifiedDate(originalDirectory);
        /* 2674 */ if ((Integer.parseInt(lastModifyedDate) >= Integer.parseInt(fromDate))
                && (Integer.parseInt(lastModifyedDate) <= Integer.parseInt(toDate))) {
            /* 2675 */ inCondition = true;
            /*      */ }
        /*      */
        /* 2678 */ boolean result = false;
        /*      */
        /* 2680 */ if ((originalDirectory.exists()) && (originalDirectory.isDirectory()))
        /*      */ {
            /* 2682 */ String[] originalFileList = originalDirectory.list();
            /* 2683 */ if (originalFileList.length > 0) {
                /* 2684 */ for (int inx = 0; inx < originalFileList.length; ++inx) {
                    /* 2685 */ File subF = new File(
                            originalDirectory.getAbsolutePath() + FILE_SEPARATOR + originalFileList[inx]);
                    /* 2686 */ File out = new File(
                            targetDirectory.getAbsolutePath() + FILE_SEPARATOR + originalFileList[inx]);
                    /*      */
                    /* 2688 */ if (subF.isFile())/* 2689 */ if (inCondition) {
                        /* 2690 */ targetDirectory.mkdirs();
                        /*      */
                        /* 2692 */ copyFile(subF, out);
                        /* 2693 */ result = true;
                        /*      */ }
                    /*      */ else {
                        /* 2696 */ result = copyDirectory(subF, out, fromDate, toDate);
                        /*      */ }
                    /*      */ }
                /*      */
                /* 2700 */ if (!(result)) return false;
                /*      */ } else {
                /* 2702 */ targetDirectory.mkdirs();
                /* 2703 */ result = true;
                /*      */ }
            /*      */ } else {
            /* 2706 */ throw new IllegalArgumentException(FileUtil.class.getName()
                    + "-copyDirectory(File originalDirectory, File targetDirectory, String fromDate, String toDate) ▶ "
                    + "The input parameter(" + originalDirectory.getAbsolutePath() +
                    /* 2707 */ ") does not exist.");
            /*      */ }
        /*      */
        /* 2710 */ return result;
        /*      */ }

    /*      */
    /*      */ public static boolean deleteDirectory(File file)
    /*      */ {
        /* 2722 */ boolean result = false;
        /*      */
        /* 2724 */ if ((file.exists()) && (file.isDirectory())) {
            /* 2725 */ String[] files = file.list();
            /*      */
            /* 2727 */ for (int inx = 0; inx < files.length; ++inx)
            /*      */ {
                /* 2729 */ File f = new File(file.getAbsolutePath() + FILE_SEPARATOR + files[inx]);
                /* 2730 */ if (f.isFile())/* 2731 */ result = f.delete();
                /*      */ else {
                    /* 2733 */ deleteDirectory(f);
                    /*      */ }
                /*      */ }
            /* 2736 */ result = file.delete();
            /*      */ } else {
            /* 2738 */ throw new IllegalArgumentException(FileUtil.class.getName() + "-deleteDirectory(File file) ▶ "
                    + "The input parameter(" + file.getAbsolutePath() + ") is not a directory or does not exist.");
            /*      */ }
        /*      */
        /* 2741 */ return result;
        /*      */ }

    /*      */
    /*      */ public static long getDirectorySize(File targetDirectory)
    /*      */ {
        /* 2752 */ if (!(targetDirectory.exists())) return 0L;
        /* 2753 */ if (targetDirectory.isFile()) return targetDirectory.length();
        /*      */
        /* 2755 */ File[] list = targetDirectory.listFiles();
        /* 2756 */ long size = 0L;
        /* 2757 */ long fileSize = 0L;
        /*      */
        /* 2759 */ for (int i = 0; i < list.length; ++i)
        /*      */ {
            /* 2761 */ if (list[i].isDirectory())/* 2762 */ fileSize = getDirectorySize(list[i]);
            /*      */ else {
                /* 2764 */ fileSize = list[i].length();
                /*      */ }
            /* 2766 */ size += fileSize;
            /*      */ }
        /* 2768 */ return size;
        /*      */ }

    /*      */
    /*      */ public static void copyByteToFile(byte[] in, File out)
    /*      */ {
        /* 2778 */ copyIoStream(new ByteArrayInputStream(in), getBufferedOutputStream(out));
        /*      */ }

    /*      */
    /*      */ public static void copyIoStream(InputStream in, OutputStream out)
    /*      */ {
        /*      */ try
        /*      */ {
            /* 2789 */ byte[] buffer = new byte[1024];
            /* 2790 */ int nrOfBytes = -1;
            /* 2791 */ while ((nrOfBytes = in.read(buffer)) != -1) {
                /* 2792 */ out.write(buffer, 0, nrOfBytes);
                /*      */ }
            /* 2794 */ out.flush();
            /*      */ } catch (IOException ie) {
            /* 2796 */ throw new IllegalStateException("IO Exception has occurred.", ie);
            /*      */ } finally {
            /* 2798 */ close(in);
            /* 2799 */ close(out);
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void copyIoStreamWithoutClose(InputStream in, OutputStream out)
    /*      */ {
        /*      */ try
        /*      */ {
            /* 2811 */ byte[] buffer = new byte[1024];
            /* 2812 */ int nrOfBytes = -1;
            /* 2813 */ while ((nrOfBytes = in.read(buffer)) != -1) {
                /* 2814 */ out.write(buffer, 0, nrOfBytes);
                /*      */ }
            /* 2816 */ out.flush();
            /*      */ } catch (IOException ie) {
            /* 2818 */ throw new IllegalStateException("IO Exception has occurred.", ie);
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void copyByteToIoStream(byte[] in, OutputStream out)
    /*      */ {
        /* 2829 */ copyIoStream(new ByteArrayInputStream(in), out);
        /*      */ }

    /*      */
    /*      */ public static byte[] converterByteArray(File in)
    /*      */ {
        /* 2839 */ return converterByteArray(getBufferedInputStream(in));
        /*      */ }

    /*      */
    /*      */ public static byte[] converterByteArray(InputStream in)
    /*      */ {
        /* 2849 */ ByteArrayOutputStream out = new ByteArrayOutputStream();
        /* 2850 */ copyIoStream(in, out);
        /* 2851 */ return out.toByteArray();
        /*      */ }

    /*      */
    /*      */ public static File getBackupFile(File originalFile, String pattern)
    /*      */ {
        /* 2863 */ SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        /* 2864 */ String timestamp = dateFormat.format(new Date());
        /* 2865 */ String newFile = originalFile.getAbsolutePath();
        /* 2866 */ File resultFile = new File(
                getFileNameWithoutExtension(newFile) + "[" + timestamp + "]." + getExtension(newFile));
        /* 2867 */ while (resultFile.exists()) {
            /* 2868 */ newFile = resultFile.getAbsolutePath();
            /* 2869 */ resultFile = new File(
                    getFileNameWithoutExtension(newFile) + "[" + timestamp + "]." + getExtension(newFile));
            /*      */ }
        /* 2871 */ return resultFile;
        /*      */ }

    /*      */
    /*      */ public static String encodeQuotedPrintable(String fileName, String encoding)
            /*      */ throws UnsupportedEncodingException
    /*      */ {
        /* 2884 */ String urlEncodingFileName = URLEncoder.encode(fileName, encoding);
        /* 2885 */ urlEncodingFileName = urlEncodingFileName.replaceAll("\\+", "_");
        /* 2886 */ urlEncodingFileName = urlEncodingFileName.replaceAll("%", "=");
        /*      */
        /* 2888 */ return urlEncodingFileName;
        /*      */ }

    /*      */
    /*      */ public static String encodeURLEncoding(String fileName, String encoding)
            /*      */ throws UnsupportedEncodingException
    /*      */ {
        /* 2901 */ String urlEncodingFileName = fileName;
        /* 2902 */ urlEncodingFileName = URLEncoder.encode(urlEncodingFileName, encoding);
        /* 2903 */ urlEncodingFileName = urlEncodingFileName.replaceAll("\\+", "%20");
        /* 2904 */ return urlEncodingFileName;
        /*      */ }

    /*      */
    /*      */ public static long parseStringFormatFileSize(String fileSize)
    /*      */ {
        /*      */ try
        /*      */ {
            /* 2923 */ if (fileSize.toUpperCase().endsWith("KB")) {
                /* 2924 */ return (Long.parseLong(fileSize.substring(0, fileSize.length() - 2).trim()) * 1024L);
                /*      */ }
            /* 2926 */ if (fileSize.toUpperCase().endsWith("MB")) {
                /* 2927 */ return (Long.parseLong(fileSize.substring(0, fileSize.length() - 2).trim()) * 1024L * 1024L);
                /*      */ }
            /* 2929 */ if (fileSize.toUpperCase().endsWith("GB")) {
                /* 2930 */ return (Long.parseLong(fileSize.substring(0, fileSize.length() - 2).trim()) * 1024L * 1024L
                        * 1024L);
                /*      */ }
            /* 2932 */ if (fileSize.toUpperCase().endsWith("B")) {
                /* 2933 */ return Long.parseLong(fileSize.substring(0, fileSize.length() - 1).trim());
                /*      */ }
            /* 2935 */ if (fileSize.toUpperCase().endsWith("BYTE")) {
                /* 2936 */ return Long.parseLong(fileSize.substring(0, fileSize.length() - 4).trim());
                /*      */ }
            /*      */
            /* 2939 */ return Long.parseLong(fileSize);
            /*      */ }
        /*      */ catch (NumberFormatException e) {
            /* 2942 */ throw new IllegalArgumentException("Unsupported file size suffix : " + fileSize);
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void close(InputStream is)
    /*      */ {
        /* 2952 */ if (is == null) return;
        /*      */ try {
            /* 2954 */ is.close();
            /*      */ } catch (Exception e) {
            /* 2956 */ LOGGER.error(e.getMessage());
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void close(OutputStream os)
    /*      */ {
        /* 2967 */ if (os == null) return;
        /*      */ try {
            /* 2969 */ os.close();
            /*      */ } catch (Exception e) {
            /* 2971 */ LOGGER.error(e.getMessage());
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void close(Reader reader)
    /*      */ {
        /* 2982 */ if (reader == null) return;
        /*      */ try {
            /* 2984 */ reader.close();
            /*      */ } catch (Exception e) {
            /* 2986 */ LOGGER.error(e.getMessage());
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static void close(Writer writer)
    /*      */ {
        /* 2997 */ if (writer == null) return;
        /*      */ try {
            /* 2999 */ writer.close();
            /*      */ } catch (Exception e) {
            /* 3001 */ LOGGER.error(e.getMessage());
            /*      */ }
        /*      */ }

    /*      */
    /*      */ public static File[] convertToFileArray(String[] filenames)
    /*      */ {
        /* 3013 */ File[] files = new File[filenames.length];
        /* 3014 */ for (int i = 0; i < filenames.length; ++i) {
            /* 3015 */ files[i] = new File(filenames[i]);
            /*      */ }
        /* 3017 */ return files;
        /*      */ }

    /*      */
    /*      */ private static OutputStream getBufferedOutputStream(File file)
    /*      */ {
        /*      */ try
        /*      */ {
            /* 3028 */ return new BufferedOutputStream(new FileOutputStream(file));
            /*      */ } catch (IOException ie) {
            /* 3030 */ throw new IllegalStateException("IO Exception has occurred.", ie);
            /*      */ }
        /*      */ }

    /*      */
    /*      */ private static InputStream getBufferedInputStream(File file)
    /*      */ {
        /*      */ try
        /*      */ {
            /* 3042 */ return new BufferedInputStream(new FileInputStream(file));
            /*      */ } catch (IOException ie) {
            /* 3044 */ throw new IllegalStateException("IO Exception has occurred.", ie);
            /*      */ }
        /*      */ }
    /*      */ }
