package seedu.modulight.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.model.GradedComponentBook;

public class TypicalGradedComponents {

    private TypicalGradedComponents() {};

    public static final GradedComponent MIDTERMS = new GradedComponentBuilder().withGcName("Midterms")
            .withMaxMarks((float) 100.0).withWeightage((float) 25).build();
    public static final GradedComponent FINALS = new GradedComponentBuilder().withGcName("Finals")
            .withMaxMarks((float) 100).withWeightage((float) 60).build();
    public static final GradedComponent QUIZ = new GradedComponentBuilder().withGcName("Quiz").withMaxMarks((float) 50)
            .withWeightage((float) 15).build();

    public static final GradedComponent CA1 = new GradedComponentBuilder().withGcName("CA1").withMaxMarks((float) 100)
            .withWeightage((float) 15).build();

    public static final GradedComponent CA2 = new GradedComponentBuilder().withGcName("CA2").withMaxMarks((float) 100)
            .withWeightage((float) 20).build();


    public static GradedComponentBook getTypicalGradedComponentBook() {
        GradedComponentBook gcb = new GradedComponentBook();
        for (GradedComponent gradedComponent : getTypicalGradedComponents()) {
            gcb.addGradedComponent(gradedComponent);
        }
        return gcb;
    }

    public static List<GradedComponent> getTypicalGradedComponents() {
        return new ArrayList<>(Arrays.asList(MIDTERMS, FINALS, QUIZ));
    }


}
