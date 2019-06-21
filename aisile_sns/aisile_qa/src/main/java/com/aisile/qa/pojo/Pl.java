package com.aisile.qa.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by 简单· on 2019/4/22.
 */
@Entity
@Table(name = "tb_pl")
@Data
public class Pl implements Serializable{
    @Id
    private String problemid;
    @Id
    private String labelid;

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }


    public Pl() {
    }

    public Pl(String problemid, String labelid) {

        this.problemid = problemid;
        this.labelid = labelid;
    }
}
