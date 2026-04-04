import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;

public class StudentController {

    @FXML private TextField idField;
    @FXML private TextField firstField;
    @FXML private TextField lastField;
    @FXML private TextField ageField;
    @FXML private TextField emailField;

    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student, String> idCol;
    @FXML private TableColumn<Student, String> firstCol;
    @FXML private TableColumn<Student, String> lastCol;
    @FXML private TableColumn<Student, Integer> ageCol;
    @FXML private TableColumn<Student, String> emailCol;

    private ObservableList<Student> students = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        idCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getId()));

        firstCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getFirstName()));

        lastCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getLastName()));

        ageCol.setCellValueFactory(data ->
                new SimpleObjectProperty<>(data.getValue().getAge()));

        emailCol.setCellValueFactory(data ->
                new SimpleStringProperty(data.getValue().getEmail()));

        table.setItems(students);

        // 👇 لما تكبسي على row
        table.setOnMouseClicked(e -> {
            Student s = table.getSelectionModel().getSelectedItem();

            if (s != null) {
                idField.setText(s.getId());
                firstField.setText(s.getFirstName());
                lastField.setText(s.getLastName());
                ageField.setText(String.valueOf(s.getAge()));
                emailField.setText(s.getEmail());
            }
        });
    }

    // ✅ ADD
    @FXML
    private void addStudent() {
        try {
            Student s = new Student(
                    idField.getText(),
                    firstField.getText(),
                    lastField.getText(),
                    Integer.parseInt(ageField.getText()),
                    emailField.getText()
            );
            students.add(s);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    // ✅ DELETE
    @FXML
    private void deleteStudent() {
        Student selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            students.remove(selected);
        }
    }

    // ✅ UPDATE
    @FXML
    private void updateStudent() {
        Student selected = table.getSelectionModel().getSelectedItem();

        if (selected != null) {
            int index = table.getSelectionModel().getSelectedIndex();

            Student updated = new Student(
                    idField.getText(),
                    firstField.getText(),
                    lastField.getText(),
                    Integer.parseInt(ageField.getText()),
                    emailField.getText()
            );

            students.set(index, updated);
        }
    }

    // ✅ CLEAR
    @FXML
    private void clearFields() {
        idField.clear();
        firstField.clear();
        lastField.clear();
        ageField.clear();
        emailField.clear();
    }
}