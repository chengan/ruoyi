package com.ruoyi.QualityCheckPro.domain.Vo;
import com.ruoyi.QualityCheckPro.domain.QualityCheckPro;

public class CheckVo extends QualityCheckPro{
    private String userName;

    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }


}
