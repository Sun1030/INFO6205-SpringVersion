package edu.neu.coe.info6205.util;


import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import java.util.ArrayList;
import java.util.List;

public class Assignment2 {
    public static void main(String[] args) {

        int[] differentN = {1000, 2000, 4000, 8000, 16000};


        for (int i = 0; i < differentN.length; i++){

            InsertionSort sorter = new InsertionSort();
            Benchmark_Timer benchmarkTimer = new Benchmark_Timer("The array type: Already ordered "
                    , null, t -> { sorter.sort((Integer[]) t, true);} , null);

            Integer[] orderedOrder = new Integer[differentN[i]];
            generateOrdered(orderedOrder);

            double mean = benchmarkTimer.runFromSupplier(() -> orderedOrder, 3000);

            System.out.println(new StringBuilder().append("n is ")
                    .append(differentN[i]).append("  The mean of running time:").append(mean).toString());
        }

        System.out.println();

        for (int i = 0; i < differentN.length; i++){
            InsertionSort sorter = new InsertionSort();
            Benchmark_Timer benchmarkTimer = new Benchmark_Timer("The array type: Random order "
                    , null, t -> { sorter.sort((Integer[]) t, true);} , null);

            Integer[] randomOrder = new Integer[differentN[i]];
            generateRandom(randomOrder);

            double mean = benchmarkTimer.runFromSupplier(() -> randomOrder, 3000);

            System.out.println(new StringBuilder().append("n is ")
                    .append(differentN[i]).append("  The mean of running time:").append(mean).toString());
        }
        System.out.println();

        for (int i = 0; i < differentN.length; i++){
            InsertionSort sorter = new InsertionSort();
            Benchmark_Timer benchmarkTimer = new Benchmark_Timer("The array type: Partially order "
                    , null, t -> { sorter.sort((Integer[]) t, true);} , null);

            Integer[] partiallyOrder = new Integer[differentN[i]];
            generatePartially(partiallyOrder, 500);

            double mean = benchmarkTimer.runFromSupplier(() -> partiallyOrder, 3000);

            System.out.println(new StringBuilder().append("n is ")
                    .append(differentN[i]).append("  The mean of running time:").append(mean).toString());
        }

        System.out.println();

        for (int i = 0; i < differentN.length; i++){
            InsertionSort sorter = new InsertionSort();
            Benchmark_Timer benchmarkTimer = new Benchmark_Timer("The array type: Reverse order "
                    , null, t -> { sorter.sort((Integer[]) t, true);} , null);

            Integer[] reverseOrder = new Integer[differentN[i]];
            generateReverse(reverseOrder);

            double mean = benchmarkTimer.runFromSupplier(() -> reverseOrder, 3000);

            System.out.println(new StringBuilder().append("n is ")
                    .append(differentN[i]).append("  The mean of running time:").append(mean).toString());
        }

    }

    /**
     * Generate an array in Ordered
     *
     * @array The array that will be generated.
     */
    public static void generateOrdered(Integer[] array){
        for(int i = 0; i < array.length; i++) array[i] = i + 1;
    }

    /**
     * Generate an array in Reverse Order
     *
     * @array The array that will be generated.
     */
    public static void generateReverse(Integer[] array){
        for(int i = 0; i < array.length; i++) array[i] = array.length - i;
    }


    /**
     * Generate an array in Random Order
     *
     * @array The array that will be generated.
     */
    public static void generateRandom(Integer[] array){
        List<Integer> random = new ArrayList<Integer>(array.length);
        for(int i = 0; i < array.length; i++){
            int randomNum = (int) (Math.random() * (array.length)) +1;
            while (random.indexOf(randomNum) != -1){
                randomNum = (int) (Math.random() * (array.length)) +1;
            }
            random.add(i, randomNum);
        }
        random.toArray(array);
    }

    /**
     * Generate an array in Partially Order
     *
     * @array The array that will be generated.
     * @n i.e. if n = 500, then the number at array[500] > array[0-499] and the number at array[1000] > array[500-999].
     */
    public static void generatePartially(Integer[] array, int n){
        List<Integer> random = new ArrayList<Integer>(array.length);
        for(int i = 0; i < array.length/n; i++) {
            for (int j = i*n; j < (i+1)*n; j++) {
                int randomNum = ((int) (Math.random() * (n)) + 1) + i * n;
                while (random.indexOf(randomNum) != -1) {
                    randomNum = ((int) (Math.random() * (n)) + 1) + i * n;
                }
                random.add(j, randomNum);
            }
        }
        random.toArray(array);
    }

    public static void toString(Integer[] array){
        for(int i = 0; i < array.length; i++) System.out.print(array[i] + " ,");
        System.out.println();
    }

}
