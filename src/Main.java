import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    public static void main(String[] args){
        // Prioridad el mensaje de ayuda
        if (AppUtils.contains(args, "-h", "--help")) AppUtils.displayHelp();
        
        // Asignación de método por el que resolver
        // Si no se ha especificado, muestra mensaje de error
        int sx = 0;
        if (AppUtils.contains(args, "-sg", "--greedy")) sx = 1;
        if (AppUtils.contains(args, "-st", "--tabulation")) sx = 2;
        if (AppUtils.contains(args, "-sm", "--memoization")) sx = 3;
        if (sx == 0) AppUtils.check_errors(1);

        Mochila m;
        /**
         * Procesamiento de ficheros del directorio
         * Se encuentra el directorio, se crea un array con todos los ficheros del directorio,
         * se ordenan alfanumericamente (1, 2, 100... en vez de 1, 100, 2...) y se procesan mediante el algoritmo especificado
         */
        if (AppUtils.contains(args, "-d", "--directory")){
            File file = new File(args[AppUtils.indexOf(args, "-d", "--directory") + 1]);
            String[] contenido = file.list();
            Comparator<String> numericalOrder = AlphanumericSortComparator.NUMERICAL_ORDER;
            Arrays.sort(contenido, numericalOrder);
            switch(sx){
                // Greedy
                case 1:
                    for (String fich : contenido){
                        m = AppUtils.fromDataToItems(file + "/" + fich);
                        Greedy gr = new Greedy(m);
                        gr.solve_Greedy();
                        System.out.print("Greedy: " + fich + "\t");
                        AppUtils.arggr(args, gr);
                        System.out.println();
                    }
                    break;
                // Tabulation
                case 2:
                    for (String fich : contenido){
                        m = AppUtils.fromDataToItems(file + "/" + fich);
                        Tabulation tb = new Tabulation(m);
                        tb.solve_Tabulation();
                        System.out.print("Tabulation: " + fich + "\t");
                        AppUtils.argtb(args, tb);
                        System.out.println();
                    }
                    break;
                // Memoization
                case 3:
                    for (String fich : contenido){
                        m = AppUtils.fromDataToItems(file + "/" + fich);
                        Memoization mm = new Memoization(m);
                        mm.solve_Memoization();
                        System.out.print("Memoization: " + fich + "\t");
                        AppUtils.argmm(args, mm);
                        System.out.println();
                    }
            }
        /**
         * Procesamiento de ficheros únicos
         * Se encuentra el fichero y se procesan mediante el algoritmo especificado
         */
        } else if (AppUtils.contains(args, "-f", "--file")){
            String inp = args[AppUtils.indexOf(args, "-f", "--file") + 1];
            switch(sx){
                // Greedy
                case 1:
                    m = AppUtils.fromDataToItems(inp);
                    Greedy gr = new Greedy(m);
                    gr.solve_Greedy();
                    System.out.print("Greedy: " + inp + "\t");
                    AppUtils.arggr(args, gr);
                    break;
                // Tabulation
                case 2:
                    m = AppUtils.fromDataToItems(inp);
                    Tabulation tb = new Tabulation(m);
                    tb.solve_Tabulation();
                    System.out.print("Tabulation: " + inp + "\t");
                    AppUtils.argtb(args, tb);
                    break;
                // Memoization
                case 3:
                    m = AppUtils.fromDataToItems(inp);
                    Memoization mm = new Memoization(m);
                    mm.solve_Memoization();
                    System.out.print("Memoization: " + inp + "\t");
                    AppUtils.argmm(args, mm);
                    break;
            }
        /**
         * Si no se especifica qué procesar, mostrar mensaje de error
         */
        } else AppUtils.check_errors(0);

    }
}
