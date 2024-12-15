package com.ruoyi.tracks1.controller;

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
import com.ruoyi.tracks1.domain.Tracks1;
import com.ruoyi.tracks1.service.ITracks1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 工作轨迹1Controller
 * 
 * @author ruoyi
 * @date 2024-11-19
 */
@RestController
@RequestMapping("/tracks1/tracks1")
public class Tracks1Controller extends BaseController
{
    @Autowired
    private ITracks1Service tracks1Service;

    /**
     * 按日期范围查询工作轨迹列表
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:list')")
    @GetMapping("/listByDateRange")
    public TableDataInfo listByDateRange(String startDate, String endDate)
    {
        startPage();
        Tracks1 tracks1 = new Tracks1();
        tracks1.setStartDate(startDate);
        tracks1.setEndDate(endDate);
        List<Tracks1> list = tracks1Service.selectTracks1List(tracks1);
        return getDataTable(list);
    }

    /**
     * 查询工作轨迹1列表
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:list')")
    @GetMapping("/list")
    public TableDataInfo list(Tracks1 tracks1)
    {
        startPage();
        List<Tracks1> list = tracks1Service.selectTracks1List(tracks1);
        return getDataTable(list);
    }

    /**
     * 导出工作轨迹1列表
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:export')")
    @Log(title = "工作轨迹1", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Tracks1 tracks1)
    {
        List<Tracks1> list = tracks1Service.selectTracks1List(tracks1);
        ExcelUtil<Tracks1> util = new ExcelUtil<Tracks1>(Tracks1.class);
        util.exportExcel(response, list, "工作轨迹1数据");
    }

    /**
     * 获取工作轨迹1详细信息
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(tracks1Service.selectTracks1ById(id));
    }

    /**
     * 新增工作轨迹1
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:add')")
    @Log(title = "工作轨迹1", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Tracks1 tracks1)
    {
        return toAjax(tracks1Service.insertTracks1(tracks1));
    }

    /**
     * 修改工作轨迹1
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:edit')")
    @Log(title = "工作轨迹1", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Tracks1 tracks1)
    {
        return toAjax(tracks1Service.updateTracks1(tracks1));
    }

    /**
     * 删除工作轨迹1
     */
    @PreAuthorize("@ss.hasPermi('tracks1:tracks1:remove')")
    @Log(title = "工作轨迹1", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(tracks1Service.deleteTracks1ByIds(ids));
    }
}
