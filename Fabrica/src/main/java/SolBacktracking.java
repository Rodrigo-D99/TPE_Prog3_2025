import java.util.Collections;
import java.util.LinkedList;

/*
 * SolBacktracking implementa un algoritmo de backtracking para encontrar combinaciones
 * de máquinas cuya suma de piezas iguale un total dado, minimizando la cantidad de máquinas usadas.
 *
 * Estrategia de resolución:
 * - Se genera un árbol de exploración donde cada nodo representa la inclusión de una máquina en la solución parcial.
 * - Cada estado mantiene una lista temporal de máquinas y la suma actual de piezas.
 * - Un estado es final cuando la suma de piezas alcanza el total requerido (piezasTotales).
 * - Un estado es solución si cumple lo anterior y usa menos máquinas que la mejor solución encontrada hasta ese momento.
 * - Se aplican podas para evitar continuar la exploración si:
 *   - La suma supera el total.
 *   - La solución parcial ya no puede ser mejor que la mejor conocida.
 * - Las máquinas se ordenan para facilitar la poda y mejorar la eficiencia de búsqueda.
 */

public class SolBacktracking {
    private LinkedList<Maquina> mejorSolucion; // Guarda la mejor solución (menos máquinas)
    private LinkedList<LinkedList<Maquina>> cantSolEncontradas; // Guarda todas las soluciones válidas encontradas para ver cuantas encontro
    private int piezastotales, estadosGenerados; // Total requerido de piezas y contador de estados explorados

    public SolBacktracking(int piezasTotales) {
        this.piezastotales = piezasTotales;
        estadosGenerados = 0;
        mejorSolucion = new LinkedList<>();
        cantSolEncontradas = new LinkedList<>();
    }

    public int getPiezastotales() {
        return piezastotales;
    }

    public void solBack(LinkedList<Maquina> maquinas){
        // Verifica que haya máquinas disponibles para explorar
        if(!maquinas.isEmpty()) {
            System.out.println("\n--------------Solucion Backtracking---------------");

            // Ordena las máquinas (por cantidad de piezas) para favorecer las podas
            Collections.sort(maquinas);

            // Inicia la exploración del árbol con cada máquina como punto de partida
            for (Maquina ma : maquinas) {
                solBack(maquinas, ma, new LinkedList<>(), 0);
            }

            // Muestra la mejor solución encontrada, si existe
            if(!mejorSolucion.isEmpty()){
                mostrarSulucion();
            }
            else{
                System.out.println("No se encontro una solucion");
            }
        }
    }

    private void solBack(LinkedList<Maquina> maquinas, Maquina maq, LinkedList<Maquina> temp , int sumaPiezas) {
        estadosGenerados++; // Cuenta el estado actual generado
        temp.add(maq); // Agrega la máquina actual a la solución parcial
        sumaPiezas += maq.getPiezas(); // Actualiza la suma de piezas

        // Caso base: se alcanza exactamente el total de piezas deseado
        if (sumaPiezas == piezastotales && esSolucion(temp)) {
            cantSolEncontradas.add(new LinkedList<>(temp)); // Guarda copia de la solución encontrada
            mejorSolucion.clear(); // Reemplaza la mejor solución
            mejorSolucion.addAll(temp);
        } else {
            // Si aún se puede seguir explorando (no se supera el total y la solución puede mejorar)
            if (sumaPiezas < piezastotales && esSolucion(temp)) {
                for (Maquina m : maquinas) {
                    solBack(maquinas, m, temp, sumaPiezas); // Explora con cada máquina
                    temp.removeLast(); // Deshace el paso (backtracking)
                }
            }
        }
    }
    // Determina si la solución actual es mejor que la guardada (menos máquinas)
    private boolean esSolucion(LinkedList<Maquina> temp){
        if (mejorSolucion.isEmpty()) {
            return true;
        }
        return temp.size() < mejorSolucion.size();
    }
    private void mostrarSulucion() {
        System.out.println("Mejor Solucion obtenida: ");
        System.out.println("Piezas Totales: " + piezastotales);
        mejorSolucion.forEach(m -> {
            System.out.println(m.toString());
        });
        System.out.println("Cant de Maquinas usadas: " + mejorSolucion.size());
        System.out.println("Cant de Soluciones encontradas: " + cantSolEncontradas.size());
        System.out.println("Estados Generados: " + estadosGenerados);
    }


}
