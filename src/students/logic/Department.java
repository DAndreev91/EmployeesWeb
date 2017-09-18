package students.logic;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Department {

    private int departmentId;
    private String departmentName;
    private int managerId;
    private int locationId;
    
    public Department(ResultSet rs) throws SQLException {
        setDepartmentId(rs.getInt(1));
        setDepartmentName(rs.getString(2));
        setManagerId(rs.getInt(3));
        setLocationId(rs.getInt(4));
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return departmentName;
    }
}