package com.aisile.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by 简单· on 2019/4/18.
 */
@Entity
@Table(name="tb_label")
@Data
public class Label {
    @Id
    private String  id;//
    private String labelname;//标签名称
    private String state;//状态 0 有效 1 无效
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐 0 推荐 1不推荐

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
