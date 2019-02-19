package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import io.ebean.Finder;
import io.ebean.Model;

@Entity
public class Project extends Model {

    public static final Finder<Long, Project> find = new Finder<Long, Project>(Project.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    private String description;

    @Column(columnDefinition = "double default 0.00")
    private double estimatedWorkingHours;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Project(String description) {
        super();
        this.description = description;
    }

    public double getCurrentWorkingHours() {
        double currentWorkingHours = 0.00;
        for (Task task : tasks) {
            currentWorkingHours += task.getTimeSpent();
        }
        return currentWorkingHours;
    }

    public void setEstimatedWorkingHours(double estimatedWorkingHours) {
        this.estimatedWorkingHours = estimatedWorkingHours;
    }

    public int getProjectDurationInDays() {
        List<Date> dates = new ArrayList<>();
        for (Task task : tasks) {
            dates.add(new Date(task.getYear(), task.getMonth(), task.getDay()));
        }
        if (dates.size() == 0) {
            return 0;
        }
        Date minDate = Collections.min(dates);
        Date maxDate = Collections.max(dates);
        return Math.toIntExact(TimeUnit.DAYS.convert((maxDate.getTime() - minDate.getTime()), TimeUnit.MILLISECONDS))
                + 1; //+1 to include the start date
    }

    public String getName() {
        return this.description;
    }
}
