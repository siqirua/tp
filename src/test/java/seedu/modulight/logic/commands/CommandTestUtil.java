package seedu.modulight.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.modulight.logic.commands.exceptions.CommandException;
import seedu.modulight.model.Model;

/**
 * A utility class for test cases in command execution.
 */
public class CommandTestUtil {
    //@@author siqirua-reused
    //Inspired by
    // https://github.com/AY2324S1-CS2103T-F10-3/tp/blob/master/src/test/java/seedu/address/testutil/TestUtil.java
    // and make some minor modifications to suit this project

    /**
     * Executes the given {@code command} under positive test cases, checks if <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} after execution matches {@code expectedModel}
     *
     * @param command Command to be executed.
     * @param actualModel Actual model before the execution.
     * @param expectedResultString Expected string representing the expected command result.
     * @param expectedModel Expected model after the execution.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedResultString,
                                            Model expectedModel) {

        CommandResult expectedResult;
        try {
            expectedResult = new CommandResult(expectedResultString);
        } catch (Exception e) {
            throw new AssertionError("Command Result not successfully created.", e);
        }
        assertCommandSuccess(command, actualModel, expectedResult, expectedModel);
    }

    /**
     * Executes the given {@code command} under positive test cases, checks if <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} after execution matches {@code expectedModel}
     *
     * @param command Command to be executed.
     * @param actualModel Actual model before the execution.
     * @param expectedResult Expected command result.
     * @param expectedModel Expected model after the execution.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command in positive test cases should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command} under negative test cases, checks if <br>
     * - the thrown {@link Exception} matches {@code expectedException} <br>
     * - the {@code actualModel} after execution matches {@code expectedModel}
     *
     * @param command Command to be executed.
     * @param actualModel Actual model before the execution.
     * @param expectedException Expected exception to be thrown.
     * @param expectedModel Expected model after the execution, usually the same as the actual model before execution.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandException expectedException,
                                            Model expectedModel) {
        try {
            command.execute(actualModel);
            throw new AssertionError("Execution of command in negative test cases should not succeed.");
        } catch (CommandException ce) {
            assertEquals(expectedException, ce);
        } finally {
            assertEquals(actualModel, expectedModel);
        }
    }
    //@@author
}
