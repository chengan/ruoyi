package com.ruoyi.CheckViews.controller;

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
import com.ruoyi.CheckViews.domain.CheckViews;
import com.ruoyi.CheckViews.service.ICheckViewsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * CheckViewsController
 * 
 * @author taotao
 * @date 2024-12-03
 */
@RestController
@RequestMapping("/CheckViews/CheckViews")
public class CheckViewsController extends BaseController
{
    @Autowired
    private ICheckViewsService checkViewsService;

    /**
     * 查询CheckViews列表
     */
    @PreAuthorize("@ss.hasPermi('CheckViews:CheckViews:list')")
    @GetMapping("/list")
    public TableDataInfo list(CheckViews checkViews)
    {
        startPage();
        List<CheckViews> list = checkViewsService.selectCheckViewsList(checkViews);
        return getDataTable(list);
    }

    /**
     * 导出CheckViews列表
     */
    @PreAuthorize("@ss.hasPermi('CheckViews:CheckViews:export')")
    @Log(title = "CheckViews", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckViews checkViews)
    {
        List<CheckViews> list = checkViewsService.selectCheckViewsList(checkViews);
        ExcelUtil<CheckViews> util = new ExcelUtil<CheckViews>(CheckViews.class);
        util.exportExcel(response, list, "CheckViews数据");
    }

    /**
     * 获取CheckViews详细信息
     */
    @PreAuthorize("@ss.hasPermi('CheckViews:CheckViews:query')")
    @GetMapping(value = "/{wbs}")
    public AjaxResult getInfo(@PathVariable("wbs") String wbs)
    {
        return success(checkViewsService.selectCheckViewsByWbs(wbs));
    }

    /**
     * 新增CheckViews
     */
    @PreAuthorize("@ss.hasPermi('CheckViews:CheckViews:add')")
    @Log(title = "CheckViews", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckViews checkViews)
    {
        return toAjax(checkViewsService.insertCheckViews(checkViews));
    }

    /**
     * 修改CheckViews
     */
    @PreAuthorize("@ss.hasPermi('CheckViews:CheckViews:edit')")
    @Log(title = "CheckViews", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckViews checkViews)
    {
        return toAjax(checkViewsService.updateCheckViews(checkViews));
    }

    /**
     * 删除CheckViews
     */
    @PreAuthorize("@ss.hasPermi('CheckViews:CheckViews:remove')")
    @Log(title = "CheckViews", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wbss}")
    public AjaxResult remove(@PathVariable String[] wbss)
    {
        return toAjax(checkViewsService.deleteCheckViewsByWbss(wbss));
    }
}
