import 'package:flutter/material.dart';
import 'package:tasks_application/model/domain/task.dart';
import 'package:tasks_application/view/screens/task_edit.dart';
import 'package:tasks_application/view_model/task_view_model.dart';

class TasksList extends StatelessWidget {
  TasksList({Key? key, required this.tasks}) : super(key: key);

  final List<Task> tasks;
  final TaskViewModel taskViewModel = TaskViewModel();

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: tasks.length,
      itemBuilder: (BuildContext context, int index) {
        return Card(
          child: InkWell(
            onTap: () {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => TaskEdit(tasks[index])),
              );
            },
            child: ListTile(
              title: Text(tasks[index].title),
              subtitle: Text(tasks[index].domain),
              trailing: IconButton(
                icon: const Icon(Icons.delete),
                onPressed: () {
                  showDialog(context: context, builder: (_) => AlertDialog(
                    title: const Text("Delete Task"),
                    content: const Text("Are you sure you want to delete this task?"),

                    actions: <Widget>[
                      ElevatedButton(
                        onPressed: () {
                          Navigator.of(context, rootNavigator: true).pop(false);
                        },
                        child: const Text("No"),
                      ),
                      ElevatedButton(
                        onPressed: () {
                          taskViewModel.deleteTask(tasks[index].id);
                          Navigator.of(context, rootNavigator: true).pop(true);
                        },
                        child: const Text("Yes"),
                      ),
                    ],
                  ));
                },
              ),
            ),
          ),
        );
      },
    );
  }
}
