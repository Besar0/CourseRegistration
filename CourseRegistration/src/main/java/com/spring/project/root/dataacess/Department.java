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

@Entity(name="department")
@Table(name="department")
@NamedQueries({
    @NamedQuery(name = "department.findAll", query = "SELECT d FROM department d")
    , @NamedQuery(name = "department.findByIdDepartment", query = "SELECT d FROM department d WHERE d.idDepartment = :idDepartment")
    , @NamedQuery(name = "department.findByName", query = "SELECT d FROM department d WHERE d.name = :name")})
public class Department implements EntityModel{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idDepartment")
    private Integer idDepartment;

    @Column(name = "name")
    private String name;
    
    @OneToMany(mappedBy = "idDepartment", fetch = FetchType.LAZY)
    private Collection<User> userCollection;
    
    @OneToMany(mappedBy = "idDepartment", fetch = FetchType.EAGER)
    private Collection<Course> courseCollection;

    public Department() {
    }

    public Department(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public Department(Integer idDepartment, String name) {
        this.idDepartment = idDepartment;
        this.name = name;
    }

    public Integer getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    public Collection<Course> getCourseCollection() {
        return courseCollection;
    }

    public void setCourseCollection(Collection<Course> courseCollection) {
        this.courseCollection = courseCollection;
    }
}
