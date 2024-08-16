/**
 * @(#)Demo.java
 *
 *
 * @author 
 * @version 1.00 2016/12/18
 */

public class Demo {
        
    /**
     * Creates a new instance of <code>Demo</code>.
     */
    public Demo() {
    	
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] arr = new int[5][5];
        int c = 0;
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++){
                arr[i][j] = c++;
                System.out.print(arr[i][j] +"     ");
            }
            System.out.println();
        }
        int[] a = new int[25];
        for(int i = 0; i < a.length; i++){
            a [i] = i;
            System.out.print(a[i]);
        }
        System.out.println();
        System.out.println(a[2*arr.length +3%arr.length+ arr.length] +"   " + arr[2][3]);
        System.out.println(a[6] + "     " + arr[6/arr.length][6%arr.length]);

    }
}
