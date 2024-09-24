package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.entity.UserEntity;
import org.acme.errors.UserNotFoundException;

import java.util.List;

@ApplicationScoped
public class UserService {

	public UserEntity createUser(UserEntity user) {
		UserEntity.persist(user);
		return user;
	}

	public List<UserEntity> findAll(Integer page, Integer pageSize) {
		return UserEntity
				.findAll()
				.page(page, pageSize)
				.list();
	}

	public UserEntity findById(Long id) {
		return (UserEntity) UserEntity
				.findByIdOptional(id)
				.orElseThrow(UserNotFoundException::new);
	}

	public UserEntity udpateUser(Long id, UserEntity userEntity) {
		var user = findById(id);

		user.username = userEntity.username;
		UserEntity.persist(user);

		return user;
	}


	public void deleteById(Long id) {
		var user = findById(id);
		UserEntity.deleteById(user.id);
	}
}
