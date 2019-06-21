package com.aisile.spit.controller;

import com.aisile.spit.pojo.Spit;
import com.aisile.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 吐槽
 */
@Controller
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    public SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 查询所有吐槽信息
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Result finOne(@PathVariable String id){
        return  new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    /**
     * 添加吐槽记录
     * @param spit
     * @return
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"兄die添加吐槽成功");
    }

    /**
     * 修改吐槽记录
     * @param spit
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}",method=RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spit.set_id(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改记录成功啦！！");
    }
    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id ){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除吐槽记录成功");
    }

    /**
     * 根据上级ID查询吐槽分页
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value="/comment/{parentId}/{page}/{size}",method=RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageList = spitService.findByParentid(parentId,page,size);
        return new Result(true,StatusCode.OK,"查询成功",
                new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent() ) );
    }

    /**
     * 点赞
     * @param id
     * @return
     */
    @RequestMapping(value="/thumbup/{id}",method=RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id){
        //判断用户是否点过赞
        String userid="2023";// 后边我们会修改为当前登陆的用户
        if(redisTemplate.opsForValue().get("thumbup_"+userid+"_"+
                id)!=null){
            return new Result(false,StatusCode.REPERROR,"你已经点过赞了");
        }
        spitService.updateThumbup(id);
        redisTemplate.opsForValue().set( "thumbup_"+userid+"_"+ id,"1");
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}
