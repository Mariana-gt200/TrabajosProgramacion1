package co.edu.uniquindio.poo.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MonederoVirtualTest {

    private MonederoVirtual monederoVirtual;

    // Antes de cada prueba se crea un sistema nuevo y vacío
    @BeforeEach
    void preparar() {
        monederoVirtual = new MonederoVirtual(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(),
                new ServicioNotificacion(), new AnalizadorPatronesGastos());
    }

    // Crea un cliente BRONCE con un monedero con el saldo indicado
    private void crearClienteConMonedero(String idCliente, String idMonedero, double saldo) {
        monederoVirtual.registrarCliente(
                new Cliente(idCliente, "Cliente " + idCliente, "c" + idCliente + "@mail.com", "300", RangoCliente.BRONCE));
        monederoVirtual.registrarMonedero(idCliente,
                new Monedero(idMonedero, TipoMonedero.AHORROS, saldo, idCliente));
    }

    @Test
    void depositarAumentaElSaldoYRegistraLaTransaccion() throws Exception {
        crearClienteConMonedero("1", "M1", 0);

        monederoVirtual.depositar("1", "M1", 50000);

        assertEquals(50000, monederoVirtual.consultarSaldo("1", "M1"), 0.001);
        assertEquals(1, monederoVirtual.getListaTransacciones().size());
        Transaccion transaccion = monederoVirtual.getListaTransacciones().get(0);
        assertTrue(transaccion instanceof Deposito);
        assertEquals(EstadoTransaccion.COMPLETADA, transaccion.getEstado());
    }

    @Test
    void depositarMontoCeroLanzaExcepcionYNoCambiaElSaldo() throws Exception {
        crearClienteConMonedero("1", "M1", 20000);

        assertThrows(MontoInvalidoException.class, () -> monederoVirtual.depositar("1", "M1", 0));

        assertEquals(20000, monederoVirtual.consultarSaldo("1", "M1"), 0.001);
        assertTrue(monederoVirtual.getListaTransacciones().isEmpty());
    }

    @Test
    void retirarMasDelSaldoLanzaExcepcionYNoCambiaElSaldo() throws Exception {
        crearClienteConMonedero("1", "M1", 30000);

        assertThrows(SaldoInsuficienteException.class, () -> monederoVirtual.retirar("1", "M1", 50000));

        assertEquals(30000, monederoVirtual.consultarSaldo("1", "M1"), 0.001);
        assertTrue(monederoVirtual.getListaTransacciones().isEmpty());
    }

    @Test
    void transferirDescuentaMontoYComisionDelOrigenYAcreditaAlDestino() throws Exception {
        crearClienteConMonedero("1", "M1", 100000);
        crearClienteConMonedero("2", "M2", 0);

        monederoVirtual.transferir("1", "M1", "2", "M2", 10000);

        // Cliente BRONCE: comisión del 2% = 200
        assertEquals(89800, monederoVirtual.consultarSaldo("1", "M1"), 0.001);
        assertEquals(10000, monederoVirtual.consultarSaldo("2", "M2"), 0.001);
        assertEquals(1, monederoVirtual.getListaTransacciones().size());
    }

    @Test
    void revertirUnRetiroDevuelveElSaldoYMarcaLaTransaccionComoRevertida() throws Exception {
        crearClienteConMonedero("1", "M1", 100000);
        monederoVirtual.retirar("1", "M1", 30000);
        assertEquals(70000, monederoVirtual.consultarSaldo("1", "M1"), 0.001);
        Transaccion retiro = monederoVirtual.getListaTransacciones().get(0);

        monederoVirtual.revertirTransaccion(retiro.getIdTransaccion());

        assertEquals(100000, monederoVirtual.consultarSaldo("1", "M1"), 0.001);
        assertEquals(EstadoTransaccion.REVERTIDA, retiro.getEstado());
    }
}