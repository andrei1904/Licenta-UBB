using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using TasksApi.Business.Interfaces;
using TasksApi.Model.Mappers;
using TasksApi.Model.ViewModels;

namespace TasksApi.Controllers
{
    [ApiController]
    [Route("api/task")]
    public class TaskController : ControllerBase
    {
        private readonly ITaskService _taskService;

        public TaskController(ITaskService taskService)
        {
            _taskService = taskService;
        }

        [HttpGet]
        public IActionResult GetAll()
        {
            var result = _taskService.GetAllAsync()
                .Select(task => task.ToViewModel());

            if (result.CountAsync().Result == 0)
            {
                return NotFound();
            }

            return Ok(result);
        }

        [HttpGet]
        [Route("{id:int}")]
        public async Task<IActionResult> GetById([FromRoute] int id)
        {
            var task = await _taskService.GetByIdAsync(id);

            if (task == null) return NotFound();

            return Ok(task.ToViewModel());
        }

        [HttpPost]
        public async Task<IActionResult> Add([FromBody] TaskCreationViewModel taskCreationViewModel)
        {
            return Ok(
                (await _taskService.AddAsync(
                    taskCreationViewModel.ToEnity())
                ).ToViewModel()
            );
        }

        [HttpDelete]
        [Route("{id:int}")]
        public async Task<IActionResult> Delete(int id)
        {
            var result = await _taskService.DeleteAsync(id);

            if (!result) return NotFound(false);

            return Ok(true);
        }

        [HttpPut]
        [Route("{id:int}")]
        public async Task<IActionResult> UpdateBoard([FromRoute] int id,
            [FromBody] TaskUpdateViewModel taskUpdateViewModel)
        {
            var task = taskUpdateViewModel.ToEnity();
            task.Id = id;

            task = await _taskService.UpdateAsync(task);

            if (task == null) return NotFound();

            return Ok(task.ToViewModel());
        }
    }
}