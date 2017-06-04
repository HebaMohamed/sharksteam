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
<title>Sharks Dashboard</title>
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
                    <div class="main-page chrt-page-grids" style="width: 100%" >
                           
                            
                            <div class="col-md-10 ">
                                <h3 class="title1">Driver Driving Behavior</h3>
                            <h4 class="title1">Driver Name : <%=(String)request.getAttribute("dname")%></h4>
                            <br/>
                            </div>
                            <div class="col-md-2">                            
                            <h1>
				<a><span class="<%=(String)request.getAttribute("avgtxtlable")%>"><%=(String)request.getAttribute("avgtxt")%></span></a>
                            </h1>

                            </div>
                            
                            <br/>

                            <div class="main-page charts-page">
                            <div class="charts">
                                
					<div class="col-md-6 chrt-page-grids">
						<h4 style="color:#4F52BA;"><%=(String)request.getAttribute("p1_name")%></h4>
						<h4 style="color:#F2B33F;"><%=(String)request.getAttribute("p2_name")%></h4>
						<h4 style="color:#e94e02;"><%=(String)request.getAttribute("p3_name")%></h4>
                                                <br/>
                                                <canvas id="line1" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
					</div>
                                
                                <div class="col-md-6 chrt-page-grids chrt-right">
						<h4 style="color:#4F52BA;"><%=(String)request.getAttribute("p4_name")%></h4>
						<h4 style="color:#F2B33F;"><%=(String)request.getAttribute("p5_name")%></h4>
						<h4 style="color:#e94e02;"><%=(String)request.getAttribute("p6_name")%></h4>
                                                <br/>
						<canvas id="line2" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
					</div>
                                                
                                
                                <div class="col-md-6 chrt-page-grids">
                                                <h4 style="color:#4F52BA;"><%=(String)request.getAttribute("p7_name")%></h4>
						<h4 style="color:#F2B33F;"><%=(String)request.getAttribute("p8_name")%></h4>
						<h4 style="color:#e94e02;"><%=(String)request.getAttribute("p9_name")%></h4>
                                                <br/>
						<canvas id="line3" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
					</div>
                                
                                <div class="col-md-6 chrt-page-grids chrt-right">
						<h4 style="color:#4F52BA;"><%=(String)request.getAttribute("p10_name")%></h4>
						<h4 style="color:#F2B33F;"><%=(String)request.getAttribute("p11_name")%></h4>
						<h4 style="color:#e94e02;"><%=(String)request.getAttribute("p12_name")%></h4>
						<br/>
                                                <canvas id="line4" height="300" width="400" style="width: 400px; height: 300px;"></canvas>
					</div>
                                                
                                        <div class="col-md-10 chrt-page-grids">
						<h4 class="title">Trips Ratings Chart</h4>

                                                <!--<span class="badge" style="background: #9358ac"><p style="color:#9358ac;">.</p></span>-->
                                                <h4 style="color:#9358ac;">★★★★★: <%=String.valueOf((Integer)request.getAttribute("rates_5"))%> trips</h4>
                                                <h4 style="color:#e94e02;">★★★★  : <%=String.valueOf((Integer)request.getAttribute("rates_4"))%> trips</h4>
                                                <h4 style="color:#585858;">★★★   : <%=String.valueOf((Integer)request.getAttribute("rates_3"))%> trips</h4>
                                                <h4 style="color:#F2B33F;">★★     : <%=String.valueOf((Integer)request.getAttribute("rates_2"))%> trips</h4>
                                                <h4 style="color:#4F52BA;">★      : <%=String.valueOf((Integer)request.getAttribute("rates_1"))%> trips</h4>

                                                <div class="doughnut-grid">
							<canvas id="doughnut" ></canvas>
						</div>
					</div>
                                                
                                                <div class="row">
                                                        <div class="col-md-6">
                                                            <br/><br/><br/><br/><br/><br/><br/><br/>
                                                            <center>
                                                        <h3><a href="#" data-toggle="modal" data-whatever="@mdo" data-target="#exampleModal" ><span class="label label-primary">Send Command</span></a></h3>
                                                        </center>
                                                        </div>
                                                </div>

                                
					<div class="clearfix"> </div>
						<script>
                                                    
                            <% ArrayList<String> daysnames = (ArrayList<String>) request.getAttribute("daysnames"); %>
                            <% ArrayList<Integer> p1Arr = (ArrayList<Integer>) request.getAttribute("p1Arr"); %>
                            <% ArrayList<Integer> p2Arr = (ArrayList<Integer>) request.getAttribute("p2Arr"); %>
                            <% ArrayList<Integer> p3Arr = (ArrayList<Integer>) request.getAttribute("p3Arr"); %>
                            <% ArrayList<Integer> p4Arr = (ArrayList<Integer>) request.getAttribute("p4Arr"); %>
                            <% ArrayList<Integer> p5Arr = (ArrayList<Integer>) request.getAttribute("p5Arr"); %>
                            <% ArrayList<Integer> p6Arr = (ArrayList<Integer>) request.getAttribute("p6Arr"); %>
                            <% ArrayList<Integer> p7Arr = (ArrayList<Integer>) request.getAttribute("p7Arr"); %>
                            <% ArrayList<Integer> p8Arr = (ArrayList<Integer>) request.getAttribute("p8Arr"); %>
                            <% ArrayList<Integer> p9Arr = (ArrayList<Integer>) request.getAttribute("p9Arr"); %>
                            <% ArrayList<Integer> p10Arr = (ArrayList<Integer>) request.getAttribute("p10Arr"); %>
                            <% ArrayList<Integer> p11Arr = (ArrayList<Integer>) request.getAttribute("p11Arr"); %>
                            <% ArrayList<Integer> p12Arr = (ArrayList<Integer>) request.getAttribute("p12Arr"); %>
                                                    
						var doughnutData = [
								{
									value: <%=(Integer)request.getAttribute("rates_1")%>,
									color:"#4F52BA",
                                                                        label: "1 Rattings"
								},
								{
									value: <%=(Integer)request.getAttribute("rates_2")%>,
									color : "#F2B33F",
                                                                        label: "2 Rattings"

								},
								{
									value: <%=(Integer)request.getAttribute("rates_3")%>,
									color : "#585858",
                                                                        label: "3 Rattings"
								},
								{
									value: <%=(Integer)request.getAttribute("rates_4")%>,
									color : "#e94e02",
                                                                        label: "4 Rattings"
								},
								{
									value: <%=(Integer)request.getAttribute("rates_5")%>,
									color : "#9358ac",
                                                                        label: "5 Rattings"
								}
							
							];
                                                        
                                                        // init arr
                                                        var labls = [];
                                                        var data_1 = [];
                                                        var data_2 = [];
                                                        var data_3 = [];
                                                        var data_4 = [];
                                                        var data_5 = [];
                                                        var data_6 = [];
                                                        var data_7 = [];
                                                        var data_8 = [];
                                                        var data_9 = [];
                                                        var data_10 = [];
                                                        var data_11 = [];
                                                        var data_12 = [];
                                                        
                                            <% for(int i = 0; i < daysnames.size(); i+=1) { %>
    
                                                labls.push('<%=daysnames.get(i)%>');
                                                data_1.push(<%=p1Arr.get(i)%>);
                                                data_2.push(<%=p2Arr.get(i)%>);
                                                data_3.push(<%=p3Arr.get(i)%>);
                                                data_4.push(<%=p4Arr.get(i)%>);
                                                data_5.push(<%=p5Arr.get(i)%>);
                                                data_6.push(<%=p6Arr.get(i)%>);
                                                data_7.push(<%=p7Arr.get(i)%>);
                                                data_8.push(<%=p8Arr.get(i)%>);
                                                data_9.push(<%=p9Arr.get(i)%>);
                                                data_10.push(<%=p10Arr.get(i)%>);
                                                data_11.push(<%=p11Arr.get(i)%>);
                                                data_12.push(<%=p12Arr.get(i)%>);


                                            <%}%>
                                                        
						var lineChartData1 = {
							labels : labls,
							datasets : [
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#4F52BA",
									pointColor : "#4F52BA",
									pointStrokeColor : "#fff",
									data : data_1
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#F2B33F",
									pointColor : "#F2B33F",
									pointStrokeColor : "#fff",
									data : data_2
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#e94e02",
									pointColor : "#e94e02",
									pointStrokeColor : "#fff",
									data : data_3
								}
							]
							
						};
                                                
                                                var lineChartData2 = {
							labels : labls,
							datasets : [
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#4F52BA",
									pointColor : "#4F52BA",
									pointStrokeColor : "#fff",
									data : data_4
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#F2B33F",
									pointColor : "#F2B33F",
									pointStrokeColor : "#fff",
									data : data_5
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#e94e02",
									pointColor : "#e94e02",
									pointStrokeColor : "#fff",
									data : data_6
								}
							]
							
						};
                                                
                                                var lineChartData3 = {
							labels : labls,
							datasets : [
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#4F52BA",
									pointColor : "#4F52BA",
									pointStrokeColor : "#fff",
									data : data_7
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#F2B33F",
									pointColor : "#F2B33F",
									pointStrokeColor : "#fff",
									data : data_8
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#e94e02",
									pointColor : "#e94e02",
									pointStrokeColor : "#fff",
									data : data_9
								}
							]
							
						};
                                                
                                                var lineChartData4 = {
							labels : labls,
							datasets : [
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#4F52BA",
									pointColor : "#4F52BA",
									pointStrokeColor : "#fff",
									data : data_10
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#F2B33F",
									pointColor : "#F2B33F",
									pointStrokeColor : "#fff",
									data : data_11
								},
								{
									fillColor : "rgba(51, 51, 51, 0)",
									strokeColor : "#e94e02",
									pointColor : "#e94e02",
									pointStrokeColor : "#fff",
									data : data_12
								}
							]
							
						};
                                                        
                                                
						new Chart(document.getElementById("doughnut").getContext("2d")).Doughnut(doughnutData);
						new Chart(document.getElementById("line1").getContext("2d")).Line(lineChartData1);
                                                new Chart(document.getElementById("line2").getContext("2d")).Line(lineChartData2);
						new Chart(document.getElementById("line3").getContext("2d")).Line(lineChartData3);
						new Chart(document.getElementById("line4").getContext("2d")).Line(lineChartData4);

					</script>	
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

<!--									<div class="modal-body">
									</div>-->
									<div class="modal-footer">
                                                                            <form action="${pageContext.request.contextPath}/ManageServlet" method="post">
                                                                                <input type="hidden" name="hiddenflag" id="hiddenflag" value="sendmsg">
										<label for="messagetext" class="control-label">Message:</label>
										<textarea class="form-control" name="messagetext" id="messagetext"></textarea>
										<button type="button" class="btn btn-default" data-dismiss="modal">No</button>
                                                                                <button type="submit" class="btn btn-primary">Yes</button>
                                                                            </form>
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
