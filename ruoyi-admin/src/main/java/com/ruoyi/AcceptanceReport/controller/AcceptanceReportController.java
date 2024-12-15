package com.ruoyi.AcceptanceReport.controller;

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
import com.ruoyi.AcceptanceReport.domain.AcceptanceReport;
import com.ruoyi.AcceptanceReport.service.IAcceptanceReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 竣工验收上报Controller
 * 
 * @author taotao
 * @date 2024-11-14
 */
@RestController
@RequestMapping("/AcceptanceReport/AcceptanceReport")
public class AcceptanceReportController extends BaseController
{
    @Autowired
    private IAcceptanceReportService acceptanceReportService;

    /**
     * 查询竣工验收上报列表
     */
    @PreAuthorize("@ss.hasPermi('AcceptanceReport:AcceptanceReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(AcceptanceReport acceptanceReport)
    {
        startPage();
        List<AcceptanceReport> list = acceptanceReportService.selectAcceptanceReportList(acceptanceReport);
        return getDataTable(list);
    }

    /**
     * 导出竣工验收上报列表
     */
    @PreAuthorize("@ss.hasPermi('AcceptanceReport:AcceptanceReport:export')")
    @Log(title = "竣工验收上报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AcceptanceReport acceptanceReport)
    {
        List<AcceptanceReport> list = acceptanceReportService.selectAcceptanceReportList(acceptanceReport);
        ExcelUtil<AcceptanceReport> util = new ExcelUtil<AcceptanceReport>(AcceptanceReport.class);
        util.exportExcel(response, list, "竣工验收上报数据");
    }

    /**
     * 获取竣工验收上报详细信息
     */
    @PreAuthorize("@ss.hasPermi('AcceptanceReport:AcceptanceReport:query')")
    @GetMapping(value = "/{acceptanceId}")
    public AjaxResult getInfo(@PathVariable("acceptanceId") Long acceptanceId)
    {
        return success(acceptanceReportService.selectAcceptanceReportByAcceptanceId(acceptanceId));
    }

    /**
     * 新增竣工验收上报
     */
    @PreAuthorize("@ss.hasPermi('AcceptanceReport:AcceptanceReport:add')")
    @Log(title = "竣工验收上报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AcceptanceReport acceptanceReport)
    {
        return toAjax(acceptanceReportService.insertAcceptanceReport(acceptanceReport));
    }

    /**
     * 修改竣工验收上报
     */
    @PreAuthorize("@ss.hasPermi('AcceptanceReport:AcceptanceReport:edit')")
    @Log(title = "竣工验收上报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AcceptanceReport acceptanceReport)
    {
        return toAjax(acceptanceReportService.updateAcceptanceReport(acceptanceReport));
    }

    /**
     * 删除竣工验收上报
     */
    @PreAuthorize("@ss.hasPermi('AcceptanceReport:AcceptanceReport:remove')")
    @Log(title = "竣工验收上报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{acceptanceIds}")
    public AjaxResult remove(@PathVariable Long[] acceptanceIds)
    {
        return toAjax(acceptanceReportService.deleteAcceptanceReportByAcceptanceIds(acceptanceIds));
    }
}
