<%-- 
    Document   : loginpage
    Created on : Dec 12, 2016, 1:00:17 PM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login-Sharks</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Novus Admin Panel Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- font CSS -->
<!-- font-awesome icons -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
 <!-- js-->
<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/modernizr.custom.js"></script>
<!--webfonts-->
<link href='//fonts.googleapis.com/css?family=Roboto+Condensed:400,300,300italic,400italic,700,700italic' rel='stylesheet' type='text/css'>
<!--//webfonts--> 
<!--animate-->
<link href="css/animate.css" rel="stylesheet" type="text/css" media="all">
<script src="js/wow.min.js"></script>
	<script>
		 new WOW().init();
	</script>
<!--//end-animate-->
<!-- Metis Menu -->
<script src="js/metisMenu.min.js"></script>
<script src="js/custom.js"></script>
<link href="css/custom.css" rel="stylesheet">
<!--//Metis Menu -->
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">


		<!-- main content start-->
		<div>
			<div class="main-page login-page ">
                            <br/>
                            <h3 class="title1">Sharks Dashboard</h3>
                            <br/>
                            <div class="widget-shadow">
					<div class="login-top">
						<h4>Welcome back to SHARKS 1 AdminPanel ! <br> Not a Member? <a href="${pageContext.request.contextPath}/SignupServlet">  Sign Up »</a> </h4>
					</div>
					<div class="login-body">
						<form action="${pageContext.request.contextPath}/LoginServlet" method="post"  >
							<input type="text" class="user" name="mid" placeholder="Enter your ID" required="">
							<input type="password" name="mpassword" class="lock" placeholder="password">
                                                        <input type="hidden" name="hiddenflag" id="hiddenflag" value="login">
							<input type="submit" name="Sign In" value="Sign In">
							<div class="forgot-grid">
								<!--<label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Remember me</label>-->
<!--								<div class="forgot">
									<a href="#">forgot password?</a>
								</div>-->
								<div class="clearfix"> </div>
							</div>
						</form>
					</div>
				</div>
				
				<div class="login-page-bottom">
                                    <br/>                                    <br/>

					<!--<h5> - OR -</h5>-->
<!--					<div class="social-btn"><a href="#"><i class="fa fa-facebook"></i><i>Sign In with Facebook</i></a></div>
					<div class="social-btn sb-two"><a href="#"><i class="fa fa-twitter"></i><i>Sign In with Twitter</i></a></div>-->
				</div>
			</div>
		</div>
		<!--footer-->
                <br/>                <br/>

                
		<div class="footer">
		   <p>&copy; 2016 Novus Admin Panel. All Rights Reserved | Design by <a href="https://w3layouts.com/" target="_blank">w3layouts</a></p>
		</div>
        <!--//footer-->
	</div>
	<!-- Classie -->
		<script src="js/classie.js"></script>
		<script>
			var menuLeft = document.getElementById( 'cbp-spmenu-s1' ),
				showLeftPush = document.getElementById( 'showLeftPush' ),
				body = document.body;
				
			showLeftPush.onclick = function() {
				classie.toggle( this, 'active' );
				classie.toggle( body, 'cbp-spmenu-push-toright' );
				classie.toggle( menuLeft, 'cbp-spmenu-open' );
				disableOther( 'showLeftPush' );
			};
			
			function disableOther( button ) {
				if( button !== 'showLeftPush' ) {
					classie.toggle( showLeftPush, 'disabled' );
				}
			}
		</script>
	<!--scrolling js-->
	<script src="js/jquery.nicescroll.js"></script>
	<script src="js/scripts.js"></script>
	<!--//scrolling js-->
	<!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.js"> </script>
</body>
</html>