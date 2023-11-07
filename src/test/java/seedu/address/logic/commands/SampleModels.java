package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.student.UniqueStudentList;
import seedu.address.model.student.model.StudentBook;

public class SampleModels {
    public static final Model sampleModel1 = new ModelManager(new StudentBook(new UniqueStudentList()))
}
