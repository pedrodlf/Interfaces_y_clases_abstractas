
<%@page import="modelos.Conceptos"%>
<%@page import="java.util.List"%>
<%@page import="dbm.DBManager"%>
<%@page import="logica.GeneradorFacturas"%>
<%@page import="modelos.Usuarios"%>
<%@page import="modelos.Clientes"%>
<%
String nfac = (String) session.getAttribute("nfac");
String fecha = (String)session.getAttribute("fecha");
String vencimiento = (String)session.getAttribute("vencimiento");
Clientes c = (Clientes) session.getAttribute("cliente");
Usuarios user = (Usuarios) session.getAttribute("usuario");
GeneradorFacturas g = new GeneradorFacturas();
DBManager db = new DBManager();
List<Conceptos> lista = db.recuperarconceptosdeFactura(nfac, user.getIdUsuario());


%>






<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Factura <%=nfac%></title>
    <link rel="stylesheet" href="style.css" media="all" />
  </head>
  <body>
    <header class="clearfix">
      <div id="logo">
        <img src="logo.png">
      </div>
      <div id="company">
          <h2 class="name"><%=user.getRazonSocial()%></h2>
          <div><%=user.getDireccion()%></div>
          <div><%=user.getTelefono()%></div>
          <div><a href="<%=user.getNickName()%>"><%=user.getNickName()%></a></div>
      </div>
      </div>
    </header>
    <main>
      <div id="details" class="clearfix">
        <div id="client">
          <div class="to">INVOICE TO:</div>
          <h2 class="name"><%=c.getRazonSocial()%></h2>
          <div class="address"><%=c.getDireccion()%></div>
          <div class="email"><a href="<%=c.getMail()%>"><%=c.getMail()%></a></div>
        </div>
        <div id="invoice">
            <h1>Factura <%=nfac%></h1>
            <div class="date">Fecha de Factura: <%=fecha%></div>
            <div class="date">Vencimiento: <%=vencimiento%></div>
        </div>
      </div>
      <table border="0" cellspacing="0" cellpadding="0">
        <thead>
          <tr>
            <th class="no">#</th>
            <th class="desc">CONCEPTO</th>
            <th class="qty">DESCRIPCION</th>
            <th class="qty">CANTIDAD</th>
            <th class="unit">PRECIO/U</th>
            <th class="qty">IVA%</th>
            <th class="qty">BASE</th>
            <th class="qty">IVA</th>
            <th class="total">SUBTOTAL</th>
          </tr>
        </thead>
        <tbody>
          
            <%g.completartabla(out, lista);%>
       
        </tbody>
        <tfoot>
            <%g.totalestabla(out);%>
        </tfoot>
      </table>
      <div id="thanks">Thank you!</div>
      <div id="notices">
        <div>NOTICE:</div>
        <div class="notice">A finance charge of 1.5% will be made on unpaid balances after 30 days.</div>
      </div>
    </main>
    <footer>
      Invoice was created on a computer and is valid without the signature and seal.
    </footer>
  </body>
</ht