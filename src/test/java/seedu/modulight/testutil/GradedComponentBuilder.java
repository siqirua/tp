package seedu.modulight.testutil;

import seedu.modulight.model.gradedcomponent.GcName;
import seedu.modulight.model.gradedcomponent.GradedComponent;
import seedu.modulight.model.gradedcomponent.MaxMarks;
import seedu.modulight.model.gradedcomponent.Weightage;

/**
 * A utility class to help with building Graded Component object
 */
public class GradedComponentBuilder {

    public static final String DEFAULT_GC_NAME = "Midterms";
    public static final float DEFAULT_WEIGHTAGE = (float) 100.0;
    public static final float DEFAULT_MAX_MARKS = (float) 100.0;

    private GcName gcName;
    private MaxMarks maxMarks;
    private Weightage weightage;

    /**
     * Creates GradedComponentBuilder with default details.
     */
    public GradedComponentBuilder() {
        gcName = new GcName(DEFAULT_GC_NAME);
        maxMarks = new MaxMarks(DEFAULT_MAX_MARKS);
        weightage = new Weightage(DEFAULT_WEIGHTAGE);
    }

    /**
     * Creates GradedComponentBuilder with an existing graded component.
     *
     * @param gradedComponentToCopy
     */
    public GradedComponentBuilder(GradedComponent gradedComponentToCopy) {
        gcName = gradedComponentToCopy.getName();
        maxMarks = gradedComponentToCopy.getMaxMarks();
        weightage = gradedComponentToCopy.getWeightage();
    }

    /**
     * Return GradedComponentBuilder with newly set gcName.
     *
     * @param gcName gcName.
     * @return Edited GradedComponentBuilder.
     */
    public GradedComponentBuilder withGcName(String gcName) {
        this.gcName = new GcName(gcName);
        return this;
    }

    /**
     * Return GradedComponentBuilder with newly set weightage.
     *
     * @param weightage weightage.
     * @return Edited GradedComponentBuilder.
     */
    public GradedComponentBuilder withWeightage(float weightage) {
        this.weightage = new Weightage(weightage);
        return this;
    }

    /**
     * Return GradedComponentBuilder with newly set maxMarks.
     *
     * @param maxMarks maxMarks.
     * @return Edited GradedComponentBuilder.
     */
    public GradedComponentBuilder withMaxMarks(float maxMarks) {
        this.maxMarks = new MaxMarks(maxMarks);
        return this;
    }

    public GradedComponent build() {
        return new GradedComponent(gcName, maxMarks, weightage);
    }

}
