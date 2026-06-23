/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projecty4;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author mariavengerska
 */
public class database {
    private static final String URL = "jdbc:sqlite:/Users/mariavengerska/Desktop/Computer science/financetracker.db";
     
    

    public static Connection connect() throws SQLException {
    try {
        Class.forName("org.sqlite.JDBC"); 
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    System.out.println("Connected to: " + URL);
    return DriverManager.getConnection(URL);
}
    
    
    
    
    public static int loginuser(String username, String password){
        String sql = "SELECT id FROM users WHERE username = ? AND password = ?";
        
        try (Connection c = connect();
                PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){ return rs.getInt("id");}
        }    
            catch (SQLException e){e.printStackTrace();}
            
            return -1;
        }
    
    
    
    
    
    

    public static void registeruser(String username, String password, double salary, double utilitybills){
        String sql = "INSERT INTO users (username, password,salary,utility_bills) VALUES (?,?,?,?)";
        
        try (Connection c = connect();
                PreparedStatement ps = c.prepareStatement(sql)){
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setDouble(3, salary);
            ps.setDouble(4, utilitybills);
            ps.executeUpdate();
        }    
            catch (SQLException e){e.printStackTrace();}
           
        }
    
    
    
    
  
    public static void addtransaction(int userid, double amount, String type, String category){
        String sql = "INSERT INTO transactions (userid, amount,type,category) VALUES (?,?,?,?)";
        
        try (Connection c = connect();
                PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, userid);
            ps.setDouble(2, amount);
            ps.setString(3, type);
            ps.setString(4, category);
            ps.executeUpdate();
        }    
            catch (SQLException e){e.printStackTrace();}
           
        }
    
    
    
    

    public static ArrayList<String []> gettransactions(int userid){
        ArrayList<String []> list = new ArrayList<>();
        String sql = "SELECT type, category, amount FROM transactions WHERE userid = ?";
        
        try (Connection c = connect();
                PreparedStatement ps = c.prepareStatement(sql)){
            ps.setInt(1, userid);
            
            ResultSet rs = ps.executeQuery();
             while (rs.next()){
                    String[] row = {
                        rs.getString("type"),
                        rs.getString("category"),
                        String.valueOf(rs.getDouble("amount")),
                    };
                            list.add(row);
                }
    }
        catch (SQLException e){e.printStackTrace();}
        return list;
    }
    

    
    
    
    

    public static String getusername(int userid) {
        String username = "";

        String sql = "SELECT username FROM users WHERE id = ?";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
            username = rs.getString("username");
}
             
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }
    
    

    public static double getbalance(int userid) {
        double balance = 0;

        String sql = "SELECT type, amount FROM transactions WHERE userid = ?";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");
                double amount = rs.getDouble("amount");

                if (type.equals("income") || type.equals("Income")) {
                    balance += amount;
                } else {
                    balance -= amount;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return balance;
    }
    
    
    
    
    
       public static ArrayList<String[]> getexpensesbycategory(int userid) {
        ArrayList<String[]> list = new ArrayList<>();

        String sql = "SELECT category, SUM(amount) as total FROM transactions WHERE userid = ? AND type = 'expense' GROUP BY category";

        try (Connection c = connect();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, userid);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = {
                        rs.getString("category"),
                        String.valueOf(rs.getDouble("total"))
                };
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
       
       
       
       
       public static double getsalary(int userid) {
    String sql = "SELECT salary FROM users WHERE id = ?";

    try (Connection c = connect();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, userid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("salary");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}
       
       
       public static double getutilitybills(int userid) {
    String sql = "SELECT utility_bills FROM users WHERE id = ?";

    try (Connection c = connect();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, userid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("utility_bills");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}
       
       
       
       
       public static double getincome(int userid) {
    String sql = "SELECT SUM(amount) AS total FROM transactions WHERE userid = ? AND type = 'Income'";

    try (Connection c = connect();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, userid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("total");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}
       
       public static double getexpense(int userid) {
    String sql = "SELECT SUM(amount) AS total FROM transactions WHERE userid = ? AND type = 'Expense'";

    try (Connection c = connect();
         PreparedStatement ps = c.prepareStatement(sql)) {

        ps.setInt(1, userid);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getDouble("total");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return 0;
}
}
    

