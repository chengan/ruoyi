package com.ruoyi.acceptance.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.acceptance.domain.CompletionAcceptance;
import com.ruoyi.acceptance.service.ICompletionAcceptanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 竣工验收管理Controller
 * 
 * @author ruoyi
 * @date 2024-10-27
 */
@RestController
@RequestMapping("/acceptance/acceptance")
public class CompletionAcceptanceController extends BaseController
{
    @Autowired
    private ICompletionAcceptanceService completionAcceptanceService;

    /**
     * 查询竣工验收管理列表
     */
    @PreAuthorize("@ss.hasPermi('acceptance:acceptance:list')")
    @GetMapping("/list")
    public TableDataInfo list(CompletionAcceptance completionAcceptance)
    {
        startPage();
        List<CompletionAcceptance> list = completionAcceptanceService.selectCompletionAcceptanceList(completionAcceptance);
        return getDataTable(list);
    }

    /**
     * 导出竣工验收管理列表
     */
    @PreAuthorize("@ss.hasPermi('acceptance:acceptance:export')")
    @Log(title = "竣工验收管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CompletionAcceptance completionAcceptance)
    {
        List<CompletionAcceptance> list = completionAcceptanceService.selectCompletionAcceptanceList(completionAcceptance);
        ExcelUtil<CompletionAcceptance> util = new ExcelUtil<CompletionAcceptance>(CompletionAcceptance.class);
        util.exportExcel(response, list, "竣工验收管理数据");
    }

    /**
     * 获取竣工验收管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('acceptance:acceptance:query')")
    @GetMapping(value = "/{reportId}")
    public AjaxResult getInfo(@PathVariable("reportId") Long reportId)
    {
        return success(completionAcceptanceService.selectCompletionAcceptanceByReportId(reportId));
    }

    /**
     * 新增竣工验收管理
     */
    @PreAuthorize("@ss.hasPermi('acceptance:acceptance:add')")
    @Log(title = "竣工验收管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CompletionAcceptance completionAcceptance)
    {
        return toAjax(completionAcceptanceService.insertCompletionAcceptance(completionAcceptance));
    }

    /**
     * 修改竣工验收管理
     */
    @PreAuthorize("@ss.hasPermi('acceptance:acceptance:edit')")
    @Log(title = "竣工验收管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CompletionAcceptance completionAcceptance)
    {
        return toAjax(completionAcceptanceService.updateCompletionAcceptance(completionAcceptance));
    }

    /**
     * 删除竣工验收管理
     */
    @PreAuthorize("@ss.hasPermi('acceptance:acceptance:remove')")
    @Log(title = "竣工验收管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{reportIds}")
    public AjaxResult remove(@PathVariable Long[] reportIds)
    {
        return toAjax(completionAcceptanceService.deleteCompletionAcceptanceByReportIds(reportIds));
    }
}
