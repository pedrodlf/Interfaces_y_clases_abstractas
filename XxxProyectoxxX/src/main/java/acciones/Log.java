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
public class Log extends org.apache.struts.action.Action {

    DBManager db = new DBManager();
    HttpSession session;
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String ERROR = "error1";

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
        Usuarios usuario = (Usuarios) form;
        System.out.println("nickName = " + usuario.getNickName());
        String nuevoregistro = request.getParameter("registro");
        session = request.getSession();
        if (db.existeUsuario(usuario) && nuevoregistro != null) {
            session.setAttribute("error", "El usuario ya existe");
            return mapping.findForward(ERROR);
        } else if (nuevoregistro == null) {
            if (db.verificarLog(usuario)) {
                usuario = db.getUserData(usuario);
                session.setAttribute("usuario", usuario);
                return mapping.findForward(SUCCESS);
            } else {
                session.setAttribute("error", "Usuario o Password incorrectos");
                return mapping.findForward(ERROR);
            }
        } else {
            db.nuevoUsuario(usuario);
            session.setAttribute("usuario", usuario);
            return mapping.findForward(SUCCESS);
        }

    }
}
