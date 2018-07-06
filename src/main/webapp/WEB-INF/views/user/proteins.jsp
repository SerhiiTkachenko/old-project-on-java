<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>
<link href="/resources/css/style.css" rel="stylesheet">

    
    <div class="container">
    	
    	<div class="row">
		
     <div class="col-md-5 col-xs-12">
	
	<form:form class="form-horizontal filter" action="/proteins" method="GET" modelAttribute="filter">
		<div class="sidebar-title">
			<div class="form-group">
				<label class="col-sm-12 text-center">Search</label>
				 <div class="col-sm-12">
					<form:input path="search"  class="form-control" placeholder="Пошук продуктів"/>
				</div>  
				
					<label class="col-sm-12 text-center">Weight</label>
					<div class="col-sm-6">
						<form:input path="minWeight" class="form-control" placeholder="minWeight"/>
					</div>
					<div class="col-sm-6">
						<form:input path="maxWeight" class="form-control" placeholder="maxWeight"/>
					</div>
				
					<label class=" col-sm-12 text-center">Price</label>
					<div class="col-sm-6">
						<form:input path="min" class="form-control" placeholder="Min"/>
					</div>
					<div class="col-sm-6">
						<form:input path="max" class="form-control" placeholder="Max"/>
					</div>
				</div>
				
								
			 <div class="form-group">
				<label class=" col-sm-12 text-left">productProducer</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productProducerId" items="${productProducers}" itemValue="id" itemLabel="name"/>
				</div>
			</div>
			<div class="form-group">
				<label class=" col-sm-12 text-left"> productTastes</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productTasteId" items="${productTastes}" itemValue="id" itemLabel="taste"/>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class=" col-sm-12 text-left"> productCategory</label>
				<div class="col-sm-12">
					<form:checkboxes element="div" path="productCategoryId" items="${productCategorys}" itemValue="id" itemLabel="category"/>
				</div>
			</div> 
			<button type="submit" class="btn btn-warning">Ok</button>
			
			
			
		</div>
			
		</form:form>
	
	</div> 
    
    
    </div>
    
		
	<div class="col-md-12">
		<div class="row">
					<div class="col-md-10 col-xs-4 text-right">
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
					<div class="col-md-1 col-xs-1 text-center">
						<custom:size posibleSizes="1,2,5,10" size="${page.size}" />
					</div>
			</div>
		</div>		
    </div>
    
    <div class="col-md-12 text-center">
    <h2>Всі товари</h2>
    </div>
    <div id="wraper">
    		<div class="container">
				<div class="row">
					<div class="col-md-12 content">
						<div class="row product-wrap">
						<c:forEach items="${page.content}" var="product">
								<div class="col-md-4 col-sm-6 product">
									<div class="product-img">
									<img src="/images/product/${product.id}.jpg?version=${product.version}" height="218"
											width="218">
									</div>
									<div class="product-footer">
										<h5>
											<a  href="#">Назва: ${product.productName}</a>
										</h5>
										<span class="cat"><u>Виробник:</u> ${product.productProducer.name}</span>
										<span class="cat"><u>Смак</u> :${product.productTaste.taste}</span>
										<span class="cat"><u>Опис:</u> ${product.productDescription}</span><br>
										<span class="cat"><u>Категорія</u>:${product.productCategory.category}</span>	
										<span  class="cat"><u>Вага (кг)</u>:${product.weight}</span>
									</div>
									<span class="price">Ціна (грн): ${product.price}</span>
									<span class="col-sm-12 text-right"><a class="btn btn-danger" href="/proteins/add/${product.id}<custom:allParams/>">В корзину</a></span>
								</div>
							
							</c:forEach>
						</div>
						<div class="col-md-12 col-sm-2">
						</div>
					</div>
				</div>	
			</div>
	</div>
    
    
    




<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>