
using System.Collections.Generic;
using System.Threading.Tasks;


namespace TasksApi.Business.Interfaces
{
    public interface ITaskService
    {
        IAsyncEnumerable<Model.Entities.Task> GetAllAsync();

        Task<Model.Entities.Task> GetByIdAsync(int id);

        Task<Model.Entities.Task> AddAsync(Model.Entities.Task task);

        Task<bool> DeleteAsync(int id);

        Task<Model.Entities.Task> UpdateAsync(Model.Entities.Task newTask);
    }
}