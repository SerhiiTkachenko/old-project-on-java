

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/custom.tld" prefix="custom"%>




			<div class="container">
				    
				 <div class="col-md-12 text-center">
				    <h2>${category.category}</h2>
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
										<span class="cat">Виробник: ${product.productProducer.name}</span>
										<span class="cat">Смак :${product.productTaste.taste}</span>
										<span class="cat">Опис: ${product.productDescription}</span><br>
										<span class="cat">Категорія:${product.productCategory.category}</span>	
										<span  class="cat">Вага (кг):${product.weight}</span>
									</div>
									<span class="price">Ціна (грн): ${product.price}</span>
									<span class="col-sm-12 text-right"><a class="btn btn-danger" href="/proteins/adds/${product.id}<custom:allParams/>">В корзину</a></span>
								</div>
							
							</c:forEach>
						</div>
						<div class="col-md-12 col-sm-2">
						</div>
					</div>
				</div>	
			</div>
	</div>
				<c:if test="${empty page.content}">
					<h3>У вас немає товару за категорією</h3>
				</c:if>
				
			</div>
			<div class="row">
	<div class="col-md-12 col-xs-12 text-center">
		<custom:pageable page="${page}" cell="<li></li>" container="<ul class='pagination'></ul>" />
	</div>
</div>