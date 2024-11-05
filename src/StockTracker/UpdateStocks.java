package StockTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class UpdateStocks {

    public void update() {
        Scanner scanner = new Scanner(System.in);
        config conf = new config();

        
        String itemId = getItemId(scanner, conf);

        
        String name = getValidatedName(scanner, "Enter new Item Name (letters only): ");
        
        
        String category = getValidatedCategory(scanner, "Enter new Item Category (letters only): ");
        
        
        String[] validSizes = {"xtra small", "small", "medium", "large", "extra large", "extra extra large"};
        String size = getValidatedSize(scanner, validSizes);
        
       
        String color = getValidatedColor(scanner, "Enter new Color (letters only): ");
        
        
        int stocks = getPositiveInteger(scanner, "Enter new Stocks: ");
        
        
        int quantity = getPositiveInteger(scanner, "Enter new Quantity: ");
        
        
        double price = getPositiveDouble(scanner, "Enter new Price: ");
        
        
        String status = getValidatedStatus(scanner);
        
        
        String sql = "UPDATE tbl_items SET name = ?, category = ?, size = ?, color = ?, stocks = ?, quantity = ?, price = ?, status = ? WHERE item_id = ?";
        
        try {
            conf.updateRecord(sql, name, category, size, color, stocks, quantity, price, status, itemId);
            System.out.println("Record updated successfully.");
        } catch (SQLException e) {
            System.err.println("An error occurred while updating the record: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    
    private String getItemId(Scanner scanner, config conf) {
        String itemId;
        while (true) {
            System.out.print("Enter the Item ID to update: ");
            itemId = scanner.next().trim();
            if (itemId.isEmpty()) {
                System.out.println("Item ID cannot be empty. Please enter a valid ID.");
            } else if (!checkItemExists(itemId, conf)) {
                System.out.println("Item ID not found in the database. Please enter a valid ID.");
            } else {
                break;
            }
        }
        scanner.nextLine(); 
        return itemId;
    }

   
    private boolean checkItemExists(String itemId, config conf) {
        String query = "SELECT 1 FROM tbl_items WHERE item_id = ?";
        try (Connection conn = conf.connectDB();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, itemId);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); 
            }
        } catch (SQLException e) {
            System.err.println("Error checking item existence: " + e.getMessage());
        }
        return false;
    }

    
    private String getValidatedName(Scanner scanner, String prompt) {
        String name;
        do {
            System.out.print(prompt);
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Item name cannot be empty. Please enter a valid name.");
            } else if (!name.matches("[A-Za-z ]+")) {
                System.out.println("Item name must contain letters only.");
            }
        } while (name.isEmpty() || !name.matches("[A-Za-z ]+"));
        return name;
    }

    
    private String getValidatedCategory(Scanner scanner, String prompt) {
        String category;
        do {
            System.out.print(prompt);
            category = scanner.nextLine().trim();
            if (category.isEmpty()) {
                System.out.println("Item category cannot be empty. Please enter a valid category.");
            } else if (!category.matches("[A-Za-z ]+")) {
                System.out.println("Item category must contain letters only.");
            }
        } while (category.isEmpty() || !category.matches("[A-Za-z ]+"));
        return category;
    }

    
    private String getValidatedSize(Scanner scanner, String[] validSizes) {
        String size;
        do {
            System.out.print("Enter new Size (extra small, small, medium, large, extra large, extra extra large): ");
            size = scanner.nextLine().trim().toLowerCase();
            if (size.isEmpty()) {
                System.out.println("Size cannot be empty. Please enter a valid size.");
            } else if (!isValidSize(size, validSizes)) {
                System.out.println("Invalid size. Please choose from: xtra small, small, medium, large, extra large, extra extra large.");
            }
        } while (size.isEmpty() || !isValidSize(size, validSizes));
        return size;
    }

    
    private boolean isValidSize(String size, String[] validSizes) {
        for (String validSize : validSizes) {
            if (validSize.equals(size)) {
                return true;
            }
        }
        return false;
    }

    
    private String getValidatedColor(Scanner scanner, String prompt) {
        String color;
        do {
            System.out.print(prompt);
            color = scanner.nextLine().trim();
            if (color.isEmpty()) {
                System.out.println("Color cannot be empty. Please enter a valid color.");
            } else if (!color.matches("[A-Za-z ]+")) {
                System.out.println("Color must contain letters only.");
            }
        } while (color.isEmpty() || !color.matches("[A-Za-z ]+"));
        return color;
    }

    
    private String getValidatedStatus(Scanner scanner) {
        String status;
        do {
            System.out.print("Enter new Status (available or not available): ");
            status = scanner.nextLine().trim().toLowerCase();
            if (status.isEmpty()) {
                System.out.println("Status cannot be empty. Please enter a valid status.");
            } else if (!status.equals("available") && !status.equals("not available")) {
                System.out.println("Invalid status. Please enter either 'available' or 'not available'.");
            }
        } while (status.isEmpty() || (!status.equals("available") && !status.equals("not available")));
        return status;
    }

    
    private String getNonEmptyInput(Scanner scanner, String prompt) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter a valid value.");
            }
        } while (input.isEmpty());
        return input;
    }

    
    private int getPositiveInteger(Scanner scanner, String prompt) {
        int value = -1;
        while (value < 0) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                if (value < 0) {
                    System.out.println("Value must be non-negative. Please enter a valid quantity.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
            scanner.nextLine();
        }
        return value;
    }

    
    private double getPositiveDouble(Scanner scanner, String prompt) {
        double value = -1.0;
        while (value < 0) {
            System.out.print(prompt);
            try {
                value = scanner.nextDouble();
                if (value < 0) {
                    System.out.println("Value must be non-negative. Please enter a valid price.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
            scanner.nextLine();
        }
        return value;
    }
}
