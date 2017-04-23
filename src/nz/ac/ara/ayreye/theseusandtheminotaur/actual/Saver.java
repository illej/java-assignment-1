package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public interface Saver {
	void save(Saveable saveable);
	void save(Saveable saveable, String fileName);
	void save(Saveable saveable, String fileName, String levelName);
}
