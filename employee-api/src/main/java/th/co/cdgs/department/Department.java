package th.co.cdgs.department;
 

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
 
@Entity
@Table(name ="department")
public class Department {
    @Id
    @SequenceGenerator(name = "departmentSequence", sequenceName = "department_code_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "departmentSequence")
    private Integer code;
 
    @Column(length = 100)
    private String name;
   

    public Department() {
    }
   
    public Department(Integer code, String name) {
        this.code = code;
        this.name = name;
    }
 
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}