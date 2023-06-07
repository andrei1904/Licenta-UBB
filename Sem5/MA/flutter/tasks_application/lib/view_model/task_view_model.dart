import 'package:tasks_application/model/domain/task.dart';
import 'package:tasks_application/model/repo/task_repository.dart';

class TaskViewModel {
  final TaskRepository _repository = TaskRepository();

  Future<List<Task>> getTasks() async {
    List<Task> tasks = _repository.getTasks();
    return tasks;
  }

  Future<void> addTask(Task task) async {
    _repository.addTask(task);
  }

  Future<void> deleteTask(int id) async {
    _repository.deleteTask(id);
  }

  Future<void> updateTask(Task task) async {
    _repository.updateTask(task);
  }
}