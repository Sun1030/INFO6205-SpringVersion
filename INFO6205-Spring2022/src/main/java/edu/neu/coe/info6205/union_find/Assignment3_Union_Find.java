package edu.neu.coe.info6205.union_find;

public class Assignment3_Union_Find {

    /**
     * Generate a random number between 0 to i-1
     *
     * @param i the max number is i-1
     * @return a random int between 0 to i-1
     */
    public static int generateRandomIndex1(int i){
        return (int)(Math.random() * i);
    }

    /**
     * Generates random pairs of integers between 0 and n-1,
     * calling connected() to determine if they are connected and union() if not.
     * Loop until all sites are connected then print the number of connections generated.
     *
     * @param n the number of "sites"
     * @return the number of connections generated
     */
    public static int count(int n){
        int countNum = 0;
        UF_HWQUPC test = new UF_HWQUPC(n);
        while(test.components() > 1){
            int i = generateRandomIndex1(n);
            int j = generateRandomIndex1(n);
            if (!test.connected(i, j)) {
                countNum++;
                test.union(i, j);
            }
        }
        return countNum;
    }

    /**
     * Call count() i times when the number of sites is n.
     *
     * @param n the number of "sites"
     * @param i the number of running time
     * @return the average number of connections generated if running i times
     */
    public static double doMultiCount(int n, int i){
        double mulCount = 0;
        for(int j = 0; j < i; j++){
            mulCount+=count(n);
        }
        return mulCount/i;
    }

    public static void main(String[] args){
        int[] numOfSites = {750, 1500, 3000, 6000, 12000, 24000};

        for(int i: numOfSites) {
            System.out.println("The number of objects (n): " + i + "\t\tThe number of pairs(m) generated: " + doMultiCount(i, 100));
        }
    }
}
