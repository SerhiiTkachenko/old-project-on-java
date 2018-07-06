<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
	<div class="container">
	<p>ХАЮ ХАЙ</p>
	<c:forEach items="${userss}" var="users">
				<div class="row">
				<div class="col-md-2 col-xs-2">${users.name}</div>
				<div class="col-md-2 col-xs-2">${users.surname}</div>
				<div class="col-md-2 col-xs-2">${users.email}</div>
				<div class="col-md-2 col-xs-2">${users.mob}</div>
				<div class="col-md-1 col-xs-1">${users.country.name}</div>
				<div class="col-md-1 col-xs-1">${users.street.nameOfStreet}</div>
				<div class="col-md-1 col-xs-1">${users.city.nameOfCity}</div>
				</div>
			</c:forEach>
	</div>
		