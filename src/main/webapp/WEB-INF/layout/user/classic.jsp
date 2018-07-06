<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

 <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>ZXC</title>
    <link rel="shortcut icon" href="resources/img/favicon.ico" type="image/x-icon">

    <!-- Bootstrap -->
    <link rel="stylesheet" href="/resources/css/bootstrap.min.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
    <link href="/resources/css/sidebar.css" rel="stylesheet">
    <link href="/resources/css/sidebar-bootstrap.css" rel="stylesheet">
    <link href="/resources/css/style.css" rel="stylesheet">
	

<script src="/resources/js/jquery-3.1.1.min.js"></script>
<script src="/resources/js/bootstrap.min.js" ></script>
<link rel="stylesheet" href="/resources/css/chosen.min.css">
<script src="/resources/js/chosen.jquery.min.js" ></script>
<style type="text/css">

section#main {
    background: url(resources/img/main_bg_1920.jpg) no-repeat center center;
    background-size: cover;
    
    .main_header{
       
    }
    h3 {
        text-transform: uppercase;
        .bold;
        span {
            color: #ff9933;
        }
    }
    .main_buttons {
    	font: 14px/20px  'Open Sans', Arial, Tahoma,  sans-serif;
        margin-top: 7px;
        margin-bottom: 100px;
        
        button#get_types {
            border: none;
            background-color: transparent;
            padding: 15px 33px;
            color: #ff9933;
            text-transform: uppercase;
            font-size: 20px;
            border: 2px solid #ff9933;
            margin-left: 20px;
            &:hover {
                background-color: rgba(255, 255, 255, 0.8);
            }
        }
    }
}

.tovar{
	float :left;
	padding: 30px;
}

</style>
<!-- сюди буде підставлено атрибут з ім'ям title -->
<title><tiles:getAsString name="title" /></title>
</head>
<body>
<!-- 	а сюди jsp файл з ім'ям header -->
    <tiles:insertAttribute name="header" />
	<div class="container-fluid">
<!-- 	сюди jsp файл з ім'ям body -->
		<tiles:insertAttribute name="body" />
	</div>
<!-- 	сюди jsp файл з ім'ям footer -->
		<tiles:insertAttribute name="footer" />
<!-- 	зверніть увагу на те що в темплейт файлі (тут) -->
<!-- 	вже описано основний HTML код, в інших файлах -->
<!-- 	його писати не потрібно(я про <head><body><html>) -->
</body>
</html>