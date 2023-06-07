import 'package:datetime_picker_formfield/datetime_picker_formfield.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:intl/intl.dart';
import 'package:tasks_application/model/domain/priority.dart';
import 'package:tasks_application/model/domain/status.dart';
import 'package:tasks_application/model/domain/task.dart';
import 'package:tasks_application/view_model/task_view_model.dart';

class TaskEdit extends StatefulWidget {
  const TaskEdit(this.task, {Key? key}) : super(key: key);

  final Task task;

  @override
  _TaskEditState createState() => _TaskEditState();
}

class _TaskEditState extends State<TaskEdit> {
  _TaskEditState();

  final TaskViewModel taskViewModel = TaskViewModel();

  final _addFormKey = GlobalKey<FormState>();
  late int _id;
  late DateTime _createdTime;
  final _domainController = TextEditingController();
  final _titleController = TextEditingController();
  final _descriptionController = TextEditingController();
  final _progressController = TextEditingController();
  final _format = DateFormat("dd-MMM-yyyy - kk:mm");
  late Priority _priority;
  late Status _status;
  late DateTime _deadline;

  @override
  void initState() {
    _id = widget.task.id;
    _domainController.text = widget.task.domain;
    _titleController.text = widget.task.title;
    _descriptionController.text = widget.task.title;
    _progressController.text = widget.task.progress.toString();
    _priority = widget.task.priority;
    _status = widget.task.status;
    _deadline = widget.task.deadline;
    _createdTime = widget.task.createdTime;

    super.initState();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Edit Task")),
      body: Form(
        key: _addFormKey,
        child: SingleChildScrollView(
          child: Container(
            padding: const EdgeInsets.all(20.0),
            child: Card(
              child: Container(
                padding: const EdgeInsets.all(10.0),
                width: 440,
                child: Column(
                  children: <Widget>[
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Created Time"),
                          TextFormField(
                            initialValue: _format.format(_createdTime).toString(),
                            textAlign: TextAlign.center,
                            decoration: const InputDecoration(
                              border: InputBorder.none,
                            ),
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Domain"),
                          TextFormField(
                            controller: _domainController,
                            decoration: const InputDecoration(
                              hintText: "Domain",
                            ),
                            validator: (value) {
                              if (value!.isEmpty) {
                                return "Please enter domain";
                              }
                              return null;
                            },
                            onChanged: (value) {},
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Title"),
                          TextFormField(
                            controller: _titleController,
                            decoration: const InputDecoration(
                              hintText: "Title",
                            ),
                            validator: (value) {
                              if (value!.isEmpty) {
                                return "Please enter title";
                              }
                              return null;
                            },
                            onChanged: (value) {},
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Priority"),
                          DropdownButtonFormField(
                            value: _priority,
                            items: Priority.values.map((Priority priority) {
                              return DropdownMenuItem<Priority>(
                                  value: priority,
                                  child: Row(
                                    children: <Widget>[
                                      Text(priority
                                          .toString()
                                          .replaceRange(0, 9, "")),
                                    ],
                                  ));
                            }).toList(),
                            decoration: const InputDecoration(
                              hintText: "Priority",
                            ),
                            validator: (value) {
                              if (value == null) {
                                return "Please select priority";
                              }
                              return null;
                            },
                            onChanged: (value) {
                              _priority = Priority.values
                                  .firstWhere((element) => element == value);
                            },
                          ),
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Progress"),
                          TextFormField(
                            controller: _progressController,
                            inputFormatters: [FilteringTextInputFormatter.digitsOnly],
                            decoration: const InputDecoration(
                              hintText: "Progress",
                            ),
                            validator: (value) {
                              if (value!.isEmpty) {
                                return "Please enter progress";
                              }
                              if (int.parse(value) < 0 || int.parse(value) > 100) {
                                return "Please enter a value between 0 and 100";
                              }
                              return null;
                            },
                            onChanged: (value) {},
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Deadline"),
                          DateTimeField(
                            initialValue: _deadline,
                            format: _format,
                            decoration: const InputDecoration(
                              hintText: "Deadline",
                            ),
                            onShowPicker: (context, currentValue) async {
                              final date = await showDatePicker(
                                  context: context,
                                  firstDate: DateTime.now(),
                                  initialDate: currentValue ?? DateTime.now(),
                                  lastDate: DateTime(2100));
                              if (date != null) {
                                final time = await showTimePicker(
                                  context: context,
                                  initialTime: TimeOfDay.fromDateTime(
                                      currentValue ?? DateTime.now()),
                                );

                                return DateTimeField.combine(date, time);
                              } else {
                                return currentValue;
                              }
                            },
                            validator: (value) {
                              if (value == null) {
                                return "Please select deadline";
                              }
                              return null;
                            },
                            onChanged: (value) {
                              if (value != null) {
                                _deadline = value;
                              }
                            },
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          const Text("Description"),
                          TextFormField(
                            keyboardType: TextInputType.multiline,
                            minLines: 5,
                            maxLines: null,
                            controller: _descriptionController,
                            decoration: const InputDecoration(
                              hintText: "Description",
                            ),
                            validator: (value) {
                              if (value!.isEmpty) {
                                return "Please enter description";
                              }
                              return null;
                            },
                            onChanged: (value) {},
                          )
                        ],
                      ),
                    ),
                    Container(
                      margin: const EdgeInsets.fromLTRB(0, 0, 0, 20),
                      child: Column(
                        children: <Widget>[
                          ElevatedButton(
                            onPressed: () {
                              if (_addFormKey.currentState!.validate()) {
                                _addFormKey.currentState!.save();

                                taskViewModel.updateTask(Task(
                                    _id,
                                    _domainController.value.text,
                                    _titleController.text,
                                    _descriptionController.text,
                                    _createdTime,
                                    _priority,
                                    _deadline,
                                    _status,
                                    int.parse(_progressController.text)));

                                Navigator.pop(context);
                              }
                            },
                            child: const Text(
                              "Save",
                              style: TextStyle(color: Colors.white),
                            ),
                          )
                        ],
                      ),
                    ),
                  ],
                ),
              ),
            ),
          ),
        ),
      ),
    );
  }
}
