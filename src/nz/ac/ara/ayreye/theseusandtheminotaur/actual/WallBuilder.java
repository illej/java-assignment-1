package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public abstract class WallBuilder extends Builder {

	protected Point point;
	protected int depth;
	protected int width;
	
	public WallBuilder(Loadable gameLoadable, Map<String, Object> map) {
		super(gameLoadable, map);
	}

	@Override
	protected void setup() {
		String key = this.getKey(); // HOOK
		super.rows = (String[]) map.get(key);
		this.depth = rows.length;
		this.width = rows[0].length();
		super.gameLoadable.setDepthDown(this.depth);
		super.gameLoadable.setWidthAcross(this.width);
	}
	
	protected abstract String getKey();

	@Override
	protected void execute() {
		for (int i = 0; i < this.depth; i++) {
			String row = super.rows[i];
			for (int j = 0; j < this.width; j++) {
				if (row.charAt(j) == 'x') {
					/* HOOK */
					this.point = new Pointer(j, i);
					this.addHook();
				}
			}
		}
	}

	protected abstract void addHook();

}
