<%-- 
    Document   : driverprofile
    Created on : Dec 10, 2016, 4:48:14 PM
    Author     : dell
--%>

<%@page import="java.util.ArrayList"%>
<%@ page import="myclassespackage.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Driver Profile</title>
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
<!--						<li class="">
							<a href="#"><i class="fa fa-book nav_icon"></i>UI Elements <span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="general.html">General<span class="nav-badge-btm">08</span></a>
								</li>
								<li>
									<a href="typography.html">Typography</a>
								</li>
							</ul>
							 /nav-second-level 
						</li>-->
						<li>
							<a href="${pageContext.request.contextPath}/EventServlet"><i class="fa fa-th-large nav_icon"></i>Events</a>
						</li>
<!--						<li>
							<a href="#"><i class="fa fa-envelope nav_icon"></i>Mailbox<span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="inbox.html">Inbox <span class="nav-badge-btm">05</span></a>
								</li>
								<li>
									<a href="compose.html">Compose email</a>
								</li>
							</ul>
							 //nav-second-level 
						</li>-->
						<li>
							<a href="${pageContext.request.contextPath}/TripServlet"><i class="fa fa-check-square-o nav_icon"></i>Trips </a>
						</li>
<!--						<li>
							<a href="#"><i class="fa fa-check-square-o nav_icon"></i>Trips</a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="forms.html">Basic Forms <span class="nav-badge-btm">07</span></a>
								</li>
								<li>
									<a href="validation.html">Validation</a>
								</li>
							</ul>
							 //nav-second-level 
						</li>-->
<!--						<li>
							<a href="#"><i class="fa fa-file-text-o nav_icon"></i>Pages<span class="nav-badge-btm">02</span><span class="fa arrow"></span></a>
							<ul class="nav nav-second-level collapse">
								<li>
									<a href="login.html">Login</a>
								</li>
								<li>
									<a href="signup.html">SignUp</a>
								</li>
								<li>
									<a href="blank-page.html">Blank Page</a>
								</li>
							</ul>
							 //nav-second-level 
						</li>
						<li>
							<a href="charts.html" class="chart-nav"><i class="fa fa-bar-chart nav_icon"></i>Charts <span class="nav-badge-btm pull-right">new</span></a>
						</li>-->
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
                            
                            <% String servletParam = "showall"; %>
                            <% request.setAttribute(servletParam,servletParam); %>
                            
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
			
                    
                    <%  Driver selectedD = (Driver)request.getAttribute("selecteddriver"); %>
                    
                    
                    
                    <div class="blank-page widget-shadow scroll">
						<h4 class="title3">Driver Profile</h4>
						<div class="profile-top">
                                                    <img width="170" height="170" src='<%=(String)request.getAttribute("image")%>' alt="">
                                                        <h4><%=selectedD.name%></h4>
							<h5>Driver</h5>
						</div>
						<div class="profile-text">
							<div class="profile-row">
								<div class="profile-left">
									<i class="fa fa-envelope profile-icon"></i>
								</div>
								<div class="profile-right">
                                                                        <p>Email</p>
									<h4><%=selectedD.email%></h4>
								</div> 
								<div class="clearfix"> </div>	
							</div>
							<div class="profile-row row-middle">
								<div class="profile-left">
									<i class="fa fa-mobile profile-icon"></i>
								</div>
								<div class="profile-right">
                                                                        <p>Contact Number</p>
									<h4>222-555-111 to be added</h4>
								</div> 
								<div class="clearfix"> </div>	
							</div>
<!--							<div class="profile-row">
								<div class="profile-left">
									<i class="fa fa-facebook profile-icon"></i>
								</div>
								<div class="profile-right">
									<h4>facebook.com/user</h4>
									<p>Facebook</p>
								</div> 
								<div class="clearfix"> </div>	
							</div>-->
							<div class="profile-row">
								<div class="profile-left">
                                                                    <img width="50" height="50" src="images/carflat.png" alt="">
								</div>
								<div class="profile-right">
                                                                <p>Current Vehicle</p>
                                                                            <% if(selectedD.vehicle.ID != 0){ %>
                                                                                <h4>Model : <%=selectedD.vehicle.Model%></h4>
                                                                                <h4>Color : <%=selectedD.vehicle.Color%></h4>
                                                                                <h4>Plate number : <%=selectedD.vehicle.Plate_number%></h4>
                                                                           <%}else{%>
                                                                                <h4>There is no vehicle assigned for this driver</h4>
                                                                           <%}%>
                                                                        <div class="col-md-9">
                                                                               
                                                                        </div>
                                                                        <div class="col-md-3">
                                                                            <a href="#" data-toggle="modal" data-whatever="@mdo" data-target="#exampleModalll"><span class="label label-danger">Change Vehicle</span></a>
                                                                        </div>
                                                                    
								</div> 
								<div class="clearfix"> </div>	
							</div>
						</div>
						<div class="profile-btm">
							<ul>
								<li>
                                                                    <h4><%=selectedD.Sharp_Uturns%></h4>
									<h5>Sharp Turns</h5>
								</li>
								<li>
									<h4><%=selectedD.Lane_Changing%></h4>
									<h5>Lane Changing</h5>
								</li>
								<li>
									<h4><%=selectedD.Harsh_Acceleration%></h4>
									<h5>Harsh Acceleration</h5>
								</li>
<!--                                                                	<li>
									<h4><%=selectedD.Last_trip_Behaviour_Map%></h4>
									<h5>Last Trip Behaviour Map</h5>
								</li>-->
                                                                	<li>
									<h4><%=selectedD.Wrong_Uturns%></h4>
									<h5>Wrong Uturns</h5>
								</li>
                                                                	<li>
									<h4><%=selectedD.Harsh_Breaking%></h4>
									<h5>Harsh Breaking</h5>
								</li>
                                                                	<li>
									<h4><%=selectedD.Awarness_Level%></h4>
									<h5>Awarness Level</h5>
								</li>
							</ul>
						</div>
                                                                        
                                                                        
                                                                        <br/>
                                                                        <% if(!(selectedD.ristrictedrouteImg.equals(""))) { %>
                                                                        
                                                                        <div class="row">
                                                                            <p>Restricted Route</p><br/>
                                                                            <center><img src="<%=selectedD.ristrictedrouteImg%>" /></center>
                                                                        </div>
                                                                        <%} else {%>
                                                                        <h5>There is no restricted routed for this driver.</h5>
                                                                        <%}%>
                                                                        <div class="form-group">
						<div class="row">
							<div class="col-md-3">
                                                            <a href="${pageContext.request.contextPath}/RouteServlet?id=<%=selectedD.id%>&name=<%=selectedD.name%>"><span class="label label-primary">Manage Restricted Route</span></a>
							</div>
							<div class="col-md-3">
							</div>
							<div class="col-md-3">
							</div>
							<div class="col-md-3">
								
                                                                            <a href="${pageContext.request.contextPath}/ManageServlet?goflag=editdriver&id=<%=selectedD.id %>"><span class="label label-warning">Edit</span></a>
                                                                            <a href="#" data-toggle="modal" data-whatever="@mdo" data-target="#exampleModall"><span class="label label-danger">Delete</span></a>
                                                                            <a href="#" data-toggle="modal" data-whatever="@mdo" data-target="#exampleModal" ><span class="label label-primary">Send Command</span></a>
                                                                                         
							</div>
							<div class="clearfix"> </div>
						</div>
					</div>
                                                                        
                                                                        
                                                                        
                                                                        
					</div>
                                                                            
                                                      
                                                                
                                                <div class="modal fade" id="exampleModalll" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title" id="exampleModalLabel">Assign New Vehicle</h4>
									</div>
									<div class="modal-body">
										
                                                                            
                                                                            <table class="table table-hover"> 
                                                                                <thead> 
                                                                                    <tr> 
                                                                                        <th>V.ID</th> 
                                                                                        <th>Vehicle Model</th> 
                                                                                        <th>Vehicle Color</th> 
                                                                                        <th>Option</th> 
                                                                                    </tr> 
                                                                                </thead> 
                                                                                <tbody>                                                                                     
                                                                                    
                                                                                                                                                
                                                            <% ArrayList<Vehicle> vehicles = (ArrayList<Vehicle>) request.getAttribute("allV"); %>
                                                                <% for(int i = 0; i < vehicles.size(); i+=1) { %>
                                                                
                                                            
								<tr>
                                                                        <th scope="row"><%=vehicles.get(i).ID %></th>
                                                                        <th><%=vehicles.get(i).Model %></th>
                                                                        <th><%=vehicles.get(i).Color %></th>
                                                                        <th>
                                                                            <a href="${pageContext.request.contextPath}/ManageServlet?goflag=assignvehicle&vid=<%=vehicles.get(i).ID%>"><span class="label label-primary">   Select   </span></a>
                                                                        </th>

                                                                </tr>
							     <% } %>
                                                                                    
                                                                                </tbody> 
                                                                            </table>
                                                                            
                                                                            
                                                                            
                                                                            
									</div>
									<div class="modal-footer">
<!--										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										<a type="button" class="btn btn-primary">Save</a>-->
									</div>
								</div>
							</div>
						</div>
                                                                            
                                                                           
                                                <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
										<h4 class="modal-title" id="exampleModalLabel">New Instruction</h4>
									</div>
									<div class="modal-body">
										<form>
											<div class="form-group">
												<label for="message-text" class="control-label">Message:</label>
												<textarea class="form-control" id="message-text"></textarea>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										<a type="button" class="btn btn-primary" data-dismiss="modal">Send message</a>
									</div>
								</div>
							</div>
						</div>

                                                                            
                                                <div class="modal fade" id="exampleModall" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                <h4 class="modal-title" id="exampleModalLabel">Are you sure to delete this driver?</h4>
									</div>
<!--									<div class="modal-body">
									</div>-->
									<div class="modal-footer">
                                                                            <form action="${pageContext.request.contextPath}/ManageServlet" method="post">
                                                                                <input type="hidden" name="hiddenflag" id="hiddenflag" value="delete">
										<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                                                                <button type="submit" class="btn btn-primary">Yes</button>
                                                                            </form>
									</div>
								</div>
							</div>
						</div>
                    
                    
                                                                                                                                   
                                                                        
                    
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
