/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poliformismohibernate.modelos;

import com.mycompany.poliformismohibernate.HibernateUtil.HerramientasHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Pedro DLF
 */
public abstract class Vehiculos {
    
    public void guardar(){
        try {
            Session s = HerramientasHibernate.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            s.save(this);
            t.commit();
            s.close();
            System.out.println("Veiculo Guardado");
        } catch (HibernateException e) {
            System.out.println("Error al guardar la moto = "+e.getMessage());
        } 
    }
    
    public abstract void cargar();
    
    public void actualizar(){
        try {
            Session s = HerramientasHibernate.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            s.update(this);
            t.commit();
            s.close();
            System.out.println("veiculo Actualizado");
        } catch (HibernateException e) {
            System.out.println("Error al actualizar veiculo");
        }
    
    }
    
}
