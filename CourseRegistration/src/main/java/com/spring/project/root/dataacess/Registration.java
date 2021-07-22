package com.spring.project.root.dataacess;

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
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

@Entity(name="registration")
@Table(name="registration")
@NamedQueries({
    @NamedQuery(name = "registration.findAll", query = "SELECT r FROM registration r")
    , @NamedQuery(name = "registration.findByUserId", query = "SELECT r FROM registration r WHERE r.idUser = :idUser")
    , @NamedQuery(name = "registration.findByUserIdUnapproved", query = "SELECT r FROM registration r WHERE r.idUser = :idUser AND r.approved = 0")
    , @NamedQuery(name = "registration.findByUserIdApproved", query = "SELECT r FROM registration r WHERE r.idUser = :idUser AND r.approved = 1")
    , @NamedQuery(name = "registration.findByIdRegistration", query = "SELECT r FROM registration r WHERE r.idRegistration = :idRegistration")
    , @NamedQuery(name = "registration.findByApproved", query = "SELECT r FROM registration r WHERE r.approved = :approved")})
public class Registration implements EntityModel{
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idRegistration")
    private Integer idRegistration;

    @Column(name = "approved")
    private short approved;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCourSesemester", referencedColumnName = "idCourseSemester")
    private Course_semester idCourseSemester;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUser", referencedColumnName = "idUser")
    private User idUser;

    public Registration() {
    }

    public Registration(Integer idRegistration) {
        this.idRegistration = idRegistration;
    }

    public Registration(Integer idRegistration, short approved) {
        this.idRegistration = idRegistration;
        this.approved = approved;
    }

    public Integer getIdRegistration() {
        return idRegistration;
    }

    public void setIdRegistration(Integer idRegistration) {
        this.idRegistration = idRegistration;
    }

    public short getApproved() {
        return approved;
    }

    public void setApproved(short approved) {
        this.approved = approved;
    }
    
    public Course_semester getIdCourseSemester() {
        return idCourseSemester;
    }

    public void setIdCourseSemester(Course_semester idCourseSemester) {
        this.idCourseSemester = idCourseSemester;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdStudent(User idUser) {
        this.idUser = idUser;
    }

}
