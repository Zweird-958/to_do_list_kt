module com.example.cod_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cod_javafx to javafx.fxml;
    exports com.example.cod_javafx;
}