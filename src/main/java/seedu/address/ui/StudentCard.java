package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.student.Student;


/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label sid;
    @FXML
    private Label id;
    @FXML
    private Label studentName;
    @FXML
    private Label studentEmail;
    @FXML
    private Label tutorialGroup;

    @FXML
    private FlowPane tags;

    @FXML
    private VBox studentBox;




    /**
     * Creates a {@code StudentCard} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        sid.setText(student.getStudentId().sid);
        studentName.setText("Name: " + student.getName().fullName);
        studentEmail.setText("Email: " + student.getEmail().value);
        tutorialGroup.setText("Tutorial Group: " + student.getTutorial().groupName);
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        student.recalculateScores();
        Label totalScoreLabel = new Label("Total Score: " + student.getTotalScore() + "%");
        totalScoreLabel.getStyleClass().add("cell_small_label");
        studentBox.getChildren().add(totalScoreLabel);

    }
}
