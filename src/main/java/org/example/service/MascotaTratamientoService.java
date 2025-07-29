package org.example.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.entity.Mascota;
import org.example.entity.MascotaTratamiento;
import org.example.entity.Tratamiento;

import java.util.List;

public class MascotaTratamientoService {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinivetPU");
    private EntityManager em = emf.createEntityManager();

    public void registrarTratamientoMascota(long mascotaId, long tratamientoId) {
        Mascota mascota = em.find(Mascota.class, mascotaId);
        Tratamiento tratamiento = em.find(Tratamiento.class, tratamientoId);
        if (mascota == null || tratamiento == null) return;
        MascotaTratamiento mt = new MascotaTratamiento(mascota, tratamiento);
        em.getTransaction().begin();
        em.persist(mt);
        em.getTransaction().commit();
    }

    public List<MascotaTratamiento> buscarTratamientoMascota(long mascotaId) {
        return em.createQuery("SELECT mt FROM MascotaTratamiento mt WHERE mt.mascota.id = :mascotaId", MascotaTratamiento.class)
                .setParameter("mascotaId", mascotaId)
                .getResultList();
    }
}
