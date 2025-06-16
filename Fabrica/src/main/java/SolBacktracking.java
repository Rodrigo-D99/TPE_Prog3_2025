
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class SolBacktracking {
    private LinkedList<Maquina> mejorSolucion;
    private LinkedList<LinkedList<Maquina>> cantSolEncontradas;
    private int piezastotales, estadosGenerados;


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
        //me aseguro que el arreglo de maquinas no este vacio
        if(!maquinas.isEmpty()) {
            System.out.println("\n--------------Solucion Backtracking---------------");
            //Lo ordeno de mayor a menor por la cant de piezas que hacen cada maquina
            Collections.sort(maquinas);
            //Si las piezas totales son menores a la cant de piezas que produce la maquina del medio
            //Ordeno las maquinas de menor produccion a mayor


            //Le paso la primera maquina del arreglo ordenado y llamo a solucion back y prueba
            for (Maquina ma : maquinas) {
                solBack(maquinas,ma, new LinkedList<>(), 0);
            }
            //si la solucion no esta vacia la imprimo
            if(!mejorSolucion.isEmpty()){
                mostrarSulucion();
            }
            else{
                System.out.println("No se encontro una solucion");
            }
        }
    }


    private void solBack(LinkedList<Maquina> maquinas,Maquina maq, LinkedList<Maquina> temp ,int sumaPiezas) {

        estadosGenerados++;
        temp.add(maq);
        sumaPiezas+=maq.getPiezas();

        if(sumaPiezas==piezastotales&& esSolucion(temp)) {
            cantSolEncontradas.add(temp);
            mejorSolucion.clear();
            mejorSolucion.addAll(temp);
        }
        else {
            if (sumaPiezas-piezastotales>=maquinas.get((maquinas.size()/2)-1).getPiezas()) {
                maquinas.sort(Comparator.reverseOrder());
            }
            if (sumaPiezas<piezastotales&&esSolucion(temp)) {
                for (Maquina m : maquinas) {
                    solBack(maquinas,m,temp,sumaPiezas);
                    temp.removeLast();
                }
            }
        }
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
    private boolean esSolucion(LinkedList<Maquina> temp){
        if(mejorSolucion.isEmpty()) {
            return true;
        }
        return temp.size()<mejorSolucion.size();
    }
}
