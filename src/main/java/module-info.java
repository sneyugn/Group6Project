module com.cs210.groupproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cs210.groupproject to javafx.fxml;
    exports com.cs210.groupproject;
}