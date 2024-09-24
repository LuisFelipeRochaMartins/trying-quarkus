package org.acme.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserEntity extends PanacheEntityBase { //Active Record Pattern

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public String username;

}
