/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : WorkflowUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 6. 29.  kwanghyui.choi   Initial
 * ===========================================
 */
package rap.application.workflow.util;

import java.util.Random;


/**
 * <pre>
 * Class : WorkflowUtil
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public class WorkflowUtil {
    public static void main(String args[]) {
//        for(int idx = 0; idx < 20; idx ++){
            //String inputStr = "EEKCA00006 (  )";
            
            String inputStr = "New part develop for applied BS- test driver min";
            System.out.println(inputStr.getBytes().length);
            System.out.println(getMaxByteString(inputStr, 36));
//        }
    }
 
    public static String getRandomTimestamp() {
        int leftLimit = 97; 
        int rightLimit = 122; 
        int targetStringLength = 20;
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
              (new Random().nextFloat() * (rightLimit - leftLimit));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
     
    }
    
    public static String getMaxByteString(String str, int maxLen) {
        String rtnStr = null;
        String ellipsis = "...";
        if(str.getBytes().length <= maxLen) {
            rtnStr = str;
        }else{
            StringBuilder sb = new StringBuilder();
            int curLen = 0;
            String curChar;
            for(int i = 0; i < str.length(); i++) {
                curChar = str.substring(i, i+1); 
                curLen += curChar.getBytes().length; 
                if (curLen > maxLen - ellipsis.getBytes().length){
                    break; 
                }
                else{
                    sb.append(curChar); 
                }
            } 
            sb.append(ellipsis);
            rtnStr = sb.toString();
        }
        return rtnStr;
    }
    
//    public static String findValue(HashMap<String, Object> map, String key) {
//        String rtnValue = null;
//        for( Entry<String, Object> elem : map.entrySet() ){
//            System.out.println( String.format("키 : %s, 값 : %s", elem.getKey(), elem.getValue()) );
//        }
//
//        return rtnValue;
//    }
}