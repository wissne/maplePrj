package com.maple.hibernate.entity;

import javax.persistence.*;

/**
 * Created by maple on 2014/5/14.
 */
@Entity
@Table(name = "TEST_HEART")
public class Heart {
    private Integer id;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
