﻿using TasksApi.Model.Enums;

namespace TasksApi.Model.ViewModels
{
    public class TaskCreationViewModel
    {
        public string Domain { get; set; }
        public string Title { get; set; }
        public string Description { get; set; }
        public long CreatedTime { get; set; }
        public string Priority { get; set; }
        public long Deadline { get; set; }
        public string Status { get; set; }
        public int Progress { get; set; }
    }
}