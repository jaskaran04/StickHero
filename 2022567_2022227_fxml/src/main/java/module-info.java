module com.example.new_ap_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.new_ap_project to javafx.fxml;
    exports com.example.new_ap_project;
}