package com.ruoyi.CheckViews1.controller;

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
import com.ruoyi.CheckViews1.domain.CheckViews1;
import com.ruoyi.CheckViews1.service.ICheckViews1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * CheckViews1Controller
 * 
 * @author taotao
 * @date 2024-12-09
 */
@RestController
@RequestMapping("/CheckViews1/CheckViews1")
public class CheckViews1Controller extends BaseController
{
    @Autowired
    private ICheckViews1Service checkViews1Service;

    /**
     * 查询CheckViews1列表
     */
    @PreAuthorize("@ss.hasPermi('CheckViews1:CheckViews1:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckViews1 checkViews1)
    {
        startPage();
        List<CheckViews1> list = checkViews1Service.selectCheckViews1List(checkViews1);
        return getDataTable(list);
    }

    /**
     * 导出CheckViews1列表
     */
    @PreAuthorize("@ss.hasPermi('CheckViews1:CheckViews1:export')")
    @Log(title = "CheckViews1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckViews1 checkViews1)
    {
        List<CheckViews1> list = checkViews1Service.selectCheckViews1List(checkViews1);
        ExcelUtil<CheckViews1> util = new ExcelUtil<CheckViews1>(CheckViews1.class);
        util.exportExcel(response, list, "CheckViews1数据");
    }

    /**
     * 获取CheckViews1详细信息
     */
    @PreAuthorize("@ss.hasPermi('CheckViews1:CheckViews1:query')")
    @GetMapping(value = "/{wbs}")
    public AjaxResult getInfo(@PathVariable("wbs") String wbs)
    {
        return success(checkViews1Service.selectCheckViews1ByWbs(wbs));
    }

    /**
     * 新增CheckViews1
     */
    @PreAuthorize("@ss.hasPermi('CheckViews1:CheckViews1:add')")
    @Log(title = "CheckViews1", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckViews1 checkViews1)
    {
        return toAjax(checkViews1Service.insertCheckViews1(checkViews1));
    }

    /**
     * 修改CheckViews1
     */
    @PreAuthorize("@ss.hasPermi('CheckViews1:CheckViews1:edit')")
    @Log(title = "CheckViews1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckViews1 checkViews1)
    {
        return toAjax(checkViews1Service.updateCheckViews1(checkViews1));
    }

    /**
     * 删除CheckViews1
     */
    @PreAuthorize("@ss.hasPermi('CheckViews1:CheckViews1:remove')")
    @Log(title = "CheckViews1", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wbss}")
    public AjaxResult remove(@PathVariable String[] wbss)
    {
        return toAjax(checkViews1Service.deleteCheckViews1ByWbss(wbss));
    }
}
