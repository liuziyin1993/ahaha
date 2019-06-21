package com.aisile.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.aisile.recruit.pojo.Recruit;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{
	//查询前4个  以发布日期降序   state 为2
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);
    //查询前12个  以创建日期降序  state不为0
    List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);
}
