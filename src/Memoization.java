import java.util.ArrayList;
import java.util.Arrays;

public class Memoization {
    // Atributos
    private Mochila m;
    private String elapsedTime;
    private int benefit;
    private int room = 0;
    private ArrayList<Integer> taken = new ArrayList<>();

    /**
     * Constructor con parámetro
     * @param m - Mochila que se asigna a self
     */
    public Memoization(Mochila m){
        this.m = m;
    }

    /**
     * Algoritmo Memoization
     */
    public void solve_Memoization(){
        // Tiempo de ejecución
        long startTime = System.nanoTime();

        int n = m.valores[1].length;
        int[][] memo = new int[n][m.peso_max+1];
        for (int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
        // Beneficio
        benefit = knapsack_helper(m.valores, m.pesos, memo, n, m.peso_max);
        // Taken y room
        encuentra(memo, m.pesos);
        // Tiempo de ejecución - Se pasa a String de dos dígitos en formato de segundos
        elapsedTime = String.format("%.2f", (double) (System.nanoTime() - startTime)/1_000_000_000);
    }

    /**
     * @param valores - array multidimensional que contiene los valores de los items 
     * @param pesos - array multidimensional que contiene los pesos de los items
     * @param memo - array multidimensional en el que se realiza el algoritmo memoization
     * @param n - longitud del array de los valores
     * @param peso_max - capacidad
     * @return beneficio
     */
    private int knapsack_helper(int[][] valores, int[][] pesos, int[][] memo, int n, int peso_max){
        if (n == 0) return 0;
        if (memo[n-1][peso_max] >  0) return memo[n-1][peso_max];
        else if (pesos[1][n-1] <= peso_max){
            benefit = Math.max(knapsack_helper(valores, pesos, memo, n-1, peso_max - pesos[1][n-1]) + valores[1][n-1],
            knapsack_helper(valores, pesos, memo, n-1, peso_max));
        }else benefit = knapsack_helper(valores, pesos, memo, n-1, peso_max);

        memo[n-1][peso_max] = benefit;
        return benefit;
    }

    /**
     * Se asigna valor a room y a taken
     * @param memo - array multidimensional en el que se realiza el algoritmo memoization
     * @param pesos - array multidimensional que contiene los pesos de los items
     */
    private void encuentra(int[][] memo, int[][] pesos){
        ArrayList<Integer> res = new ArrayList<>();
        int i = memo.length - 1;
        int j = memo[0].length - 1;
        while (i > 0){
            if (memo[i][j] != memo[i-1][j]){
                res.add(pesos[0][i]);
                j -= pesos[1][i];
            }
            i -= 1;
        }
        int peso_total = 0;
        for(int h=0; h<res.size(); h++){
            peso_total += pesos[1][res.get(h)];
        }
        room = m.peso_max - peso_total;
        taken = res;
    }

    /**
     * @return beneficio
     */
    public int getBenefit() {
        return this.benefit;
    }

    /**
     * @return espacio sin usar de la mochila memoization
     */
    public int getRoom() {
        return this.room;
    }

    /**
     * @return identificadores de los items escogidos de la mochila memoization
     */
    public ArrayList<Integer> getTaken() {
        return this.taken;
    }

    /**
     * @return tiempo de ejecución del algoritmo memoization
     */
    public String getElapsedTime() {
        return this.elapsedTime;
    }    

}
