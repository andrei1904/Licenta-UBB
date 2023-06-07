import 'package:flutter/material.dart';
import 'package:tasks_application/view/screens/task_add.dart';
import 'package:tasks_application/view/screens/tasks_list.dart';
import 'package:tasks_application/view_model/task_view_model.dart';

import 'model/domain/task.dart';

void main() {
  WidgetsFlutterBinding.ensureInitialized();

    runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Tasks",
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: "Tasks Home Page"),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  final TaskViewModel taskViewModel = TaskViewModel();
  List<Task> tasks = List.empty();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: FutureBuilder(
          future: loadList(),
          builder: (context, snapshot) {
            return tasks.isNotEmpty
                ? TasksList(tasks: tasks)
                : const Center(
                    child: Text(
                    "There are no tasks, tap plus button to add a task!",
                  ));
          },
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          _navigateToAddTaskScreen(context);
        },
        tooltip: "Increment",
        child: const Icon(Icons.add),
      ),
    );
  }

  Future loadList() {
    Future<List<Task>> futureTasks = taskViewModel.getTasks();
    futureTasks.then((tasks) {
      setState(() {
        this.tasks = tasks;
      });
    });

    return futureTasks;
  }

  _navigateToAddTaskScreen(BuildContext context) async {
    Navigator.push(context, MaterialPageRoute(builder: (context) => const TaskAdd()));
  }
}
