import 'package:tasks_application/model/domain/priority.dart';
import 'package:tasks_application/model/domain/status.dart';

class Task {
  int id;
  final String domain;
  final String title;
  final String description;
  final DateTime createdTime;
  final Priority priority;
  final DateTime deadline;
  final Status status;
  final int progress;

  Task(
      this.id,
      this.domain,
      this.title,
      this.description,
      this.createdTime,
      this.priority,
      this.deadline,
      this.status,
      this.progress);
}
