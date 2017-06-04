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
<title>Trips</title>
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
                            
                            <h3 class="title1">Trips</h3>
                            
                            <% ArrayList<Trip> trips = (ArrayList<Trip>) request.getAttribute("trips"); %>
                            
                    <div class="col-md-12 widget-shadow">
                            <div class="row">
                                        <div class="col-md-6 widget states-mdl" style="width: 45%">
						<div class="stats-left ">
							<h5>Today</h5>
							<h4>Trips</h4>
						</div>
						<div class="stats-right">
							<label><%=(String)request.getAttribute("todaycount")%></label>
						</div>
						<div class="clearfix"> </div>	
					</div>
                                                 <div class="col-md-6 widget states-last" style="width: 45%">
                                                     <div class="stats-left " >
							<h5>Today</h5>
							<h4>Earnings</h4>
                                                        </div>
                                                        <div class="stats-right">
                                                                <label><%=(String)request.getAttribute("todayearnings")%></label>
                                                        </div>
                                                        <div class="clearfix"> </div>	
                                                </div>
                                                        <br/>
                                                </div>
                                                        <br/>
                                                            <input id="pac-input" class="controls" type="text" placeholder="Search Box">
                                                         <div id="map"></div>
                                                             <script>

    var map;//3shn yb2a pub
      
    var selecteddtogo = "";
    
    var marr = [];
    var mids = [];
    
    function initAutocomplete() {
        var uluru = {lat: 30.045915, lng: 31.22429};//
        map = new google.maps.Map(document.getElementById('map'), {
          center: uluru,
          zoom: 14,
          mapTypeId: 'roadmap'
        });
      
        // Create the search box and link it to the UI element.
        var input = document.getElementById('pac-input');
        var searchBox = new google.maps.places.SearchBox(input);
        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

        // Bias the SearchBox results towards current map's viewport.
        map.addListener('bounds_changed', function() {
          searchBox.setBounds(map.getBounds());
        });

        var markers = [];
        // Listen for the event fired when the user selects a prediction and retrieve
        // more details for that place.
        searchBox.addListener('places_changed', function() {
          var places = searchBox.getPlaces();

          if (places.length == 0) {
            return;
          }

          // Clear out the old markers.
          markers.forEach(function(marker) {
            marker.setMap(null);
          });
          markers = [];

          // For each place, get the icon, name and location.
          var bounds = new google.maps.LatLngBounds();
          places.forEach(function(place) {
            if (!place.geometry) {
              console.log("Returned place contains no geometry");
              return;
            }
            var icon = {
              url: place.icon,
              size: new google.maps.Size(71, 71),
              origin: new google.maps.Point(0, 0),
              anchor: new google.maps.Point(17, 34),
              scaledSize: new google.maps.Size(25, 25)
            };

            // Create a marker for each place.
            markers.push(new google.maps.Marker({
              map: map,
              icon: icon,
              title: place.name,
              position: place.geometry.location
            }));

            if (place.geometry.viewport) {
              // Only geocodes have viewport.
              bounds.union(place.geometry.viewport);
            } else {
              bounds.extend(place.geometry.location);
            }
          });
          map.fitBounds(bounds);
        });
        
        
        //for me
        //initMap();
        
        var image = new google.maps.MarkerImage('personwalking.png',
        new google.maps.Size(65, 124),
        new google.maps.Point(0,0),
        new google.maps.Point(56, 122)
    );
    
<% for(int i = 0; i < trips.size(); i+=1) { %>
        
        new google.maps.Marker({
              map: map,
              icon: image,
              title: "Pickup",
              position: {lat: <%=trips.get(i).lattitude%>, lng: <%=trips.get(i).longtude%>}
            });
        
<% } %>
    
    
        
        
      }
      
      
      
    </script>
    
    
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMkIegihYnGDWqYZukBz2eo_InQOh-XEI&libraries=places&callback=initAutocomplete"
         async defer></script>
                
                <!--end map part-->
                                                         
                                                         
                                                         
                                                
                                                
                                                <br/>
                            
                            <ul class="nav navbar-nav"> 
				<li class="dropdown"> <a href="#" id="navbarDrop1" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">Search By Date<span class="caret"></span></a> 
					<ul class="dropdown-menu" aria-labelledby="navbarDrop1"> 
                                            <li class=""><a href="${pageContext.request.contextPath}/TripServlet?goflag=today">Today</a></li> 
                                            <li class=""><a href="${pageContext.request.contextPath}/TripServlet?goflag=lastweek">Last Week</a></li> 	
                                            <li class=""><a href="${pageContext.request.contextPath}/TripServlet?goflag=lastmonth">Last Month</a></li> 
                                            <li class=""><a href="${pageContext.request.contextPath}/TripServlet?goflag=lastyear">Last Year</a></li> 
                                            <li class=""><a href="${pageContext.request.contextPath}/TripServlet?goflag=all">All Trips</a></li> 
                                        </ul> 
				</li> 
										
                            </ul>

                    </div>
                                    
					<div class="col-md-12 stats-info widget-shadow">
						<table class="table stats-table ">
							<thead>
								<tr>
									<th>T.ID</th>
                                                                        <th>Pathway Map</th>
                                                                        <th>From-Address</th>
                                                                        <th>To-Address</th>
									<th>Start</th>
                                                                        <th>End</th>
									<th>Price</th>
                                                                        <th>Ratting</th>
                                                                        <th>Comment</th>
									<th>Options</th>
								</tr>
							</thead>
							<tbody>
                                                           
                                                            <% String deletef = "0"; %>
                                                            <%double total = 0;%>
                                                                <% for(int i = 0; i < trips.size(); i+=1) { %>
                                                                
                                                                <%//calculate ratting 
                                                                 
                                                                    String label = "";
                                                                    String stars = "";
                                                                    
                                                                    if(trips.get(i).rating==1){
                                                                        label = "label label-danger";
                                                                        stars = "★";
                                                                    }
                                                                    else if(trips.get(i).rating==2){
                                                                        label = "label label-info";
                                                                        stars = "★★";
                                                                    }
                                                                    else if(trips.get(i).rating==3){
                                                                        label = "label label-warning";
                                                                        stars = "★★★";
                                                                    }
                                                                    else if(trips.get(i).rating==4){
                                                                        label = "label label-success";
                                                                        stars = "★★★★";
                                                                    }
                                                                    else if(trips.get(i).rating==5){
                                                                        label = "label label-success";
                                                                        stars = "★★★★★";
                                                                    }
                                                                
                                                                %>
                                                                
                                                            
								<tr>
                                                                        <th><%=trips.get(i).trip_ID %></th>
                                                                        <%if(!trips.get(i).staticmapurl.equals("")){%>
                                                                        <th><img src="<%=trips.get(i).staticmapurl %>" /></th>
                                                                        <%} else {%>
                                                                        <th>Not Moved</th>
                                                                        <%}%>
                                                                        <th><%=trips.get(i).from_addr %></th>
                                                                        <th><%=trips.get(i).to_addr %></th>
                                                                        <th><%=trips.get(i).getStartdate() %></th>
                                                                        <th><%=trips.get(i).getEnddate() %></th>
                                                                        <th><%=trips.get(i).price %>$</th>
                                                                        <th><span class="<%=label %>"><%=stars %></span></th>
                                                                        <th><%=trips.get(i).comment %></th>
                                                                        
                                                                        <th>
                                                                            <!--<a href="${pageContext.request.contextPath}/ManageServlet?goflag=showdriver&id=<%=trips.get(i).d.id  %>"><span class="label label-primary">View Driver</span></a>-->
                                                                            <a href="${pageContext.request.contextPath}/TripServlet?goflag=showtrip&tid=<%=trips.get(i).trip_ID  %>"><span class="label label-primary">View Details</span></a>

                                                                           </th>

                                                                           <%total+=trips.get(i).price;%>
                                                                </tr>
							     <% } %>
                                                             
							</tbody>
						</table>
                                                             <div class="row">
                                                                 <div class="col-md-10"></div>
                                                                 <div class="col-md-2"
                                                                       <h2>Total : <%=total%>$</h2>
                                                                 </div>
                                                             </div>
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
