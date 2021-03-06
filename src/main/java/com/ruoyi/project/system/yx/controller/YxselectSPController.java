package com.ruoyi.project.system.yx.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.yx.domain.Yx;
import com.ruoyi.project.system.yx.service.IYxService;

/**
 * 牙星公司Controller
 * 
 * @author ruoyi
 * @date 2020-04-07
 */
@Controller
@RequestMapping("/system/yx/selectSP")
public class YxselectSPController extends BaseController
{
    private String prefix = "system/yx/selectSP";

    @Autowired
    private IYxService yxService;

    @RequiresPermissions("system:yx:selectSP:view")
    @GetMapping()
    public String yx()
    {
        return prefix + "/yx";
    }

   
    /**
     * 查询牙星公司列表
     */
    @RequiresPermissions("system:yx:selectSP:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Yx yx)
    {
        startPage();
        List<Yx> list = yxService.selectYxKHList(yx);
        return getDataTable(list);
    }

    /**
     * 导出牙星公司列表
     */
    @RequiresPermissions("system:yx:selectSP:export")
    @Log(title = "牙星公司", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Yx yx)
    {
        List<Yx> list = yxService.selectYxList(yx);
        ExcelUtil<Yx> util = new ExcelUtil<Yx>(Yx.class);
        return util.exportExcel(list, "yx");
    }

    /**
     * 新增牙星公司
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存牙星公司
     */
    @RequiresPermissions("system:yx:selectSP:add")
    @Log(title = "牙星公司", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Yx yx)
    {
        return toAjax(yxService.insertYx(yx));
    }

    /**
     * 修改牙星公司
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Yx yx = yxService.selectYxById(id);
        mmap.put("yx", yx);
        return prefix + "/edit";
    }

    /**
     * 修改保存牙星公司
     */
    @RequiresPermissions("system:yx:selectSP:edit")
    @Log(title = "牙星公司", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Yx yx)
    {
        return toAjax(yxService.updateYx(yx));
    }

    /**
     * 删除牙星公司
     */
    @RequiresPermissions("system:yx:selectSP:remove")
    @Log(title = "牙星公司", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(yxService.deleteYxByIds(ids));
    }
}
