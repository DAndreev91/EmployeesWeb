
package students.web.forms;

import java.util.Collection;
import students.logic.Department;
import students.logic.Employee;


public class EmployeeForm {
    
    private Employee employee;
    private Collection<Department> departments;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Collection<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(Collection<Department> departments) {
        this.departments = departments;
    }
    
}
