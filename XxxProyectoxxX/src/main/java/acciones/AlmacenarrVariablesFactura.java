/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Clientes;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Pedro DLF
 */
public class AlmacenarrVariablesFactura extends org.apache.struts.action.Action {



    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("Almacenando Variables Factura...");
        String idcliente = request.getParameter("selectCliente");
        String nfac = request.getParameter("numeroFac");
        String vencimiento = request.getParameter("vencimiento");
        String fecha = request.getParameter("fecha");
        System.out.println("Id cliente = "+idcliente+" numeroFac = "+nfac+" fecha = "+fecha+" vencimiento  = "+vencimiento);
        try {
            dbm.DBManager db = new dbm.DBManager();
            Clientes c = db.recuperarclienteporID(idcliente);
            HttpSession s = request.getSession(false);
            System.out.println("session = "+s);
            s.setAttribute("nfac", nfac);
            s.setAttribute("fecha", fecha);
            s.setAttribute("vencimiento", vencimiento);
            s.setAttribute("cliente", c);
            System.out.println("Variables sublidas");
        } catch (Exception e) {
            System.out.println("ERROR al subir las variables causa = "+e.getMessage());
        }
        
        return null;
    }
}
