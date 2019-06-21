package com.aisile.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.aisile.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{
	//根据标签Id 查询最新问题  replyTime 降序
    @Query("select p from Problem  p where p.id in (select pl.problemid from Pl pl where pl.labelid = ?1) order by p.replytime desc")
    Page<Problem> findNewListByLabelId(String labelid, Pageable pageable);

    //根据标签Id 查询热门问题  reply 降序
    @Query("select p from Problem p where p.id in( select pl.problemid from Pl pl where pl.labelid=?1 ) order by p.reply desc")
    //@Query(value = "select pr.* from tb_problem pr left join tb_pl pl on pr.id = pl.problemid where pl.labelid = ?1 order by pr.reply desc",nativeQuery = true)
    Page<Problem> findHotListByLabelId(String labelid, Pageable pageable);

    //根据标签Id 查询等待回答问题  createtime 降序
    @Query("select p from Problem p where id in( select problemid from Pl where labelid=?1 ) and reply=0 order by createtime desc")
    //@Query(value = "SELECT x.* FROM tb_problem x LEFT JOIN tb_pl xx on x.id = xx.problemid WHERE xx.labelid=?1 AND x.reply = 0 ORDER BY x.createtime DESC ", nativeQuery = true)
    Page<Problem> findWaitListByLabelId(String labelid, Pageable pageable);
}
