package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public interface Saver {
	void save(Saveable gameSaver);
	void save(Saveable gameSaver, String fileName);
	void save(Saveable gameSaver, String fileName, String levelName);
}
