package dds.monedero.model;

import java.time.LocalDate;

public interface Movimiento {
    double getMonto();
    LocalDate getFecha();
    double calcularValor(Cuenta cuenta);
}
