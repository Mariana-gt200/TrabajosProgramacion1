import co.edu.uniquindio.poo.parcial_3.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PanaderiaTest {

    //REGISTRAR

    @Test
    void registrarPedidoQuedaPendiente() {
        Panaderia panaderia = new Panaderia("El Trigal");
        PanaderoArtesanal ana = new PanaderoArtesanal("Ana", "111", "E1", 100);
        panaderia.registrarEmpleado(ana);

        Pedido pedido = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);

        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(25, pedido.getCantidadUnidades());
        assertEquals(1, panaderia.getPedidos().size());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    @Test
    void registrarCodigoRepetidoDeUnPedidoCanceladoLanzaExcepcion() {
        Panaderia panaderia = new Panaderia("El Trigal");
        panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);
        panaderia.cancelarPedido("P01");

        assertThrows(IllegalArgumentException.class,
                () -> panaderia.registrarPedido("P01", "Otro Cliente", TipoPan.INTEGRAL, 10));

        assertEquals(1, panaderia.getPedidos().size());
        assertEquals(EstadoPedido.CANCELADO, panaderia.getPedidos().get(0).getEstado());
    }

    @Test
    void registrarPedidoConCeroPanesLanzaExcepcion() {
        Panaderia panaderia = new Panaderia("El Trigal");

        assertThrows(IllegalArgumentException.class,
                () -> panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 0));

        assertTrue(panaderia.getPedidos().isEmpty());
    }

    //ASIGNAR

    @Test
    void asignarPedidoAlPanaderoQueTardaMenos() {
        Panaderia panaderia = new Panaderia("El Trigal");
        PanaderoArtesanal ana = new PanaderoArtesanal("Ana", "111", "E1", 100);
        PanaderoIndustrial carlos = new PanaderoIndustrial("Carlos", "222", "E2", 45);
        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(carlos);
        Pedido pedido = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);

        // Ana tardaria 90 y Carlos 45
        assertEquals(90, ana.calcularMinutos(TipoPan.TRADICIONAL, 25));
        assertEquals(45, carlos.calcularMinutos(TipoPan.TRADICIONAL, 25));

        panaderia.asignarPedido("P01");

        assertEquals(EstadoPedido.ASIGNADO, pedido.getEstado());
        assertEquals("E2", pedido.getCodigoEmpleadoAsignado());
        assertEquals(45, pedido.getMinutosReservados());
        assertEquals(0, carlos.getMinutosDisponibles());
        assertEquals(100, ana.getMinutosDisponibles());
    }

    @Test
    void asignarConEmpateSeEligeAlPrimeroDeLaLista() {
        Panaderia panaderia = new Panaderia("El Trigal");
        PanaderoArtesanal ana = new PanaderoArtesanal("Ana", "111", "E1", 80);
        PanaderoArtesanal diana = new PanaderoArtesanal("Diana", "222", "E2", 80);
        PanaderoIndustrial carlos = new PanaderoIndustrial("Carlos", "333", "E3", 54);
        panaderia.registrarEmpleado(ana);
        panaderia.registrarEmpleado(diana);
        panaderia.registrarEmpleado(carlos);
        Pedido pedido = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.INTEGRAL, 13);

        // Carlos necesitaria 55 y queda afuera, Ana y Diana necesitan 80
        assertEquals(55, carlos.calcularMinutos(TipoPan.INTEGRAL, 13));

        panaderia.asignarPedido("P01");

        assertEquals(EstadoPedido.ASIGNADO, pedido.getEstado());
        assertEquals("E1", pedido.getCodigoEmpleadoAsignado());
        assertEquals(80, pedido.getMinutosReservados());
        assertEquals(0, ana.getMinutosDisponibles());
        assertEquals(80, diana.getMinutosDisponibles());
        assertEquals(54, carlos.getMinutosDisponibles());
    }

    @Test
    void asignarSinTiempoSuficienteLanzaExcepcion() {
        Panaderia panaderia = new Panaderia("El Trigal");
        PanaderoIndustrial carlos = new PanaderoIndustrial("Carlos", "111", "E1", 44);
        Cajero lucia = new Cajero("Lucia", "222", "E2", 120, "Caja 1");
        panaderia.registrarEmpleado(carlos);
        panaderia.registrarEmpleado(lucia);
        Pedido pedido = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);

        assertThrows(IllegalStateException.class, () -> panaderia.asignarPedido("P01"));

        assertEquals(EstadoPedido.PENDIENTE, pedido.getEstado());
        assertEquals(44, carlos.getMinutosDisponibles());
        assertEquals(120, lucia.getMinutosDisponibles());
    }
   //CANCELAR

        @Test
        void cancelarPedidoAsignadoDevuelveMinutosReservados() {
            Panaderia panaderia = new Panaderia("El Trigal");
            PanaderoIndustrial carlos = new PanaderoIndustrial("Carlos", "111", "E1", 120);
            panaderia.registrarEmpleado(carlos);
            Pedido p1 = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);
            Pedido p2 = panaderia.registrarPedido("P02", "Restaurante Sol", TipoPan.TRADICIONAL, 25);
            panaderia.asignarPedido("P01");
            panaderia.asignarPedido("P02");

            // Cada pedido reserva 45 minutos
            assertEquals(45, p1.getMinutosReservados());
            assertEquals(45, p2.getMinutosReservados());
            assertEquals(30, carlos.getMinutosDisponibles());

            panaderia.cancelarPedido("P01");

            assertEquals(75, carlos.getMinutosDisponibles());
            assertEquals(EstadoPedido.CANCELADO, p1.getEstado());
            assertEquals(EstadoPedido.ASIGNADO, p2.getEstado());
        }

        @Test
        void cancelarPedidoPendienteConservaMinutos() {
            Panaderia panaderia = new Panaderia("El Trigal");
            PanaderoArtesanal ana = new PanaderoArtesanal("Ana", "111", "E1", 100);
            panaderia.registrarEmpleado(ana);
            Pedido pedido = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);

            panaderia.cancelarPedido("P01");

            assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
            assertEquals(1, panaderia.getPedidos().size());
            assertTrue(panaderia.buscarPedido("P01").isPresent());
            assertEquals(100, ana.getMinutosDisponibles());
        }

        @Test
        void cancelarPedidoYaCanceladoLanzaExcepcionYDevuelveMinutosUnaVez() {
            Panaderia panaderia = new Panaderia("El Trigal");
            PanaderoIndustrial carlos = new PanaderoIndustrial("Carlos", "111", "E1", 100);
            panaderia.registrarEmpleado(carlos);
            Pedido pedido = panaderia.registrarPedido("P01", "Cafe Central", TipoPan.TRADICIONAL, 25);
            panaderia.asignarPedido("P01");
            panaderia.cancelarPedido("P01");

            assertThrows(IllegalStateException.class, () -> panaderia.cancelarPedido("P01"));

            assertEquals(EstadoPedido.CANCELADO, pedido.getEstado());
            assertEquals(100, carlos.getMinutosDisponibles());
        }
    }