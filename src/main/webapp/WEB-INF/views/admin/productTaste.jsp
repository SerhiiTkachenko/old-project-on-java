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
					<li class="active"><a href="/admin/productTaste<custom:allParams/>">productTaste</a></li>
					<li><a href="/admin/city">city</a></li>
					<li><a href="/admin/street">street</a></li>
					<li><a href="/admin/product">product</a></li>
					<li><a href="/admin/orders">orders</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		<form:form class="form-inline" action="/admin/productTaste" method="GET" modelAttribute="filter">
 		<custom:hiddenInputs excludeParams="search"/>
 		<div class="form-group">
 			<form:input class="form-control" path="search" placeholder="Search"/>
 		</div>
 		<button type="submit" class="btn btn-primary">Ok</button>
 	</form:form>
	</div>
	
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/productTaste" method="POST" modelAttribute="productTaste">
				<custom:hiddenInputs excludeParams="taste"/>
				<div class="form-group">
						<label for="taste" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="taste"/></label>
					</div>
					<div class="form-group">
    					<label for="taste" class="col-sm-2 control-label">taste</label>
    					<div class="col-sm-10">
      						<form:input class="form-control" path="taste" id="taste"/>
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
			<div class="col-md-4 col-xs-4"><h3>productTaste</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Update</h3></div>
			<div class="col-md-4 col-xs-4"><h3>Delete</h3></div>
		</div>
			<c:forEach items="${page.content}" var="productTaste">
				<div class="row">
					<div class="col-md-4 col-xs-4">${productTaste.taste}</div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-warning" href="/admin/productTaste/update/${productTaste.id}<custom:allParams/>">update</a></div>
					<div class="col-md-4 col-xs-4"><a class="btn btn-danger" href="/admin/productTaste/delete/${productTaste.id}<custom:allParams/>">delete</a></div>
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
								<custom:sort innerHtml="Taste asc" paramValue="taste" />
								<custom:sort innerHtml="Taste desc" paramValue="taste,desc" />
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