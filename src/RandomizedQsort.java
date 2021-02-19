import java.util.Random; 

class RandomizedQsort{     
    /**
     * @param valores - array con los valores de los items
     * @param pesos - array con los pesos de los items
     * @param low - índice en el que se desea empezar a realizar la ordenación
     * @param high - índice en el que se desea parar de realizar la ordenación
     */
    private static void random(int[][] valores, int[][] pesos,int low,int high){
        // Randomized quicksort, escogemos un valor random para el pivote que se encuentre entre low y high
        Random rand= new Random(); 
        int pivot = rand.nextInt(high-low)+low; 
        swap(valores, pivot, high);
        swap(pesos, pivot, high); 
    } 
    
    /**
     * @param valores - array con los valores de los items
     * @param pesos - array con los pesos de los índices
     * @param low - índice en el que se desea empezar a realizar la ordenación
     * @param high - índice en el que se desea parar de realizar la ordenación
     * @return índice de pivote
     */
    private static int partition(int[][] valores, int[][] pesos,int low,int high){ 
        // Se elige el pivote de manera aleatoria
        random(valores, pesos,low,high);
        int pivot = valores[1][high]; 
        int i = (low-1);
        for (int j = low; j < high; j++){ 
            if (valores[1][j] > pivot){ 
                i++;
                swap(valores, i, j);
                swap(pesos, i, j);
            } 
        } 
        swap(valores, i+1, high);
        swap(pesos, i+1, high); 
        return i+1; 
    }  

    /**
     * @param valores - array con los valores de los items
     * @param pesos - array con los pesos de los items
     * @param low - índice en el que se desea empezar a realizar la ordenación
     * @param high - índice en el que se desea parar de realizar la ordenación
     */
    public static void  _quicksort(int[][] valores, int[][] pesos,int low,int high){ 
        if (low < high){ 
            int pi = partition(valores, pesos, low, high); 
            _quicksort(valores, pesos, low, pi-1); 
            _quicksort(valores, pesos, pi+1, high); 
        } 
    } 

    /**
     * @param valores - array con los valores de los items
     * @param pesos - array con los pesos de los items
     */
    public static void quicksort(int[][] valores, int[][] pesos){ _quicksort(valores, pesos, 0, valores[0].length - 1); }

    /**
     * Realiza un intercambio entre dos posiciones de un array multidimensional
     * @param map
     * @param p1
     * @param p2
     */
    private static void swap(int[][] map, int p1, int p2){
        int x0 = map[0][p1];
        map[0][p1]=map[0][p2];
        map[0][p2] = x0;

        int x1 = map[1][p1];
        map[1][p1]=map[1][p2];
        map[1][p2] = x1;
    }
}



 

