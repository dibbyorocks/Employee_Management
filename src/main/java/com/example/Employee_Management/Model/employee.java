package com.example.Employee_Management.Model;

import javax.persistence.*;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@Entity
@Table(name="emp_details")
public class employee {


    //public String getemail;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@NotNull(message = "name cannot be empty")
    private String emp_name;

    private String role;
    private String address;
    //@NotNull(message = "email cannot be empty")
   private String email;


    public void setRole(String role) {
        this.role=role;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getid() {
        return id;
    }
    public String getrole(){
        return role;
    }
    public String getaddress()
    {
        return address;
    }
    public String getemail(){
        return email;
    }
    public String getemp_name(){
        return emp_name;
    }


}
