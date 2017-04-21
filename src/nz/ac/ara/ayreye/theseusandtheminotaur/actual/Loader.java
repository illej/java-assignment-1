package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

import java.util.Map;

public interface Loader {
	//Map<String, String[]> loadFile(String filename);
	public void load(Loadable gameLoader, String filename);
}
