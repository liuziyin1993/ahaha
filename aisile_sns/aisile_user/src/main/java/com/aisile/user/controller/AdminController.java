package com.aisile.user.controller;

import com.aisile.user.pojo.Admin;
import com.aisile.user.service.AdminService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 查询所有信息
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result finAll() {
        return new Result(true, StatusCode.OK, "查询成功", adminService.findAll());
    }

    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
    @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Admin> pageList = adminService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Admin>(pageList.getTotalElements(), pageList.getContent()) );
    }

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",adminService.findSearch(searchMap));
    }
    /**
     * 添加操作
     *
     * @param admin
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Admin admin) {
        adminService.add(admin);
        return new Result(true, StatusCode.OK, "添加操作成功");
    }

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    @RequestMapping(name="/id",method = RequestMethod.POST)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询单个信息成功",adminService.findById(id));
    }
    /**
     * 修改信息
     *
     * @param admin
     * @return
     */
    @RequestMapping(name = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Admin admin, @PathVariable String id) {
        admin.setId(id);
        adminService.update(admin);
        return new Result(true, StatusCode.OK, "修改信息成功");
    }

    /**
     * 删除信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @RequestMapping(name = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        adminService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除信息失败");
    }

    /**
     * 登录验证信息
     * @param loginMap
     * @return
     */
    @RequestMapping(value="/login",method = RequestMethod.POST)
    public Result login(@RequestBody Map<String,String> loginMap){
        Admin admin = adminService.findByLoginnameAndPassword(
                loginMap.get("loginname"),loginMap.get("password"));
        if (admin!=null){
            return new Result(true,StatusCode.OK,"登录成功");
        }else {
            return new Result(false,StatusCode.ACCESSERROR,"账号密码不匹配");
        }
    }
}