package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.HashMap;
import java.util.Map;

public class Cell {

	private Map<String, Object> container = new HashMap<String, Object>();
	
	public Cell() {
		this.container.put("top", Wall.NOTHING);
		this.container.put("left", Wall.NOTHING);
		this.container.put("character", Part.NONE);
		this.container.put("objective", Part.NONE);
	}
	
	public void set(String key, Object object) {
		this.container.put(key, object);
	}
	
	public Object get(String key) {
		return this.container.get(key);
	}
}


