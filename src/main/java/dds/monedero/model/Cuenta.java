package dds.monedero.model;

import dds.monedero.exceptions.CuentaException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cuenta {

  private double saldo;
  private List<Deposito> depositos = new ArrayList<>();
  private List<Extraccion> extracciones = new ArrayList<>();
  private double limite;


  public Cuenta(double montoInicial,double limite) {
    saldo = montoInicial;
    this.limite=limite;
  }

  public void setDepositos(List<Deposito> movimientos) {
    this.depositos = movimientos;
  }
  public void setExtracciones(List<Extraccion> movimientos) {
    this.extracciones = movimientos;
  }


  public void poner(double cuanto) {
    if (cuanto <= 0) {
      throw new CuentaException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }
    if (this.getDepositos().size() >= 3) {
      throw new CuentaException("Ya excedio los " + 3 + " depositos diarios");
    }
    this.agregarDeposito(new Deposito(LocalDate.now(),cuanto));
    this.setSaldo(saldo+cuanto);
  }

  public void sacar(double cuanto) {
    if (cuanto <= 0) {
      throw new CuentaException(cuanto + ": el monto a ingresar debe ser un valor positivo");
    }
    if (getSaldo() - cuanto < 0) {
      throw new CuentaException("No puede sacar mas de " + getSaldo() + " $");
    }
    if (limite-getMontoExtraidoA(LocalDate.now())-cuanto<0) {
      throw new CuentaException("No puede extraer mas de $ " + limite+ " diarios");
    }
    this.agregarExtraccion(new Extraccion(LocalDate.now(),cuanto));
    this.setSaldo(saldo-cuanto);
  }


  public double getMontoExtraidoA(LocalDate fecha) {
    return getExtracciones().stream()
        .filter(extraccion -> extraccion.getFecha().equals(fecha))
        .mapToDouble(Extraccion::getMonto)
        .sum();
  }

  public List<Deposito> getDepositos() {
    return depositos;
  }
  public List<Extraccion> getExtracciones() {
    return extracciones;
  }


  public double getSaldo() {
    return saldo;
  }

  public void setSaldo(double saldo) {
    this.saldo = saldo;
  }
  public void agregarDeposito(Deposito deposito){
    depositos.add(deposito);
  }
  public void agregarExtraccion(Extraccion extraccion){
    extracciones.add(extraccion);
  }
}
