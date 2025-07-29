package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Tratamiento;

public class TratamientoService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinivetPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarTratamiento(String nombre, String decripcion){
        Tratamiento tratamiento = new Tratamiento();
        tratamiento.setNombre(nombre);
        tratamiento.setDescripcion(decripcion);
        em.getTransaction().begin();
        em.persist(tratamiento);
        em.getTransaction().commit();
    }

    public Tratamiento buscarTratamiento(long id){
        return em.find(Tratamiento.class, id);
    }
}
