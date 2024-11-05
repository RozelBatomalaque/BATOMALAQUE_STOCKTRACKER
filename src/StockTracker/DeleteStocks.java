package StockTracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteStocks {
    public void delete() {
        Scanner scanner = new Scanner(System.in);
        config conf = new config();
        
        
        String itemId = getItemId(scanner, conf);

        
        String sql = "DELETE FROM tbl_items WHERE item_id = ?";
        
        
        conf.deleteRecord(sql, itemId);
    }

    
    private String getItemId(Scanner scanner, config conf) {
        String itemId;
        while (true) {
            System.out.print("Enter the Item ID to delete: ");
            itemId = scanner.next().trim();
            if (itemId.isEmpty()) {
                System.out.println("Item ID cannot be empty. Please enter a valid ID.");
            } else if (!checkItemExists(itemId, conf)) {
                System.out.println("Item ID not found in the database. Please enter a valid ID.");
            } else {
                break;  
            }
        }
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
}
