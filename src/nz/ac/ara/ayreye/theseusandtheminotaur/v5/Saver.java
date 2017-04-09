package nz.ac.ara.ayreye.theseusandtheminotaur.v5;

public interface Saver {

	void save(Game game);
	void save(Game game, String fileName);
	void save(Game game, String fileName, String levelName);

}
