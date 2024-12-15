package com.ruoyi.AcceptanceReport.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.AcceptanceReport.mapper.AcceptanceReportMapper;
import com.ruoyi.AcceptanceReport.domain.AcceptanceReport;
import com.ruoyi.AcceptanceReport.service.IAcceptanceReportService;

/**
 * 竣工验收上报Service业务层处理
 * 
 * @author taotao
 * @date 2024-11-14
 */
@Service
public class AcceptanceReportServiceImpl implements IAcceptanceReportService 
{
    @Autowired
    private AcceptanceReportMapper acceptanceReportMapper;

    /**
     * 查询竣工验收上报
     * 
     * @param acceptanceId 竣工验收上报主键
     * @return 竣工验收上报
     */
    @Override
    public AcceptanceReport selectAcceptanceReportByAcceptanceId(Long acceptanceId)
    {
        return acceptanceReportMapper.selectAcceptanceReportByAcceptanceId(acceptanceId);
    }

    /**
     * 查询竣工验收上报列表
     * 
     * @param acceptanceReport 竣工验收上报
     * @return 竣工验收上报
     */
    @Override
    public List<AcceptanceReport> selectAcceptanceReportList(AcceptanceReport acceptanceReport)
    {
        return acceptanceReportMapper.selectAcceptanceReportList(acceptanceReport);
    }

    /**
     * 新增竣工验收上报
     * 
     * @param acceptanceReport 竣工验收上报
     * @return 结果
     */
    @Override
    public int insertAcceptanceReport(AcceptanceReport acceptanceReport)
    {
        return acceptanceReportMapper.insertAcceptanceReport(acceptanceReport);
    }

    /**
     * 修改竣工验收上报
     * 
     * @param acceptanceReport 竣工验收上报
     * @return 结果
     */
    @Override
    public int updateAcceptanceReport(AcceptanceReport acceptanceReport)
    {
        return acceptanceReportMapper.updateAcceptanceReport(acceptanceReport);
    }

    /**
     * 批量删除竣工验收上报
     * 
     * @param acceptanceIds 需要删除的竣工验收上报主键
     * @return 结果
     */
    @Override
    public int deleteAcceptanceReportByAcceptanceIds(Long[] acceptanceIds)
    {
        return acceptanceReportMapper.deleteAcceptanceReportByAcceptanceIds(acceptanceIds);
    }

    /**
     * 删除竣工验收上报信息
     * 
     * @param acceptanceId 竣工验收上报主键
     * @return 结果
     */
    @Override
    public int deleteAcceptanceReportByAcceptanceId(Long acceptanceId)
    {
        return acceptanceReportMapper.deleteAcceptanceReportByAcceptanceId(acceptanceId);
    }
}
