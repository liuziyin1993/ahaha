package com.aisile.article.controller;

import com.aisile.article.pojo.Comment;
import com.aisile.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    public CommentService commentService;

    /**
     * 评论模块
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment){
        commentService.add(comment);
        return new Result(true, StatusCode.OK,"评论成功");
    }

    /**
     * 查询评论模块
     * @param articleid
     * @return
     */
    @RequestMapping(value="/article/{articleid}",method= RequestMethod.GET)
    public Result findByArticleid(@PathVariable String articleid){
        return new Result(true, StatusCode.OK, "查询成功",commentService.findByArticleid(articleid));
    }

    /**
     * 删除评论
     * @param articleid
     * @return
     */
    @RequestMapping(value="/{articleid}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String articleid ){
        commentService.deleteById(articleid);
        return new Result(true,StatusCode.OK,"删除评论成功");
    }
}
