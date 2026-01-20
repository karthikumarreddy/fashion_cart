<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%= request.getContextPath() %>/controller?command=listProducts&category=ALL">
    View All Products
</a>

<a href="<%= request.getContextPath() %>/controller?command=listProducts&category=MEN">
    MEN
</a>

<a href="<%= request.getContextPath() %>/controller?command=listProducts&category=WOMEN">
    WOMEN
</a>

</body>
</html>