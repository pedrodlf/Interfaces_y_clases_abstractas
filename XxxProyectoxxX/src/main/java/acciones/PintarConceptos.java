/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import dbm.DBManager;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Conceptos;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Pedro DLF
 */
public class PintarConceptos extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        DBManager db = new DBManager();
        System.out.println("Entrando en el servlet pintarConceptos......");
        String idconcepto = request.getParameter("selectConcepto");
        Conceptos concepto = db.recuperarConceptoPorID(idconcepto);
        System.out.println("El nombre de concepto es "+concepto.getNombreConcepto());
        String NC = concepto.getNombreConcepto();
        response.setContentType("text/html; charset=iso-8859-1");
        PrintWriter out = response.getWriter();
        out.println("<div class=\"6u 12u$(small)\">");
        out.println("<h5>Nombre Concepto</h5>");
        out.println(" <input type=\"text\" name=\"nombreConcepto\" id=\"nombreConcepto\" value=\""+NC+"\" placeholder=\"-Concepto-\"/>");
        out.println(" </div>");
        out.println("<div class=\"2u 12u$(xsmall)\">");
        out.println("<h5>Unidades</h5>");
        out.println("<input type=\"text\" name=\"cantidad\" id=\"cantidadpr\" value=\"\" placeholder=\"Unidades\" />");
        out.println(" </div>");
        out.println("<div class=\"2u 12u$(xsmall)\">");
        out.println("<h5>Precio/Base</h5>");
        out.println(" <input type=\"text\" name=\"base\" id=\"basepr\" value="+concepto.getBase()+" placeholder=\"Base\" />");
        out.println("</div>");
        out.println("<div class=\"2u 12u$(xsmall)\">");
        out.println("<h5>IVA%</h5>");
        out.println("<input type=\"text\" name=\"iva\" id=\"ivapro\" value="+concepto.getIva()+" placeholder=\"IVA%\" />");
        out.println("</div>");
        out.println("<div class=\"12u$\" id=\"txtdes\" style=\"display:none\">");
        out.println("<textarea name=\"descripcion\" id=\"descripccionconcep\" placeholder=\"Añade la descripcion del producto\" rows=\"6\"></textarea>");
        out.println("</div>");
        return null;
    }
}
