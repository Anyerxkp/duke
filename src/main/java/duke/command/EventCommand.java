package duke.command;

import java.io.IOException;
import java.util.HashMap;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.exception.InvalidInputException;
import duke.exception.InvalidInputException.InputExceptionType;
import duke.exception.SaveException;
import duke.task.Event;
import java.time.format.DateTimeParseException;
import duke.DateTime;

/**
 * Add an event task into the task list
 */
public class EventCommand extends Command {
    public EventCommand(Ui ui, TaskList tasks, HashMap<String, String> arguments) {
        super(ui, tasks, arguments);
    }

    @Override
    public void execute() throws InvalidInputException, IOException, SaveException {
        String at = arguments.get("at");
        String to = arguments.get("to");
        if (at == null || at.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_AT_DATE);
        }
        try {
            tasks.addTask(new Event(arguments.get("payload"), new DateTime(at),new DateTime(to)));
            ui.printNewTask(tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(InputExceptionType.MALFORMED_DATE);
        }
    }

    @Override
    public String executeGui() throws InvalidInputException, IOException, SaveException {
        String at = arguments.get("at");
        String to = arguments.get("to");
        if (at == null || at.length() == 0) {
            // Either /at is not found at all, or no dates are following /at
            throw new InvalidInputException(InputExceptionType.NO_AT_DATE);
        }
        try {
            tasks.addTask(new Event(arguments.get("payload"), new DateTime(at),new DateTime(to)));
        } catch (DateTimeParseException e) {
            throw new InvalidInputException(InputExceptionType.MALFORMED_DATE);
        }
        return "nice,I have added this event with at";
    }
}