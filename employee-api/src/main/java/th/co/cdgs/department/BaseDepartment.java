package th.co.cdgs.department;
 

 
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
 
@MappedSuperclass
public class BaseDepartment {
    @Id
    @SequenceGenerator(name = "departmentSequence",sequenceName="department_id_seq",
                    allocationSize=1,initialValue = 15)
    @GeneratedValue(generator = "departmentSequence")
    private Integer code;
 
    @Column(length = 100)
    private String name;
 
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