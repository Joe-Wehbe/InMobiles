package net.lakis.webapi;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/name")
public class NameServlet {

	private static final Map<String, Name> map = new HashMap<>();
	private static int id = 1;

	// Method that lists all the names
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Name> listAll() {
		return map.values();
	}

	// Method that returns the name of a specific user by id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Name searchByID(@PathParam("id") String userID) {
		return map.get(userID);
	}

	// Method that adds names
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addName(Name name) {
		name.setId(String.valueOf(id++));
		map.put(name.getId(), name);
		System.out.println("Name added");
		return "Name added";
	}

	// Method that updates a name specified by the id
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateName(Name name) {
		if (!map.containsKey(name.getId())) {
			return "Unknown Id: " + name.getId();
		}
		map.put(name.getId(), name);
		System.out.println("Name updated");
		return "Name updated";
	}

	// Method that deletes a name specified by the id
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteName(@PathParam("id") String id) {
		if (!map.containsKey(id)) {
			return "Unknown Id: " + id;
		}
		map.remove(id);
		System.out.println("Name deleted");
		return "Name deleted";
	}

}
