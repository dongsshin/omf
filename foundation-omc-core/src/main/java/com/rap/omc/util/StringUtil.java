/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : StringUtil.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 1. 27. yongsik1.kim Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import com.rap.omc.api.oql.model.OmcGroupByParamVO;
import com.rap.omc.api.oql.model.OmcOQLCondition;
import com.rap.omc.api.oql.utility.OmcUtility;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.EtcUtil;
import com.rap.omc.constants.FoundationConstants;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.schema.util.OmcSystemConstants;

import net.htmlparser.jericho.Renderer;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.Tag;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class StringUtil {
    private static final String EMPTY_STRING = "";
    
    public static boolean equals(String string, String anotherString){
        if ((string == null) && (anotherString == null)) { return true; }

        if ((string == null) || (anotherString == null)) { return false; }

        return string.equals(anotherString);
    }
    public static void appendWhereAndPattern(StringBuffer sourcePattern, String ... added){
        appendWherePatternSub(false,sourcePattern,added);
    }
    private static void appendWherePatternSub(boolean isOr,StringBuffer sourcePattern, String ... added){
        StringBuffer tempBuf = new StringBuffer("(");
        boolean isFirst = true;
        for(int i = 0; i < added.length; i++){
            if(isOr){
                if(!isFirst) tempBuf.append(GlobalConstants.OQL_OPERATOR_OR);
            }else{
                if(!isFirst) tempBuf.append(GlobalConstants.OQL_OPERATOR_AND);
            }
            tempBuf.append("(");
            tempBuf.append(added[i]);
            tempBuf.append(")");
            isFirst = false;
        }
        tempBuf.append(")");
        if(!StrUtil.isEmpty(sourcePattern)) {
            String str = "(" + sourcePattern + ")";
            sourcePattern.setLength(0);
            sourcePattern.append(str);
            sourcePattern.append(GlobalConstants.OQL_OPERATOR_AND);
        }
        sourcePattern.append(tempBuf);
        
    }
    public static void appendWhereOrPattern(StringBuffer sourcePattern, String ... added){
        appendWherePatternSub(true,sourcePattern,added);
    }
    /**
     * findObject() 이용 시 사용되는 selectPattern을 구성한다.
     *
     * @param selectPattern
     * @param selectValue “From[” + className + ”]” + “.“ + attribute
     *            or “From[” + className + ”]” + “.To.“ + attribute
     *            (ex, From[PartFamily2Plant].To.names)
     */
    public static void constructSelectPattern(StringBuffer selectPattern, String selectValue){
        if(!NullUtil.isNone(selectValue)){
            if (selectPattern.length() > 0) {
                selectPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE);
            }
            if(!NullUtil.isNone(selectValue) && !NullUtil.isNone(selectValue.trim())) selectPattern.append(selectValue.trim());
        }
    }
    public static void constructSelectPatternForFile(StringBuffer selectPattern, final String attributName){
        constructSelectPatternForFile(selectPattern,attributName,"file"+attributName);
    }
    public static void constructSelectPatternForFile(StringBuffer selectPattern, final String attributName, final String attributeAliasIn){
        constructSelectPatternForFile(selectPattern,attributName,attributeAliasIn);
    }
    /**
     * ":"로 구분자로 사용함. Alias를 사용하는경우 Space 다음에 Alias를 줄 수 있음.
     * StringUtil.makeGroubByMaxMinPattern("@this.[creator] creadtorAlias","@this.[flags]","@this.[names] nameMax","@this.[names] nameMin:@this.[titles] titles")
     * Sort를 원하는 경우 Java에서 SortUtil를 이용함
     * Sample : List<GroupByResultVO> groupByList =  SortUtil.groupBy(findObjectsVOList, GroupByResultVO.getBasisAttributeNameForSort("creadtorAlias"), "", GroupByResultVO.getSumAttributeNameForSort("flags"), GroupByResultVO.getSumAttributeNameForSort("flags"));
     * @param groupByPattern
     * @param sumPattern
     * @param maxPattern
     * @param minPattern
     * @param avgPattern
     * @return
     */
    public static List<OmcGroupByParamVO>  makeGroubByMaxMinPattern(String groupByPattern, String sumPattern, String maxPattern, String minPattern, String avgPattern){
        List<OmcGroupByParamVO> groupByParamList = new ArrayList<OmcGroupByParamVO>();
        if(!StrUtil.isEmpty(groupByPattern)){
            String[] groupByArray = groupByPattern.split(Pattern.quote(":"));
            for(int i = 0; i< groupByArray.length; i++){
                OmcGroupByParamVO omcGroupByParamVO = new OmcGroupByParamVO(groupByArray[i],OmcGroupByParamVO.KEY.groubBy);
                omcGroupByParamVO.splitPattern();
                groupByParamList.add(omcGroupByParamVO);
            }
        }
        if(!StrUtil.isEmpty(sumPattern)){
            String[] groupByArray = sumPattern.split(Pattern.quote(":"));
            for(int i = 0; i< groupByArray.length; i++){
                OmcGroupByParamVO omcGroupByParamVO = new OmcGroupByParamVO(groupByArray[i],OmcGroupByParamVO.KEY.sum);
                omcGroupByParamVO.splitPattern();
                groupByParamList.add(omcGroupByParamVO);
            }
        }
        if(!StrUtil.isEmpty(maxPattern)){
            String[] groupByArray = maxPattern.split(Pattern.quote(":"));
            for(int i = 0; i< groupByArray.length; i++){
                OmcGroupByParamVO omcGroupByParamVO = new OmcGroupByParamVO(groupByArray[i],OmcGroupByParamVO.KEY.max);
                omcGroupByParamVO.splitPattern();
                groupByParamList.add(omcGroupByParamVO);
            }
        }
        if(!StrUtil.isEmpty(minPattern)){
            String[] groupByArray = minPattern.split(Pattern.quote(":"));
            for(int i = 0; i< groupByArray.length; i++){
                OmcGroupByParamVO omcGroupByParamVO = new OmcGroupByParamVO(groupByArray[i],OmcGroupByParamVO.KEY.min);
                omcGroupByParamVO.splitPattern();
                groupByParamList.add(omcGroupByParamVO);
            }
        }
        if(!StrUtil.isEmpty(avgPattern)){
            String[] groupByArray = avgPattern.split(Pattern.quote(":"));
            for(int i = 0; i< groupByArray.length; i++){
                OmcGroupByParamVO omcGroupByParamVO = new OmcGroupByParamVO(groupByArray[i],OmcGroupByParamVO.KEY.avg);
                omcGroupByParamVO.splitPattern();
                groupByParamList.add(omcGroupByParamVO);
            }
        }
        return groupByParamList;
    }
    
    /**
     * findObject() 이용 시 화일에 대새서 selectPattern을 구성한다.
     *
     * @param selectPattern
     * @param attributName
     * @param attributeAliasIn
     * @param conditionList
     */
    
    public static void constructSelectPatternForFile(StringBuffer selectPattern, final String attributName, final String attributeAliasIn,final List<OmcOQLCondition> conditionList){
        StringBuffer b = new StringBuffer();
        StringBuffer wb = new StringBuffer();
        String attributeAlias = attributeAliasIn;
        if(StrUtil.isEmpty(attributeAlias)) attributeAlias = "file" + attributName;
        if(!NullUtil.isNone(conditionList)){
            for(OmcOQLCondition condition : conditionList){
                if(!StrUtil.isEmpty(wb)) wb.append(GlobalConstants.OQL_OPERATOR_AND);
                wb.append(condition.getLeftValue()).append(condition.getOperator()).append(condition.getRightValue());
            }
        }
        if(!NullUtil.isNone(attributName)){
            if (selectPattern.length() > 0) {
                selectPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE);
            }
            if(StrUtil.isEmpty(wb)){
                b.append(GlobalConstants.FLAG_TYPE_FROM).append("[").append(FoundationConstants.BIZCLASS_FILES).append("]").append(attributName).append(" ").append(attributeAlias);
            }else{
                b.append(GlobalConstants.FLAG_TYPE_FROM).append("[").append(FoundationConstants.BIZCLASS_FILES).append("{").append(wb).append("}").append(attributName).append(" ").append(attributeAlias);
            }
            if(!StrUtil.isEmpty(b)) {
                selectPattern.append(b);
            }
        }
    }
    /**
     * findObject(), searchObjects() 이용 시 사용되는 fromPattern을 구성한다.
     *
     * @param fromPattern
     * @param leftClass
     * @param leftAlias
     * @param connectionType
     * @param rightClass
     * @param rightAlias
     */
    public static void constructFromPattern(StringBuffer fromPattern, String leftClass, String leftAlias, String connectionType, String rightClass, String rightAlias ){
        String leftClassStr  = "";
        String rightClassStr = "";
        if (leftClass.equals("this")){
            leftClassStr = "<" + leftClass + ">";
        }else{
            leftClassStr = "<[" + leftClass + "]@" + leftAlias + ">";
        }
        if (rightClass.equals("this")){
            rightClassStr = "<" + rightClass + ">";
        }else{
            rightClassStr = "<[" + rightClass + "]@" + rightAlias + ">";
        }
        if (fromPattern.length() > 0) {
            fromPattern.append("+");
        }
        fromPattern.append(leftClassStr).append(connectionType).append(rightClassStr);
    }
    
    /**
     * findObject(), searchObjects() 이용 시 사용되는 wherePattern을 구성한다.
     *
     * @param wherePattern
     * @param paramPattern
     * @param attrName
     * @param oqlOperator
     * @param attrValue
     */
    public static void constructWherePattern(StringBuffer wherePattern, StringBuffer paramPattern, String attrName,
            String oqlOperator, String attrValue){
        String generatedAttrName = EtcUtil.getParamter();
        if (wherePattern.length() > 0) {
            wherePattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
        }
        wherePattern.append(attrName.trim());
        wherePattern.append(oqlOperator);
        wherePattern.append("<%").append(generatedAttrName).append("%>");

        String newAttrValue = null;
        if (oqlOperator.equals(GlobalConstants.OQL_OPERATOR_IN)
                || oqlOperator.equals(GlobalConstants.OQL_OPERATOR_NOT_IN)) {
            newAttrValue = "('" + attrValue.replace(",", "','") + "')";
        } else {
            if(OmcUtility.isArributeFormat(attrValue)){
                newAttrValue = attrValue;
            }else{
                newAttrValue = "'" + attrValue + "'";
            }
        }

        StringUtil.constructParamString(paramPattern, generatedAttrName, newAttrValue);
    }
    /**
     * Select Pattern에 Parameter 처리하여 추가함.
     *
     * @param selectPattern
     * @param wherePattern
     * @param paramPattern
     * @param alias
     * @param selectValue
     */
    public static void constructSelectParameterPattern(StringBuffer selectPattern, StringBuffer wherePattern, StringBuffer paramPattern, String alias, String selectValue){
        String generatedAttrName = EtcUtil.getParamter();
        constructSelectPattern(selectPattern,"<%" + generatedAttrName+ "%> " + alias);
        if (wherePattern.length() > 0) {
            wherePattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME );
        }
        wherePattern.append("<%").append(generatedAttrName).append("%>");
        wherePattern.append(GlobalConstants.OQL_OPERATOR_EQUAL);
        wherePattern.append("<%").append(generatedAttrName).append("%>");
        StringUtil.constructParamString(paramPattern, generatedAttrName, selectValue);
    }
    
    public static void constructSelectParameterPattern(StringBuffer selectPattern, StringBuffer wherePattern, StringBuffer paramPattern, String alias, String functionStr, String... selectValueList){
        
        for(int i = 0; i < selectValueList.length; i++){
            String generatedAttrName = EtcUtil.getParamter();
            StringUtil.constructParamString(paramPattern, generatedAttrName, selectValueList[i]);
            functionStr = functionStr.replace("#" + (i+1), "<%" + generatedAttrName + "%>");
            if (wherePattern.length() > 0) {
                wherePattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
            }
            wherePattern.append("<%").append(generatedAttrName).append("%>");
            wherePattern.append(GlobalConstants.OQL_OPERATOR_EQUAL);
            wherePattern.append("<%").append(generatedAttrName).append("%>");
        }
        functionStr = functionStr + " " + alias;
        constructSelectPattern(selectPattern,functionStr);
    }
    public static void constructBetweenDateWherePattern(StringBuffer wherePattern, StringBuffer paramPattern, String attrName, String fromDate, String toDate){
        fromDate = OmcSystemConstants.OMC_DATE_CONVERT_PREFIX + fromDate + " 00:00:00";
        toDate   = OmcSystemConstants.OMC_DATE_CONVERT_PREFIX + toDate   + " 23:59:59";
        StringUtil.constructWherePattern(wherePattern, paramPattern, attrName, GlobalConstants.OQL_OPERATOR_GREATER_EQTHAN, fromDate);
        StringUtil.constructWherePattern(wherePattern, paramPattern, attrName, GlobalConstants.OQL_OPERATOR_LESS_EQTHAN, toDate);
    }
    /**
     * 여러 조건에 대한 Or Pattern을 만듬.
     *
     * @param wherePattern
     * @param paramPattern
     * @param conditionList
     */
    public static void constructOrWherePattern(StringBuffer wherePattern, StringBuffer paramPattern, List<OmcOQLCondition> conditionList){
        StringBuffer rsltWherePattern = new StringBuffer();
        StringBuffer rsltParamPattern = new StringBuffer();
        boolean added = false;
        if(!NullUtil.isNone(conditionList)){
            for(OmcOQLCondition condition : conditionList){
                StringBuffer tempWherePattern = new StringBuffer();
                StringBuffer tempParamPattern = new StringBuffer();
                constructWherePattern(tempWherePattern,tempParamPattern,condition.getLeftValue(),condition.getOperator(),condition.getRightValue());
                if(rsltWherePattern.length() > 0) rsltWherePattern.append(" ").append(GlobalConstants.OQL_OPERATOR_OR).append(" ");
                rsltWherePattern.append("(").append(tempWherePattern.toString()).append(")");
                if(rsltParamPattern.length() > 0 ) {
                    rsltParamPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
                }
                rsltParamPattern.append(tempParamPattern);
                added = true;
            }
        }
        if(added){
            if (!StrUtil.isEmpty(wherePattern)) wherePattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
            if (!StrUtil.isEmpty(paramPattern)) paramPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
            wherePattern.append("(").append(rsltWherePattern).append(")");
            paramPattern.append(rsltParamPattern);
        }
    }
    /**
     * constructWherePattern로 별도 만드어진 Where Pattern(일반적으로 Or조건을 만든것)을 WherePattern에 추가 하는 기능
     *
     * @param wherePattern
     * @param addedWhere
     */
    public static void constructOrWherePattern(StringBuffer wherePattern,  String addedWhere){
        wherePattern.append(" ").append(GlobalConstants.OQL_OPERATOR_AND).append(" ").append(addedWhere);
    }
    /**
     * N개의 Where Pattern을 가지고 Or조건 하나의 Where Pattern을 만듬
     *
     * @param whereString1
     * @param whereString2
     * @return
     */
    public static String makeOrPattern(String ... whereString){
        StringBuffer rsltWherePattern = new StringBuffer("(");
        boolean isFirst = true;
        for(int i = 0; i < whereString.length ; i++){
            if(!isFirst) rsltWherePattern.append(" ").append(GlobalConstants.OQL_OPERATOR_OR);
            rsltWherePattern.append("(").append(whereString[i]).append(")");
            isFirst = false;
        }
        rsltWherePattern.append(")");
        return rsltWherePattern.toString();
    }
    /**
     * N개의 Where Pattern을 가지고 And조건 하나의 Where Pattern을 만듬
     *
     * @param whereString1
     * @param whereString2
     * @return
     */
    public static String makeAndPattern(String ... whereString){
        StringBuffer rsltWherePattern = new StringBuffer("(");
        boolean isFirst = true;
        for(int i = 0; i < whereString.length ; i++){
            if(!isFirst) rsltWherePattern.append(" ").append(GlobalConstants.OQL_OPERATOR_AND);
            rsltWherePattern.append("(").append(whereString[i]).append(")");
            isFirst = false;
        }
        rsltWherePattern.append(")");
        return rsltWherePattern.toString();
    }
    public static void addSortByPattern(StringBuffer selectPattern,String attrName){
        if(!NullUtil.isNone(attrName)){
            if (selectPattern.length() > 0) {
                selectPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE);
            }
            if(!NullUtil.isNone(attrName) && !NullUtil.isNone(attrName.trim())) selectPattern.append(GlobalConstants.OQL_DBMS_ORDERBY).append(attrName.trim());
        }
    }
    /**
     * OQL 이용 시, 사용되는 ParamString을 구성한다.
     *
     * @param paramPattern
     * @param attrName
     * @param attrValue
     */
    public static void constructParamString(StringBuffer paramPattern, String attrName, String attrValue){
        if (paramPattern.length() > 0) {
            paramPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_NAME);
        }
        paramPattern.append(attrName);
        paramPattern.append(OmcSystemConstants.ATTRIBUTE_DELIMINATOR_VALUE);
        paramPattern.append(attrValue);
    }

    /**
     * overring 된 toString() 에서 해당 object 의 address 를 표시하기 위하여 Object.toString() 코드를 가져 옴
     * 
     * @param object
     * @return Object.toString() 과 동일한 address
     */
    public static String getObjectAddress(Object object){
        return object.getClass().getName() + "@" + Integer.toHexString(object.hashCode());
    }

    public static String setEmptyExt(String arg){
        if (StringUtils.isEmpty(arg)) {
            return "";
        } else {
            return arg.trim();
        }
    }

    public static String setEmpty(String str){
        String ret = "";
        if (str != null) {
            ret = str.trim();
        }
        return ret;
    }
    
    /**
     * &gt; : < 
     * &lt; : >
     * 변환 
     * @param orgStr
     * @return
     */
    public static String getStringExceptGtLt(String orgStr){
        orgStr = orgStr.replaceAll("&gt;", ">");
        orgStr = orgStr.replaceAll("&lt;", "<");
        return orgStr;
    }
    /**
     * HTML Tag를 제외한 문자열 반환
     * @param orgStr
     * @return
     */
    public static String getStringExceptHtmlTag(String orgStr){
        orgStr =  orgStr.replaceAll("</div>","\r\n");
        orgStr =  orgStr.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
        orgStr = orgStr.replaceAll("&nbsp;", "");
        orgStr = orgStr.replaceAll("&gt;", ">");
        orgStr = orgStr.replaceAll("&lt;", "<");
        orgStr = orgStr.replaceAll("&quot;", "\"");
        orgStr = orgStr.replaceAll("&amp;", "&");
        return orgStr;
    }

    public static String extractTextFromHTML( String htmlString ){
        String result = null;
        Source source = new Source( htmlString );
        Tag[] tags = source.fullSequentialParse();
        
        if( tags.length > 0 ){
            Renderer renderer = source.getRenderer();
            renderer.setIncludeHyperlinkURLs( false );
            renderer.setIncludeAlternateText( false );
            renderer.setDecorateFontStyles( false );
            renderer.setMaxLineLength( Integer.MAX_VALUE );
            renderer.setBlockIndentSize( 4 );
            renderer.setConvertNonBreakingSpaces( false );
            renderer.setNewLine( "\n" );
            result = renderer.toString();
        }
        else{
            result = "";
        }
        return result;
    }
    
    /**
     * Exception Stack Trace 내용을 문자열로 추출하여 반환
     *
     * @param exeption
     * @return
     */
    public static String getStackTraceString( Exception exeption ){
        StringBuffer sb = new StringBuffer();
        
        for( StackTraceElement element : exeption.getStackTrace() ){
            sb.append( element.toString() );
            sb.append( "\n" );
        }
        
        return sb.toString().length() > 4000 ? StringUtil.substring(sb.toString(), 4000) : sb.toString();
    }
    
    public static String convertYn(String ynStr)
    {
    	if (ynStr == null) return "";
        if (ynStr.trim().toUpperCase().equals("Y")) return "예";
        if (ynStr.trim().toUpperCase().equals("N")) return "아니오";
        return "";
    }
    public static String insert(String string, int location, String add)
    {
    	String result = null;
        int insertPosition = location;

        StringBuilder strBuf = new StringBuilder();
        int length = string.length();
        if (insertPosition >= 0) {
        if (length < insertPosition) {
        	insertPosition = length;
        }
                strBuf.append(string.substring(0, insertPosition));
                strBuf.append(add);
                strBuf.append(string.substring(insertPosition));
                } else {
                if (length < Math.abs(insertPosition)) {
                    insertPosition = length * -1;
                    }
                strBuf.append(string.substring(0, length - 1 + insertPosition));
                strBuf.append(add);
                strBuf.append(string.substring(length - 1 + insertPosition));
                }
            result = strBuf.toString();
            
            return result;
            }

        
        public static String insertAndOverwrite(String string, int location, String add)
        {
            int insertPosition = location;
            String result = null;
            
            StringBuilder strBuf = new StringBuilder();
            int lengthSize = string.length();
            if (insertPosition >= 0) {
                if (lengthSize < insertPosition) {
                    insertPosition = lengthSize;
                    }
                strBuf.append(string.substring(0, insertPosition));
                strBuf.append(add);
                strBuf.append(string.substring(insertPosition + add.length()));
                } else {
                if (lengthSize < Math.abs(insertPosition)) {
                    insertPosition = lengthSize * -1;
                    }
                strBuf.append(string.substring(0, lengthSize - 1 + insertPosition));
                strBuf.append(add);
                strBuf.append(string.substring(lengthSize - 1 + insertPosition + add.length()));
                }
            result = strBuf.toString();
            
            return result;
            }

        
        public static String delete(String source, String pattern)
        {
            return replace(source, pattern, "");
            }

        
        public static String replace(String source, String pattern, String replacement)
        {
            StringBuilder rtnStr = new StringBuilder();
            String preStr = "";
            String nextStr = source;
            String srcStr = source;
            
            while (srcStr.indexOf(pattern) >= 0) {
                preStr = srcStr.substring(0, srcStr.indexOf(pattern));
                nextStr = srcStr.substring(srcStr.indexOf(pattern) + pattern.length(), srcStr.length());
                srcStr = nextStr;
                rtnStr.append(preStr).append(replacement);
                }
            rtnStr.append(nextStr);
            return rtnStr.toString();
        }
        public static String[] split(String string, String separator)/*      */ throws NullPointerException
        {
            if (string == null) return new String[0];
            if ((separator == null) || (separator.length() == 0)) return new String[] { string };
            
            String[] returnVal = null;
            int cnt = 1;
            
            int index = string.indexOf(separator);
            int index0 = 0;
            while (index >= 0) {
                ++cnt;
                index = string.indexOf(separator, index + 1);
                }
            returnVal = new String[cnt];
            cnt = 0;
            index = string.indexOf(separator);
            while (index >= 0) {
                returnVal[cnt] = string.substring(index0, index);
                index0 = index + 1;
                index = string.indexOf(separator, index + 1);
                ++cnt;
                }
            returnVal[cnt] = string.substring(index0);
            
            return returnVal;
            }

        
        
		public static String[] split(String source, String separator, boolean containNull)
        {
            ArrayList matchList = new ArrayList();
            
            Pattern regex = Pattern.compile(separator);
            Matcher matcher = regex.matcher(source);
            
            int inx = 0;
            while (matcher.find()) {
                String match = source.subSequence(inx, matcher.start()).toString();
                matchList.add(match);
                inx = matcher.end();
                }
            
            if (inx == 0) return new String[] { source };
            
            matchList.add(source.subSequence(inx, source.length()).toString());
            
            int resultSize = matchList.size();
            if (!(containNull)) {
                for (int jnx = 0; jnx < matchList.size(); ++jnx) {
                    if (((String)matchList.get(jnx)).equals("")) {
                        --resultSize;
                        }
                    }
                }
            
            String[] returnVal = new String[resultSize];
            return ((String[])matchList.subList(0, resultSize).toArray(returnVal));
            }

        
        public static String[] split(String source, String separator, int arraylength)
                throws NullPointerException
        {
            String[] returnVal = new String[arraylength];
            int cnt = 0;
            int index0 = 0;
            int index = source.indexOf(separator);
            while ((index >= 0) && (cnt < arraylength - 1)) {
                returnVal[cnt] = source.substring(index0, index);
                index0 = index + 1;
                index = source.indexOf(separator, index + 1);
                ++cnt;
                }
            returnVal[cnt] = source.substring(index0);
            if (cnt < arraylength - 1) {
                for (int inx = cnt + 1; inx < arraylength; ++inx) {
                    returnVal[inx] = "";
                    }
                }
            
            return returnVal;
            }

        
        public static String[] splitByBytes(String source, int cutLength, String encoding)
                throws UnsupportedEncodingException
        {
            if (source == null) return null;
            
            String[] resultStrArray = null;
            
            byte[] byteStr = source.getBytes(encoding);
            int byteLength = byteStr.length;
            int koreanByteLength = (encoding.equalsIgnoreCase("UTF-8")) ? 3 : 2;
            
            int index = 0;
            int targetBytelength = 0;
            int offset = 0;
            
            if (byteLength > cutLength) {
                int arrayLength = byteLength / cutLength + ((byteLength % cutLength == 0) ? 0 : 1);
                resultStrArray = new String[arrayLength];
                
                for (int inx = 0; inx < arrayLength; ++inx) {
                    targetBytelength = 0;
                    offset = cutLength;
                    
                    if (index + offset > byteStr.length) {
                        offset = byteStr.length - index;
                        }
                    
                    for (int jnx = 0; jnx < offset; ++jnx) {
                        if ((byteStr[(index + jnx)] & 0x80) != 0) {
                            ++targetBytelength;
                            }
                        }
                    
                    if (targetBytelength % koreanByteLength != 0) {
                        offset -= targetBytelength % koreanByteLength;
                        }
                    
                    resultStrArray[inx] = new String(byteStr, index, offset, encoding);
                    index += offset;
                    }
                } else {
                resultStrArray = new String[] { source };
                }
            
            return resultStrArray;
            }

        
        public static int search(String source, String search)
        {
            int result = 0;
            String strCheck = source;
            for (int inx = 0; inx < source.length();) {
                 int loc = strCheck.indexOf(search);
                 if (loc == -1) {
                    break;
                    }
                ++result;
                inx = loc + search.length();
                strCheck = strCheck.substring(inx);
                }
            
            return result;
            }

        
        public static String replaceEscapingDollarSign(String source)
        {
            return replace(source, "$", "\\$");
            }

        
        public static String token(String source, String separator, int index)
        {
            if (source == null) return null;
            StringTokenizer stringTokenizer = new StringTokenizer(source, separator);
            int inx = 0;
            while (stringTokenizer.hasMoreTokens()) {
                if (inx++ == index) {
                    return stringTokenizer.nextToken();
                    }
                stringTokenizer.nextToken();
                }
            
            return null;
            }

        
        public static String[] divide(String source, String separator)
        {
            if (source == null) return new String[] { "", "" };
            if ((separator == null) || (separator.length() == 0)) return new String[] { source, "" };
            int idx = source.indexOf(separator);
            if (idx < 0) {
                return new String[] { source, "" };
                }
            return new String[] { source.substring(0, idx), source.substring(idx + separator.length()) };
            }

        
        public static String toLowerCaseFirstLetter(String source)
        {
            if ((source == null) || (source.length() < 1)) return source;
            return source.substring(0, 1).toLowerCase() + source.substring(1);
            }

        
        public static String encodeBase64(Object obj)/*      */ throws IOException
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return new Base64().encodeToString(baos.toByteArray());
            }

        
        public static Object decodeBase64(String text)/*      */ throws IOException, ClassNotFoundException
        {
            ByteArrayInputStream bais = new ByteArrayInputStream(new Base64().decode(text));
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
            }

        
        public static String leftPad(String source, byte pad, int length)
        {
            String sourceStr = source;
            if (sourceStr == null) sourceStr = "";
            byte[] byteString;
            try {
                byteString = sourceStr.getBytes("ksc5601");
                } catch (Exception e) {
                return sourceStr;
                }
            if (length <= byteString.length) return sourceStr;
            byte[] chs = new byte[length];
            int jnx = length - 1;
            for (int inx = byteString.length - 1; inx >= 0; --inx) {
                chs[(jnx--)] = byteString[inx];
                }
            for (; jnx >= 0; --jnx)
                chs[jnx] = pad;
            try
            {
                return new String(chs, "ksc5601");
            } catch (Exception e) {
                }
            return sourceStr;
            }

        
        public static String rightPad(String source, byte pad, int length)
        {
            String sourceStr = source;
            if (sourceStr == null) sourceStr = "";
            byte[] byteString;
            try {
                byteString = sourceStr.getBytes("ksc5601");
                } catch (Exception e) {
                return sourceStr;
                }
            if (length <= byteString.length) return sourceStr;
            byte[] chs = new byte[length];
            int jnx = 0;
            for (int inx = 0; inx < byteString.length; ++inx) {
                chs[(jnx++)] = byteString[inx];
                }
            for (; jnx < length; ++jnx)
                chs[jnx] = pad;
            try
            {
                return new String(chs, "ksc5601");
            } catch (Exception e) {
                }
            return sourceStr;
            }

        
        public static String strip(String string, String separator)
        {
            if ((string == null) || (string.length() == 0) || (separator == null)) return string;
            StringBuilder buf = new StringBuilder();
            StringTokenizer st = new StringTokenizer(string, separator);
            while (st.hasMoreTokens()) {
                buf.append(st.nextToken());
                }
            return buf.toString();
            }

       
        public static String stripHyphen(String string)
        {
            String changeStr = string;
            if (changeStr == null) return null;
            while (true) {
                int index = changeStr.indexOf("-");
                if (index < 0) break;
                changeStr = changeStr.substring(0, index) + changeStr.substring(index + 1);
                }
            
            return changeStr;
            }

        
        public static String toUpperCaseFirstLetter(String source)
        {
            if ((source == null) || (source.length() < 1)) return source;
            return source.substring(0, 1).toUpperCase() + source.substring(1);
            }

        
        public static String arrayToString(String[] values, String separator)
        {
            StringBuilder sbf = new StringBuilder();
            if ((values == null) || (values.length < 1)) return "";
            sbf.append(values[0]);
            for (int inx = 1; inx < values.length; ++inx) {
                sbf.append(separator).append(values[inx]);
                }
            return sbf.toString();
            }

        
        public static String java2mysql(String string)
        {
            if (string == null) return null;
            StringBuilder buf = new StringBuilder();
            char[] cha = string.toCharArray();
            int len = cha.length;
            for (int inx = 0; inx < len; ++inx) {
                if (cha[inx] == '\n') buf.append("\\n");
                else if (cha[inx] == '\t') buf.append("\\t");
                else if (cha[inx] == '\r') buf.append("\\r");
                else if (cha[inx] == '\'') buf.append("\\'");
                else if (cha[inx] == '"') buf.append("\\\"");
                else if (cha[inx] == '%') buf.append("\\%");
                else {
                    buf.append(cha[inx]);
                    }
                }
            return buf.toString();
            }

        
        public static String java2alert(String string)
        {
            if (string == null) return null;
            StringBuilder buf = new StringBuilder();
            char[] cha = string.toCharArray();
            int len = cha.length;
            for (int inx = 0; inx < len; ++inx) {
                if (cha[inx] == '\n') buf.append("\\n");
                else if (cha[inx] == '\t') buf.append("\\t");
                else if (cha[inx] == '"') buf.append("'");
                else {
                    buf.append(cha[inx]);
                    }
                }
            return buf.toString();
            }

        
        public static String java2msg(String string)
        {
            if (string == null) return null;
            StringBuilder buf = new StringBuilder();
            char[] cha = string.toCharArray();
            int len = cha.length;
            for (int inx = 0; inx < len; ++inx) {
                if (cha[inx] == '\n')/*  646 */ buf.append("\\n");
                else if (cha[inx] == '\t')/*  648 */ buf.append("\\t");
                else {
                    buf.append(cha[inx]);
                    }
                }
            return buf.toString();
            }

        
        public static String java2html(String string)
        {
            if (string == null) return null;
            StringBuilder buf = new StringBuilder();
            char[] cha = string.toCharArray();
            int len = cha.length;
            for (int inx = 0; inx < len; ++inx) {
                if (cha[inx] == '&') buf.append("&amp;");
                else if (cha[inx] == '<') buf.append("&lt;");
                else if (cha[inx] == '>') buf.append("&gt;");
                else if (cha[inx] == '"') buf.append("&quot;");
                else if (cha[inx] == '\'') buf.append("&#039;");
                else {
                    buf.append(cha[inx]);
                    }
                }
            return buf.toString();
            }

        
        public static String toOneLine(String string)
        {
            if (string == null) {
                return null;
                }
            return string.replace('\n', ' ');
            }

        
        public static String print(String string, String format)
        {
            if (string == null) return "";
            if (format == null) return string;
            
            int jnx = 0;
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < format.length(); ++i) {
                if (format.charAt(i) == '#') {
                    if (jnx >= string.length()) return buf.toString();
                    buf.append(string.charAt(jnx++));
                    } else {
                    buf.append(format.charAt(i));
                    }
                }
            return buf.toString();
            }

        
        public static String print(Date date, String format, Locale locale)
        {
            if (date == null) return "";
            if ((format == null) || (locale == null)) return date.toString();
            
            return new SimpleDateFormat(format, locale).format(date);
            }

        
        public static String print(Number number, String format)
        {
            if (number == null) return "";
            if (format == null) return number.toString();
            
            return new DecimalFormat(format).format(number);
            }

        
        public static String getCalcStr(String source, int strat, int end)
        {
            String rltStr = "";
            try {
                rltStr = new String(source.getBytes(), strat, end - strat);
                } catch (Exception e) {
                return source;
                }
            return rltStr;
            }

        
        public static String makeRepeatString(String string, int count)
        {
            StringBuilder resultStr = new StringBuilder();
            for (int inx = 0; inx < count; ++inx) {
                resultStr.append(string);
                }
            return resultStr.toString();
            }

        
        public static String escapeBackslashAndDollarSign(String string)
        {
            if ((string.indexOf(92) == -1) && (string.indexOf(36) == -1)) return string;
            
            StringBuilder builder = new StringBuilder();
            for (int inx = 0; inx < string.length(); ++inx) {
                char cha = string.charAt(inx);
                if (cha == '\\')/*  803 */ builder.append('\\').append('\\');
                else if (cha == '$')/*  805 */ builder.append('\\').append('$');
                else {
                    builder.append(cha);
                    }
                }
            return builder.toString();
            }

        
        public static String escapeBackslash(String string)
        {
            return escapeCharacter(string, '\\');
            }

        
        public static String escapeDollarSign(String string)
        {
            return escapeCharacter(string, '$');
            }

        
        public static String escapeCharacter(String string, char add)
        {
            if (string.indexOf(add) == -1) return string;
            StringBuilder buf = new StringBuilder();
            for (int i = 0; i < string.length(); ++i) {
                char c = string.charAt(i);
                if (c == add) buf.append('\\').append(add);
                else {
                    buf.append(c);
                    }
                }
            return buf.toString();
            }

        
        public static String insertDelimToString(String string, char add)
        {
            StringBuilder buf = new StringBuilder();
            
            char[] chars = string.toCharArray();
            for (int inx = 0; inx < chars.length; ++inx) {
                if (inx == 0) {
                    if (chars[inx] == add)/*  868 */ buf.append(add).append(chars[inx]);
                    else/*  870 */ buf.append(chars[inx]);
                    }
                else if (inx == chars.length - 1) {
                    if (chars[(inx - 1)] == add)/*  874 */ buf.append(add).append(chars[inx]);
                    else {
                        buf.append(chars[inx]);
                        }
                    }
                else if ((chars[inx] == add) && (chars[(inx - 1)] != add))
                    buf.append(add).append(chars[inx]);
                else {
                    buf.append(chars[inx]);
                    }
                
                }
            
            return buf.toString();
            }

        
        public static String getStackTrace(Throwable cause)
        {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PrintStream print = new PrintStream(out);
            cause.printStackTrace(print);
            return out.toString();
        }
        public static String toLowerCase(String string)
        {
            if (string == null) return null;
            return string.toLowerCase();
        }
        public static String toUpperCase(String string)
        {
            if (string == null) return null;
            return string.toUpperCase();
        }

        
        public static String trim(String string)
        {
            if (string == null) return null;
            return string.trim();
            }

        
        public static int compareTo(String string, String anotherString)
        {
            if ((string == null) || (anotherString == null)) return -1;
            return string.compareTo(anotherString);
            }

        
        public static int compareToIgnoreCase(String string, String anotherString)
        {
            if ((string == null) || (anotherString == null)) return -1;
            return string.compareToIgnoreCase(anotherString);
            }

        
        public static String objectToString(Object object)
        {
            String string = "";
            
            if (object != null) {
                string = object.toString().trim();
                }
            
            return string;
            }

        
        public static String remove(String string, char remove)
        {
            if ((NullUtil.isNone(string)) || (string.indexOf(remove) == -1)) return string;
            char[] chars = string.toCharArray();
            int pos = 0;
            for (int i = 0; i < chars.length; ++i) {
                if (chars[i] != remove) {
                    chars[(pos++)] = chars[i];
                    }
                }
            return new String(chars, 0, pos);
            }

        
        public static String removeCommaChar(String string)
        {
            return remove(string, ',');
            }

        
        public static String removeMinusChar(String string)
        {
            return remove(string, '-');
            }

        
        public static String removeWhitespace(String string)
        {
            if (NullUtil.isNone(string)) return string;
            int length = string.length();
            char[] chs = new char[length];
            int count = 0;
            for (int inx = 0; inx < length; ++inx) {
                if (!(Character.isWhitespace(string.charAt(inx)))) {
                    chs[(count++)] = string.charAt(inx);
                    }
                }
            if (count == length) return string;
            
            return new String(chs, 0, count);
            }

        
        public static String substringThenAddString(String source, int length, String add)
        {
            String returnVal = null;
            if (source != null) {
               if (source.length() > length)/* 1065 */ returnVal = source.substring(0, length) + add;
               else {
                    returnVal = source;
                    }
                }
            return returnVal;
            }

        
        public static String substringByBytes(String string, int length)
        {
            String changeStr = string;
            if (changeStr == null) return changeStr;
            
            int stringLength = changeStr.length();
            int count = 0;
            int index = 0;
            
            while ((index < stringLength) && (count < length))
            {
                if (changeStr.charAt(index++) < 256) {
                    ++count;
                    }
                else {
                    count += 2;
                    }
                }
            
            if (index < stringLength) changeStr = changeStr.substring(0, index);
            
            return changeStr;
            }

        
        public static String substring(String source, int length)
        {
            return substringThenAddString(source, length, "");
            }

        
        public static String format(String format, String value, boolean leftAlign)
        {
            if (!(leftAlign)) return formatWithRightAlign(format, value);
           
            StringBuilder buf = new StringBuilder();
            int index = 0;
            for (int inx = 0; inx < format.length(); ++inx) {
                 if (format.charAt(inx) == '#') {
                     if (index < value.length()) {
                         buf.append(value.charAt(index++));
                         }
                     }
                 else if (format.charAt(inx) == '\\')
                    if ((inx + 1 < format.length()) && (format.charAt(inx + 1) == '#')) {
                    buf.append("#");
                    ++inx;
                    } else if ((inx + 1 < format.length()) && (format.charAt(inx + 1) == '\\')) {
                    buf.append("\\");
                    ++inx;
                    } else {
                    buf.append(format.charAt(inx));
                    }
                else {
                    buf.append(format.charAt(inx));
                    }
                }
            
            return buf.toString();
            }

        
        private static String formatWithRightAlign(String format, String string){
            StringBuilder buf = new StringBuilder();
            int index = string.length() - 1;
            for (int inx = format.length() - 1; inx >= 0; --inx) {
                if (format.charAt(inx) == '#') {
                    if ((inx - 2 >= 0) && (format.charAt(inx - 1) == '\\') && (format.charAt(inx - 2) != '\\')) {
                        buf.insert(0, "#");
                        --inx;
                        }
                    else if (index >= 0) {
                        buf.insert(0, string.charAt(index--));
                        }
                    
                    }
                else if (format.charAt(inx) == '\\')
                    if ((inx - 1 >= 0) && (format.charAt(inx - 1) == '\\')) {
                    buf.insert(0, "\\");
                    --inx;
                    } else {
                    buf.insert(0, format.charAt(inx));
                    }
                else {
                    buf.insert(0, format.charAt(inx));
                    }
                }
            
            return buf.toString();
            }
        }
    

