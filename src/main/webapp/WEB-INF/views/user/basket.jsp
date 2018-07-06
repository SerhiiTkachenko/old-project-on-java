<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="col-sm-12 text-center">
		<h2>Корзина</h2>
	</div>
	<div class="col-sm-12">
		<br>
	</div>
	<div class="row">
		<div class="col-md-2 col-xs-2">Назва товару</div>
		<div class="col-md-2 col-xs-2">Ціна</div>
		<div class="col-md-2 col-xs-2">Кількість</div>
		
	</div>
	<div class="col-sm-12">
		<br>
	</div>
	<div class="row">
		<div class="col-sm-12 col-xs-12 parent">
			<c:forEach items="${orderss}" var="orders">
					<div class="row">
						<div class="col-md-2 col-xs-2">${orders.product.productName}</div>
						<div class="col-md-2 col-xs-2">${orders.product.price}</div>
						<div class="col-md-2 col-xs-2">${orders.amount}</div>
						<div class="col-md-1 col-xs-1">
							<a class="btn btn-primary"
								href="/basket/plus/${orders.id}">+</a>
						</div>
						<div class="col-md-1 col-xs-1">
							<a class="btn btn-primary"
								href="/basket/minus/${orders.id}">-</a>
						</div>
						<div class="col-md-1"></div>
						<div class="col-md-1 col-xs-1">
							<a class="btn btn-primary"
								href="/basket/delete/${orders.id}/">delete</a>
						</div>




					</div>
					
			</c:forEach>
			<div class="col-md-1 col-xs-1">
				${sum}
			</div>
			
			<div class="col-md-1 col-xs-1">
				<a href="/basket/accept"><button type="submit"
						class="btn btn-primary">Замовити</button></a>
			</div>
		</div>
	</div>
	</div>