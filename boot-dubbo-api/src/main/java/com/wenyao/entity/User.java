package com.wenyao.entity;

import com.wenyao.base.BaseEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="t_user")
public class User extends BaseEntity implements Serializable{

    @Id
    @GeneratedValue(generator="JDBC")
    private Integer id;

    private String telephone;

    private String channel;

    private Integer companyId;

    private Date created;

    private Date updated;

    private Integer status;

    private String name;

    private Integer role;

    private String headImgUrl;

    private Integer sex;

    private String position;

    private String remark;

    private String remarkName;

}