package com.ruoyi.ReviseRecord.service;

import java.util.List;
import com.ruoyi.ReviseRecord.domain.ReviseRecord;

/**
 * 整改结果上报Service接口
 * 
 * @author taotao
 * @date 2024-11-14
 */
public interface IReviseRecordService 
{
    /**
     * 查询整改结果上报
     * 
     * @param reviseId 整改结果上报主键
     * @return 整改结果上报
     */
    public ReviseRecord selectReviseRecordByReviseId(Long reviseId);

    /**
     * 查询整改结果上报列表
     * 
     * @param reviseRecord 整改结果上报
     * @return 整改结果上报集合
     */
    public List<ReviseRecord> selectReviseRecordList(ReviseRecord reviseRecord);

    /**
     * 新增整改结果上报
     * 
     * @param reviseRecord 整改结果上报
     * @return 结果
     */
    public int insertReviseRecord(ReviseRecord reviseRecord);

    /**
     * 修改整改结果上报
     * 
     * @param reviseRecord 整改结果上报
     * @return 结果
     */
    public int updateReviseRecord(ReviseRecord reviseRecord);

    /**
     * 批量删除整改结果上报
     * 
     * @param reviseIds 需要删除的整改结果上报主键集合
     * @return 结果
     */
    public int deleteReviseRecordByReviseIds(Long[] reviseIds);

    /**
     * 删除整改结果上报信息
     * 
     * @param reviseId 整改结果上报主键
     * @return 结果
     */
    public int deleteReviseRecordByReviseId(Long reviseId);
}
