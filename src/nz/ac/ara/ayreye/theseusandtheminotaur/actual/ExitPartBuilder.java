package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public class ExitPartBuilder extends PartBuilder {

	public ExitPartBuilder(Loadable gameLoadable, Map<String, Object> map) {
		super(gameLoadable, map);
	}

	@Override
	protected String getKey() {
		return "E";
	}
	
	@Override
	protected void execute() {
		super.gameLoadable.addExit(super.point);
	}

}
