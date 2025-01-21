import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            // Start the server
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Seller Server Started...");

            // Wait for a client connection
            Socket socket = serverSocket.accept();
            System.out.println("Customer connected to Seller...");

            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            // Read number of items
            int numberOfItems = Integer.parseInt(input.readLine());
            System.out.println("No. of items: " + numberOfItems);

            int[] prices = new int[numberOfItems];
            int totalFoodItemPrice = 0;

            // Read prices of items and calculate the total price
            for (int i = 0; i < numberOfItems; i++) {
                prices[i] = Integer.parseInt(input.readLine());
                totalFoodItemPrice += prices[i];
                System.out.println("Price of item" + (i + 1) + ": " + prices[i]);
            }

            // Calculate VAT (15%) and delivery charge (100 taka)
            double vat = totalFoodItemPrice * 0.15;
            double deliveryCharge = 100;
            double totalPayment = totalFoodItemPrice + vat + deliveryCharge;

            System.out.println("Total food item price: " + totalFoodItemPrice);
            System.out.println("Total payment (adding VAT & delivery charge): " + totalPayment);

            // Send results back to the client
            output.println(totalFoodItemPrice);
            output.println(totalPayment);

            // Close the streams and socket
            input.close();
            output.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}