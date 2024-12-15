package com.ruoyi.QualityCheckReport.mapper;

import java.util.List;
import com.ruoyi.QualityCheckReport.domain.QualityCheckReport;

/**
 * 施工质量上报Mapper接口
 * 
 * @author ruoyi
 * @date 2024-11-13
 */
public interface QualityCheckReportMapper 
{

    /**
     * 查询施工质量上报
     * 
     * @param checkId 施工质量上报主键
     * @return 施工质量上报
     */
    public QualityCheckReport selectQualityCheckReportByCheckId(Long checkId);

    /**
     * 查询施工质量上报列表
     * 
     * @param qualityCheckReport 施工质量上报
     * @return 施工质量上报集合
     */
    public List<QualityCheckReport> selectQualityCheckReportList(QualityCheckReport qualityCheckReport);

    /**
     * 新增施工质量上报
     * 
     * @param qualityCheckReport 施工质量上报
     * @return 结果
     */
    public int insertQualityCheckReport(QualityCheckReport qualityCheckReport);

    /**
     * 修改施工质量上报
     * 
     * @param qualityCheckReport 施工质量上报
     * @return 结果
     */
    public int updateQualityCheckReport(QualityCheckReport qualityCheckReport);

    /**
     * 删除施工质量上报
     * 
     * @param checkId 施工质量上报主键
     * @return 结果
     */
    public int deleteQualityCheckReportByCheckId(Long checkId);

    /**
     * 批量删除施工质量上报
     * 
     * @param checkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQualityCheckReportByCheckIds(Long[] checkIds);
}
