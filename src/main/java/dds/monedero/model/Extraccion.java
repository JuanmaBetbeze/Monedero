package dds.monedero.model;

import java.time.LocalDate;

public class Extraccion {
    LocalDate fecha;
    double monto;

    public Extraccion(LocalDate fecha, double monto) {
        this.fecha = fecha;
        this.monto = monto;
    }

    public LocalDate getFecha() {
        return fecha;
    }
    public double getMonto() {
        return monto;
    }

}
