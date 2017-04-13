<%-- 
    Document   : facturar
    Created on : 02-abr-2017, 19:57:49
    Author     : Pedro DLF
--%>
<%@page import="modelos.Conceptos"%>
<%@page import="modelos.Clientes"%>
<%@page import="java.util.List"%>
<%@page import="dbm.DBManager"%>
<%@page import="logica.GeneradorFacturas"%>
<%@page import="java.util.HashMap"%>
<%@page import="modelos.Usuarios"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="javax.servlet.jsp.JspWriter"%>

<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>



<%
    Usuarios us = (Usuarios) session.getAttribute("usuario");
    Clientes cliente = (Clientes) session.getAttribute("ncliente");
    String cli;
    String cId;
    String usuario = us.getNickName();
    System.out.println("session " + session.getId());
    GeneradorFacturas generador = new GeneradorFacturas();
    DBManager db = new DBManager();
    String fecha = generador.generarFecha();
    String numerofactura = db.cargarnumerofactura(us);
    session.setAttribute("nfactura", numerofactura);
    String fechaMod = "Vencimiento";
    boolean tieneconceptos = false;
    boolean tieneProductos = false;
    boolean tieneClientes = false;
    List<Clientes> listadeClientes = db.recuperarClientes(us); //db.cargarClientesUsuario(usuario);
    List<Conceptos> listaproductos = db.recuperarConceptos(us);//db.cargarConceptosUsuario(usuario);
   

    if (listaproductos != null) {
        tieneProductos = true;
    }
    
    if (listadeClientes != null) {
        tieneClientes = true;
    }
    if (cliente == null) {
        cli = "-Seleciona un Cliente-";
        cId = "";
    } else {
        cli = cliente.getRazonSocial();
        cId = String.valueOf(cliente.getIdCliente());
    }
    session.setAttribute("cli", cli);
    session.setAttribute("cId", cId);

%>
<!DOCTYPE html>
 <html:html lang="true">
    <head>
        <title>Presupuestos</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
        <script src="//code.jquery.com/jquery-latest.js"></script>
        <link rel="stylesheet" href="assets/css/main.css" />

    </head>
    <body>

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Main -->
            <div id="main">
                <div class="inner">

                    <!-- Header -->
                    <header id="header">
                        <a href="principal.jsp" class="logo"><strong>Presupuestos</strong> Usuario <%=usuario%></a>
                        <ul class="icons">
                            <li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
                            <li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
                            <li><a href="#" class="icon fa-snapchat-ghost"><span class="label">Snapchat</span></a></li>
                            <li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
                            <li><a href="#" class="icon fa-medium"><span class="label">Medium</span></a></li>
                        </ul>
                    </header>

                    <!-- Content -->

                    <section class="formularioFacP" id="formularioFacP">
                        <h1>Presupuestos</h1>
                        <html:form action="Billing.do" method="POST">
                            <div class="row uniform">
                                <div class="6u 12u$(small)">
                                    <h5 id="nftxt">Fecha </h5>
                                    <input type="text" name="fechapre" id="fechapre" value="<%=fecha%>" placeholder=<%=fecha%> />
                                </div>

                                <div class="6u 12u$(small)">
                                    <h5 id="nftxt">Nº de Presupuestos</h5>
                                    <input type="text" name="numeropre" id="numeropre" value=<%=numerofactura%> placeholder=<%=numerofactura%> />
                                </div>

                                <div class="6u 12u$(small)">
                                    <input type="text" name="presupuesto-vencimiento" id="presupuesto-vencimiento" value="" placeholder=<%=fechaMod%> />
                                </div>

                                <div class="6u 12u$(small)">
                                    <div class="select-wrapper1" id="combo-vencimiento">

                                        <select name="combo-vencimiento" size="1" onChange="mostrarValor(this.value)">
                                            <option value="1" selected>- Sin Vencimiento -</option>
                                            <option value="2">- 15 Dias -</option>
                                            <option value="3">- 30 Dias -</option>
                                            <option value="4">- 60 Dias -</option>
                                            <option value="5">- 90 Dias-</option>

                                        </select>

                                    </div>
                                </div>

                                <!-- Break -->

                                <div class="12u$">
                                    <div class="select-wrapper" >
                                        <select name="combo-Cliente" id="combo-Cliente" >
                                            <option value=<%=cId%> selected><%=cli%></option>
                                            <%if (tieneClientes) {
                                                    generador.completarSelect(out, listadeClientes);
                                                }%>
                                        </select>
                                    </div>
                                </div>

                                <div class="12u$">
                                    <button type="button" onclick="mostrarElemento('regi')">Nuevo cliente</button>
                                </div> 

                                <!-- TABLA -->
                                <div id="tablaProductos"></div>
                            
                       </html:form>
                    </section>
                    <!-- Content -->

                    <section id="regi" class="regi" style="display:none"> 
                        <div class="12u$" id="regis" >
                            <h3 class="titnc">Cada cliente quedara registrado para futuras operaciones</h3>
                            <html:form action="CrearCliente.do" method="POST">
                                <div class="row uniform">
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="cif" id="cifc" value="" placeholder="NIF/CIF" />
                                    </div>
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="razonSocial" id="razonSocialc" value="" placeholder="Razón Social" />
                                    </div>
                                    <div class="6u 12u$(xsmall)">
                                        <input type="text" name="mail" id="mailc" value="" placeholder="Email"/>
                                    </div>
                                    <div class="12u$">
                                        <input type="text" name="direccion" id="direccionc" value="" placeholder="Direccion" />
                                    </div>
                                    <div class="12u$">
                                        <input type="text" name="telefono" id="telefonoc" value="" placeholder="Teléfono" />
                                    </div>
                                    <div class="12u$">
                                        <input type="text" name="cp" id="cpc" value="" placeholder="Código postal" />
                                    </div>
                                    <div class="12u$" style="display:none">
                                        <input type="text" name="nickName" id="nickNamec" value=<%=generador.quitarbarra(String.valueOf(us.getIdUsuario()))%>/>
                                    </div>
                                    
                                    <!-- Break -->
                                    <div class="12u$">
                                        <ul class="actions" id="botongnc">
                                            <li><input type="submit" name="guardar" value="Guardar los datos" class="special" /></li>
                                            <li><button type="button" onclick="ocultarElemento('regi')">cancelar</button></li>
                                        </ul>
                                    </div>
                                </div>
                            </html:form>
                        </div>   

                    </section> 
                    <section id="regicon" class="regicon">
                        <div class="12u$">
                            <h4>Añadir un concepto</h4>
                            <html:form  action="CrearConcepto.do" method="POST" >
                                <div class="row uniform">  
                                    <%if (tieneProductos) {%>  
                                    <div class="12u$" id="select-clientes">
                                        <div class="select-wrapper">
                                            <select id="selectconcepto" >
                                                <option value="" selected>-Seleciona un Concepto-</option>
                                                <%generador.completarSelectConceptos(out, listaproductos);%>
                                            </select>
                                        </div>

                                    </div>
                                    <%}%> 
                                    <div id='pppp' class="row uniform" >
                                        <div class="6u 12u$(small)">
                                            
                                            <input type="text" name="nombreConcepto" id="nombreConcepto" value="" placeholder="-Concepto-"/>
                                        </div>
                                        <div class="2u 12u$(xsmall)">
                                          
                                            <input type="text" name="cantidad" id="cantidadpr" value="" placeholder="Unidades" />
                                        </div>
                                        <div class="2u 12u$(xsmall)">
                                           
                                            <input type="text" name="base" id="basepr" value="" placeholder="Base" />
                                        </div>
                                        <div class="2u 12u$(xsmall)">
                                       
                                            <input type="text" name="iva" id="ivapro" value="" placeholder="IVA%" />
                                        </div>
                                        <div class="12u$" id="txtdes" style="display:none">
                                            <textarea name="descripcion" id="descripccionpro" placeholder="Añade la descripcion del producto" rows="6"></textarea>
                                        </div>    
                                    </div>    
                                    <div class="2u 12u$(xsmall)" style="display:none">
                                        <input type="text" name="numeroFactura" id="numeroFacturapro" value=<%=Integer.parseInt(numerofactura)%>/>
                                    </div>
                                    <div class="2u 12u$(xsmall)" style="display:none">
                                        <input type="text" name="nickName" id="nickNamepro" value=<%=generador.quitarbarra(String.valueOf(us.getIdUsuario()))%>/>
                                    </div>


                                </div>

                                <div class="12u$" id="botfactura">
                                    <ul class="actions">
                                        <li><input type="button" id="aniadircon" value="Añadir Concepto" class="special" /></li>
                                        <li><button type="button" id="addDesc" onclick="mostrarElemento('txtdes'), mostrarElemento('canDesc')">descripcion</button></li>
                                        <li><button type="button" id="canDesc" onclick="ocultarElemento('txtdes'), ocultarElemento('canDesc')" style="display:none">Cancelar</button></li>
                               
                                    </ul>
                                    <div id="tablaProductos"></div>
                                </div>
                            </html:form>


                    </section>            
                    <!-- Break -->


                </div>
            </div>
            <!-- Break -->

            <!-- Sidebar -->
            <div id="sidebar">
                <div class="inner">

                    <!-- Search -->
                    <section id="search" class="alt">
                        <form method="post" action="#">
                            <input type="text" name="query" id="query" placeholder="Search" />
                        </form>
                    </section>

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
        <script src="assets/js/visuales.js" type="text/javascript"></script>
    </body>

</html:html>

