
<%-- 
    Document   : index
    Created on : Oct 14, 2016, 12:50:17 AM
    Author     : dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="myclassespackage.*"%>
<!DOCTYPE html>
<html class="full" lang="en">
<!-- Make sure the <html> tag is set to the .full CSS class. Change the background image in the full.css file. -->

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Sharks</title>

    <!-- Bootstrap Core CSS -->
    <link href="indexfolder/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="indexfolder/css/the-big-picture.css" rel="stylesheet">

</head>

<body>

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-bottom" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Sharks</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="${pageContext.request.contextPath}/LoginServlet">Login</a>
                    </li>
                    <li>
                        <a href="${pageContext.request.contextPath}/SignUpServlet">Signup</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        <div class="row">
            <div class="col-md-6 col-sm-12">
<!--                <h1>Sharks</h1>
                <p>Transportation Quality Assurance </p>
                <p>Putting New Standards for the World’s Transportation Quality</p>-->
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
<!--defeR-->
    <!-- jQuery -->
    <script src="indexfolder/js/jquery.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="indexfolder/js/bootstrap.min.js"></script>

</body>

</html>
