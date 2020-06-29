/**
 * ===========================================
 * System Name : LGE GPDM Project
 * Program ID : UIItemVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2015. 2. 9.  kwanghyui.choi   Initial
 * ===========================================
 */
package com.rap.omc.foundation.ui.model;

import java.util.List;


/**
 * <pre>
 * Class : UIItemVO
 * Description : TODO
 * </pre>
 * 
 * @author kwanghyui.choi
 */
public interface UIItemVO {
   String getId();
   void setId(String id);
   String getParentId();
   void setParentId(String parentId);
   void setParent(UIItemVO parent);
   void addRecord(UIItemVO child);
   UIItemVO getRecord(String uniqueStr);
   void removeRecord(UIItemVO layoutVO);
//   List<UIItemVO> getRecords();
   List<UIItemVO> getChildren();
   
}
