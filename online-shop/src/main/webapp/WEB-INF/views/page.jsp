<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<spring:url var="css" value="/resources/css" />
<spring:url var="vendor" value="/resources/vendor" />
<spring:url var="js" value="/resources/js" />
<spring:url var="images" value="/resources/images" />

<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Online Shop - ${title}</title>

<script>
window.menu = '${title}';

window.contextRoot = '${contextRoot}'
</script>

<!-- Bootstrap core CSS -->
<link href="${vendor}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Bootstrap readable theme CSS -->
<link href="${css}/bootstrap-letara.theme.css" rel="stylesheet">

<!-- Bootstrap DataTable theme CSS -->
<link href="${css}/dataTables.bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->

<link href="${css}/shop-homepage.css" rel="stylesheet">
<link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>
	<div class="wrapper">
	
		<!-- Navigation -->
		<%@include file="./shared/navbar.jsp"%>
		
		<div class="content">
		
			<!-- PAGE CONTENT -->
			
			<!-- Page Content Home Page -->
			<c:if test="${userClickHome==true }">
				<%@include file="home.jsp"%>
			</c:if>

			<!-- Page Content About page -->
			<c:if test="${userClickabout==true }">
				<%@include file="about.jsp"%>
			</c:if>

			<!-- Page Content clicks Contact page-->
			<c:if test="${userClickcontact==true }">
				<%@include file="contact.jsp"%>
			</c:if>

			<!-- Page Content clicks productlist page -->
			<c:if test="${userClickAllProducts==true or userClickCategoryProducts==true}">
				<%@include file="listProducts.jsp"%>
			</c:if>
			<!-- /.container -->
		</div>
		
		<!-- Footer -->
		<%@include file="./shared/footer.jsp"%>

		<!-- Bootstrap core JavaScript -->
		
		<script src="${vendor}/jquery/jquery.min.js"></script>
		<script src="${vendor}/bootstrap/js/bootstrap.bundle.min.js"></script>
		
		<!-- Datatable plugin -->
		<script src="${js}/jquery.dataTables.js"></script>
		
		<!-- Datatable Bootstrap Script 
		<script src="${js}/dataTables.bootstrap.js"></script>-->
				
		<!-- My own coded jquery -->
		<script src="${js}/myapp.js"></script>
	</div>
</body>

</html>
