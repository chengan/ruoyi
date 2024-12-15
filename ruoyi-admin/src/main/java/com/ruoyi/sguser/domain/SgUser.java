package com.ruoyi.sguser.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户管理对象 sg_user
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public class SgUser extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long userId;

    /** 姓名 */
    @Excel(name = "姓名")
    private String userName;

    /** 性别 */
    @Excel(name = "性别")
    private String gender;

    /** 角色 */
    @Excel(name = "角色")
    private String userRole;

    /** 密码 */
    @Excel(name = "密码")
    private String userPassword;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setGender(String gender) 
    {
        this.gender = gender;
    }

    public String getGender() 
    {
        return gender;
    }
    public void setUserRole(String userRole) 
    {
        this.userRole = userRole;
    }

    public String getUserRole() 
    {
        return userRole;
    }
    public void setUserPassword(String userPassword) 
    {
        this.userPassword = userPassword;
    }

    public String getUserPassword() 
    {
        return userPassword;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("gender", getGender())
            .append("userRole", getUserRole())
            .append("userPassword", getUserPassword())
            .toString();
    }
}
