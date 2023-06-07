namespace TasksApi.Model.Enums
{
    public enum Priority
    {
        Low = 0, Medium = 1, High = 2
    }

    public static class PriorityExtensions
    {
        public static string GetString(this Priority priority)
        {
            return priority switch
            {
                Priority.Medium => "MEDIUM",
                Priority.High => "HIGH",
                _ => "LOW"
            };
        }

        public static Priority GetPriority(this string priority)
        {
            return priority switch
            {
                "MEDIUM" => Priority.Medium,
                "HIGH" => Priority.High,
                _ => Priority.Low
            };
        }
    }
}