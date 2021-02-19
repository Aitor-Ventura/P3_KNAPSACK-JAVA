import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AppUtils {
    // Muestra el mensaje de ayuda y sale del programa
    public static void displayHelp(){
        System.out.println("Usage: Main.java [-h] [-d [DIRECTORY] | -f [FILE]] [-b] [-dt] [-r] [-t] [-sg | -sm | -st]\n\n" +
            "   optional arguments:\n" +
            "       -h, --help                                  show this help message and exit\n" +
            "       -d [DIRECTORY], --directory [DIRECTORY]     process many files in a directory\n" +
            "       -f [FILE], --file [FILE]                    process a single file\n" +
            "       -b, --benefit                               display benefit\n" +
            "       -dt, --display_taken                        display identifier of taken items\n" +
            "       -r, --room                                  display unused knapsack weight\n" +
            "       -t, --time                                  display execution time\n" + 
            "       -sg, --greedy                               solve the knapsack 01 problem with Greedy\n" +
            "       -sm, --memoization                          solve the knapsack 01 problem with Memoization\n" +
            "       -st, --tabulation                           solve the knapsack 01 problem with Tabulation");
        System.exit(0);
    }
    // Devuelve verdadero si se pide mostrar el beneficio por pantalla, falso si no
    public static boolean benefit(String[] args){ return contains(args, "-b", "--benefit"); }

    // Devuelve verdadero si se pide mostrar los identificadores de los items escogidos por pantalla, falso si no
    public static boolean taken(String[] args){ return contains(args, "-dt", "--display_taken"); }

    // Devuelve verdadero si se pide mostrar el peso sobrante de la mochila, falso si no
    public static boolean room(String[] args){ return contains(args, "-r", "--room"); }

    // Devuelve verdadero si se pide mostrar el tiempo de ejecución por pantalla, falso si no
    public static boolean elapsedTime(String[] args){ return contains(args, "-t", "--time"); }

    /**
     * @param s - Array de Strings que contiene todas las strings
     * @param p - Se comprueba si p se encuentra en s
     * @param p2 - Se comprueba si p2 se encuentra en s
     * @return verdadero si se encuentra alguna, falso si no
     */
    public static boolean contains(String[] s, String p, String p2){
        boolean flag = false;
        for (String item : s){
            if (p.equals(item) || p2.equals(item)) flag = true;
        }
        return flag;
    }

    /**
     * @param s - Array de Strings que contiene todas las strings
     * @param p - Se comprueba si en el índice i de s se encuentra p
     * @param p2 - Se comprueba si en el índice i de s se encuentra p2
     * @return el índice de donde se encuentre cualquiera de las strings
     */
    public static int indexOf(String[] s, String p, String p2){
        for (int i = 0; i < s.length; i++){
            if (p.equals(s[i]) || p2.equals(s[i])) return i;
        }
        return 0;
    }

    /**
     * @param input - Fichero único que es procesado
     * @return mochila con la cantidad de items, la capacidad máxima, array de valores, y array de pesos
     */
    public static Mochila fromDataToItems(String input){
        try {
            ArrayList<Integer> res = new ArrayList<>();
            File fich = new File(input);
            Mochila m;
            try (Scanner s = new Scanner(fich)){
                String line[];
                int i = 0;
                while (s.hasNextLine()){
                    line = s.nextLine().split(" ");
                    res.add(i, Integer.parseInt(line[0]));
                    res.add(i+1, Integer.parseInt(line[1]));
                    i += 2;
                }

                int len = (res.size() / 2) - 1;
                int items = res.get(0), peso_max = res.get(1);
                int valores[] = new int[len], pesos[] = new int[len];
                int v = 0, p = 0;

                for (int j = 2; j < res.size(); j++){
                    if (j % 2 == 0){
                        valores[v] = res.get(j);
                        v++;
                    } else {
                        pesos[p] = res.get(j);
                        p++;
                    }
                }
                m = new Mochila(items, peso_max, valores, pesos);
            }
            return m;
        } catch (IOException e){ System.out.println(e.getMessage()); }
        Mochila m = new Mochila();
        return m;
    }

    /**
     * @param args - Array de Strings que contiene todas las strings para comprobar si se encuentran las diferentes opciones
     * @param m - Mochila greedy
     */
    public static void arggr(String[] args, Greedy m){
        if(AppUtils.benefit(args)) System.out.print(m.getBenefit() + "\t");
        if(AppUtils.taken(args)) System.out.print(m.getTaken() + "\t");
        if(AppUtils.room(args)) System.out.print(m.getRoom() + "kg\t");
        if(AppUtils.elapsedTime(args)) System.out.print(m.getElapsedTime() + "s\n");
    }
    
    /**
     * @param args - Array de Strings que contiene todas las strings para comprobar si se encuentran las diferentes opciones
     * @param m - Mochila tabulation
     */
    public static void argtb(String[] args, Tabulation m){
        if(AppUtils.benefit(args)) System.out.print(m.getBenefit() + "\t");
        if(AppUtils.taken(args)) System.out.print(m.getTaken() + "\t");
        if(AppUtils.room(args)) System.out.print(m.getRoom() + "kg\t");
        if(AppUtils.elapsedTime(args)) System.out.print(m.getElapsedTime() + "s\n");
    }

    /**
     * @param args - Array de Strings que contiene todas las strings para comprobar si se encuentran las diferentes opciones
     * @param m - Mochila memoization
     */
    public static void argmm(String[] args, Memoization m){
        if(AppUtils.benefit(args)) System.out.print(m.getBenefit() + "\t");
        if(AppUtils.taken(args)) System.out.print(m.getTaken() + "\t");
        if(AppUtils.room(args)) System.out.print(m.getRoom() + "kg\t");
        if(AppUtils.elapsedTime(args)) System.out.print(m.getElapsedTime() + "s\n");
    }

    /**
     * @param errorId - Código de error
     * Muestra por pantalla el error correspondiente
     */
    public static void check_errors(int errorId){
        switch(errorId){
            case 0:
                System.out.println("ERROR!! No se ha especificado qué leer (directorio? fichero?)");
                System.exit(0);
            case 1:
                System.out.println("ERROR!! No se ha especificado por qué método resolver el problema de la mochila (sg? st? sm?)");
                System.exit(0);
        }
    }
}
