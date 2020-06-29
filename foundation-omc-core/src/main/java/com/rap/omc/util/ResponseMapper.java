/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : ResponseMapper.java
 * ===========================================
 * Modify Date    Modifier    Description
 * -------------------------------------------
 * 2015. 2. 12.  youngs2.park   Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.util.HashMap;

import org.springframework.ui.ModelMap;

import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.constants.ResponseConstants;


/**
 * <pre>
 * Class : ResponseMapper
 * Description : TODO
 * </pre>
 *
 * @author youngs2.park
 */
public class ResponseMapper{



/*
    STATUS_MAX_LOGIN_ATTEMPTS_EXCEEDED // -6
    STATUS_UPDATE_WITHOUT_PK_ERROR // -9
    STATUS_OFFLINE -> browser off-line // 1
    STATUS_CONNECTION_RESET_ERROR // -92, socket reset by proxy server
    STATUS_LOGIN_INCORRECT  // -5
    STATUS_LOGIN_SUCCESS    // -8
    else if( status == RPCResponse.STATUS_SERVER_TIMEOUT ){ // -100
    else if( status == RPCResponse.STATUS_TRANSPORT_ERROR ){ // -90
*/
    protected int    statusCode = ResponseConstants.STATUS_SUCCESS;
    protected String message    = "";

    protected HashMap<String, Object> response = new HashMap<String, Object>();

    public void setStatusCode( int newStatusCode ){
        this.statusCode = newStatusCode;
    }
    public int getStatusCode(){
        return this.statusCode;
    }

    public void setMessage( String newMessage ){
        this.message = newMessage;
    }
    public String getMessage(){
        return this.message;
    }

    public void setMessageId( String messageId ){
//
    }

    public void setData( Object object ){
        this.setData( ResponseConstants.ID_UNKNOWN,  object );
    }
    public void setData( String id, Object object ){
        this.response.put( id,  object );
    }

    public Object getData(){
        return this.getData( ResponseConstants.ID_UNKNOWN );
    }
    public Object getData( String id ){
        return this.response.get( id );
    }

    public void setModelMap( ModelMap model ){
        /*
        for( Entry<String, Object> entry : this.response.entrySet()) {
             model.addAttribute( entry.getKey(), entry.getValue() );
        }
        */
        model.addAttribute(GlobalConstants.M_STATUS_CODE, this.statusCode );
        model.addAttribute(GlobalConstants.M_MESSAGE,    this.message );
        model.addAttribute(GlobalConstants.M_DATA,       this.response );
    }
}