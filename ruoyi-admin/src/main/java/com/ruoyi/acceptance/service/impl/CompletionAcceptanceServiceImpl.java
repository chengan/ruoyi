package com.ruoyi.acceptance.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.acceptance.mapper.CompletionAcceptanceMapper;
import com.ruoyi.acceptance.domain.CompletionAcceptance;
import com.ruoyi.acceptance.service.ICompletionAcceptanceService;

/**
 * 竣工验收管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@Service
public class CompletionAcceptanceServiceImpl implements ICompletionAcceptanceService 
{
    @Autowired
    private CompletionAcceptanceMapper completionAcceptanceMapper;

    /**
     * 查询竣工验收管理
     * 
     * @param reportId 竣工验收管理主键
     * @return 竣工验收管理
     */
    @Override
    public CompletionAcceptance selectCompletionAcceptanceByReportId(Long reportId)
    {
        return completionAcceptanceMapper.selectCompletionAcceptanceByReportId(reportId);
    }

    /**
     * 查询竣工验收管理列表
     * 
     * @param completionAcceptance 竣工验收管理
     * @return 竣工验收管理
     */
    @Override
    public List<CompletionAcceptance> selectCompletionAcceptanceList(CompletionAcceptance completionAcceptance)
    {
        return completionAcceptanceMapper.selectCompletionAcceptanceList(completionAcceptance);
    }

    /**
     * 新增竣工验收管理
     * 
     * @param completionAcceptance 竣工验收管理
     * @return 结果
     */
    @Override
    public int insertCompletionAcceptance(CompletionAcceptance completionAcceptance)
    {
        return completionAcceptanceMapper.insertCompletionAcceptance(completionAcceptance);
    }

    /**
     * 修改竣工验收管理
     * 
     * @param completionAcceptance 竣工验收管理
     * @return 结果
     */
    @Override
    public int updateCompletionAcceptance(CompletionAcceptance completionAcceptance)
    {
        return completionAcceptanceMapper.updateCompletionAcceptance(completionAcceptance);
    }

    /**
     * 批量删除竣工验收管理
     * 
     * @param reportIds 需要删除的竣工验收管理主键
     * @return 结果
     */
    @Override
    public int deleteCompletionAcceptanceByReportIds(Long[] reportIds)
    {
        return completionAcceptanceMapper.deleteCompletionAcceptanceByReportIds(reportIds);
    }

    /**
     * 删除竣工验收管理信息
     * 
     * @param reportId 竣工验收管理主键
     * @return 结果
     */
    @Override
    public int deleteCompletionAcceptanceByReportId(Long reportId)
    {
        return completionAcceptanceMapper.deleteCompletionAcceptanceByReportId(reportId);
    }
}
