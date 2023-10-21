package seedu.address.model.util;

import seedu.address.model.gradedcomponent.GradedComponent;
import seedu.address.model.gradedcomponent.model.GradedComponentBook;
import seedu.address.model.gradedcomponent.model.ReadOnlyGradedComponentBook;

/**
 * Contains utility methods for populating GradedComponentBook with sample data.
 */
public class SampleGcDataUtil {

    public static GradedComponent[] getSampleGradedComponents() {
        return new GradedComponent[]{};
    }

    public static ReadOnlyGradedComponentBook getSampleGcBook() {
        GradedComponentBook sampleAb = new GradedComponentBook();
        for (GradedComponent sampleGradedComponent : getSampleGradedComponents()) {
            sampleAb.addGradedComponent(sampleGradedComponent);
        }
        return sampleAb;
    }
}
