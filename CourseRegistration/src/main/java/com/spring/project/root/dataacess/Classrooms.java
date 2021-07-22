package com.spring.project.root.dataacess;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.spring.project.root.repository.EntityModel;

@Entity(name = "classrooms")
@Table(name = "classrooms")
@NamedQueries({
    @NamedQuery(name = "classrooms.findAll", query = "SELECT c FROM classrooms c")
    , @NamedQuery(name = "classrooms.findByIdClassroom", query = "SELECT c FROM classrooms c WHERE c.idClassrooms = :idClassrooms")
    })
public class Classrooms implements EntityModel{
	
	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idClassrooms")
    private Integer idClassrooms;
    
    @Column(name = "name")
    private String name;
    
    public Classrooms() {
    }
    
    public Classrooms(Integer idClassrooms) {
    	this.idClassrooms = idClassrooms;
    }

    public Classrooms(Integer idClassrooms, String name) {
        this.idClassrooms = idClassrooms;
        this.name = name;
    }
    
    public Integer getIdClassrooms() {
        return idClassrooms;
    }

    public void setIdClassrooms(Integer idClassrooms) {
        this.idClassrooms = idClassrooms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	
}
