package StockTracker;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AddStocks {
    public void add() {
        Scanner scanner = new Scanner(System.in);
        config conf = new config();
        
        
        String name;
        do {
            System.out.print("Enter Item Name (text only): ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Item name cannot be empty. Please enter a valid name.");
            } else if (!isValidName(name)) {
                System.out.println("Item name must contain letters only.");
            }
        } while (name.isEmpty() || !isValidName(name));

        
        String category;
        do {
            System.out.print("Enter Item Category: ");
            category = scanner.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("Item category cannot be empty. Please enter a valid category.");
            }
        } while (category.isEmpty());

        
        String size;
        String[] validSizes = {"xtra small", "small", "medium", "large", "extra large", "extra extra large"};
        do {
            System.out.print("Enter Size (extra small, small, medium, large, extra large, extra extra large): ");
            size = scanner.nextLine().trim().toLowerCase();
            if (size.isEmpty()) {
                System.out.println("Size cannot be empty. Please enter a valid size.");
            } else if (!isValidSize(size, validSizes)) {
                System.out.println("Invalid size. Please choose from: xtra small, small, medium, large, extra large, extra extra large.");
            }
        } while (size.isEmpty() || !isValidSize(size, validSizes));

        
        String color;
        do {
            System.out.print("Enter Color (text only): ");
            color = scanner.nextLine().trim();
            if (color.isEmpty()) {
                System.out.println("Color cannot be empty. Please enter a valid color.");
            } else if (!isValidColor(color)) {
                System.out.println("Color must contain letters only. Please enter a valid color.");
            }
        } while (color.isEmpty() || !isValidColor(color));

        
        int stocks = -1;
        while (stocks < 0) {
            System.out.print("Enter Stocks (integer only): ");
            try {
                stocks = Integer.parseInt(scanner.nextLine().trim());
                if (stocks < 0) {
                    System.out.println("Stocks must be a non-negative integer. Please enter a valid stock quantity.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for stocks.");
            }
        }

        
        int quantity = -1;
        while (quantity < 0) {
            System.out.print("Enter Quantity (integer only): ");
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity < 0) {
                    System.out.println("Quantity must be a non-negative integer. Please enter a valid quantity.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for quantity.");
            }
        }

        
        double price = -1;
        while (price < 0) {
            System.out.print("Enter Price (positive number only): ");
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price < 0) {
                    System.out.println("Price must be a non-negative number. Please enter a valid price.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for price.");
            }
        }

       
        String status;
        do {
            System.out.print("Enter Status (available or not available): ");
            status = scanner.nextLine().trim().toLowerCase();
            if (status.isEmpty()) {
                System.out.println("Status cannot be empty. Please enter a valid status.");
            } else if (!status.equals("available") && !status.equals("not available")) {
                System.out.println("Invalid status. Please enter either 'available' or 'not available'.");
            }
        } while (status.isEmpty() || (!status.equals("available") && !status.equals("not available")));

        
        String sql = "INSERT INTO tbl_items (name, category, size, color, stocks, quantity, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        
        conf.addRecord(sql, name, category, size, color, stocks, quantity, price, status);
    }


    private boolean isValidName(String name) {
        return name.matches("[A-Za-z ]+"); 
    }

    
    private boolean isValidSize(String size, String[] validSizes) {
        for (String validSize : validSizes) {
            if (validSize.equals(size)) {
                return true;
            }
        }
        return false;
    }

   
    private boolean isValidColor(String color) {
        return color.matches("[A-Za-z ]+"); 
    }
}
