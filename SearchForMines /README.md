
# This is the person in charge of executing the program
As you can see, the language of the variables is in Catalan/Spanish, but it is something that every programmer will know how to identify and change. Likewise, copying and pasting all the codes in IntelliJ IDEA with the following folder structure, it should work:

```
(Project Name)
    |
    |
    |-src
        |- MinesMLM
             |- Controlador
                     |- Controlador.java
             |  - Models
                     |- Model.java
             |  - Vista
                     |- Vista.java
```

```java
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
```
