<%-- 
    Document   : managedriver
    Created on : Dec 9, 2016, 12:29:56 AM
    Author     : dell
--%>

<%@page import="myclassespackage.*"%>
<%@page import="net.sf.json.JSONArray"%>
<%@page import="net.sf.json.JSONObject"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Drivers Management</title>
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
                                                                <li>
									<a href="${pageContext.request.contextPath}/PassengersServlet">Passengers</a>
								</li>
							</ul>
							<!-- /nav-second-level -->
						</li>
                                                <li>
							<a href="${pageContext.request.contextPath}/MonitorServlet"><i class="fa fa-home nav_icon"></i>Monitor</a>
						</li>

						<li>
							<a href="#" ><i class="fa fa-th-large nav_icon"></i>Events <span id="eventsf" class="nav-badge-btm"></span><span class="fa arrow"></span></a>
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

				<div class="clearfix"> </div>
			</div>

                    <div class="header-right">
				<div class="profile_details_left"><!--notifications of menu start -->
					<ul class="nofitications-dropdown">
						<li class="dropdown head-dpdn">
							<a href="${pageContext.request.contextPath}/PendingServlet" class="dropdown-toggle" aria-expanded="false"><i class="fa fa-bell"></i><span id="memberf" class="badge blue"></span></a>
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
			<div class="main-page">
                                                        
                                <h3 class="title1">Drivers Management</h3>
                            
<!--                            <div class="col-md-2">                            
                            <h1>
				<a href="${pageContext.request.contextPath}/ManageServlet?goflag=refreshdriversavg"><span class="label label-warning">Refresh all drivers average</span></a>
                            </h1>

                            </div>-->

				<div class="blank-page widget-shadow scroll" id="style-2 div1">
                
                                    							
                                    <form action="${pageContext.request.contextPath}/ManageServlet" method="post"> 
                                        <input type="hidden" name="hiddenflag" id="hiddenflag" value="openadd">

                                        <!--<h3><a href="#"><span class="label label-primary">New Driver</span></a></h3>-->
                                        <div class="col-md-9">
                                             
                                            <input class="btn btn-primary" type="submit" name="button1" value="New Driver" />

                                        </div>
                                                        
                                        <div class="col-md-3">                            
                                            <h3>
                                                <a href="${pageContext.request.contextPath}/ManageServlet?goflag=refreshdriversavg"><span class="label label-warning">Refresh all drivers average</span></a>
                                            </h3>
                                        </div>
                                            <br/>
                                    </form>
                                    <br/>
                                    	<form class="input"action="${pageContext.request.contextPath}/ManageServlet" method="post">
                                            <div class="col-md-12">    
                                                <input type="hidden" name="hiddenflag" id="hiddenflag" value="search">
						<input class="sb-search-input input__field--madoka"  placeholder="Search..." type="search" name="searchtxt" id="input-31" />
                                            </div>
                                        </form>
                                    
                                    <br/>                                   
                                    
                                    
                                    
                                          
<!--					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic 
						It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here.
					</p>-->
				</div>
                                    
					<div class="col-md-12 stats-info widget-shadow">
						<table class="table stats-table ">
							<thead>
								<tr>
									<th>D.ID</th>
									<th>Driver Name</th>
                                                                        <th>Email</th>
									<th>Vehicle ID</th>
									<th>Options</th>
								</tr>
							</thead>
							<tbody>
                                                           
                                                            <% String deletef = "0"; %>
                                                            <% ArrayList<Driver> drivers = (ArrayList<Driver>) request.getAttribute("drivers"); %>
                                                                <% for(int i = 0; i < drivers.size(); i+=1) { %>
                                                                
                                                            
								<tr>
<!--									<th scope="row">1</th>
									<td>Thomas</td>
									<td><span class="label label-success">In progress</span></td>
									<td><h5>85% <i class="fa fa-level-up"></i></h5></td>-->
                                                                        <th><%=drivers.get(i).id %></th>
                                                                        <th><%=drivers.get(i).name %></th>
                                                                        <th><%=drivers.get(i).email %></th>
                                                                        <th><%=drivers.get(i).vehicle.ID %></th>
                                                                        <th>
                                                                            <a href="${pageContext.request.contextPath}/ManageServlet?goflag=showdriver&id=<%=drivers.get(i).id  %>"><span class="label label-primary">   View   </span></a>
<!--                                                                            <a href="${pageContext.request.contextPath}/ManageServlet?goflag=editdriver&id=<%=drivers.get(i).id  %>"><span class="label label-warning">Edit</span></a>
                                                                            <a href="#" class="tableRow" data-toggle="modal" data-whatever="@mdo" data-target="#exampleModal"><span class="label label-danger">Delete</span></a>
                                                                            <a href="#"><span class="label label-primary">Send Command</span></a>
                                                                            -->
                                                                        </th>

                                                                </tr>
							     <% } %>
                                                             
							</tbody>
						</table>
                                                             
                                                    <script type='text/javascript'>
                                                        var id ='';
                                                        function myFunction(d){
                                                        //...script code
                                                        id=d;
                                                        <%deletef = "<script>document.writeln(id)</script>";%>
                                                        }
                                                        
                                                        
                                                        $(".tableRow").click(function(e){
                                                           <%deletef = "<script>document.writeln(id)</script>";%>
                                                          });

                                                    </script>
                                                             
                                                             
                                                             
                                                    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                                                                <h4 class="modal-title" id="exampleModalLabel">Are you sure to delete this driver? <%=deletef%></h4>
									</div>
									<div class="modal-body">
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                                                                <a href="?res=yes" type="button" class="btn btn-primary">Yes</a>
									</div>
								</div>
							</div>
						</div>

                                                             
                                                             
					</div>
                                    
                                    
                                    
                                    
                              
			</div>
		</div>
                                                                        
                <script src="https://www.gstatic.com/firebasejs/3.7.4/firebase.js"></script>
<script>
    

                                                              var config = {
                                                                   apiKey: "AIzaSyDm82ItD0ET3--vv1k99xRq3-NvBFVUYnA",
                                                                    authDomain: "sharksmapandroid-158200.firebaseapp.com",
                                                                    databaseURL: "https://sharksmapandroid-158200.firebaseio.com"
                                                              };
                                                              firebase.initializeApp(config);

                                                              // Get a reference to the database service
                                                              var database = firebase.database();
                                                              
                                                              
        ///////for notifications
         var cRef2 = database.ref('notifications');   
         cRef2.on('value', function(snapshot) {
             var driverwarning = snapshot.child("driverwarning").val();
             var femalewarning = snapshot.child("femalewarning").val();
             var newmember = snapshot.child("newmember").val();
             
            if(newmember != ""){
                 document.getElementById("memberf").style.visibility = "visible";
                document.getElementById("memberf").innerHTML = newmember;
            }
            else{
                document.getElementById("memberf").style.visibility = "hidden";
            }
            
            if(driverwarning != "" || femalewarning != ""){
                document.getElementById("eventsf").style.visibility = "visible";
                document.getElementById("eventsf").innerHTML = "NEW";
            }
            else{
                document.getElementById("eventsf").style.visibility = "hidden";
            }

         });
         var cRef3 = database.ref('warning');
         var x = 0;
         cRef3.on('value', function(snapshot) {
             if(x!=0){
             simpleNotify.notify('There is a new Warning!, Please check events section', 'warning');
             simpleNotify.notify('', 'warning');
             simpleNotify.notify('', 'warning');
            }
            else{
                x = 1;//3shn awl mra msh ybyn notifications
            }
         });



</script>                                                        
                                                                        
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
