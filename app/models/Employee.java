package models;

import java.util.List;

import javax.persistence.*;

import io.ebean.*;

@Entity
public class Employee extends Model {

    public static final Finder<Long, Employee> find = new Finder<Long, Employee>(Employee.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "boolean default false")
    private boolean isController;

    @ManyToOne(optional = false)
    @JoinColumn(insertable = false, updatable = false)
    private Company company;
   
    @Column(nullable = false)
    private String pw;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Task> tasks;

    public Employee(String lastName, String name, Company company, String pw) {
        super();
        this.lastName = lastName;
        this.name = name;
        this.company = company;
        this.pw = pw;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }
    
    public String getLastName() {
        return this.lastName;
    }

    public String getName() {
        return this.name;
    }

    public int getCompanyId() {
        return Math.toIntExact(this.company.id);
    }
}
