<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<header id="header">
	<div class="container">
		<div class="row">
			<div class="h-panel clearfix">
				<nav class="h-nav h-nav-tabs">
					<a href="#" class="active">Інтернет-магазин</a> <a href="#">Про
						нас</a>
				</nav>
				<nav class="h-nav h-nav-center">
					<a href="#">Акції</a> <a href="#">Дисконтна система</a> <a href="#">Адреса
						і контакти</a> <a href="#">Доставка і оплата</a>
				</nav>
				<nav class="h-nav pull-right">
					<sec:authorize access="isAuthenticated()">
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="/admin" class="ic-time">Admin</a>
						</sec:authorize>
						<form:form action="/logout" method="POST">
							<a href="/basket" class="ic-time">Basket</a>
							<button type="submit" class="btn btn-success">Logout</button>
						</form:form>
					</sec:authorize>
					<sec:authorize access="!isAuthenticated()">
						<a href="/login"><i class="ic-time"></i> <span
							class="hidden-xss">Вхід</span></a>
						<a href="/registration"><i class="ic-user"></i> <span
							class="hidden-xss">Реєстрація</span></a>
					</sec:authorize>
				</nav>
			</div>
		</div>
	</div>
	<div class="header-bottom">
		<div class="container">
			<div class="row">
				<div class="col-lg-3 col-md-4 col-sm-3 col-xs-4 col-xss-6">
					<div class="logo">
						<a href="/"><img src="/resources/img/logo1.png" height="59"
							width="238" alt=""></a>
					</div>
				</div>
				<div class="col-lg-6 col-md-5 col-sm-5 col-xs-6 col-xss-3">
					<form action="#" id="h-search">
						<input type="text" value="Пошук по сайту" /> 
						<input type="submit"
							value="" /> <a href="#" class="h-search_close">x</a>
					</form>
					<a href="#" class="h-search__link">&nbsp;</a>
				</div>
				<div class="col-lg-3 col-md-3 col-sm-4 col-xs-2 col-xss-3">
					<div class="dropdown h-phone">
						<a data-toggle="dropdown" href="#"> <small>Бзкоштовно
								по всій Україні</small> <span>8 800 123-45-67 <i
								class="ic-arrow-down"></i></span>
						</a>
						<ul class="dropdown-menu arrow" role="menu"
							aria-labelledby="dLabel">
							<li><a href="#">Замовити зворотній звінок</a></li>
							<li><a href="#">Форма зворотнього зв'язку</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="mainMenu">
		<nav class="navbar navbar-default navbar-static">
			<div class="container">
				<div class="row">
					<div class="container-fluid">
						<div class="navbar-header">
							<button class="navbar-toggle collapsed" type="button"
								data-toggle="collapse" data-target=".js-navbar">
								<span class="sr-only">Toggle navigation</span> <span
									class="icon-bar"></span> <span class="icon-bar"></span> <span
									class="icon-bar"></span>
							</button>
						</div>
						<div class="collapse navbar-collapse js-navbar navigationMenu">
							<ul class="nav navbar-nav">
								<li><a href="/">Головна </a></li>
								<li><a href="/proteins"> Товар </a></li>
								<li class="dropdown"><a id="drop1" href="#"
									class="dropdown-toggle" data-toggle="dropdown"> Гейнери
										 </a>
									<ul class="dropdown-menu">
										<li><a href="/category/3">Сивороточні</a></li>
										<li><a href="#">Вуглеводні</a></li>
									</ul></li>
								<li class="dropdown"><a id="drop1" href="#"
									class="dropdown-toggle" data-toggle="dropdown"> Креатин </a>
									<ul class="dropdown-menu">
										<li><a href="#">Моногідрат</a></li>
										<li><a href="#">В капсулах</a></li>
									</ul></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
	</div>
</header>
