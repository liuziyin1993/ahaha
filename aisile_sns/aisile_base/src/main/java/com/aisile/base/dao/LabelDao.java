package com.aisile.base.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.aisile.base.pojo.Label;

/**
 * Created by 简单· on 2019/4/18.
 */
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {

}
