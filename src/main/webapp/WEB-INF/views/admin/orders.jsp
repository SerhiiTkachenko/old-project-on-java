<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<style>
	.filter .control-label{
		text-align: left;
	}
</style>
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
					<li><a href="/admin/street">street</a></li>
					<li><a href="/admin/product">product</a></li>
					<li><a href="/admin/country">country</a></li>
					<li class="active"><a href="/admin/orders<custom:allParams/>">orders</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
	
		<form:form class="form-horizontal filter" action="/admin/orders" method="GET" modelAttribute="filter">
			<div class="form-group">
				
			
			<label class="control-label col-sm-12">Amount</label>
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
				
				
				
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">product</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productId" items="${products}" itemValue="id" itemLabel="productName"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12"> user</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="usersId" items="${userss}" itemValue="id" itemLabel="name"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
		</form:form>
	
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
				<form:form class="form-horizontal" action="/admin/orders" method="POST" modelAttribute="orders">
				<custom:hiddenInputs excludeParams="orders,product,users"/>
					<div class="form-group">
    					<label for="product" class="col-sm-2 control-label">productName</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="product" id="product" items="${products}" itemValue="id" itemLabel="productName"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="amount" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="amount"/></label>
					</div>
					<div class="form-group">
    					<label for="amount" class="col-sm-2 control-label">OrdersAmount</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="amount" id="amount"/>
    					</div>
  					</div>
  					<div class="form-group">
    					<label for="users" class="col-sm-2 control-label">users</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="users" id="users" items="${userss}" itemValue="id" itemLabel="name"/>
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
			<div class="col-md-3 col-xs-3"><h3>Product</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Orders</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Users</h3></div>
			<div class="col-md-3 col-xs-3"><h3>Options</h3></div>
		</div>
			<c:forEach items="${page.content}" var="orders">
				<div class="row">
					<div class="col-md-3 col-xs-3">${orders.product.productName}</div>
					<div class="col-md-3 col-xs-3">${orders.amount}</div>
					<div class="col-md-3 col-xs-3">${orders.users.name}</div>
					<div class="col-md-3 col-xs-3"><a class="btn btn-warning" href="/admin/orders/update/${orders.id}<custom:allParams/>">update</a>
					<a class="btn btn-danger" href="/admin/orders/delete/${orders.id}<custom:allParams/>">delete</a></div>
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
								<custom:sort innerHtml="Product asc" paramValue="product.productName" />
								<custom:sort innerHtml="Product desc" paramValue="product.productName,desc" />
								<custom:sort innerHtml="Orders amount asc" paramValue="amount" />
								<custom:sort innerHtml="Orders amount desc" paramValue="amount" />
								<custom:sort innerHtml="users asc" paramValue="users.name" />
								<custom:sort innerHtml="users desc" paramValue="users.name,desc" />
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
<script>
	$('label').each(function() {
		if(!$(this).html()) $(this).parent('div').hide();
	});
</script>