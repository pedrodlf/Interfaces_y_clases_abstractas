package com.mycompany.poliformismohibernate.modelos;
// Generated 18-abr-2017 13:38:43 by Hibernate Tools 4.3.1

import com.mycompany.poliformismohibernate.HibernateUtil.HerramientasHibernate;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;




/**
 * Utilitario generated by hbm2java
 */
public class Utilitario extends Vehiculos implements java.io.Serializable {


     private Integer idVehiculo;
     private int color;
     private int numeroDeRuedas;
     private int esBerlina;
     private int esAutomatico;
     private String modelo;
     
     private List<Utilitario> lista;

    public List<Utilitario> getLista() {
        return lista;
    }

   

    public Utilitario() {
    }

    public Utilitario(int color, int numeroDeRuedas, int esBerlina, int esAutomatico, String modelo) {
       this.color = color;
       this.numeroDeRuedas = numeroDeRuedas;
       this.esBerlina = esBerlina;
       this.esAutomatico = esAutomatico;
       this.modelo = modelo;
    }
   
    public Integer getIdVehiculo() {
        return this.idVehiculo;
    }
    
    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }
    public int getColor() {
        return this.color;
    }
    
    public void setColor(int color) {
        this.color = color;
    }
    public int getNumeroDeRuedas() {
        return this.numeroDeRuedas;
    }
    
    public void setNumeroDeRuedas(int numeroDeRuedas) {
        this.numeroDeRuedas = numeroDeRuedas;
    }
    public int getEsBerlina() {
        return this.esBerlina;
    }
    
    public void setEsBerlina(int esBerlina) {
        this.esBerlina = esBerlina;
    }
    public int getEsAutomatico() {
        return this.esAutomatico;
    }
    
    public void setEsAutomatico(int esAutomatico) {
        this.esAutomatico = esAutomatico;
    }
    public String getModelo() {
        return this.modelo;
    }
    
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    @Override
    public void cargar() {
       try {
            Session s = HerramientasHibernate.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            Query q = s.createQuery("from Utilitario");
            this.lista = q.list();
            s.close();
            System.out.println("Utilitarios cargados");
        } catch (HibernateException e) {
            System.out.println("Error al actualizar la lista de motos "+e.getMessage());
        }
    }




}


