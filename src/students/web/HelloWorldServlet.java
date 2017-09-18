package students.web;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import students.logic.Employee;
import students.logic.ManagementSystem;
import students.web.forms.DepartmentsForm;
import students.web.forms.EmployeeForm;

public class HelloWorldServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action;
        action = checkAction(req);
        if (action == "Delete")  {
            try {
                if (req.getParameter("employeeId") != null) {
                    int empId = Integer.parseInt(req.getParameter("employeeId"));
                    Employee emp = ManagementSystem.getInstance().getEmployeeById(empId);
                    ManagementSystem.getInstance().deleteEmployee(emp);
                }
            } catch (SQLException e) {
                Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, e);
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/Error.jsp").forward(req, res);
                return;
            }
        }
        if (action == "Edit") {
            try {
                if (req.getParameter("employeeId") != null) {
                    int empId = Integer.parseInt(req.getParameter("employeeId"));
                    Employee emp = ManagementSystem.getInstance().getEmployeeById(empId);
                    Collection deps = ManagementSystem.getInstance().getDepartments();
                    EmployeeForm empForm = new EmployeeForm();
                    empForm.setEmployee(emp);
                    empForm.setDepartments(deps);
                    req.setAttribute("empForm", empForm);
                    getServletContext().getRequestDispatcher("/EmployeePage.jsp").forward(req, res);
                    return;
                }
            } catch (SQLException e) {
                Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, e);
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/Error.jsp").forward(req, res);
                return;
            }
        }
        if (action == "Add") {
            try {
                Employee emp = new Employee(0);
                Collection deps = ManagementSystem.getInstance().getDepartments();
                EmployeeForm empForm = new EmployeeForm();
                empForm.setEmployee(emp);
                empForm.setDepartments(deps);
                req.setAttribute("empForm", empForm);
                getServletContext().getRequestDispatcher("/EmployeePage.jsp").forward(req, res);
                return;
            } catch (SQLException e) {
                Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, e);
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/Error.jsp").forward(req, res);
                return;
            }
        }
        if (action == "Save") {
             int empId = Integer.parseInt(req.getParameter("employeeId"));
             try {
                Employee emp = getEmployeeFromPage(req);
                if (empId > 0) {
                    ManagementSystem.getInstance().updateEmployee(emp);
                } else {
                    ManagementSystem.getInstance().insertEmployee(emp);
                }
             } catch (SQLException e) {
                Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, e);
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/Error.jsp").forward(req, res);
                return;
             } catch (ParseException e) {
                Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, e);
                req.setAttribute("error", e);
                getServletContext().getRequestDispatcher("/Error.jsp").forward(req, res);
                return;
             }
        }
        
        int departmentId = 0;
        if (req.getParameter("departmentId") != null) {
            departmentId = Integer.parseInt(req.getParameter("departmentId"));
        }
        DepartmentsForm depsForm = new DepartmentsForm();
        try {
            Collection emps = ManagementSystem.getInstance().getAllEmployees();
            Collection deps = ManagementSystem.getInstance().getDepartments();
            depsForm.setDepartmentId(departmentId);
            depsForm.setEmployees(emps);
            depsForm.setDepartments(deps);
        } catch (SQLException e) {
            Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, e);
            req.setAttribute("error", e);
            getServletContext().getRequestDispatcher("/Error.jsp").forward(req, res);
            return;
        }
        req.setAttribute("depsForm", depsForm);
        getServletContext().getRequestDispatcher("/DepartmentsPage.jsp").forward(req, res);
    }
    
    private String checkAction(HttpServletRequest req) {
        if (req.getParameter("Add") != null) {
            return "Add";
        } if (req.getParameter("Edit") != null) {
            return "Edit";
        } if (req.getParameter("Delete") != null) {
            return "Delete";
        } if (req.getParameter("Save") != null && req.getParameter("employeeId") != null) {
            return "Save";
        }
        return "null";
    }
    
    private Employee getEmployeeFromPage(HttpServletRequest req) throws ParseException {
        Locale locale = new Locale("ru","RU");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", locale);
        Employee emp = new Employee();
        emp.setEmployeeId(Integer.parseInt(req.getParameter("employeeId")));
        emp.setFirstName(req.getParameter("firstName").trim());
        emp.setLastName(req.getParameter("lastName").trim());
        emp.setEmail(req.getParameter("email").trim());
        emp.setPhoneNumber(req.getParameter("phoneNumber").trim());
        emp.setHireDate(format.parse(req.getParameter("hireDate").trim()));
        emp.setJobId(req.getParameter("jobId").trim());
        emp.setSalary(Integer.parseInt(req.getParameter("salary")));
        emp.setCommissionPct(Integer.parseInt(req.getParameter("commissionPct")));
        emp.setManagerId(Integer.parseInt(req.getParameter("managerId")));
        emp.setDepartmentId(Integer.parseInt(req.getParameter("departmentId")));
        return emp;
    }
    
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req, res);
    }
 
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        processRequest(req, res);
    }
    
    
    /*public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        RequestDispatcher disp = getServletContext().getRequestDispatcher("/hello.jsp");
        disp.forward(req, res);
    }*/
    
    /*public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = res.getWriter();
        pw.println("<h1>Hello Tay</h1>");
        pw.println("<h2>It's test example of Oracle DB and Java connect</h2>");
        pw.println("<table border='1px'>");
        try {
            Collection<DfObject> dfObjects = ManagementSystem.getInstance().getAllStudents();
            for (DfObject obj : dfObjects) {
                pw.println("<tr>");
                pw.println("<td>" + obj.getObjectId() + "</td>");
                pw.println("<td>" + obj.getObjectDocNum() + "</td>");
                pw.println("<td>" + obj.getObjectDocDate() + "</td>");
                pw.println("<td>" + obj.getObjectDocDate() + "</td>");
                pw.println("<td>" + obj.getObjectDocType() + "</td>");
                pw.println("</tr>");
            }
        pw.println("</table>");
        } catch (Exception ex) {
            Logger.getLogger(HelloWorldServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}

