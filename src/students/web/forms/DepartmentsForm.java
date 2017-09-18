package students.web.forms;

import java.util.Collection;

    
public class DepartmentsForm {
    
    private int departmentId;
    private Collection departments;
    private Collection employees;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Collection getDepartments() {
        return departments;
    }

    public void setDepartments(Collection departments) {
        this.departments = departments;
    }

    public Collection getEmployees() {
        return employees;
    }

    public void setEmployees(Collection employees) {
        this.employees = employees;
    }
    
}
