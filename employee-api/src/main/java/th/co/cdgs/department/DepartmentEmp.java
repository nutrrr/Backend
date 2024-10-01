package th.co.cdgs.department;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import th.co.cdgs.employee.Employee;

@Entity
@Table(name = "department")
public class DepartmentEmp extends BaseDepartment{

	@Id
	@Column(length = 100)
    private Integer code;

    @Column(length = 100)
    private String name;
    
    @OneToMany(mappedBy="department")
    private List<Employee> employees;
    
   
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

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}



}
