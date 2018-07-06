<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<div class="row">
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li><a href="/admin/productProducer">productProducer</a></li>
					<li><a href="/admin/productTaste">productTaste</a></li>
					<li><a href="/admin/city">city</a></li>
					<li class="active"><a href="/admin/street<custom:allParams/>">street</a></li>
					<li><a href="/admin/product">product</a></li>
					<li><a href="/admin/orders">orders</a></li>
					<li><a href="/admin/order_Details">order_Details</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		
		<form:form class="form-horizontal" action="/admin/street" method="GET" modelAttribute="filter">
			
			<div class="form-group">
				<label class="control-label col-sm-12">city</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="cityId" items="${citys}" itemValue="id" itemLabel="nameOfCity"/>
				</div>
			</div>
			
			<div class="form-group">
				<form:input class="form-control" path="search" placeholder="search"/>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
			
	</form:form>
	
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/street" method="POST" modelAttribute="street">
				<custom:hiddenInputs excludeParams="city,street"/>
					<div class="form-group">
    					<label for="city" class="col-sm-2 control-label">City</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="city" id="city" items="${citys}" itemValue="id" itemLabel="nameOfCity"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="nameOfStreet" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="nameOfStreet"/></label>
					</div>
					<div class="form-group">
    					<label for="nameOfStreet" class="col-sm-2 control-label">street</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="nameOfStreet" id="nameOfStreet"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<div class="col-sm-offset-2 col-sm-10">
      						<button type="submit" class="btn btn-default">Create</button>
    					</div>
  					</div>
				</form:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-4 col-xs-4"><h3>City</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Street</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Options</h3></div>
		</div>
			<c:forEach items="${page.content}" var="street">
				<div class="row">
					<div class="col-md-3 col-xs-3">${street.city.nameOfCity}</div>
					<div class="col-md-3 col-xs-3">${street.nameOfStreet}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-warning" href="/admin/street/update/${street.id}<custom:allParams/>">update</a>
					<a class="btn btn-danger" href="/admin/street/delete/${street.id}<custom:allParams/>">delete</a></div>
				</div>
			</c:forEach>
	</div>
	<div class="col-md-2 col-xs-12">
 		<div class="row">
 					<div class="col-md-6 col-xs-6 text-center">
 						<div class="dropdown">
 							<button class="btn btn-primary dropdown-toggle" type="button"
 								data-toggle="dropdown">
 								Sort <span class="caret"></span>
 							</button>
 							<ul class="dropdown-menu">
 								<custom:sort innerHtml="city asc" paramValue="city.nameOfCity" />
 								<custom:sort innerHtml="city desc" paramValue="city.nameOfCity,desc" />
 								<custom:sort innerHtml="street asc" paramValue="nameOfStreet" />
 								<custom:sort innerHtml="street desc" paramValue="nameOfStreet,desc" />
 							</ul>
 						</div>
 					</div>
					<div class="col-md-6 col-xs-6 text-center">
 						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
 					</div>
 				</div>
 	</div>
</div>
<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>