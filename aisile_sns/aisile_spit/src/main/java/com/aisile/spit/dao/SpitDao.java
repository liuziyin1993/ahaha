package com.aisile.spit.dao;

import com.aisile.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.awt.print.Pageable;

/**
 * 吐槽数据访问层
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * 根据上级ID查询吐槽列表（分页）
     * @param parentid
     * @param pageable
     * @return
     */
    Page<Spit> findByParentid(String parentid, PageRequest pageRequest);
}
