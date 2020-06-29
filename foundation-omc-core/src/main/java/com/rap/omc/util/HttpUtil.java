/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : HttpUtil.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 8. 24.  jongjung.kwon   Initial
 * ===========================================
 */
package com.rap.omc.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rap.omc.api.util.MessageUtil;
import com.rap.omc.framework.exception.FoundationException;



/**
 * <pre>
 * Class : HttpUtil
 * Description : TODO
 * </pre>
 * 
 * @author jongjung.kwon
 */
public class HttpUtil {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);
    private String boundary = null;
    private static final String LINE_FEED = "\r\n";
    private HttpURLConnection httpConn;
    private OutputStream outputStream;
    private PrintWriter writer;
    private StringBuilder postData = new StringBuilder();
    
    private static final int TIME_OUT = 5000;
    private static final int BUFFER_SIZE = 4096;

    public void setUrl(String requestURL, String charset)  throws IOException {
        boundary = "===" + System.currentTimeMillis() + "===";
        URL url = new URL(requestURL);
        httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setConnectTimeout(TIME_OUT * 1000);
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("POST");
        //httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", "");
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("Cache-Control", "no-cache");
        httpConn.setRequestProperty("Accept-Charset", "UTF-8");
        httpConn.setRequestProperty("X-Requested-With", "XMLHttpRequest");
        httpConn.setRequestProperty("Pdm-Server-Name", "VC-GPDM");
        httpConn.setChunkedStreamingMode(1024*1024*2);
        outputStream = httpConn.getOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),true);
    }
    public void addHeaderField(String name, String value) {
      writer.append(name + ": " + value).append(LINE_FEED);
      writer.flush();
    }
    public void addFormField(String name, String value) throws UnsupportedEncodingException {       
        if (postData.length() != 0) postData.append('&');
        postData.append(URLEncoder.encode(name, "UTF-8")).append("=").append(URLEncoder.encode(value, "UTF-8"));
     }
    public void finish(String downFile, String fileName) {
        if (postData.length() != 0){
            String urlParameters = postData.toString();
            writer.append(urlParameters);
        }
        writer.close();
        FileOutputStream fos = null;
        InputStream      is  = null;
        try{
            fos = new FileOutputStream(downFile);
            int httpStatus = httpConn.getResponseCode();
            if (httpStatus == HttpURLConnection.HTTP_OK) {
                is = httpConn.getInputStream();
                byte[] buffer = new byte[1024];
                int readBytes;
                while ((readBytes = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, readBytes);
                }
            }else if(httpStatus == HttpURLConnection.HTTP_NOT_FOUND){
                throw new FoundationException("api.object.error.file.noFileInFcs", new Object[] { fileName });
            }else{
                throw new IOException("Server returned non-OK status: " + httpStatus);
            }
        }catch(Exception e){
            LOGGER.debug("message : " +MessageUtil.getMessage("api.object.error.file.noFileInFcs",  new Object[] { fileName }));
            throw new FoundationException("api.object.error.file.noFileInFcs", new Object[] { fileName }, e);
        }finally{
            if(!NullUtil.isNull(writer)) writer.close();
            if(!NullUtil.isNull(fos)){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!NullUtil.isNull(is)){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!NullUtil.isNull(httpConn)){
                httpConn.disconnect();
            }
        }
    }
    public String formFinish() {
        if (postData.length() != 0) writer.append(postData.toString());
        writer.close();
        StringBuffer strBuf = new StringBuffer();
        InputStream inputStream = null;
        BufferedReader readBuf = null;
        try{
            int httpStatus = httpConn.getResponseCode();
            if (httpStatus == HttpURLConnection.HTTP_OK) {
                inputStream = httpConn.getInputStream();
                readBuf = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = readBuf.readLine()) != null) {
                    strBuf.append(line);
                }
            }else{
                throw new FoundationException("[Foundation.HttpUtil.formFinish]Server returned non-OK status:" + httpStatus);
            }
        }catch(Exception e){
            LOGGER.debug("[Foundation.HttpUtil.formFinish]Error Message:" + e.getMessage());
            throw new FoundationException("[Foundation.HttpUtil.formFinish]Error Message:" + e.getMessage(), e);
        }finally{
            if(!NullUtil.isNull(writer)) writer.close();
            if(!NullUtil.isNull(inputStream)){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!NullUtil.isNull(readBuf)){
                try {
                    readBuf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!NullUtil.isNull(httpConn)){
                httpConn.disconnect();
            }
        }
        return strBuf.toString();
    }
    public String formFinish(String fileName) {
        if (postData.length() != 0){
            String urlParameters = postData.toString();
            LOGGER.debug("urlParameters : " +urlParameters);
            writer.append(urlParameters);
        }
        writer.close();
        InputStream is = null;
        StringBuffer sb = new StringBuffer();
        BufferedReader rd = null;
        try{
            int status = httpConn.getResponseCode();
            LOGGER.debug("statusCode : " +status);
            if (status == HttpURLConnection.HTTP_OK) {
                is = httpConn.getInputStream();
                rd = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
            }else if(status == HttpURLConnection.HTTP_NOT_FOUND){
                throw new FoundationException("api.object.error.file.noFileInFcs", new Object[] { fileName });
            }else{
                throw new IOException("Server returned non-OK status: " + status);
            }
        }catch(Exception e){
            LOGGER.debug("message : " +MessageUtil.getMessage("api.object.error.file.noFileInFcs",  new Object[] { fileName }));
            throw new FoundationException("api.object.error.file.noFileInFcs", new Object[] { fileName }, e);
        }finally{
            if(!NullUtil.isNull(writer)) writer.close();
            if(!NullUtil.isNull(is)){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!NullUtil.isNull(rd)){
                try {
                    rd.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(!NullUtil.isNull(httpConn)){
                httpConn.disconnect();
            }
        }
        return sb.toString();
    }
    public String downloadFile(String saveDir, String fileName) throws IOException {
        if (postData.length() != 0)  writer.append(postData.toString());
        writer.close();
        String saveFilePath = "";
        int responseCode = httpConn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStream inputStream = httpConn.getInputStream();
            saveFilePath = saveDir + File.separator + fileName;
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);
            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
        } else {
            throw new IOException("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
        return saveFilePath;
    }
}