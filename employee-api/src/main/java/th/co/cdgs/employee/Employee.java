package th.co.cdgs.employee;
 
import java.util.Date;
import java.util.Set;
 
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.Version;
import th.co.cdgs.department.Department;
 
@Entity
@Table(name = "employee")
public class Employee {
   
   
    @Id
    @SequenceGenerator(name = "employeeSequence", sequenceName = "employee_id_seq",
            allocationSize = 1, initialValue = 15)
    @GeneratedValue(generator = "employeeSequence")
    private Integer id;
 
    @Column(name = "first_name", length = 100)
    private String firstName;
 
    @Column(name = "last_name", length = 100)
    private String lastName;
 
    @Column(length = 1)
    private String gender;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department",referencedColumnName = "code")
    private Department department;
 
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<EmployeeEmail>email;

    @Version
    private Integer version;

    @Column(name = "seq_no")
    private Integer seqNo;

 
    public Integer getSeqNo() {
        return seqNo;
    }
    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }
    public void setDepartment(Department department) {
        this.department = department;
    }
    public Department getDepartment(){
        return department;
    }
    @Column(name = "register_date")
    private Date registerDate;
 
    @Transient
    private Date startRegisterDate;
 
    @Transient
    private Date endRegisterDate;
 
 
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
 
    public Date getRegisterDate() {
        return registerDate;
    }
 
    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
 
    public String getFullName() {
        return firstName + " " + lastName;
    }
 
    public Date getStartRegisterDate() {
        return startRegisterDate;
    }
 
    public void setStartRegisterDate(Date startRegisterDate) {
        this.startRegisterDate = startRegisterDate;
    }
 
    public Date getEndRegisterDate() {
        return endRegisterDate;
    }
 
    public void setEndRegisterDate(Date endRegisterDate) {
        this.endRegisterDate = endRegisterDate;
    }
 
    public Set<EmployeeEmail> getEmail() {
        return email;
    }
    public void setEmail(Set<EmployeeEmail> email) {
        this.email = email;
    }
    public Integer getVersion() {
        return version;
    }
    public void setVersion(Integer version) {
        this.version = version;
    }
 
 
 
   
}