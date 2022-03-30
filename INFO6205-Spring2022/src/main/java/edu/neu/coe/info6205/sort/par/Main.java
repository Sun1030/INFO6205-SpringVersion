package edu.neu.coe.info6205.sort.par;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
//        processArgs(args);
//        System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
//        Random random = new Random();
//        int[] array = new int[2000000];
//        ArrayList<Long> timeList = new ArrayList<>();
//        for (int j = 50; j < 100; j++) {
//            ParSort.cutoff = 10000 * (j + 1);
//
//            // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
//            long time;
//            long startTime = System.currentTimeMillis();
//            for (int t = 0; t < 10; t++) {
//                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
//                ParSort.sort(array, 0, array.length);
//            }
//            long endTime = System.currentTimeMillis();
//            time = (endTime - startTime);
//            timeList.add(time);
//
//
//            System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms");
//
//        }
//        try {
//            FileOutputStream fis = new FileOutputStream("./src/result.csv");
//            OutputStreamWriter isr = new OutputStreamWriter(fis);
//            BufferedWriter bw = new BufferedWriter(isr);
//            int j = 0;
//            for (long i : timeList) {
//                String content = (double) 10000 * (j + 1) / 2000000 + "," + (double) i / 10 + "\n";
//                j++;
//                bw.write(content);
//                bw.flush();
//            }
//            bw.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        //5 different length of array
        int[] sizeofArray = {500000, 1000000, 2000000, 4000000, 8000000};
        //if already have a result.csv delete it first
        try{
            Path path = Paths.get("./src/result.csv");
            Files.deleteIfExists(path);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        for(int size = 0; size < sizeofArray.length; size++) {
            for (int i = 1; i < 128; i = i * 2) { //stop at 64
                doSort(i, sizeofArray[size]);
            }
        }
    }

    public static void doSort(int number, int size){
        ForkJoinPool partPool = new ForkJoinPool(number);
        System.out.println("Degree of parallelism: " + partPool.getParallelism() + " Array Size:" + size);
        Random random = new Random();
        System.out.println();
        ArrayList<Long> timeList = new ArrayList<>();
        int[] array = new int[size];
        for (int j = 0; j < 10; j++) {
            ParSort.cutoff = 5000 * (j + 1); //from 5000 to 50000, increase 5000 each time
            // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
            long time;
            long startTime = System.currentTimeMillis();
            for (int t = 0; t < 10; t++) {
                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length);
            }
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);
            timeList.add(time);

            System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms");

        }
        try {
            FileOutputStream fis = new FileOutputStream("./src/result.csv", true);
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            String infoOfTest = "Number of threads," + number + ",Length of array," + size + "\n" + "Test #,cutoff,10 Times Time\n";
            bw.write(infoOfTest);
            int j = 1;
            for (long i : timeList) {
                String content = (double) j / 10 + "," + 5000 * j + "," +  (double) i / 10 + "\n";
                j++;
                bw.write(content);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) //noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();
    }

    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }

    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();


}
