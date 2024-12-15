package com.ruoyi.QualityCheckReport.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.QualityCheckReport.mapper.QualityCheckReportMapper;
import com.ruoyi.QualityCheckReport.domain.QualityCheckReport;
import com.ruoyi.QualityCheckReport.service.IQualityCheckReportService;

/**
 * 施工质量上报Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-11-13
 */
@Service
public class QualityCheckReportServiceImpl implements IQualityCheckReportService
{
    @Autowired
    private QualityCheckReportMapper qualityCheckReportMapper;



    /**
     * 查询施工质量上报
     * 
     * @param checkId 施工质量上报主键
     * @return 施工质量上报
     */
    @Override
    public QualityCheckReport selectQualityCheckReportByCheckId(Long checkId)
    {
        return qualityCheckReportMapper.selectQualityCheckReportByCheckId(checkId);
    }

    /**
     * 查询施工质量上报列表
     * 
     * @param qualityCheckReport 施工质量上报
     * @return 施工质量上报
     */
    @Override
    public List<QualityCheckReport> selectQualityCheckReportList(QualityCheckReport qualityCheckReport)
    {
        return qualityCheckReportMapper.selectQualityCheckReportList(qualityCheckReport);
    }

    /**
     * 新增施工质量上报
     * 
     * @param qualityCheckReport 施工质量上报
     * @return 结果
     */
    @Override
    public int insertQualityCheckReport(QualityCheckReport qualityCheckReport)
    {
        return qualityCheckReportMapper.insertQualityCheckReport(qualityCheckReport);
    }

    /**
     * 修改施工质量上报
     * 
     * @param qualityCheckReport 施工质量上报
     * @return 结果
     */
    @Override
    public int updateQualityCheckReport(QualityCheckReport qualityCheckReport)
    {
        return qualityCheckReportMapper.updateQualityCheckReport(qualityCheckReport);
    }

    /**
     * 批量删除施工质量上报
     * 
     * @param checkIds 需要删除的施工质量上报主键
     * @return 结果
     */
    @Override
    public int deleteQualityCheckReportByCheckIds(Long[] checkIds)
    {
        return qualityCheckReportMapper.deleteQualityCheckReportByCheckIds(checkIds);
    }

    /**
     * 删除施工质量上报信息
     * 
     * @param checkId 施工质量上报主键
     * @return 结果
     */
    @Override
    public int deleteQualityCheckReportByCheckId(Long checkId)
    {
        return qualityCheckReportMapper.deleteQualityCheckReportByCheckId(checkId);
    }
}
