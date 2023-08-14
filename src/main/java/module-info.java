module com.example.deber_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.deber_javafx to javafx.fxml;
    exports com.example.deber_javafx;
}