using System.ComponentModel.DataAnnotations;
using TasksApi.Model.Enums;

namespace TasksApi.Model.Entities
{
    public class Task
    {
        [Key]
        public int Id { get; set; }
        public string Domain { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public long CreatedTime { get; set; }
        public Priority Priority { get; set; }
        public long Deadline { get; set; }
        public Status Status { get; set; }
        public int Progress { get; set; }
    }
}