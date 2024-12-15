package com.ruoyi.sguser.mapper;

import java.util.List;
import com.ruoyi.sguser.domain.SgUser;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface SgUserMapper 
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
     * 删除用户管理
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteSgUserByUserId(Long userId);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSgUserByUserIds(Long[] userIds);
}
