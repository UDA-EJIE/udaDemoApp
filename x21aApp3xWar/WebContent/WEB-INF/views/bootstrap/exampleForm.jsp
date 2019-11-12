<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@include file="/WEB-INF/includeTemplate.inc"%>




<section>
	<!-- Stack the columns on mobile by making one full-width and the other half-width -->
	<div class="row">
	  <div class="col-12 col-md-8">
	  
	  		<div class="row">
	  			<div class="col-md-12">
	  				<form:form method="post" action=".">
                    <input type='hidden' name='csrfmiddlewaretoken' value='XFe2rTYl9WOpV8U6X5CfbIuOZOELJ97S' />
                            

                    <form:form modelAttribute="randomForm" class="form-horizontal" method="post" >
                        <input type='hidden' name='csrfmiddlewaretoken' value='XFe2rTYl9WOpV8U6X5CfbIuOZOELJ97S' />
                        <div id="div_id_select" class="form-group required">
                            <label for="id_select"  class="control-label col-md-4  requiredField"> Select<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
                                <label class="radio-inline"><form:radiobutton checked="checked" path="select" id="id_select_1" value="S"  style="margin-bottom: 10px"/>Knowledge Seeker</label>
                                <label class="radio-inline"> <form:radiobutton type="radio" path="select" id="id_select_2" value="P"  style="margin-bottom: 10px"/>Knowledge Provider </label>
                            </div>
                        </div> 
                        <div id="div_id_As" class="form-group required">
                            <label for="id_As"  class="control-label col-md-4  requiredField">As<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
                                <label class="radio-inline"> <form:radiobutton path="As" id="id_As_1" value="I"  style="margin-bottom: 10px"/>Individual </label>
                                <label class="radio-inline"> <form:radiobutton path="As" id="id_As_2" value="CI"  style="margin-bottom: 10px"/>Company/Institute </label>
                            </div>
                        </div>
                        <div id="div_id_username" class="form-group required">
                            <label for="id_username" class="control-label col-md-4  requiredField"> Username<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <form:input class="input-md  textinput textInput form-control" id="id_username" maxlength="30" path="username" placeholder="Choose your username" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>
                        <div id="div_id_email" class="form-group required">
                            <label for="id_email" class="control-label col-md-4  requiredField"> E-mail<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <form:input class="input-md emailinput form-control" id="id_email" path="email" placeholder="Your current email address" style="margin-bottom: 10px" type="email" />
                            </div>     
                        </div>
                        <div id="div_id_password1" class="form-group required">
                            <label for="id_password1" class="control-label col-md-4  requiredField">Password<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "> 
                                <form:input class="input-md textinput textInput form-control" id="id_password1" path="password1" placeholder="Create a password" style="margin-bottom: 10px" type="password" />
                            </div>
                        </div>
                        <div id="div_id_password2" class="form-group required">
                             <label for="id_password2" class="control-label col-md-4  requiredField"> Re:password<span class="asteriskField">*</span> </label>
                             <div class="controls col-md-8 ">
                                <form:input class="input-md textinput textInput form-control" id="id_password2" path="password2" placeholder="Confirm your password" style="margin-bottom: 10px" type="password" />
                            </div>
                        </div>
                        <div id="div_id_name" class="form-group required"> 
                            <label for="id_name" class="control-label col-md-4  requiredField"> full name<span class="asteriskField">*</span> </label> 
                            <div class="controls col-md-8 "> 
                                <form:input class="input-md textinput textInput form-control" id="id_name" path="name" placeholder="Your Frist name and Last name" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div>
                        <div id="div_id_gender" class="form-group required">
                            <label for="id_gender"  class="control-label col-md-4  requiredField"> Gender<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "  style="margin-bottom: 10px">
                                 <label class="radio-inline"> <form:radiobutton path="gender" id="id_gender_1" value="M"  style="margin-bottom: 10px"/>Male</label>
                                 <label class="radio-inline"> <form:radiobutton path="gender" id="id_gender_2" value="F"  style="margin-bottom: 10px"/>Female </label>
                            </div>
                        </div>
                        <div id="div_id_company" class="form-group required"> 
                            <label for="id_company" class="control-label col-md-4  requiredField"> company name<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "> 
                                 <form:input class="input-md textinput textInput form-control" id="id_company" path="company" placeholder="your company name" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div> 
                        <div id="div_id_catagory" class="form-group required">
                            <label for="id_catagory" class="control-label col-md-4  requiredField"> catagory<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 "> 
                                 <form:input class="input-md textinput textInput form-control" id="id_catagory" path="catagory" placeholder="skills catagory" style="margin-bottom: 10px" type="text" />
                            </div>
                        </div> 
                        <div id="div_id_number" class="form-group required">
                             <label for="id_number" class="control-label col-md-4  requiredField"> contact number<span class="asteriskField">*</span> </label>
                             <div class="controls col-md-8 ">
                                 <form:input class="input-md textinput textInput form-control" id="id_number" path="number" placeholder="provide your number" style="margin-bottom: 10px" type="text" />
                            </div> 
                        </div> 
                        <div id="div_id_location" class="form-group required">
                            <label for="id_location" class="control-label col-md-4  requiredField"> Your Location<span class="asteriskField">*</span> </label>
                            <div class="controls col-md-8 ">
                                <form:input class="input-md textinput textInput form-control" id="id_location" path="location" placeholder="Your Pincode and City" style="margin-bottom: 10px" type="text" />
                            </div> 
                        </div>
                        <div class="form-group">
                            <div class="controls col-md-offset-4 col-md-8 ">
                                <div id="div_id_terms" class="checkbox required">
                                    <label for="id_terms" class=" requiredField">
                                         <form:checkbox class="input-ms checkboxinput" id="id_terms" path="terms" style="margin-bottom: 10px" />
                                         Agree with the terms and conditions
                                    </label>
                                </div> 
                                    
                            </div>
                        </div> 
                        <div class="form-group"> 
                            <div class="aab controls col-md-4 "></div>
                            <div class="controls col-md-8 ">
                                <input type="submit" name="Signup" value="Signup" class="btn btn-primary btn btn-info" id="submit-id-signup" />
                                or <input type="button" name="Signup" value="Sign Up with Facebook" class="btn btn btn-primary" id="button-id-signup" />
                            </div>
                        </div> 
                            
                    </form:form>

                </form:form>
	  			
	  			</div>
	  		
	  		</div>
	  <br>
	  <hr>
	  <br>
	  <section>
	        <div class="row">
	        <div class="col-lg-4">
	          <h2>Safari bug warning!</h2>
	          <p class="text-danger">As of v9.0, Safari exhibits a bug in which resizing your browser horizontally causes rendering errors in the justified nav that are cleared upon refreshing.</p>
	          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <spring:url value="#" var="urlHashtag" htmlEscape="true"/>
	          <p><a class="btn btn-primary" href="${urlHashtag}" role="button">View details »</a></p>
	        </div>
	        <div class="col-lg-4">
	          <h2>Heading</h2>
	          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
	          <p><a class="btn btn-primary" href="${urlHashtag}" role="button">View details »</a></p>
	       </div>
	        <div class="col-lg-4">
	          <h2>Heading</h2>
	          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>
	          <p><a class="btn btn-primary" href="${urlHashtag}" role="button">View details »</a></p>
	        </div>
	      </div>
	     </section>
	  
	  </div>
	  <div class="col-xs-6 col-md-4">
	  			<div class="row">
					<div class="col-md-12">
						<div class="sidebar-module sidebar-module-inset">
				            <h4>About</h4>
				            <p>Etiam porta <em>sem malesuada magna</em> mollis euismod. Cras mattis consectetur purus sit amet fermentum. Aenean lacinia bibendum nulla sed consectetur.</p>
				          </div>
				
					
					</div>
				</div>
				<br>
	  <hr>
	  <br>
				<div class="row">
					<div class="col-md-12">
						<div class="sidebar-module">
				            <h4>Archives</h4>
				            <ol class="list-unstyled">
				              <li><a href="${urlHashtag}">March 2014</a></li>
				              <li><a href="${urlHashtag}">February 2014</a></li>
				              <li><a href="${urlHashtag}">January 2014</a></li>
				              <li><a href="${urlHashtag}">December 2013</a></li>
				              <li><a href="${urlHashtag}">November 2013</a></li>
				              <li><a href="${urlHashtag}">October 2013</a></li>
				              <li><a href="${urlHashtag}">September 2013</a></li>
				              <li><a href="${urlHashtag}">August 2013</a></li>
				              <li><a href="${urlHashtag}">July 2013</a></li>
				              <li><a href="${urlHashtag}">June 2013</a></li>
				              <li><a href="${urlHashtag}">May 2013</a></li>
				              <li><a href="${urlHashtag}">April 2013</a></li>
				            </ol>
				          </div>
				
					
					</div>
				</div>
				<br>
	  <hr>
	  <br>
				<div class="row">
					<div class="col-md-12">
						<div class="sidebar-module">
			            <h4>Elsewhere</h4>
			            <ol class="list-unstyled">
			              <li><a href="${urlHashtag}">GitHub</a></li>
			              <li><a href="${urlHashtag}">Twitter</a></li>
			              <li><a href="${urlHashtag}">Facebook</a></li>
			            </ol>
			          </div>
			    </div>
				</div>
	  
	  </div>
	</div>
	
	

	
</section>