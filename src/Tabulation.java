import java.util.Arrays;
import java.util.ArrayList;

public class Tabulation {
    // Atributos
    private Mochila m;
    private String elapsedTime;
    private int benefit;
    private int room;
    private ArrayList<Integer> taken = new ArrayList<>();
    
    /**
     * Constructor con parámetro
     * @param m - Mochila que se asigna a self
     */
    public Tabulation(Mochila m){
        this.m = m;
    }

    /**
     * Algoritmo Tabulation
     */
    public void solve_Tabulation(){
        // Tiempo de ejecución
        long startTime = System.nanoTime();

        int n = m.valores[1].length;
        int W = m.peso_max;
        int tabulation[][] = new int[n][W+1];
        for (int i = 0; i < tabulation.length; i++) Arrays.fill(tabulation[i], 0);

        // Beneficio
        for (int i = 1; i < n; i++){
            for (int j = 1; j < W+1; j++){
                if (m.pesos[1][i] <= j){
                    if (m.valores[1][i] + tabulation[i-1][j - m.pesos[1][i]] > tabulation[i-1][j]){
                        tabulation[i][j] = m.valores[1][i] + tabulation[i-1][j - m.pesos[1][i]];
                    } else tabulation[i][j] = tabulation[i-1][j];
                } else tabulation[i][j] = tabulation[i-1][j];
            }
        }

        // Taken
        int i = n-1;
        int k = W;
        while (i > 0 && k > 0){
            if (tabulation[i][k] != tabulation[i-1][k]){
                taken.add(m.valores[0][i]);
                k = k - m.pesos[1][i];
            } else i -= 1;
        }
        int peso_total = 0;
        for(int h=0; h<taken.size(); h++){
            peso_total += m.pesos[1][taken.get(h)];
        }
        room = m.peso_max - peso_total;
        benefit = tabulation[n-1][W];
        // Tiempo de ejecución - Se pasa a String de dos dígitos en formato de segundos
        elapsedTime = String.format("%.2f", (double) (System.nanoTime() - startTime)/1_000_000_000);
    }

    /**
     * @return beneficio
     */
    public int getBenefit() {
        return this.benefit;
    }

    /**
     * @return espacio sin usar de la mochila tabulation
     */
    public int getRoom() {
        return this.room;
    }

    /**
     * @return identificadores de los items escogidos de la mochila tabulation
     */
    public ArrayList<Integer> getTaken() {
        return this.taken;
    }

    /**
     * @return tiempo de ejecución del algoritmo tabulation
     */
    public String getElapsedTime() {
        return this.elapsedTime;
    }    
}
