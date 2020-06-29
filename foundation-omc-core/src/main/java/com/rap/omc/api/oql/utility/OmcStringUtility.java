package com.rap.omc.api.oql.utility;

import java.util.ArrayList;
import java.util.List;

public class OmcStringUtility {
	public static String LPAD(String str, int len, String append){
	    String rtn   = str;
	    int    inLen = str.length();
	    if (str.length() < len){for (int i = (len - inLen); i > 0; i--){rtn = append + rtn;}}
	    else{rtn = str.substring(0, len);}
	    return rtn;
	}
    public static String RPAD(String str, int len, String append){
        String rtn   = str;
        int    inLen = str.length();
        if (str.length() < len){for (int i = (len - inLen); i > 0; i--){rtn = rtn + append;}}
        else{rtn = str.substring(0, len);}
        return rtn;
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
    public static void addList(ArrayList<String> strList, String value, int processLevel,boolean testMode){
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
		if (strIn.indexOf(from) == -1)  return strIn;
		return(replaceAll(strIn.replace(from,to),from,to));
	}
	public static void replaceAll(StringBuffer strIn, String from, String to){
        int pos1  = strIn.indexOf(from);
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
		return(strIn.indexOf(wanted)+1);
	}
	public static int inStr(final String strIn, final String wanted, final int pos){
	    if (pos > 0) {return(strIn.indexOf(wanted, pos-1)+1);} else {return(strIn.indexOf(wanted, strIn.length() + pos)+1);}
	}

	public static int inStr(final String strIn, final String wanted, final int pos, final int nth){
	    int rtn = 0;
	    int nsp = pos;
	    for (int i = 0; i < nth; i++)
	    {
	        rtn = strIn.indexOf(wanted, nsp);
	        nsp = rtn + wanted.length();
	    }
		return(rtn+1);
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
}
