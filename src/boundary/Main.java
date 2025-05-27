package boundary;

import controller.Organizacion;
import entity.Donacion;
import entity.Donante;
import entity.Estado;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Organizacion org = new Organizacion("Codigo Verde");
        // b.2 Registro de donantes
        Donante dt1 = org.cargarDonante("Wiley", "Wesley");
        Donante dt2 = org.cargarDonante("Chandler", "Stephens");
        Donante dt3 = org.cargarDonante("Keeley", "Sherman");
        Donante dt4 = org.cargarDonante("Keiran", "Raines");
        Donante dt5 = org.cargarDonante("Brendon", "Boothman");
        // b.3 Carga de Donaciones
        Donacion dc1 = org.cargarDonacion(dt1.getId(), LocalDate.of(2023,10,26), 50);
        Donacion dc2 = org.cargarDonacion(dt2.getId(), LocalDate.of(2023,10,22), 100);
        Donacion dc3 = org.cargarDonacion(dt3.getId(), LocalDate.of(2023,10,22), 60);
        Donacion dc4 = org.cargarDonacion(dt4.getId(), LocalDate.of(2023,10,31), 40);
        Donacion dc5 = org.cargarDonacion(dt5.getId(), LocalDate.of(2023,10,15), 30);
        Donacion dc6 = org.cargarDonacion(dt1.getId(), LocalDate.of(2023,11,7), 100);
        Donacion dc7 = org.cargarDonacion(dt2.getId(), LocalDate.of(2023,11,2), 100);
        Donacion dc8 = org.cargarDonacion(dt3.getId(), LocalDate.of(2023,11,9), 60);
        Donacion dc9 = org.cargarDonacion(dt4.getId(), LocalDate.of(2023,11,10), 50);
        Donacion dc10 = org.cargarDonacion(dt5.getId(), LocalDate.of(2023,11,11), 90);
        // b.4 Actualizacion de Donaciones
        org.actualizarDonacion(dc1.getId(), Estado.COBRADA);
        org.actualizarDonacion(dc2.getId(), Estado.COBRADA);
        org.actualizarDonacion(dc3.getId(), Estado.COBRADA);
        org.actualizarDonacion(dc4.getId(), Estado.RECHAZADA);
        org.actualizarDonacion(dc5.getId(), Estado.COBRADA);
        org.actualizarDonacion(dc6.getId(), Estado.COBRADA);
        org.actualizarDonacion(dc7.getId(), Estado.RECHAZADA);
        org.actualizarDonacion(dc8.getId(), Estado.COBRADA);
        // b.5 Mostrar Donantes
        org.mostrarDonantes();
        // b.6 Mostrar Donaciones ordenadas por fecha
        org.mostrarDonaciones();
        // b.7 Mostrar Resultado a la fecha 10/11/2023
        org.mostrarResultadoaFecha(LocalDate.of(2023,11,10));
    }
}
