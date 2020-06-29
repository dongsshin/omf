/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : DesktopTile.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.common.dom;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rap.omc.api.object.dom.Files;
import com.rap.omc.api.object.dom.ObjectRoot;
import com.rap.omc.api.object.model.BusinessRelationObjectVO;
import com.rap.omc.api.object.model.FilesVO;
import com.rap.omc.api.util.DomUtil;
import com.rap.omc.api.util.general.RelationShip;
import com.rap.omc.constants.GlobalConstants;
import com.rap.omc.framework.exception.ApplicationException;
import com.rap.omc.util.NullUtil;
import com.rap.omc.util.StringUtil;

import rap.api.object.common.model.DesktopTileVO;
import rap.api.object.organization.dom.Users;
import rap.api.object.relation.model.DesktopTile2TileVO;
import rap.application.constants.ApplicationSchemaConstants;
import rap.application.constants.DesktopConstants;


public class DesktopTile extends DesktopItem {
    public DesktopTile(String obid){
        super(obid);
    }
    public DesktopTile(DesktopTileVO vo){
        super(vo);
    }
    @Override
    public DesktopTileVO getVo(){
        return (DesktopTileVO)super.getVo();
    }
    @Override
    public void initialize(){
        super.initialize();
        initializeDesktopTile();
    }
    public void initializeDesktopTile(){
    /*code here*/
    }
    @Override
    public String toString() {
        return "DesktopTile[toString()=" + super.toString() + "]";
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
   public final List<DesktopTileVO> retrieveChildDesktopTileList(){
       return this.retrieveChildDesktopTileListByType( null, null );
   }

   public final List<DesktopTileVO> retrieveChildDesktopTileListByType( String tileType, String targetObid ){

       StringBuffer sbWhere = new StringBuffer();
       StringBuffer sbParam = new StringBuffer();

       if( tileType != null && tileType.equals( DesktopConstants.TILE_TYPE_OBJECT ) ){
           if( !NullUtil.isNone( targetObid ) ){
               StringUtil.constructWherePattern(
                   sbWhere, 
                   sbParam, 
                   "@this.[targetObid]",
                   GlobalConstants.OQL_OPERATOR_EQUAL,
                   targetObid );            
           }
       }
       
       List<DesktopTileVO> voTileList = this.getRelatedTilesSimple( DesktopConstants.PATTERN_TARGET_OBID, sbWhere.toString(), sbParam.toString(), 1 );
       return voTileList;        
   }    

   public final DesktopTileVO retrieveChildDesktopTile( String childObid ){

       StringBuffer sbWhere = new StringBuffer();
       StringBuffer sbParam = new StringBuffer();

       StringUtil.constructWherePattern(
           sbWhere, 
           sbParam, 
           "@this.[obid]",
           GlobalConstants.OQL_OPERATOR_EQUAL,
           childObid );            
       
       List<DesktopTileVO> voTileList = this.getRelatedTilesSimple( DesktopConstants.PATTERN_TARGET_OBID, sbWhere.toString(), sbParam.toString(), 1 );
       
       DesktopTileVO voTile = null;
       if( voTileList.size() > 0 ){
           voTile = voTileList.get( 0 );
       }
       return voTile;        
   }        
   
   public void createDesktopTile( String userObid, String parentObid, FilesVO voFile ){
       
       this.createObject();
       this.connectToParent( userObid, parentObid );

       if( voFile != null && voFile.getSysFileName() != null && !voFile.getSysFileName().isEmpty() ){
           voFile.setActType( "C" );

           this.checkInFile( GlobalConstants.FILE_UNLOCK, GlobalConstants.FILE_APPEND, voFile );

           this.getVo().setIconUrl( voFile.getObid() );
           this.getVo().setIconType( DesktopConstants.ICON_TYPE_FCSFILE );
           this.modifyObject();
       }
   }
   
   public void modifyDesktopTile( FilesVO voFile ){
       
       DesktopTile curTile = DomUtil.toDom( this.getVo().getObid() );
       DesktopTileVO voCurTile = curTile.getVo();
       
       // Legacy Link Desktop Tile 수정할 경우에는 File 삭제하지 않도록 처리
       if( voFile.getSysFileName() == null || voFile.getSysFileName().isEmpty() ){
           if( !this.getVo().getIconType().equals( DesktopConstants.ICON_TYPE_FCSFILE ) &&
               voCurTile.getIconType().equals( DesktopConstants.ICON_TYPE_FCSFILE ) &&
               !voCurTile.getDescriptions().startsWith(DesktopConstants.TILE_DESC_PREFIX_LEGACYLINK)){
               
               Files domCurFile = DomUtil.toDom( voCurTile.getIconUrl() );
               FilesVO curFileVO = domCurFile.getVo();
               curFileVO.setActType( "D" );
               this.checkInFile( GlobalConstants.FILE_UNLOCK, GlobalConstants.FILE_MODIFY, curFileVO );
           }
       }
       else{
           voFile.setActType( "C" );

           this.checkInFile( GlobalConstants.FILE_UNLOCK, GlobalConstants.FILE_MODIFY, voFile );
           this.getVo().setIconUrl( voFile.getObid() );
           this.getVo().setIconType( DesktopConstants.ICON_TYPE_FCSFILE );

       }
       this.modifyObject();
   }
   
   public void deleteDesktopTile( String parentObid ){

//       this.disconnectFromParent();
       this.deleteObject();
   }
   
   public DesktopTileVO registerObject( String userObid, String parentObid ){
       
       List<DesktopTileVO> dupList;

       if( this.isNull( parentObid ) ){
           Users domUsers = DomUtil.toDom( userObid );
           dupList = domUsers.retrieveChildDesktopTileListByType( DesktopConstants.TILE_TYPE_OBJECT, this.getVo().getTargetObid() );             
       }
       else{
           DesktopTile domParent = DomUtil.toDom( parentObid );
           dupList = domParent.retrieveChildDesktopTileListByType( DesktopConstants.TILE_TYPE_OBJECT, this.getVo().getTargetObid() );
       }
       
       if( dupList.size() > 0 ){
           throw new ApplicationException( "desktop.error.registerObject.duplicated" );                     
       }

       this.createObject();
       this.connectToParent( userObid, parentObid );
       
       return this.findObjectTileList().get( 0 );
   }
   
   public void changePath( String userObid, String newParentObid ){

       DesktopTileVO voTile = this.getVo();
       String tileType = voTile.getTileType();
       
       String errMsgKey = null;
       
       if( this.checkChildAlreadyExists( userObid, newParentObid, voTile.getObid(), DesktopConstants.PATTERN_OBID ) ){
           errMsgKey = "desktop.error.registerObject.duplicated";
       }
       else{
           if( tileType.equals( DesktopConstants.TILE_TYPE_OBJECT ) ){
               if( this.checkChildAlreadyExists( userObid, newParentObid, voTile.getTargetObid(), DesktopConstants.PATTERN_TARGET_OBID ) ){
                   errMsgKey = "desktop.error.registerObject.duplicated";            
               }            
           }
       }

       if( errMsgKey != null ){
           if( tileType.equals( DesktopConstants.TILE_TYPE_FOLDER ) && !this.isNull( newParentObid ) ){
               StringBuffer sbWhere = new StringBuffer();
               StringBuffer sbParam = new StringBuffer();
   
               StringUtil.constructWherePattern(
                   sbWhere, 
                   sbParam, 
                   "@this.[tileType]",
                   GlobalConstants.OQL_OPERATOR_EQUAL,
                   DesktopConstants.TILE_TYPE_FOLDER );            
               
               List<DesktopTileVO> children = this.getRelatedTilesSimple( 0, sbWhere.toString(), sbParam.toString(), 0 );
               DesktopTileVO voTmpTile;
               for( int index = 0; index < children.size(); index ++ ){
                    voTmpTile =  children.get( index );
                    if( voTmpTile.getObid().equals( newParentObid ) ){
                        errMsgKey = "desktop.error.changePath.folderHierarchyViolation";
                        break;
                    }
               }
           }
       }
       
       if( errMsgKey == null ){
           this.disconnectFromParent();
           this.connectToParent( userObid, newParentObid );
       }
       else{
           throw new ApplicationException( errMsgKey );       
       }
   }
   
   public List<DesktopTileVO> findObjectTileList(){
       StringBuffer sbWhere = new StringBuffer();
       StringBuffer sbParam = new StringBuffer();

       String tmpNames = this.getVo().getNames();
       if( tmpNames == null ){
           tmpNames = "";
       }
       StringUtil.constructWherePattern(
           sbWhere, 
           sbParam, 
           "@this.[targetObid]",
           GlobalConstants.OQL_OPERATOR_EQUAL,
           this.getVo().getTargetObid() );

       return ObjectRoot.findObjects(
               ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE, // className
               tmpNames, //namePattern, 
               "", //revisionPattern, 
               this.getSelectPattern( DesktopConstants.PATTERN_TARGET_OBID ),     // selectPattern
               sbWhere.toString(),                      // wherePattern,         
               sbParam.toString(),                    // parameterPattern, 
               true,                     //expandType
               0
       );         
   }
   
   private void connectToParent( String userObid, String parentObid ){
       
       String obid = this.getVo().getObid();
       int sequences = 0;

       if( this.isNull( parentObid ) ){
           Users domUsers = DomUtil.toDom( userObid );
           sequences = domUsers.getMaxDesktopSequenceAmongChildren();
       }
       else{            
           DesktopTile domTile = DomUtil.toDom( parentObid );
           sequences = domTile.getMaxDesktopSequenceAmongChildren();
       }

       HashMap<String, Object> map = new HashMap<String, Object>();
       map.put( "sequences", sequences + 1 );
       
       if( this.isNull( parentObid ) ){
           RelationShip.connect( ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPTILE, userObid, obid, map );
       }
       else{
           RelationShip.connect( ApplicationSchemaConstants.RELCLASS_DESKTOPTILE2TILE, parentObid, obid, map );            
       }        
   }

   public int getMaxDesktopSequenceAmongChildren(){

       String relationName = ApplicationSchemaConstants.RELCLASS_DESKTOPTILE2TILE;
       String className    = ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE;
       String direction    = GlobalConstants.FLAG_TYPE_TO;
       
       List<BusinessRelationObjectVO> relationList = this.getRelationship( relationName, className, direction );                   

       int sequences = 0;
       DesktopTile2TileVO  voRelation;
      
       for( int index = 0; index < relationList.size(); index ++ ){
            voRelation = (DesktopTile2TileVO)relationList.get( index );
            if( voRelation.getSequences() != null ){
                sequences = Math.max( sequences, voRelation.getSequences() );
            }
       }
       return sequences;
   }
   
   private void disconnectFromParent(){
       
       this.disconnectFromParent( ApplicationSchemaConstants.RELCLASS_USERS2DESKTOPTILE, ApplicationSchemaConstants.BIZCLASS_USERS );
       this.disconnectFromParent( ApplicationSchemaConstants.RELCLASS_DESKTOPTILE2TILE,  ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE );
   }
   
   private void disconnectFromParent( String relationName, String filterName ){
       List<BusinessRelationObjectVO> relationList;
       
       relationList = this.getRelationship( 
               relationName, 
               filterName,
               GlobalConstants.FLAG_TYPE_FROM );
       
       if( relationList.size() > 0 ){
           for( int index = 0; index < relationList.size(); index ++ ){
                RelationShip.disconnect( relationList.get( index ) );            
           }
           ObjectRoot.deleteObjectSet(relationList);
           //this.deleteRelation( relationList );
       }
   }
   
   private boolean isNull( String value ){
       return ( NullUtil.isNone( value ) || value.isEmpty() || value.equals( "null" ) );        
   }
   
   private String getSelectPattern( int keys ){
       
       StringBuffer sbSelect = new StringBuffer();

       if( ( keys & DesktopConstants.PATTERN_TARGET_OBID ) == DesktopConstants.PATTERN_TARGET_OBID ){
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'titles') targetTitles" );
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'names') targetNames" );            
           
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'states') targetStates" );            
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'className') targetClassName" );            
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'revision') targetRevision" );            
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'modelSuffix') targetDocModelName" ); 
//           StringUtil.constructSelectPattern( sbSelect, "\"getObjectInfo\"(@this.[targetObid],'repModelStr') targetPartModelName" ); 
       } 
       
       return  sbSelect.toString();
   }
   
   private List<DesktopTileVO> getRelatedTilesSimple( int selectPattern, String wherePattern, String paramPattern, int depth ){
       
       String strSelectPattern = this.getSelectPattern( selectPattern );
      
       List<DesktopTileVO> children = this.getRelatedObjects(
           ApplicationSchemaConstants.RELCLASS_DESKTOPTILE2TILE, // relationPattern, 
           ApplicationSchemaConstants.BIZCLASS_DESKTOPTILE, //filterPattern, 
           GlobalConstants.FLAG_TYPE_TO,  // fromToDirection, 
           strSelectPattern, //selectPattern, 
           wherePattern, // wherePattern, 
           paramPattern, //parameterPattern, 
           false, 
           true, 
           0,
           depth
       );        
       return children;
   }
   
   private boolean checkChildAlreadyExists( String userObid, String parentObid, String obidToCheck, int pattern ){
       
       StringBuffer sbWhere = new StringBuffer();
       StringBuffer sbParam = new StringBuffer();

       if( pattern == DesktopConstants.PATTERN_TARGET_OBID ){
           StringUtil.constructWherePattern(
               sbWhere, 
               sbParam, 
               "@this.[targetObid]",
               GlobalConstants.OQL_OPERATOR_EQUAL,
               obidToCheck );                 
       }
       else if( pattern == DesktopConstants.PATTERN_OBID ){
           StringUtil.constructWherePattern(
               sbWhere, 
               sbParam, 
               "@this.[obid]",
               GlobalConstants.OQL_OPERATOR_EQUAL,
               obidToCheck );
       }
       
       List<DesktopTileVO> children;
       if( this.isNull( parentObid ) ){
           Users domUser = DomUtil.toDom( userObid );
           children = domUser.retrieveChildDesktopTileList( sbWhere.toString(), sbParam.toString() );
       }
       else{
           DesktopTile domParent = DomUtil.toDom( parentObid );
           children = domParent.getRelatedTilesSimple( 0, sbWhere.toString(), sbParam.toString(), 1 );            
       }
       return ( children.size() > 0 );
   }
   
   /**
    * Child DesktopTile 삭제
    */
   public void deleteChildDesktopTile(){
       List<DesktopTileVO> childList = this.getRelatedObjects(ApplicationSchemaConstants.RELCLASS_DESKTOPTILE2TILE, GlobalConstants.FLAG_TYPE_TO);
       if( childList != null && childList.size() > 0){
           DesktopTile parentDom = null;
           for( int inx = 0; inx < childList.size(); inx++ ){
               parentDom = new DesktopTile(childList.get(inx).getObid());
               parentDom.deleteChildDesktopTile();
               parentDom.deleteObject();
           }
       }
   }
}

