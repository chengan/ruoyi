package com.ruoyi.RecordQuestion.mapper;

import java.util.List;
import com.ruoyi.RecordQuestion.domain.RecordQuestion;

/**
 * 整改记录Mapper接口
 * 
 * @author zwt
 * @date 2024-11-13
 */
public interface RecordQuestionMapper 
{
    /**
     * 查询整改记录
     * 
     * @param taskId 整改记录主键
     * @return 整改记录
     */
    public RecordQuestion selectRecordQuestionByTaskId(Long taskId);

    /**
     * 查询整改记录列表
     * 
     * @param recordQuestion 整改记录
     * @return 整改记录集合
     */
    public List<RecordQuestion> selectRecordQuestionList(RecordQuestion recordQuestion);

    /**
     * 新增整改记录
     * 
     * @param recordQuestion 整改记录
     * @return 结果
     */
    public int insertRecordQuestion(RecordQuestion recordQuestion);

    /**
     * 修改整改记录
     * 
     * @param recordQuestion 整改记录
     * @return 结果
     */
    public int updateRecordQuestion(RecordQuestion recordQuestion);

    /**
     * 删除整改记录
     * 
     * @param taskId 整改记录主键
     * @return 结果
     */
    public int deleteRecordQuestionByTaskId(Long taskId);

    /**
     * 批量删除整改记录
     * 
     * @param taskIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRecordQuestionByTaskIds(Long[] taskIds);
}
