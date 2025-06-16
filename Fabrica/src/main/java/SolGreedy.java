import java.util.Collections;
import java.util.LinkedList;

public class SolGreedy {
    private LinkedList<Maquina> mejorSolucion;
    private int piezastotales, estadosGenerados;


    public SolGreedy(int piezasTotales) {

        this.piezastotales = piezasTotales;
        estadosGenerados = 0;
        mejorSolucion = new LinkedList<>();
    }
    public void solGreedy(LinkedList<Maquina> maquinas){
        System.out.println("\n--------------Solucion Greedy---------------");
        Collections.sort(maquinas);
        solGreedyPriv(maquinas);
        mostrarSulucion();
    }
    private void solGreedyPriv(LinkedList<Maquina> maquinas){
        int cociente;
        int resto = getPiezastotales();
            for(Maquina m : maquinas){
                if(m.getPiezas() <= resto ){
                    cociente = resto/m.getPiezas();
                    resto = resto - cociente*m.getPiezas();
                    for(int i = 0; i< cociente; i++){
                        estadosGenerados++;
                        mejorSolucion.add(m);
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
        System.out.println("Estados Generados: " + estadosGenerados);

    }
    public int getPiezastotales() {
        return piezastotales;
    }

    public void setPiezastotales(int piezastotales) {
        this.piezastotales = piezastotales;
    }
}
