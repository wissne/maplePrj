package com.maple.hibernate.entity;

import javax.persistence.*;

/**
 * Created by maple on 2014/5/14.
 */
@Entity
@Table(name = "TEST_BODY")
public class Body {

    private Integer id;
    private Heart heart;

    @Id
    @GeneratedValue
    @Column(name = "BODY_ID")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name ="id")
    public Heart getHeart() {
        return heart;
    }

    public void setHeart(Heart heart) {
        this.heart = heart;
    }
}
