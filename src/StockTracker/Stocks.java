package StockTracker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Stocks {

    public void STOCKS(){
        Scanner sc = new Scanner(System.in);
        AddStocks ar = new AddStocks();
        ViewStockReports vr = new ViewStockReports();
        UpdateStocks ur = new UpdateStocks();
        DeleteStocks dr = new DeleteStocks();
        String response = null;
        
        do {  
            System.out.println("-------------------------------------------");
            System.out.println("\n-- STOCKS'S MENU --");        
            System.out.println("1. Add Item");
            System.out.println("2. View Item Reports");
            System.out.println("3. Update Item");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit Stock's Menu");
            System.out.println("-------------------------------------------");

            int action = 0;
            boolean validAction = false;

            
            while (!validAction) {
                System.out.print("Enter Action (1-5): ");
                try {
                    action = sc.nextInt();
                    if (action >= 1 && action <= 5) {
                        validAction = true;
                    } else {
                        System.out.println("Invalid action. Please choose a number between 1 and 5.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                    sc.next(); 
                }
            }

            
            switch(action) {
                case 1:
                    vr.view();
                    System.out.println("\n-- These are the Stock Items --");
                    ar.add();
                    break;
                case 2:
                    vr.view();
                    break;
                case 3:
                    vr.view();
                    ur.update();
                    vr.view();
                    break;
                case 4:
                    vr.view();
                    dr.delete();
                    vr.view();

                    break;
                case 5:
                    System.out.println("Exiting Stock's Menu...");
                    return; 
            }

            
            boolean validResponse = false;
            while (!validResponse) {
                System.out.print("Do you want to make another transaction? (yes/no): ");
                response = sc.next().trim();
                if (response.equalsIgnoreCase("yes")) {
                    validResponse = true;
                } else if (response.equalsIgnoreCase("no")) {
                    response = "no"; 
                    validResponse = true;
                } else {
                    System.out.println("Invalid response. Please type 'yes' or 'no'.");
                }
            }

        } while (response.equalsIgnoreCase("yes"));

        System.out.println("-- Returning to Main Menu --");
    }
}
