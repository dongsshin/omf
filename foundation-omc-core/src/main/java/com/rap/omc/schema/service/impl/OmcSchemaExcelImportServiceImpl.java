/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : SchemaExcelImportService.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 2. 20.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.rap.omc.api.util.StrUtil;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.schema.object.dom.OmcSchemaAttribute;
import com.rap.omc.schema.object.dom.OmcSchemaDynamicAttrGrp;
import com.rap.omc.schema.object.dom.OmcSchemaFileFormat;
import com.rap.omc.schema.object.dom.OmcSchemaFileStore;
import com.rap.omc.schema.object.dom.OmcSchemaLifeCycle;
import com.rap.omc.schema.object.dom.OmcSchemaMenu;
import com.rap.omc.schema.object.dom.OmcSchemaMethodSet;
import com.rap.omc.schema.object.dom.OmcSchemaSite;
import com.rap.omc.schema.object.dom.OmcSchemaSysBizClass;
import com.rap.omc.schema.object.dom.OmcSchemaSysClassAttrInfo;
import com.rap.omc.schema.object.dom.OmcSchemaSysConstant;
import com.rap.omc.schema.object.dom.OmcSchemaSysRelClass;
import com.rap.omc.schema.object.dom.OmcSchemaTriggerParameter;
import com.rap.omc.schema.object.dom.OmcSchemaUserCommon;
import com.rap.omc.schema.object.model.OmcSchemaAttributeVO;
import com.rap.omc.schema.object.model.OmcSchemaDynamicAttrGrpVO;
import com.rap.omc.schema.object.model.OmcSchemaFileFormatVO;
import com.rap.omc.schema.object.model.OmcSchemaFileServerVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleBranchVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleStateInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaLifeCycleVO;
import com.rap.omc.schema.object.model.OmcSchemaMenuVO;
import com.rap.omc.schema.object.model.OmcSchemaMethodSetVO;
import com.rap.omc.schema.object.model.OmcSchemaRoleGroupVO;
import com.rap.omc.schema.object.model.OmcSchemaSiteVO;
import com.rap.omc.schema.object.model.OmcSchemaStateTriggerVO;
import com.rap.omc.schema.object.model.OmcSchemaSysBizClassVO;
import com.rap.omc.schema.object.model.OmcSchemaSysClassAttrInfoVO;
import com.rap.omc.schema.object.model.OmcSchemaSysConstantVO;
import com.rap.omc.schema.object.model.OmcSchemaSysRelClassVO;
import com.rap.omc.schema.object.model.OmcSchemaTabLayoutVO;
import com.rap.omc.schema.object.model.OmcSchemaTriggerParameterVO;
import com.rap.omc.schema.service.OmcSchemaExcelImportService;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcSchemaConstants;

/**
 * <pre>
 * Class : SchemaExcelImportService
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
@Service("omcSchemaExcelImportService")
@SuppressWarnings({ "rawtypes", "unchecked" })
public class OmcSchemaExcelImportServiceImpl implements OmcSchemaExcelImportService  {
    /**
     *
     * @param uploadOption
     * @param targetFile
     * @param map
     * @throws IOException
     * @throws InvalidFormatException
     * @see omc.schema.service.SchemaExcelImportService#getSystemConstants(long, java.lang.String, java.util.Map)
     */
    @Override
    public void getSystemConstants( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException{
        File file = new File(targetFile);
        OPCPackage opcPackage = OPCPackage.open(file);
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)){
            List<OmcSchemaSysConstantVO> constantsResult = new ArrayList<OmcSchemaSysConstantVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_CONSTANTS);
            for(int idx = OmcSchemaConstants.C_SHEET_CONSTANTS_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_NAMES)))) break;
                OmcSchemaSysConstantVO vo = new OmcSchemaSysConstantVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_CHANGE_COMMENTS)));
                vo.setKinds(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_KINDS)));
                vo.setKindDesc(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_KIND_DESC)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_NAMES)));
                vo.setConstantValues(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_VALUES)));
                vo.setHexConvertingFlag(getExcelCellValue(row.getCell(OmcSchemaConstants.C_COLUMN_CONST_PHEX_CONVERTING_FLAG)));
                constantsResult.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_CONSTANTS, constantsResult);
        }
        opcPackage.close();
    }
    /**
     *
     * @param uploadOption
     * @param targetFile
     * @param map
     * @throws IOException
     * @throws InvalidFormatException
     * @see omc.schema.service.SchemaExcelImportService#getClassAndAttr(long, java.lang.String, java.util.Map)
     */
    @Override
    public void getClassAndAttr   ( long uploadOption,String targetFile,Map<String,Object> map) throws IOException, InvalidFormatException{
        File file = new File(targetFile);
        OPCPackage opcPackage = OPCPackage.open(file);
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)){
            List<OmcSchemaSysBizClassVO> bizResult = new ArrayList<OmcSchemaSysBizClassVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_BUSINESS_CLASS);
            for(int idx = OmcSchemaConstants.C_SHEET_BUSINESS_CLASS_SROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;
                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_NAMES)))) break;
                OmcSchemaSysBizClassVO vo = new OmcSchemaSysBizClassVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_CHANGE_COMMENTS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_NAMES)));
                vo.setNamesParent(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_NAMES_PARENT)));
                vo.setDefaultPolicy(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_DEFAULT_POLICY)));
                vo.setDbmsTable(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_DBMS_TABLE)));
                vo.setIsInstantiableStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_IS_INSTANTIABLE)));
                vo.setJavaPackage(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_JAVA_PACKAGE)));
                vo.setDisplayedName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_DISPLAYED_NAME)));
                vo.setDisplayedNameKr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_DISPLAYED_NAME_KR)));
                vo.setComboDisplayStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_COMBO_DISPLAY)));
                vo.setApplyWorkflowStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_APPLY_WORKFLOW)));
                vo.setWorkflowUrl(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_WORKFLOW_URL)));
                vo.setModuleName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_MODULE_NAME)));
                vo.setClassIcon(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_CLASS_ICON)));
                vo.setClassIconSmall(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_CLASS_ICON_SMALL)));
                vo.setRemarks(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_REMARKS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_BIZ_COLUMN_OWNERS)));
                bizResult.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_BIZ_CLASS, bizResult);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)){
            List<OmcSchemaSysRelClassVO> relResult = new ArrayList<OmcSchemaSysRelClassVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_RELATION_CLASS);
            for(int idx = OmcSchemaConstants.C_SHEET_RELATION_CLASS_SROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_NAMES)))) break;
                OmcSchemaSysRelClassVO vo = new OmcSchemaSysRelClassVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_CHANGE_COMMENTS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_NAMES)));
                vo.setNamesParent(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_NAMES_PARENT)));
                vo.setDisplayedName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_DISPLAYED_NAME)));
                vo.setDisplayedNameKr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_DISPLAYED_NAME_KR)));
                vo.setDbmsTable(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_DBMS_TABLE)));
                vo.setAllowDuplicateStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_ALLOW_DUPLICATE)));
                vo.setFromClass(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_FROM_TYPE)));
                vo.setToClass(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_TO_TYPE)));
                vo.setFromRelationship(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_FROM_RELATONSHIP)));
                vo.setToRelationship(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_TO_RELATONSHIP)));
                vo.setRelationFromMeaning(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_FROM_MEANING)));
                vo.setRelationToMeaning(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_TO_MEANING)));
                vo.setCardinalityFromStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_CARDINALITY_FROM)));
                vo.setCardinalityToStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_CARDINALITY_TO)));
                vo.setRevisionRuleFromStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_REVISION_RULE_FROM)));
                vo.setRevisionRuleToStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_REVISION_RULE_TO)));
                vo.setIsInstantiableStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_IS_INSTANTIABLE)));
                vo.setJavaPackage(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_JAVA_PACKASGE)));
                vo.setModuleName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_MODULE_NAME)));
                vo.setRemarks(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_REMARKS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_REL_COLUMN_OWNERS)));
                relResult.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_REL_CLASS, relResult);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR)){
            List<OmcSchemaSysClassAttrInfoVO> attrResult = new ArrayList<OmcSchemaSysClassAttrInfoVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_CLASS_ATTRIBUTE);
            String tempLength = "";
            String dataTypeStr = "";
            for(int idx = OmcSchemaConstants.C_SHEET_CLASS_ATTRIBUTE_SROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_ATTR_NAME)))) break;
                OmcSchemaSysClassAttrInfoVO vo = new OmcSchemaSysClassAttrInfoVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_SEQUENCES)).trim()));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_CHANGE_COMMENTS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_ATTR_NAME)));
                vo.setClassType(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_CLASS_TYPE)));
                vo.setClassName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_CLASS_NAME)));
                vo.setAttributeName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_ATTR_NAME)));
                vo.setDbmsColumn(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_DBMS_COLUMN)));
                vo.setColumnAlias(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_DBMS_ALIAS)));
                vo.setSortings(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_ATTR_SORTING)).trim()));
                dataTypeStr = getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_ATTR_TYPE));
                vo.setDataTypeStr(dataTypeStr);
                tempLength = getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_ATTR_LENGTH)).trim();
                if(StrUtil.isEmpty(tempLength)) {
                    if(StrUtil.in(dataTypeStr, "Integer","Float","Date","Boolean","Double","BigDecimal","Long","UserId")){
                        vo.setLengths(0);
                    }else{
                        throw new FoundationException("[Foundation]★★★★★★ Attribute Length cannot be null. Please check class attribute Schema Info.");
                    }
                }else{
                    vo.setLengths(Integer.parseInt(tempLength));
                }
                vo.setNullAbleStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_NULLABLE)));
                vo.setDefaultValue(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_DEFAULT_VALUE)));
                
                String valueList = getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_VALUE_LIST));
                if(StrUtil.isEmpty(valueList)) valueList = "N/A";
                if(valueList.indexOf("|") != -1){
                    throw new FoundationException("[Foundation]★★★★★★ Value List cannot include '|' Character. Please check class attribute Schema Info.");
                }
                vo.setValueList(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_VALUE_LIST)));
                vo.setDisplayedName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_DISPLAYED_NAME)));
                vo.setRemarks(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_REMARKS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_COLUMN_OWNERS)));
                attrResult.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_CLASS_ATTR, attrResult);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ATTR_DISPLAYED)){
            List<OmcSchemaAttributeVO> attrResult = new ArrayList<OmcSchemaAttributeVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_CLASS_ATTR_DISPLAYED);
            for(int idx = OmcSchemaConstants.C_SHEET_CLASS_ATTR_DISPLAYED_SROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_ATTR_NAME)))) break;
                OmcSchemaAttributeVO vo = new OmcSchemaAttributeVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_SEQUENCES)).trim()));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_CHANGE_COMMENTS)));
                vo.setModuleName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_MODULE)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_ATTR_NAME)));
                vo.setDisplayedNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_ATTR_DISPLAYED_NAME)));
                vo.setDisplayedNamesKr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_ATTR_DISPLAYED_NAME_KR)));
                vo.setRemarks(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_REMARKS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ATTR_DISPLAYED_OWNERS)));
                attrResult.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_ATTR_DISPLYED, attrResult);
        }
        opcPackage.close();
    }

    /**
     *
     * @param uploadOption
     * @param targetFile
     * @param map
     * @throws IOException
     * @throws InvalidFormatException
     * @see omc.schema.service.SchemaExcelImportService#getLifeCycles(long, java.lang.String, java.util.Map)
     */
    @Override
    public void getLifeCycles(long uploadOption, String targetFile, Map<String, Object> map) throws IOException,
            InvalidFormatException{
        File file = new File(targetFile);
        OPCPackage opcPackage = OPCPackage.open(file);
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_LIFE_CYCLE_UPLOAD)){
            List<OmcSchemaLifeCycleVO> rslt = new ArrayList<OmcSchemaLifeCycleVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_LIFE_CYCLE);
            for(int idx = OmcSchemaConstants.C_SHEET_LIFE_CYCLE_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_NAMES)))) break;
                OmcSchemaLifeCycleVO vo = new OmcSchemaLifeCycleVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_CHANGE_COMMENTS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_NAMES)));
                vo.setDisplayedName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_DISPLAYED_NAME)));
                vo.setSequenceRule(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_SEQUENCE_RULE)));
                vo.setDefaultFormat(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_DEFAULT_FORMAT)));
                vo.setStateList(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_STATE_LIST)));
                vo.setStoreName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_STORE)));
                vo.setAllStateFlag(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_ALL_STATE_DEF)));
                vo.setAppliedClassList(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_APPLIED_CLASS_LIST)));
                vo.setAppliedFormatList(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_ALLOWED_FORMAT_LIST)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFECYCLE_COLUMN_OWNERS)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_CYCLE, rslt);
        }
        //-----------------------------------------------------------------------------------------------------------------------------------
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_INFO_UPLOAD)){
            List<OmcSchemaLifeCycleStateInfoVO> rslt = new ArrayList<OmcSchemaLifeCycleStateInfoVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_STATE);
            for(int idx = OmcSchemaConstants.C_SHEET_STATE_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_LIFE_CYCLE)))) break;
                OmcSchemaLifeCycleStateInfoVO vo = new OmcSchemaLifeCycleStateInfoVO();
                vo.setSequences(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_CHANGE_COMMENTS)));
                vo.setLifeCycle(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_LIFE_CYCLE)));
                vo.setStates(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_STATE)));
                vo.setRouteCompleteAction(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_ROUTE_COMPLETE_ACTION)));
                vo.setDefaultRoutePurpose(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_DEFAULT_ROUTE_PURPOSE)));
                vo.setRouteAutoStartOnReject(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_ROUTE_AUTOSTART_ONREJECT)));
                vo.setRouteHowToOnReject(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_ROUTE_HOWTO_ONREJECT)));
                vo.setInboxTaskAutoComplete(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_INBOX_TASK_AUTO_COMPLETE)));
                vo.setDateOffsetDay(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_DATE_OFFSET_DAY))));
                vo.setParallelProcessRule(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFESTATEINFO_COLUMN_PARALLEL_PROCESS_RULE)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_STATE_INFO, rslt);
        }
        //-----------------------------------------------------------------------------------------------------------------------------------
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_BRANCH_UPLOAD)){
            List<OmcSchemaLifeCycleBranchVO> rslt = new ArrayList<OmcSchemaLifeCycleBranchVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_BRANCH);
            for(int idx = OmcSchemaConstants.C_SHEET_BRANCH_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFEBRANCH_COLUMN_LIFE_CYCLE)))) break;
                OmcSchemaLifeCycleBranchVO vo = new OmcSchemaLifeCycleBranchVO();
                vo.setSequences(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFEBRANCH_COLUMN_SEQUENCES)).trim()));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFEBRANCH_COLUMN_CHANGE_COMMENTS)));
                vo.setLifeCycle(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFEBRANCH_COLUMN_LIFE_CYCLE)));
                vo.setBracnhInfo(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFEBRANCH_COLUMN_BRACNH_INFO)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_BRANCH, rslt);
        }
        //-----------------------------------------------------------------------------------------------------------------------------------
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PARAMETER_UPLOAD)){
            List<OmcSchemaTriggerParameterVO> rslt = new ArrayList<OmcSchemaTriggerParameterVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_TRIGGER_PARAM);
            for(int idx = OmcSchemaConstants.C_SHEET_TRIGGER_PARAM_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_NAMES)))) break;
                OmcSchemaTriggerParameterVO vo = new OmcSchemaTriggerParameterVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_CHANGE_COMMENTS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_NAMES)));
                vo.setParmDescription(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_DESCRIPTION)));
                vo.setProgramName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_PROGRAM_NAME)));
                vo.setMethodName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_METHOD_NAME)));
                vo.setProgramConstructor(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_CONSTRUCTOR)));
                vo.setCalledSequence(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_CALLED_SEQUENCE)).trim()));
                vo.setErrorType(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_RETURN_TYPE)));
                vo.setTargetStates(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_TARGET_STATES)));
                vo.setArgument01(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT01)));
                vo.setArgument02(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT02)));
                vo.setArgument03(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT03)));
                vo.setArgument04(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT04)));
                vo.setArgument05(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT05)));
                vo.setArgument06(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT06)));
                vo.setArgument07(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT07)));
                vo.setArgument08(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT08)));
                vo.setArgument09(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT09)));
                vo.setArgument10(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT10)));
                vo.setArgumentDesc01(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC01)));
                vo.setArgumentDesc02(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC02)));
                vo.setArgumentDesc03(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC03)));
                vo.setArgumentDesc04(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC04)));
                vo.setArgumentDesc05(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC05)));
                vo.setArgumentDesc06(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC06)));
                vo.setArgumentDesc07(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC07)));
                vo.setArgumentDesc08(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC08)));
                vo.setArgumentDesc09(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC09)));
                vo.setArgumentDesc10(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_TRIGGER_COLUMN_ARGUMENT_DESC10)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_PARAMETER, rslt);
        }
        //-----------------------------------------------------------------------------------------------------------------------------------
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_TRIGGER_UPLOAD)){
            List<OmcSchemaStateTriggerVO> rslt = new ArrayList<OmcSchemaStateTriggerVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_STATE_TRIGGER);
            for(int idx = OmcSchemaConstants.C_SHEET_STATE_TRIGGER_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_POLICY_NAME)))) break;
                OmcSchemaStateTriggerVO vo = new OmcSchemaStateTriggerVO();
                vo.setSequences(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_CHANGE_COMMENTS)));
                vo.setPolicyName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_POLICY_NAME)));
                vo.setStateName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_STATE_NAME)));
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_KIND_STR)));
                vo.setProgramKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_PROGRAM_TYPE)));
                vo.setCalledSequences(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_CALLED_SEQUENCE)).trim()));
                vo.setProgramName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_TRIGGER_MANAGER)));
                vo.setTriggerName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_STATE_TRIGGER_COLUMN_TRIGGER_NAME)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_STATE_TRIGGER, rslt);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_FILE_FORMAT_UPLOAD)){
            List<OmcSchemaFileFormatVO> rslt = new ArrayList<OmcSchemaFileFormatVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_FILE_FORMAT);
            for(int idx = OmcSchemaConstants.C_SHEET_FILE_FORMAT_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_NAMES)))) break;
                OmcSchemaFileFormatVO vo = new OmcSchemaFileFormatVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_CHANGE_COMMENTS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_NAMES)));
                vo.setAllowedSuffix(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_SUFFIX)));
                vo.setDisplayedNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_DISPLAYED_NAME)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_FILE_FORMAT_COLUMN_OWNERS)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_FILE_FORMAT, rslt);
        }
        opcPackage.close();
    }
    /**
     *
     * @param uploadOption
     * @param targetFile
     * @param map
     * @throws IOException
     * @throws InvalidFormatException
     * @see omc.schema.service.SchemaExcelImportService#getMemusAndLayouts(long, java.lang.String, java.util.Map)
     */
    @Override
    public void getMemusAndLayouts(long uploadOption, String targetFile, Map<String, Object> map)
            throws IOException, InvalidFormatException{
        File file = new File(targetFile);
        OPCPackage opcPackage = OPCPackage.open(file);
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_MENU_UPLOAD)){
            List<OmcSchemaMenuVO> rslt = new ArrayList<OmcSchemaMenuVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_MENU);
            for(int idx = OmcSchemaConstants.C_SHEET_MENU_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_NAMES)))) break;
                OmcSchemaMenuVO vo = new OmcSchemaMenuVO();
                vo.setSequences(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_CHANGE_COMMENTS)));
                vo.setIsSub(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_IS_DUB)));
                vo.setModuleName(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_MODULE_NAME)));
                vo.setIsAccessControlObject(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_ACCESS_CONTROL)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_NAMES)));
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_KINDS)));
                vo.setSortings(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_SORT)).trim()));
                vo.setSubNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_SUB_NAMES)));
                vo.setIsHidden(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_IS_HIDDEN)));
                vo.setLabels(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_LABELS)));
                vo.setLabelsKr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_COLUMN_LABELS_KR)));
                vo.setCallingType(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_CALLING_TYPE)));
                vo.setLinkHref(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_HERF)));
                vo.setLinkAlt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_ALT)));
                vo.setImages(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_IMAGE)));
                String expression = getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_ACCESS_EXPRESSION));
                if(!StrUtil.isEmpty(expression)) expression.replaceAll("/n", "");
                vo.setAccessExpression(expression);
                vo.setDescriptions(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_REMARKS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_MENU_OWNERS)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_MENU, rslt);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PAGE_LAYOUT_UPLOAD)){
            List<OmcSchemaTabLayoutVO> rslt = new ArrayList<OmcSchemaTabLayoutVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_LAYOUT_TAB);
            for(int idx = OmcSchemaConstants.C_SHEET_LAYOUT_TAB_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_NAMES)))) break;
                OmcSchemaTabLayoutVO vo = new OmcSchemaTabLayoutVO();
                vo.setSequences(Integer.parseInt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_CHANGE_COMMENTS)));
                vo.setUsages(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_USAGES)));
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_KINDS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_NAMES)));
                vo.setLabels(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_DISPLAYED_NAMES)));
                vo.setLabelsKr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_DISPLAYED_NAMES_KR)));
                vo.setSubObjectList(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_SUBOBJECT_LIST)));
                vo.setLinkHerf(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_LINK_HERF)));
                vo.setLinkAlt(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_COLUMN_LINK_ALT)));
                String str = getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_HEIGHTS)).trim();
                if(!StrUtil.isEmpty(str)){
                    vo.setHeights(Integer.parseInt(str));
                }else{
                    vo.setHeights(0);
                }
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_LIFE_LAYOUT_TAB_OWNERS)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_TAB_LAYOUT, rslt);
        }
        opcPackage.close();
    }
    /**
     *
     * @param uploadOption
     * @param targetFile
     * @param map
     * @throws IOException
     * @throws InvalidFormatException
     * @see omc.schema.service.SchemaExcelImportService#getEtcSchemas(long, java.lang.String, java.util.Map)
     */
    @Override
    public void getEtcSchemas(long uploadOption, String targetFile, Map<String, Object> map) throws IOException,
            InvalidFormatException{
        File file = new File(targetFile);
        OPCPackage opcPackage = OPCPackage.open(file);
        XSSFWorkbook workbook = new XSSFWorkbook(opcPackage);
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_SITE_UPLOAD)){
            List<OmcSchemaSiteVO> rslt = new ArrayList<OmcSchemaSiteVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_SITE);
            for(int idx = OmcSchemaConstants.C_SHEET_SITE_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_SITE_COLUMN_NAMES)))) break;
                OmcSchemaSiteVO vo = new OmcSchemaSiteVO();
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_SITE_COLUMN_NAMES)));
                vo.setDisplayedNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_SITE_COLUMN_DISPLAYED_NAMES)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_SITE, rslt);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STORE_LOCATION_UPLOAD)){
            List<OmcSchemaFileServerVO> rslt = new ArrayList<OmcSchemaFileServerVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_STORE_LOCATION);
            for(int idx = OmcSchemaConstants.C_SHEET_STORE_LOCATION_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_NAMES)))) break;
                OmcSchemaFileServerVO vo = new OmcSchemaFileServerVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_CHANGE_COMMENTS)));
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_KINDS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_NAMES)));
                vo.setFileServer(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_SERVER)));
                vo.setFilePath(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_PATH)));
                vo.setFtpUser(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_FTP_USER)));
                vo.setFtpPassword(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_FTP_PASSWORD)));
                vo.setServerProtocol(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_FTP_PROTOCOL)));
                vo.setServerPort(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_FTP_PORT)));
                vo.setServiceDomain(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_SERVICE_DOMAIN)));
                vo.setServicePort(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_SERVICE_PORT)));
                vo.setServiceUrl(getExcelCellValue(row.getCell(OmcSchemaConstants.C_STORE_LOCATION_COLUMN_SERVICE_URL)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_STORE_LOCATION, rslt);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ROLE_GROUP_UPLOAD)){
            List<OmcSchemaRoleGroupVO> rslt = new ArrayList<OmcSchemaRoleGroupVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_ROLE_GROUP);
            for(int idx = OmcSchemaConstants.C_SHEET_ROLE_GROUP_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_NAMES)))) break;
                OmcSchemaRoleGroupVO vo = new OmcSchemaRoleGroupVO();
                vo.setSequences(Long.parseLong(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_SEQUENCES))));
                vo.setChangeComments(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_CHANGE_COMMENTS)));
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_KINDS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_NAMES)));
                vo.setDescriptions(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_DESCRIPTIONS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_OWNERS)));
                vo.setRemarks(getExcelCellValue(row.getCell(OmcSchemaConstants.C_ROLE_GROUP_COLUMN_REMARKS)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_ROLE_GROUP, rslt);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ACCESS_METHOD_UPLOAD)){
            List<OmcSchemaMethodSetVO> rslt = new ArrayList<OmcSchemaMethodSetVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_METHOD_SET);
            for(int idx = OmcSchemaConstants.C_SHEET_METHOD_SET_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_METHODSET_COLUMN_NAMES)))) break;
                OmcSchemaMethodSetVO vo = new OmcSchemaMethodSetVO();
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_METHODSET_COLUMN_NAMES)));
                vo.setAccessConstant(getExcelCellValue(row.getCell(OmcSchemaConstants.C_METHODSET_COLUMN_CONSTANT_VALUE)));
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_METHODSET_COLUMN_KINDS)));
                vo.setOwners(getExcelCellValue(row.getCell(OmcSchemaConstants.C_METHODSET_COLUMN_OWNERS)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_METHOD_SET, rslt);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_DYNAMIC_ATTR_UPLOAD)){
            List<OmcSchemaDynamicAttrGrpVO> rslt = new ArrayList<OmcSchemaDynamicAttrGrpVO>();
            XSSFSheet workSheet = workbook.getSheet(OmcSchemaConstants.C_SHEET_DYNAMIC_ATTRIBUTE);
            for(int idx = OmcSchemaConstants.C_SHEET_DYNAMIC_ATTRIBUTE_ROW; idx <= workSheet.getLastRowNum(); idx++){
                XSSFRow row = workSheet.getRow(idx);
                if(row == null) break;

                if(StrUtil.isEmpty(getExcelCellValue(row.getCell(OmcSchemaConstants.C_DYNAMIC_ATTRGRP_COLUMN_NAMES)))) break;
                OmcSchemaDynamicAttrGrpVO vo = new OmcSchemaDynamicAttrGrpVO();
                vo.setKindsStr(getExcelCellValue(row.getCell(OmcSchemaConstants.C_DYNAMIC_ATTRGRP_COLUMN_KINDS)));
                vo.setNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_DYNAMIC_ATTRGRP_COLUMN_NAMES)));
                vo.setDescriptions(getExcelCellValue(row.getCell(OmcSchemaConstants.C_DYNAMIC_ATTRGRP_COLUMN_DESCRIPTIONS)));
                vo.setParent(getExcelCellValue(row.getCell(OmcSchemaConstants.C_DYNAMIC_ATTRGRP_COLUMN_PARENT)));
                vo.setDisplayedNames(getExcelCellValue(row.getCell(OmcSchemaConstants.C_DYNAMIC_ATTRGRP_COLUMN_DISPLAYED_NAME)));
                rslt.add(vo);
            }
            map.put(OmcSchemaConstants.C_SCHEMA_MAP_DYNAMIC_ATTRIBUTE, rslt);
        }
        opcPackage.close();
    }
    /**
     *
     * @param uploadOption
     * @param map
     * @throws Exception
     * @see omc.schema.service.SchemaExcelImportService#uploadSystemConstants(long, java.util.Map)
     */
    public void uploadSystemConstants( long uploadOption,Map<String,Object> map) throws Exception{
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)){
            List<OmcSchemaSysConstantVO> list = (List<OmcSchemaSysConstantVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_CONSTANTS);
            OmcSchemaSysConstant.uploadTemporaryList(list);
        }
    }
    /**
     *
     * @param uploadOption
     * @param map
     * @throws Exception
     * @see omc.schema.service.SchemaExcelImportService#uploadClassAndAttr(long, java.util.Map)
     */
    @Override
    public void uploadClassAndAttr   ( long uploadOption,Map<String,Object> map) throws Exception{
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)){
            List<OmcSchemaSysBizClassVO> list = (List<OmcSchemaSysBizClassVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_BIZ_CLASS);
            OmcSchemaSysBizClass.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS)){
            List<OmcSchemaSysRelClassVO> list = (List<OmcSchemaSysRelClassVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_REL_CLASS);
            OmcSchemaSysRelClass.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR)){
            List<OmcSchemaSysClassAttrInfoVO> list = (List<OmcSchemaSysClassAttrInfoVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_CLASS_ATTR);
            OmcSchemaSysClassAttrInfo.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ATTR_DISPLAYED)){
            List<OmcSchemaAttributeVO> list = (List<OmcSchemaAttributeVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_ATTR_DISPLYED);
            OmcSchemaAttribute.uploadTemporaryList(list);
        }
    }
    /**
     *
     * @param uploadOption
     * @param map
     * @throws Exception
     * @see omc.schema.service.SchemaExcelImportService#uploadMemusAndLayouts(long, java.util.Map)
     */
    @Override
    public void uploadMemusAndLayouts(long uploadOption, Map<String, Object> map) throws Exception{
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_MENU_UPLOAD)){
            List<OmcSchemaMenuVO> list = (List<OmcSchemaMenuVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_MENU);
            OmcSchemaMenu.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PAGE_LAYOUT_UPLOAD)){
            List<OmcSchemaTabLayoutVO> list = (List<OmcSchemaTabLayoutVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_TAB_LAYOUT);
            OmcSchemaMenu.uploadTemporaryTabLayoutList(list);
        }
    }
    /**
     *
     * @param uploadOption
     * @param map
     * @throws Exception
     * @see omc.schema.service.SchemaExcelImportService#uploadLifeCycles(long, java.util.Map)
     */
    @Override
    public void uploadLifeCycles(long uploadOption, Map<String, Object> map) throws Exception{
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_LIFE_CYCLE_UPLOAD)){
            List<OmcSchemaLifeCycleVO> list = (List<OmcSchemaLifeCycleVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_CYCLE);
            OmcSchemaLifeCycle.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_INFO_UPLOAD)){
                List<OmcSchemaLifeCycleStateInfoVO> list = (List<OmcSchemaLifeCycleStateInfoVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_STATE_INFO);
                OmcSchemaLifeCycle.uploadTemporaryStateInfoList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_BRANCH_UPLOAD)){
                List<OmcSchemaLifeCycleBranchVO> list = (List<OmcSchemaLifeCycleBranchVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_BRANCH);
                OmcSchemaLifeCycle.uploadTemporaryBranchList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PARAMETER_UPLOAD)){
            List<OmcSchemaTriggerParameterVO> list = (List<OmcSchemaTriggerParameterVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_PARAMETER);
            OmcSchemaTriggerParameter.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_TRIGGER_UPLOAD)){
            List<OmcSchemaStateTriggerVO> list = (List<OmcSchemaStateTriggerVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_LIFE_STATE_TRIGGER);
            OmcSchemaLifeCycle.uploadTemporaryStateTriggerList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_FILE_FORMAT_UPLOAD)){
            List<OmcSchemaFileFormatVO> list = (List<OmcSchemaFileFormatVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_FILE_FORMAT);
            OmcSchemaFileFormat.uploadTemporaryList(list);
        }
    }
    /**
     *
     * @param uploadOption
     * @param map
     * @throws Exception
     * @see omc.schema.service.SchemaExcelImportService#uploadEtcSchemas(long, java.util.Map)
     */
    @Override
    public void uploadEtcSchemas(long uploadOption, Map<String, Object> map) throws Exception{

        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_SITE_UPLOAD)){
            List<OmcSchemaSiteVO> list = (List<OmcSchemaSiteVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_SITE);
            OmcSchemaSite.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STORE_LOCATION_UPLOAD)){
            List<OmcSchemaFileServerVO> list = (List<OmcSchemaFileServerVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_STORE_LOCATION);
            OmcSchemaFileStore.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ROLE_GROUP_UPLOAD)){
            List<OmcSchemaRoleGroupVO> list = (List<OmcSchemaRoleGroupVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_ROLE_GROUP);
            OmcSchemaUserCommon.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ACCESS_METHOD_UPLOAD)){
            List<OmcSchemaMethodSetVO> list = (List<OmcSchemaMethodSetVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_METHOD_SET);
            OmcSchemaMethodSet.uploadTemporaryList(list);
        }
        if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_DYNAMIC_ATTR_UPLOAD)){
            List<OmcSchemaDynamicAttrGrpVO> list = (List<OmcSchemaDynamicAttrGrpVO>)map.get(OmcSchemaConstants.C_SCHEMA_MAP_DYNAMIC_ATTRIBUTE);
            OmcSchemaDynamicAttrGrp.uploadTemporaryList(list);
        }
    }
    /**
     *
     * @param cell
     * @see omc.schema.service.SchemaExcelImportService#getExcelCellValue(org.apache.poi.xssf.usermodel.XSSFCell)
     */
    private String getExcelCellValue(XSSFCell cell){
        String cellValue = "";
        try{
            cellValue = cell == null ? "" : cell.getStringCellValue().trim();
        }
        catch( IllegalStateException e ){
            try{
            cellValue = String.valueOf( ((Double)cell.getNumericCellValue()).intValue() );
            }
            catch( IllegalStateException ed ){
                try{
                cellValue = String.valueOf((cell.getBooleanCellValue()));
                }
                catch(Exception ee){
                    cellValue = "";
                }
            }
        }
        return cellValue;
    }
}
