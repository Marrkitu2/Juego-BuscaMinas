/**
 * Classe que representa la vista del joc de Mines.
 * Conté funcions per mostrar la informació del camp de mines a l'usuari.
 *
 * @author Marcos Lopez Medina
 * @version 1.0
 * @since 2024-01-23
 */
package MinesMLM.Vista;
/**
 * Classe que conté funcions per mostrar la informació del camp de mines a l'usuari.
 */
public class Vista {
    /**
     * Mostra el camp de mines a l'usuari.
     *
     * @param campMines Una matriu que representa l'estat del camp de mines.
     */
    public static void mostrarCampMines(String[][] campMines) {
        System.out.print("  ");
        for (int i = 0; i < campMines[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < campMines.length; i++) {
            char letraFila = (char) ('A' + i);
            System.out.print(letraFila + " ");
            for (int j = 0; j < campMines[0].length; j++) {
                System.out.print(campMines[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
