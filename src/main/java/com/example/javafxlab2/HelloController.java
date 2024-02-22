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
    private Button addbut,deletebut,getdatabut,updatebut;

    @FXML
    private TextField booknameinput,genreinput,inputbookid,realseaseinput;


    @FXML
    private Label error;

    @FXML
    private TableColumn<Books, String> gen_col;

    @FXML
    private TableView<Books> tablefordata;

    @FXML
    void addData(ActionEvent event) {
        String query = "INSERT INTO Books ( BookName, Genre, ReleaseDate) VALUES (?, ?, ?)";
            executeQuery(query, List.of(booknameinput.getText(), genreinput.getText(), realseaseinput.getText()));

        makeEmpty();
    }

    @FXML
    void deleteData(ActionEvent event) {
        String query = "DELETE FROM Books WHERE BookID=?";
        executeQuery(query, List.of(inputbookid.getText()));

        makeEmpty();
    }

    @FXML
    void getData(ActionEvent event) {
        showBooks();

    }

    @FXML
    void updateData(ActionEvent event) {

            String query = "UPDATE Books SET BookName=?, Genre=?, ReleaseDate=? WHERE BookID=?";
            executeQuery(query, List.of(booknameinput.getText(), genreinput.getText(), realseaseinput.getText(), inputbookid.getText()));

        makeEmpty();
    }

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
                Books book = new Books(rs.getInt("BookID"), rs.getString("BookName"), rs.getString("Genre"),rs.getString("ReleaseDate"));
                booksList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return booksList;
    }
    private void showBooks() {
        ObservableList<Books> list = getBooksList();

        ID_Col.setCellValueFactory(new PropertyValueFactory<>("BookID"));
        Book_col.setCellValueFactory(new PropertyValueFactory<>("BookName"));
        gen_col.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        Date_col.setCellValueFactory(new PropertyValueFactory<>("ReleaseDate"));


        tablefordata.setItems(list);
    }
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

    private void makeEmpty(){
        booknameinput.setText("");
                genreinput.setText("");
                        inputbookid.setText("");
                        realseaseinput.setText("");

    }



}



