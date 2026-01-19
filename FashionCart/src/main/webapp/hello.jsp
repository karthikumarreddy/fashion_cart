<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
<script>
function loadProducts() {

    fetch('<%= request.getContextPath() %>/')
        .then(response => response.json())
        .then(products => {
        	console.log(products);
            let html = `
                <h3>Product List</h3>
                <table border="1" cellpadding="8" cellspacing="0">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Category</th>
                        <th>Price</th>
                        <th>Available</th>
                    </tr>
            `;

            products.forEach(p => {
            	console.log(p.available);
            	html +=
            	    "<tr>" +
            	        "<td>" + p.id + "</td>" +
            	        "<td>" + p.name + "</td>" +
            	        "<td>" + p.category + "</td>" +
            	        "<td>" + p.price + "</td>" +
            	        "<td>" + (p.available? "Yes" : "No") + "</td>" +
            	    "</tr>";
            });
			
            html += "</table>";
            

            document.getElementById("productSection").innerHTML = html;
        })
        .catch(error => {
            console.error("Error loading products:", error);
        }).finally(() => {
            // runs either promise is success or not
            console.log("Products fetch ocmpleted.");
          });
}
</script>
</html>