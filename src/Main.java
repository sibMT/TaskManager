public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        Task task = new Task("уборка квартиры","мойка",TaskStatus.NEW);
        Task task1 = new Task("учеба java","спринт 4",TaskStatus.IN_PROGRESS);
        Epic epic = new Epic("Разобраться с теорией","Наследование",TaskStatus.IN_PROGRESS);
        Epic epic1 = new Epic("Задача","Функции", TaskStatus.NEW);
        Subtask subtask = new Subtask(epic,"Классы","особенности", TaskStatus.IN_PROGRESS);
        Subtask subtask1Epic1 = new Subtask(epic1,"Линейные уравнения","Выход за пределы",TaskStatus.DONE);
        Subtask subtask2Epic1 = new Subtask(epic1,"Основы 11 класс","примеры из 2015", TaskStatus.IN_PROGRESS);
        taskManager.createTask(task);
        taskManager.createTask(task1);
        taskManager.createEpic(epic);
        taskManager.createEpic(epic1);
        taskManager.createSubtasks(subtask);
        taskManager.createSubtasks(subtask1Epic1);
        taskManager.createSubtasks(subtask2Epic1);
    }
}
