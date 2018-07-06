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
					<li class="active"><a href="/admin/product<custom:allParams/>">Product</a></li>
					<li><a href="/admin/orders">orders</a></li>
					<li><a href="/admin/country">country</a></li>
					<li><a href="/admin/productCategory">productCategory</a></li>
				</ul>
			</div>
		</div>
	</nav>
</div>
<div class="row">
	<div class="col-md-3 col-xs-12">
		
		<form:form class="form-horizontal filter" action="/admin/product" method="GET" modelAttribute="filter">
			<div class="form-group">
				
				<label class="control-label col-sm-12">Weight</label>
				<div class="col-sm-6">
					<form:input path="minWeight" class="form-control" placeholder="minWeight"/>
				</div>
				<div class="col-sm-6">
					<form:input path="maxWeight" class="form-control" placeholder="maxWeight"/>
				</div>
			
			<label class="control-label col-sm-12">Price</label>
				<div class="col-sm-6">
					<form:input path="min" class="form-control" placeholder="Min"/>
				</div>
				<div class="col-sm-6">
					<form:input path="max" class="form-control" placeholder="Max"/>
				</div>
				
				<label class="control-label col-sm-12">productName</label>
				<div class="col-sm-6">
					<form:input path="search" class="form-control" placeholder="search"/>
				</div>
				
				<label class="control-label col-sm-12">productDescription</label>
				<div class="col-sm-6">
					<form:input path="searchDescription" class="form-control" placeholder="searchDescription"/>
				</div>
				
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12">productProducer</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productProducerId" items="${productProducers}" itemValue="id" itemLabel="name"/>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-12"> productTastes</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productTasteId" items="${productTastes}" itemValue="id" itemLabel="taste"/>
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-12"> productCategory</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productCategoryId" items="${productCategorys}" itemValue="id" itemLabel="category"/>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Ok</button>
			
		</form:form>
				
	</div>
	<div class="col-md-7 col-xs-12">
		<div class="row">
			<div class="col-md-12 col-xs-12">
			<form:form class="form-horizontal" action="/admin/product" method="POST" modelAttribute="product" enctype="multipart/form-data">
				<custom:hiddenInputs excludeParams="productProducer, product, productTaste,productCategory"/>
				<div class="form-group">
						<label for="productProducer" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="productProducer"/></label>
					</div>
					<div class="form-group">
    					<label for="productProducer" class="col-sm-2 control-label">productProducer</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="productProducer" id="productProducer" items="${productProducers}" itemValue="id" itemLabel="name"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="productName" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="productName"/></label>
					</div>
					<div class="form-group">
    					<label for="productName" class="col-sm-2 control-label">ProductName</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="productName" id="productName"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="productDescription" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="productDescription"/></label>
					</div>
  					<div class="form-group">
    					<label for="productDescription" class="col-sm-2 control-label">productDescription</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="productDescription" id="productDescription"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="weight" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="weight"/></label>
					</div>
  					<div class="form-group">
    					<label for="weight" class="col-sm-2 control-label">ProductWeight</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="weight" id="weight"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="price" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="price"/></label>
					</div>
  					<div class="form-group">
    					<label for="price" class="col-sm-2 control-label">price</label>
    					<div class="col-sm-10">
      						<form:input type="text" class="form-control" path="price" id="price"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="productTaste" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="productTaste"/></label>
					</div>
  					<div class="form-group">
    					<label for="productTaste" class="col-sm-2 control-label">productTaste</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="productTaste" id="productTaste" items="${productTastes}" itemValue="id" itemLabel="taste"/>
    					</div>
  					</div>
  					<div class="form-group">
						<label for="productCategory" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="productCategory"/></label>
					</div>
  					<div class="form-group">
    					<label for="productCategory" class="col-sm-2 control-label">productCategory</label>
    					<div class="col-sm-10">
      						<form:select class="form-control" path="productCategory" id="productCategory" items="${productCategorys}" itemValue="id" itemLabel="category"/>
    					</div>
  					</div>
  					
  					<div class="form-group">
    					<label for="file" class="col-sm-2 control-label">Image</label>
    					<div class="col-sm-10">
      						<input name="file" id="file" type="file">
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
			<div class="col-md-2 col-xs-2"><h3>Image</h3></div>
			<div class="col-md-12 col-xs-12"><h3>Product</h3></div>
			<div class="col-md-12 col-xs-12"><h3>ProductProducer</h3></div>
			<div class="col-md-12 col-xs-12"><h3>productTaste</h3></div>
			<div class="col-md-12 col-xs-12"><h3>Category</h3></div>
		</div>
		<div class="row">	
			<div class="col-md-12 col-xs-12"><h3>desc</h3></div>		
			<div class="col-md-12 col-xs-12"><h3>weight</h3></div>
			<div class="col-md-12 col-xs-12"><h3>price</h3></div>
			<div class="col-md-12 col-xs-12"><h3>Options</h3></div>
		</div>
			<c:forEach items="${page.content}" var="product">
				<div class="row">
					<div class="col-md-12 col-xs-12">${product.productName}</div>
					<div class="col-md-12 col-xs-12">${product.productProducer.name}</div>
					<div class="col-md-12 col-xs-12">${product.productTaste.taste}</div>
					<div class="col-md-12 col-xs-12">${product.productCategory.category}</div>
					<div class="col-md-2 col-xs-2"><img src="/images/product/${product.id}.jpg?version=${product.version}" width="100%"></div>
				</div>	
				<div class="row">	
					<div class="col-md-12 col-xs-12">${product.productDescription}</div>
					<div class="col-md-12 col-xs-12">${product.weight}</div>
					<div class="col-md-12 col-xs-12">${product.price}</div>
					<div class="col-md-12 col-xs-12"><a class="btn btn-warning" href="/admin/product/update/${product.id}<custom:allParams/>">update</a>
					<a class="btn btn-danger" href="/admin/product/delete/${product.id}<custom:allParams/>">delete</a></div>
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
								<custom:sort innerHtml="Product asc" paramValue="productName" />
								<custom:sort innerHtml="Product desc" paramValue="productName,desc" />
								<custom:sort innerHtml="productProducer name asc" paramValue="productProducer.name" />
								<custom:sort innerHtml="productProducer name desc" paramValue="productProducer.name,desc" />
								<custom:sort innerHtml="productTaste taste asc" paramValue="productTaste.taste" />
								<custom:sort innerHtml="productTaste taste desc" paramValue="productTaste.taste,desc" />
								<custom:sort innerHtml="productCategory category asc" paramValue="productCategory.category" />
								<custom:sort innerHtml="productCategory category desc" paramValue="productCategory.category,desc" />
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