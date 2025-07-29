package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Cliente;
import org.example.entity.Mascota;

import java.util.List;

public class MascotaService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinivetPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarMascota(String nombre, String tipo, int edad, long clienteId){
        Cliente cliente = em.find(Cliente.class, clienteId);
        if(cliente == null) return;
        Mascota mascota = new Mascota();
        mascota.setNombre(nombre);
        mascota.setTipo(tipo);
        mascota.setEdad(edad);
        mascota.setCliente(cliente);
        em.getTransaction().begin();
        em.persist(mascota);
        em.getTransaction().commit();
    }

    public List<Mascota> consultarMascotaCliente(long clienteId){
        return em.createQuery("SELECT m FROM Mascota m WHERE m.cliente.id = :clienteId", Mascota.class)
                .setParameter("clienteId", clienteId)
                .getResultList();
    }

    public Mascota buscarMascotaId(long id) {
        return em.find(Mascota.class, id);
    }

}
