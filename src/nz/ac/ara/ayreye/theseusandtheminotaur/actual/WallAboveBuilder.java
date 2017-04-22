package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public class WallAboveBuilder extends WallBuilder {

	public WallAboveBuilder(Loadable gameLoadable, Map<String, Object> map) {
		super(gameLoadable, map);
	}

	@Override
	protected String getKey() {
		return "U";
	}
	
	@Override
	protected void addHook() {
		super.gameLoadable.addWallAbove(super.point);
	}

}
