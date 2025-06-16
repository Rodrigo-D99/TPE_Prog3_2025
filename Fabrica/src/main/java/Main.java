import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        Maquina m1 = new Maquina("M1", 4);
        Maquina m2 = new Maquina("M2", 7);
        Maquina m3 = new Maquina("M3", 3);
        Maquina m4 = new Maquina("M4", 1);
//       Maquina m5 = new Maquina("M2", 22);
//        Maquina m6 = new Maquina("M3", 6);
//        Maquina m7 = new Maquina("M4", 18);

        SolBacktracking back=new SolBacktracking(12);
        SolGreedy greedy=new SolGreedy(12);
        LinkedList<Maquina> maquinas = new LinkedList<>();
        maquinas.add(m1);
        maquinas.add(m4);
        maquinas.add(m3);
        maquinas.add(m2);
//        maquinas.add(m5);
//        maquinas.add(m6);
//        maquinas.add(m7);

        back.solBack(maquinas);
        greedy.solGreedy(maquinas);
    }
}
