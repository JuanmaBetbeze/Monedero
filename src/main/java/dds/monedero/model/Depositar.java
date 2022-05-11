package dds.monedero.model;

import java.time.LocalDate;

public class Depositar implements Movimiento{
    LocalDate fecha;
    double monto;

    public Depositar(LocalDate fecha, double monto) {
        this.fecha = fecha;
        this.monto = monto;
    }

    @Override
    public double getMonto() {
        return monto;
    }

    @Override
    public LocalDate getFecha() {
        return fecha;
    }

    @Override
    public double calcularValor(Cuenta cuenta) {
        return cuenta.getSaldo()+ monto;
    }
}
