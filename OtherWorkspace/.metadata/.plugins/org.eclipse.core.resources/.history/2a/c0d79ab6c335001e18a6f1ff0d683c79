/*
This Java code defines a class named JsonProvider which is used as a provider 
for configuring JSON serialization and deserialization using the Jackson library 
within a Java web application. It's specifically designed to work with JAX-RS 
(Java API for RESTful Web Services) to handle JSON processing.
*/
package net.lakis.webapi;

/*
 These are import statements that bring in various classes from the JAX-RS 
 and Jackson libraries. These classes are necessary for setting up and configuring 
 JSON processing.
*/
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/*
  The JsonProvider class is declared as a provider by annotating it with 
  @Provider. This annotation indicates that this class provides some functionality 
  to the JAX-RS runtime. It also implements the ContextResolver interface with a 
  type parameter of ObjectMapper. This interface is used to provide a custom 
  context (in this case, an ObjectMapper) to JAX-RS for handling JSON 
  serialization and deserialization. 
*/
@Provider
public class JsonProvider implements ContextResolver<ObjectMapper> {
	
	/*
	 This line declares a private static field named MAPPER of type ObjectMapper. 
	 An ObjectMapper is a core class in the Jackson library that is used for JSON 
	 serialization and deserialization. It's initialized as a singleton instance 
	 to be shared across the application. 
	*/
	private static final ObjectMapper MAPPER = new ObjectMapper();
		
/*
	static {
		MAPPER.setSerializationInclusion(Include.NON_EMPTY);
        MAPPER.enable(MapperFeature..)
		MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
        MAPPER.enable(MapperFeature.us)
		MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

		MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
		MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

		// MAPPER.disable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		MAPPER.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
		MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	}
*/		
	
/*
 This method is required by the ContextResolver interface. It returns the shared 
 ObjectMapper instance (MAPPER) when called. This instance will be used by JAX-RS 
 to perform JSON processing.
*/
	public ObjectMapper getContext(Class<?> type) {
		return MAPPER;
	}

	/*
	This static block initializes the shared ObjectMapper instance (MAPPER) with 
	various configuration settings.
	*/
	static {
		
		// Specifies that properties with null or empty values should be excluded from serialization.
		MAPPER.setSerializationInclusion(Include.NON_EMPTY);
				
/*
		if (configHandler.getConfig().getServletsConfig().isIdentJson())
			MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
		else
*/				
		
		// Disables pretty-printing of JSON output.
		MAPPER.disable(SerializationFeature.INDENT_OUTPUT);

		// Prevents getters from being treated as setters during deserialization.
		MAPPER.disable(MapperFeature.USE_GETTERS_AS_SETTERS);

		/* Configures the visibility of properties during serialization and 
		 deserialization. In this case, all methods are hidden (none are auto-detected), 
		 but fields are made visible.	 
		*/
		MAPPER.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
		MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

		// Allows reading unknown enum values as null during deserialization.
		MAPPER.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
		
		// Prevents failure on encountering unknown properties during deserialization.
		MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

	}

}