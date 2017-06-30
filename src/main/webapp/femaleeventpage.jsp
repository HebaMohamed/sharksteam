<%-- 
    Document   : trips
    Created on : Jan 24, 2017, 9:48:22 PM
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
<title>Female Safety Warning</title>
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

   <style>
       #map {
        height: 400px;
        width: 100%;
       }
   </style>

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
                            
                            
                            <% FemaleWarning warning = (FemaleWarning) request.getAttribute("warning"); %>
                            <% Driver neardriver = (Driver) request.getAttribute("neardriver"); %>
                            <% int neardriverflag = (Integer) request.getAttribute("neardriverflag"); %>

                            
					<div class="col-md-12 stats-info widget-shadow">
                                            
                                            <br/>
                            <h3 class="title1">Female Safety Warning</h3>
                            
                            <%if(warning.status.equals("new")){%>
                            
                            <div class="alert alert-danger" role="alert">
				<strong>New !</strong> take an action ASAP!
		            </div>
                            
                            <%} else if(warning.status.equals("assigned")){%>
                            
                            <div class="alert alert-warning" role="alert">
				<strong>Nearest driver has been notified with this issue location</strong> 
			    </div>
                            <%} else {%>
                            <div class="alert alert-success" role="alert">
				<strong>Closed issue</strong> This issue is marked as closed
			    </div>
                            <%}%>




                                            
                                            <div class="profile-right">
                                                <p>Passenger</p>
                                                <h4>Name : <%=warning.p.FullName%></h4>
                                                <h4>Phone : <%=warning.p.Phone%></h4>
                                                <h4>Relative Phone : <%=warning.p.Relative_phone%></h4>
                                            </div> 
                                             <div class="profile-right">
                                                <p>Driver</p>
                                                <h4>Name : <%=warning.d.name%></h4>
                                                
                                                <div id="latlngdiv">
                                                    <p>Warning Location</p>
                                                    <h4>Latitude : <%=warning.lat%></h4>
                                                    <h4>Longitude : <%=warning.lng%></h4>
                                                </div>
                                            <br/>
                                            </div> 
                                            
                                                             
                                            
                                            
                                                <h2 style="opacity: 0.1;">   . </h2>
                                                <div id="map"></div>
    
<script src="https://www.gstatic.com/firebasejs/3.7.4/firebase.js"></script>
    <script>
        
    function initAutocomplete() {
//        var uluru = {lat: 30.045915, lng: 31.22429};//
var lat = <%=warning.lat%>;
var lng = <%=warning.lng%>;
        var uluru = {lat: lat, lng: lng};//

        var map = new google.maps.Map(document.getElementById('map'), {
          center: uluru,
          zoom: 14,
          mapTypeId: 'roadmap'
        });
        
        var image = new google.maps.MarkerImage('warningmaker.png',
        new google.maps.Size(65, 124),
        new google.maps.Point(0,0),
        new google.maps.Point(56, 122)
    );
   
     var marker = new google.maps.Marker({
          position: uluru,
          map: map,
          icon: image,
          title:"Warning Point"
        });
        
        //for map
           var sharkimg = new google.maps.MarkerImage('smallblueshark.png',
            new google.maps.Size(65, 124),
            new google.maps.Point(0,0),
            new google.maps.Point(56, 122)
        );
        vmarker = new google.maps.Marker({
              map: map,
              icon: sharkimg,
              title: "Vehicle Location",
              position: {lat: 0, lng: 0}
        });
      


             
//                                                              var config = {
//                                                                   apiKey: "AIzaSyDm82ItD0ET3--vv1k99xRq3-NvBFVUYnA",
//                                                                    authDomain: "sharksmapandroid-158200.firebaseapp.com",
//                                                                    databaseURL: "https://sharksmapandroid-158200.firebaseio.com"
//                                                              };
//                                                              firebase.initializeApp(config);
//
//                                                              // Get a reference to the database service
//                                                              var database = firebase.database();
//                                                                var cRef = database.ref('vehicles');          
//                                                                cRef.on('value', function(snapshot) {
//                                                                    //myGauge.setValue(snapshot.val());
//                                                                    snapshot.forEach(function(child){
//                                                                        var vid = child.key;
//                                                                        var vlat = child.child("Latitude").val();
//                                                                        var vlng = child.child("Longitude").val();
//                                                                        
//                                                                        var currentdid=vid;
//
//                                                                        var newpos = {lat: vlat, lng: vlng};
//                                                                        //get id index in driver array
//                                                                        for (i = 0; i < mids.length; i++) {
//                                                                            var iid = mids[i];
//                                                                            if(iid == currentdid){
//                                                                            marr[i].setPosition(newpos);
////                                                                            simpleNotify.notify('dsgregrgrthtr'+currentdid, 'warning');
////                                                                            simpleNotify.notify('dsgregrgrthtr'+currentdid, 'warning');
////                                                                            simpleNotify.notify('dsgregrgrthtr'+iid, 'warning');
//                                                                        }
//                                                                    }
//                                                                        
//                                                                        
//                                                                        
//                                                                    });
//                                                                    
//                                                                });

        
        
        
        
        
        
      }
      
      
      
        
     
    

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


         var rcRef = database.ref('driver').child(<%=neardriver.id%>).child("warninghelp");
         rcRef.on('value', function(snapshot) {
             var res="";
             res = snapshot.child("response").val();
             if(res!="null"){
                 if(res=="danger"){
                    document.getElementById("dresponse").innerHTML = "<h3><span class=\"label label-danger\">"+res+"</span></h3>";
                }
                else if(res=="safe"){
                    document.getElementById("dresponse").innerHTML = "<h3><span class=\"label label-success\">"+res+"</span></h3>";
                }
             }
         });
         
         
         
         
         //listen to vehicle moves
                  ////listen to vehicle moves
         var vid="";
         var vid = <%=warning.v.ID%>;
         //for moving vehicle
            var cRefv = database.ref('vehicles').child(vid);          
            cRefv.on('value', function(snapshot) {
                var vlat = snapshot.child("Latitude").val();
                var vlng = snapshot.child("Longitude").val();
                                                                        
                var newpos = {lat: vlat, lng: vlng};
                
                vmarker.setPosition(newpos);
                map.panTo(newpos);
                map.setZoom(14);
            });



</script>
   
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMkIegihYnGDWqYZukBz2eo_InQOh-XEI&libraries=places&callback=initAutocomplete"
         async defer></script> 
         
         <br/>
         
         
         <div class="profile-right">
                    <%if(!warning.status.equals("closed")){%>
             <p>Nearest Driver Name</p>
             <%if(neardriverflag == 1){%>
             <h4><%=neardriver.name%></h4>
             <%}else{%>
             <h4>There is no nearby drivers !</h4>
             <%}%>
                    <%}%>
                    
                    <%if(warning.status.equals("assigned")){%>
                    <p>Driver Response</p>
                    <div id="dresponse"><h4>.......</h4></div>
                    <%}%>
         </div>
         
         <div class="col-md-9"></div>
         <div class="col-md-3">
             <h3>
                 <%if(neardriverflag == 1){%>
                 <%if((!warning.status.equals("closed")) && (!warning.status.equals("assigned"))){%>
                    <a href="${pageContext.request.contextPath}/FemaleServlet?goflag=sendhelp&id=<%=neardriver.id%>&nearvid=<%=neardriver.vehicle.ID%>&fid=<%=warning.timestamp%>"><span class="label label-danger">Ask him for help</span></a>
                 <%}%>
                 <%}%>
                 <br/><br/>
             </h3>
                 
                 <h3>
                     <a href="${pageContext.request.contextPath}/FemaleServlet?goflag=close&id=<%=neardriver.id%>&nearvid=<%=neardriver.vehicle.ID%>&fid=<%=warning.timestamp%>"><span class="label label-warning">Close issue</span></a>
                 </h3>
         </div>
         
         
         
         
         <br/><br/>
                                                             
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
