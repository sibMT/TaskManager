import java.util.ArrayList;

public class Epic extends Task {
    ArrayList<Integer> subtasks;

    public Epic(String taskName, String description,TaskStatus taskStatus) {
        super(taskName,description,taskStatus);
    }

    public ArrayList<Integer> getSubtasks() {
        return subtasks;
    }

    public void setSubtasks(ArrayList<Integer> subtasks) {
        this.subtasks = subtasks;
    }

    public void removeSubtaskId(Integer id) {
        subtasks.remove(id);
    }

    public void clearSubtasks() {
        subtasks.clear();
    }
}
