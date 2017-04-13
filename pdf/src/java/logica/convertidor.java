package logica;


import com.pdfcrowd.Client;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pedro DLF
 */
public class convertidor {

    public void convertirApedf(String url) {

        Client client = new Client("pedrodlafuente@gmail.com", "proyectox");

// convert a web page and save the PDF to a file
        FileOutputStream fileStream;
        try {
            fileStream = new FileOutputStream("google_com.pdf");
            client.convertURI(url, fileStream);
            fileStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(convertidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(convertidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
