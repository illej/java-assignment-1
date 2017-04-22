package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public abstract class Builder {

	protected Loadable gameLoadable;
	protected Map<String, Object> map;
	protected String[] rows;
	
	public Builder(Loadable gameLoadable, Map<String, Object> map) {
		this.gameLoadable = gameLoadable;
		this.map = map;
	}
	
	public void build() {
		setup();
		execute();
	}
	
	protected Point parseCoordinates(String[] value) {
		int x = Integer.parseInt((String) value[0]);
		int y = Integer.parseInt((String) value[1]);
		return new Pointer(x, y);
	}
	
	protected abstract void setup();
	
	protected abstract void execute();
	
}
