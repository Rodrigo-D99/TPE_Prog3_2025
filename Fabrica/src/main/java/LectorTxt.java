import java.io.*;
import java.util.LinkedList;

/*
 * LectorTxt se encarga de:
 * - Leer un archivo de texto con formato específico desde la carpeta de recursos.
 * - Construir una lista de objetos Maquina con los datos del archivo.
 * - Obtener la cantidad total de piezas a producir.
 * - Ejecutar y comparar dos estrategias de resolución (Backtracking y Greedy).
 *
 * Formato del archivo esperado (Maquinas.txt):
 * Línea 1: Número entero que indica la cantidad total de piezas.
 * Líneas siguientes: Una por máquina, con nombre y cantidad de piezas separadas por coma (Ej: M1,5).
 *
 * Flujo del programa:
 * - Se obtiene el InputStream del archivo ubicado en resources usando el classloader.
 * - Se parsea la primera línea para obtener las piezasTotales.
 * - Se crea una lista de objetos Maquina con las líneas restantes.
 * - Se imprime el listado de máquinas leídas.
 * - Se ejecuta el algoritmo de Backtracking con una copia de la lista.
 * - Se ejecuta el algoritmo Greedy con otra copia.
 *
 * Errores manejados:
 * - Archivo no encontrado.
 * - Formato numérico inválido.
 */

public class LectorTxt {

    public static void main(String[] args) throws IOException {
        try {
            // Cargar archivo desde carpeta "resources" del proyecto usando el classloader
            InputStream is = LectorTxt.class.getClassLoader().getResourceAsStream("Maquinas.txt");

            // Validar que el archivo fue encontrado
            if (is == null) {
                throw new IOException("No se encontró el archivo Maquinas.txt en resources");
            }

            // Lectura del archivo línea por línea
            BufferedReader lector = new BufferedReader(new InputStreamReader(is));

            // Leer la primera línea que contiene la cantidad total de piezas a producir
            int piezasTotales = Integer.parseInt(lector.readLine().trim());
            System.out.println("Piezas totales: " + piezasTotales);

            LinkedList<Maquina> maquinas = new LinkedList<>();
            String linea;

            // Leer y procesar el resto del archivo (una línea por máquina)
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                if (partes.length == 2) {
                    String nombre = partes[0].trim();             // Nombre de la máquina (ej. "M1")
                    int piezas = Integer.parseInt(partes[1].trim()); // Piezas que produce (ej. 5)
                    Maquina maquina = new Maquina(nombre, piezas);
                    maquinas.add(maquina);
                }
            }

            // Mostrar las máquinas cargadas desde el archivo
            System.out.println("Listado de máquinas:");
            for (Maquina m : maquinas) {
                System.out.println(m);
            }

            // Crear e invocar estrategia Backtracking con una copia de la lista
            SolBacktracking back = new SolBacktracking(piezasTotales);
            back.solBack(new LinkedList<>(maquinas)); // Paso por copia

            // Crear e invocar estrategia Greedy con una copia de la lista
            SolGreedy greedy = new SolGreedy(piezasTotales);
            greedy.solGreedy(new LinkedList<>(maquinas)); // Paso por copia

        } catch (IOException | NumberFormatException e) {
            // Manejo de errores de lectura o formato numérico
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}
