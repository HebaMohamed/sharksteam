<%-- 
    Document   : vehicleprofile
    Created on : Dec 12, 2016, 12:04:54 AM
    Author     : dell
--%>

<%@page import="myclassespackage.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Trip Details</title>
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
<!-- chart -->
<script src="js/Chart.js"></script>
</head> 
<body class="cbp-spmenu-push">
	<div class="main-content">
		<!--left-fixed -navigation-->
		<div class=" sidebar" role="navigation">
            <div class="navbar-collapse">
				<nav class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
					<ul class="nav" id="side-menu">
<!--						<li>
							<a href="index.html"><i class="fa fa-home nav_icon"></i>Dashboard</a>
						</li>-->
						<li>
							<a href="#"><i class="fa fa-cogs nav_icon"></i>Manage  <span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="${pageContext.request.contextPath}/ManageServlet?goflag=showdrivers">Drivers</a>
								</li>
								<li>
									<a href="${pageContext.request.contextPath}/ManageVehicleServlet?goflag=showvehicles">Vehicles</a>
								</li>
							</ul>
							<!-- /nav-second-level -->
						</li>
                                                <li>
							<a href="${pageContext.request.contextPath}/MonitorServlet"><i class="fa fa-home nav_icon"></i>Monitor</a>
						</li>

						<li>
							<a href="#" ><i class="fa fa-th-large nav_icon"></i>Events <span class="fa arrow"></span></a>
                                                        <ul class="nav nav-second-level collapse">
								<li>
									<a href="${pageContext.request.contextPath}/FemaleServlet">Female Safety</a>
								</li>	
                                                                <li>
									<a href="${pageContext.request.contextPath}/EventServlet">Warnings</a>
								</li>
							</ul>
						</li>

						<li>
							<a href="${pageContext.request.contextPath}/PattrensServlet"><i class="fa fa-cogs nav_icon"></i>Pattrens </a>
						</li>

                                                <li>
							<a href="${pageContext.request.contextPath}/TripServlet"><i class="fa fa-check-square-o nav_icon"></i>Trips </a>
						</li>

					</ul>
					<div class="clearfix"> </div>
					<!-- //sidebar-collapse -->
				</nav>
			</div>
		</div>
		<!--left-fixed -navigation-->
		<!-- header-starts -->
		<div class="sticky-header header-section ">
			<div class="header-left">
				<!--toggle button start-->
				<button id="showLeftPush"><i class="fa fa-bars"></i></button>
				<!--toggle button end-->
				<!--logo -->
				<div class="logo">
					<a href="index.html">
						<h1>SHARKS</h1>
						<span>AdminPanel</span>
					</a>
				</div>
				<!--//logo-->
				<!--search-box-->
<!--				<div class="search-box">
					<form class="input">
						<input class="sb-search-input input__field--madoka" placeholder="Search..." type="search" id="input-31" />
						<label class="input__label" for="input-31">
							<svg class="graphic" width="100%" height="100%" viewBox="0 0 404 77" preserveAspectRatio="none">
								<path d="m0,0l404,0l0,77l-404,0l0,-77z"/>
							</svg>
						</label>
					</form>
				</div>//end-search-box-->
				<div class="clearfix"> </div>
			</div>
                    <div class="header-right">
				<div class="profile_details_left"><!--notifications of menu start -->
					<ul class="nofitications-dropdown">
						<li class="dropdown head-dpdn">
							<a href="${pageContext.request.contextPath}/PendingServlet" class="dropdown-toggle" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">1</span></a>
						</li>	
					</ul>
					<div class="clearfix"> </div>
				</div>
                            
                                                
				<!--notification menu end -->
				<div class="profile_details">		
					<ul>
						<li class="dropdown profile_details_drop">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
								<div class="profile_img">	
									<span class="prfil-img"><img src="images/a.png" alt=""> </span> 
									<div class="user-name">
										<p><%= DataClass.currentMM.name %></p>
										<span><%= DataClass.currentMM.position %></span>
									</div>
									<i class="fa fa-angle-down lnr"></i>
									<i class="fa fa-angle-up lnr"></i>
									<div class="clearfix"></div>	
								</div>	
							</a>
							<ul class="dropdown-menu drp-mnu">
								<li> <a href="${pageContext.request.contextPath}/LoginServlet?goflag=logout"><i class="fa fa-sign-out"></i> Logout</a> </li>
							</ul>
						</li>
					</ul>
				</div>
				<div class="clearfix"> </div>	
			</div>
			<div class="clearfix"> </div>	
		</div>
		<!-- //header-ends -->
		<!-- main content start-->
		<div id="page-wrapper">
                    
                    
                            <h3 class="title1">Trip Details</h3>
			
                    
                    <%  Trip selectedT = (Trip)request.getAttribute("selectedtrip"); %>
                    
                    
                    
                    <div class="blank-page widget-shadow scroll">
						<!--<h4 class="title3">Trip Details</h4>-->
<!--						<div class="profile-top">
							<img src='' alt="">
                                                        <h4></h4>
							<h5>Vehicle</h5>
						</div>-->
<br/>
                                            
                                        <center><img src='<%= selectedT.staticmapurl %>' alt=""></center>

						<div class="profile-text col-md-12">
							<div class="profile-row">
								<div class="profile-left">
									<!--<i class="fa fa-envelope profile-icon"></i>-->
								</div>
								<div class="profile-right">
                                                                    <p>Pickup Address <span style="float:right"> <%=selectedT.getStartdate()%></span></p>
								    <h4><%=selectedT.from_addr%></h4>
                                                                     <p>Destination Address <span style="float:right"> <%=selectedT.getEnddate()%></span></p>
								    <h4><%=selectedT.to_addr%></h4>
								</div> 
								<div class="clearfix"> </div>	
							</div>
							<div class="profile-row row-middle">
								<div class="profile-left">
									<!--<i class="fa fa-mobile profile-icon"></i>-->
								</div>
								<div class="profile-right">
								    <p>Driver Name <span style="float:right"> ID : <%=selectedT.d.id%></span></p>
								    <h4><%=selectedT.d.name%></h4>
                                                                    <p>Passenger Name <span style="float:right"> ID : <%=selectedT.p.ID%></span></p>
								    <h4><%=selectedT.p.FullName%></h4>
								</div> 
								<div class="clearfix"> </div>	
							</div>
                                                        <div class="profile-row">
								<div class="profile-left">
									<!--<i class="fa fa-facebook profile-icon"></i>-->
								</div>
								<div class="profile-right">
                                                                    <p>Detected Pattrens</p>
                                                                    <h4><%=(String)request.getAttribute("p1_name")%> : <%=(Integer)request.getAttribute("p1")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p2_name")%> : <%=(Integer)request.getAttribute("p2")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p3_name")%> : <%=(Integer)request.getAttribute("p3")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p4_name")%> : <%=(Integer)request.getAttribute("p4")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p5_name")%> : <%=(Integer)request.getAttribute("p5")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p6_name")%> : <%=(Integer)request.getAttribute("p6")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p7_name")%> : <%=(Integer)request.getAttribute("p7")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p8_name")%> : <%=(Integer)request.getAttribute("p8")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p9_name")%> : <%=(Integer)request.getAttribute("p9")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p10_name")%> : <%=(Integer)request.getAttribute("p10")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p11_name")%> : <%=(Integer)request.getAttribute("p11")%></h4>
                                                                    <h4><%=(String)request.getAttribute("p12_name")%> : <%=(Integer)request.getAttribute("p12")%></h4>

                                                                    <div class="row">
                                                                        <div class="col-md-4">
                                                                        </div>
<!--                                                                     <div class="col-md-8 chrt-page-grids">
                                                                        <br/>
                                                                        <canvas id="line" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
                                                                     </div>-->
                                                
                                                                    </div>
								</div> 
								<div class="clearfix"> </div>	
							</div>
							<div class="profile-row">
								<div class="profile-left">
									<!--<i class="fa fa-facebook profile-icon"></i>-->
								</div>
								<div class="profile-right">
                                                                    <p>Price</p>
                                                                    <h4><%=selectedT.price%>$</h4>
								    <p>Feedback</p>
								    <!--<h4>*****</h4>-->
                                                                     <%//calculate ratting 
                                                                 
                                                                    String label = "";
                                                                    String stars = "";
                                                                    
                                                                    if(selectedT.rating==1){
                                                                        label = "label label-danger";
                                                                        stars = "★";
                                                                    }
                                                                    else if(selectedT.rating==2){
                                                                        label = "label label-info";
                                                                        stars = "★★";
                                                                    }
                                                                    else if(selectedT.rating==3){
                                                                        label = "label label-warning";
                                                                        stars = "★★★";
                                                                    }
                                                                    else if(selectedT.rating==4){
                                                                        label = "label label-success";
                                                                        stars = "★★★★";
                                                                    }
                                                                    else if(selectedT.rating==5){
                                                                        label = "label label-success";
                                                                        stars = "★★★★★";
                                                                    }
                                                                
                                                                %>
                                                                    <h4><span class="<%=label %>"><%=stars %></span></h4>
                                                                    <br/>
								    <h4><%=selectedT.comment%></h4>
								</div> 
								<div class="clearfix"> </div>	
							</div>
						</div>        
                                                                        
                                                                        <br/>
                                                                        <div class="form-group">
						<div class="row">
							<div class="col-md-3 grid_box1">
							</div>
							<div class="col-md-3 grid_box1">
							</div>
							<div class="col-md-3 grid_box1">
							</div>
							<div class="col-md-3">
								
                                                            
                                                                            <a href="${pageContext.request.contextPath}/ManageServlet?goflag=showdriver&id=<%=selectedT.d.id  %>"><span class="label label-primary">View Driver</span></a>
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
                                                                        
                                                                        
                                                                        
                                                                        
					</div>
                                                                            
                                                                            
                                                                            
                                                                            
                       
                                                                            <script>
//                                                                                new Chart(document.getElementById("line").getContext("2d")).Line(lineChartData);
                                                                            </script>
                                                                                                                                   
                                                                        
                    
		</div>
		<!--footer-->
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
