/**
 * ===========================================
 * System Name : LGE PLM Project
 * Program ID : ConvertedPhaseVO.java
 * ===========================================
 * Modify Date    Modifier    Description 
 * -------------------------------------------
 * 2017. 9. 22.  s_dongsshin   Initial
 * ===========================================
 */
package rap.application.workflow.model;


/**
 * <pre>
 * Class : ConvertedPhaseVO
 * Description : TODO
 * </pre>
 * 
 * @author s_dongsshin
 */
public class ConvertedPhaseVO {
    private String        names                              ;
    private String        codeForDivision                    ;
    private String        nameForDivision                    ;
    private String        codeForSystemControl               ;
    private String        nameForSystemControl               ;
    
    public ConvertedPhaseVO(String names, String codeForDivision, String nameForDivision, String codeForSystemControl,
            String nameForSystemControl) {
        super();
        this.names = names;
        this.codeForDivision = codeForDivision;
        this.nameForDivision = nameForDivision;
        this.codeForSystemControl = codeForSystemControl;
        this.nameForSystemControl = nameForSystemControl;
    }

    public String getNames(){
        return names;
    }
    
    public void setNames(String names){
        this.names = names;
    }
    
    public String getCodeForDivision(){
        return codeForDivision;
    }
    
    public void setCodeForDivision(String codeForDivision){
        this.codeForDivision = codeForDivision;
    }
    
    public String getNameForDivision(){
        return nameForDivision;
    }
    
    public void setNameForDivision(String nameForDivision){
        this.nameForDivision = nameForDivision;
    }
    
    public String getCodeForSystemControl(){
        return codeForSystemControl;
    }
    
    public void setCodeForSystemControl(String codeForSystemControl){
        this.codeForSystemControl = codeForSystemControl;
    }
    
    public String getNameForSystemControl(){
        return nameForSystemControl;
    }
    
    public void setNameForSystemControl(String nameForSystemControl){
        this.nameForSystemControl = nameForSystemControl;
    }
    
}
