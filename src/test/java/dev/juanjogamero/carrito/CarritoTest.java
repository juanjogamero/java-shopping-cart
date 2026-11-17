package dev.juanjogamero.carrito;

import java.math.BigDecimal;

/** Pruebas ejecutables sin librerías externas: java -ea ... CarritoTest */
public final class CarritoTest {
    private CarritoTest() {}

    public static void main(String[] args) {
        Carrito carrito = new Carrito();
        check(carrito.importeTotal().compareTo(new BigDecimal("0.00")) == 0, "Carrito vacío");
        check(carrito.numeroProductos() == 0, "Número de productos inicial");

        carrito.agregar(new Elemento("Tarjeta SD", new BigDecimal("19.95"), 2));
        carrito.agregar(new Elemento("Cámara", new BigDecimal("449.00"), 1));
        check(carrito.numeroProductos() == 2 && carrito.numeroUnidades() == 3, "Recuento");
        check(carrito.importeTotal().compareTo(new BigDecimal("488.90")) == 0, "Importe exacto");

        carrito.agregar(new Elemento("tarjeta sd", new BigDecimal("19.95"), 1));
        check(carrito.numeroProductos() == 2 && carrito.numeroUnidades() == 4, "Agrupación de artículos");
        check(carrito.importeTotal().compareTo(new BigDecimal("508.85")) == 0, "Total tras agrupación");

        carrito.actualizarCantidad("Tarjeta SD", 1);
        check(carrito.importeTotal().compareTo(new BigDecimal("468.95")) == 0, "Cambio de cantidad");
        carrito.actualizarCantidad("Tarjeta SD", 0);
        check(carrito.numeroProductos() == 1, "Cantidad cero elimina producto");
        check(carrito.eliminar("Cámara"), "Eliminar producto");
        check(!carrito.eliminar("Desconocido"), "Eliminar inexistente");

        expectInvalid(() -> new Elemento("", new BigDecimal("1"), 1));
        expectInvalid(() -> new Elemento("Artículo", new BigDecimal("-1"), 1));
        expectInvalid(() -> new Elemento("Artículo", new BigDecimal("1"), 0));
        expectInvalid(() -> new Elemento("Artículo", new BigDecimal("1.001"), 1));
        expectInvalid(() -> carrito.actualizarCantidad("Inexistente", 1));
        expectInvalid(() -> carrito.actualizarCantidad("Inexistente", -1));

        carrito.agregar(new Elemento("Prueba", new BigDecimal("1.00"), 1));
        expectInvalid(() -> carrito.agregar(new Elemento("Prueba", new BigDecimal("2.00"), 1)));
        try {
            carrito.getElementos().clear();
            throw new AssertionError("Lista modificable");
        } catch (UnsupportedOperationException esperado) {
            // Correcto: vista inmutable.
        }
        carrito.vaciar();
        check(carrito.numeroProductos() == 0, "Vaciar carrito");
        System.out.println("Todas las pruebas del carrito han pasado.");
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
    }

    private static void expectInvalid(Runnable action) {
        try {
            action.run();
            throw new AssertionError("Se esperaba rechazar un dato inválido");
        } catch (IllegalArgumentException esperado) {
            // Correcto.
        }
    }
}
