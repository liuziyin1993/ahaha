package com.aisile.user.service;

import com.aisile.user.dao.UserDao;
import com.aisile.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    public UserDao userDao;

    @Autowired
    public IdWorker idWorker;


    /**
     * 查询全部列表
     * @return
     */
    public List<User> findAll() {
        return userDao.findAll();
    }


    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<User> findSearch(Map whereMap, int page, int size) {
        Specification<User> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return userDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<User> findSearch(Map whereMap) {
        Specification<User> specification = createSpecification(whereMap);
        return userDao.findAll(specification);
    }

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public User findById(String id) {
        return userDao.findById(id).get();
    }

    /**
     * 增加
     * @param User
     */
    public void add(User User) {
        User.setId( idWorker.nextId()+"" );
        userDao.save(User);
    }

    /**
     * 修改
     * @param User
     */
    public void update(User User) {
        userDao.save(User);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        userDao.deleteById(id);
    }

    /**
     * 动态条件构建
     *
     * @param searchMap
     * @return
     */
    private Specification<User> createSpecification(Map searchMap) {

        return new Specification<User>() {

            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                    predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                    predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                if (searchMap.get("birthday")!=null && !"".equals(searchMap.get("birthday"))) {
                    predicateList.add(cb.like(root.get("birthday").as(String.class), "%"+(String)searchMap.get("birthday")+"%"));
                }
                if (searchMap.get("avatar")!=null && !"".equals(searchMap.get("avatar"))) {
                    predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
                }
                if (searchMap.get("mobile")!=null && !"".equals(searchMap.get("mobile"))) {
                    predicateList.add(cb.like(root.get("mobile").as(String.class), "%"+(String)searchMap.get("mobile")+"%"));
                }
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                    predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                if (searchMap.get("regdate")!=null && !"".equals(searchMap.get("regdate"))) {
                    predicateList.add(cb.like(root.get("regdate").as(String.class), "%"+(String)searchMap.get("regdate")+"%"));
                }
                if (searchMap.get("updatedate")!=null && !"".equals(searchMap.get("updatedate"))) {
                    predicateList.add(cb.like(root.get("updatedate").as(String.class), "%"+(String)searchMap.get("updatedate")+"%"));
                }
                if (searchMap.get("lastdate")!=null && !"".equals(searchMap.get("lastdate"))) {
                    predicateList.add(cb.like(root.get("lastdate").as(String.class), "%"+(String)searchMap.get("lastdate")+"%"));
                }
                if (searchMap.get("online")!=null && !"".equals(searchMap.get("online"))) {
                    predicateList.add(cb.like(root.get("online").as(String.class), "%"+(String)searchMap.get("online")+"%"));
                }
                if (searchMap.get("interest")!=null && !"".equals(searchMap.get("interest"))) {
                    predicateList.add(cb.like(root.get("interest").as(String.class), "%"+(String)searchMap.get("interest")+"%"));
                }
                if (searchMap.get("personality")!=null && !"".equals(searchMap.get("personality"))) {
                    predicateList.add(cb.like(root.get("personality").as(String.class), "%"+(String)searchMap.get("personality")+"%"));
                }
                if (searchMap.get("fanscount")!=null && !"".equals(searchMap.get("fanscount"))) {
                    predicateList.add(cb.like(root.get("fanscount").as(String.class), "%"+(String)searchMap.get("fanscount")+"%"));
                }
                if (searchMap.get("followcount")!=null && !"".equals(searchMap.get("followcount"))) {
                    predicateList.add(cb.like(root.get("followcount").as(String.class), "%"+(String)searchMap.get("followcount")+"%"));
                }
                return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));
            }

        };
    }

}