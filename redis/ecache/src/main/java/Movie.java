
import lombok.Data;

import java.io.Serializable;

@Data
public class Movie implements Serializable {

	int id;
	String name;
	String directory;

	//getters and setters
	//constructor with fields
	//toString()
}