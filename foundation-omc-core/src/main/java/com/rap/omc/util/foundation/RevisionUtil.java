/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : RevisionUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 3. 11.  hyeyoung.park   Initial
 * ===========================================
 */
package com.rap.omc.util.foundation;

import java.math.BigDecimal;
import java.util.List;

import com.rap.omc.api.object.dom.BusinessObject;
import com.rap.omc.api.object.model.BusinessObjectVO;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.foundation.lifecycle.model.LifeCycleInfo;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.util.foundation.CommonServiceUtil;
import com.rap.omc.util.foundation.LifeCycleUtil;




/**
 * <pre>
 * Class : RevisionUtil
 * Description : TODO
 * </pre>
 * 
 * @author hyeyoung.park
 */
public class RevisionUtil {

    private static RevisionUtil lInstance;

    /**
     * Singleton을 구성된 getInstance() method
     */
    private synchronized static RevisionUtil getInstance(){
        if (lInstance == null) {
            lInstance = new RevisionUtil();
        }
        return lInstance;
    }

    public static String getNextRevisionNo(String lifeCycleName, String revision){
        if(revision.indexOf(".") != revision.lastIndexOf(".")){
            throw new FoundationException("Revions cannot have more one '.' characters.");
        }
        String newRevision = "";
        LifeCycleInfo lifeCycleInfo = LifeCycleUtil.getLifeCycleInfo(lifeCycleName);
        String revisionRule = lifeCycleInfo.getSequenceRule();
        if(StrUtil.isEmpty(revisionRule)) revisionRule = "-";
        if(StrUtil.isEmpty(revision)){
            if(revisionRule.indexOf(",") ==-1) return revisionRule;
            return(revisionRule.substring(0, revisionRule.indexOf(",")));
        }
        revision = revision.toUpperCase();//Revision은 항상 대문자로 함.
        String numberSuffix = StrUtil.getSuffixNumber(revision);
        String stringPrefix = StrUtil.getPrefixString(revision);
        if(StrUtil.isEmpty(numberSuffix) && StrUtil.isEmpty(stringPrefix)) throw new FoundationException("How can new revision.");

        if(!StrUtil.isEmpty(numberSuffix)){
            int dotIdx = numberSuffix.indexOf(".");
            int len = numberSuffix.length();
            if(dotIdx == 0){
                numberSuffix = "." + String.valueOf((Integer.parseInt(numberSuffix.substring(1)) + 1));
            }else if(dotIdx < 0){
                String numberStr = String.valueOf((Integer.parseInt(numberSuffix) + 1));
                if(numberStr.length() < len){
                    numberSuffix = StrUtil.LPAD(numberStr, len, "0");
                }else{
                    numberSuffix = numberStr;
                }
                
            }else{
                int dotLen = numberSuffix.length() - dotIdx - 1;
                BigDecimal dbl = new BigDecimal(numberSuffix);
                switch(dotLen){
                    case 1:
                        dbl = dbl.add(new BigDecimal(0.1));
                        break;
                    case 2:
                        dbl = dbl.add(new BigDecimal(0.01));
                        break;
                    case 3:
                        dbl = dbl.add(new BigDecimal(0.001));
                        break;
                    case 4:
                        dbl = dbl.add(new BigDecimal(0.0001));
                        break;
                    case 5:
                        dbl = dbl.add(new BigDecimal(0.00001));
                        break;
                    case 7:
                        dbl = dbl.add(new BigDecimal(0.000001));
                        break;
                    default:
                        dbl = dbl.add(new BigDecimal(0.0000001));
                }
                numberSuffix = StrUtil.RPAD(String.valueOf(dbl), len, "0");
            }
            newRevision = stringPrefix + numberSuffix;
        }else{
            StringBuffer strBuf = new StringBuffer(stringPrefix);
            strBuf.reverse();
            
            if (strBuf.charAt(0) == 'Z'){
                numberSuffix = numberSuffix.substring(1, numberSuffix.length()-2) + "AA";
            }else
            {
                int chr = (int)strBuf.charAt(0) + 1;
                numberSuffix = numberSuffix.substring(1, numberSuffix.length()-2) + String.valueOf((char)chr);
            }
        }
        return newRevision;
    }
    public static <T extends BusinessObjectVO> List<T> getFirstRevision(String obid){
        BusinessObject bizDom = new BusinessObject(obid);
        return CommonServiceUtil.getRevisionObjects("Revision.getFirstRevision", bizDom.getVo().getClassName(),bizDom.getVo().getNames());
    }

    public static <T extends BusinessObjectVO> List<T> getLastRevision(String obid){
        BusinessObject bizDom = new BusinessObject(obid);
        return CommonServiceUtil.getRevisionObjects("Revision.getLastRevision", bizDom.getVo().getClassName(),bizDom.getVo().getNames());
    }
    
    public static <T extends BusinessObjectVO> List<T> getRevisions(String obid){
        BusinessObject bizDom = new BusinessObject(obid);
        return CommonServiceUtil.getRevisionObjects("Revision.getRevisions", bizDom.getVo().getClassName(),bizDom.getVo().getNames());
    }
}
