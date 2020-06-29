/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : a.java
 * ===========================================
 * Modify Date Modifier Description
 * -------------------------------------------
 * 2015. 2. 9. jongjung.kwon Initial
 * ===========================================
 */
package com.rap.omc.api.object.dom;

import java.util.Map;

import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.general.NameGeneratorUtil;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.foundation.file.model.FcsLocationVO;
import com.rap.omc.foundation.file.model.FileServiceURLVO;
import com.rap.omc.schema.util.OmcApplicationConstants;
import com.rap.omc.schema.util.OmcUniqueIDGenerator;
import com.rap.omc.util.file.FCSServerLocationUtil;
import com.rap.omc.util.file.HttpClientUtil;
/**
 * 
 * <pre>
 * Class : Files
 * Description : File관리를 위한 Class
 * </pre>
 * 
 * @author s_dongsshin
 */
public class Files extends BusinessObjectMaster {
    public Files(String obid){
        super(obid);
        FileServiceURLVO fileServiceURLVO = FCSServerLocationUtil.getFileServiceURLForDownloadWithLocation(this.getVo().getFileLocation());
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileLocationPhysicalPath, fileServiceURLVO.getFileRootPath());
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_fileServiceUrl, fileServiceURLVO.getServiceUrl());                    
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKOUT_URL, fileServiceURLVO.getServiceCheckOutUrl());
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKIN_URL, fileServiceURLVO.getServiceCheckOutUrl());
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKOUT_FROMFMSTOSERVER_URL, fileServiceURLVO.getServiceCheckOutFromServerUrl());
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKIN_FROMFMSTOSERVER_URL, fileServiceURLVO.getServiceCheckInFromServerUrl());
        this.getVo().getOutData().put(OmcApplicationConstants.FILE_OUTATA_CHECKOUT_ALL_URL, fileServiceURLVO.getServiceCheckOutAllUrl());
    }
    public Files(FilesVO vo){
        super(vo);
    }
    @Override
    public FilesVO getVo(){
        return (FilesVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeFiles();
    }
    public void initializeFiles(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "Files[toString()=" + super.toString() + "]";
    }


    @Override
    protected void validateForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.validateForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void preProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.preProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

    @Override
    protected void postProcessForChange(String newClassName, String newName, String newLifeCycle, String newStates, Map<String, Object> map){
        super.postProcessForChange(newClassName, newName, newLifeCycle, newStates, map);
        /*code below*/

    }

   @Override
    protected void validateForCreate(Map<String, Object> map){
        super.validateForCreate(map);
        /*code below*/

    }
   @Override
    protected void preProcessForCreate(Map<String, Object> map){
        super.preProcessForCreate(map);
        /*code below*/
        this.getVo().setNames(NameGeneratorUtil.generateUniqueName(GlobalConstants.IDGEN_IDKEY_FILE));
        this.getVo().setLifeCycle(OmcApplicationConstants.FILES_LIFE_CYCLE);
        this.getVo().setStates(OmcApplicationConstants.FILES_STATES);
        this.getVo().setFileTimestamp(OmcUniqueIDGenerator.getObid());
    }

   @Override
    protected void postProcessForCreate(Map<String, Object> map){
        super.postProcessForCreate(map);
        /*code below*/

    }

   @Override
    protected void validateForDelete(Map<String, Object> map){
        super.validateForDelete(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDelete(Map<String, Object> map){
        super.preProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDelete(Map<String, Object> map){
        super.postProcessForDelete(map);
        /*code below*/

    }

   @Override
    protected void validateForModify(Map<String, Object> map){
        super.validateForModify(map);
        /*code below*/

    }

   @Override
    protected void preProcessForModify(Map<String, Object> map){
        super.preProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void postProcessForModify(Map<String, Object> map){
        super.postProcessForModify(map);
        /*code below*/

    }

   @Override
    protected void validateForWithdraw(Map<String, Object> map){
        super.validateForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void preProcessForWithdraw(Map<String, Object> map){
        super.preProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void postProcessForWithdraw(Map<String, Object> map){
        super.postProcessForWithdraw(map);
        /*code below*/

    }

   @Override
    protected void validateForDemote(Map<String, Object> map){
        super.validateForDemote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForDemote(Map<String, Object> map){
        super.preProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForDemote(Map<String, Object> map){
        super.postProcessForDemote(map);
        /*code below*/

    }

   @Override
    protected void validateForPromote(Map<String, Object> map){
        super.validateForPromote(map);
        /*code below*/

    }

   @Override
    protected void preProcessForPromote(Map<String, Object> map){
        super.preProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void postProcessForPromote(Map<String, Object> map){
        super.postProcessForPromote(map);
        /*code below*/

    }

   @Override
    protected void validateForClone(Map<String, Object> map){
        super.validateForClone(map);
        /*code below*/

    }

    @Override
    protected void preProcessForClone(Map<String, Object> map){
        super.preProcessForClone(map);
        /*code below*/

    }

   @Override
    protected void postProcessForClone(Map<String, Object> map){
        super.postProcessForClone(map);
        /*code below*/

    }
    public void changeModifyFileInfo( FilesVO file ){
       getVo().setDescriptions( file.getDescriptions() );
       getVo().setSizes( file.getSizes() );
       getVo().setSysFileName( file.getSysFileName() );
       getVo().setFilePath( file.getFilePath() );
       getVo().setUserFileName( file.getUserFileName() );
       getVo().setTitles( file.getUserFileName() );        
    }
    /**
     * 화일에 대한 각 Service 정보를 Return함.(CheckIn URL, Copy URL....)
     *
     * @return
     */
    public FileServiceURLVO getServiceURLInfo(){
        return FCSServerLocationUtil.getFileServiceURLForDownloadWithLocation(this.getLocationInfo().getLocationName());
    }
    /**
     * 화일에 대한 Store의 정보를 Return함.
     *
     * @return
     */
    public FcsLocationVO getStoreInfo(){
        return FCSServerLocationUtil.getFileLocation(this.getVo().getFileStore());
    }
    /**
     * 화일에 대한 Location의 정보를 Return함.
     *
     * @return
     */
    public FcsLocationVO getLocationInfo(){
        return FCSServerLocationUtil.getFileLocation(this.getVo().getFileLocation());
    }
    /**
     * 해당 화일에 대한 FCS Full File Path를 Return함.
     *
     * @return
     */
    public String getFullFilePath(){
        return (String)this.getVo().getOutData().get(OmcApplicationConstants.FILE_OUTATA_fileLocationPhysicalPath) + this.getVo().getFilePath();
    }
    /**
     * FCS 서버의 File을 Check Out하여 Server혹은 Client에 Download함.
     *
     * @param fileVO
     * @return
     */
    public final String checkOutPhysicalFile(String temporaryWorkingDir){
       String localTemporaryFile = HttpClientUtil.checkOutPhysicalFile(temporaryWorkingDir, this.getVo());
       return localTemporaryFile;
    }
}

