/**
 * Classe que representa el model del joc de Mines.
 * Conté funcions per inicialitzar, gestionar l'estat del joc i verificar el seu final.
 * Utilitza la classe Vista per mostrar la informació del camp de mines.
 *
 * @author Marcos Lopez Medina
 * @version 1.0
 * @since 2024-01-23
 */
package MinesMLM.Models;
import MinesMLM.Vista.Vista;
import java.util.Scanner;

/**
 * Classe que conté les funcions i variables per gestionar el model del joc de Mines.
 */
public class Model {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Variables per gestionar l'estat del joc i la informació del camp de mines.
     */
    private static int files;
    private static int columnes;
    private static int bombes;
    private static boolean jocFinalitzat;
    private static String[][] campMinesOcult;
    private static String[][] campMinesVisible;

    /**
     * Inicialitza el joc amb les dimensions i nombre de bombes especificats.
     *
     * @param files    El nombre de files del camp de mines.
     * @param columnes El nombre de columnes del camp de mines.
     * @param bombes   El nombre de bombes que es posaran al camp de mines.
     */
    public static void inicialitzarJoc(int files, int columnes, int bombes) {
        Model.files = files;
        Model.columnes = columnes;
        Model.bombes = bombes;
        Model.jocFinalitzat = false;
        Model.campMinesOcult = new String[files][columnes];
        Model.campMinesVisible = new String[files][columnes];

        inicialitzarCampsMines();
        posarBombesAleatoriament();
        comptarBombesAlVoltant();
        Vista.mostrarCampMines(campMinesOcult);
        Vista.mostrarCampMines(campMinesVisible);
    }
    /**
     * Inicialitza els camps de mines ocult i visible amb valors per defecte.
     */
    public static void inicialitzarCampsMines() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                campMinesOcult[i][j] = " ";
                campMinesVisible[i][j] = "·";
            }
        }
    }

    /**
     * Posiciona les bombes aleatòriament al camp de mines ocult.
     */
    public static void posarBombesAleatoriament() {
        int bombesPosades = 0;
        while (bombesPosades < bombes) {
            int fila = (int) (Math.random() * files);
            int columna = (int) (Math.random() * columnes);
            if (!campMinesOcult[fila][columna].equals("*")) {
                campMinesOcult[fila][columna] = "*";
                bombesPosades++;
            }
        }
    }

    /**
     * Compta el nombre de bombes al voltant de cada casella no bomba i actualitza el camp de mines ocult.
     */
    public static void comptarBombesAlVoltant() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (!campMinesOcult[i][j].equals("*")) {
                    int bombesAlVoltant = 0;
                    for (int k = -1; k <= 1; k++) {
                        for (int l = -1; l <= 1; l++) {
                            if (i + k >= 0 && i + k < files && j + l >= 0 && j + l < columnes) {
                                if (campMinesOcult[i + k][j + l].equals("*")) {
                                    bombesAlVoltant++;
                                }
                            }
                        }
                    }
                    campMinesOcult[i][j] = String.valueOf(bombesAlVoltant);
                }
            }
        }
    }
    /**
     * Verifica si el jugador ha guanyat el joc.
     *
     * @return Cert si el jugador ha guanyat; Fals altrament.
     */
    public static boolean haGuanyat() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (campMinesOcult[i][j].equals("*")) {
                    if (!campMinesVisible[i][j].equals("⚑")) {
                        return false;
                    }
                } else if (campMinesVisible[i][j].equals("·")) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Mostra el resultat del joc, indicant si el jugador ha perdut o ha abandonat.
     */
    public static void mostrarResultado() {
        if (jocFinalitzat) {
            mostrarSolucio();
            System.out.println("¡Has perdido el juego!");
        } else {
            System.out.println("¡Has abandonado el juego!");
        }
    }
    /**
     * Mostra la solució del joc en cas de derrota.
     */
    public static void mostrarSolucio() {
        for (int i = 0; i < files; i++) {
            for (int j = 0; j < columnes; j++) {
                if (campMinesOcult[i][j].equals("*")) {
                    campMinesVisible[i][j] = "*";
                }
            }
        }
        Vista.mostrarCampMines(campMinesVisible);
    }
    /**
     * Destapa una casella en les coordenades especificades i gestiona les accions segons el contingut de la casella.
     *
     * @param fila     La fila de la casella.
     * @param columna  La columna de la casella.
     */
    public static void destaparCasella(int fila, int columna) {
        if (campMinesOcult[fila][columna].equals("*")) {
            jocFinalitzat = true;
            mostrarSolucio();
            System.out.println("¡Has aixafat una bomba! Final del joc.");
        } else if (campMinesOcult[fila][columna].equals("0") && campMinesVisible[fila][columna].equals("·")) {
            destaparCasillasAlrededor(fila, columna);
        } else if (campMinesVisible[fila][columna].equals("·")) {
            campMinesVisible[fila][columna] = campMinesOcult[fila][columna];
        }
    }
    private static void destaparCasillasAlrededor(int fila, int columna) {
        campMinesVisible[fila][columna] = campMinesOcult[fila][columna];

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int novaFila = fila + i;
                int novaColumna = columna + j;
                if (verificarFilaIColumna(novaFila, novaColumna) && campMinesVisible[novaFila][novaColumna].equals("·")) {
                    destaparCasella(novaFila, novaColumna);
                }
            }
        }
    }
    public static void trepitjarAlVoltant(int fila, int columna) {
        if (verificarFilaIColumna(fila, columna) && campMinesVisible[fila][columna].equals("·")) {
            destaparCasella(fila, columna);

            if (campMinesOcult[fila][columna].equals("0")) {
                destaparCasillasAlrededor(fila, columna);
            }
        }
    }

    public static boolean verificarFilaIColumna(int fila, int columna) {
        return fila >= 0 && fila < files && columna >= 0 && columna < columnes;
    }
    public static void posarBandera(int fila, int columna) {
        if (campMinesVisible[fila][columna].equals("·")) {
            campMinesVisible[fila][columna] = "⚑";
            if (haGuanyat()) {
                System.out.println("¡Felicidades! Has ganado el juego.");
                mostrarSolucio();
                jocFinalitzat = true;
            }
        } else if (campMinesVisible[fila][columna].equals("⚑")) {
            campMinesVisible[fila][columna] = "·";
        }
        Vista.mostrarCampMines(campMinesVisible);
    }

    public static void trepitjarCasella(int fila, int columna) {
        if (campMinesVisible[fila][columna].equals("·")) {
            if (campMinesOcult[fila][columna].equals("*")) {
                jocFinalitzat = true;
                mostrarSolucio();
                System.out.println("¡Has pisado una mina! Fin del juego.");
                return;
            } else {
                destaparCasella(fila, columna);
                if (campMinesOcult[fila][columna].equals("0")) {
                    trepitjarAlVoltant(fila, columna);
                }
                if (haGuanyat()) {
                    System.out.println("¡Felicidades! Has ganado el juego.");
                    mostrarSolucio();
                }
            }
        } else {
            System.out.println("¡Ja has pisat aquesta casella o té una bandera!");
        }

        Vista.mostrarCampMines(campMinesVisible);
    }
    public static boolean ComprovarFinalitzat() {
        return jocFinalitzat;
    }
}
