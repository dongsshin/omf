package com.rap.omc.dataaccess.paging.executer;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.dataaccess.paging.exception.PagingException;

@SuppressWarnings({"rawtypes","unchecked"})
public class DatabaseUrlHolder{

    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseUrlHolder.class);
    private static DatabaseUrlHolder metaData = new DatabaseUrlHolder();
    
	private Map metaDataMap = new HashMap();
    public static DatabaseUrlHolder getInstance(){
        return metaData;
    }
	public void setDatabaseUrl(DataSource dataSource, String databaseUrl)
    {
    	this.metaDataMap.put(dataSource, databaseUrl);
    }
    public String getDatabaseUrl(DataSource dataSource)
    {
        String databaseUrl = (String)this.metaDataMap.get(dataSource);
        if (databaseUrl == null) {
            Connection conn = null;
            try {
                conn = dataSource.getConnection();
                DatabaseMetaData meta = conn.getMetaData();
                databaseUrl = meta.getURL();
                getInstance().setDatabaseUrl(dataSource, databaseUrl);
            }catch (SQLException e) {
                throw new PagingException("FRM_CNN_004 : create(String statementStr, String pagingDbTypeSpec, PagingEntity pagingData, DataSource dataSource)",e);
            } finally {
                if (conn != null) {
               	    try {
                         conn.close();
                     }catch (Exception ex) {
                         LOGGER.error("getDatabaseUrl method of DatabaseUrlHolder [ {} ] don't close DB Connection ", this, ex);
                     }
                }
            }
        }
        return databaseUrl;
    }
}
