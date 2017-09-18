<%-- 
    Document   : EmployeePage
    Created on : 15.09.2017, 17:04:53
    Author     : andreevds91
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <script
			  src="https://code.jquery.com/jquery-3.2.1.min.js"
			  integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
			  crossorigin="anonymous"></script>
            <!-- Latest compiled and minified CSS -->
            <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

            <!-- Optional theme -->
            <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

            <!-- Latest compiled and minified JavaScript -->
            <script src="//netdna.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
            <title>Employee Page</title>
        </head>
        <body>
            <div class="container-fluid">
                <h2><c:out value="Insert/Edit employee"/></h2>
                <form class="col-md-6 col-md-offset-1" action="<c:url value="/main"/>" method="post" role="form">
                    <input name="employeeId" value="${empForm.employee.employeeId}" type="hidden"/>
                    <div class="form-group">
                        <label for="firstName">First name</label>
                        <input type="text" class="form-control" id="firstName" name="firstName" value="${empForm.employee.firstName}" placeholder="Enter first name">
                    </div>
                    <div class="form-group">
                        <label for="lastName">Last name</label>
                        <input type="text" class="form-control" id="lastName" name="lastName" value="${empForm.employee.lastName}" placeholder="Enter last name">
                    </div>
                    <div class="form-group">
                        <label for="email">First name</label>
                        <input type="email" class="form-control" id="email" name="email" value="${empForm.employee.email}" placeholder="Enter email">
                    </div>
                    <div class="form-group">
                        <label for="phoneNumber">Phone number</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${empForm.employee.phoneNumber}" placeholder="Enter phone number">
                    </div>
                    <div class="form-group">
                        <label for="hireDate">Hire date</label>
                        <input type="date" class="form-control" id="hireDate" name="hireDate" value="${empForm.employee.hireDate}" placeholder="Enter hire date">
                    </div>
                    <div class="form-group">
                        <label for="jobId">Job id</label>
                        <input type="text" class="form-control" id="jobId" name="jobId" value="${empForm.employee.jobId}" placeholder="Enter job id">
                    </div>
                    <div class="form-group">
                        <label for="salary">Salary</label>
                        <input type="number" class="form-control" id="salary" name="salary" value="${empForm.employee.salary}" placeholder="Enter salary">
                    </div>
                    <div class="form-group">
                        <label for="commissionPct">Commission pct</label>
                        <input type="number" class="form-control" id="commissionPct" name="commissionPct" value="${empForm.employee.commissionPct}" placeholder="Enter commission pct">
                    </div>
                    <div class="form-group">
                        <label for="managerId">Manager id</label>
                        <input type="number" class="form-control" id="managerId" name="managerId" value="${empForm.employee.managerId}" placeholder="Enter manager id">
                    </div>
                    <div class="form-group">
                        <label for="departmentId">Department id</label>
                        <select class="form-control" name="departmentId">
                            <c:forEach var="department" items="${empForm.departments}">
                                <c:choose>
                                    <c:when test="${department.departmentId==empForm.employee.departmentId}">
                                        <option value="${department.departmentId}" selected><c:out value="${department.departmentName}" /></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${department.departmentId}"><c:out value="${department.departmentName}" /></option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                    <input class="col-md-2 col-md-offset-1" type="submit" value="Save" name="Save"/></td>
                    <input class="col-md-2 col-md-offset-4" type="submit" value="Cancel" name="Cancel"/></td>
                </form>
            </div>
        </body>
    </html>
