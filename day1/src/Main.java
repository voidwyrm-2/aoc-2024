import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {
    public static class Part1 {
        private static int difference(int a, int b) {
            return b > a ? b - a : a - b;
        }

        public static void run(int[] left, int[] right) {
            int[] diff = new int[right.length];
            for (int i = 0; i < right.length; i++) {
                diff[i] = difference(left[i], right[i]);
            }

            int sum = 0;
            for (int d : diff) {
                sum += d;
            }

            System.out.println(sum);
        }
    }

    public static class Part2 {
        private static int getScore(int n, Map<Integer, Integer> counts) {
            Integer count = counts.get(n);
            if (count == null) return 0;
            return n * count;
        }

        public static void run(int[] left, int[]right) {
            Map<Integer, Integer> counts = new HashMap<>();
            for (int n : right) {
                counts.merge(n, 1, Integer::sum);
            }

            int similarityScore = 0;
            for (int n : left) {
                similarityScore += getScore(n, counts);
            }

            System.out.println(similarityScore);
        }
    }

    public static void main(String[] args) {
        String path = "input.txt";

        int[] left = new int[0];
        int[] right = new int[0];
        try (Scanner s = new Scanner(new File(path))) {
            ArrayList<String> lineList = new ArrayList<>();
            while (s.hasNextLine()) lineList.add(s.nextLine());
            left = new int[lineList.size()];
            right = new int[lineList.size()];
            var i = 0;
            for (String line : lineList) {
                var codePair = line.split(" {3}");
                left[i] = Integer.parseInt(codePair[0]);
                right[i] = Integer.parseInt(codePair[1]);
                i++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        Arrays.sort(left);
        Arrays.sort(right);

        //Part1.run(left, right);
        Part2.run(left, right);
    }
}