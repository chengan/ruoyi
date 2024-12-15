package com.ruoyi.AcceptanceReport.service;

import java.util.List;
import com.ruoyi.AcceptanceReport.domain.AcceptanceReport;

/**
 * 竣工验收上报Service接口
 * 
 * @author taotao
 * @date 2024-11-14
 */
public interface IAcceptanceReportService 
{
    /**
     * 查询竣工验收上报
     * 
     * @param acceptanceId 竣工验收上报主键
     * @return 竣工验收上报
     */
    public AcceptanceReport selectAcceptanceReportByAcceptanceId(Long acceptanceId);

    /**
     * 查询竣工验收上报列表
     * 
     * @param acceptanceReport 竣工验收上报
     * @return 竣工验收上报集合
     */
    public List<AcceptanceReport> selectAcceptanceReportList(AcceptanceReport acceptanceReport);

    /**
     * 新增竣工验收上报
     * 
     * @param acceptanceReport 竣工验收上报
     * @return 结果
     */
    public int insertAcceptanceReport(AcceptanceReport acceptanceReport);

    /**
     * 修改竣工验收上报
     * 
     * @param acceptanceReport 竣工验收上报
     * @return 结果
     */
    public int updateAcceptanceReport(AcceptanceReport acceptanceReport);

    /**
     * 批量删除竣工验收上报
     * 
     * @param acceptanceIds 需要删除的竣工验收上报主键集合
     * @return 结果
     */
    public int deleteAcceptanceReportByAcceptanceIds(Long[] acceptanceIds);

    /**
     * 删除竣工验收上报信息
     * 
     * @param acceptanceId 竣工验收上报主键
     * @return 结果
     */
    public int deleteAcceptanceReportByAcceptanceId(Long acceptanceId);
}
