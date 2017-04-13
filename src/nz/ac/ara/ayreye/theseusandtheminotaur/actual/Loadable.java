package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public interface Loadable {
	int setWidthAcross(int widthAcross);
	int setDepthDown(int depthDown);
	void addWallAbove(MyPoint where);
	void addWallLeft(MyPoint where);
	void addTheseus(MyPoint where) /*throws Exception*/;
	void addMinotaur(MyPoint where);
	void addExit(MyPoint where);
}
