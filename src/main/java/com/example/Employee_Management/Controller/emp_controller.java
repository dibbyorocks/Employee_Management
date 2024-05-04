package com.example.Employee_Management.Controller;

import com.example.Employee_Management.DTO.EmpDTO;
import com.example.Employee_Management.Model.employee;
import com.example.Employee_Management.Service.employee_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RestControllerAdvice
@RequestMapping("/emp")
public class emp_controller {

    @Autowired
    private employee_service employee_service;

    //endpoint for adding employee details
    @PostMapping("/addemp")
public ResponseEntity<employee> add_emp(@RequestBody employee emp)
{
return employee_service.addemp(emp);
}

//endpoint for getting employee details
    @GetMapping("/get_empdetails")
    public  ResponseEntity<List<employee>> get_empdetails(){
        return employee_service.get_empdetails();

}

//----------endpoint for getting employee details by role
    @GetMapping("/getempbyrole/{role}")
    public ResponseEntity<List<employee>>get_empbyrole(@PathVariable String role){
        List<employee> e1= employee_service.get_empbyrole(role);
        return ResponseEntity.ok(e1);
}

//endpoint for partially updating the employee details
    @PatchMapping("/partialUpdate/{id}/{role}")
    public ResponseEntity<EmpDTO> update_role(@PathVariable int id, @PathVariable String role) {

        EmpDTO emp1 = employee_service.partialUpdate(id, role);
        return ResponseEntity.ok(emp1);
    }
        //endpoint for deleting the employee details

//    @DeleteMapping("/del_emp/{id}")
//    public ResponseEntity<employee> delete_emp( @PathVariable int id){
//             employee e1=employee_service.deleteEmp(id);
//             return ResponseEntity.ok(e1);
//
//    }

    //updating the details of employee
    @PutMapping("/update/{id}")
    public ResponseEntity<employee> update( @PathVariable int id,@RequestBody employee e1  )
                {
                    employee e2=employee_service.update_emp(id,e1);
                    return ResponseEntity.ok(e2);


        }

   @DeleteMapping("/deleteEmp/{id}")
         public ResponseEntity<String> Delete(@PathVariable int id)

   {
         employee_service.delete(id);
        return  ResponseEntity.ok("employee details deleted");
   }


}

