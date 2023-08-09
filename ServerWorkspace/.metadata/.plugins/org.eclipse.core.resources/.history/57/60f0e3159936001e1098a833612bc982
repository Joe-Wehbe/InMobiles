package net.lakis.webapi;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/class")
public class JerseyServlet {

	private static final Map<String, Name> map = new HashMap<>();
	private static int id = 1;

	// Method that sends to the client an instance of fname and lname
	@GET
	@Path("/example")
	@Produces(MediaType.APPLICATION_JSON)
	public Name get() {
		Name name = new Name(String.valueOf(id++), "kamal", "lakis");
		return name;
	}

	// Method that takes id, fname and lname as parameters and returns an instance
	// of the Name
	@GET
	@Path("/return")
	@Produces(MediaType.APPLICATION_JSON)
	public Object getView(@QueryParam("fname") String fname, @QueryParam("lname") String lname) {
		return new Name(String.valueOf(id++), fname, lname);
	}

	/*********************************************************************************************/
	/* 											Added APIs 										 */
	/*********************************************************************************************/

	
	// Method that lists all the names
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Name> listAll() {
		map.put("1", new Name("1","joe", "wwehbe"));
		map.put("2", new Name("2","lol", "wwehbe"));
		map.put("3", new Name("3","hi", "wwehbe"));

		return map;
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
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Name> addName(Name name) {
		name.setId(String.valueOf(id++));
		map.put(name.getId(), name);
		System.out.println("Name added");
		return map;
	}
	
	// Method that updates a name specified by the id
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Name> updateName(Name name) {
		map.put(name.getId(), name);
		System.out.println("Name updated");
		return map;
	}
	
	// Method that deletes a name specified by the id
	@DELETE
	@Path("/delete")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Map<String, Name> deleteName(Name name){
		map.remove(name.getId());
		System.out.println("Name deleted");
		return map;
	}
	

}
