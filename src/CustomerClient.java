import java.io.*;
import java.net.*;
import java.util.Scanner;

public class CustomerClient {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Customer connected to Seller...");

            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // Input number of items
            System.out.print("Enter the number of items: ");
            int numberOfItems = scanner.nextInt();
            output.println(numberOfItems);

            // Input prices of items
            int[] prices = new int[numberOfItems];
            for (int i = 0; i < numberOfItems; i++) {
                System.out.print("Enter price of item " + (i + 1) + ": ");
                prices[i] = scanner.nextInt();
                output.println(prices[i]);
            }

            // Receive total food price and total payment
            int totalFoodItemPrice = Integer.parseInt(input.readLine());
            double totalPayment = Double.parseDouble(input.readLine());

            System.out.println("Total food item price: " + totalFoodItemPrice);
            System.out.println("Total payment (adding VAT & delivery charge): " + totalPayment);

            // Close the streams and socket
            scanner.close();
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
