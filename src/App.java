import java.util.*;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter number of disks (1-10): ");
        int n = scanner.nextInt();
        
        if (n < 1 || n > 10) {
            System.out.println("The max number is 10"); //to check if i have more than 10
            return;
        }
        
        System.out.println("n = " + n);
        
        Stack<Integer> A = new Stack<>();
        Stack<Integer> B = new Stack<>(); //here i create the pegs 
        Stack<Integer> C = new Stack<>();
        
        for (int i = n; i >= 1; i--) {
            A.push(i); //here i put the disks on a in descending order
        }
        
        System.out.println("Initial state:");
        printState(A, B, C);
        

        // The names of the pegs again
        char[] pegs = {'A', 'B', 'C'};

        //here i swap the pegs for some optimization depending on the number of disks
        if (n % 2 == 0) {
            char temp = pegs[1];
            pegs[1] = pegs[2];
            pegs[2] = temp;
        }
        
        Stack<Integer>[] towers = new Stack[]{A, B, C};
        int moves = (int) Math.pow(2, n) - 1; //calculating the number of moves with the formula 2^n - 1
        
        //here i start the iterative solution
        for (int move = 1; move <= moves; move++) {
            int from = (move & move - 1) % 3;
            int to = ((move | move - 1) + 1) % 3;
            

            //here im checking if im following the rules
            if (!towers[from].isEmpty() && (towers[to].isEmpty() || towers[to].peek() > towers[from].peek())) {
                int disk = towers[from].pop();
                towers[to].push(disk);
                if (move < 10){
                    System.out.printf("   %d. Move disk %d from %c to %c. ", move, disk, pegs[from], pegs[to]);
                } else
                if(move < 100){
                    System.out.printf("  %d. Move disk %d from %c to %c. ", move, disk, pegs[from], pegs[to]);
                } else if (move < 1000){
                    System.out.printf(" %d. Move disk %d from %c to %c. ", move, disk, pegs[from], pegs[to]);
                }else {
                    System.out.printf("%d. Move disk %d from %c to %c. ", move, disk, pegs[from], pegs[to]);
                }
            }
            printState(A, B, C);
        }
        
        scanner.close();
        System.out.println("The total nymber of moves is " + moves);
    }
    
    private static void printState(Stack<Integer> A, Stack<Integer> B, Stack<Integer> C) {
        System.out.printf("A=%s, B=%s, C=%s%n", A, B, C);
    }
}
