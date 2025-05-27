package controller;

import entity.Donacion;
import entity.Donante;
import entity.Estado;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Organizacion {
    private String razonSocial;
    private Map<Integer, Donante> donantes;
    private Map<Integer, Donacion> donaciones;

    public Organizacion(String nombre) {
        this.razonSocial = nombre;
        this.donantes = new HashMap<>();
        this.donaciones = new HashMap<>();
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public Map<Integer, Donante> getDonantes() {
        return donantes;
    }

    public Map<Integer, Donacion> getDonaciones() {
        return donaciones;
    }

    public Donante cargarDonante(String nombre, String apellido) {
        for (Donante donante: donantes.values()) {
            if (donante.getNombre().equals(nombre) && donante.getApellido().equals(apellido)) {
                return donante;
                //verifico si el donante esta previamente registrado y lo devuelvo
            }
        }

        Donante nuevoDonante = new Donante(nombre, apellido);
        donantes.put(nuevoDonante.getId(), nuevoDonante);
        return nuevoDonante;
    }

    public Donacion cargarDonacion(int idDonante, LocalDate fecha, double monto) {
        if (!donantes.containsKey(idDonante)) {
            throw new IllegalArgumentException("El id del donante \"" + idDonante + "\" no se encuentra registrado");
        }
        Donacion nuevaDonacion = new Donacion(fecha, monto, donantes.get(idDonante));
        donaciones.put(nuevaDonacion.getId(), nuevaDonacion);
        return nuevaDonacion;
    }

    public void actualizarDonacion(int idDonacion, Estado estado) {
        if (!donaciones.containsKey(idDonacion)) {
            throw new IllegalArgumentException("El id de la donacion \"" + idDonacion + "\" no se encuentra registrado");
        }
        donaciones.get(idDonacion).setEstado(estado);
    }

    public void mostrarDonantes() {
        donantes.values()
                .forEach(System.out::println);
    }

    public void mostrarDonaciones() {
        donaciones.values().stream()
                .sorted((d1, d2) -> d2.getFecha().compareTo(d1.getFecha()))
                .forEach(System.out::println);
    }

    public void mostrarResultadoaFecha(LocalDate fecha) {
        donaciones.values().stream()
                .filter(donacion -> donacion.getFecha().isBefore(fecha) ||
                        donacion.getFecha().equals(fecha))
                // Agrego el metodo equals porque no se si isBefore tambien toma una fecha igual
                .sorted((d1, d2) -> d2.getFecha().compareTo(d1.getFecha()))
                .forEach(System.out::println);
    }
}
