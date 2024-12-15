package com.ruoyi.sguser.service;

import java.util.List;
import com.ruoyi.sguser.domain.SgUser;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface ISgUserService 
{
    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    public SgUser selectSgUserByUserId(Long userId);

    /**
     * 查询用户管理列表
     * 
     * @param sgUser 用户管理
     * @return 用户管理集合
     */
    public List<SgUser> selectSgUserList(SgUser sgUser);

    /**
     * 新增用户管理
     * 
     * @param sgUser 用户管理
     * @return 结果
     */
    public int insertSgUser(SgUser sgUser);

    /**
     * 修改用户管理
     * 
     * @param sgUser 用户管理
     * @return 结果
     */
    public int updateSgUser(SgUser sgUser);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteSgUserByUserIds(Long[] userIds);

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteSgUserByUserId(Long userId);
}
