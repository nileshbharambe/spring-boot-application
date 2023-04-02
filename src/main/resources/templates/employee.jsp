<!DOCTYPE html>
  <html xmlns="http://www.w3.org/1999/xhtml" 
    xmlns:th="http://www.thymeleaf.org" 
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    
<head>
<meta charset="utf-8"/>
<title>employee page</title>
</head>
<body>
<div align="center">
    <h1>Employee Records</h1>
    <a href="/">home</a>
    <br/><br/>
    <table border="1" cellpadding="10">
        <thead>
            <tr>
                <th>Employee ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Phone Number</th>
                <th>EmailId</th>
                <th>Address</th>
                 <th>Action</th>
            </tr>
        </thead>
        <tbody>
				<tr th:each="employee : ${listEmployees}">
					<td th:text="${employee.id}"></td>
					<td th:text="${employee.firstName}"></td>
					<td th:text="${employee.lastName}"></td>
					<td th:text="${employee.phoneNumber}"></td>
					<td th:text="${employee.emailId}"></td>
					<td th:text="${employee.address}"></td>
					<td> 
					<a th:href="@{/showFormForUpdate/{id}(id=${employee.id})}" class="btn btn-primary">Update</a> / 
					    <a th:href="@{/deleteEmployee/{id}(id=${employee.id})}" class="btn btn-danger">Delete</a>
					</td>
				</tr>
			</tbody>

    </table>
</div>   
</body>
</html>