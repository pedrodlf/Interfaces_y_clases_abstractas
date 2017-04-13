/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import dbm.DBManager;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.GeneradorFacturas;
import modelos.Conceptos;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Pedro DLF
 */
public class CrearConcepto extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("Entrando en el servlet CrearConcepto..........");
        DBManager db = new DBManager();
        DecimalFormat f = new DecimalFormat("######.00");
        GeneradorFacturas generador = new GeneradorFacturas();
        HttpSession s = request.getSession();
        String nombreConcepto = request.getParameter("nombreConcepto");
        String cantidad = request.getParameter("cantidadpro");
        String base =generador.formatearTexto(request.getParameter("basepro")) ;
        String iva = generador.formatearTexto(request.getParameter("ivapro"));
        String descripcion = request.getParameter("descripccionpro");
        String descripcionx = "";
        String numeroFactura = generador.quitarbarra(request.getParameter("numeroFacturapro"));
        String nickName = generador.quitarbarra(request.getParameter("nickNamepro"));
        String concepto = "", precioUnidad = "", ivax = "", basex = "", ivacalculadox = "";
        String subtotalx = "";
        double basetotal = 0.00, ivatotal = 0.00, subtotaltotal = 0.00;
        double stt, bt, ivat;
        if(descripcion == null || descripcion.equalsIgnoreCase(" ")){descripcion = "N/A";}
        System.out.println("Nombre concepto " + nombreConcepto);
        System.out.println("Cantidad concepto " + cantidad);
        System.out.println("Base concepto " + base);
        System.out.println("Iva concepto " + iva);
        System.out.println("Descripcion concepto " + descripcion);
        System.out.println("Numero Factura concepto " + numeroFactura);
        System.out.println("Nick name concepto " + nickName);

        Conceptos nuevoconcepto = new Conceptos(nickName, generador.formatearTexto(descripcion), base, iva, nombreConcepto, Integer.parseInt(numeroFactura), Integer.parseInt(cantidad));
        //Conceptos nuevoconcepto = (Conceptos) form;

        try {
            db.guardarConceptos(nuevoconcepto);
            System.out.println("nickname = " + nuevoconcepto.getNickName() + " nombreConcepto " + nuevoconcepto.getNombreConcepto() + " iva= " + nuevoconcepto.getIva());

        } catch (Exception e) {
            System.out.println("Error al guardar Concepto causa " + e.getMessage());
        }
        try {
            System.out.println("Pintando tabla ..........");
            // response.setContentType("text/html; charset=iso-8859-1");
            PrintWriter out = response.getWriter();
            out.println("<div class=\"12u$\">");
            out.println("<div class=\"table-wrapper\">");
            out.println("<table>");
            out.println("<thead>");
            out.println("<tr>");
            out.println("<th>Concepto</th>");
            out.println("<th>Descripcion</th>");
            out.println("<th>Catidad</th>");
            out.println("<th>Precio/Unidad</th>");
            out.println("<th>IVA%</th>");
            out.println("<th>Base</th>");
            out.println("<th>IVA</th>");
            out.println("<th>Subtotal</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            try {
                List<Conceptos> lista = db.recuperarconceptosdeFactura(numeroFactura, Integer.parseInt(nickName));
                int numeroClientes = lista.size();
                System.out.println("Tamño de la lista recuperada");
                for (int i = 0; i < numeroClientes;) {

                    if (i <= numeroClientes) {
                        Conceptos c = lista.get(i);
                        System.out.println("concepto =" + c.getNombreConcepto());
                        descripcionx = c.getDescripcion();
                        if (descripcionx == null || descripcionx.equalsIgnoreCase("")) {
                            descripcionx = "N/A";
                        }
                        concepto = c.getNombreConcepto();
                        cantidad = String.valueOf(c.getCantidad());
                        System.out.println(cantidad);
                        precioUnidad = c.getBase();
                        System.out.println(precioUnidad);
                        ivax = c.getIva();
                        System.out.println(iva);
                        basex = generador.calcularBase(c.getBase(), c.getCantidad(), "0");
                        System.out.println(basex);
                       ivacalculadox = generador.calcularIva(basex, ivax);
                        System.out.println(ivacalculadox);
                         subtotalx = generador.calcularTotalConcepto(basex, ivacalculadox);
                        System.out.println(subtotalx);
                        
                       
                        stt = Double.parseDouble(generador.cambiarComaporPunto(subtotalx));
                        bt = Double.parseDouble(generador.cambiarComaporPunto(basex));
                        ivat = Double.parseDouble(generador.cambiarComaporPunto(ivacalculadox));
                        basetotal = basetotal + bt;
                        ivatotal = ivatotal + ivat;
                        subtotaltotal = subtotaltotal + stt;
                        
                        out.println(" <tr>\n"
                                + "<td>" + concepto + "</td>\n"
                                + " <td>" + descripcionx + "</td>\n"
                                + " <td>" + cantidad + "</td>\n"
                                + " <td>" + precioUnidad + "</td>\n"
                                + " <td>" + ivax + "</td>\n"
                                + " <td>" + basex + "</td>\n"
                                + " <td>" + ivacalculadox + "</td>\n"
                               + " <td>" + subtotalx + "</td>\n"
                                + " </tr>");

                        i++;
                    }

                }
            } catch (Exception e) {
                System.out.println("Bucle de pintado fallido ERROR =" + e.getMessage());
            }

            out.println("</tbody>");
            out.println("<tfoot>");
            String b = f.format(basetotal);
            String i = f.format(ivatotal);
            String su = f.format(subtotaltotal);
            out.println("<tr>\n"
                    + "<td colspan=\"4\"></td>\n"
                    + "<td>TOTAL</td>\n"
                    + "<td>" + b + "</td>\n"
                    + "<td>" + i + "</td>\n"
                    + "<td>" + su + "</td>\n"
                    + "</tr>");
            out.println("</tfoot>");
            out.println("</table>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"12u$\" id=\"botfactura\">");
            out.println("<ul class=\"actions\">");
            out.println("<li><input type=\"submit\" value=\"Facturar\" class=\"special\" /></li>");
            out.println("<li><input type=\"button\" onclick='ventanaPlantilla()' value=\"Visualizar Factura\" /></li>");
            out.println("</ul>");
            out.println("</div>");
            

            System.out.println("Tabla pintada");
        } catch (Exception e) {
            System.out.println("Error al pintar el pie de tabla ="+e.getMessage());
        }

        return null;
    }

}
