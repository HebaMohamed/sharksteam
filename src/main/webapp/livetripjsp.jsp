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
<title>Live Trip</title>
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

<!--for speed meter-->

<link rel="stylesheet" href="speedmeterfiles/jg/css/jgauge.css" type="text/css" />

<script src="speedmeterfiles/justgage/raphael-2.1.4.min.js"></script>
<script src="speedmeterfiles/justgage/justgage.js"></script>

<!--end for speed meter-->

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
                            
                            
                    <%  Driver selectedD = (Driver)request.getAttribute("selecteddriver"); %>
                            <h3 class="title1">Live Trip</h3>
                            
                            <h5>This is a real-time trip location with traffic layer and directions</h5>
                            <br/>
                            
                            
                    <div class="col-md-12 widget-shadow">
                                                         <div id="map"></div>
                                                             <script>

    var map;//3shn yb2a pub
      
    var selecteddtogo = "";
    
    var marr = [];
    var mids = [];
    var vmarker;
    
    function initAutocomplete() {
        var uluru = {lat: <%=selectedD.trip.ilat%>, lng: <%=selectedD.trip.ilng%>}//{lat: 30.045915, lng: 31.22429};//
        map = new google.maps.Map(document.getElementById('map'), {
          center: uluru,
          zoom: 5,
          mapTypeId: 'roadmap'
        });
      
      
        
        //for me
        //initMap();
        
        var image1 = new google.maps.MarkerImage('destination1.png',
        new google.maps.Size(65, 124),
        new google.maps.Point(0,0),
        new google.maps.Point(56, 122)
    );
        var image2 = new google.maps.MarkerImage('destination2.png',
        new google.maps.Size(65, 124),
        new google.maps.Point(0,0),
        new google.maps.Point(56, 122)
    );
    
        var sharkimg = new google.maps.MarkerImage('smallblueshark.png',
        new google.maps.Size(65, 124),
        new google.maps.Point(0,0),
        new google.maps.Point(56, 122)
    );
//        new google.maps.Marker({
//              map: map,
//              icon: image1,
//              title: "Start Location",
//              position: {lat: <%=selectedD.trip.ilat%>, lng: <%=selectedD.trip.ilng%>}
//            });
//        new google.maps.Marker({
//              map: map,
//              icon: image2,
//              title: "Destination Location",
//              position: {lat: <%=selectedD.trip.dlat%>, lng: <%=selectedD.trip.dlng%>}
//            });
            
            vmarker = new google.maps.Marker({
              map: map,
              icon: sharkimg,
              title: "Vehicle Location",
              position: {lat: <%=selectedD.trip.ilat%>, lng: <%=selectedD.trip.ilng%>}
            });
            
            
            //for directions
            var directionsService = new google.maps.DirectionsService;
            var directionsDisplay = new google.maps.DirectionsRenderer;
            directionsDisplay.setMap(map);
            calculateAndDisplayRoute(directionsService, directionsDisplay);
            
            //show traffic
            var trafficLayer = new google.maps.TrafficLayer();
            trafficLayer.setMap(map);
      }
      
       function calculateAndDisplayRoute(directionsService, directionsDisplay) {
        directionsService.route({
          origin: {lat: <%=selectedD.trip.ilat%>, lng: <%=selectedD.trip.ilng%>},
          destination: {lat: <%=selectedD.trip.dlat%>, lng: <%=selectedD.trip.dlng%>},
          travelMode: 'DRIVING'
        }, function(response, status) {
          if (status === 'OK') {
            directionsDisplay.setDirections(response);
          } else {
            window.alert('Directions request failed due to ' + status);
          }
        });
      }
      
      
    </script>
    
    
                                                <!--speedometers-->
                                                <div class="col-md-12">
                                                    <div class="col-md-4">
                                                        <div id="gauge1" class="200x160px"></div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div id="gauge2" class="200x160px"></div>
                                                    </div>
                                                    <div class="col-md-4">
                                                        <div id="gauge3" class="200x160px"></div>
                                                    </div>
                                                </div>
                                                
                                                <br/>
                                                
<!--                                                <div class="col-md-12 panel-grids">
						<div class="panel panel-danger"> 
                                                    <div class="panel-heading"> 
                                                        <h3 class="panel-title">Trip Warning</h3> 
                                                    </div> 
                                                    <div class="panel-body"> There is no warning until now</div> 
                                                </div>
					</div>-->

    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMkIegihYnGDWqYZukBz2eo_InQOh-XEI&libraries=places&callback=initAutocomplete"
         async defer></script>

                                                                        
               <script src="https://www.gstatic.com/firebasejs/3.7.4/firebase.js"></script>
<script>
    
    
                                                              var gauge1 = new JustGage({
                                                                id: "gauge1",
                                                                value: 67,
                                                                min: 0,
                                                                max: 7000,
                                                                title: "RPM",
                                                                pointer: true,
                                                                pointerOptions: {
                                                                  toplength: -15,
                                                                  bottomlength: 10,
                                                                  bottomwidth: 12,
                                                                  color: '#8e8e93',
                                                                  stroke: '#ffffff',
                                                                  stroke_width: 3,
                                                                  stroke_linecap: 'round'
                                                                }
                                                              });
                                                              var gauge2 = new JustGage({
                                                                id: "gauge2",
                                                                value: 67,
                                                                min: 0,
                                                                max: 260,
                                                                title: "Speed",
                                                                pointer: true,
                                                                pointerOptions: {
                                                                  toplength: -15,
                                                                  bottomlength: 10,
                                                                  bottomwidth: 12,
                                                                  color: '#8e8e93',
                                                                  stroke: '#ffffff',
                                                                  stroke_width: 3,
                                                                  stroke_linecap: 'round'
                                                                }
                                                              });
                                                              var gauge3 = new JustGage({
                                                                id: "gauge3",
                                                                value: 67,
                                                                min: 0,
                                                                max: 100,
                                                                title: "Throttle",
                                                                pointer: true,
                                                                pointerOptions: {
                                                                  toplength: -15,
                                                                  bottomlength: 10,
                                                                  bottomwidth: 12,
                                                                  color: '#8e8e93',
                                                                  stroke: '#ffffff',
                                                                  stroke_width: 3,
                                                                  stroke_linecap: 'round'
                                                                }
                                                              });
                                                            
                                                            
                                                            // This function is called by jQuery once the page has finished loading.
                                                            $(document).ready(function(){
                                                                gauge1.init();
                                                                gauge2.init();
                                                                gauge3.init();
                                                            });
                                                            
                                                            ////////////////////////////////////////
    

                                                              var config = {
                                                                   apiKey: "AIzaSyDm82ItD0ET3--vv1k99xRq3-NvBFVUYnA",
                                                                    authDomain: "sharksmapandroid-158200.firebaseapp.com",
                                                                    databaseURL: "https://sharksmapandroid-158200.firebaseio.com"
                                                              };
                                                              firebase.initializeApp(config);

                                                              // Get a reference to the database service
                                                              var database = firebase.database();
                                                              
                                                              
                                                              //listen to gaugesss
                                                               var cRefg = database.ref('vehicles').child(<%=selectedD.id%>).child('RPM');
                                                                cRefg.on('value', function(snapshot) {
//                                                                    messageResults.value += '\n' + snapshot.val();
//                                                                    myGauge.setValue(snapshot.val());
                                                                      gauge1.refresh(snapshot.val());
                                                                });
                                                                var cRefg2 = database.ref('vehicles').child(<%=selectedD.id%>).child('Speed');
                                                                cRefg2.on('value', function(snapshot) {
//                                                                    messageResults.value += '\n' + snapshot.val();
//                                                                    myGauge2.setValue(snapshot.val());
                                                                      gauge2.refresh(snapshot.val());
                                                                });
                                                                var cRefg3 = database.ref('vehicles').child(<%=selectedD.id%>).child('Throttle');
                                                                cRefg3.on('value', function(snapshot) {
//                                                                    messageResults.value += '\n' + snapshot.val();
//                                                                    myGauge3.setValue(snapshot.val());
                                                                      gauge3.refresh(snapshot.val());
                                                                });
                                                                 var cRefg4 = database.ref('vehicles').child(<%=selectedD.id%>).child('Heading');
                                                                cRefg4.on('value', function(snapshot) {
//                                                                    messageResults.value += '\n' + snapshot.val();
//                                                                    myGauge4.setValue(snapshot.val());
                                                                });
                                                              
                                                              
                                                              
                                                              
                                                              
                                                              
                                                              
                                                              
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
         var cRef3 = database.ref('warning').child("femalesaftey");
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
         
         var vid="";
         var vid = <%=selectedD.vehicle.ID%>;
         //for moving vehicle
            var cRefv = database.ref('vehicles').child(vid);          
            cRefv.on('value', function(snapshot) {
                var vlat = snapshot.child("Latitude").val();
                var vlng = snapshot.child("Longitude").val();
                                                                        
                var newpos = {lat: vlat, lng: vlng};
                
                vmarker.setPosition(newpos);
            });
            
            
            
//            ///for chat
                var tid = "";
                tid = <%=selectedD.trip.trip_ID%>;
                                                        var chRef = database.ref('trips').child(tid).child('talk');
                                                        chRef.on('value', function(snapshot) {
                                                            document.getElementById("tripchatbox").innerHTML="";//3shn y7t mra w7da bs
                                                            snapshot.forEach(function(child){
                                                            var f = child.child("f").val();
                                                            var msg = child.child("msg").val();
                                                            
                                                            
                                                                    var div1 = document.createElement("div");
                                                                     div1.setAttribute("class", "activity-row activity-row1 activity-right");
                                                                     div1.innerHTML = "<div class=\"col-xs-3 activity-img\"><img src=\"images/1.png\" class=\"img-responsive\" alt=\"\"></div> "+
                                                                                     "<div class=\"col-xs-9 activity-img1\">"+
                                                                                     "<div class=\"activity-desc-sub\">"+
                                                                                     "<p>"+msg+"</p>"+
                                                                                     "</div>"+
                                                                                     "</div>"+
                                                                                     "<div class=\"clearfix\"> </div>";

                                                                     var div2 = document.createElement("div");
                                                                     div2.setAttribute("class", "activity-row activity-row1 activity-left");
                                                                     div2.innerHTML = "<div class=\"col-xs-9 activity-img2\">"+
                                                                                      "<div class=\"activity-desc-sub1\">"+
                                                                                      "<p>"+msg+"</p>"+
                                                                                      "</div>"+
                                                                                      "</div>"+
                                                                                      "<div class=\"col-xs-3 activity-img\"><img src=\"images/3.png\" class=\"img-responsive\" alt=\"\"></div>"+
                                                                                      "<div class=\"clearfix\"> </div>";

                                                                if(f=="p"){
                                                                     document.getElementById("tripchatbox").appendChild(div1);
                                                                 }
                                                                 else{
                                                                     document.getElementById("tripchatbox").appendChild(div2);
                                                                 }
                                                            });
                                                    
                                                        
                                                        });
//                                                        simpleNotify.notify('ss', 'warning');simpleNotify.notify('s', 'warning');simpleNotify.notify('ssss', 'warning');
//
//                                                        

////////////////////////////////////////////////////////
                                                        var wRef = database.ref('warning').child('femalesaftey');
                                                        wRef.on('value', function(snapshot) {
                                                            document.getElementById("warningssection").innerHTML="";//3shn y7t mra w7da bs
                                                            snapshot.forEach(function(child){
                                                                var wtid = child.child("tid").val();
                                                                var ts = child.key;
                                                                if(wtid==tid){
                                                                    var div = document.createElement("div");
                                                                    div.innerHTML = "<h3>There is a new Female Safety Warning !</h3>"+
                                                                     "<a href=\"${pageContext.request.contextPath}/FemaleServlet?goflag=showevent&id="+ts+"\"><span class=\"label label-primary\">   View Details  </span></a>";
                                                                    document.getElementById("warningssection").appendChild(div);
                                                                        

                                                                }
                                                            });
                                                        });





</script>        
                
                <!--end map part-->
                                                         
                                                         
                                                                                                        
                                                
                                                
                                                <br/>
                                                
                                                
                                                
                                                <div class="col-md-6 profile widget-shadow chat-mdl-grid" style="width:48%">
						<h4 class="title3">Conversation</h4>
						<div id="tripchatbox" class="scrollbar scrollbar1">
                                                    
                                                    
                                                    
<!--							<div class="activity-row activity-row1 activity-right">
								<div class="col-xs-3 activity-img"><img src="images/1.png" class="img-responsive" alt=""></div>
								<div class="col-xs-9 activity-img1">
									<div class="activity-desc-sub">
										<p>Hello ! Lorem ipsum dolor sit</p>
										<span>10:00 PM</span>
									</div>
								</div>
								<div class="clearfix"> </div>
							</div>
							<div class="activity-row activity-row1 activity-left">
								<div class="col-xs-9 activity-img2">
									<div class="activity-desc-sub1">
										<p>What about our next meeting?</p>
										<span class="right">9:55 PM</span>
									</div>
								</div>
								<div class="col-xs-3 activity-img"><img src="images/3.png" class="img-responsive" alt=""></div>
								<div class="clearfix"> </div>
							</div>-->
						</div>
					</div>

                                                
                                            <div class="col-md-6 profile widget-shadow" style="width:48%">
						<div class="activity_box activity_box2">
							<h4 class="title3">Warnings</h4>
							<div class="scrollbar scrollbar1">
								<div id="warningssection" class="single-bottom">
									
								</div>
							</div>
						</div>
					</div>
                                                
					<div class="clearfix"> </div>	


                                    

                                    
                                     </div>
                                    
                              
			</div>
		</div>
                                                 
                                              
<!--<br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/><br/><br/>
<br/><br/><br/><br/><br/><br/><br/><br/>-->
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
