package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Task;

/**
 * Delete a task from the task list
 */
public class DeleteCommand extends Command {
    public DeleteCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        String indexString = arguments.get("payload");
        if (indexString == null) {
            // An index must be provided for the task to be marked "done"
            throw new InvalidInputException(InputExceptionType.EMPTY_INDEX);
        } else {
            try {
                int index = Integer.parseInt(indexString);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new InvalidInputException(InputExceptionType.INDEX_OUT_OF_BOUND);
                }

                Task task = tasks.deleteTask(index - 1);
                ui.print("Sure! I've removed this task:");
                ui.print("\t" + task);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(InputExceptionType.NOT_INTEGER, e);
            }
        }
    }

    @Override
    public String executeGui() throws InvalidInputException, IOException, SaveException {
        String indexString = arguments.get("payload");
        if (indexString == null) {
            // An index must be provided for the task to be marked "done"
            throw new InvalidInputException(InputExceptionType.EMPTY_INDEX);
        } else {
            try {
                int index = Integer.parseInt(indexString);
                if (index > tasks.size() || index < 1) {
                    // This index is out of the boundary of our database
                    throw new InvalidInputException(InputExceptionType.INDEX_OUT_OF_BOUND);
                }

                Task task = tasks.deleteTask(index - 1);
            } catch (NumberFormatException e) {
                throw new InvalidInputException(InputExceptionType.NOT_INTEGER, e);
            }
        }
        return "Sure! I've removed this task";
    }
}