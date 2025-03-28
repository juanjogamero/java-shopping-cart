package CARRITO;
import java.util.ArrayList;

public class Carrito {
    ArrayList<Elemento> elementos;
    
    public Carrito(){
        this.elementos = new ArrayList<>();
    }
    
    public void agrega(Elemento elemento){
        elementos.add(elemento);
    }

    public int numeroElementos(){
        return elementos.size();
    }

    public double importeTotal(){
        double total = 0;
        for (Elemento elemento : elementos) {
            total += elemento.getCantidad() * elemento.getPrecio();
        }
        return total;
    }

    @Override
    public String toString() {
        return  ""+elementos;
    }
}
