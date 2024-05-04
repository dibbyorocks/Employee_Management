package com.example.Employee_Management.Service;

import com.example.Employee_Management.Custom_Exception.ResourceNotFoundException;
import com.example.Employee_Management.DAO.employee_dao;
import com.example.Employee_Management.DTO.EmpDTO;
import com.example.Employee_Management.Model.employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class employee_service {

//BO class to get the business logic of all the operations
    @Autowired
     private employee_dao employee_dao;
   private employee emp;
    //Getting list of employee details
    public ResponseEntity<List<employee>> get_empdetails() {
        try {
            return new ResponseEntity<>(employee_dao.findAll(), HttpStatus.valueOf(200));
        } catch (Exception e) {
         e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_GATEWAY);
    }
   //adding employee
    public ResponseEntity<employee> addemp(employee emp) {
        return new ResponseEntity<>(employee_dao.save(emp),HttpStatus.CREATED);
    }
//get employee by role
    public List<employee> get_empbyrole(String role) {
        return  employee_dao.findByrole(role);
    }
//partial updating role of employee
    public EmpDTO partialUpdate(int id, String role) {

//        Todo todo = todoRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));
//
//        todo.setCompleted(Boolean.TRUE);
//
//        Todo updatedTodo = todoRepository.save(todo);
//
//        return mapToTodoDto(updatedTodo);     todoDto.setCompleted(todo.isCompleted());

        // employee_dao.findByrole(role);

        employee e2 = employee_dao.findById(id).orElseThrow();
        e2.setRole(String.valueOf(employee_dao.findByrole(role)));
        employee e2_new = employee_dao.save(e2);

        return  mapemp(e2_new);


    }

        private EmpDTO mapemp(employee e2){
      EmpDTO e1dto=new EmpDTO();
   e1dto.setid(e2.getid());
   e1dto.setemp_name(e2.getemp_name());
   e1dto.setrole(e2.getrole());
   e1dto.setaddress(e2.getaddress());
   e1dto.setemail(e2.getemail());
   return e1dto;
        }

//updating employee details
    public employee update_emp(int id, employee e1) {
       // employee e1=new employee();
        e1.setRole("jr developer");
        e1.setAddress("Mumbai");
        e1.setEmail("ueijeebbhb@gmail.com");
        e1.setEmp_name("RAMESH");
        return employee_dao.save(id,e1);
        // return e3;


    }

    //deleting employee details
    public void delete(int id) {

        employee e2=employee_dao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Todo not found with id : " + id));

        employee_dao.deleteAllById(id);


    }



}





