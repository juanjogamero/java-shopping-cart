package dev.juanjogamero.carrito;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Carrito de compra en memoria. Los productos repetidos se agrupan por nombre. */
public final class Carrito {
    private final List<Elemento> elementos = new ArrayList<>();

    public void agregar(Elemento nuevo) {
        Objects.requireNonNull(nuevo, "No se puede añadir un producto nulo");
        int indice = buscarIndice(nuevo.getNombre());
        if (indice < 0) {
            elementos.add(nuevo);
            return;
        }
        Elemento actual = elementos.get(indice);
        if (actual.getPrecioUnitario().compareTo(nuevo.getPrecioUnitario()) != 0) {
            throw new IllegalArgumentException("El mismo producto no puede tener precios diferentes");
        }
        elementos.set(indice, actual.conCantidad(Math.addExact(actual.getCantidad(), nuevo.getCantidad())));
    }

    public boolean eliminar(String nombre) {
        int indice = buscarIndice(nombre);
        if (indice < 0) return false;
        elementos.remove(indice);
        return true;
    }

    /** Una cantidad cero elimina el artículo; las cantidades negativas se rechazan. */
    public void actualizarCantidad(String nombre, int cantidad) {
        if (cantidad < 0) throw new IllegalArgumentException("La cantidad no puede ser negativa");
        int indice = buscarIndice(nombre);
        if (indice < 0) throw new IllegalArgumentException("Producto no encontrado: " + nombre);
        if (cantidad == 0) elementos.remove(indice);
        else elementos.set(indice, elementos.get(indice).conCantidad(cantidad));
    }

    public void vaciar() { elementos.clear(); }
    public List<Elemento> getElementos() { return List.copyOf(elementos); }
    public int numeroProductos() { return elementos.size(); }

    public int numeroUnidades() {
        int total = 0;
        for (Elemento elemento : elementos) total = Math.addExact(total, elemento.getCantidad());
        return total;
    }

    public BigDecimal importeTotal() {
        BigDecimal total = new BigDecimal("0.00");
        for (Elemento elemento : elementos) total = total.add(elemento.getSubtotal());
        return total;
    }

    private int buscarIndice(String nombre) {
        Objects.requireNonNull(nombre, "El nombre no puede ser nulo");
        for (int i = 0; i < elementos.size(); i++) {
            if (elementos.get(i).getNombre().equalsIgnoreCase(nombre.trim())) return i;
        }
        return -1;
    }

    @Override
    public String toString() {
        if (elementos.isEmpty()) return "El carrito está vacío.";
        StringBuilder resultado = new StringBuilder();
        for (Elemento elemento : elementos) resultado.append(elemento).append(System.lineSeparator());
        return resultado.toString();
    }
}
