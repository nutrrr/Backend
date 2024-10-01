package th.co.cdgs.employee;
 
import java.util.Date;
import java.util.List;
 
import th.co.cdgs.department.Department;
 
public class EmployeeRequest {
    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Department department;
    private Date registerDate;
    private List<EmployeeEmail> email;
   
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Department getDepartment() {
        return department;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Date getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
    public List<EmployeeEmail> getEmail() {
        return this.email;
    }
    public void setEmail(List<EmployeeEmail> email) {
        this.email = email;
    }
 
}