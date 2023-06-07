using System.Collections.Generic;
using TasksApi.Model.Entities;
using TasksApi.Model.Enums;
using TasksApi.Model.ViewModels;

namespace TasksApi.Model.Mappers
{
    public static class TaskMappers
    {
        public static Task ToEntity(this TaskViewModel taskViewModel)
        {
            var task = new Task
            {
                Id = taskViewModel.Id,
                Domain = taskViewModel.Domain,
                Title = taskViewModel.Title,
                Description = taskViewModel.Description,
                CreatedTime = taskViewModel.CreatedTime,
                Priority = taskViewModel.Priority.GetPriority(),
                Deadline = taskViewModel.Deadline,
                Status = taskViewModel.Status.GetStatus(),
                Progress = taskViewModel.Progress
            };

            return task;
        }

        public static TaskViewModel ToViewModel(this Task task)
        {
            var taskViewModel = new TaskViewModel
            {
                Id = task.Id,
                Domain = task.Domain,
                Title = task.Title,
                Description = task.Description,
                CreatedTime = task.CreatedTime,
                Priority = task.Priority.GetString(),
                Deadline = task.Deadline,
                Status = task.Status.GetString(),
                Progress = task.Progress
            };

            return taskViewModel;
        }

        public static Task ToEnity(this TaskCreationViewModel taskCreationViewModel)
        {
            var task = new Task()
            {
                Domain = taskCreationViewModel.Domain,
                Title = taskCreationViewModel.Title,
                Description = taskCreationViewModel.Description,
                CreatedTime = taskCreationViewModel.CreatedTime,
                Priority = taskCreationViewModel.Priority.GetPriority(),
                Deadline = taskCreationViewModel.Deadline,
                Status = taskCreationViewModel.Status.GetStatus(),
                Progress = taskCreationViewModel.Progress
            };

            return task;
        }
        
        public static Task ToEnity(this TaskUpdateViewModel taskUpdateViewModel)
        {
            var task = new Task()
            {
                Domain = taskUpdateViewModel.Domain,
                Title = taskUpdateViewModel.Title,
                Description = taskUpdateViewModel.Description,
                CreatedTime = taskUpdateViewModel.CreatedTime,
                Priority = taskUpdateViewModel.Priority.GetPriority(),
                Deadline = taskUpdateViewModel.Deadline,
                Status = taskUpdateViewModel.Status.GetStatus(),
                Progress = taskUpdateViewModel.Progress
            };

            return task;
        }
    }
}