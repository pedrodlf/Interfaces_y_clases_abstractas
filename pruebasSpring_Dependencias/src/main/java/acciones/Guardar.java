/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package acciones;

import com.pedro.pruebasSpring.Motos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Pedro DLF
 */
public class Guardar extends HttpServlet {
    private String cilindrada;
    private String color;
    private String numeroRuedas;
    private String esAutomatico;
    private String modelo;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       cilindrada = request.getParameter("cilindrada");
       color = request.getParameter("color");
       numeroRuedas = request.getParameter("numero-ruedas");
       esAutomatico = request.getParameter("es_automatico");
       modelo = request.getParameter("modelo");
       
        Motos moto = new Motos(cilindrada, color, numeroRuedas, esAutomatico, modelo);
        
        moto.guardar();
    
    }

}
