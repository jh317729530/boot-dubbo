package com.wenyao.base;


import lombok.Data;

import javax.persistence.Transient;

@Data
public class BaseEntity {

    @Transient
    private Integer page = 1;

    @Transient
    private Integer rows = 10;
}
