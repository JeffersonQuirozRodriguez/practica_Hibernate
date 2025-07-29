package org.example;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Id;
import jakarta.persistence.Persistence;
import org.example.entity.Cliente;
import org.example.entity.Mascota;
import org.example.entity.MascotaTratamiento;
import org.example.entity.Tratamiento;
import org.example.service.ClienteService;
import org.example.service.MascotaService;
import org.example.service.MascotaTratamientoService;
import org.example.service.TratamientoService;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ClinivetPU");
        EntityManager em = emf.createEntityManager();

        ClienteService cs1 = new ClienteService();
        MascotaService ms1 = new MascotaService();
        TratamientoService ts1 = new TratamientoService();
        MascotaTratamientoService mts1 = new MascotaTratamientoService();

        //Registrar Cliente
        cs1.registrarCliente("Enmanuel", "enmanuel@gmail");
        //Buscar cliente por ID
        Cliente cliente = cs1.buscarClienteId(1);
        if (cliente != null) {
            System.out.println("Cliente registrado: " + cliente.getNombre());
        }
        //Registrar mascota
        ms1.registrarMascota("Toby", "Perro", 5, cliente.getId());
        ms1.registrarMascota("Mimi", "Gato", 3, cliente.getId());

        // 4. Consultar mascotas del cliente
        List<Mascota> mascotas = ms1.consultarMascotaCliente(cliente.getId());
        System.out.println("Mascotas de " + cliente.getNombre() + ":");
        for (Mascota m : mascotas) {
            System.out.println("- " + m.getNombre() + " (" + m.getTipo() + ")");
        }
        // 5. Registrar tratamiento
        ts1.registrarTratamiento("Vacuna Rabia", "Vacuna anual contra la rabia");
        ts1.registrarTratamiento("Desparasitación", "Tratamiento antiparasitario completo");


        // 7. Asignar tratamientos a mascotas (nuevo método en MascotaTratamientoService)
        Mascota mascota = ms1.buscarMascotaId(1);
        Tratamiento tratamiento1 = ts1.buscarTratamiento(1);
        Tratamiento tratamiento2 = ts1.buscarTratamiento(2);

        if (mascota != null && tratamiento1 != null && tratamiento2 != null) {
            mts1.registrarTratamientoMascota(mascota.getId(), tratamiento1.getId());
            mts1.registrarTratamientoMascota(mascota.getId(), tratamiento2.getId());
            System.out.println("Tratamientos asignados correctamente a la mascota: " + mascota.getNombre());
        } else {
            System.out.println("No se pudo asignar tratamientos, datos incompletos.");
        }

        // 8. Mostrar tratamientos de una mascota
        List<MascotaTratamiento> tratamientosMascota = mts1.buscarTratamientoMascota(mascota.getId());

        System.out.println("Tratamientos registrados para " + mascota.getNombre() + ":");
        for (MascotaTratamiento mt : tratamientosMascota) {
            Tratamiento t = mt.getTratamiento();
            System.out.println("- " + t.getNombre() + ": " + t.getDescripcion());
        }

        // Finalizar programa
        System.out.println("Sistema Clinivet finalizado.");
    }
}