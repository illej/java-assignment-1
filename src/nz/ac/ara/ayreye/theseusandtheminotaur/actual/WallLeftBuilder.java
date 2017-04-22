package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public class WallLeftBuilder extends WallBuilder {

	public WallLeftBuilder(Loadable gameLoadable, Map<String, Object> map) {
		super(gameLoadable, map);
	}

	@Override
	protected String getKey() {
		return "L";
	}

	@Override
	public void addHook() {
		super.gameLoadable.addWallLeft(super.point);
	}

}
