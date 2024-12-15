package com.ruoyi.ReviseRecord.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.ReviseRecord.mapper.ReviseRecordMapper;
import com.ruoyi.ReviseRecord.domain.ReviseRecord;
import com.ruoyi.ReviseRecord.service.IReviseRecordService;

/**
 * 整改结果上报Service业务层处理
 * 
 * @author taotao
 * @date 2024-11-14
 */
@Service
public class ReviseRecordServiceImpl implements IReviseRecordService 
{
    @Autowired
    private ReviseRecordMapper reviseRecordMapper;

    /**
     * 查询整改结果上报
     * 
     * @param reviseId 整改结果上报主键
     * @return 整改结果上报
     */
    @Override
    public ReviseRecord selectReviseRecordByReviseId(Long reviseId)
    {
        return reviseRecordMapper.selectReviseRecordByReviseId(reviseId);
    }

    /**
     * 查询整改结果上报列表
     * 
     * @param reviseRecord 整改结果上报
     * @return 整改结果上报
     */
    @Override
    public List<ReviseRecord> selectReviseRecordList(ReviseRecord reviseRecord)
    {
        return reviseRecordMapper.selectReviseRecordList(reviseRecord);
    }

    /**
     * 新增整改结果上报
     * 
     * @param reviseRecord 整改结果上报
     * @return 结果
     */
    @Override
    public int insertReviseRecord(ReviseRecord reviseRecord)
    {
        return reviseRecordMapper.insertReviseRecord(reviseRecord);
    }

    /**
     * 修改整改结果上报
     * 
     * @param reviseRecord 整改结果上报
     * @return 结果
     */
    @Override
    public int updateReviseRecord(ReviseRecord reviseRecord)
    {
        return reviseRecordMapper.updateReviseRecord(reviseRecord);
    }

    /**
     * 批量删除整改结果上报
     * 
     * @param reviseIds 需要删除的整改结果上报主键
     * @return 结果
     */
    @Override
    public int deleteReviseRecordByReviseIds(Long[] reviseIds)
    {
        return reviseRecordMapper.deleteReviseRecordByReviseIds(reviseIds);
    }

    /**
     * 删除整改结果上报信息
     * 
     * @param reviseId 整改结果上报主键
     * @return 结果
     */
    @Override
    public int deleteReviseRecordByReviseId(Long reviseId)
    {
        return reviseRecordMapper.deleteReviseRecordByReviseId(reviseId);
    }
}
