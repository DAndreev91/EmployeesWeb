package students.logic;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagementSystem {

    private static Connection con;
    private static ManagementSystem instance;

    private ManagementSystem() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@131.108.39.3:1521:reg";
            con = DriverManager.getConnection(url, "andreev", "1234");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ManagementSystem.class.getName()).log(Level.SEVERE, null, e);
        } catch (SQLException e) {
            Logger.getLogger(ManagementSystem.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }

    /*public static void main(String[] args) {
        try {
            ManagementSystem ms = ManagementSystem.getInstance();
        } catch (Exception ex) {
            Logger.getLogger(ManagementSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public List<Department> getDepartments() throws SQLException {
        List<Department> departments = new ArrayList<Department>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT department_id, department_name, manager_id, location_id "
                    + "FROM departments");
            while (rs.next()) {
                Department dep = new Department(rs);
                departments.add(dep);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return departments;
    }

    public Collection<Employee> getAllEmployees() throws SQLException {
        Collection<Employee> employees = new ArrayList<Employee>();

        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "SELECT employee_id, first_name, last_name, email, phone_number,"
                            + "hire_date, job_id, salary, commission_pct, manager_id, department_id \n" +
                        "FROM employees");
            while (rs.next()) {
                Employee emp = new Employee(rs);
                employees.add(emp);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return employees;
    }

    public Collection<Employee> getEmployeesFromDepartment(Department dep, int year) throws SQLException {
        Collection<Employee> employees = new ArrayList<Employee>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "SELECT employee_id, first_name, last_name, email, phone_number,"
                            + "hire_date, job_id, salary, commission_pct, manager_id, department_id \n" +
                        "FROM employees WHERE department_id = ?");
            stmt.setInt(1, dep.getDepartmentId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Employee emp = new Employee(rs);
                employees.add(emp);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return employees;
    }
    
    public Employee getEmployeeById(int employeeId) throws SQLException {
        Employee employee = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(
                    "SELECT employee_id, first_name, last_name, email, phone_number,"
                            + "hire_date, job_id, salary, commission_pct, manager_id, department_id \n" +
                        "FROM employees WHERE employee_id = ?");
            stmt.setInt(1, employeeId);
            rs = stmt.executeQuery();
            while (rs.next()) {
                employee = new Employee(rs);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

        return employee;
    }

    /*public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE students SET group_id=?, educationYear=? " +
                    "WHERE group_id=? AND educationYear=?");
            stmt.setInt(1, newGroup.getGroupId());
            stmt.setInt(2, newYear);
            stmt.setInt(3, oldGroup.getGroupId());
            stmt.setInt(4, oldYear);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }*/

    /*public void removeStudentsFromGroup(Group group, int year) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM students WHERE group_id=? AND educationYear=?");
            stmt.setInt(1, group.getGroupId());
            stmt.setInt(2, year);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }*/

    public void insertEmployee(Employee emp) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "INSERT INTO employees " +
                    "VALUES (andreev.EMPLOYEES_SEQ.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhoneNumber());
            stmt.setDate(5, (Date) emp.getHireDate());
            stmt.setString(6, emp.getJobId());
            stmt.setInt(7, emp.getSalary());
            stmt.setInt(8, emp.getCommissionPct());
            stmt.setInt(9, emp.getManagerId());
            stmt.setInt(10, emp.getDepartmentId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void updateEmployee(Employee emp) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "UPDATE employees SET " +
                    "first_name=?, last_name=?, email=?, " +
                    "phone_number=?, hire_date=?, job_id=?, " +
                    "salary=?, commission_pct=?, manager_id=?, department_id=? " +
                    "WHERE employee_id=?");
            stmt.setString(1, emp.getFirstName());
            stmt.setString(2, emp.getLastName());
            stmt.setString(3, emp.getEmail());
            stmt.setString(4, emp.getPhoneNumber());
            java.sql.Date sqlDate = new java.sql.Date(emp.getHireDate().getTime());
            stmt.setDate(5, sqlDate);
            stmt.setString(6, emp.getJobId());
            stmt.setInt(7, emp.getSalary());
            stmt.setInt(8, emp.getCommissionPct());
            stmt.setInt(9, emp.getManagerId());
            stmt.setInt(10, emp.getDepartmentId());
            stmt.setInt(11, emp.getEmployeeId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteEmployee(Employee emp) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(
                    "DELETE FROM employees WHERE id=?");
            stmt.setInt(1, emp.getEmployeeId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}