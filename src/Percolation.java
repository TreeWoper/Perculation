import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] mat;
    private int numOpen;
    //true open
    //false closed

    private WeightedQuickUnionUF id;
    private WeightedQuickUnionUF id2;

    public Percolation(int n) {               // create n-by-n grid, with all sites blocked
        if (n <= 0) throw new IllegalArgumentException();
        numOpen = 0;
        mat = new boolean[n][n];
        id = new WeightedQuickUnionUF(n*n+2);
        id2 = new WeightedQuickUnionUF(n*n+1);
        if(mat.length > 1) {
            for (int i = 0; i < mat.length; i++) {
                id.union(0, i + 1);
                id2.union(0, i+1);
                id.union(mat.length * mat.length + 1, mat.length * mat.length + 1 - i - 1);
            }
        }
    }

    public int numberOfOpenSites(){
        return numOpen;
    }

    public void open(int row, int col) {       // open site (row, col) if it is not open already
        checkRange(row, col);
        if(mat[row-1][col-1] == false){
            numOpen++;
        }
        mat[row -1][col -1] = true;
        comb(row, col, row, col + 1);
        comb(row, col, row + 1, col);
        comb(row, col, row, col - 1);
        comb(row, col, row - 1, col);
    }

    private void comb(int r, int c, int r2, int c2) {
        if(r2 > 0 && r2 <= mat.length && c2 > 0 && c2 <= mat.length) {
            if (mat[r-1][c-1] && mat[r2-1][c2-1]) {
                id.union((r-1) * mat.length + (c -1) % mat.length +1, (r2-1) * mat.length + (c2-1) % mat.length +1);
                id2.union((r-1) * mat.length + (c -1) % mat.length +1, (r2-1) * mat.length + (c2-1) % mat.length +1);
            }
        }
    }

    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        checkRange(row, col);
        return mat[row -1][col -1];
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        checkRange(row, col);
        if(mat.length <= 1){
            return isOpen(row, col);
        }
        if(isOpen(row, col)) {
            if (id2.find((row - 1) * mat.length + col) == id2.find(0)) {
                return true;
            }
        }
        return false;
    }

    private void checkRange(int row, int col) {    // validate input
        if (row <= 0 || row > mat.length || col <= 0 || col > mat.length) throw new IllegalArgumentException();
    }

    public boolean percolates()              // does the system percolate?
    {
        if(mat.length > 1) {
            return id.find(0) == id.find(mat.length * mat.length +1);
        }
        else{
            return isOpen(1, 1);
        }
    }

    private void print() {                   // prints boolean[][] called mat
        for (boolean[] row : mat) {
            for (boolean col : row) {
                System.out.print((col ? 1 : 0) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args)   // test client (optional){
    {
        Percolation test = new Percolation(2);    // simple test case
        test.print();
        test.open(1, 1);
        test.print();
        test.open(2, 1);
        test.print();
        System.out.println(test.percolates());
    }
}