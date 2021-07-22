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

@Entity(name = "user")
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "user.findAll", query = "SELECT u FROM user u")
    , @NamedQuery(name = "user.findByIduser", query = "SELECT u FROM user u WHERE u.idUser = :idUser")
    , @NamedQuery(name = "user.findStudentByMajorId", query = "SELECT u FROM user u WHERE u.idDepartment = :idDepartment "
    		+ "AND u.role = 'ROLE_STUDENT'")
    , @NamedQuery(name = "user.findByName", query = "SELECT u FROM user u WHERE u.name = :name")
    , @NamedQuery(name = "user.findBySurname", query = "SELECT u FROM user u WHERE u.surname = :surname")
    , @NamedQuery(name = "user.findByEmail", query = "SELECT u FROM user u WHERE u.email = :email")
    , @NamedQuery(name = "user.findByPassword", query = "SELECT u FROM user u WHERE u.password = :password")})
public class User implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser")
    private Integer idUser;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "surname")
    private String surname;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private String role;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idDepartment", referencedColumnName = "idDepartment")
    private Department idDepartment;
    
    @OneToMany(mappedBy = "idUser",fetch = FetchType.LAZY)	
    private Collection<Registration> registrationCollection;

    public User() {
    }

    public User(Integer idUser, String name, String surname, String email, String password, String role) {
        this.idUser = idUser;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    
    public boolean match(final String email, final String password) {
		return this.email.equals(email) && this.password.equals(password);
	}

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Department getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Department idDepartment) {
        this.idDepartment = idDepartment;
    }
    
    public Collection<Registration> getRegistrationCollection() {
        return registrationCollection;
    }

    public void setRegistrationCollection(Collection<Registration> registrationCollection) {
        this.registrationCollection = registrationCollection;
    }
	
}
