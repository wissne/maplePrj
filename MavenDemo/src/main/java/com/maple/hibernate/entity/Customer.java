package com.maple.hibernate.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by maple on 2014/5/15.
 */
@Entity
@Table(name = "t_customer")
public class Customer {

    private Integer customerId;
    private String customerName;

    private Set<Order> orders;

    @Id
    @GeneratedValue
    @Column(name = "customer_id")
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Column(name = "customer_name")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Customer{");
        sb.append("customerId=").append(customerId);
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
