public class Mochila {
    // Atributos
    protected int[][] valores;
    protected int[][] pesos;
    protected int items;
    protected int peso_max;

    // Constructor vacío
    public Mochila(){}

    /**
     * Constructor con parámetros
     * @param items - número de items
     * @param peso_max - capacidad de la mochila
     * @param v - array con los valores
     * @param p - array con los pesos
     */
    public Mochila(int items, int peso_max, int[] v, int[] p){
        // Tamaño 2, en el primer índice se encontrarán los índices o identificadores de los items
        // En el segundo índice se encontrarán los valores correspondientes de cada uno
        valores = new int[2][v.length];
        pesos = new int[2][p.length];
        this.items = items;
        this.peso_max = peso_max;

        for (int i = 0; i <= valores.length - 1; i++){
            if (i == 0){
                for (int j = 0; j <= valores[i].length - 1; j++){
                    valores[i][j] = j;
                    pesos[i][j] = j;
                }
            } else {
                for (int j = 0; j <= valores[i].length - 1; j++){
                    valores[i][j] = v[j];
                    pesos[i][j] = p[j];
                }
            }
        }
    }

    /**
     * @return copia de la mochila (deep copy, no tienen relación entre sí)
     */
    public Mochila copyMochila(){
        int v[] = new int[this.valores[1].length];
        int p[] = new int[this.pesos[1].length];
        
        for (int i = 0; i < this.valores[1].length; i++){
            v[i] = this.valores[1][i];
            p[i] = this.pesos[1][i];
        }

        Mochila res = new Mochila(this.items, this.peso_max, v, p);
        return res;
    }
}
