package students.logic;

import java.text.DateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.Collator;
import java.util.Date;
import java.util.Locale;

public class Employee implements Comparable {

    private int employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date hireDate;
    private String jobId;
    private int salary;
    private int commissionPct;
    private int managerId;
    private int departmentId;

    public Employee() {
    }
    
    public Employee(int employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(ResultSet rs) throws SQLException {
        this.employeeId = rs.getInt(1);
        this.firstName = rs.getString(2);
        this.lastName = rs.getString(3);
        this.email = rs.getString(4);
        this.phoneNumber = rs.getString(5);
        this.hireDate = rs.getDate(6);
        this.jobId = rs.getString(7);
        this.salary = rs.getInt(8);
        this.commissionPct = rs.getInt(9);
        this.managerId = rs.getInt(10);
        this.departmentId = rs.getInt(11);
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(int commissionPct) {
        this.commissionPct = commissionPct;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    

    @Override
    public String toString() {
        return String.valueOf(employeeId);
    }

    @Override
    public int compareTo(Object obj) {
        Collator c = Collator.getInstance(new Locale("ru"));
        c.setStrength(Collator.PRIMARY);
        return c.compare(this.toString(), obj.toString());
    }
}