package dev.juanjogamero.carrito;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/** Una línea de producto inmutable con importe monetario exacto. */
public final class Elemento {
    private final String nombre;
    private final BigDecimal precioUnitario;
    private final int cantidad;

    public Elemento(String nombre, BigDecimal precioUnitario, int cantidad) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        Objects.requireNonNull(precioUnitario, "El precio no puede ser nulo");
        if (nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");
        if (precioUnitario.signum() < 0) throw new IllegalArgumentException("El precio no puede ser negativo");
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor que cero");
        this.nombre = nombre.trim();
        try {
            this.precioUnitario = precioUnitario.setScale(2, RoundingMode.UNNECESSARY);
        } catch (ArithmeticException error) {
            throw new IllegalArgumentException("El precio debe tener como máximo dos decimales", error);
        }
        this.cantidad = cantidad;
    }

    public String getNombre() { return nombre; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }

    public BigDecimal getSubtotal() {
        return precioUnitario.multiply(BigDecimal.valueOf(cantidad));
    }

    public Elemento conCantidad(int nuevaCantidad) {
        return new Elemento(nombre, precioUnitario, nuevaCantidad);
    }

    @Override
    public String toString() {
        return nombre + " | " + cantidad + " × " + precioUnitario.toPlainString()
            + " € = " + getSubtotal().toPlainString() + " €";
    }
}
