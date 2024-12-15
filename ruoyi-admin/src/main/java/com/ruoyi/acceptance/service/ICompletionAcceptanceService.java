package com.ruoyi.acceptance.service;

import java.util.List;
import com.ruoyi.acceptance.domain.CompletionAcceptance;

/**
 * 竣工验收管理Service接口
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
public interface ICompletionAcceptanceService 
{
    /**
     * 查询竣工验收管理
     * 
     * @param reportId 竣工验收管理主键
     * @return 竣工验收管理
     */
    public CompletionAcceptance selectCompletionAcceptanceByReportId(Long reportId);

    /**
     * 查询竣工验收管理列表
     * 
     * @param completionAcceptance 竣工验收管理
     * @return 竣工验收管理集合
     */
    public List<CompletionAcceptance> selectCompletionAcceptanceList(CompletionAcceptance completionAcceptance);

    /**
     * 新增竣工验收管理
     * 
     * @param completionAcceptance 竣工验收管理
     * @return 结果
     */
    public int insertCompletionAcceptance(CompletionAcceptance completionAcceptance);

    /**
     * 修改竣工验收管理
     * 
     * @param completionAcceptance 竣工验收管理
     * @return 结果
     */
    public int updateCompletionAcceptance(CompletionAcceptance completionAcceptance);

    /**
     * 批量删除竣工验收管理
     * 
     * @param reportIds 需要删除的竣工验收管理主键集合
     * @return 结果
     */
    public int deleteCompletionAcceptanceByReportIds(Long[] reportIds);

    /**
     * 删除竣工验收管理信息
     * 
     * @param reportId 竣工验收管理主键
     * @return 结果
     */
    public int deleteCompletionAcceptanceByReportId(Long reportId);
}
