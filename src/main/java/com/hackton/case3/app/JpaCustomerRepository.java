package com.hackton.case3.app;

import com.hackton.case3.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCustomerRepository extends JpaRepository<Customer, Long> {

}
