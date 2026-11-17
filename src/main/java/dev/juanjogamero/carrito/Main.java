package dev.juanjogamero.carrito;

import java.math.BigDecimal;
import java.util.Locale;

/** Ejemplo ejecutable del flujo de compra original, ampliado con operaciones. */
public final class Main {
    private Main() {}

    public static void main(String[] args) {
        Locale.setDefault(Locale.forLanguageTag("es-ES"));

        Carrito carrito = new Carrito();
        carrito.agregar(new Elemento("Tarjeta SD 64 GB", new BigDecimal("19.95"), 2));
        carrito.agregar(new Elemento("Canon EOS 2000D", new BigDecimal("449.00"), 1));

        mostrarResumen("Primer resumen", carrito);

        carrito.agregar(new Elemento("Samsung Galaxy Tab", new BigDecimal("199.00"), 3));
        carrito.agregar(new Elemento("Tarjeta SD 64 GB", new BigDecimal("19.95"), 1));

        mostrarResumen("Continuamos la compra", carrito);

        carrito.actualizarCantidad("Tarjeta SD 64 GB", 2);
        carrito.eliminar("Canon EOS 2000D");
        mostrarResumen("Carrito actualizado", carrito);
    }

    private static void mostrarResumen(String titulo, Carrito carrito) {
        System.out.println("\n" + titulo);
        System.out.println("--------------------------------");
        System.out.print(carrito);
        System.out.printf("Productos diferentes: %d | Unidades: %d%n", carrito.numeroProductos(), carrito.numeroUnidades());
        System.out.println("Total: " + carrito.importeTotal().toPlainString() + " €");
    }
}
