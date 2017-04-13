<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
    String error = (String)session.getAttribute("error");

%> 
<html:html lang="true">
    <head>
        <title>PiedPieper</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="assets/css/main.css" />
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
    </head>
    <body>

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Main -->
            <div id="main">
                <div class="inner">

                    <!-- Header -->
                    <header id="header">
                        <a href="index.jsp" class="logo"><strong>Pied</strong>Pieper</a>
                        <ul class="icons">
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
                            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                            <li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
                        </ul>
                    </header>

                    <!-- Banner -->
                    <section class="banner">
                        <div class="12u$" id="logreg">

                            <html:form action="Log.do" method="POST">
                                <div class="12u$">
                                    <div class="8u$" id="actions-lr">  
                                        <input type="email" name="nickName" id="nickName" value="" placeholder="Email" />
                                        <input type="password" name="password" id="password" value="" placeholder="Password" />
                                    </div>
                                    <%if (error != null) {%>
                                    <div class="12u$" id="errorlog"> <%=error%> </div>
                                    <%}%>
                                    <!-- Break -->
                                    <div class="12u$">
                                        <ul class="actions" id="action-logreg">
                                            <li><input name="registro" type="submit" value="Registrarme" class="special" /></li>
                                            <li><input name="registrado" type="submit" value="Ya estoy registrado" class="special"/></li>
                                        </ul>
                                    </div>
                                </div>
                            </html:form>
                        </div> 
                    </section> 
                </div>
            </div>




        </div>
    </div>

</div>

<!-- Scripts -->
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
<script src="assets/js/main.js"></script>

</body>
</html:html>
