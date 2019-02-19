package models;

public class EmployeeTaskDTO {
    
    public long id;
    public String taskName;
    public long project_id;
    
    public EmployeeTaskDTO (long id, String taskName, long project_id) {
        this.id = id;
        this.taskName = taskName;
        this.project_id = project_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }
    
    
}