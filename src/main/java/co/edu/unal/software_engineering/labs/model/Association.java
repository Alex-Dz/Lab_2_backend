package co.edu.unal.software_engineering.labs.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the association database table.
 *
 * @author Sebastián Moreno
 */
@Entity
@Table(name = "association", schema = "public")
public class Association implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Attributes
     */

    @Id
    /*@SequenceGenerator(name = "ASSOCIATION_ID_GENERATOR",
            sequenceName = "public.association_association_id_seq", allocationSize = 1)*/
    @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "ASSOCIATION_ID_GENERATOR"*/)
    @Column(name = "association_id")
    private Integer id;

    //bi-directional many-to-one association to Course
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    //bi-directional many-to-one association to Period
    @ManyToOne
    @JoinColumn(name = "period_id")
    private Period period;

    //bi-directional many-to-one association to UserRole
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserRole userRole;

    //bi-directional many-to-one association to Grade
    @OneToOne(mappedBy = "association")
    private Grade grade;

    /**
     * Constructors
     */

    public Association() {
        userRole = new UserRole();
    }

    public Association(User user, Role role, Course course, Period period) {
        userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(role);
        this.course = course;
        this.period = period;
    }

    /**
     * Getters and Setters
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public User getUser() {
        return userRole.getUser();
    }

    public void setUser(User user) {
        this.userRole.setUser(user);
    }

    public Role getRole() {
        return userRole.getRole();
    }

    public void setRole(Role role) {
        this.userRole.setRole(role);
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    UserRole getUserRole() {
        return userRole;
    }

    void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * Methods
     */

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Association)) return false;
        return id.equals(((Association) object).getId());
    }

    @Override
    public int hashCode() {
        return id;
    }

}