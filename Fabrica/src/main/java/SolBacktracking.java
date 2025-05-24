
import java.util.Collections;
import java.util.LinkedList;

public class SolBacktracking {
    private LinkedList<Maquina> mejorSolucion;

    private int piezastotales, estadosGenerados,puestasFunsionamiento;

    public SolBacktracking(int piezasTotales) {

        this.piezastotales = piezasTotales;
        estadosGenerados = 0;
        mejorSolucion = new LinkedList<>();
    }

    public int getPiezastotales() {
        return piezastotales;
    }

    public void solBack(LinkedList<Maquina> maquinas){
        if(!maquinas.isEmpty()) {
            System.out.println("\n--------------Solucion Backtracking---------------");

            Collections.sort(maquinas);
            this.puestasFunsionamiento = 0;
            for (Maquina ma : maquinas) {
                solBack(maquinas,ma, new LinkedList<>(), 0);
            }
//        if(!solucion.isEmpty()){
//
//        }
        }
    }

    private void solBack(LinkedList<Maquina> maquinas,Maquina maq, LinkedList<Maquina> temp ,int sumaPiezas) {
        estadosGenerados++;
        temp.add(maq);
        sumaPiezas+=maq.getPiezas();
        if(sumaPiezas==piezastotales&& esSolucion(temp)) {
            mejorSolucion.clear();
            mejorSolucion.addAll(temp);
        }
        else {
            if (sumaPiezas<piezastotales&&esSolucion(temp)) {
                for (Maquina m : maquinas) {
                    solBack(maquinas,m,temp,sumaPiezas);
                    temp.removeLast();
                }
            }
        }
    }

    private boolean esSolucion(LinkedList<Maquina> temp) {
        if(mejorSolucion.isEmpty()) {
            return true;
        }
        return temp.size()<mejorSolucion.size();
    }
}
