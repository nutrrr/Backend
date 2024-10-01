package th.co.cdgs.employee;
 
 
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "employee_email")
public class EmployeeEmail {
 
    @Id
    @SequenceGenerator(name = "employeeEmailmentSequence", sequenceName = "employee_email_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "employeeEmailmentSequence")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "employee_id",referencedColumnName = "id")
    @JsonBackReference
    private Employee employee;

   

    @Column(name = "email",length=255)
    private String email;
   
 
    public Integer getId() {
        return id;
    }
 
 
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 

    public EmployeeEmail() {
    }

    public Employee getEmployee(){
        return employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
 
   
}