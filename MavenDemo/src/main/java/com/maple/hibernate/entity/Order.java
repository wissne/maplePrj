package com.maple.hibernate.entity;

import javax.persistence.*;

/**
 * Created by maple on 2014/5/15.
 */
@Entity
@Table(name = "t_order")
public class Order {

    private Integer orderId;
    private String orderName;

    private Customer customer;

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column(name = "order_name")
    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Order{");
        sb.append("orderId=").append(orderId);
        sb.append(", orderName='").append(orderName).append('\'');
//        sb.append(", customer=").append(customer);
        sb.append('}');
        return sb.toString();
    }
}
