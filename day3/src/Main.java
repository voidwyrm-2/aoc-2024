import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static class Part1 {
        public static void run(String input) {
            var pat = Pattern.compile("mul\\(([0-9]*),([0-9]*)\\)");
            var m = pat.matcher(input);

            int sum = 0;
            if (m.find()) {
                do {
                    sum += Integer.parseInt(m.group(1)) * Integer.parseInt(m.group(2));
                } while(m.find(m.start()+1));
            }

            System.out.println(sum);
        }
    }

    public static class Part2 {
        public static void run(String input) {
            var pat = Pattern.compile("(mul\\(([0-9]*),([0-9]*)\\)|do\\(\\)|don't\\(\\))");
            var m = pat.matcher(input);

            int sum = 0;
            boolean doMul = true;
            if (m.find()) {
                do {
                    if (Objects.equals(m.group(), "do()") || Objects.equals(m.group(), "don't()")) {
                        doMul = Objects.equals(m.group(), "do()");
                    } else {
                        if (doMul) sum += Integer.parseInt(m.group(2)) * Integer.parseInt(m.group(3));
                    }
                } while(m.find(m.start()+1));
            }

            System.out.println(sum);
        }
    }

    public static void main(String[] args) {
        String path = "input.txt";

        String input = "";
        try (Scanner s = new Scanner(new File(path))) {
            var builder = new StringBuilder();
            while (s.hasNextLine()) builder.append(s.nextLine());
            input = builder.toString();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        Part1.run(input);
        Part2.run(input);
    }
}
