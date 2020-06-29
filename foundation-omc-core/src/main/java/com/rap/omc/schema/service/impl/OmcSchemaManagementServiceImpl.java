/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : OmcSchemaManagementServiceImpl.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2017. 2. 1.  s_dongsshin   Initial
 * ===========================================
 */
package com.rap.omc.schema.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rap.config.datasource.dao.SchemaDao;
import com.rap.omc.api.oql.model.OmcSQLVariableParameter;
import com.rap.omc.api.oql.utility.OmcFoundationConstant;
import com.rap.omc.api.util.StrUtil;
import com.rap.omc.foundation.classes.model.ClassInfo;
import com.rap.omc.foundation.classes.model.ColumnInfo;
import com.rap.omc.foundation.classes.service.CommonService;
import com.rap.omc.framework.exception.FoundationException;
import com.rap.omc.api.util.spring.SpringFactoryUtil;
import com.rap.omc.schema.object.dom.OmcSchemaAttribute;
import com.rap.omc.schema.object.model.OmcSchemaAttributeVO;
import com.rap.omc.schema.object.model.OmcSchemaPropertyVO;
import com.rap.omc.schema.object.model.OmcSchemaTableColumnVO;
import com.rap.omc.schema.service.OmcSchemaCoreService;
import com.rap.omc.schema.service.OmcSchemaExcelImportService;
import com.rap.omc.schema.service.OmcSchemaManagementService;
import com.rap.omc.schema.util.Bit;
import com.rap.omc.schema.util.OmcEnvironment;
import com.rap.omc.schema.util.OmcFileUtil;
import com.rap.omc.schema.util.OmcSchemaConstants;
import com.rap.omc.schema.util.OmcSchemaExcelFileConstants;
import com.rap.omc.schema.util.OmcSchemaServiceUtils;
import com.rap.omc.schema.util.OmcSystemConstants;
import com.rap.omc.util.foundation.ClassInfoUtil;

/**
 * <pre>
 * Class : OmcSchemaManagementServiceImpl
 * Description : TODO
 * </pre>
 *
 * @author s_dongsshin
 */
@Service("omcSchemaManagementService")
public class OmcSchemaManagementServiceImpl implements OmcSchemaManagementService{
    private static final Logger LOGGER = LoggerFactory.getLogger(OmcSchemaManagementServiceImpl.class);
    
    @Resource(name = "schemaDao")
    private SchemaDao schemaDao;
    
    
    @Autowired
    private OmcSchemaCoreService omcSchemaCoreService;
    @Autowired
    private OmcSchemaExcelImportService omcSchemaExcelImportService;
    
    public void dumpTableScripts(OmcEnvironment.Environment envionment,boolean isFull, boolean includeCreateIndex, long targetDBMSType){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append ("select distinct pdbms_table as dbms_table");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysattrref a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pdbms_table not in (#{funVariable_00001})");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.pdbms_table");
        variableParameter.setFunVariable_00001("DUMMY");
        variableParameter.setSql(sqlStrBuff.toString());
        List<String> dbmsTableList = schemaDao.selectList("Common.getDyancmicStringQuery", variableParameter);
        StringBuffer scriptBuf = new StringBuffer();
        for(String tableName: dbmsTableList){
            sqlStrBuff.setLength(0);
            tableName = tableName.toLowerCase();
            variableParameter = new OmcSQLVariableParameter();
            if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            {
                sqlStrBuff.append("                                      select count(*) as cnt");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("from user_tables a");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where where a.table_name= #{funVariable_00001}");
                variableParameter.setFunVariable_00001(tableName.toUpperCase());
            }else if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA)){
                sqlStrBuff.append("                                      select count(*) as cnt");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("from information_schema.tables a");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.table_catalog   = #{funVariable_00001}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.table_schema    = #{funVariable_00002}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.table_name      = #{funVariable_00003}");
                variableParameter.setFunVariable_00001("def");
                if(envionment == OmcEnvironment.Environment.DEVELOPMENT){
                    variableParameter.setFunVariable_00002("plmd");
                }else if(envionment == OmcEnvironment.Environment.MIGRATION){
                    variableParameter.setFunVariable_00002("plmp");
                }else if(envionment == OmcEnvironment.Environment.PRODUCTION){
                    variableParameter.setFunVariable_00002("plmp");
                }else if(envionment == OmcEnvironment.Environment.MIGRATION_TEST1){
                    variableParameter.setFunVariable_00002("ptd1");
                }else if(envionment == OmcEnvironment.Environment.MIGRATION_TEST2){
                    variableParameter.setFunVariable_00002("ptd2");
                }
                variableParameter.setFunVariable_00003(tableName);
                variableParameter.setSql(sqlStrBuff.toString());
            }
            Integer exists = schemaDao.select("Common.getDyancmicIntegerQuery", variableParameter);
            sqlStrBuff.setLength(0);
            variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      select a.pdbms_column  as dbms_column");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,max(a.class_type) as class_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,max(a.pnullable) as nullable");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdata_type    as data_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,max(a.plengths) as lengths  ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysattrref a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pdbms_table    = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("group by a.pdbms_column,a.pdata_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by min(a.psorting)");
            variableParameter.setFunVariable_00001(tableName.toUpperCase());
            variableParameter.setSql(sqlStrBuff.toString());
            List<OmcSchemaTableColumnVO> definedColumnList = schemaDao.selectList("ClassInfo.getDyancmicTableColumn", variableParameter);
            if(isFull) exists = 0;
            if(exists.equals(0)){
                scriptBuf.append(getTableHeader(tableName,"DS Shin"));
                scriptBuf.append(OmcFoundationConstant.newline).append("create table ").append(tableName.toLowerCase()).append("(");
                boolean isFirst = true;
                boolean isRevisionObject = false;
                boolean isRelationObject = false;
                for(OmcSchemaTableColumnVO columnInfo : definedColumnList){
                    if(!isFirst) scriptBuf.append(",");
                    if("pprevious_obid".equals(columnInfo.getDbmsColumn().toLowerCase())) isRevisionObject = true;
                    if("RO".equals(columnInfo.getClassType())) isRelationObject = true;
                    scriptBuf.append(OmcFoundationConstant.newline).append(getStringForColumn(columnInfo,targetDBMSType));
                    isFirst = false;
                }
                scriptBuf.append(OmcFoundationConstant.newline).append(");");
                if(includeCreateIndex){
                    if(isRelationObject){
                        scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                        //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(tableName).append("_01 on ").append(tableName).append("(obid);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_02 on ").append(tableName).append("(pfrom_obid,pclass_name,pto_class,pto_obid);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_03 on ").append(tableName).append("(pto_obid,pclass_name,pfrom_class,pfrom_obid);");
                    }else{
                        if(isRevisionObject){
                            scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                            //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(tableName).append("_01 on ").append(tableName).append("(obid);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(tableName).append("_02 on ").append(tableName).append("(pclass_name,pnames,prevision);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_03 on ").append(tableName).append("(pnames,prevision);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_04 on ").append(tableName).append("(powner,pnames,prevision);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_05 on ").append(tableName).append("(pmodified,pclass_name);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_06 on ").append(tableName).append("(pcreator,pclass_name);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_07 on ").append(tableName).append("(pstates,plife_cycle);");
                        }else{
                            scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                            //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(tableName).append("_01 on ").append(tableName).append("(obid);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(tableName).append("_02 on ").append(tableName).append("(pnames,pclass_name);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_03 on ").append(tableName).append("(powner,pnames);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_04 on ").append(tableName).append("(pmodified,pclass_name);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_05 on ").append(tableName).append("(pcreator,pclass_name);");
                            scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(tableName).append("_06 on ").append(tableName).append("(pstates,plife_cycle);");
                        }
                    }                    
                }
            }else{
                sqlStrBuff.setLength(0);
                variableParameter = new OmcSQLVariableParameter();
                if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
                {
                    sqlStrBuff.append("                                      select a.column_name as dbms_column");
                    sqlStrBuff.append(OmcFoundationConstant.newline).append("from user_tab_cols a");
                    sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.table_name    = #{funVariable_00001}");
                    variableParameter.setFunVariable_00001(tableName);
                }else if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA)){
                    sqlStrBuff.append("                                      select a.column_name  as dbms_column");
                    sqlStrBuff.append(OmcFoundationConstant.newline).append("from information_schema.columns a");
                    sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.table_name    = #{funVariable_00001}");
                    sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.table_schema  = #{funVariable_00002}");
                    sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.table_catalog = #{funVariable_00003}");
                    variableParameter.setFunVariable_00001(tableName);
                    if(envionment == OmcEnvironment.Environment.DEVELOPMENT){
                        variableParameter.setFunVariable_00002("plmd");
                    }else if(envionment == OmcEnvironment.Environment.MIGRATION){
                        variableParameter.setFunVariable_00002("plmp");
                    }else if(envionment == OmcEnvironment.Environment.PRODUCTION){
                        variableParameter.setFunVariable_00002("plmp");
                    }else if(envionment == OmcEnvironment.Environment.MIGRATION_TEST1){
                        variableParameter.setFunVariable_00002("ptd1");
                    }else if(envionment == OmcEnvironment.Environment.MIGRATION_TEST2){
                        variableParameter.setFunVariable_00002("ptd2");
                    }
                    variableParameter.setFunVariable_00003("def");
                }
                variableParameter.setSql(sqlStrBuff.toString());
                List<OmcSchemaTableColumnVO> dbmsColumnList = schemaDao.selectList("ClassInfo.getDyancmicTableColumn", variableParameter);
                boolean isHederWritten  = false;
                for(OmcSchemaTableColumnVO dbmsColInfo : dbmsColumnList){
                    boolean isExists = false;
                    for(OmcSchemaTableColumnVO definedColInfo : definedColumnList){
                        if(dbmsColInfo.getDbmsColumn().toLowerCase().equals(definedColInfo.getDbmsColumn().toLowerCase())){
                            isExists = true;
                            break;
                        }
                    }

                    if(!isExists && !isHederWritten) {scriptBuf.append(getTableHeader(tableName,"DS Shin")); isHederWritten = true;}
                    if(!isExists) scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" drop column ").append(dbmsColInfo.getDbmsColumn()).append(";");
                }
                for(OmcSchemaTableColumnVO definedColInfo : definedColumnList){
                    boolean isExists = false;
                    for(OmcSchemaTableColumnVO dbmsColInfo : dbmsColumnList){
                        if(dbmsColInfo.getDbmsColumn().toLowerCase().equals(definedColInfo.getDbmsColumn().toLowerCase())){
                            isExists = true;
                            break;
                        }
                    }
                    if(!isExists && !isHederWritten) {scriptBuf.append(getTableHeader(tableName,"DS Shin")); isHederWritten = true;}
                    if(!isExists) scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add column ").append(getStringForColumn(definedColInfo)).append(";");
                }
            }
            scriptBuf.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline);
        }
        LOGGER.debug(scriptBuf.toString());
        String str = scriptBuf.toString();
        while(str.indexOf(OmcFoundationConstant.newline + OmcFoundationConstant.newline) >= 0){
            str = str.replace(OmcFoundationConstant.newline + OmcFoundationConstant.newline, OmcFoundationConstant.newline);
        }
        if(!StrUtil.isEmpty(str)) this.writeSqlScripts(str);
    }
    public void dumpIndexScriptsAll(OmcEnvironment.Environment envionment, boolean isParallelOption){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append ("select distinct pdbms_table as dbms_table");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysattrref a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pdbms_table not in (#{funVariable_00001})");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("order by a.pdbms_table");
        variableParameter.setFunVariable_00001("DUMMY");
        variableParameter.setSql(sqlStrBuff.toString());
        List<String> dbmsTableList = schemaDao.selectList("Common.getDyancmicStringQuery", variableParameter);
        StringBuffer scriptBuf = new StringBuffer();
        Integer idexSeq = 1;
        for(String tableName: dbmsTableList){
            sqlStrBuff.setLength(0);
            tableName = tableName.toLowerCase();
            variableParameter = new OmcSQLVariableParameter();
            if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_ORACLE))
            {
                sqlStrBuff.append("                                      select count(*) as cnt");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("from user_tables a");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where where a.table_name= #{funVariable_00001}");
                variableParameter.setFunVariable_00001(tableName.toUpperCase());
            }else if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA)){
                sqlStrBuff.append("                                      select count(*) as cnt");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("from information_schema.tables a");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.table_catalog   = #{funVariable_00001}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.table_schema    = #{funVariable_00002}");
                sqlStrBuff.append(OmcFoundationConstant.newline).append("and   a.table_name      = #{funVariable_00003}");
                variableParameter.setFunVariable_00001("def");
                if(envionment == OmcEnvironment.Environment.DEVELOPMENT){
                    variableParameter.setFunVariable_00002("plmd");
                }else if(envionment == OmcEnvironment.Environment.MIGRATION){
                    variableParameter.setFunVariable_00002("plmp");
                }else if(envionment == OmcEnvironment.Environment.PRODUCTION){
                    variableParameter.setFunVariable_00002("plmp");
                }else if(envionment == OmcEnvironment.Environment.MIGRATION_TEST1){
                    variableParameter.setFunVariable_00002("ptd1");
                }else if(envionment == OmcEnvironment.Environment.MIGRATION_TEST2){
                    variableParameter.setFunVariable_00002("ptd2");
                }
                variableParameter.setFunVariable_00003(tableName);
                variableParameter.setSql(sqlStrBuff.toString());
            }
            Integer exists = schemaDao.select("Common.getDyancmicIntegerQuery", variableParameter);
            sqlStrBuff.setLength(0);
            variableParameter = new OmcSQLVariableParameter();
            sqlStrBuff.append("                                      select a.pdbms_column  as dbms_column");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,max(a.class_type) as class_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,max(a.pnullable) as nullable");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,a.pdata_type    as data_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("      ,max(a.plengths) as lengths  ");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysattrref a");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pdbms_table    = #{funVariable_00001}");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("group by a.pdbms_column,a.pdata_type");
            sqlStrBuff.append(OmcFoundationConstant.newline).append("order by min(a.psorting)");
            variableParameter.setFunVariable_00001(tableName.toUpperCase());
            variableParameter.setSql(sqlStrBuff.toString());
            List<OmcSchemaTableColumnVO> definedColumnList = schemaDao.selectList("ClassInfo.getDyancmicTableColumn", variableParameter);
            //scriptBuf.append(getTableHeader(tableName,"DS Shin"));
            
            String indexName = StrUtil.LPAD(idexSeq, 5, "0");
            boolean isRevisionObject = false;
            boolean isRelationObject = false;
            for(OmcSchemaTableColumnVO columnInfo : definedColumnList){
                if("pprevious_obid".equals(columnInfo.getDbmsColumn().toLowerCase())) isRevisionObject = true;
                if("RO".equals(columnInfo.getClassType())) isRelationObject = true;
            }
            if(isRelationObject){
                if(isParallelOption){
                    scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                    //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_01 on ").append(tableName).append("(obid) parallel;");
                    scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_02 on ").append(tableName).append("(pfrom_obid,pclass_name,pto_class,pto_obid) parallel;");
                    scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_03 on ").append(tableName).append("(pto_obid,pclass_name,pfrom_class,pfrom_obid) parallel;");
                }
                else{
                    scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                    //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_01 on ").append(tableName).append("(obid);");
                    scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_02 on ").append(tableName).append("(pfrom_obid,pclass_name,pto_class,pto_obid);");
                    scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_03 on ").append(tableName).append("(pto_obid,pclass_name,pfrom_class,pfrom_obid);");
                }
            }else{
                if(isRevisionObject){
                    if(isParallelOption){
                        scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                        //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_01 on ").append(tableName).append("(obid) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_02 on ").append(tableName).append("(pclass_name,pnames,prevision) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_03 on ").append(tableName).append("(pnames,prevision) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_04 on ").append(tableName).append("(powner,pnames,prevision) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_05 on ").append(tableName).append("(pmodified,pclass_name) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_06 on ").append(tableName).append("(pcreator,pclass_name) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_07 on ").append(tableName).append("(pstates,plife_cycle) parallel;");
                    }else{
                        scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                        //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_01 on ").append(tableName).append("(obid);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_02 on ").append(tableName).append("(pclass_name,pnames,prevision);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_03 on ").append(tableName).append("(pnames,prevision);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_04 on ").append(tableName).append("(powner,pnames,prevision);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_05 on ").append(tableName).append("(pmodified,pclass_name);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_06 on ").append(tableName).append("(pcreator,pclass_name);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_07 on ").append(tableName).append("(pstates,plife_cycle);");                        
                    }
                }else{
                    if(isParallelOption){
                        scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                        //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_01 on ").append(tableName).append("(obid) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_02 on ").append(tableName).append("(pnames,pclass_name) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_03 on ").append(tableName).append("(powner,pnames) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_04 on ").append(tableName).append("(pmodified,pclass_name) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_05 on ").append(tableName).append("(pcreator,pclass_name) parallel;");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_06 on ").append(tableName).append("(pstates,plife_cycle) parallel;");                        
                    }else{
                        scriptBuf.append(OmcFoundationConstant.newline).append("alter table ").append(tableName).append(" add constraint pk_").append(tableName).append("_01 primary key(obid);");
                        //scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_01 on ").append(tableName).append("(obid);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create unique index uk_").append(indexName).append("_02 on ").append(tableName).append("(pnames,pclass_name);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_03 on ").append(tableName).append("(powner,pnames);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_04 on ").append(tableName).append("(pmodified,pclass_name);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_05 on ").append(tableName).append("(pcreator,pclass_name);");
                        scriptBuf.append(OmcFoundationConstant.newline).append("create index        ix_").append(indexName).append("_06 on ").append(tableName).append("(pstates,plife_cycle);");
                    }
                }
            }
            idexSeq++;
        }
        LOGGER.debug(scriptBuf.toString());
        String str = scriptBuf.toString();
        while(str.indexOf(OmcFoundationConstant.newline + OmcFoundationConstant.newline) >= 0){
            str = str.replace(OmcFoundationConstant.newline + OmcFoundationConstant.newline, OmcFoundationConstant.newline);
        }
        if(!StrUtil.isEmpty(str)) this.writeSqlScripts(str);
    }
    private String getStringForColumn(OmcSchemaTableColumnVO columnInfo){
        return getStringForColumn(columnInfo, OmcSystemConstants.DBMS_CURRENT);
    }
    private String getStringForColumn(OmcSchemaTableColumnVO columnInfo, long currentDBMS){
        StringBuffer strBuf = new StringBuffer("    ");
        String dbmsColumn = StrUtil.RPAD(columnInfo.getDbmsColumn().toLowerCase(),31," ");
        
        if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING || columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" varchar2(").append(columnInfo.getLengths()).append(")");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                if(columnInfo.getLengths() > 3500){
                    strBuf.append(dbmsColumn).append(" text");
                }else{
                    strBuf.append(dbmsColumn).append(" varchar(").append(columnInfo.getLengths()).append(")");
                }
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" number");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" decimal(22,0)");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" number");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" decimal(22,").append(OmcSystemConstants.SCHEMA_FLOATING_POINT_SCALE).append(")");
                
                
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_DATE){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" date");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" datetime");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" number");
            }else if(Bit.isInclude(OmcSystemConstants.DBMS_CURRENT, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" smallint");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_LONG){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" number");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" decimal(22,0)");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" number");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" decimal(22,").append(OmcSystemConstants.SCHEMA_FLOATING_POINT_SCALE).append(")");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" number");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" decimal(22,").append(OmcSystemConstants.SCHEMA_FLOATING_POINT_SCALE).append(")");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" varchar2(").append(columnInfo.getLengths()).append(")");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" varchar(").append(columnInfo.getLengths()).append(")");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" clob");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" text");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" varchar2(").append(columnInfo.getLengths()).append(")");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" varchar(").append(columnInfo.getLengths()).append(")");
            }
        }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_FILE){
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" varchar2(").append(columnInfo.getLengths()).append(")");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" varchar(").append(columnInfo.getLengths()).append(")");
            }
        }else{
            if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_ORACLE)){
                strBuf.append(dbmsColumn).append(" varchar2(").append(columnInfo.getLengths()).append(")");
            }else if(Bit.isInclude(currentDBMS, OmcSystemConstants.DBMS_TYPE_MARIA)){
                strBuf.append(dbmsColumn).append(" varchar(").append(columnInfo.getLengths()).append(")");
            }
        }
        if("N".equals(columnInfo.getNullable())) strBuf.append( " not null");
        return(strBuf.toString());
    }
    public void synchronizeClassProcess(OmcEnvironment.Environment envionment, String propertyUtilClass, String objectRootVOClass){
        StringBuffer sqlStrBuff = new StringBuffer();
        OmcSQLVariableParameter variableParameter = new OmcSQLVariableParameter();
        sqlStrBuff.append ("select a.pclass_name");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("from psysclassinfo a");
        sqlStrBuff.append(OmcFoundationConstant.newline).append("where a.pclass_name not in (#{funVariable_00001},#{funVariable_00002},#{funVariable_00003}");
        sqlStrBuff.append(",#{funVariable_00004},#{funVariable_00005}");
        sqlStrBuff.append(",#{funVariable_00006},#{funVariable_00007}");
        sqlStrBuff.append(",#{funVariable_00008})");

        variableParameter.setFunVariable_00001(OmcSystemConstants.OBJECT_ROOT);
        variableParameter.setFunVariable_00002(OmcSystemConstants.BUSINESS_OBJECT_ROOT);
        variableParameter.setFunVariable_00003(OmcSystemConstants.BUSINESS_OBJECT);
        variableParameter.setFunVariable_00004(OmcSystemConstants.BUSINESS_OBJECT_MASTER);
        variableParameter.setFunVariable_00005(OmcSystemConstants.RELATION_OBJECT_ROOT);
        variableParameter.setFunVariable_00006(OmcSystemConstants.BUSINESS_FILES);
        variableParameter.setFunVariable_00007(OmcSystemConstants.BUSINESS_STRUCTURED_OBJECT);
        variableParameter.setFunVariable_00008(OmcSystemConstants.BUSINESS_STRUCTURED_OBJECT_MASTER);

        variableParameter.setSql(sqlStrBuff.toString());
        List<String> classList = schemaDao.selectList("Common.getDyancmicStringQuery", variableParameter);

        for(String className: classList){
            ClassInfo classInfo = ClassInfoUtil.getClassInfo(className);
            LOGGER.debug("className : " + classInfo.getClassName() + " , packageName : " + classInfo.getJavaPackage());
            String strDomClassStr = getDomClassStr(classInfo, objectRootVOClass);
            String strVoClassStr = getVoClassStr(classInfo);
            writeJavaFileFile(classInfo,strDomClassStr,true);
            writeJavaFileFile(classInfo,strVoClassStr,false);
        }

        CommonService commonService = (CommonService)SpringFactoryUtil.getBean("commonService");
        List<OmcSchemaPropertyVO> variableList = commonService.getVariableList();
        this.writeApplicationConstantFile(variableList, propertyUtilClass);

        List<OmcSchemaAttributeVO> allDefinedList = OmcSchemaAttribute.getAllAttribute();
        this.writeAttributeMessageFile(allDefinedList);



        //        List<String> foundationConstantList = schemaDao.selectList("fcs.getFoundationConstants");
//
//        writeFoundationConstantFile(foundationConstantList);
//
    }

    private String getDomClassStr(ClassInfo classInfo, String objectRootVOClass){
        String strExtends         = "public class @Class extends @ParentClass {";
        String strConstructorObid = "    public @Class(String obid){" + OmcFoundationConstant.newline + "        super(obid);" + OmcFoundationConstant.newline + "    }";
        String strConstructorVO   = "    public @Class(@ClassVO vo){" + OmcFoundationConstant.newline + "        super(vo);" + OmcFoundationConstant.newline   + "    }";
        String strGetVO           = "    @Override" + OmcFoundationConstant.newline + "    public @ClassVO getVo(){"    + OmcFoundationConstant.newline + "        return (@ClassVO)super.getVo();" + OmcFoundationConstant.newline + "    }";
        String strInitializeMaster= "    @Override" + OmcFoundationConstant.newline + "    public void initialize(){"   + OmcFoundationConstant.newline + "        super.initialize();" + OmcFoundationConstant.newline + "        initialize@Class();" + OmcFoundationConstant.newline + "    }";
        String strInitializeSub   = "    public void initialize@Class(){" + OmcFoundationConstant.newline + "    /*code here*/" + OmcFoundationConstant.newline + "    }";
        String strToStrMethod     = "    @Override" + OmcFoundationConstant.newline + "    public String toString() {" + OmcFoundationConstant.newline + "        return \"@Class[toString()=\" + super.toString() + \"]\";" + OmcFoundationConstant.newline + "    }";

        StringBuffer bdomStrBuf = new StringBuffer(this.getClassHeader(classInfo.getClassName(), "DS Shin",false));
        bdomStrBuf.append(OmcFoundationConstant.newline).append("package ").append(StrUtil.replace(classInfo.getJavaPackage(), "model", "dom")).append(";").append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline);

        bdomStrBuf.append(OmcFoundationConstant.newline).append("import java.util.Map;");
        if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Relation)){
            bdomStrBuf.append(OmcFoundationConstant.newline).append("import " + objectRootVOClass + ";");
        }

        bdomStrBuf.append(OmcFoundationConstant.newline).append("import ").append(classInfo.getJavaPackage()).append(".").append(classInfo.getClassName()).append("VO").append(";");
        bdomStrBuf.append(OmcFoundationConstant.newline).append("import ").append(StrUtil.replace(classInfo.getJavaPackageParent(),"model","dom")).append(".").append(classInfo.getClassNameParent()).append(";");
        bdomStrBuf.append(OmcFoundationConstant.newline);
        bdomStrBuf.append(OmcFoundationConstant.newline);

        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(StrUtil.replace(strExtends, "@Class", classInfo.getClassName()),"@ParentClass",classInfo.getClassNameParent()));
        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(strConstructorObid, "@Class", classInfo.getClassName()));
        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(strConstructorVO, "@Class", classInfo.getClassName()));
        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(strGetVO, "@Class", classInfo.getClassName()));
        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(strInitializeMaster, "@Class", classInfo.getClassName()));
        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(strInitializeSub, "@Class", classInfo.getClassName()));
        bdomStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(strToStrMethod, "@Class", classInfo.getClassName()));

        bdomStrBuf.append(OmcFoundationConstant.newline).append(getOwerrideMethod(classInfo));

        bdomStrBuf.append(OmcFoundationConstant.newline).append("}").append(OmcFoundationConstant.newline);
        return(bdomStrBuf.toString());
    }

    private String getVoClassStr(ClassInfo classInfo){
        String strImportDate           = "import java.util.Date;";
        String strImportList           = "import java.util.List;";
        String strImportParseException = "import java.text.ParseException;";
        String strImportDateFormat     = "import java.text.SimpleDateFormat;";
        String strImportBigDecimal     = "import java.math.BigDecimal;";
        
        String strExtends              = "@SuppressWarnings(\"serial\")" + OmcFoundationConstant.newline + "public class @ClassVO extends @ParentClassVO {";

        StringBuffer strBufSetMethodDate = new StringBuffer("    public void    set@Attribute(String    @attribute){");
        strBufSetMethodDate.append(OmcFoundationConstant.newline);
        strBufSetMethodDate.append("        this.@attribute = this.omcConvertStr2Date(@attribute);");
        strBufSetMethodDate.append(OmcFoundationConstant.newline);
        strBufSetMethodDate.append("    }");
        
        String strSetMethodDate = strBufSetMethodDate.toString();
        String strSetMethod            = "    public void    set@Attribute(@type @attribute){" + OmcFoundationConstant.newline + "        this.@attribute = @attribute;" + OmcFoundationConstant.newline + "    }";
        String strGetMethod            = "    public @type get@Attribute(){" + OmcFoundationConstant.newline + "        return @attribute;" + OmcFoundationConstant.newline + "    }";

        StringBuffer voStrBuf = new StringBuffer(this.getClassHeader(classInfo.getClassName(), "DS Shin",true));
        StringBuffer setMethodStrBuf = new StringBuffer();
        StringBuffer getMethodStrBuf = new StringBuffer();

        voStrBuf.append(OmcFoundationConstant.newline).append("package ").append(classInfo.getJavaPackage()).append(";").append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline);
        voStrBuf.append(OmcFoundationConstant.newline).append("import ").append(classInfo.getJavaPackageParent()).append(".").append(classInfo.getClassNameParent()).append("VO").append(";");
        voStrBuf.append(OmcFoundationConstant.newline).append(strImportDate);
        voStrBuf.append(OmcFoundationConstant.newline).append(strImportList);
        voStrBuf.append(OmcFoundationConstant.newline).append(strImportParseException);
        voStrBuf.append(OmcFoundationConstant.newline).append(strImportDateFormat);
        voStrBuf.append(OmcFoundationConstant.newline).append(strImportBigDecimal);
        voStrBuf.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline);
        voStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replace(StrUtil.replace(strExtends, "@Class", classInfo.getClassName()),"@ParentClass",classInfo.getClassNameParent()));

        List<ColumnInfo> columnList = classInfo.getColumnList();

        for(ColumnInfo columnInfo : columnList){
            if("N".equals(columnInfo.getIsInherited())){
                if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING || 
                   columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID)
                {
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" String", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = \"").append(columnInfo.getDefaultValue()).append("\"");
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" Integer", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = ").append(columnInfo.getDefaultValue());
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" Float", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = (float)").append(columnInfo.getDefaultValue());
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_DATE){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" Date", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" Boolean", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = (Boolean)").append(columnInfo.getDefaultValue().toLowerCase());
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_LONG){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" Long", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = (long)").append(columnInfo.getDefaultValue());
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" Double", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = (double)").append(columnInfo.getDefaultValue());
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" BigDecimal", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = new BigDecimal(").append(columnInfo.getDefaultValue()).append(")");
                    }else{
                        voStrBuf.append(" = new BigDecimal(0)");
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET){
                    ;
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING){
                    voStrBuf.append(OmcFoundationConstant.newline).append("    private").append(StrUtil.RPAD(" String", 15, " ")).append(StrUtil.RPAD(columnInfo.getAttributeName(),50," "));
                    if(!StrUtil.isEmpty(columnInfo.getDefaultValue())){
                        voStrBuf.append(" = \"").append(columnInfo.getDefaultValue()).append("\"");
                    }
                    voStrBuf.append(";");
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY){
                    ;
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_FILE){
                    ;
                }
                else
                {
                    ;
                }
                String dataType = "String";
                if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_STRING || 
                        columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_LONGSTRING || 
                   columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_USERID){
                    dataType = "String";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_INTEGER){
                    dataType = "Integer";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_FLOAT){
                    dataType = "Float";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_DATE){
                    dataType = "Date";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_BOOLEAN){
                    dataType = "Boolean";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_LONG){
                    dataType = "Long";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_DOUBLE){
                    dataType = "Double";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_BIGDECIMAL){
                    dataType = "BigDecimal";
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_ATTRIBUTESET){
                    ;
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_ARRAY){
                    ;
                }else if(columnInfo.getDataType() == OmcSystemConstants.SCHEMA_DATA_TYPE_FILE){
                    ;
                }
                String methodName = columnInfo.getAttributeName().substring(0, 1).toUpperCase() + columnInfo.getAttributeName().substring(1);
                setMethodStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(StrUtil.replaceAll(StrUtil.replaceAll(strSetMethod, "@Attribute", methodName),"@attribute",columnInfo.getAttributeName()),"@type",dataType));
                if(columnInfo.getDataType() == 4){
                    setMethodStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(StrUtil.replaceAll(StrUtil.replaceAll(strSetMethodDate, "@Attribute", methodName),"@attribute",columnInfo.getAttributeName()),"@type",dataType));
                }
                getMethodStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(StrUtil.replaceAll(StrUtil.replaceAll(strGetMethod, "@Attribute", methodName),"@attribute",columnInfo.getAttributeName()),"@type",dataType));
            }
        }
        voStrBuf.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline);
        voStrBuf.append(setMethodStrBuf).append(getMethodStrBuf);
        voStrBuf.append(OmcFoundationConstant.newline).append("}").append(OmcFoundationConstant.newline);
        return(voStrBuf.toString());
    }
    private String getClassHeader(String className, String owner, boolean isVo ){
        if(isVo) className = className + "VO";
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("/*");
        strBuf.append(OmcFoundationConstant.newline).append(" * ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append(" * System Name : PLM Project");
        strBuf.append(OmcFoundationConstant.newline).append(" * Program ID : ").append(className).append(".java");
        strBuf.append(OmcFoundationConstant.newline).append(" * ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append(" *  Modification Date      Modifier           Description");
        strBuf.append(OmcFoundationConstant.newline).append(" *      2017.04.??       ").append(owner).append("            Initial");
        strBuf.append(OmcFoundationConstant.newline).append(" * ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append(" */");
        return(strBuf.toString());
    }
    private String getTableHeader(String tableName, String owner){
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("/*");
        strBuf.append(OmcFoundationConstant.newline).append(" * ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append(" * System Name : PLM Project");
        strBuf.append(OmcFoundationConstant.newline).append(" * Table Name : ").append(tableName);
        strBuf.append(OmcFoundationConstant.newline).append(" * ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append(" *  Modification Date      Modifier           Description");
        strBuf.append(OmcFoundationConstant.newline).append(" *      2017.04.??       ").append(owner).append("            Initial");
        strBuf.append(OmcFoundationConstant.newline).append(" * ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append(" */");
        return(strBuf.toString());
    }
    private String getOwerrideMethod(ClassInfo classInfo){
        StringBuffer strBuf = new StringBuffer();
        StringBuffer  chnageMethdBuf = new StringBuffer();
        chnageMethdBuf.append(OmcFoundationConstant.newline).append("    @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){");
        chnageMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForChange(newClassName, newName, newLifeCycle, newStates, map);");
        chnageMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        chnageMethdBuf.append(OmcFoundationConstant.newline).append("    }");
        LOGGER.debug(chnageMethdBuf.toString());
        StringBuffer  createMethdBuf = new StringBuffer();
        createMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForCreate(Map<String, Object> map){");
        createMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForCreate(map);");
        createMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        createMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  deleteMethdBuf = new StringBuffer();
        deleteMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForDelete(Map<String, Object> map){");
        deleteMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForDelete(map);");
        deleteMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        deleteMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  modifyMethdBuf = new StringBuffer();
        modifyMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForModify(Map<String, Object> map){");
        modifyMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForModify(map);");
        modifyMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        modifyMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  withrawalMethdBuf = new StringBuffer();
        withrawalMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForWithdraw(Map<String, Object> map){");
        withrawalMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForWithdraw(map);");
        withrawalMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        withrawalMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  demoteMethdBuf = new StringBuffer();
        demoteMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForDemote(Map<String, Object> map){");
        demoteMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForDemote(map);");
        demoteMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        demoteMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  promoteMethdBuf = new StringBuffer();
        promoteMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForPromote(Map<String, Object> map){");
        promoteMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForPromote(map);");
        promoteMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        promoteMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  reviseMethdBuf = new StringBuffer();
        reviseMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForRevise(Map<String, Object> map){");
        reviseMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForRevise(map);");
        reviseMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        reviseMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  createRelMethdBuf = new StringBuffer();
        createRelMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForCreate(ObjectRootVO fromObject, ObjectRootVO toObject, Map<String,Object> map){");
        createRelMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForCreate(fromObject,toObject,map);");
        createRelMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        createRelMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer  cloneMethdBuf = new StringBuffer();
        cloneMethdBuf.append(OmcFoundationConstant.newline).append("   @Override").append(OmcFoundationConstant.newline).append("    protected void @methodTypeForClone(Map<String, Object> map){");
        cloneMethdBuf.append(OmcFoundationConstant.newline).append("        super.@methodTypeForClone(map);");
        cloneMethdBuf.append(OmcFoundationConstant.newline).append("        /*code below*/").append(OmcFoundationConstant.newline);
        cloneMethdBuf.append(OmcFoundationConstant.newline).append("    }");

        StringBuffer rtnStrBuf = new StringBuffer();

        if(Bit.isInclude(classInfo.getClassInfoFlags(), OmcSystemConstants.CLASSINFO_FLAG_Business))
        {
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(chnageMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(chnageMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(chnageMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(createMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(createMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(createMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(deleteMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(deleteMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(deleteMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(modifyMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(modifyMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(modifyMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(withrawalMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(withrawalMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(withrawalMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(demoteMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(demoteMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(demoteMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(promoteMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(promoteMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(promoteMethdBuf.toString(), "@methodType", "postProcess"));
            
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(cloneMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(cloneMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(cloneMethdBuf.toString(), "@methodType", "postProcess"));
            
            if(Bit.isInclude(classInfo.getFlags(), OmcSystemConstants.BUSINESS_FLAG_Revisible)){
                rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(reviseMethdBuf.toString(), "@methodType", "validate"));
                rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(reviseMethdBuf.toString(), "@methodType", "preProcess"));
                rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(reviseMethdBuf.toString(), "@methodType", "postProcess"));
            }
        }else{
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(deleteMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(deleteMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(deleteMethdBuf.toString(), "@methodType", "postProcess"));

            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(createRelMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(createRelMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(createRelMethdBuf.toString(), "@methodType", "postProcess"));
            
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(modifyMethdBuf.toString(), "@methodType", "validate"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(modifyMethdBuf.toString(), "@methodType", "preProcess"));
            rtnStrBuf.append(OmcFoundationConstant.newline).append(StrUtil.replaceAll(modifyMethdBuf.toString(), "@methodType", "postProcess"));
            
        }
        LOGGER.debug(rtnStrBuf.toString());
        return(rtnStrBuf.toString());
    }
    /**
    *
    * @param classInfo
    * @param javaContents
    * @param isDom
    */

   private String getJavaSourceRootDir() {
       String rootDir = System.getProperty("user.dir");
       String[] strArray = rootDir.split(Pattern.quote("\\"));
       rootDir = rootDir.replace(strArray[strArray.length-1], "");
       rootDir = rootDir + "\\rap-business-object\\src\\";
       return rootDir;
   }
   private void writeJavaFileFile(ClassInfo classInfo, String javaContents, boolean isDom){
       StringBuffer sb = new StringBuffer();
       String rootDir = getJavaSourceRootDir();
       sb.append(rootDir);
       if(isDom){
           sb.append(classInfo.getJavaPackage().replace(".", "\\").replace("model", "dom"));
       }else{
           sb.append(classInfo.getJavaPackage().replace(".", "\\"));
       }
       new File(sb.toString()).mkdirs();
       sb.append("\\");
       if(isDom){
           sb.append(classInfo.getClassName()+".java");
       }else{
           sb.append(classInfo.getClassName()+"VO.java");
       }
       LOGGER.debug(sb.toString());
       try {
           File file = new File(sb.toString());
           if(isDom){
               if(!file.exists()){
                   file.createNewFile();
                   BufferedWriter out = new BufferedWriter(new FileWriter(sb.toString()));
                   out.write(javaContents);
                   out.newLine();
                   out.close();
               }
           }else{
               file.createNewFile();
               BufferedWriter out = new BufferedWriter(new FileWriter(sb.toString()));
               out.write(javaContents);
               out.newLine();
               out.close();
           }
         } catch (IOException e) {
             System.err.println(e);
         }
   }
   /**
   *
   * @param classInfo
   * @param javaContents
   * @param isDom
   */
    private void writeSqlScripts(String sqlScriptContents){
      StringBuffer sb = new StringBuffer();
      String rootDir = System.getProperty("user.dir");
      String subDir = "\\sqlscript\\";
      sb.append(rootDir).append(subDir);
      new File(sb.toString()).mkdirs();
      sb.append("schema_sql.sql");
      LOGGER.debug(sb.toString());
      try {
          File file = new File(sb.toString());
          file.createNewFile();
          BufferedWriter out = new BufferedWriter(new FileWriter(sb.toString()));
          out.write(sqlScriptContents);
          out.newLine();
          out.close();
        } catch (IOException e) {
            System.err.println(e);
        }
  }
    /**
     *
     * @param foundationConstantList
     */
    private void writeFoundationConstantFile(List<String> foundationConstantList){
        // TODO Auto-generated method stub
        StringBuffer sb = new StringBuffer();
        String rootDir = (new File(System.getProperty("user.dir"))).getParent();
        String subDir = "\\omc-foundation\\src\\main\\java\\omc\\foundation\\constants\\";
        String fileName = "FoundationConstants.java";

        sb.append(rootDir).append(subDir).append(fileName);

        File file = new File(sb.toString());

        try {
            file.createNewFile();

            BufferedWriter out = new BufferedWriter(new FileWriter(sb.toString()));

            out.write("package omc.foundation.constants;");
            out.newLine();
            out.newLine();
            out.write("import omc.api.util.PropertyUtil;");
            out.newLine();
            out.newLine();
            out.write("public class FoundationConstants {");
            out.newLine();
            out.newLine();

            for(String applicationConstant : foundationConstantList){
                out.write("\t\t" + applicationConstant);
                out.newLine();
            }

            out.write("\t\t" + "public static final String RELCLASS_WORKFLOWOBJECTROUTE = PropertyUtil.getProperty(\"RELCLASS_WORKFLOWOBJECTROUTE\");");
            out.newLine();
            out.write("\t\t" + "public static final String BIZCLASS_WORKFLOWROUTE = PropertyUtil.getProperty(\"BIZCLASS_WORKFLOWROUTE\");");
            out.newLine();
            out.write("\t\t" + "public static final String RELCLASS_WORKFLOWROUTESTEP = PropertyUtil.getProperty(\"RELCLASS_WORKFLOWROUTESTEP\");");
            out.newLine();
            out.write("\t\t" + "public static final String BIZCLASS_WORKFLOWSTEP = PropertyUtil.getProperty(\"BIZCLASS_WORKFLOWSTEP\");");
            out.newLine();
            out.write("\t\t" + "public static final String BIZCLASS_FILES = PropertyUtil.getProperty(\"BIZCLASS_FILES\");");
            out.newLine();
            out.write("\t\t" + "public static final String SYSTEM_ATTRIBUTE_DELIMINATOR_VALUE = PropertyUtil.getProperty(\"SYSTEM_ATTRIBUTE_DELIMINATOR_VALUE\");");
            out.newLine();
            out.write("\t\t" + "public static final String SYSTEM_ATTRIBUTE_DELIMINATOR_NAME = PropertyUtil.getProperty(\"SYSTEM_ATTRIBUTE_DELIMINATOR_NAME\");");
            out.newLine();
            out.newLine();
            out.write("}");
            out.newLine();
            out.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param variableList
     */
    private void writeApplicationConstantFile(List<OmcSchemaPropertyVO> variableList, String propertyUtilClass){

        StringBuffer sb = new StringBuffer();
        String rootDir = getJavaSourceRootDir();
        String subDir = "\\rap\\application\\constants\\";
        sb.append(rootDir).append(subDir);

        new File(sb.toString()).mkdirs();

        String fileName = "ApplicationSchemaConstants.java";
        sb.append(fileName);
        File file = new File(sb.toString());
        try {
            file.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(sb.toString()));
            StringBuffer strBuf = new StringBuffer();

            strBuf.append("package rap.application.constants;");
            strBuf.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline).append("import " + propertyUtilClass + ";");
            strBuf.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline).append("public class ApplicationSchemaConstants {");
            for(OmcSchemaPropertyVO property : variableList){
                strBuf.append(OmcFoundationConstant.newline).append("    public static final String ").append(property.getPropertyName().toUpperCase()).append(" = ").append("PropertyUtil.getProperty(\"").append(property.getPropertyName()).append("\");");
            }
            strBuf.append(OmcFoundationConstant.newline).append("}").append(OmcFoundationConstant.newline);
            out.write(strBuf.toString());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void writeAttributeMessageFile(List<OmcSchemaAttributeVO> allDefinedList){
        StringBuffer sb = new StringBuffer();
        String rootDir = System.getProperty("user.dir");
        String subDir = "\\src\\main\\resources\\message\\";
        sb.append(rootDir).append(subDir);

        new File(sb.toString()).mkdirs();

        String fileName_en = sb.toString() + "attribute_en.properties";
        String fileName_ko = sb.toString() + "attribute_ko.properties";

        File file_en = new File(fileName_en);
        File file_ko = new File(fileName_ko);
        String moduleSaved = "Initial";
        try {
            file_en.createNewFile();
            file_ko.createNewFile();

            BufferedWriter out_en = new BufferedWriter(new FileWriter(file_en));
            BufferedWriter out_ko = new BufferedWriter(new FileWriter(file_ko));

            StringBuffer strBuf_en = new StringBuffer(this.getDisplayedNamesFileDesc());
            StringBuffer strBuf_ko = new StringBuffer(this.getDisplayedNamesFileDesc());

            for(OmcSchemaAttributeVO property : allDefinedList){
                if (!moduleSaved.equals(property.getModuleName())){
                    strBuf_en.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline).append("#Module : ").append(property.getModuleName());
                    strBuf_ko.append(OmcFoundationConstant.newline).append(OmcFoundationConstant.newline).append("#Module : ").append(property.getModuleName());
                }
                strBuf_en.append(OmcFoundationConstant.newline).append("attr.").append(property.getNames()).append("=").append(property.getDisplayedNames());
                strBuf_ko.append(OmcFoundationConstant.newline).append("attr.").append(property.getNames()).append("=").append(property.getDisplayedNamesKr());
                moduleSaved = property.getModuleName();
            }
            out_en.write(strBuf_en.toString());
            out_ko.write(strBuf_ko.toString());

            out_en.close();
            out_ko.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getDisplayedNamesFileDesc(){
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(OmcFoundationConstant.newline).append("#===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append("# System Name : PLM Project");
        strBuf.append(OmcFoundationConstant.newline).append("# File Desc   : Display Name Definition For Attribute");
        strBuf.append(OmcFoundationConstant.newline).append("#               You cannot change manually this file. ");
        strBuf.append(OmcFoundationConstant.newline).append("#               System will recreate automatically.");
        strBuf.append(OmcFoundationConstant.newline).append("# ===================================================================");
        strBuf.append(OmcFoundationConstant.newline).append("#  Modification Date      Modifier           Description");
        strBuf.append(OmcFoundationConstant.newline).append("#      2017.04.??         DS Shin               Initial");
        strBuf.append(OmcFoundationConstant.newline).append("# ===================================================================");
        return(strBuf.toString());
    }
    public void txnSchemaUpload(OmcEnvironment.Environment envionment,
                                String schemaDir,
                                boolean isClassAttrUpload,
                                boolean isMenuUpload,
                                boolean isLifeCycleUpload,
                                boolean isEtcUpload,
                                boolean isConstantUpload,
                                boolean isTemporaryOnly,
                                Map<String,String> schemaExcelScheet){

        if(envionment == OmcEnvironment.Environment.PRODUCTION && isLifeCycleUpload){
            throw new FoundationException("Cannot Life Cycle Schema Upload To Production");
        }
        String workingDir = System.getProperty("user.dir") + "\\schema\\";
        new File(workingDir).mkdirs();
        
        String workingFileSchemaClass     = workingDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_CLASSATTR);
        String workingFileSchemaConstants = workingDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_CONSTANTS);
        String workingFileSchemaLifeCycle = workingDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_LIFECYCLE);
        String workingFileSchemaMenu      = workingDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_MENU);
        String workingFileSchemaEtc       = workingDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_ETC);

        long uploadOption = 0;
        if(isTemporaryOnly) uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_IS_TEMPORARY_ONLY);
        
        if(isClassAttrUpload){
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_ATTR_DISPLAYED);            
        }
        if(isMenuUpload){
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_MENU_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_PAGE_LAYOUT_UPLOAD);            
        }
        if(isLifeCycleUpload){
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_LIFE_CYCLE_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_STATE_INFO_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_BRANCH_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_PARAMETER_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_STATE_TRIGGER_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_FILE_FORMAT_UPLOAD);            
        }
        if(isEtcUpload){
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_SITE_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_STORE_LOCATION_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_ROLE_GROUP_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_ACCESS_METHOD_UPLOAD);
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_DYNAMIC_ATTR_UPLOAD);            
        }
        if(isConstantUpload){
            uploadOption = Bit.or(uploadOption,OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD);
        }
        try {
            if (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CONSTANT_UPLOAD)){
                OmcFileUtil.copyFile(schemaDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_CONSTANTS), workingFileSchemaConstants,true);
                this.uploadSystemConstants(workingFileSchemaConstants,uploadOption,omcSchemaExcelImportService,omcSchemaCoreService);
            }

            if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_SITE_UPLOAD))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STORE_LOCATION_UPLOAD))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ROLE_GROUP_UPLOAD))  ||   //개발해야 함.
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ACCESS_METHOD_UPLOAD))  ||//개발해야 함.
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_DYNAMIC_ATTR_UPLOAD))){//개발해야 함.
                
                OmcFileUtil.copyFile(schemaDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_ETC), workingFileSchemaEtc,true);
                this.uploadEtc(workingFileSchemaEtc,uploadOption,omcSchemaExcelImportService,omcSchemaCoreService);
            }
            if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_BIZ_CLASS)) ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_REL_CLASS))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_CLASS_UPLOAD_ATTR))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_ATTR_DISPLAYED))){
                
                OmcFileUtil.copyFile(schemaDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_CLASSATTR), workingFileSchemaClass,true);
                this.uploadClassAndAttr(workingFileSchemaClass,uploadOption,omcSchemaExcelImportService,omcSchemaCoreService);
            }
            if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_LIFE_CYCLE_UPLOAD)) ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_INFO_UPLOAD))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_BRANCH_UPLOAD))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PARAMETER_UPLOAD))  ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_STATE_TRIGGER_UPLOAD)) ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_FILE_FORMAT_UPLOAD))){
                
                OmcFileUtil.copyFile(schemaDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_LIFECYCLE), workingFileSchemaLifeCycle,true);
                this.uploadLifeCycles(workingFileSchemaLifeCycle,uploadOption,omcSchemaExcelImportService,omcSchemaCoreService);
            }
            if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_MENU_UPLOAD)) ||
                (Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_PAGE_LAYOUT_UPLOAD))){
                
                OmcFileUtil.copyFile(schemaDir + schemaExcelScheet.get(OmcSchemaExcelFileConstants.C_FILE_NAME_MENU), workingFileSchemaMenu,true);
                this.uploadMemusAndLayouts(workingFileSchemaMenu,uploadOption,omcSchemaExcelImportService,omcSchemaCoreService);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        	throw new FoundationException(e);
        }

        LOGGER.info("=======================================================");
    }
    //Complete
    private void uploadSystemConstants(String targetFileSchemaConstants, long uploadOption,OmcSchemaExcelImportService omcSchemaExcelImportService,OmcSchemaCoreService omcSchemaCoreService)throws IOException, InvalidFormatException,Exception{
        Map<String, Object> map = new HashMap<String,Object>();
        omcSchemaExcelImportService.getSystemConstants(uploadOption, targetFileSchemaConstants, map);
        omcSchemaExcelImportService.uploadSystemConstants(uploadOption, map);
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_IS_TEMPORARY_ONLY))) return;
        omcSchemaCoreService.txnUploadSystemConstants(uploadOption);
    }
    //Complete
    private void uploadClassAndAttr(String targetFileSchemaClass, long uploadOption,OmcSchemaExcelImportService   omcSchemaExcelImportService,OmcSchemaCoreService omcSchemaCoreService)throws IOException, InvalidFormatException,Exception{
        Map<String, Object> map = new HashMap<String,Object>();
        omcSchemaExcelImportService.getClassAndAttr(uploadOption, targetFileSchemaClass, map);
        omcSchemaExcelImportService.uploadClassAndAttr(uploadOption, map);
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_IS_TEMPORARY_ONLY))) return;
        omcSchemaCoreService.txnUploadClassAndAttr(uploadOption);
    }
  //Complete
    private void uploadMemusAndLayouts(String targetFileSchemaMenu, long uploadOption,OmcSchemaExcelImportService   omcSchemaExcelImportService,OmcSchemaCoreService omcSchemaCoreService)throws IOException, InvalidFormatException,Exception{
        Map<String, Object> map = new HashMap<String,Object>();
        omcSchemaExcelImportService.getMemusAndLayouts(uploadOption, targetFileSchemaMenu, map);
        omcSchemaExcelImportService.uploadMemusAndLayouts(uploadOption, map);
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_IS_TEMPORARY_ONLY))) return;
        omcSchemaCoreService.txnUploadMemusAndLayouts(uploadOption);
    }
    //Complete
    private void uploadLifeCycles(String targetFileSchemaLifeCycle, long uploadOption,OmcSchemaExcelImportService   omcSchemaExcelImportService,OmcSchemaCoreService omcSchemaCoreService)throws IOException, InvalidFormatException,Exception{
        Map<String, Object> map = new HashMap<String,Object>();
        omcSchemaExcelImportService.getLifeCycles(uploadOption, targetFileSchemaLifeCycle, map);
        omcSchemaExcelImportService.uploadLifeCycles(uploadOption, map);
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_IS_TEMPORARY_ONLY))) return;
        omcSchemaCoreService.txnUploadLifeCycles(uploadOption);
    }
    private void uploadEtc(String targetFileSchemaEtc, long uploadOption,OmcSchemaExcelImportService   omcSchemaExcelImportService,OmcSchemaCoreService omcSchemaCoreService)throws IOException, InvalidFormatException,Exception{
        Map<String, Object> map = new HashMap<String,Object>();
        omcSchemaExcelImportService.getEtcSchemas(uploadOption, targetFileSchemaEtc, map);
        omcSchemaExcelImportService.uploadEtcSchemas(uploadOption, map);
        if ((Bit.isInclude(uploadOption, OmcSchemaConstants.C_SCHEMA_IS_TEMPORARY_ONLY))) return;
        omcSchemaCoreService.txnUploadSchemaEtc(uploadOption);
    }
    /**
     * 
     * @see omc.schema.service.OmcSchemaManagementService#initialSchemaSetupMain()
     */
    @Override
    public void txnInitialSchemaSetupMain(OmcEnvironment.Environment envionment,String defaultSite){
        OmcSchemaServiceUtils.initialSchemaSetupMain(defaultSite);
    }
}
