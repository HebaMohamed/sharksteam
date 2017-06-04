<%-- 
    Document   : drouterestrictions
    Created on : Dec 12, 2016, 8:30:52 PM
    Author     : dell
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="myclassespackage.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Route Restrictions </title>
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
							<a href="${pageContext.request.contextPath}/ManageServlet?goflag=showpending" class="dropdown-toggle" aria-expanded="false"><i class="fa fa-bell"></i><span class="badge blue">1</span></a>
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
			<div class="main-page">
                            
                            <h3 class="title1">Route Restrictions</h3>
                            
                            <div class="row">
                                <div class="col-md-10">                 
                                    <%Driver d = (Driver)request.getAttribute("sDriver");%>
                            <h4>Driver ID : <%=Integer.toString(d.id)%></h4>
                            <h4>Driver Name : <%=d.name%></h4>
                                </div>
                                <div class="col-md-2">
                                    <h3><a href="${pageContext.request.contextPath}/RouteServlet?goflag=clear"><span class="btn btn-primary">Clear Pins</span></a></h3>
                                </div>
                                <br/>
                            </div>

                            
<% ArrayList<Double> glats = (ArrayList<Double>) request.getAttribute("glats"); %>
<% ArrayList<Double> glngs = (ArrayList<Double>) request.getAttribute("glngs"); %>


                                                                        <!--map part-->
                 <br/>
                 <input id="pac-input" style="width: 27%;" class="controls" type="text" placeholder="Search Box">
    <div id="map"></div>
    <script>
//      function initMap() {
//        var uluru = {lat: -25.363, lng: 131.044};
//        var map = new google.maps.Map(document.getElementById('map'), {
//          zoom: 11,
//          center: uluru
//        });
//        
//                
//        var image = new google.maps.MarkerImage('pin.png',
//        new google.maps.Size(65, 124),
//        new google.maps.Point(0,0),
//        new google.maps.Point(56, 122)
//    );
//        
//        var marker = new google.maps.Marker({
//          position: uluru,
//          map: map,
//          icon: image,
//          title:"Driver : Nashwa"
//        });
//        
//                
//        var marker2 = new google.maps.Marker({
//          position: {lat: -25.463, lng: 131.044},
//          map: map,
//          icon: image,
//          title:"Driver : Thomas"
//        });
//
//        var marker3 = new google.maps.Marker({
//          position: {lat: -25.465, lng: 131.148},
//          map: map,
//          icon: image,
//          title:"Driver : Youstina"
//        });
//        
//        var marker4 = new google.maps.Marker({
//          position: {lat: -25.461, lng: 131.245},
//          map: map,
//          icon: image,
//          title:"Driver : Heba"
//        });
//        
//        var marker5 = new google.maps.Marker({
//          position: {lat: -25.461, lng: 131.111},
//          map: map,
//          icon: image,
//          title:"Driver : Sarah"
//        });
//        
//        var marker6 = new google.maps.Marker({
//          position: {lat: -25.401, lng: 131.275},
//          map: map,
//          icon: image,
//          title:"Driver : Alaa"
//        });
//        
//        google.maps.event.addListener(marker, 'click', function() {
//        infowindow.open(map,marker);
//      });
//  }
      ////////////////////////////////////////////////////////////
    function initAutocomplete() {
        
        var uluru = {lat: <%=(Double)request.getAttribute("selectedlat")%>, lng: <%=(Double)request.getAttribute("selectedlng")%>}; //{lat: -33.8688, lng: 151.2195} //{lat: 37.334818, lng: -121.884886}

            
        var map = new google.maps.Map(document.getElementById('map'), {
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
        
        var image = new google.maps.MarkerImage('pin.png',
        new google.maps.Size(65, 124),
        new google.maps.Point(0,0),
        new google.maps.Point(56, 122)
    );
    
    
  
        var directionsService = new google.maps.DirectionsService();
        var directionsDisplay = new google.maps.DirectionsRenderer();
        
        directionsDisplay.setMap(map);
        
        map.addListener('click', function(e) {
    
        //placeMarker(e.latLng, map);
            
        new google.maps.Marker({
          position: e.latLng,
          map: map
        });
        
        var s1 = '${pageContext.request.contextPath}';
        var s2 = '/RouteServlet?goflag=clicked&lat=';
        var s3 = e.latLng.lat() + "";
        var s4 = '&lng=';
        var s5 = e.latLng.lng() + "";
        var s6 = '&selectedlat=' + map.getCenter().lat();
        var s7 = '&selectedlng=' + map.getCenter().lng();

       //window.location = s1.concat(s2).concat(s3).concat(s4).concat(s5);
       window.location = s1.concat(s2).concat(s3).concat(s4).concat(s5).concat(s6).concat(s7);
        
        //here navigate to the other url and search for string concatination
        
        
        //da kn s7
//    var start = new google.maps.LatLng(37.334818, -121.884886);
//    //var end = new google.maps.LatLng(38.334818, -181.884886);
//    var end = new google.maps.LatLng(37.441883, -122.143019);
//    var request = {
//      origin: start,
//      destination: end,
//      travelMode: google.maps.TravelMode.DRIVING
//    };
//    directionsService.route(request, function(response, status) {
//      if (status == google.maps.DirectionsStatus.OK) {
//        directionsDisplay.setDirections(response);
//        directionsDisplay.setMap(map);
//      } else {
//        alert("Directions Request from " + start.toUrlValue(6) + " to " + end.toUrlValue(6) + " failed: " + status);
//      }
//    });






});


        ////////////////////////list routes
        
        var arr = [];
<% for(int i = 0; i < glats.size(); i+=1) { %>
//        var uluru = {lat: <%=glats.get(i) %>, lng: <%=glngs.get(i) %>};    
//    new google.maps.Marker({
//          position: uluru,
//          map: map,
//          title:""
//        });

        var uluru = {lat: <%=glats.get(i) %>, lng: <%=glngs.get(i) %>};
        arr.push(uluru);

<% } %>
        var flightPath = new google.maps.Polyline({
          path: arr,
          geodesic: true,
          strokeColor: '#FF0000',
          strokeOpacity: 1.0,
          strokeWeight: 5
        });
        flightPath.setMap(map);
//    //add marker to the last pin
    
        <% if(glats.size() != 0) {%>;
        new google.maps.Marker({
          position: {lat: <%=glats.get(glats.size()-1) %>, lng: <%=glngs.get(glngs.size()-1) %>},
          map: map,
          title:"Last Pin"
        });
        
          <%}%>
        
        
        
      }
      
      
      
        
      
    </script>
<!--    <script async defer
        src="https://maps.googleapis.com/maps/api/js?key=AIzaSyATc18NoAmLoEZFU9gIbIb8uGpXEbLoTDk&callback=initMap">
    </script>-->

    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBMkIegihYnGDWqYZukBz2eo_InQOh-XEI&libraries=places&callback=initAutocomplete"
         async defer></script>
                
                <!--end map part-->
                
                
                
                <br/>
                    <h3><a href="${pageContext.request.contextPath}/RouteServlet?goflag=save"><span class="btn btn-primary">Save Route</span></a></h3>
                <br/>

                
                
                            
                            
                            
                            
                            
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

