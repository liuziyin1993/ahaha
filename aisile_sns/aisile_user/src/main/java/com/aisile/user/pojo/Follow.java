package com.aisile.user.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_follow")
public class Follow {
    @Id
    public String userid;
    public String targetuser;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTargetuser() {
        return targetuser;
    }

    public void setTargetuser(String targetuser) {
        this.targetuser = targetuser;
    }
}
