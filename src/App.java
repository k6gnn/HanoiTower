import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of disks (1-10): ");
        int n = scanner.nextInt();
        
        if (n < 1 || n > 10) {
            System.out.println("Invalid number of disks. Please enter a value between 1 and 10.");
            return;
        }
        
        System.out.println("n = " + n);
        
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>();
        Stack<Integer> C = new Stack<>();
        
        for (int i = n; i >= 1; i--) {
            A.push(i);
        }
        
        System.out.println("Initial state:");
        printState(A, B, C);
        
        char[] pegs = {'A', 'B', 'C'};
        if (n % 2 == 0) {
            char temp = pegs[1];
            pegs[1] = pegs[2];
            pegs[2] = temp;
        }
        
        Stack<Integer>[] towers = new Stack[]{A, B, C};
        int moves = (int) Math.pow(2, n) - 1;
        
        for (int move = 1; move <= moves; move++) {
            int from = (move & move - 1) % 3;
            int to = ((move | move - 1) + 1) % 3;
            
            if (!towers[from].isEmpty() && (towers[to].isEmpty() || towers[to].peek() > towers[from].peek())) {
                int disk = towers[from].pop();
                towers[to].push(disk);
                System.out.printf("%d. Move disk %d from %c to %c. ", move, disk, pegs[from], pegs[to]);
            }
            printState(A, B, C);
        }
        
        scanner.close();
    }
    
    private static void printState(Stack<Integer> A, Stack<Integer> B, Stack<Integer> C) {
        System.out.printf("A=%s, B=%s, C=%s%n", A, B, C);
    }
}
