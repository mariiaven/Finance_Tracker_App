/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projecty4;

/**
 *
 * @author mariavengerska
 */
public class ProjectY4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //database.registeruser("anna", "123", 20000, 5000);
        // int userid = database.loginuser("anna", "123");
         // database.addtransaction(userid, 20000, "income", "salary");
       // database.addtransaction(userid, 100, "expense", "food");
        //database.addtransaction(userid, 150, "expense", "utility bills");
        //System.out.println(database.getbalance(userid));
        login l = new login();
            l.setVisible(true);
            
    }
    
}
