```java
*/
package MinesMLM.Controlador;
import MinesMLM.Models.Model;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe que conté les funcions per interactuar amb l'usuari i coordinar les accions amb el model del joc de Mines.
 */
public class Controlador {
    private static Scanner scanner = new Scanner(System.in);
    /**
     * Inicia el joc de Mines i gestiona les accions de l'usuari fins que el joc finalitza.
     */
    public static void jugar() {
        Model.inicialitzarJoc(10, 10, 5);
        boolean juegoActivo = true;

        while (juegoActivo) {
            System.out.println("¿Vols posar una bandera, trepitjar o acabar? (Bandera/Acabar/Trepitjar)");
            String opcio = scanner.nextLine().toLowerCase();

            switch (opcio) {
                case "bandera":
                    realizarAccionBandera();
                    break;
                case "trepitjar":
                    realizarAccionTrepitjar();
                    juegoActivo = !Model.ComprovarFinalitzat();
                    break;
                case "acabar":
                    juegoActivo = false;
                    Model.mostrarSolucio();
                    Model.mostrarResultado();
                    break;
                default:
                    System.out.println("Opció no vàlida. Torna-ho a provar.");
                    break;
            }
        }
    }
    /**
     * Realitza l'acció de posar una bandera a una casella especificada per l'usuari.
     */
    private static void realizarAccionBandera() {
        try {
            char lletraFila;
            int columna;
            System.out.println("Introdueix la fila (lletra):");
            lletraFila = scanner.nextLine().toUpperCase().charAt(0);
            int fila = lletraFila - 'A';

            System.out.println("Introdueix la columna:");
            columna = scanner.nextInt();
            scanner.nextLine();

            if (Model.verificarFilaIColumna(fila, columna)) {
                Model.posarBandera(fila, columna);
            } else {
                System.out.println("Coordenades no vàlides. Torna-ho a provar.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no vàlida. Torna-ho a provar.");
        }
    }
    /**
     * Realitza l'acció de trepitjar una casella especificada per l'usuari.
     */
    private static void realizarAccionTrepitjar() {
        try {
            char lletraFila;
            int columna;
            System.out.println("Introdueix la fila (lletra):");
            lletraFila = scanner.nextLine().toUpperCase().charAt(0);
            int fila = lletraFila - 'A';

            System.out.println("Introdueix la columna:");
            columna = scanner.nextInt();
            scanner.nextLine();

            if (Model.verificarFilaIColumna(fila, columna)) {
                Model.trepitjarCasella(fila, columna);
            } else {
                System.out.println("Coordenades no vàlides. Torna-ho a provar.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada no vàlida. Torna-ho a provar.");
        }
    }
}
```
