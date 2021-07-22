package com.spring.project.root.dataacess;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

@Entity(name="semester")
@Table(name="semester")
@NamedQueries({
    @NamedQuery(name = "semester.findAll", query = "SELECT s FROM semester s")
    , @NamedQuery(name = "semester.findByIdSemester", query = "SELECT s FROM semester s WHERE s.idSemester = :idSemester")
    , @NamedQuery(name = "semester.findBySemesterName", query = "SELECT s FROM semester s WHERE s.semesterName = :semesterName")
    , @NamedQuery(name = "semester.findByCurrent", query = "SELECT s FROM semester s WHERE s.current = 1")
    , @NamedQuery(name = "semester.findByYear", query = "SELECT s FROM semester s WHERE s.year = :year")})
public class Semester implements EntityModel{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idSemester")
    private Integer idSemester;

    @Column(name = "semesterName")
    private String semesterName;

    @Column(name = "year")
    private int year;
    
    @Column(name = "current")
    private short current;
    
    @OneToMany(mappedBy = "idSemester", fetch = FetchType.EAGER)
    private Collection<Course_semester> courseSemesterCollection;

    public Semester() {
    }

    public Semester(Integer idSemester) {
        this.idSemester = idSemester;
    }

    public Semester(Integer idSemester, String semesterName, int year) {
        this.idSemester = idSemester;
        this.semesterName = semesterName;
        this.year = year;
    }

    public Integer getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Integer idSemester) {
        this.idSemester = idSemester;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
    public short getCurrent() {
        return current;
    }

    public void setCurrent(short current) {
        this.current = current;
    }
    
    public Collection<Course_semester> getCourseSemesterCollection() {
        return courseSemesterCollection;
    }

    public void setCourseSemesterCollection(Collection<Course_semester> courseSemesterCollection) {
        this.courseSemesterCollection = courseSemesterCollection;
    }
}
