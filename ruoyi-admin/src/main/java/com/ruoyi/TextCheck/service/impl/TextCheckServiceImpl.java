package com.ruoyi.TextCheck.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.TextCheck.mapper.TextCheckMapper;
import com.ruoyi.TextCheck.domain.TextCheck;
import com.ruoyi.TextCheck.service.ITextCheckService;

/**
 * 检查测试Service业务层处理
 * 
 * @author zwt
 * @date 2024-11-12
 */
@Service
public class TextCheckServiceImpl implements ITextCheckService 
{
    @Autowired
    private TextCheckMapper textCheckMapper;

    /**
     * 查询检查测试
     * 
     * @param taskId 检查测试主键
     * @return 检查测试
     */
    @Override
    public TextCheck selectTextCheckByTaskId(Long taskId)
    {
        return textCheckMapper.selectTextCheckByTaskId(taskId);
    }

    /**
     * 查询检查测试列表
     * 
     * @param textCheck 检查测试
     * @return 检查测试
     */
    @Override
    public List<TextCheck> selectTextCheckList(TextCheck textCheck)
    {
        return textCheckMapper.selectTextCheckList(textCheck);
    }

    /**
     * 新增检查测试
     * 
     * @param textCheck 检查测试
     * @return 结果
     */
    @Override
    public int insertTextCheck(TextCheck textCheck)
    {
        return textCheckMapper.insertTextCheck(textCheck);
    }

    /**
     * 修改检查测试
     * 
     * @param textCheck 检查测试
     * @return 结果
     */
    @Override
    public int updateTextCheck(TextCheck textCheck)
    {
        return textCheckMapper.updateTextCheck(textCheck);
    }

    /**
     * 批量删除检查测试
     * 
     * @param taskIds 需要删除的检查测试主键
     * @return 结果
     */
    @Override
    public int deleteTextCheckByTaskIds(Long[] taskIds)
    {
        return textCheckMapper.deleteTextCheckByTaskIds(taskIds);
    }

    /**
     * 删除检查测试信息
     * 
     * @param taskId 检查测试主键
     * @return 结果
     */
    @Override
    public int deleteTextCheckByTaskId(Long taskId)
    {
        return textCheckMapper.deleteTextCheckByTaskId(taskId);
    }
}
