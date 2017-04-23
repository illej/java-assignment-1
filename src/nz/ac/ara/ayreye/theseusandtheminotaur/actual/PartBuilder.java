package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public abstract class PartBuilder extends Builder {
	
	public PartBuilder(Loadable gameLoadable, Map<String, Object> map) {
		super(gameLoadable, map);
	}

	@Override
	protected void setup() {
		String key = this.getKey();
		String[] value = (String[]) super.map.get(key);
		super.point = super.parseCoordinates(value);
	}

}
