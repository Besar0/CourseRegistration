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

@Entity(name="course_semester")
@Table(name="course_semester")
@NamedQueries({
    @NamedQuery(name = "course_semester.findAll", query = "SELECT c FROM course_semester c")
    , @NamedQuery(name = "course_semester.findByIdCourseSemester", query = "SELECT c FROM course_semester c WHERE c.idCourseSemester = :idCourseSemester")
    , @NamedQuery(name = "course_semester.findByStudentsRegistered", query = "SELECT c FROM course_semester c WHERE c.studentsRegistered = :studentsRegistered")
    , @NamedQuery(name = "course_semester.findByAvailableSeats", query = "SELECT c FROM course_semester c WHERE c.availableSeats = :availableSeats")
    , @NamedQuery(name = "course_semester.findByCurrentAndAvailable", query = "SELECT c FROM course_semester c WHERE c.idSemester = :idSemester AND c.availability = 1")
    , @NamedQuery(name = "course_semester.findByAvailability", query = "SELECT c FROM course_semester c WHERE c.availability = :availability")})
public class Course_semester implements EntityModel{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idCourseSemester")
    private Integer idCourseSemester;

    @Column(name = "studentsRegistered")
    private int studentsRegistered;

    @Column(name = "availableSeats")
    private int availableSeats;

    @Column(name = "availability")
    private short availability;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCourse", referencedColumnName = "idCourse")
    private Course idCourse;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idSemester", referencedColumnName = "idSemester")
    private Semester idSemester;
    
    @OneToMany(mappedBy = "idCourseSemester", fetch = FetchType.EAGER)
    private Collection<Registration> registrationCollection;

    public Course_semester() {
    }

    public Course_semester(Integer idCourseSemester) {
        this.idCourseSemester = idCourseSemester;
    }

    public Course_semester(Integer idCourseSemester, int studentsRegistered, int availableSeats, short availability) {
        this.idCourseSemester = idCourseSemester;
        this.studentsRegistered = studentsRegistered;
        this.availableSeats = availableSeats;
        this.availability = availability;
    }

    public Integer getIdCourseSemester() {
        return idCourseSemester;
    }

    public void setIdCourseSemester(Integer idCourseSemester) {
        this.idCourseSemester = idCourseSemester;
    }

    public int getStudentsRegistered() {
        return studentsRegistered;
    }

    public void setStudentsRegistered(int studentsRegistered) {
        this.studentsRegistered = studentsRegistered;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public short getAvailability() {
        return availability;
    }

    public void setAvailability(short availability) {
        this.availability = availability;
    }
    
    public Course getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Course idCourse) {
        this.idCourse = idCourse;
    }

    public Semester getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Semester idSemester) {
        this.idSemester = idSemester;
    }

    public Collection<Registration> getRegistrationCollection() {
        return registrationCollection;
    }

    public void setRegistrationCollection(Collection<Registration> registrationCollection) {
        this.registrationCollection = registrationCollection;
    }

}
