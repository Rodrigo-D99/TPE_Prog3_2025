import java.util.Collections;
import java.util.LinkedList;

/*
 * SolGreedy implementa una estrategia para seleccionar la combinación de máquinas
 * que sumen una cantidad determinada de piezas, buscando reducir la cantidad de máquinas utilizadas.
 *
 * Estrategia de resolución:
 * - Las máquinas se ordenan por cantidad de piezas (de mayor a menor si Maquina implementa Comparable adecuadamente).
 * - Se selecciona la máquina más "grande" posible que aún puede usarse para el resto de piezas.
 * - A cada paso, se calcula cuántas veces puede usarse esa máquina (cociente), y se descuenta del total.
 * - Se agregan todas esas instancias de la máquina a la solución.
 * - El proceso se repite con las siguientes máquinas hasta completar o quedar sin opciones.
 *
 * Estados finales y solución:
 * - La solución final se almacena en `mejorSolucion`, que contiene las máquinas elegidas.
 * - La cantidad de piezas debe igualar `piezastotales`, pero no hay verificación de que sea exacta (podría faltar o sobrar).
 * - No se garantiza que esta sea la mejor solución posible (como sí hace el backtracking).
 *
 * Ventajas:
 * - Muy rápida, pocos estados generados.
 * - Buena solución si las piezas de las máquinas son divisores del total.
 *
 * Limitaciones:
 * - No garantiza encontrar la mejor solución (ni siquiera una válida) si el greedy falla en la elección inicial.
 */

public class SolGreedy {
    private LinkedList<Maquina> mejorSolucion; // Lista con las máquinas seleccionadas
    private int piezastotales, estadosGenerados; // Total deseado y contador de estados

    public SolGreedy(int piezasTotales) {
        this.piezastotales = piezasTotales;
        estadosGenerados = 0;
        mejorSolucion = new LinkedList<>();
    }

    public void solGreedy(LinkedList<Maquina> maquinas) {
        if(!maquinas.isEmpty()) {

            System.out.println("\n--------------Solucion Greedy---------------");
            // Ordena las máquinas (se asume que Comparable las ordena de mayor a menor)
            Collections.sort(maquinas);

            // Ejecuta el algoritmo voraz sobre la lista ordenada
            solGreedyPriv(maquinas);

            // Muestra la solución obtenida
            if(!mejorSolucion.isEmpty()){
                mostrarSulucion();
            }
            else{
                System.out.println("No se encontro una solucion");
            }
        }
    }

    private void solGreedyPriv(LinkedList<Maquina> maquinas) {
        int cociente;
        int resto = getPiezastotales();

        // Recorre las máquinas desde la más grande
        for (Maquina m : maquinas) {
            if (m.getPiezas() <= resto) {
                // Calcula cuántas veces cabe esta máquina en el resto
                cociente = resto / m.getPiezas();

                // Actualiza el resto de piezas que faltan
                resto = resto - cociente * m.getPiezas();

                // Agrega esa cantidad de máquinas a la solución
                for (int i = 0; i < cociente; i++) {
                    estadosGenerados++;
                    mejorSolucion.add(m);
                }
            }
        }
        if(resto > 0){
            mejorSolucion.clear();
        }
    }

    private void mostrarSulucion() {
        System.out.println("Mejor Solucion obtenida:");
        System.out.println("Piezas Totales: " + piezastotales);
        mejorSolucion.forEach(m -> {
            System.out.println(m.toString());
        });
        System.out.println("Cant de Maquinas usadas: " + mejorSolucion.size());
        System.out.println("Estados Generados: " + estadosGenerados);
    }

    public int getPiezastotales() {
        return piezastotales;
    }

    public void setPiezastotales(int piezastotales) {
        this.piezastotales = piezastotales;
    }
}

