<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employees</title>
</head>
<body>
<h1>Employee List</h1>
<table>
    <tr>
        <th>PIN</th>
        <th>Name</th>
        <th>Email</th>
    </tr>
    <c:forEach items="${employeeList}" var="employee">
        <tr>
            <td>${employee.pin}</td>
            <td>${employee.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>