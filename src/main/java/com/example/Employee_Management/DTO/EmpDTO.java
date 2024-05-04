package com.example.Employee_Management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmpDTO {
    private Integer id;

    @NotNull(message = "name cannot be empty")
    private String emp_name;

    private String role;
    private String address;
    @NotNull(message = "email cannot be empty")
    private String email;

    public void setid(Integer getid) {
        this.id=id;
    }

    public void setrole(String getrole) {
    }

    public void setaddress(String getaddress) {
    }

    public void setemail(String getemail) {
    }

    public void setemp_name(String getempName) {
    }
}
