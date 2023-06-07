namespace TasksApi.Model.Enums
{
    public enum Status
    {
        New = 0, InProgress = 1, Done = 2
    }
    
    public static class StatusExtensions
    {
        public static string GetString(this Status status)
        {
            return status switch
            {
                Status.New => "NEW",
                Status.InProgress => "IN_PROGRESS",
                _ => "DONE"
            };
        }
        
        public static Status GetStatus(this string status)
        {
            return status switch
            {
                "NEW" => Status.New,
                "IN_PROGRESS" => Status.InProgress,
                _ => Status.Done
            };
        }
    }
}