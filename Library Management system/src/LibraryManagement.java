import java.sql.*;

import java.util.Scanner;

public class LibraryManagement {
    static final   String url = "jdbc:mysql://localhost:3306/library";
    static final   String username = "your user name";
    static final   String password = "your password";
    public static void main(String[] args) throws ClassNotFoundException{

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("driver load succesfully");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection con = DriverManager.getConnection(url,username,password);
            System.out.println("connection establized");
            Scanner kb = new Scanner(System.in);
            while (true){
                System.out.println(" LIBRARY MANAGEMENT SYSTEM ");
                System.out.println("1. Add Book");
                System.out.println("2. View Books");
                System.out.println("3. Search Book");
                System.out.println("4. Update Book");
                System.out.println("5. Delete Book");
                System.out.println("6. Exit");

                System.out.println("enter choice..");

                int choice = kb.nextInt();
                kb.nextLine();
                switch (choice){
                    case 1:
                        System.out.println("enter book_title");
                        String book_title = kb.nextLine();

                        System.out.print("Enter Author : ");
                        String author = kb.nextLine();

                        System.out.print("Enter Price : ");
                        double price = kb.nextDouble();

                        System.out.print("Enter Quantity : ");
                        int quantity = kb.nextInt();

                        String query =  "INSERT INTO books(book_title,author,price,quantity) VALUES(?,?,?,?)";

                        PreparedStatement prinsert = con.prepareStatement(query);
                        prinsert.setString(1,book_title);
                        prinsert.setString(2,author);
                        prinsert.setDouble(3,price);
                        prinsert.setInt(4,quantity);
                        int book_added = prinsert.executeUpdate();

                        if(book_added > 0){
                            System.out.println("book added succesfully");
                        }
                        break;

                    case 2:
                        String select = "SELECT * FROM books";
                        Statement stmt = con.createStatement();
                        ResultSet rsview = stmt.executeQuery(select);
                        while (rsview.next()){

                            int Id = rsview.getInt("id");
                            String Title = rsview.getString("book_title");
                            String Author = rsview.getString("author");
                            double Price = rsview.getDouble("price");
                            int Quantity = rsview.getInt("quantity");

                            System.out.println(Id + " " + Title + " " + Author + " " + Price + " " + Quantity);

                        }
                        break;

                    case 3:
                        System.out.println("enter book id");
                        int id = kb.nextInt();
                        String searchedbook = "select * from books where id = ?";
                        PreparedStatement pssearch = con.prepareStatement(searchedbook);
                        pssearch.setInt(1,id);

                        ResultSet rssearch = pssearch.executeQuery();
                        if(rssearch.next()){
                            System.out.println("book found");

                            int Id = rssearch.getInt("id");
                            String Title = rssearch.getString("book_title");
                            String Author = rssearch.getString("author");
                            double Price = rssearch.getDouble("price");
                            int Quantity = rssearch.getInt("quantity");

                            System.out.println(Id + " " + Title + " " + Author + " " + Price + " " + Quantity);

                        } else {
                        System.out.println("book not found");
                    }
                        break;

                    case 4:

                        System.out.print("Enter Book ID : ");
                        int updateId = kb.nextInt();
                        kb.nextLine();

                        System.out.print("Enter New Title : ");
                        String newTitle = kb.nextLine();

                        System.out.print("Enter New Author : ");
                        String newAuthor = kb.nextLine();

                        System.out.print("Enter New Price : ");
                        double newPrice = kb.nextDouble();

                        System.out.print("Enter New Quantity : ");
                        int newQty = kb.nextInt();

                        String update = "UPDATE books SET book_title=?,author=?,price=?,quantity=? WHERE id=?";

                        PreparedStatement psupdate = con.prepareStatement(update);

                        psupdate .setString(1, newTitle);
                        psupdate .setString(2, newAuthor);
                        psupdate .setDouble(3, newPrice);
                        psupdate .setInt(4, newQty);
                        psupdate .setInt(5, updateId);

                        int updated = psupdate .executeUpdate();

                        if (updated > 0)
                            System.out.println("Book Updated Successfully");
                        else
                            System.out.println("Book Not Found");

                        break;

                    case 5:
                        System.out.print("Enter Book ID : ");
                        int deleteId = kb.nextInt();

                        String delete = "DELETE FROM books WHERE id=?";

                        PreparedStatement psdelete = con.prepareStatement(delete);

                        psdelete.setInt(1, deleteId);

                        int deleted = psdelete.executeUpdate();

                        if (deleted > 0)
                            System.out.println("Book Deleted Successfully");
                        else
                            System.out.println("Book Not Found");

                        break;

                    case 6:

                        System.out.println("Thank You");

                        con.close();



                    default:
                        System.out.println("Invalid Choice");
                }
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
}
