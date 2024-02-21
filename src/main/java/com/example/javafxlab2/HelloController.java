package com.example.javafxlab2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.Books;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private TableColumn<Books, String> Book_col;

    @FXML
    private TableColumn<Books, String> Date_col;

    @FXML
    private TableColumn<Books, Integer> ID_Col;

    @FXML
    private Button addbut;

    @FXML
    private TextField booknameinput;

    @FXML
    private Button deletebut;

    @FXML
    private Label error;

    @FXML
    private TableColumn<Books, String> gen_col;

    @FXML
    private TextField genreinput;

    @FXML
    private Button getdatabut;

    @FXML
    private TextField inputbookid;

    @FXML
    private TextField realseaseinput;

    @FXML
    private TableView<Books> tablefordata;

    @FXML
    private Button updatebut;
    @FXML
    void addData(ActionEvent event) {

    }

    @FXML
    void deleteData(ActionEvent event) {

    }

    @FXML
    void getData(ActionEvent event) {

    }

    @FXML
    void updateData(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}



