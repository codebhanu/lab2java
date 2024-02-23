package com.example.javafxlab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import com.example.javafxlab2.model.Books;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Books, String> Book_col;

    @FXML
    private TableColumn<Books, String> Date_col;

    @FXML
    private TableColumn<Books, Integer> ID_Col;

    @FXML
    private TextField booknameinput, genreinput, inputbookid, realseaseinput;

    @FXML
    private Label error;

    @FXML
    private TableColumn<Books, String> gen_col;

    @FXML
    private TableView<Books> tablefordata;

    @FXML     // This adds the data in the table we only provide name,genre and Release date as id is auto increasing
    void addData(ActionEvent event) {
            String query = "INSERT INTO Books (BookName, Genre, ReleaseDate) VALUES (?, ?, ?)";
            executeQuery(query, List.of(booknameinput.getText(), genreinput.getText(), realseaseinput.getText()));
            makeEmpty();

    }


    @FXML
    void deleteData(ActionEvent event) {
        validateID(); // this function validate the id if it is valid id or not
        if (error.getText().isEmpty()){
        String query = "DELETE FROM Books WHERE BookID=?";
        executeQuery(query, List.of(inputbookid.getText()));
        makeEmpty();}
    }

    @FXML
    void updateData(ActionEvent event) {
        validateID();
            if (error.getText().isEmpty()){
        String query = "UPDATE Books SET BookName=?, Genre=?, ReleaseDate=? WHERE BookID=?";
        executeQuery(query, List.of(booknameinput.getText(), genreinput.getText(), realseaseinput.getText(), inputbookid.getText()));
        makeEmpty();} // this function makes the input filed clear after the action is finished
    }

    @FXML
    void getData(ActionEvent event) {
        showBooks(); //This displays the data in the table
    }




    @FXML
    public void viewData(ActionEvent actionEvent) {
        validateID();
        if (error.getText().isEmpty()){
        String query = "SELECT BookID, BookName, Genre, ReleaseDate FROM Books WHERE BookID=?";
        int bookId = Integer.parseInt(inputbookid.getText());

        try (Connection conn = Database.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                error.setText(""); // this remove the error when user input the id that exist in the database
                inputbookid.setText(String.valueOf(rs.getInt("BookID")));
                booknameinput.setText(rs.getString("BookName"));
                genreinput.setText(rs.getString("Genre"));
                realseaseinput.setText(rs.getString("ReleaseDate"));
            } else {
                error.setText("No book found with ID" + bookId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    private ObservableList<Books> getBooksList() {
        ObservableList<Books> booksList = FXCollections.observableArrayList();
        String query = "SELECT * FROM Books";
        try (Connection conn = Database.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                Books book = new Books(rs.getInt("BookID"), rs.getString("BookName"), rs.getString("Genre"), rs.getString("ReleaseDate"));
                booksList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
        //in the above code  i used a function executeQuery which is defined  below
    }

    private void showBooks() {
        ObservableList<Books> list = getBooksList();   // this is the function we defined in line no 75
        ID_Col.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        Book_col.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        gen_col.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        Date_col.setCellValueFactory(new PropertyValueFactory<>("ReleaseDate"));
        tablefordata.setItems(list);  //this sets the list in table view as like we have mapped the table in above code
    }


    //in this below code it takes one string query and list of string parameter and use for loop to set the placeholder value in string
    private void executeQuery(String query, List<String> parameters) {
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            for (int i = 0; i < parameters.size(); i++) {
                ps.setString(i + 1, parameters.get(i));
            }
            ps.executeUpdate();
            showBooks();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // in this code below we defined the function that takes a string input and parse into integer we use this function to validate the data
    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    //in this code below we check if the id exist in the database or not to perform validation

    private boolean doesIdExist(String id) {
        String query = "SELECT COUNT(*) FROM Books WHERE BookID = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Returns true if count is greater than 0, meaning ID exists
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

// below code helps to validate data
    public void  validateID() {
        if (inputbookid.getText().isEmpty()){
            error.setText("Enter an ID");}
        else{
                if (!isInteger(inputbookid.getText())) {
            error.setText("Error: ID must be an integer");
        } else {
            if (!doesIdExist(inputbookid.getText())) {
                error.setText("Error:ID does Not exist");

            } else {
                error.setText("");
            }
        }
    }}

    // this is the function that we use to make the input filed empty after each action.
    private void makeEmpty() {
        booknameinput.setText("");
        genreinput.setText("");
        inputbookid.setText("");
        realseaseinput.setText("");

    }
}




