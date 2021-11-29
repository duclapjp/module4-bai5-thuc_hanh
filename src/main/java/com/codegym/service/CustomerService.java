package com.codegym.service;

import com.codegym.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer update(Customer customer);
    Customer findById(int id);
    void update(int id, Customer customer);
    void remote(int id);
    Customer findOne(Long id);
    List<Customer> save(List<Customer> customers);
    boolean exists(Long id);
    long count();
    void delete(Long id);
    void delete(Customer customer);
    void delete(List<Customer> customers);
    void deleteAll();
    List<Customer> findAll(List<Long> ids);
}
