import 'dart:math';

import 'package:tasks_application/model/domain/priority.dart';
import 'package:tasks_application/model/domain/status.dart';
import 'package:tasks_application/model/domain/task.dart';

class TaskRepository {
  static final TaskRepository _instance = TaskRepository._internal();

  final List<Task> _taskList = [];

  factory TaskRepository() {
    return _instance;
  }

  TaskRepository._internal() {
    generateTasks();
  }

  void generateTasks() {
    for (int i = 1; i <= 5; i++) {
      if (i % 3 == 0) {
        _taskList.add(Task(
            i,
            "school",
            "Title$i",
            "Descript",
            DateTime.now(),
            Priority.low,
            DateTime.now(),
            Status.newTask,
            0));
      } else if (i % 3 == 1) {
        _taskList.add(Task(
            i,
            "school",
            "Title$i",
            "Description",
            DateTime.now(),
            Priority.medium,
            DateTime.now(),
            Status.done,
            100));
      } else {
        _taskList.add(Task(
            i,
            "school",
            "Title$i",
            "Description",
            DateTime.now(),
            Priority.high,
            DateTime.now(),
            Status.inProgress,
            i * 5 % 100));
      }
    }
  }

  List<Task> getTasks() {
    return _taskList;
  }

  void addTask(Task task) {
    var rand = Random();

    var taskIds = _taskList.map((e) => e.id);
    var newId = rand.nextInt(999999);
    while (taskIds.contains(newId)) {
      newId = rand.nextInt(999999);
    }

    _taskList.add(task);
  }

  void deleteTask(int id) {
    _taskList.removeWhere((element) => element.id == id);
  }

  void updateTask(Task task) {
    int index = _taskList.indexWhere((element) => element.id == task.id);

    _taskList.replaceRange(index, index + 1, [task]);
  }
}
