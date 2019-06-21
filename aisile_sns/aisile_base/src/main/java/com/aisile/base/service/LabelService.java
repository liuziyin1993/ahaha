package com.aisile.base.service;

import com.aisile.base.dao.LabelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.aisile.base.pojo.Label;
import org.springframework.util.StringUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 简单· on 2019/4/18.
 */
@Service
public class LabelService {
    @Autowired
    LabelDao labelDao;
    @Autowired
    IdWorker idWorker;

    /*
     *查询全部
     * */
    public List<Label> findAll(){
        return labelDao.findAll();
    }

    /*
     *根据ID查询
     * */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    /*
    * 添加标签
    * */
    public void add(Label label) {
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    /*
    * 修改标签
    * */
    public void update(Label label) {
        //label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    /*
    * 删除标签
    * */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }
    /*
    * 条件查询 labekname like ? and state = ? and recommond = ？
    * */
    public List<Label> findSearch(Map searchMap) {
        return labelDao.findAll(getSpecification(searchMap));
    }
    /*
    * 分页条件查询 labekname like ? and state = ? and recommond = ？
    * */
    public Page<Label> findSearch(Map searchMap, int page, int size) {
        PageRequest pageRequest= PageRequest.of(page , size);
        return labelDao.findAll(getSpecification(searchMap), pageRequest);
    }

    /*
    * 拼装条件 labekname like ? and state = ? and recommond = ？
    * */
    public Specification<Label> getSpecification(Map searchMap){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                //拼查询条件
                List<Predicate> list = new ArrayList<>();
                String labelname=(String)searchMap.get("labelname");//标签名称
                String state=(String)searchMap.get("state");//0 无效   1  有效
                String recommend=(String)searchMap.get("recommend"); //0 不推荐 1 推荐
                if (!StringUtils.isEmpty(labelname)){
                    list.add(cb.like(root.get("labelname"),"%"+labelname+"%"));//添加条件
                }
                if (!StringUtils.isEmpty(state)){
                    list.add(cb.equal(root.get("state"), state));//添加条件
                }
                if (!StringUtils.isEmpty(recommend)){
                    list.add(cb.equal(root.get("recommend"), recommend));//添加条件
                }
                Predicate and = cb.and(list.toArray(new Predicate[list.size()]));
                return and;
            }
        };
    }
}
