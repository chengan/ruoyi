package com.ruoyi.sguser.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.sguser.mapper.SgUserMapper;
import com.ruoyi.sguser.domain.SgUser;
import com.ruoyi.sguser.service.ISgUserService;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class SgUserServiceImpl implements ISgUserService 
{
    @Autowired
    private SgUserMapper sgUserMapper;

    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public SgUser selectSgUserByUserId(Long userId)
    {
        return sgUserMapper.selectSgUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     * 
     * @param sgUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<SgUser> selectSgUserList(SgUser sgUser)
    {
        return sgUserMapper.selectSgUserList(sgUser);
    }

    /**
     * 新增用户管理
     * 
     * @param sgUser 用户管理
     * @return 结果
     */
    @Override
    public int insertSgUser(SgUser sgUser)
    {
        return sgUserMapper.insertSgUser(sgUser);
    }

    /**
     * 修改用户管理
     * 
     * @param sgUser 用户管理
     * @return 结果
     */
    @Override
    public int updateSgUser(SgUser sgUser)
    {
        return sgUserMapper.updateSgUser(sgUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteSgUserByUserIds(Long[] userIds)
    {
        return sgUserMapper.deleteSgUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteSgUserByUserId(Long userId)
    {
        return sgUserMapper.deleteSgUserByUserId(userId);
    }
}
