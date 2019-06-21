package com.aisile.user.service;

import com.aisile.user.dao.AdminDao;
import com.aisile.user.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public IdWorker idWorker;

    @Autowired
    public BCryptPasswordEncoder encoder;

    /**
     * 查询所有条件
     * @return
     */
    public List<Admin> findAll(){
        return adminDao.findAll();
    }

    /**
     * 根据id查询实体
     * @param id
     * @return
     */
    public Admin findById(String id){
        return adminDao.findById(id).get();
    }
    /**
     * 添加新用户
     * MD5盐加密
     * @param admin
     */
    public void add(Admin admin){
        admin.setId(idWorker.nextId()+"");
        //额就要用MD5炒菜盐使劲加密
        String xinmima=encoder.encode(admin.getPassword());
        admin.setPassword(xinmima);
        adminDao.save(admin);
    }

    /**
     * 删除用户
     * @param id
     */
    public void deleteById(String id){
        adminDao.deleteById(id);
    }

    /**
     * 修改用户
     * @param admin
     */
    public void update(Admin admin){
        //MD5加密
        String xinmima=encoder.encode(admin.getPassword());
        admin.setPassword(xinmima);
        adminDao.save(admin);
    }
    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
    public Page<Admin> findSearch(Map whereMap, int page, int size) {
        Specification<Admin> specification = createSpecification(whereMap);
        PageRequest pageRequest =  PageRequest.of(page-1, size);
        return adminDao.findAll(specification, pageRequest);
    }


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
    public List<Admin> findSearch(Map whereMap) {
        Specification<Admin> specification = createSpecification(whereMap);
        return adminDao.findAll(specification);
    }

    /**
     *动态条件查询
     * @param searchMap
     * @return
     */
    private Specification<Admin> createSpecification(Map searchMap) {

        return new Specification<Admin>(){

            @Override
            public Predicate toPredicate(Root<Admin> root, CriteriaQuery<?> query, CriteriaBuilder sb) {
                List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                    predicateList.add(sb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                if (searchMap.get("loginname")!=null && !"".equals(searchMap.get("loginname"))) {
                    predicateList.add(sb.like(root.get("loginname").as(String.class), "%"+(String)searchMap.get("loginname")+"%"));
                }if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                    predicateList.add(sb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                    predicateList.add(sb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                return sb.and( predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 根据登陆名和密码查询
     * @param loginname
     * @param password
     * @return
     */
    public Admin findByLoginnameAndPassword(String loginname, String password){
        Admin admin = adminDao.findByLoginname(loginname);
        if( admin!=null && encoder.matches(password,admin.getPassword())){
            return admin;
        }else{
            return null;
        }
    }

}
