package com.ruoyi.RecordQuestion.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.RecordQuestion.mapper.RecordQuestionMapper;
import com.ruoyi.RecordQuestion.domain.RecordQuestion;
import com.ruoyi.RecordQuestion.service.IRecordQuestionService;

/**
 * 整改记录Service业务层处理
 * 
 * @author zwt
 * @date 2024-11-13
 */
@Service
public class RecordQuestionServiceImpl implements IRecordQuestionService 
{
    @Autowired
    private RecordQuestionMapper recordQuestionMapper;

    /**
     * 查询整改记录
     * 
     * @param taskId 整改记录主键
     * @return 整改记录
     */
    @Override
    public RecordQuestion selectRecordQuestionByTaskId(Long taskId)
    {
        return recordQuestionMapper.selectRecordQuestionByTaskId(taskId);
    }

    /**
     * 查询整改记录列表
     * 
     * @param recordQuestion 整改记录
     * @return 整改记录
     */
    @Override
    public List<RecordQuestion> selectRecordQuestionList(RecordQuestion recordQuestion)
    {
        return recordQuestionMapper.selectRecordQuestionList(recordQuestion);
    }

    /**
     * 新增整改记录
     * 
     * @param recordQuestion 整改记录
     * @return 结果
     */
    @Override
    public int insertRecordQuestion(RecordQuestion recordQuestion)
    {
        return recordQuestionMapper.insertRecordQuestion(recordQuestion);
    }

    /**
     * 修改整改记录
     * 
     * @param recordQuestion 整改记录
     * @return 结果
     */
    @Override
    public int updateRecordQuestion(RecordQuestion recordQuestion)
    {
        return recordQuestionMapper.updateRecordQuestion(recordQuestion);
    }

    /**
     * 批量删除整改记录
     * 
     * @param taskIds 需要删除的整改记录主键
     * @return 结果
     */
    @Override
    public int deleteRecordQuestionByTaskIds(Long[] taskIds)
    {
        return recordQuestionMapper.deleteRecordQuestionByTaskIds(taskIds);
    }

    /**
     * 删除整改记录信息
     * 
     * @param taskId 整改记录主键
     * @return 结果
     */
    @Override
    public int deleteRecordQuestionByTaskId(Long taskId)
    {
        return recordQuestionMapper.deleteRecordQuestionByTaskId(taskId);
    }
}
