/**
 * La classe principal MinesMLM conté el mètode main que inicia el joc de MinesMLM.
 * El joc està controlat per la classe Controlador.
 *
 * @author Marcos Lopez Medina
 * @version 1.0
 * @since 2024-01-23
 */
package MinesMLM;

import MinesMLM.Controlador.Controlador;

/**
 * La classe MinesMLM conté el mètode main per iniciar el joc MinesMLM.
 */
public class MinesMLM {
    public static void main(String[] args) {
        // Inicia el joc cridant al mètode jugar() de la classe Controlador.
        Controlador.jugar();
    }
}
