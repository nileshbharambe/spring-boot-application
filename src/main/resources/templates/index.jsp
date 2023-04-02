<!DOCTYPE html>
  <html xmlns="http://www.w3.org/1999/xhtml" 
    xmlns:th="http://www.thymeleaf.org" 
    xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
    xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
    
<head>
<meta charset="utf-8"/>
<title>index page</title>
</head>
<body>
<div align="center">
    <h1>Hello</h1>
    <p>Welcome to <span th:text="${appName}">Our App</span></p>
</div>  
</body>
</html>