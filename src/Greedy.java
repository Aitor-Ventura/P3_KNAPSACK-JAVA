import java.util.ArrayList;

public class Greedy{
    // Atributos
    private Mochila m;
    private int benefit;
    private int room;
    private ArrayList<Integer> taken = new ArrayList<>();
    private String elapsedTime;

    /**
     * Constructor con parámetro
     * @param m - Mochila que se asigna a self
     */
    public Greedy(Mochila m) {
        this.m = m;
    }

    /**
     * Algoritmo Greedy
     * Se ordena en orden descendiente, y se asigna un beneficio, los identificadores de los items escogidos,
     * tiempo de ejecución, y espacio sin usar de la mochila
     */
    public void solve_Greedy(){
        // Tiempo de ejecución
        long start = System.nanoTime();
        RandomizedQsort.quicksort(m.valores, m.pesos);

        int currentWeight = 0;
        int i = 0;
        while(currentWeight <= m.peso_max && i < m.items){
            if(m.pesos[1][i] <= (m.peso_max - currentWeight)) {
                currentWeight += m.pesos[1][i];
                benefit += m.valores[1][i];
                taken.add(m.valores[0][i]);
            }
            i++;
        }
        // Tiempo de ejecución - Se pasa a String de dos dígitos en formato de segundos
        elapsedTime = String.format("%.2f", (double) (System.nanoTime() - start)/1_000_000_000);

        room = m.peso_max - currentWeight;
    }

    /**
     * @return beneficio
     */
    public int getBenefit() {
        return this.benefit;
    }

    /**
     * @return espacio sin usar de la mochila greedy
     */
    public int getRoom() {
        return this.room;
    }

    /**
     * @return identificadores de los items escogidos de la mochila greedy
     */
    public ArrayList<Integer> getTaken() {
        return this.taken;
    }

    /**
     * @return tiempo de ejecución del algoritmo greedy
     */
    public String getElapsedTime() {
        return this.elapsedTime;
    }    
}
