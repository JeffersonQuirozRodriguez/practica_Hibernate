package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Cliente;

public class ClienteService {
    //Registrar información básica de los clientes (nombre y correo electrónico).
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinivetPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarCliente(String nombre, String email) {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
    }

    public Cliente buscarClienteId(long id){
      return em.find(Cliente.class, id);
    };


}
