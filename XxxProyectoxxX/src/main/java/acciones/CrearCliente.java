/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import dbm.DBManager;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.GeneradorFacturas;
import modelos.Clientes;
import modelos.Usuarios;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Pedro DLF
 */
public class CrearCliente extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String ERROR = "error3";

  
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        System.out.println("entrando en el servlet CrearCliente");
        DBManager db = new DBManager();
        HttpSession s = request.getSession();
        GeneradorFacturas g = new GeneradorFacturas();
        Usuarios user = (Usuarios)s.getAttribute("usuario");
        System.out.println("Usuario en servlet es "+user.getNickName());
        Clientes cliente = (Clientes) form;
        cliente.setNickName(g.quitarbarra(String.valueOf(user.getIdUsuario())));
        
        try {
        db.guardarCliente(cliente);
        s.setAttribute("ncliente", cliente);
        response.sendRedirect("facturar.jsp");
        return mapping.findForward(SUCCESS);
        } catch (Exception e) {
            System.out.println("Error = "+e.getMessage());    
        return mapping.findForward(ERROR);
        }
  
    }
    
  
  
}
