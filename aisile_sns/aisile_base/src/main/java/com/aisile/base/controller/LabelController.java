package com.aisile.base.controller;

import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.aisile.base.pojo.Label;
import com.aisile.base.service.LabelService;

import java.util.List;
import java.util.Map;

/**
 * Created by 简单· on 2019/4/18.
 */
@RestController
@RequestMapping("/label")   //资源路径
@CrossOrigin    //异常处理
public class LabelController {

    @Autowired
    private LabelService labelService;

    /*
    *查询全部标签、
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        List<Label> all = labelService.findAll();
        return new Result(true, StatusCode.OK, "查询成功",all);
    }
    /*
    *根据ID查询标签、
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.GET)
    public Result findById(@PathVariable(value = "labelId") String labelId){
        Label byId = labelService.findById(labelId);
        return new Result(true, StatusCode.OK, "查询一条成功",byId);
    }
    /*
    *根据ID删除标签、
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable(value = "labelId") String labelId){
        labelService.deleteById(labelId);
        return new Result(true, StatusCode.OK, "删除成功");
    }
    /*
    *添加标签、
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label){
        labelService.add(label);
        return  new Result(true, StatusCode.OK, "添加成功");
    }
    /*
    *修改标签、
     */
    @RequestMapping(value = "/{labelId}", method = RequestMethod.PUT)
    public Result update(@PathVariable(value = "labelId") String labelId, @RequestBody Label label){
        label.setId(labelId);
        labelService.update(label);
        return  new Result(true, StatusCode.OK, "修改成功");
    }

    /*
    *条件查询
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        List<Label> search = labelService.findSearch(searchMap);
        return  new Result(true, StatusCode.OK, "根据条件查询成功", search);
    }

    /*
     * 分页条件查询
     */
    @RequestMapping(value = "search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearchPage(@RequestBody Map searchMap,@PathVariable int page, @PathVariable int size){
        Page<Label> search = labelService.findSearch(searchMap, (page-1), size);
        return  new Result(true, StatusCode.OK, "根据条件分页查询成功",
                new PageResult<>(search.getTotalElements(), search.getContent()));
    }
}
