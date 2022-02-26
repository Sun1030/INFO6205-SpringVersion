package edu.neu.coe.info6205.union_find;

public class Assignment3_Union_Find {

    //generate a random number between 0 to i
    public static int generateRandomIndex1(int i){
        return (int)(Math.random() * i);
    }

    //generate a random number between 0 to i and different from the int n;
    public static int generateRandomIndex2(int i, int n){
        int r = (int)(Math.random() * i);
        while (r == n){
            r = (int)(Math.random() * i);
        }
        return r;
    }

    public static int count(int n){
        int countNum = 0;
        UF_HWQUPC test = new UF_HWQUPC(n);
        while(test.components() > 1){
            int i = generateRandomIndex1(n);
            int j = generateRandomIndex2(n, i);
            countNum++;
            if (!test.isConnected(i, j)) {
                test.union(i, j);
            }
        }
        return countNum;
    }

    //Call count() i times when the number if sites is n.
    public static double doMultiCount(int n, int i){
        double mulCount = 0;
        for(int j = 0; j < i; j++){
            mulCount+=count(n);
        }
        return mulCount/i;
    }
    public static void main(String[] args){
        System.out.println(doMultiCount(100, 100));

    }
}
