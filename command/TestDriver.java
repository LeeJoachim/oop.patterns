package command;

import java.util.ArrayList;
import java.util.List;

interface Command {
    public void execute();
}
class LightOn implements Command {
    public void execute() {
        System.out.println("Light is on");
    }
}
class LightOff implements Command {
    public void execute() {
        System.out.println("Light is off");
    }
}
class NoCommand implements Command {
    public void execute() {
        System.out.println("No command");
    }
}

class Invoker {
    private List<Command> onCommands = new ArrayList<Command>();
    private List<Command> offCommands = new ArrayList<Command>();
    private List<Command> undoCommands = new ArrayList<Command>();

    public Invoker() {
        for (int i = 0; i < 7; i++) {
            onCommands.add(new NoCommand());
            offCommands.add(new NoCommand());
            onCommands.add(new Command() {
                public void execute() {
                    System.out.println("No command");
                }
            });
        }
    }
    public void addOnCommands(int n, Command onCommand) {
        onCommands.add(n, onCommand);
    }
    public void addOffCommands(int n, Command offCommand) {
        offCommands.add(n, offCommand);
    }
    public void onButtonPressed(int slot) {
        onCommands.get(slot).execute();
        addUndoCommands(onCommands.get(slot));
    }
    public void offButtonPressed(int slot) {
        offCommands.get(slot).execute();
        addUndoCommands(offCommands.get(slot));
    }
    public void addUndoCommands(Command undoCommand) {
        undoCommands.add(undoCommand);
    }
    public void undoButtonPressed() {
        if (undoCommands.size() == 0) {
            System.out.println("list is empty");
            return;
        }
        undoCommands.get(undoCommands.size() - 1).execute();
        undoCommands.remove(undoCommands.size() - 1);
    }

}

public class TestDriver {
    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        invoker.addOnCommands(0, new LightOn());
        invoker.addOffCommands(0, new LightOff());
        invoker.onButtonPressed(0);
        invoker.offButtonPressed(0);
        invoker.undoButtonPressed();
        invoker.undoButtonPressed();
        invoker.undoButtonPressed();
    }
}