using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using TasksApi.Business.Interfaces;
using TasksApi.Repository;

namespace TasksApi.Business.Implementations
{
    public class TaskService : ITaskService
    {
        private readonly DataContext _dataContext;

        public TaskService(DataContext dataContext)
        {
            _dataContext = dataContext;
        }
        
        public IAsyncEnumerable<Model.Entities.Task> GetAllAsync()
        {
            return _dataContext.Tasks.AsAsyncEnumerable();
        }

        public async Task<Model.Entities.Task> GetByIdAsync(int id)
        {
            return await _dataContext.Tasks
                .FirstOrDefaultAsync(task => task.Id == id);
        }

        public async Task<Model.Entities.Task> AddAsync(Model.Entities.Task task)
        {
            await _dataContext.Tasks.AddAsync(task);
            await _dataContext.SaveChangesAsync();

            return task;
        }

        public async Task<bool> DeleteAsync(int id)
        {
            var existingTask = await _dataContext.Tasks
                .FirstOrDefaultAsync(task => task.Id == id);

            if (existingTask == null)
            {
                return false;
            }

            _dataContext.Tasks.Remove(existingTask);
            await _dataContext.SaveChangesAsync();

            return true;
        }

        public async Task<Model.Entities.Task> UpdateAsync(Model.Entities.Task newTask)
        {
            var existingTask = await _dataContext.Tasks
                .FirstOrDefaultAsync(task => task.Id == newTask.Id);

            if (existingTask == null)
            {
                return null;
            }

            existingTask.Domain = newTask.Domain;
            existingTask.Title = newTask.Title;
            existingTask.Description = newTask.Description;
            existingTask.CreatedTime = newTask.CreatedTime;
            existingTask.Priority = newTask.Priority;
            existingTask.Deadline = newTask.Deadline;
            existingTask.Status = newTask.Status;
            existingTask.Progress = newTask.Progress;

            await _dataContext.SaveChangesAsync();

            return existingTask;
        }
    }
}