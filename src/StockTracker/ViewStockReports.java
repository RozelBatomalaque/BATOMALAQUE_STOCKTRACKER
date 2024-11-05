package StockTracker;

public class ViewStockReports {
        public void view(){
        config conf = new config();
    
        String query = "SELECT * FROM tbl_items"; 
        String[] headers = {"Item ID", "Name", "Category", "Size", "Color", "Stocks", "Quantity", "Price", "Status"};
        String[] columns = {"item_id", "name", "category", "size", "color", "stocks", "quantity", "price", "status"};

        
       conf.viewRecords(query, headers, columns);
    }

        
    }
    
