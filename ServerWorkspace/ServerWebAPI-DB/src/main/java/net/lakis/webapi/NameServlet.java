package net.lakis.webapi;

import java.sql.SQLException;
import java.util.ArrayList;

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

	private static int id = 1;
	ConnectionDB connection = new ConnectionDB();

	// Method that lists all the names
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Object[] listAll() {
		ArrayList<Name> names = new ArrayList<Name>();
		try {
			names = connection.listAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Object[] namesArray = names.toArray();
		return namesArray;
	}

	// Method that returns the name of a specific user by id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Name searchByID(@PathParam("id") String userID) {
		Name name = null;
		try {
			 name = connection.searchNameById(userID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}

	// Method that adds names
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String addName(Name name) {
		try {
			connection.addName(name.getFname(), name.getLname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Name added";
	}

	// Method that updates a name specified by the id
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateName(Name name) {
		try {
			connection.updateName(name.getId(), name.getFname(), name.getLname());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Name updated";
	}

	// Method that deletes a name specified by the id
	@DELETE
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteName(@PathParam("id") String id) {
		try {
			connection.deleteName(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "Name deleted";
	}

}
