package com.aisile.user.service;

import com.aisile.user.dao.FollowDao;
import com.aisile.user.pojo.Follow;
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
public class FollowService {
    @Autowired
    public FollowDao followDao;

    @Autowired
    public IdWorker idWorker;

    /**
     * 查询全部
     * @return
     */
    public List<Follow> findAll() {
        return followDao.findAll();
    }

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    public Follow findById(String id){
        return followDao.findById(id).get();
    }

    /**
     * 添加方法
     * @param follow
     */
    public void add(Follow follow){
        follow.setUserid(idWorker.nextId()+"");
        followDao.save(follow);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id){
        followDao.deleteById(id);
    }

    /**
     * 修改方法
     * @param follow
     */
    public void update(Follow follow){
        followDao.save(follow);
    }

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Follow> findSearch(Map whereMap, int page, int size) {
        Specification<Follow> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return followDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<Follow> findSearch(Map whereMap) {
        Specification<Follow> specification = createSpecification(whereMap);
        return followDao.findAll(specification);
    }

    /**
     * 动态条件查询
     * @param searchMap
     * @return
     */
    private Specification<Follow> createSpecification(Map searchMap) {

        return new Specification<Follow>(){

            @Override
            public Predicate toPredicate(Root<Follow> root, CriteriaQuery<?> query, CriteriaBuilder sb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                    predicateList.add(sb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                if (searchMap.get("targetuser")!=null && !"".equals(searchMap.get("targetuser"))) {
                    predicateList.add(sb.like(root.get("targetuser").as(String.class), "%"+(String)searchMap.get("targetuser")+"%"));
                }
                return sb.and( predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}
