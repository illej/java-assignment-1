package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public interface Saveable {
	int getWidthAcross();
	int getDepthDown();
	Wall whatsAbove(MyPoint where);
	Wall whatsLeft(MyPoint where);
	MyPoint wheresTheseus();
	MyPoint wheresMinotaur();
	MyPoint wheresExit();
}
