package dds.monedero.model;

import dds.monedero.exceptions.CuentaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MonederoTest {
  private Cuenta cuenta;

  @BeforeEach
  void init() {
    cuenta = new Cuenta(0,1000);
  }

  @Test
  void Poner() {
    cuenta.poner(1500);
    assertEquals(cuenta.getSaldo(),1500);
  }

  @Test
  void PonerMontoNegativo() {
    assertThrows(CuentaException.class, () -> cuenta.poner(-1500));
  }

  @Test
  void TresDepositos() {
    cuenta.poner(1500);
    cuenta.poner(400);
    cuenta.poner(1900);
    assertEquals(cuenta.getSaldo(),3800);
  }

  @Test
  void MasDeTresDepositos() {
    assertThrows(CuentaException.class, () -> {
          cuenta.poner(1500);
          cuenta.poner(456);
          cuenta.poner(1900);
          cuenta.poner(245);
    });
  }

  @Test
  void ExtraerMasQueElSaldo() {
    assertThrows(CuentaException.class, () -> {
          cuenta.setSaldo(90);
          cuenta.sacar(101);
    });
  }

  @Test
  public void ExtraerMasDe1000() {
    assertThrows(CuentaException.class, () -> {
      cuenta.setSaldo(5000);
      cuenta.sacar(1001);
    });
  }

  @Test
  public void ExtraerMontoNegativo() {
    assertThrows(CuentaException.class, () -> cuenta.sacar(-500));
  }

  @Test
  public void ExtraerCorrectament(){
    cuenta.setSaldo(1000);
    cuenta.sacar(750);
    assertEquals(cuenta.getSaldo(),250);
  }
}