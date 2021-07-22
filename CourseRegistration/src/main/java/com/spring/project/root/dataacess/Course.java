package com.spring.project.root.dataacess;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

@Entity(name="course")
@Table(name="course")
@NamedQueries({
    @NamedQuery(name = "course.findAll", query = "SELECT c FROM course c")
    , @NamedQuery(name = "course.findByIdCourse", query = "SELECT c FROM course c WHERE c.idCourse = :idCourse")
    , @NamedQuery(name = "course.findByCourseName", query = "SELECT c FROM course c WHERE c.courseName = :courseName")
    , @NamedQuery(name = "course.findByCredits", query = "SELECT c FROM course c WHERE c.credits = :credits")
    , @NamedQuery(name = "course.findByPrerequisite", query = "SELECT c FROM course c WHERE c.idPrerequisite = :idPrerequisite")
    , @NamedQuery(name = "course.findByClassroom", query = "SELECT c FROM course c WHERE c.idclassrooms = :idclassrooms")
    , @NamedQuery(name = "course.findByDay", query = "SELECT c FROM course c WHERE c.day = :day")
    , @NamedQuery(name = "course.findByStarttime", query = "SELECT c FROM course c WHERE c.startTime = :startTime")
    , @NamedQuery(name = "course.findByEndtime", query = "SELECT c FROM course c WHERE c.endTime = :endTime")})
public class Course implements EntityModel{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idCourse")
    private Integer idCourse;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "credits")
    private String credits;
    
    @Column(name = "idPrerequisite")
    private Integer idPrerequisite;

    @Column(name = "day")
    private String day;

    @Column(name = "startTime")
    private String startTime;

    @Column(name = "endTime")
    private String endTime;
    
    @OneToMany(mappedBy = "idCourse", fetch = FetchType.LAZY)
    private Collection<Course_semester> courseSemesterCollection;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "iddepartment", referencedColumnName = "iddepartment")
    private Department idDepartment;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idclassrooms", referencedColumnName = "idclassrooms")
    private Classrooms idclassrooms;
    
    public Course() {
    }

    public Course(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public Course(Integer idCourse, String courseName, String credits, Classrooms idclassrooms, String day, String startTime, String endTime) {
        this.idCourse = idCourse;
        this.courseName = courseName;
        this.credits = credits;
        this.idclassrooms = idclassrooms;
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Integer idCourse) {
        this.idCourse = idCourse;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCredits() {
        return credits;
    }

    public void setCredits(String credits) {
        this.credits = credits;
    }

    public Integer getIdPrerequisite() {
        return idPrerequisite;
    }

    public void setIdPrerequisite(Integer idPrerequisite) {
        this.idPrerequisite = idPrerequisite;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public Collection<Course_semester> getCourseSemesterCollection() {
        return courseSemesterCollection;
    }

    public void setCourseSemesterCollection(Collection<Course_semester> courseSemesterCollection) {
        this.courseSemesterCollection = courseSemesterCollection;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    public Classrooms getIdClassrooms() {
        return idclassrooms;
    }

    public void setIdClassrooms(Classrooms idclassrooms) {
        this.idclassrooms = idclassrooms;
    }

}
