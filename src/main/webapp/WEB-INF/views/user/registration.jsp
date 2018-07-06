<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="row">
	<div class="col-sm-12 col-xs-12">
		<form:form class="form-horizontal" action="/registration" method="POST" modelAttribute="registration">
		
	
		<div class="form-group">
				<label for="country" class="col-sm-offset-2 col-sm-10"><form:errors path="country"/></label>
			</div>
	
		<div class="form-group">
				<label for="country" class="col-sm-2 control-label">Країна</label>
				<div class="col-sm-10">
					<form:input class="form-control" path="country" id="country"/>
				</div>
			</div>
	
	
		<div class="form-group">
				<label for="nameOfCity" class="col-sm-offset-2 col-sm-10"><form:errors path="nameOfCity"/></label>
			</div>
			
			
			 <div class="form-group">
    			<label for="nameOfCity" class="col-sm-2 control-label">Місто:</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="nameOfCity" id="nameOfCity"/>
    			</div>
  			</div>	
	
				<div class="form-group">
				<label for="nameOfStreet" class="col-sm-offset-2 col-sm-10"><form:errors path="nameOfStreet"/></label>
			</div>
			
  			<div class="form-group">
    			<label for="nameOfStreet" class="col-sm-2 control-label">Вулиця:</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="nameOfStreet" id="nameOfStreet"/>
    			</div>
  			</div>
		
			<div class="form-group">
						<label for="name" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="name"/></label>
					</div>	
			<div class="form-group">
    			<label for="name" class="col-sm-2 control-label">name</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="name" id="name"/>
    			</div>
  			</div>
  			
  			<div class="form-group">
						<label for="surname" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="surname"/></label>
					</div>	
  			
  			<div class="form-group">
    			<label for="surname" class="col-sm-2 control-label">surname</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="surname" id="surname"/>
    			</div>
  			</div>
  			
  			
  			<div class="form-group">
				<label for="mob" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10"><form:errors path="mob"/></label>
			</div>			
  			
  			<div class="form-group">
    			<label for="mob" class="col-sm-2 control-label">Мобільний</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="mob" id="mob"/>
    			</div>
  			</div>
				
			
			<div class="form-group">
						<label for="email" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="email"/></label>
			</div>
			
			<div class="form-group">
    			<label for="email" class="col-sm-2 control-label">Email</label>
    			<div class="col-sm-10">
      				<form:input class="form-control" path="email" id="email"/>
    			</div>
  			</div>
  			
  			<div class="form-group">
						<label for="password" style="color:red;text-align:left;" class="col-sm-offset-2 col-sm-10 control-label"><form:errors path="password"/></label>
			</div>
  			
  			<div class="form-group">
				<label for="email" class="col-sm-offset-2 col-sm-10"><form:errors path="password"/></label>
			</div>
			<div class="form-group">
    			<label for="password" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<form:password class="form-control" path="password" id="password"/>
    			</div>
  			</div>
  		
  			
  			<div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">
      				<button type="submit" class="btn btn-default">Register</button>
    			</div>
  			</div>
		</form:form>
	</div>
</div>