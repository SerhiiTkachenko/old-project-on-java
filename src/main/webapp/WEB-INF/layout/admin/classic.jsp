<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title></title>

<!-- Bootstrap -->
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link href="/resources/css/sidebar.css" rel="stylesheet">
<link href="/resources/css/sidebar-bootstrap.css" rel="stylesheet">
<link href="/resources/css/style.css" rel="stylesheet">

<script src="/resources/js/jquery-3.1.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resources/css/chosen.min.css">
<script src="/resources/js/chosen.jquery.min.js"></script>
<style>
@media ( min-width : 1000px) {
	.navbar .navbar-nav {
		display: inline-block;
		float: none;
		vertical-align: top;
	}
	.navbar .navbar-collapse {
		text-align: center;
	}
}

@media ( max-width :1000px) {
	.nav>li {
		float: none;
		position: relative;
		display: block;
	}
	.navbar-collapse.collapse {
		display: none !important;
	}
	.navbar-collapse {
		overflow-x: visible !important;
	}
	.navbar-collapse.in {
		overflow-y: auto !important;
	}
	.collapse.in {
		display: block !important;
	}
	.navbar-toggle {
		display: block;
	}
}
</style>
<script>
	$(function() {
		$('select').chosen();
	});
</script>
<title><tiles:getAsString name="title" /></title>
</head>
<body>
	<tiles:insertAttribute name="header" />
	<tiles:insertAttribute name="subHeader" />
	<div class="container-fluid">
		<tiles:insertAttribute name="body" />
	</div>
	<tiles:insertAttribute name="footer" />
</body>
</html>