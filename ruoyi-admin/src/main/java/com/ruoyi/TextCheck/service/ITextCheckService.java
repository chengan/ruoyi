package com.ruoyi.TextCheck.service;

import java.util.List;
import com.ruoyi.TextCheck.domain.TextCheck;

/**
 * 检查测试Service接口
 * 
 * @author zwt
 * @date 2024-11-12
 */
public interface ITextCheckService 
{
    /**
     * 查询检查测试
     * 
     * @param taskId 检查测试主键
     * @return 检查测试
     */
    public TextCheck selectTextCheckByTaskId(Long taskId);

    /**
     * 查询检查测试列表
     * 
     * @param textCheck 检查测试
     * @return 检查测试集合
     */
    public List<TextCheck> selectTextCheckList(TextCheck textCheck);

    /**
     * 新增检查测试
     * 
     * @param textCheck 检查测试
     * @return 结果
     */
    public int insertTextCheck(TextCheck textCheck);

    /**
     * 修改检查测试
     * 
     * @param textCheck 检查测试
     * @return 结果
     */
    public int updateTextCheck(TextCheck textCheck);

    /**
     * 批量删除检查测试
     * 
     * @param taskIds 需要删除的检查测试主键集合
     * @return 结果
     */
    public int deleteTextCheckByTaskIds(Long[] taskIds);

    /**
     * 删除检查测试信息
     * 
     * @param taskId 检查测试主键
     * @return 结果
     */
    public int deleteTextCheckByTaskId(Long taskId);
}
