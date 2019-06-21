package com.aisile.article.service;

import com.aisile.article.dao.CommentDao;
import com.aisile.article.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    public CommentDao commentDao;

    @Autowired
    public IdWorker idWorker;

    /**
     * 文章评论
     * @param comment
     */
    public void add(Comment comment){
        comment.set_id( idWorker.nextId()+"");
                commentDao.save(comment);
    }

    /**
     * 查询文章评论列表
     * @param articleid
     * @return
     */
    public List<Comment> findByArticleid(String articleid) {
        return commentDao.findByArticleid(articleid);
    }

    /**
     * 删除评论
     * @param articleid
     */
    public void deleteById(String articleid) {
        commentDao.deleteById(articleid);
    }
}
