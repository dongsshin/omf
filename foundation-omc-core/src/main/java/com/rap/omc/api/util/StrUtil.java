package com.rap.omc.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import com.rap.omc.api.oql.utility.OmcRandomString;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.NullUtil;

public class StrUtil {
    public static String nvl(String originalStr, String defaultStr){
        if ((originalStr == null) || (originalStr.length() < 1)) return defaultStr;
        return originalStr;
    }
    public static Date convertStr2Date(String strDate) {
        Date date = null;
        if(strDate == null || strDate.length() == 0 ) return date;
        String prefix = strDate.substring(0,10);
        StringBuffer format = new StringBuffer();
        if( Pattern.matches("[0-9][0-9][0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9]",prefix)){
            format.append("yyyy-MM-dd");
            if(strDate.length() == 19) format.append(" HH:mm:ss");
            if(strDate.length() == 16) format.append(" HH:mm");
        }else if(Pattern.matches("[0-9][0-9][0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9]",prefix)){
            format.append("yyyy/MM/dd");
            if(strDate.length() == 19) format.append(" HH:mm:ss");
            if(strDate.length() == 16) format.append(" HH:mm");
        }else if(Pattern.matches("[0-9][0-9]\\-[0-9][0-9]\\-[0-9][0-9][0-9][0-9]",prefix)){
            format.append("MM-dd-yyyy");
            if(strDate.length() == 19) format.append(" HH:mm:ss");
            if(strDate.length() == 16) format.append(" HH:mm");
        }else if(Pattern.matches("[0-9][0-9]\\/[0-9][0-9]\\/[0-9][0-9][0-9][0-9]",prefix)){
            format.append("MM/dd/yyyy");
            if(strDate.length() == 19) format.append(" HH:mm:ss");
            if(strDate.length() == 16) format.append(" HH:mm");
        }else{
            prefix = strDate.substring(1,9);
            if(Pattern.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]",prefix)){
                format.append("yyyyMMdd");
                if(strDate.length() == 17) format.append(" HH:mm:ss");
                if(strDate.length() == 14) format.append(" HH:mm");
            }
        }
        SimpleDateFormat transFormat = new SimpleDateFormat(format.toString());
        try {
            date = transFormat.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            throw new FoundationException("[Foundation]Date Format does not support for '" + strDate + "' format.");
        }
        return date;
    }
    
    
    public static boolean checkAllExistsChar(String source, String chkWanted){
        char[] sourceArray    = source.toCharArray();
        char[] chkWantedArray = chkWanted.toCharArray();
        
        for(int i = 0; i < chkWantedArray.length; i++){
            boolean chk = false;
            for(int j = 0; j < sourceArray.length; j++){
                if(sourceArray[i] == chkWantedArray[j]){
                    chk = true;break;
                }
            }
            if(!chk) return false;
        }
        return true;
    }

    public static boolean in(Set<String> strSet,Set<String> tarSet){

        for(String str : strSet){
            if(tarSet.contains(str)) return true;
        }
        return false;
    }
    public static boolean in(String inStr,Set<String> tarSet){
        if(tarSet.contains(inStr)) return true;
        return false;
    }
    

    public static boolean in(String source, char chr){
        char[] chrArray = source.toCharArray();
        for(int i = 0; i< chrArray.length; i++){
            if(chr == chrArray[i]) return true;
        }
        return false;
    }
    
    
    public static String makeWhereInStr(Set<String> strSet){
        StringBuffer strBuf = new StringBuffer();
        boolean isFirst = true;
        for(String str : strSet){
            if(isFirst){
                strBuf.append("'").append(str).append("'");
            }else{
                strBuf.append(",'").append(str).append("'");
            }
            isFirst = false;
        }
        return(strBuf.toString());
    }
    public static String makeWhereInStr(List<String> strList){
        StringBuffer strBuf = new StringBuffer();
        boolean isFirst = true;
        for(String str : strList){
            if(isFirst){
                strBuf.append("'").append(str).append("'");
            }else{
                strBuf.append(",'").append(str).append("'");    
            }
            isFirst = false;
        }
        return(strBuf.toString());
    }
    public static String makeWhereInStr(String[] strList){
        StringBuffer strBuf = new StringBuffer();
        boolean isFirst = true;
        for(String str : strList){
            if(isFirst){
                strBuf.append("'").append(str).append("'");
            }else{
                strBuf.append(",'").append(str).append("'");
            }
            isFirst = false;
        }
        return(strBuf.toString());
    }
    public static List<String> convertArrayToList(String[] strArray) {
        List<String> strList = new ArrayList<String>();
        for(int i = 0; i < strArray.length; i++){
            strList.add(strArray[i]);
        }
        return strList;
    }
    public static String[] convertListToArray(List<String> strList) {
        if(NullUtil.isNone(strList)) return null;
        String[] array = new String[strList.size()];
        int i = 0;
        for(String str : strList){
            array[i++] = str;
        }
        return array;
    }
    public static String convertArrayToString(String[] strArray) {
        StringBuffer strBuf = new StringBuffer();
        for(int i = 0; i < strArray.length; i++){
            if(i > 0) strBuf.append(",");
            strBuf.append(strArray[i]);
        }
        return strBuf.toString();
    }
    public static Set<String> convertArrayToSet(String[] strArray) {
        Set<String> strList = new HashSet<String>();
        for(int i = 0; i < strArray.length; i++){
            strList.add(strArray[i]);
        }
        return strList;
    }
    public static Set<String> convertListToSet(List<String> strList) {
        Set<String> strSet = new HashSet<String>();
        for(String str : strList){
            strSet.add(str);
        }
        return strSet;
    }
    public static List<String> convertSetToList(Set<String> strSet) {
        List<String> strList = new ArrayList<String>();
        for(String str : strSet){
            strList.add(str);
        }
        return strList;
    }
    public static boolean in(String source, String... values) {
        List<String> strList = Arrays.asList(values);
        return(in(source,strList));
    }
    public static boolean in(char source, char... values) {
        for(int i = 0; i < values.length; i++){
            if(source == values[i]) return true;
        }
        return false;
    }
    public static boolean in(String source, List<String> strList) {
        for(String str : strList){
            if(str.equals(source)) return true;
        }
        return false;
    }
    public static String[] uniquize(String[] arr) {
        if(NullUtil.isNone(arr)) return null;
        List<String> strList = Arrays.asList(arr);
        Set<String> strSet = new HashSet<String>();
        for(String str : strList){
            if(!StrUtil.isEmpty(str)) strSet.add(str);
        }
        String[] rtn = new String[strSet.size()];
        int i = 0;
        for(String str : strSet){
            rtn[i++] = str;
        }
        return(rtn);
    }
    public static String[] uniquize(List<String> strList) {
        if(NullUtil.isNone(strList)) return null;
        Set<String> strSet = new HashSet<String>();
        for(String str : strList){
            if(!StrUtil.isEmpty(str)) strSet.add(str);
        }
        String[] rtn = new String[strSet.size()];
        int i = 0;
        for(String str : strSet){
            rtn[i++] = str;
        }
        return(rtn);
    }
    public static String[] uniquize(String strIn, String separator) {
        if(NullUtil.isNone(strIn)) return null;
        return(uniquize(strIn.split(Pattern.quote(separator))));
    }
    public static boolean isEqual(String s1, String s2){
        if(StrUtil.isEmpty(s1) && StrUtil.isEmpty(s2)) return true;
        if(!StrUtil.isEmpty(s1) && !StrUtil.isEmpty(s2)){
            if(s1.equals(s2)) return true;
        }
        return false;
    }
    public static boolean isEqual(int s1, int s2){
        if(s1 == s2) return true;
        return false;
    }
    public static boolean isEqual(long s1, long s2){
        if(s1 == s2) return true;
        return false;
    }
    public static String removeFromToStr(String strIn, String from, String to)
    {
        String str = strIn;
        int firstPos = 0;
        int endPos = 0;
        int idx = 1;
        int cnt = 0;
        int charLength = from.length();
        if(from.equals(to))
        {
            firstPos = StrUtil.inStr(str, from,1,1);
            endPos   = StrUtil.inStr(str, from,1,2);
        }
        else
        {
            int totalLen = str.length();
            while(idx <= totalLen)
            {
                if(StrUtil.subStr(str, idx, charLength).equals(from))
                {
                    cnt++;
                    if (firstPos == 0) firstPos = idx;
                }
                if(StrUtil.subStr(str, idx, charLength).equals(to) && firstPos > 0 && idx > firstPos )
                {
                    cnt--;   
                }
                if(cnt == 0 && firstPos > 0 && idx > firstPos) 
                {
                    endPos = idx;
                    break;
                }
                idx = idx + charLength;
            }
        }
        if(endPos == 0) return(str);
        str = StrUtil.subStr(str, 1,firstPos-1) + StrUtil.subStr(str, endPos+charLength);
        return(removeFromToStr(str,from,to));
    }
	public static String LPAD(String str, int len, String append){
	    String rtn   = str;
	    int    inLen = str.length();
	    if (str.length() < len){for (int i = (len - inLen); i > 0; i--){rtn = append + rtn;}}
	    else{rtn = str.substring(0, len);}
	    return rtn;
	}
	public static String LPAD(int str, int len, String append){
	    return(LPAD(String.valueOf(str),len,append));
	}
    public static String RPAD(String str, int len, String append){
        String rtn   = str;
        int    inLen = str.length();
        if (str.length() < len){for (int i = (len - inLen); i > 0; i--){rtn = rtn + append;}}
        else{rtn = str.substring(0, len);}
        return rtn;
    }
    public static String RPAD(int str, int len, String append){
        return(RPAD(String.valueOf(str),len,append));
    }
	public static int countCharInStr(String inStr, char chr){
	    int cnt = 0;
	    char[] chrList = inStr.toCharArray();
	    for (char tempChr: chrList ) {if(tempChr == chr) cnt++;}
	    return cnt;
	}
    public static int countCharInStr(String inStr, String wanted){
        int cnt = 0;
        int idx = 0;
        idx = inStr.indexOf(wanted);
        while(idx != -1){
            cnt++;
            idx = inStr.indexOf(wanted,idx+1);
        }
        return cnt;
    }
    public static void addList(ArrayList<String> strList, String value){
        if (strList == null) strList = new ArrayList<String>();
        boolean isExist = false;
        
        int size = strList.size();
        for (int i =0;i < size; i ++ ){
            if (strList.get(i) != null && strList.get(i).equals(value)) isExist = true;
        }
        if(!isExist) strList.add(value);
    }
	public static String replaceCharFromToCondition(String strIn, String ccf, String cct, String cfv, String ctv)
	{
		int p1,p2;p1=p2=0;
		String t1,t2,ls,rs; t1=t2=ls=rs=null;
		p1 = strIn.indexOf(ccf);
		p2 = strIn.indexOf(cct);
		if ((p1 != -1) && (p2 != -1) && (p2 > p1)){
			ls = strIn.substring(0, p1);
			rs = strIn.substring(p2+1);
			t1 = strIn.substring(p1, p2+1);
			t2 = replaceAll(t1,cfv, ctv);
			ls = replaceAll(ls,t1, t2);
			rs = replaceAll(rs,t1, t2);
			rs = replaceCharFromToCondition(rs,cct,ccf,cfv,ctv);
			strIn = ls+t2+rs;
		}
		return(strIn);
	}
	public static String replaceAll(final String strIn, final String from, final String to){
	    
	    return(replace(strIn,from,to));
	    /*
	    int pos2 = 0;
	    if (strIn.indexOf(from) == -1)  return strIn;
		return(replaceAll(strIn.replace(from,to),from,to));*/
	}
	private static int countNumFimesOneStrInOther(char[] str1Arr,char[] str2Arr )
    {
         int counter=0;
         int scLen   = str1Arr.length;
         int fromLen = str2Arr.length;
         int i=0,j=0,k=0;
         for(i=0; i < scLen ; i++)         
         {
             j=i;k=0;
             while(j < scLen && k < fromLen && str1Arr[j]==str2Arr[k])
             {
                 j++;k++;
             }
            if(k==fromLen) counter++;
         }
         return counter;
    }

	public static String replace(String src, String from, String to)
    {
	    if(to==null) to = "";
	    if(from==null || from.equals("")) return(src);
	    if(src==null || src.equals("")) return("");
	    if (src.indexOf(from) == -1)  return src;
        //return(src.replace(from, to));
        return(src.replace(from, to));
//	    
//	    
//	    char[] str1Arr = src.toCharArray();
//        char[] str2Arr = from.toCharArray();
//        char[] str3Arr = to.toCharArray();
//        
//        int numTimes = countNumFimesOneStrInOther(str1Arr, str2Arr);
//        int scLen   = str1Arr.length;
//        int fromLen = str2Arr.length;
//        int toLen   = str3Arr.length;
//        int charLength =  scLen + Math.abs(numTimes*(toLen-fromLen));
//        char[] strResult = new char[charLength];
//
//        int resIndex=0;
//        int i=0,j=0,k=0;
//        for(i=0; i < scLen ; i++)               
//        {
//            j=i;k=0;
//            while(j < scLen && k < fromLen && str1Arr[j]==str2Arr[k])
//            {
//                j++;k++;
//            }
//            if(k==fromLen)
//            {
//                k=0;
//                while(k < toLen)
//                {
//                    strResult[resIndex] = str3Arr[k];
//                    resIndex++;k++;
//                }
//                i = i + fromLen - 1;
//            }
//            else
//            {
//                strResult[resIndex] = str1Arr[i];
//                resIndex++;
//            }
//        }
//        return new String(strResult);
    }
	public static void replaceAll(StringBuffer strIn, String from, String to){
        int pos1  = strIn.indexOf(from);
        int pos2 = 0;
        if (pos1 == -1) return;
        strIn.replace(pos1, pos1+from.length(), to);
		replaceAll(strIn,from,to);
	}
	public static String clearStringData  (String strIn) {
		String strTemp = "@#$!*)(}{jghf";
		while(strTemp.length() > 0){
			strTemp = getStringForFromTo(strIn,1,"'","'");
			if (strTemp.length() > 0){
				strIn = replaceAll(strIn,"'" + strTemp + "'","");
			}
		}
		return(strIn);
	}
    public static void clearStringData  (StringBuffer strBuffIn) {
        String strTemp = "@#$!*)(}{jghf";
        while(strTemp.length() > 0){
            strTemp = getStringForFromTo(strBuffIn,1,"'","'");
            if (strTemp.length() > 0){
                replaceAll(strBuffIn,"'" + strTemp + "'","");
            }
        }
    }
	public static String getStringForFromTo(final String strIn, int startPos, final String left, final String right){
		int t1 = 0;int t2 = 0;
		if(startPos==0) startPos = 1;
		t1 = strIn.indexOf(left, startPos-1);
		if (t1 == -1) return("");
		if (left.equals(right)){
			t2 = strIn.indexOf(right, t1+left.length());
		}else{
			t2 = strIn.indexOf(right, t1);
		}
		if (t2 == -1) return("");
		return(strIn.substring(t1+left.length(), t2));
	}
    public static String getStringForFromTo(final StringBuffer strBuffIn, int startPos, final String left, final String right){
        int t1 = 0;int t2 = 0;
        if(startPos==0) startPos = 1;
        t1 = strBuffIn.indexOf(left, startPos-1);
        if (t1 == -1) return("");
        if (left.equals(right)){
            t2 = strBuffIn.indexOf(right, t1+left.length());
        }else{
            t2 = strBuffIn.indexOf(right, t1);
        }
        if (t2 == -1) return("");
        return(strBuffIn.substring(t1+left.length(), t2));
    }
    public static boolean isNotEmpty(final String strIn){
		return !isEmpty(strIn);
	}
	public static boolean isEmpty(final String strIn){
		if (strIn == null) return true;
		if (strIn.length() <= 0) return true;
		return false;
	}
	public static boolean isEmpty(final StringBuffer strIn){
		if (strIn == null) return true;
		if (strIn.length() <= 0 ) return true;
		return false;
	}
	public static String subStr(final String strIn, final int start, int len){
		if (isEmpty(strIn)) return("");
		if (strIn.length() < (start + len)) len = strIn.length() - start + 1;
		return(strIn.substring(start-1, start+len-1));
	}
	public static String subStr(final String strIn, int start){
		if (isEmpty(strIn)) return("");
		if (start > 0 ) {return(strIn.substring(start-1));} else {return(strIn.substring(strIn.length() + start - 1));}
	}
	//ok
	public static int inStr(final String strIn, final String wanted){
	    if(StrUtil.isEmpty(strIn)) return 0;
		return(strIn.indexOf(wanted)+1);
	}
	
	public static int inStr(final String strIn, final String wanted, final int pos){
	    if(StrUtil.isEmpty(strIn)) return(0);
	    if (pos > 0) 
	    {
	        return(strIn.indexOf(wanted, pos-1)+1);
	    } 
	    else 
	    {
	        return(minusInstr(strIn,wanted,pos,1));
	    }
	}

	private static int minusInstr(final String strIn, final String wanted, int pos, int nth){
	      if(StrUtil.isEmpty(strIn)) return(0);
	      StringBuffer strBuf = new StringBuffer(strIn);
	      StringBuffer wantedrBuf = new StringBuffer(wanted);
	      strBuf.reverse();wantedrBuf.reverse();
	      pos = pos*-1;
      
	      int ddd = inStr(strBuf.toString(),wantedrBuf.toString(),pos,nth);
	      if(ddd == 0) return(0);
	      return(strIn.length()-ddd+(2+-1*wanted.length()));
    }
	
	public static int inStr(final String strIn, final String wanted, final int pos, final int nth){
	    if (pos > 0) 
	    {
    	    int rtn = 0;
    	    int nsp = pos-1;
    	    for (int i = 0; i < nth; i++)
    	    {
    	        rtn = strIn.indexOf(wanted, nsp);
    	        if (rtn == -1) return(0);
    	        nsp = rtn + wanted.length();
    	    }
    		return(rtn+1);
	    }
	    else{
	        return(minusInstr(strIn,wanted,pos,nth));
	    }
	}
    public static String subStr(final StringBuffer strIn, final int start){
        if (isEmpty(strIn)) return("");
        return(strIn.substring(start-1));
    }
    public static int inStr(final StringBuffer strIn, final String wanted){
        return(strIn.indexOf(wanted)+1);
    }
    public static int inStr(final StringBuffer strIn, final String wanted, final int pos){
        return(strIn.indexOf(wanted)+1);
    }
    public static int inStr(final StringBuffer strIn, final String wanted, final int pos, final int nth){
        int rtn = 0;
        int nsp = pos;
        for (int i = 0; i < nth; i++)
        {
            rtn = strIn.indexOf(wanted, nsp);
            nsp = rtn + wanted.length();
        }
        return(rtn+1);
    }
	
	public static String getRandomString(int len){
		return((new OmcRandomString(len)).nextString());
	}
	public static String convertList2Str(List<String> classList)
	{
		StringBuffer rtn = new StringBuffer();
		int chk = 0;
		for(String str: classList)
		{
			if (chk > 0) rtn.append(",");
			rtn.append(str);
			chk++;
		}
		return(rtn.toString());
	}
	public static String convertSet2Str(Set<String> strSet)
    {
        StringBuffer rtn = new StringBuffer();
        int chk = 0;
        for(String str: strSet)
        {
            if (chk > 0) rtn.append(",");
            rtn.append(str);
            chk++;
        }
        return(rtn.toString());
    }
	public static String convertSet2Str(Set<String> strSet,String seperate)
	    {
	        StringBuffer rtn = new StringBuffer();
	        int chk = 0;
	        for(String str: strSet)
	        {
	            if (chk > 0) rtn.append(seperate);
	            rtn.append(str);
	            chk++;
	        }
	        return(rtn.toString());
	    }
    public static String convertSet2Str(HashSet<Integer> intSet)
    {
        StringBuffer rtn = new StringBuffer();
        int chk = 0;
        for(Integer str: intSet)
        {
            if (chk > 0) rtn.append(",");
            rtn.append(str);
            chk++;
        }
        return(rtn.toString());
    }
	public static String[] convertSet2Array(Set<String> strSet){
	    String[] array = new String[strSet.size()];
	        int chk = 0;
	        for(String str: strSet)
	        {
	            array[chk] = str;
	            chk++;
	        }
	        return array;
	    }
	public static String getSuffixNumber(String str){
	    if(NullUtil.isNone(str)) return "";
        StringBuffer wrk = new StringBuffer(str);
        wrk.reverse();
        StringBuffer rtn = new StringBuffer();
        char[] charArray = wrk.toString().toCharArray();
        for(int i = 0; i < charArray.length; i++){
            if(in("0123456789.",charArray[i])){
                rtn.append(charArray[i]);
            }else{
                break;
            }
        }
        if(rtn.length() == 0) return "";
        rtn.reverse();
        return(rtn.toString());
//        if(rtn.charAt(0) == '.'){
//            return("0" + rtn.toString());
//        }else{
//            return(rtn.toString());
//        }
    }
	public static String getPrefixString(String str){
        if(NullUtil.isNone(str)) return "";
        StringBuffer wrk = new StringBuffer(str);
        wrk.reverse();
        StringBuffer rtn = new StringBuffer();
        boolean isChar = false;
        char[] charArray = wrk.toString().toCharArray();
        for(int i = 0; i < charArray.length; i++){
            if(!isChar && !in("0123456789.",charArray[i])){
                isChar = true;
            }
            if(isChar) rtn.append(charArray[i]);
        }
        rtn.reverse();
        return(rtn.toString());
    }
	public static boolean isNumeric(String str)  
	{  
	  if(str == null) return false;
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	public static String defaultString(String str) {
		if(isNotEmpty(str)) return str;
		return "";
	}
	public static String defaultIfEmpty(String str, String defa) {
		if(isNotEmpty(str)) return str;
		return defa;
	}	
}
