package entity;

public class Donante {
    private String nombre;
    private String apellido;
    private int id;
    private static int contadorId = 1;

    public Donante(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = contadorId++;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getApellido() {
        return this.apellido;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Donante: " +
                getNombre() + " " +
                getApellido() +
                ", id: " + getId() + "\n";
    }
}
