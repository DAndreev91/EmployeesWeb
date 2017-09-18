<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        asd
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

        <title>
            ДЕПАРТАМЕНТЫ
        </title>
    </head>
 
    <body>
		<div class="container-fluid">
			<h1>Перечень департаментов</h1>
			<div class="col-md-6 col-md-offset-1">
				<form action=" <c:url value="/main" /> " method="POST">
					<div class="panel-group" id="accordion">
						<c:forEach var="department" items="${depsForm.departments}">
							
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion" href="#${department.departmentId}">
												<c:out value="${department.departmentName}" />
											</a>
											<button type="submit" class="btn btn-default" name="Add" value="Add"><span class="glyphicon glyphicon-plus"></span></button>
										</h4>
									</div>
									<c:choose>
										<c:when test="${depsForm.departmentId==department.departmentId}">
											<div id="${department.departmentId}" class="panel-collapse collapse in">
										</c:when>
										<c:otherwise>
											<div id="${department.departmentId}" class="panel-collapse collapse">
										</c:otherwise>
									</c:choose>
										<div class="panel-body">
											<table class='table table-hover table-bordered'>
												<c:forEach var="employee" items="${depsForm.employees}">
													<c:choose>
														<c:when test="${employee.departmentId==department.departmentId}">
															<tr>
																<td>
																	<c:out value="${employee.firstName} ${employee.lastName} ${employee.departmentId}" />
                                                                                                                                        <form role="form" method="post">
                                                                                                                                            <input name="employeeId" value="${employee.employeeId}" type="hidden"/>
                                                                                                                                            <button type="submit" class="btn btn-default" name="Edit" value="Edit"><span class="glyphicon glyphicon-pencil"></span></button>
                                                                                                                                            <button type="submit" class="btn btn-default" name="Delete" value="Delete"><span class="glyphicon glyphicon-remove"></span></button>
                                                                                                                                        </form>
																</td>
															</tr>
														</c:when>
													</c:choose>
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							
						</c:forEach>
					</div>
					
						<button type="submit" class="btn btn-default" name="getList" value="Обновить">Обновить</button>
				</form>
			</div>
		</div>
    </body>
</html>