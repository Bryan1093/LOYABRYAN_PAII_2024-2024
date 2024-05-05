package modelos;

public class Vida {
    private int cantidad;
    
    public Vida(int cantidadInicial) {
        this.cantidad = cantidadInicial;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public void restarVida(int cantidad) {
        this.cantidad -= cantidad;
        if (this.cantidad < 0) {
            this.cantidad = 0; // Asegurar que la vida no sea menor que 0
        }
    }
}
