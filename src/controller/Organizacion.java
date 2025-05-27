package controller;

import entity.Donacion;
import entity.Donante;
import entity.Estado;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

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
        Stream<Donacion> d = donaciones.values().stream()
                .filter(donacion -> donacion.getFecha().isBefore(fecha) ||
                        donacion.getFecha().equals(fecha));
                // Agrego el metodo equals porque no se si isBefore tambien toma una fecha igual

        // Para reutilizar el stream, debés recolectarlo en una lista
        List<Donacion> donacionesFiltradas = d.toList();

        int cob = (int) donacionesFiltradas.stream()
                .filter(donacion -> donacion.getEstado().equals(Estado.COBRADA))
                .count();

        int rec = (int) donacionesFiltradas.stream()
                .filter(donacion -> donacion.getEstado().equals(Estado.RECHAZADA))
                .count();

        int pen = (int) donacionesFiltradas.stream()
                .filter(donacion -> donacion.getEstado().equals(Estado.PENDIENTE))
                .count();

        double total = donacionesFiltradas.stream()
                .mapToDouble(Donacion::getMonto)
                .sum();

        double max = donacionesFiltradas.stream()
                .mapToDouble(Donacion::getMonto)
                .max()
                .orElse(0); // valor por defecto si está vacío

        double min = donacionesFiltradas.stream()
                .mapToDouble(Donacion::getMonto)
                .min()
                .orElse(0); // valor por defecto si está vacío

        double mean = donacionesFiltradas.stream()
                .mapToDouble(Donacion::getMonto)
                .average()
                .orElse(0);

        System.out.println("- Cobradas: " + cob);
        System.out.println("- Rechazadas: " + rec);
        System.out.println("- Cobradas: " + pen);
        System.out.println("- Total: $" + total);
        System.out.println("- Máximo: $" + max);
        System.out.println("- Mínimo: $" + min);
    }
}
