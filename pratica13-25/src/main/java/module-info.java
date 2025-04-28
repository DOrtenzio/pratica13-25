module com.example.pratica1325 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.pratica1325 to javafx.fxml;
    exports com.example.pratica1325;
}