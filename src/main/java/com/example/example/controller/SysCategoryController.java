package com.example.controller;

import com.example.dto.Result;
import com.example.entity.SysCategory;
import com.example.service.ISysCategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * 分类操作接口
 *
 * @since 2023-11-30
 */
@RestController
@RequestMapping("/sysCategory")
public class SysCategoryController {
    @Resource
    private ISysCategoryService categoryService;

    @GetMapping()
    public Result queryList() {
        List<SysCategory> list = categoryService.list();
        return Result.success(list);
    }

    @GetMapping("/{id}")
    public Result queryById(@PathVariable Long id) {
        SysCategory result = categoryService.getById(id);
        return Result.success(result);
    }

    @PostMapping()
    public Result add(@RequestBody SysCategory sysCategory) {
        sysCategory.setId(null);
        boolean status = categoryService.save(sysCategory);
        if (status) {
            return Result.success("添加成功");
        } else {
            return Result.error("操作失败，请检查数据格式");
        }
    }

    @PutMapping()
    public Result update(@RequestBody SysCategory sysCategory) {
        if (sysCategory.getId() == null) {
            return Result.error("你id呢");
        }
        boolean status = categoryService.updateById(sysCategory);
        if (status) {
            return Result.success();
        } else {
            return Result.error("操作失败，请检查数据格式");
        }
    }

    @DeleteMapping("/{ids}")
    public Result deleteById(@PathVariable Long[] ids) {
        boolean status = categoryService.removeBatchByIds(Arrays.asList(ids));
        if (status) {
            return Result.success();
        } else {
            return Result.error("操作失败，请检查数据格式");
        }
    }

}
