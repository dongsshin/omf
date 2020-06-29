/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : CommonUtil.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 1. 30.  hyeyoung.park   Initial
 * ===========================================
 */
package com.rap.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.api.util.omc.ThreadLocalUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.CodeDetailVO;
import rap.api.object.common.model.CodeMasterVO;
import rap.api.object.organization.model.PlantUnitVO;
import rap.api.object.organization.model.UsersVO;
import rap.application.constants.ApplicationSchemaConstants;


/**
 * <pre>
 * Class : CommonUtil
 * Description : TODO
 * </pre>
 *
 * @author hyeyoung.park
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class CommonUtil {

    /**
     * request IP를 반환한다.
     */
    public static String getRequestIp(HttpServletRequest req){
        // TODO : L4 Switch 도입 시 변경 필요함
        return req.getRemoteAddr();
    }

    /**
     * Code Master Test Code
     *
     * @param codeList
     * @return
     */
    public static String getValueMap(List<CodeDetailVO> codeList) {
        StringBuffer sb = new StringBuffer();
		for(int i = 0; i < codeList.size()  ; i++)
		{
			sb.append("\"");
			sb.append(codeList.get(i).getNames());
			sb.append("\" : \"");
			sb.append(codeList.get(i).getDescriptions());
			sb.append("\"");
			if(i != (codeList.size() -1))
			{
				sb.append(",");
			}
		}
		return sb.toString();
    }

    public static String getValueMap(List<HashMap> resultList, boolean flag) {
        StringBuffer sb = new StringBuffer();
        sb.append("[ {");
		for(int i = 0; i < resultList.size()  ; i++)
		{
			Object[] keySet = (Object[])resultList.get(i).entrySet().toArray();
			for(int j = 0; j < keySet.length  ; j++){
				sb.append("\"");

				sb.append(keySet[j].toString());

				sb.append("\" : \"");

				sb.append(resultList.get(i).get(keySet[j].toString()));

				sb.append("\"");
			}
			if(i != (resultList.size() -1))
			{
				sb.append(",");
			}
		}
		sb.append("} ]");
		return sb.toString();
    }

    public static String getDivisionFromPlant(String plantName)
    {
        if( !StrUtil.isEmpty(plantName) ){
            return plantName.substring(0,3);
        }else{
            return "";
        }
    }

    /**
     * Code Display 문자열 조회
     * @param codeValue
     * @param codeMasterNames
     * @return
     */
    public static String getCodeDisplay(String codeValue, String codeMasterNames) throws ApplicationException{
        List<CodeDetailVO> codeDetailList = null;
        CodeMasterVO codeMasterVO = null;
        String codeScope = "";
        String codeDisplay = "";

        if( NullUtil.isNull(codeValue) ){
            return "";
        }

        List<CodeMasterVO> codeMasterList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEMASTER,
                codeMasterNames,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
        );

        if( codeMasterList != null && codeMasterList.size() > 0 && codeMasterList.get(0) != null ){
            codeMasterVO = codeMasterList.get(0);

            // From 설정
            StringBuffer fromPattern = new StringBuffer();
            fromPattern.append("<this>ThisConnectedWithTo<[CodeMaster2Code]@M2D>+");
            fromPattern.append("<[CodeMaster2Code]@M2D>FromConnectedWithThis<[CodeMaster]@MASTER>+");
            fromPattern.append("<this>ThisConnectedWithFrom<[Code2Organization]@D2O>+");
            fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis<[Organizations]@ORG>+");

            // Where 조건 설정
            if( ApplicationSchemaConstants.BIZCLASS_COMPANY.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = "LGE";
            }
            else if( ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.businessUnit, "-");
            }
            else if( ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, "-");
            }
            else if( ApplicationSchemaConstants.BIZCLASS_PLANTUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.plantUnit, "-");
            }

            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeValue);

            codeDetailList = ObjectRoot.searchObjects(
                    ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    false,
                    false,
                    false,
                    false,
                    null,
                    fromPattern.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    true,
                    0
            );

            if( codeDetailList != null){
                if( codeDetailList.size() > 1 ){
                    throw new ApplicationException("plm.common.code.error.dupdata", new Object[] { codeDetailList.size(), codeMasterNames, codeValue });
                }
                else if( codeDetailList.size() == 1 ){
                    if( codeDetailList.get(0) != null ){
                        codeDisplay = codeDetailList.get(0).getTitles();
                    }
                    else{
                        throw new ApplicationException("plm.common.code.error.nodata", new Object[] { codeMasterNames, codeValue });
                    }
                }
                else{
                    codeDisplay = codeValue;
                }
            }
            else{
                codeDisplay = codeValue;
            }
        }

        return codeDisplay;
    }

    /**
     * Code Display 문자열 조회
     * @param codeValue
     * @param codeMasterNames
     * @return
     */
    public static String getCodeDisplay(String codeValue, String codeMasterNames, String codeScope) throws ApplicationException{
        List<CodeDetailVO> codeDetailList = null;
        CodeMasterVO codeMasterVO = null;
        String codeDisplay = "";

        if( NullUtil.isNull(codeValue) ){
            return "";
        }

        List<CodeMasterVO> codeMasterList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEMASTER,
                codeMasterNames,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
        );

        if( codeMasterList != null && codeMasterList.size() > 0 && codeMasterList.get(0) != null ){
            codeMasterVO = codeMasterList.get(0);

            // From 설정
            StringBuffer fromPattern = new StringBuffer();
            fromPattern.append("<this>ThisConnectedWithTo<[CodeMaster2Code]@M2D>+");
            fromPattern.append("<[CodeMaster2Code]@M2D>FromConnectedWithThis<[CodeMaster]@MASTER>+");
            fromPattern.append("<this>ThisConnectedWithFrom<[Code2Organization]@D2O>+");
            fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis<[Organizations]@ORG>+");

            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeValue);

            codeDetailList = ObjectRoot.searchObjects(
                    ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    false,
                    false,
                    false,
                    false,
                    null,
                    fromPattern.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    true,
                    0
            );

            if( codeDetailList != null){
                if( codeDetailList.size() > 1 ){
                    throw new ApplicationException("plm.common.code.error.dupdata", new Object[] { codeDetailList.size(), codeMasterNames, codeValue });
                }
                else if( codeDetailList.size() == 1 ){
                    if( codeDetailList.get(0) != null ){
                        codeDisplay = codeDetailList.get(0).getTitles();
                    }
                    else{
                        throw new ApplicationException("plm.common.code.error.nodata", new Object[] { codeMasterNames, codeValue });
                    }
                }
                else{
                    codeDisplay = codeValue;
                }
            }
            else{
                codeDisplay = codeValue;
            }
        }

        return codeDisplay;
    }

    /**
     * Code Display 문자열 조회
     * @param codeValue
     * @param codeMasterNames
     * @return
     */
    public static String getCodeDescription(String codeValue, String codeMasterNames) throws ApplicationException{
        List<CodeDetailVO> codeDetailList = null;
        CodeMasterVO codeMasterVO = null;
        String codeScope = "";
        String codeDisplay = "";

        if( NullUtil.isNull(codeValue) ){
            return "";
        }

        List<CodeMasterVO> codeMasterList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEMASTER,
                codeMasterNames,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
        );

        if( codeMasterList != null && codeMasterList.size() > 0 && codeMasterList.get(0) != null ){
            codeMasterVO = codeMasterList.get(0);

            // From 설정
            StringBuffer fromPattern = new StringBuffer();
            fromPattern.append("<this>ThisConnectedWithTo<[CodeMaster2Code]@M2D>+");
            fromPattern.append("<[CodeMaster2Code]@M2D>FromConnectedWithThis<[CodeMaster]@MASTER>+");
            fromPattern.append("<this>ThisConnectedWithFrom<[Code2Organization]@D2O>+");
            fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis<[Organizations]@ORG>+");

            // Where 조건 설정
            if( ApplicationSchemaConstants.BIZCLASS_COMPANY.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = "LGE";
            }
            else if( ApplicationSchemaConstants.BIZCLASS_BUSINESSUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.businessUnit, "-");
            }
            else if( ApplicationSchemaConstants.BIZCLASS_DIVISIONUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.divisionUnit, "-");
            }
            else if( ApplicationSchemaConstants.BIZCLASS_PLANTUNIT.equals(codeMasterVO.getCodeMasterScope()) ){
                codeScope = ThreadLocalUtil.getString(ThreadLocalUtil.KEY.plantUnit, "-");
            }

            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeValue);

            codeDetailList = ObjectRoot.searchObjects(
                    ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    false,
                    false,
                    false,
                    false,
                    null,
                    fromPattern.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    true,
                    0
            );

            if( codeDetailList != null){
                if( codeDetailList.size() > 1 ){
                    throw new ApplicationException("plm.common.code.error.dupdata", new Object[] { codeDetailList.size(), codeMasterNames, codeValue });
                }
                else if( codeDetailList.size() == 1 ){
                    if( codeDetailList.get(0) != null ){
                        codeDisplay = codeDetailList.get(0).getDescriptions();
                    }
                    else{
                        throw new ApplicationException("plm.common.code.error.nodata", new Object[] { codeMasterNames, codeValue });
                    }
                }
                else{
                    codeDisplay = codeValue;
                }
            }
            else{
                codeDisplay = codeValue;
            }
        }

        return codeDisplay;
    }

    public static String getCodeDisplayList(String codeValue, String codeMasterNames, String codeScope) throws ApplicationException{
        List<CodeDetailVO> codeDetailList = null;
        CodeMasterVO codeMasterVO = null;
        String codeDisplay = "";

        if( NullUtil.isNull(codeValue) ){
            return "";
        }

        List<CodeMasterVO> codeMasterList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEMASTER,
                codeMasterNames,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
                );

        if(codeMasterList != null && codeMasterList.size() > 0 && codeMasterList.get(0) != null){
            codeMasterVO = codeMasterList.get(0);

            // From 설정
            StringBuffer fromPattern = new StringBuffer();
            fromPattern.append("<this>                   ThisConnectedWithTo  <[CodeMaster2Code  ]@M2D>+");
            fromPattern.append("<[CodeMaster2Code  ]@M2D>FromConnectedWithThis<[CodeMaster       ]@MASTER>+");
            fromPattern.append("<this>                   ThisConnectedWithFrom<[Code2Organization]@D2O>+");
            fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis  <[Organizations    ]@ORG>+");

            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                    GlobalConstants.OQL_OPERATOR_IN, codeValue);

            codeDetailList = ObjectRoot.searchObjects(
                    ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    false,
                    false,
                    false,
                    false,
                    "SortBy@this.[sequences]",
                    fromPattern.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    true,
                    0
                    );

            if( codeDetailList != null && !codeDetailList.isEmpty()){
                for(CodeDetailVO codeDetailVO : codeDetailList){
                    codeDisplay += codeDetailVO.getTitles() + ",";
                }
                codeDisplay = codeDisplay.substring(0,codeDisplay.length()-1);
            }else{
                codeDisplay = codeValue;
            }
        }

        return codeDisplay;
    }

    public static List<CodeDetailVO> getCodeList(String codeValue, String codeMasterNames, String codeScope) throws ApplicationException{
        List<CodeDetailVO> codeDetailList = new ArrayList<CodeDetailVO>();
        CodeMasterVO codeMasterVO = null;

        if( NullUtil.isNull(codeValue) ){
            return codeDetailList;
        }

        List<CodeMasterVO> codeMasterList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEMASTER,
                codeMasterNames,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
                );

        if(codeMasterList != null && codeMasterList.size() > 0 && codeMasterList.get(0) != null){
            codeMasterVO = codeMasterList.get(0);

            // From 설정
            StringBuffer fromPattern = new StringBuffer();
            fromPattern.append("<this>                   ThisConnectedWithTo  <[CodeMaster2Code  ]@M2D>+");
            fromPattern.append("<[CodeMaster2Code  ]@M2D>FromConnectedWithThis<[CodeMaster       ]@MASTER>+");
            fromPattern.append("<this>                   ThisConnectedWithFrom<[Code2Organization]@D2O>+");
            fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis  <[Organizations    ]@ORG>+");

            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[names]",
                    GlobalConstants.OQL_OPERATOR_IN, codeValue);

            codeDetailList = ObjectRoot.searchObjects(
                    ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    false,
                    false,
                    false,
                    false,
                    "SortBy@this.[sequences]",
                    fromPattern.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    true,
                    0
                    );

        }

        return codeDetailList;
    }
    public static List<CodeDetailVO> getCodeListWithCodeDisplay(String codeDisplayValue, String codeMasterNames, String codeScope) throws ApplicationException{
        List<CodeDetailVO> codeDetailList = new ArrayList<CodeDetailVO>();
        CodeMasterVO codeMasterVO = null;

        if( NullUtil.isNull(codeDisplayValue) ){
            return codeDetailList;
        }

        List<CodeMasterVO> codeMasterList = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_CODEMASTER,
                codeMasterNames,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
                );

        if(codeMasterList != null && codeMasterList.size() > 0 && codeMasterList.get(0) != null){
            codeMasterVO = codeMasterList.get(0);

            // From 설정
            StringBuffer fromPattern = new StringBuffer();
            fromPattern.append("<this>                   ThisConnectedWithTo  <[CodeMaster2Code  ]@M2D>+");
            fromPattern.append("<[CodeMaster2Code  ]@M2D>FromConnectedWithThis<[CodeMaster       ]@MASTER>+");
            fromPattern.append("<this>                   ThisConnectedWithFrom<[Code2Organization]@D2O>+");
            fromPattern.append("<[Code2Organization]@D2O>ToConnectedWithThis  <[Organizations    ]@ORG>+");

            StringBuffer wherePatternBuf = new StringBuffer();
            StringBuffer paramPatternBuf = new StringBuffer();
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@MASTER.[obid]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getObid());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[names]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeScope);
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@ORG.[className]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, codeMasterVO.getCodeMasterScope());
            StringUtil.constructWherePattern(wherePatternBuf, paramPatternBuf, "@this.[titles]",
                    GlobalConstants.OQL_OPERATOR_IN, codeDisplayValue);

            codeDetailList = ObjectRoot.searchObjects(
                    ApplicationSchemaConstants.BIZCLASS_CODEDETAIL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    GlobalConstants.FLAG_TYPE_ALL,
                    false,
                    false,
                    false,
                    false,
                    "SortBy@this.[sequences]",
                    fromPattern.toString(),
                    wherePatternBuf.toString(),
                    paramPatternBuf.toString(),
                    true,
                    0
                    );

        }

        return codeDetailList;
    }

    /**
     * Plant Display 문자열 조회
     * @param plantName
     * @return
     */
    public static String getPlantDisplay(String plantName){
        String plantDisplay = "";

        List<PlantUnitVO> result = ObjectRoot.findObjects(
                ApplicationSchemaConstants.BIZCLASS_PLANTUNIT,
                plantName,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                GlobalConstants.FLAG_TYPE_ALL,
                true,
                0
        );

        if( result != null && result.size() > 0 && result.get(0) != null ){
            plantDisplay = result.get(0).getTitles();
        }

        return plantDisplay;
    }
    /**
     * Query의 IN clause 에서 사용할 수 있도록 ' 를 붙여줌
     * @param plantName
     * @return
     */
    public static String plateForINClause( String source ){
        return CommonUtil.plateForINClause( source, "," );
    }

    public static String plateForINClause( String source, String delimeter ){

        String[] list = source.split( delimeter );
        StringBuffer sb = new StringBuffer( 1024 );

        for( int index = 0; index < list.length; index ++ ){
             if( !list[ index ].isEmpty() ){
                 if( index > 0 ){
                     sb.append( delimeter );
                 }
                 sb.append( "'" + list[ index ] + "'" );
             }
        }
        return sb.toString();
    }

    
    public static UsersVO getUserInfoBySSOID(String SSOID, boolean checkActive){
        StringBuffer selectPattern = new StringBuffer();
        StringBuffer wherePattern = new StringBuffer();
        StringBuffer parameterPattern = new StringBuffer();
        StringUtil.constructSelectPattern( selectPattern, "SortBycase when @this.[states] = 'Active' then lpad(@this.[created],10,'1') else lpad(@this.[created],10,'0') end desc" );
        StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[mailId]", GlobalConstants.OQL_OPERATOR_EQUAL, SSOID);
        if(checkActive){
            StringUtil.constructWherePattern(wherePattern, parameterPattern, "@this.[states]",
                    GlobalConstants.OQL_OPERATOR_EQUAL, ApplicationSchemaConstants.STATE_ACTIVE_INACTIVE_ACTIVE);
        }
        UsersVO usersVO = null;
        List<UsersVO> findObjects = ObjectRoot.findObjects(ApplicationSchemaConstants.BIZCLASS_USERS,
                GlobalConstants.FLAG_TYPE_ALL, GlobalConstants.FLAG_TYPE_ALL, selectPattern.toString(),
                wherePattern.toString(), parameterPattern.toString(), false, 0);
        if(findObjects.size() > 0){
            usersVO = findObjects.get(0);
        }
        return usersVO;
    }

}

