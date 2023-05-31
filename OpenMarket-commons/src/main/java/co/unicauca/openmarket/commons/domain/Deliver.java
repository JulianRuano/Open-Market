
package co.unicauca.openmarket.commons.domain;

/**
 *
 * @author brayan
 */
public class Deliver {
    private int idCompra;
    private int puntuacion;

    public Deliver() {
    }

    public Deliver(int idCompra, int puntuacio) {
        this.idCompra = idCompra;
        this.puntuacion = puntuacio;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getPuntuacio() {
        return puntuacion;
    }

    public void setPuntuacio(int puntuacio) {
        this.puntuacion = puntuacio;
    }  
}
