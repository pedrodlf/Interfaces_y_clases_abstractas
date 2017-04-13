package logica;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspWriter;
import modelos.Clientes;
import modelos.Conceptos;
import modelos.Usuarios;

public class GeneradorFacturas {

    DecimalFormat f = new DecimalFormat("######.00");
    double basetotal = 0.00;
    double ivatotal = 0.00;
    double subtotaltotal = 0.00;

    public String calcularIva(String base, String iva) {
        String ivaCal = "";
        base = cambiarComaporPunto(base);
        iva = cambiarComaporPunto(iva);

        double b = Double.parseDouble(base);
        int i = Integer.parseInt(iva);
        double resultado = b * i / 100;
        ivaCal = f.format(resultado);

        return ivaCal;
    }

    public String calcularBase(String base, int cantidad, String descuento) {
        System.out.println("calculando base...");
        String bt = "";
        base = cambiarComaporPunto(base);
        System.out.println(base);
        System.out.println(cantidad);
        descuento = cambiarComaporPunto(descuento);
        System.out.println(descuento);
        try {
            
            double btn = Double.parseDouble(base) * cantidad;
            System.out.println(btn);
            bt = f.format(btn);
            System.out.println(bt);
            try {
                double i = (Double.parseDouble(descuento));
                if (i > 0) {
                    btn = btn * i / 100;
                    bt = f.format(btn);

                }

            } catch (Exception e) {
                System.out.println("Sin descuento");
            }
        } catch (Exception e) {
            System.out.println("ERROR calculando la base ="+e.getMessage());
            bt = base;
        }

        return bt;
    }

    public String[] guardarDatosConcepto(Conceptos concepto) {
        String[] datos = new String[3];
        datos[0] = concepto.getNombreConcepto();
        datos[1] = concepto.getBase();
        datos[2] = concepto.getIva();

        return datos;
    }

    public String generarFecha() {
        String fecha = null;
        Calendar c = new GregorianCalendar();
        Date fe = c.getTime();
        c.setTime(fe);
        c.add(Calendar.MONTH, 1);
        String dia, mes, year;

        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        year = Integer.toString(c.get(Calendar.YEAR));
        if (dia.length() < 2) {
            dia = "0" + dia;
        }
        if (mes.length() < 2) {
            mes = "0" + mes;
        }

        fecha = dia + "/" + mes + "/" + year;

        return fecha;
    }

    public String generarfechamodificada(int diasAgregados) {
        String f = null;
        String dia, mes, year;
        Calendar c = new GregorianCalendar();
        Date fecha = c.getTime();
        c.setTime(fecha);
        System.out.println(fecha);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_YEAR, diasAgregados);
        fecha = c.getTime();
        System.out.println(fecha);

        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH));
        year = Integer.toString(c.get(Calendar.YEAR));
        if (dia.length() < 2) {
            dia = "0" + dia;
        }
        if (mes.length() < 2) {
            mes = "0" + mes;
        }

        f = dia + "/" + mes + "/" + year;
        return f;
    }

    public String calcularTotalConcepto(String base, String ivaCalculado) {
        String total = "";
        base = cambiarComaporPunto(base);
        ivaCalculado = cambiarComaporPunto(ivaCalculado);
        double bas = Double.parseDouble(base);
        double iv = Double.parseDouble(ivaCalculado);
        total = f.format(bas + iv);

        return total;
    }

    public String cambiarComaporPunto(String cadena) {
        String c = cadena.replace(',', '.');

        return c;
    }

    public String cambiarEspacioPorBarra(String s) {
        String c = s.replace(" ", "_");

        return c;
    }
      public String quitarbarra(String s) {
        String c = s.replace("/", " ");
        c = c.trim();

        return c;
    }
    
      public String formatearTexto(String s){
      String c = "";
      c = s.replace(".EUR", " ");
      c = s.replace("%", " ");
      c = c.trim();
      c= cambiarEspacioPorBarra(s);
      return c;
      }
      

    public void completarSelect(JspWriter out, List<Clientes> lista) {
       
        int numeroClientes = lista.size();
        try {
            for (int i = 0; i < numeroClientes;) {
                if (i <= numeroClientes) {
                    out.println("<option value=\"" + lista.get(i).getIdCliente() + "\" >" + lista.get(i).getRazonSocial() + "</option>");
                    i++;
                }
            }
        } catch (java.io.IOException e1) {
            System.out.println(e1);
        }
    }
    
     public void completarSelectConceptos(JspWriter out, List<Conceptos> lista) {
        
        int numeroClientes = lista.size();
        try {
            for (int i = 0; i < numeroClientes;) {
                if (i <= numeroClientes) {
                   
                    out.println("<option value=\"" + lista.get(i).getIdConcepto() + "\" >" + lista.get(i).getNombreConcepto() + "</option>");
                    i++;
                }
            }
        } catch (java.io.IOException e1) {
            System.out.println(e1);
        }
    }

    public void completartabla(JspWriter out, List<Conceptos> lista) {
        System.out.println("Pintando tabla Factura......");
        String concepto = "";
        String precioUnidad = "";
        int cantidad = 0;
        int no = 1;
        String iva = "";
        String base = "";
        String ivacalculado = "";
        String subtotal = "";
        double stt, bt, ivat;
        String descripcion="";
        Conceptos c;
        
        int numeroClientes = lista.size();
        try {

            for (int i = 0; i < numeroClientes;) {
                     
                if (i <= numeroClientes) {
                    c = lista.get(i);
                    concepto = c.getNombreConcepto();
                    cantidad = c.getCantidad();
                    precioUnidad = c.getBase();
                    iva = c.getIva();
                    descripcion = c.getDescripcion();
                    if (descripcion.equalsIgnoreCase("")||descripcion==null){descripcion="N/A";}
                    base = calcularBase(precioUnidad, cantidad, "0");
                    ivacalculado = calcularIva(base, iva);
                    subtotal = calcularTotalConcepto(base, ivacalculado);
                    stt = Double.parseDouble(cambiarComaporPunto(subtotal));
                    bt = Double.parseDouble(cambiarComaporPunto(base));
                    ivat = Double.parseDouble(cambiarComaporPunto(ivacalculado));
                    basetotal = basetotal + bt;
                    ivatotal = ivatotal + ivat;
                    subtotaltotal = subtotaltotal + stt;
                    out.println(" <tr>\n"
                            + "<td class=\"no\">" + no + "</td>\n"
                            + "<td>" + concepto + "</td>\n"
                            + "<td class=\"desc\">" + descripcion + "</td>\n"
                            + " <td>" + cantidad + "</td>\n"
                            + " <td class=\"unit\">" + precioUnidad + "</td>\n"
                            + " <td>" + iva + "</td>\n"
                            + " <td>" + base + "</td>\n"
                            + " <td>" + ivacalculado + "</td>\n"
                            + " <td class=\"total\">" + subtotal + "</td>\n"
                            + " </tr>");
                    no++;
                    i++;
                    
                }

            }
        } catch (java.io.IOException e1) {
            System.out.println(e1);
        }
    }

    public void totalestabla(JspWriter out) {
        try {
            String b = f.format(basetotal);
            String i = f.format(ivatotal);
            String s = f.format(subtotaltotal);
            out.println("<tr>\n"
                    + "<td colspan=\"5\"></td>\n"
                    + "<td>TOTAL</td>\n"
                    + "<td>" + b + "</td>\n"
                    + "<td>" + i + "</td>\n"
                    + "<td>" + s + "</td>\n"
                    + "</tr>");
        } catch (IOException ex) {
            System.out.println("Error de totales: " + ex.getMessage());
        }

    }
   public String selecionarPresupuesto(HttpSession sesion) {
        Usuarios usuario = (Usuarios) sesion.getAttribute("usuario");
        String url = "";
        String esPresupuesto = "si";

        if (usuario != null) {

            if (usuario.getRazonSocial() != null) {
                url = "presupuestar.jsp";
            } else {
                url = "completarDatosUsuario.jsp";
                sesion.setAttribute("espresupuesto", esPresupuesto);
            }
        }else{ url= "index.jsp";}
        return url;
    }
 public String selecionarFacturacion(HttpSession sesion) {
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
}
