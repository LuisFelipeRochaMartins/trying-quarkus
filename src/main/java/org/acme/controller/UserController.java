package org.acme.controller;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.entity.UserEntity;
import org.acme.service.UserService;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@Path("/{id}")
	@GET
	public Response findById(@PathParam("id") Long id) {
		var users = service.findById(id);

		return Response.ok(users).build();
	}

	@GET
	public Response findAll(@QueryParam("page") @DefaultValue("0") Integer page,
	                        @QueryParam("pageSize") @DefaultValue("10") Integer pageSize) {
		var users = service.findAll(page, pageSize);

		return Response.ok(users).build();
	}

	@POST
	@Transactional
	public Response createUser(UserEntity userEntity) {
		var user = service.createUser(userEntity);
		return Response.ok(user).build();
	}

	@Path("/{id}")
	@PUT
	@Transactional
	public Response updateUser(@PathParam("id") Long id, UserEntity userEntity) {
		var user = service.udpateUser(id, userEntity);

		return Response.ok(user).build();
	}

	@DELETE
	@Path("/{id}")
	public Response deletebyId(@PathParam("id") Long id) {
		service.deleteById(id);
		return Response.noContent().build();
	}
}
