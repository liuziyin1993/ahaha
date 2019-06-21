package com.aisile.user.dao;

import com.aisile.user.pojo.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FollowDao extends JpaRepository<Follow,String>,JpaSpecificationExecutor<Follow>{

}