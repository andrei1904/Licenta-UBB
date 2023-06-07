using Microsoft.EntityFrameworkCore;
using TasksApi.Model.Entities;

namespace TasksApi.Repository
{
    public class DataContext : DbContext
    {
        public DataContext(DbContextOptions<DataContext> options) : base(options) { }
        
        public DbSet<Task> Tasks { get; set; }
    }
}