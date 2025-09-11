import java.util.Scanner;
import java.util.Random;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int num0 = random.nextInt(100) + 1;
        int num1 = random.nextInt(100) + 1;

        char[] ops = {'+', '-', '*', '/'};
        char op = ops[random.nextInt(ops.length)];

        int attemps = 0;
        int quess = 0;

        System.out.printf("Твой пример: %d %c %d\n", num0, op, num1);

        int res;
        switch (op) {
            case '+':
                res = num0 + num1;
                break;
            case '-':
                res = num0 - num1;
                break;
            case '*':
                res = num0 * num1;
                break;
            case '/':
                res = num0 / num1;
                break;
            default:
                res = 0;
        }

        while (quess != res) {
            quess = scanner.nextInt();
            attemps++;

            if (quess > res) {
                System.out.println("Меньше! ");
            } else if (quess < res) {
                System.out.println("Больше! ");
            }
        }

        System.out.println("Верно!");

        scanner.close();
    }
}