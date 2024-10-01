package th.co.cdgs.employee;
 
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Hibernate;

import io.quarkus.narayana.jta.runtime.TransactionConfiguration;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.OptimisticLockException;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import th.co.cdgs.RunningSrevice;
import th.co.cdgs.audit.AuditLog;
import th.co.cdgs.audit.AuditLogService;
 
@Path("employee")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class EmployeeResource {
 
    @Inject
    EntityManager entityManager;

    @Inject
    AuditLogService auditLogService;

    @Inject
    RunningSrevice runningSrevice;
 
/*     @Override
    public Response to Response(Exception exception){
        Map<String,String> messages = new HashMap<>();
        Throwable childCase = gerExpectCause(exception , expectClazz);
        if(childCase instanceof SQLException){
            Log.error("getErrorCode = " +((SQLException) childCase).getErrorCode());
            messages.put("message","SQLExcption code ="+((SQLException) childCase).getErrorCode());
        }else if (childCase instanceof jakarta.persistence.OptimisticLockException){
            messages.put("message", "ไม่สามารถแก้ไขข้อมูลได้ เนื่องจาก ข้อมูลถูกแก้ไขด้วยผู้อื่น");
        }else if (childCase instanceof jakarta.persistence.PersistenceException && childCase.getCause() instanceof org.hibernate.exception.ConstraintViolationException){
            messages.put("message", "Referential intgerity constraint violation");
        }else{
            messages.put("message", exception.getMessage());
        }
        return Response.status(Status.INTERNAL_SERVER_ERROR).entity(messages).build();
    } */
   
    @POST
    @Path("search")
    public List<Employee> search(@QueryParam(value = "litmit") @DefaultValue("10") int limit,
        @QueryParam(value = "offset") @DefaultValue("0") int offset, Employee condition){
             
        StringBuilder jpql = new StringBuilder("from Employee e where 1=1");
        if (condition.getStartRegisterDate() !=null) {
            jpql.append("and e.registerDate >= :startRegisterDate ");
        }
        if (condition.getEndRegisterDate() !=null){
            jpql.append("and e.registerDate <= :endRegsiterDate");
        }
        Query query = entityManager.createQuery(jpql.toString(), Employee.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        if (condition.getStartRegisterDate() !=null) {
            query.setParameter("startRegisterDate", condition.getStartRegisterDate());
        }
        if (condition.getEndRegisterDate() !=null) {
            query.setParameter("endRegsiterDate", condition.getEndRegisterDate());
        }
        return query.getResultList();      
    }
 
    @GET
    public List<Employee> get() {
        return entityManager.createQuery("from Employee", Employee.class).getResultList();
    }
/* ใช้เพื่อค้นหาข้อมูลใน table */
    @GET
    @Path("{id}")
    public Employee getSingle(Integer id) {
        Employee entity = entityManager.find(Employee.class, id);
        if (entity == null) {
            throw new WebApplicationException("employee with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        return entity;
    }
/* ใช้เพื่อเพิ่มข้อมุลใน table */
/*     @POST
    @Transactional
    public Response create(EmployeeRequest request) {
        if (request.getId() != null) {
            request.setId(null);
        }
        Employee employee = new Employee();
        employee.setDepartment(request.getDepartment());
        employee.setGender(request.getGender());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setRegisterDate(request.getRegisterDate());
        entityManager.persist(employee);
        for (EmployeeEmail email : request.getEmail()){
            EmployeeEmail employeeEmail = new EmployeeEmail();
            employeeEmail.setEmployeeId(employee.getId());
            employeeEmail.setEmail(email.getEmail());
            entityManager.persist(employeeEmail);
        }
        return Response.status(Status.CREATED).entity(employee).build();
    } */
    /*@POST อันเก่า
    @Transactional
    public Response create(Employee employee){
        auditLogService.logCreation(new AuditLog("Create Employee", employee.toString()));
        if (employee.getId() != null){
            employee.setId(null);
        }
        if(employee.getEmail()!= null){
            employee.getEmail().forEach(email -> email.setEmployee(employee));
        }
        entityManager.persist(employee);
        return Response.status(Status.CREATED).entity(employee).build();
    }
    */
    /* ใช้เพื่อแก้ไขข้อมูลทั้งหมดใน table */
    // @PUT
    // @Path("{id}")
    // @Transactional
    // public Response update(Integer id, Employee employee) {
    //     Employee entity = entityManager.find(Employee.class, id);
    //     if (entity == null) {
    //         throw new WebApplicationException("Employee with id of " + id + " does not exist.",
    //                 Status.NOT_FOUND);
    //     }
    //     Employee department;
    //     entity.setDepartment(entity.getDepartment());
    //     entity.setFirstName(employee.getFirstName());
    //     entity.setLastName(employee.getLastName());
    //     entity.setGender(employee.getGender());
    //     return Response.ok(entity).build();
    // }
/* ใช้เพื่อลบข้อมูลใน table */
    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Integer id) {
        Employee entity = entityManager.getReference(Employee.class, id);
        if (entity == null) {
            throw new WebApplicationException("Employee with id of " + id + " does not exist.",
                    Status.NOT_FOUND);
        }
        entityManager.remove(entity);
        return Response.ok().build();
    }
    @GET
    @Path("searchByNativeSql")
    public List<EmployeeBean> nativeQuery(@BeanParam EmployeeBeanParam condition) {
        StringBuilder sql = new StringBuilder(
                "select id, first_name,last_name,concat(first_name,' ',last_name) full_name,gender,department ");
        sql.append(" from employee ");
        sql.append(" where 1=1 ");
        if (condition.getFirstName() != null) {
            sql.append("and first_name like :firstName ");
        }
        if (condition.getLastName() != null) {
            sql.append("and last_name like :lastName ");
        }
        if (condition.getGender() != null) {
            sql.append("and gender = :gender ");
        }
        if (condition.getDepartment() != null) {
            sql.append("and department = :department ");
        }
        Query query = entityManager.createNativeQuery(sql.toString());
        if (condition.getFirstName() != null) {
            query.setParameter("firstName", condition.getFirstName());
        }
        if (condition.getLastName() != null) {
            query.setParameter("lastName", condition.getLastName());
        }
        if (condition.getGender() != null) {
            query.setParameter("gender", condition.getGender());
        }
        if (condition.getDepartment() != null) {
            query.setParameter("department", condition.getDepartment());
        }
        List<EmployeeBean> list = new ArrayList<>();
        List<Object[]> objects = query.getResultList();
        for (Object[] object : objects) {
            EmployeeBean emp = new EmployeeBean();
            emp.setId((Integer) object[0]);
            emp.setFirstName((String) object[1]);
            emp.setLastName((String) object[2]);
            emp.setFullName((String) object[3]);
            emp.setGender(String.valueOf(object[4]));
            emp.setDepartment((String) object[5]);
            list.add(emp);
        }
        return list;
    }

    @PATCH
    @Transactional
    public Response changeDepartment(Employee request) {
        Employee entity = entityManager.createQuery("from Employee e join fetch e.department join fetch e.email where e.id = :id",Employee.class).setParameter("id", request.getId()).getSingleResult();
        // ตรวจสอบ version
        if(!Objects.equals(entity.getVersion(), request.getVersion())){
            throw new OptimisticLockException();
        }
        // อัปเดตเฉพาะฟิลด์ department ที่มาจาก request
        entity.setDepartment(request.getDepartment());
        // Hibernate.initialize ใช้สำหรับการโหลดค่าแบบ Lazy
        Hibernate.initialize(entity.getDepartment());
        return Response.ok(entity).build();
    }
    @PUT
    @Transactional
    public Response update(final Employee request) {
        if (request.getEmail() != null) {
            request.getEmail().forEach(email ->{
                email.setEmployee(request);
            });
        }
        Employee entity = entityManager.merge(request);
        Hibernate.initialize(entity.getDepartment());
        Hibernate.initialize(entity.getEmail());
        return Response.ok(entity).build();
    }
 
    
    @POST
    @Transactional
    public Response create(Employee employee) {
        auditLogService.logCreation (new AuditLog("Create Employee", employee.toString()));
        if (employee.getId() != null){
            employee.setId(null);
        }
        if (employee.getEmail() != null) {
            employee.setId(null);
            employee.getEmail().forEach(email -> {
                email.setEmployee (employee);
            });
        }
        employee.setSeqNo(runningSrevice.next(Employee.class.getName())); entityManager.persist (employee);
        return Response.status(Status.CREATED).entity (employee).build();
    }

    
    @POST
    @Path("/sleep")
    @Transactional
    @TransactionConfiguration (timeout = 70000)
    public Response sleep (Employee employee) throws InterruptedException {
        Thread.sleep(60000);
        entityManager.persist (employee);
        return Response.status(Status.CREATED).entity (employee).build();
    }
}
