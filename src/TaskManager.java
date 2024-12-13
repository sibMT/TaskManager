import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks = new HashMap<>();
    private HashMap<Integer, Epic> epics = new HashMap<>();
    private HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private static int id = 1;
    private int counter = 0;

    public Task createTask(Task task) {
        Integer newId = nextId();
        task.setId(newId);
        tasks.put(task.getId(), task);
        return task;
    }

    public Task updateTask(Task task) {
        if(tasks.containsKey(task.getId())) {
            Task existingTask = tasks.get(task.getId());
            existingTask.setTaskName(task.getTaskName());
            existingTask.setDescription(task.getDescription());
            existingTask.setTaskStatus(task.getTaskStatus());
        }
        return task;
    }

    public Task removeTaskById(Integer id) {
        return tasks.remove(id);
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public ArrayList<Task> getAllTasks () {
        return new ArrayList<>(tasks.values());
    }

    public Task findTaskById(Integer id) {
        return tasks.get(id);
    }

    public Epic createEpic (Epic epic) {
        Integer newId = nextId();
        epic.setId(newId);
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        }
    }

    public void removeEpicById (Integer id) {
        Epic epic = epics.get(id);
        if(epic != null) {
            for(int subtaskId: epic.getSubtasks()) {
                subtasks.remove(subtaskId);
            }
            epics.remove(id);
        }
    }


    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public Epic findEpicById(Integer id) {
        return epics.get(id);
    }

    public Subtask createSubtasks(Subtask subtask) {
        Integer newId = nextId();
        subtask.setId(newId);
        subtasks.put(subtask.getId(), subtask);
        return subtask;
    }

    public void updateSubtask(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {
            subtasks.put(subtask.getId(), subtask);
        }
        updateEpicStatus(epics.get(subtask.getEpicId()));
    }

    public void removeSubtaskById(Integer id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        epic.removeSubtaskId(id);
        subtasks.remove(id);
        updateEpicStatus(epic);
    }

    public void removeAllSubtasks() {
        clearEpicSubtasks();
        subtasks.clear();
        for (Epic epic : epics.values()) {
            updateEpicStatus(epic);
        }
    }


    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }


    private ArrayList<Subtask> getSubtasksByEpicId (ArrayList<Integer> subTasksId) {
        ArrayList<Subtask> subTasks = new ArrayList<>();
        for (Integer id : subTasksId) {
            subTasks.add(subTasks.get(id));
        }
        return  subTasks;
    }

    private void updateEpicStatus(Epic epic) {
        if(epic.getSubtasks().isEmpty()) {
            epic.setTaskStatus((TaskStatus.NEW));
            return;
        }
        boolean allTasksIsNew = true;
        boolean allTasksIsDone = true;

        ArrayList<Subtask> epicSubtasks = getSubtasksByEpicId(epic.getSubtasks());

        for (Subtask subtask : epicSubtasks) {
            if(subtask.getTaskStatus() != TaskStatus.NEW) {
                allTasksIsNew = false;
            }
            if(subtask.getTaskStatus() != TaskStatus.DONE) {
             allTasksIsDone = false;
            }
        }
        if (allTasksIsNew) {
            epic.setTaskStatus(TaskStatus.NEW);
        } else if (allTasksIsDone) {
            epic.setTaskStatus(TaskStatus.DONE);
        }else {
            epic.setTaskStatus(TaskStatus.IN_PROGRESS);
        }
    }

    private void clearEpicSubtasks() {
        for (Epic epic : epics.values()) {
            epic.clearSubtasks();
        }
    }

    private int nextId() {
        return counter++;
    }

}
