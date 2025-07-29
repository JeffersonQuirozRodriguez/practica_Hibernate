package org.example.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "mascota_tratamiento")
public class MascotaTratamiento implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "mascota_id", nullable = false)
    private Mascota mascota;

    @Id
    @ManyToOne
    @JoinColumn(name = "tratamiento_id", nullable = false)
    private Tratamiento tratamiento;

    public MascotaTratamiento() {
    }

    public MascotaTratamiento(Mascota mascota, Tratamiento tratamiento) {
        this.mascota = mascota;
        this.tratamiento = tratamiento;
    }

    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }
}
