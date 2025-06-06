import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Maquina m1 = new Maquina("M1", 13);
        Maquina m2 = new Maquina("M2", 3);
        Maquina m3 = new Maquina("M3", 4);
        Maquina m4 = new Maquina("M4", 1);
        //Maquina m5 = new Maquina("M5", 13);
        SolBacktracking back=new SolBacktracking(12);
        LinkedList<Maquina> maquinas = new LinkedList<>();
        maquinas.add(m1);
        maquinas.add(m4);
        maquinas.add(m3);
        maquinas.add(m2);
        //maquinas.add(m5);
        back.solBack(maquinas);
    }
}
