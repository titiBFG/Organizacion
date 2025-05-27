package entity;

import java.time.LocalDate;

public class Donacion {
    private int id;
    private LocalDate fecha;
    private Donante donante;
    private double monto;
    private Estado estado;
    private static int contadorId = 123435;

    public Donacion(LocalDate fecha, double monto, Donante donante) {
        this.id = contadorId++;
        this.fecha = fecha;
        this.donante = donante;
        this.monto = monto;
        this.estado = Estado.PENDIENTE;
    }

    public int getId() {
        return this.id;
    }

    public LocalDate getFecha() {
        return this.fecha;
    }

    public Donante getDonante() {
        return this.donante;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public double getMonto() {
        return this.monto;
    }

    @Override
    public String toString() {
        return "Donacion: {\n" + " ID: " + getId() + "\n" +
                " Donante: " + getDonante().getNombre() + " " + getDonante().getApellido() + "\n" +
                " Fecha: " + getFecha() + "\n" +
                " Estado: " + getEstado() + "\n" +
                " Monto: " + getMonto() + "\n}\n";
    }
}
