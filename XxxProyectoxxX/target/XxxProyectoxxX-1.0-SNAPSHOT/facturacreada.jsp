<%-- 
    Document   : principal
    Created on : 01-abr-2017, 20:19:35
    Author     : Pedro DLF
--%>
<%@page import="logica.GeneradorFacturas"%>
<%@page import="modelos.Usuarios"%>
<%
    Usuarios usuario = (Usuarios) session.getAttribute("usuario");
    /*GeneradorFacturas g = new GeneradorFacturas();
    String nuevoID = String.valueOf(usuario.getIdUsuario());
    usuario.setIdUsuario(Integer.parseInt(g.quitarbarra(nuevoID)));
    */
    HttpSession s = request.getSession();
    
    //s.setAttribute("usuario", usuario);

%>
<%!
    private String selecionarFacturacion(HttpSession sesion) {
        Usuarios usuario = (Usuarios) sesion.getAttribute("usuario");
        String url = "";

        if (usuario != null) {

            if (usuario.getRazonSocial() != null) {
                url = "facturar.jsp";
            } else {
                url = "completarDatosUsuario.jsp";
            }
        }else{ url= "index.jsp";}
        return url;
    }

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Lazarus <%=usuario.getNickName()%></title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <link rel="stylesheet" href="assets/css/main.css" />
    </head>
    <body>

        <div id="wrapper">

            <div id="main">
                <div class="inner">

                    <header id="header">
                        <a href="index.html" class="logo"><strong>Bienvenido </strong><%=usuario.getNickName()%></a>
                        <ul class="icons">
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
                            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                            <li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
                        </ul>
                    </header>

                    <section>
                       
                        <div><h2>Factura creada!</h2></div>
                        
                        
                    </section>
                </div>
            </div>
            <div id="sidebar">
                <div class="inner">
                    <nav id="menu">
                        <header class="major">
                            <h2>Menu</h2>
                        </header>
                        <ul>

                            <li><a href="principal.jsp">Inicio</a></li>
                            <li><a href=<%=selecionarFacturacion(s)%>>Facturar</a></li>
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
</html>
