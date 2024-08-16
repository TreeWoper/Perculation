import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] ary;

    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        ary = new double[trials];
        for (int i = 0; i < trials; i++) {
            Percolation p = new Percolation(n);
            int count = 0;
            while (!p.percolates()) {
                int r = StdRandom.uniform(1, n + 1);
                int c = StdRandom.uniform(1, n + 1);
                if(!p.isOpen(r, c)){
                    count++;
                }
                p.open(r, c);
            }
            ary[i] = (double) count / (n * n);
        }
    }

    public double mean() {                        // sample mean of percolation threshold
        return StdStats.mean(ary);
    }

    public double stddev() {                       // sample standard deviation of percolation threshold
        return StdStats.stddev(ary);
    }

    public double confidenceLo() {                 // low  endpoint of 95% confidence interval
        double x = mean();
        double s = stddev() * 1.96;
        return x - (s / Math.sqrt(ary.length));
    }

    public double confidenceHi() {                 // high endpoint of 95% confidence interval
        double x = mean();
        double s = stddev() * 1.96;
        return x + (s / Math.sqrt(ary.length));
    }

    public static void main(String[] args) {    // test client (described below)
        int n = Integer.parseInt(args[0]);         // n-by-n percolation system
        int T = Integer.parseInt(args[1]);            // T trials
        PercolationStats ps = new PercolationStats(n, T);
        StdOut.println("mean\t\t\t\t = " + ps.mean());
        StdOut.println("stddev\t\t\t\t = " + ps.stddev());
        StdOut.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}