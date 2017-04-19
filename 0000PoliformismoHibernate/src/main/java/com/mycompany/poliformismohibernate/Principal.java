/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.poliformismohibernate;

import com.mycompany.poliformismohibernate.modelos.Motos;
import com.mycompany.poliformismohibernate.modelos.TodoTerreno;
import com.mycompany.poliformismohibernate.modelos.Utilitario;
import java.util.List;

/**
 *
 * @author Pedro DLF
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("----------------------------INICIO PROGRAMA----------------------------");
        Motos moto = new Motos("1100", "Negra", "2", "No", "Virago");
        Utilitario utilitario= new Utilitario(1, 2, 4, 3, "Mondeo");
        TodoTerreno todoterreno = new TodoTerreno("rojo", "No", "4", "No", "Land Rover");
        moto.guardar();
        utilitario.guardar();
        todoterreno.guardar();
        moto.cargar();
        utilitario.cargar();
        todoterreno.cargar();
        List<Motos> listaMotos = moto.getLista();
        List<Utilitario> listaUtilitarios = utilitario.getLista();
        List<TodoTerreno> listaTodoTerrenos = todoterreno.getLista();
        
        System.out.println("El primer modelo de Moto es "+ listaMotos.get(0).getModelo());
        System.out.println("El primer modelo de Utilitario es "+ listaUtilitarios.get(0).getModelo());
        System.out.println("El primer modelo de Todo Terreno es "+ listaTodoTerrenos.get(0).getModelo());
        System.out.println("--------------------------FIN DEL PROGRAMA---------------------------");
        
        
    }
    
}
