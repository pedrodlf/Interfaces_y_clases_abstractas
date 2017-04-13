/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import dbm.DBManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuarios;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author Pedro DLF
 */
public class UpdateUser extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String ERROR = "error2";

    
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Usuarios usuario = (Usuarios) form;
        HttpSession s = request.getSession();
        Usuarios u = (Usuarios)s.getAttribute("usuario");
        usuario.setIdUsuario(u.getIdUsuario());
        usuario.setNickName(u.getNickName());
        usuario.setPassword(u.getPassword());
        s.setAttribute("usuario", usuario);
        try {
        DBManager db= new DBManager();
        db.setUserData(usuario);
        return mapping.findForward(SUCCESS);  
        } catch (Exception e) {
        return mapping.findForward(ERROR);    
        }
        
        
    }
}
