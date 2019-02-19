package models;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import io.ebean.Finder;
import io.ebean.Model;

@Entity
public class Task extends Model {

    public static final Finder<Long, Task> find = new Finder<Long, Task>(Task.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(columnDefinition = "double default 0.00")
    private double timeSpent;

    @Column(nullable = false)
    private int calendarWeek;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int year;

    private String notes;

    @ManyToOne(optional = false)
    @JoinColumn(insertable = false, updatable = false)
    private Project project;

    @ManyToOne
    private Employee employee;

    public Task(double timeSpent, Date workingDay, Project project, Employee employee, String notes) {
        super();
        this.timeSpent = timeSpent;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workingDay);
        this.calendarWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        this.day = calendar.get(Calendar.DATE);
        this.month = calendar.get(Calendar.MONTH);
        this.year = calendar.get(Calendar.YEAR);
        this.project = project;
        this.employee = employee;
        this.notes = notes;
    }

    public int getCalendarWeek() {
        return calendarWeek;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public Project getProject() {
        return project;
    }

    public Employee getEmployee() {
        return employee;
    }
    
}