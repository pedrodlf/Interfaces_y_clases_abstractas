/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import dbm.DBManager;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.GeneradorFacturas;
import modelos.Clientes;
import modelos.Facturas;
import modelos.Usuarios;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Pedro DLF
 */
public class Billing extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("Entrando en el servlet Facturar");
         HttpSession s = request.getSession();
        DBManager db = new DBManager();
        GeneradorFacturas generador = new GeneradorFacturas();
        Usuarios usuario = (Usuarios) s.getAttribute("usuario");
        System.out.println("Usuario = "+usuario.getNickName());
        String numerofactura = (String) s.getAttribute("nfactura");
        System.out.println("Numero de Factura = "+numerofactura);
        String cliente = request.getParameter("combo-Cliente");
        System.out.println("Cliente = "+cliente);
        String fecha = request.getParameter("demo-fecha");
        System.out.println("fecha ="+fecha);
        Clientes c = db.recuperarclienteporID(cliente);
        String fechamodificada = request.getParameter("factura-vencimiento");
        System.out.println("fecha modificda = "+fechamodificada);
        
        if(fecha.equalsIgnoreCase("")){
        fecha = generador.generarFecha();
        }
        if(fechamodificada.equalsIgnoreCase("")|| fechamodificada==null){
        fechamodificada= "no";
        }
        
        Facturas nuevafactura = new Facturas(c.getNickName(), Integer.parseInt(numerofactura), fecha, fechamodificada, cliente);
        db.guardarFactura(nuevafactura);
        /*
        
        if(usuario!= null && numerofactura!= null){
        db.nuevaFactura(factura);
        resp.sendRedirect("./plantillas/example2/plantilla2.jsp");
        }*/
        return mapping.findForward(SUCCESS);
    }
}
