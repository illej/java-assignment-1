package nz.ac.ara.ayreye.theseusandtheminotaur.v5;

public interface Saveable {
	
	int getWidthAcross();
	int getDepthDown();
	Wall whatsAbove(MyPoint where);
	Wall whatsLeft(MyPoint where);
	MyPoint wheresTheseus();
	MyPoint wheresMinotaur();
	MyPoint wheresExit();
	
}
