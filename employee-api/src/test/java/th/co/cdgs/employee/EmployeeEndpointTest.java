package th.co.cdgs.employee;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import th.co.cdgs.department.Department;

@QuarkusTest
class EmployeeEndpointTest {
        

        @Test
        void getEmployeeHasTonyAndSteve() throws JsonMappingException, JsonProcessingException {
                Response response = given().when().get("/employee").then().extract().response();
                assertEquals(200, response.statusCode());
                ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Employee[] employees = objectMapper.readValue(response.body().asString(),
                                Employee[].class);
                assertEquals(14, employees.length);
                for (Employee employee : employees) {
                        switch (employee.getId()) {
                                case 1:
                                        assertEquals("Tony", employee.getFirstName());
                                        assertNotNull(employee.getDepartment());
                                        assertEquals("Mavel", employee.getDepartment().getName());
                                        break;
                                case 2:
                                        assertEquals("Bruce", employee.getFirstName());
                                        assertNotNull(employee.getDepartment());
                                        assertEquals("DC", employee.getDepartment().getName());
                                        break;
                                default:
                                        break;
                        }
                }

        }

        @Test
        void getEmployeeByIdHasTony() throws JsonMappingException, JsonProcessingException {
                Response response = given().when().get("/employee/1").then().extract().response();
                assertEquals(200, response.statusCode());
                ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                Employee employee = objectMapper.readValue(response.body().asString(),
                                Employee.class);
                assertEquals("Tony", employee.getFirstName());
        }

        @Test
        void createThoeStatusShouldCreated() throws JsonProcessingException {
                Employee employee = new Employee();
                employee.setFirstName("Thor");
                given().when().body(new ObjectMapper().writeValueAsString(employee))
                                .contentType("application/json").post("/employee").then()
                                .statusCode(201);
        }

        @Test
        void deleteTonyShouldFindGetStatus404() {
                given().when().delete("/employee/14").then().statusCode(200);
                given().when().get("/employee/14").then().statusCode(404);
        }



        @Test
        void updateBruceToDCShouldGetDC() throws JsonProcessingException {
                Employee employee = new Employee();
                employee.setId(3);
                employee.setFirstName("Bruce");
                employee.setLastName("Banner");
                employee.setGender("M");
                employee.setDepartment(new Department(2, "DC"));
                ObjectMapper objectMapper = new ObjectMapper();
                given().when().body(objectMapper.writeValueAsString(employee))
                                .contentType("application/json").put("/employee/3").then()
                                .statusCode(200).body(containsString("DC"));


        }


        @Test
        void putNoIdShouldException() throws JsonProcessingException {
                Employee employee = new Employee();
                employee.setId(2);
                employee.setDepartment(new Department(1, "Mavel"));
                ObjectMapper objectMapper = new ObjectMapper();
                given().when().body(objectMapper.writeValueAsString(employee))
                                .contentType("application/json").put("/employee/100").then()
                                .statusCode(404).body(containsString("Employee with id of 100"));
        }

}
