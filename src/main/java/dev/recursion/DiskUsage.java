package dev.recursion;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Objects;

public class DiskUsage {
    /**
     * 1. Retrieve the length of the current file and return, because that represents
     * the size of current file being processing.
     * 2. If it is a directory, traverse the whole directory until reach an end file
     * and then store accumulative calculation of each file size in "total".
     * 3. Then, when all files and all directories are traversed, accumulative of
     * total represents the sum of cumulative disk usage.
     *
     * @param root - beginning of directory
     * @return - the cumulative disk usage.
     */

    public static long diskUsage(File root, PrintWriter writer) {
        long total = root.length();

        /**
         * Recursively traverse each nested directory,
         * this also represent each base case of recursion,
         * when root is not directory total is returned in the
         * accumulative sum of line 31.
         */
        if (root.isDirectory()) {
            for (String childFileName : Objects.requireNonNull(root.list())) {
                File file = new File(root, childFileName);
                total += diskUsage(file, writer);
            }
        }

        writer.println(total + "," + toCsvValue(root.toString()));

        return total;
    }

    private static String toCsvValue(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }

    public static void main(String[] args) throws FileNotFoundException {
        File root = args.length > 0 ? new File(args[0]) : new File("C:\\Users\\BrunoSilveira\\OneDrive - Info Tecnologia\\Imagens");

        try (PrintWriter writer = new PrintWriter("disk-usage.csv")) {
            writer.println("diskUsage,directory");

            long totalDiskUsage = diskUsage(root, writer);

            writer.println(totalDiskUsage + ",\"TOTAL\"");
        }
    }
}
