<%@page import="modelos.Usuarios"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%
 Usuarios us = (Usuarios) session.getAttribute("usuario");


%>


<!DOCTYPE html>
<html:html lang="true">

    <head>
        <title>Lazarus</title>
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
                        <a href="index.html" class="logo"><strong>Bienvenido</strong><%=us.getIdUsuario()%></a>
                        <ul class="icons">
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
                            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                            <li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
                        </ul>
                    </header>

                    <section>
                        <div class="12u$" id="registro">
                            <h2>Completa tus datos<strong> Solo será una vez</strong></h2>
                            <html:form action="UpdateUser.do" method="POST">
                                      
                                <div class="row uniform">
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="nif" id="nif" value="" placeholder="NIF/CIF" />
                                    </div>
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="razonSocial" id="razonSocial" value="" placeholder="Razón Social" />
                                    </div>
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="nickName" id="nickName" value="" placeholder=<%=us.getNickName()%> />
                                    </div>
                                    <div class="12u$">
                                        <input type="text" name="direccion" id="direccion" value="" placeholder="Direccion" />
                                    </div>
                                    <div class="12u$">
                                        <input type="text" name="telefono" id="telefono" value="" placeholder="Teléfono" />
                                    </div>
                                    <div class="12u$">
                                        <input type="text" name="codigoPostal" id="codigoPostal" value="" placeholder="Código postal" />
                                    </div>
                                    <!-- Break -->
                                    <div class="8u 12u$(small)">
                                        <input type="checkbox" id="demo-copy" name="condiciones">
                                        <label for="demo-copy">Accepto las condiciones de uso. <a href="#">Condiciones</a></label>
                                    </div>
                                    <!-- Break -->
                                    <div class="12u$">
                                        <ul class="actions">
                                            <li><input type="submit" value="Guardar los datos" class="special" /></li>

                                        </ul>
                                    </div>
                                </div>
                            </html:form>
                        </div>   

                    </section>
               
                </div>
            </div>

            <!-- Sidebar -->
            <div id="sidebar">
                <div class="inner">

                    <!-- Search -->
                

                    <!-- Menu -->
                    <nav id="menu">
                        <header class="major">
                            <h2>Menu</h2>
                        </header>
                        <ul>
                            <li><a href="principal.jsp">Inicio</a></li>
                            <li><a href="Facturacion.jsp">Facturar</a></li>
                            <li><a href="generic.html">Presupuesto</a></li>
                            <li><a href="elements.html">Gastos</a></li>
                            <li>
                                <span class="opener">Submenu</span>
                                <ul>
                                    <li><a href="#">Lorem Dolor</a></li>
                                    <li><a href="#">Ipsum Adipiscing</a></li>
                                    <li><a href="#">Tempus Magna</a></li>
                                    <li><a href="#">Feugiat Veroeros</a></li>
                                </ul>
                            </li>

                        </ul>
                    </nav>

                    <!-- Section -->

                    <!-- Section -->
                    <section>
                        <header class="major">
                            <h2>Get in touch</h2>
                        </header>
                        <p>Sed varius enim lorem ullamcorper dolore aliquam aenean ornare velit lacus, ac varius enim lorem ullamcorper dolore. Proin sed aliquam facilisis ante interdum. Sed nulla amet lorem feugiat tempus aliquam.</p>
                        <ul class="contact">
                            <li class="fa-envelope-o"><a href="#">information@untitled.tld</a></li>
                            <li class="fa-phone">(000) 000-0000</li>
                            <li class="fa-home">1234 Somewhere Road #8254<br />
                                Nashville, TN 00000-0000</li>
                        </ul>
                    </section>

                    <!-- Footer -->
                    <footer id="footer">
                        <p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
                    </footer>

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

