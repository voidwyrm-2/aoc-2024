import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static boolean debug = false;

    public static class Part1 {
        private static boolean isSafe(int[] report) {
            int prev = report[0];
            Boolean decreasing = null;
            for (int i = 1; i < report.length; i++) {
                if (decreasing == null) {
                    decreasing = report[i] < prev;
                    if (debug) System.out.println("decreasing? " + decreasing);
                } else {
                    if ((report[i] > prev && decreasing) || (report[i] < prev && !decreasing)) return false;
                }

                var cmp = report[i] > prev ? report[i] - prev : prev - report[i];

                if (debug) System.out.printf("%d: %d, %d -> %d\n", i, prev, report[i], cmp);

                if (cmp > 3 || cmp < 1) return false;

                prev = report[i];
            }
            return true;
        }

        public static void run(int[][] reports) {
            int safeReports = 0;

            int i = 0;
            for (var report : reports) {
                if (debug) System.out.println("report " + i + ", " + Arrays.toString(report) + ":");
                var safe = isSafe(report);
                if (debug) System.out.println("is safe? " + safe + "\n");
                if (safe) safeReports++;
                i++;
            }

            System.out.println(safeReports);
        }
    }

    public static class Part2 {
        private static int[] removeIndex(int[] arr, int index) {
            var newArr = new int[arr.length - 1];
            System.arraycopy(arr, 0, newArr, 0, index);
            System.arraycopy(arr, index + 1, newArr, index, arr.length - (index + 1));
            return newArr;
        }

        private static boolean isSafe(int[] report) {
            int prev = report[0];
            Boolean decreasing = null;
            for (int i = 1; i < report.length; i++) {
                if (decreasing == null) {
                    decreasing = report[i] < prev;
                    if (debug) System.out.println("decreasing? " + decreasing);
                } else {
                    if ((report[i] > prev && decreasing) || (report[i] < prev && !decreasing)) return false;
                }

                var cmp = report[i] > prev ? report[i] - prev : prev - report[i];

                if (debug) System.out.printf("%d: %d, %d -> %d\n", i, prev, report[i], cmp);

                if (cmp > 3 || cmp < 1) return false;

                prev = report[i];
            }
            return true;
        }

        public static void run(int[][] reports) {
            int safeReports = 0;

            int i = 0;
            for (var report : reports) {
                if (debug) System.out.println("report " + i + ", " + Arrays.toString(report) + ":");
                var safe = isSafe(report);
                if (debug) System.out.println("is safe? " + safe + "\n");
                if (safe) {
                    safeReports++;
                } else {
                    for (int j = 0; j < report.length; j++) {
                        var nr = removeIndex(report, j);
                        if (isSafe(nr)) {
                            safeReports++;
                            break;
                        }
                    }
                }
                i++;
            }

            System.out.println(safeReports);
        }
    }

    public static void main(String[] args) {
        String path = "input.txt";

        int[][] reports = new int[0][];
        try (Scanner s = new Scanner(new File(path))) {
            ArrayList<String> lineList = new ArrayList<>();
            while (s.hasNextLine()) lineList.add(s.nextLine());
            reports = new int[lineList.size()][];
            var i = 0;
            for (String line : lineList) {
                var report = line.split(" ");
                reports[i] = new int[report.length];
                for (int j = 0; j < report.length; j++) reports[i][j] = Integer.parseInt(report[j]);
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        Part1.run(reports);
        Part2.run(reports);
    }
}
