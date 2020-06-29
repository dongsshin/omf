/*
 * ===================================================================
 * System Name : PLM Project
 * Program ID : AbstractAccountVO.java
 * ===================================================================
 *  Modification Date      Modifier           Description
 *      2017.04.??       DS Shin            Initial
 * ===================================================================
 */
package rap.api.object.bizplan.model;


import com.rap.omc.api.object.model.BusinessObjectMasterVO;


@SuppressWarnings("serial")
public class AbstractAccountVO extends BusinessObjectMasterVO {
    private String        accountCode                                       ;
    private String        accountKorName                                    ;
    private String        accountEngName                                    ;
    private Integer       accountLevelCode                                  ;
    private String        upperAccountCode                                  ;
    private String        accountGroupCode                                  ;
    private String        useFlag                                           ;
    private String        expenseType                                       ;


    public void    setAccountCode(String accountCode){
        this.accountCode = accountCode;
    }
    public void    setAccountKorName(String accountKorName){
        this.accountKorName = accountKorName;
    }
    public void    setAccountEngName(String accountEngName){
        this.accountEngName = accountEngName;
    }
    public void    setAccountLevelCode(Integer accountLevelCode){
        this.accountLevelCode = accountLevelCode;
    }
    public void    setUpperAccountCode(String upperAccountCode){
        this.upperAccountCode = upperAccountCode;
    }
    public void    setAccountGroupCode(String accountGroupCode){
        this.accountGroupCode = accountGroupCode;
    }
    public void    setUseFlag(String useFlag){
        this.useFlag = useFlag;
    }
    public void    setExpenseType(String expenseType){
        this.expenseType = expenseType;
    }
    public String getAccountCode(){
        return accountCode;
    }
    public String getAccountKorName(){
        return accountKorName;
    }
    public String getAccountEngName(){
        return accountEngName;
    }
    public Integer getAccountLevelCode(){
        return accountLevelCode;
    }
    public String getUpperAccountCode(){
        return upperAccountCode;
    }
    public String getAccountGroupCode(){
        return accountGroupCode;
    }
    public String getUseFlag(){
        return useFlag;
    }
    public String getExpenseType(){
        return expenseType;
    }
}

