package com.example.Employee_Management.DAO;

import com.example.Employee_Management.Model.employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface employee_dao extends JpaRepository<employee,Integer> {


    employee-
    List<employee> findAll();

    List<employee> findByrole(String role);

    @Transactional
    void deleteAllById(int id);




}
