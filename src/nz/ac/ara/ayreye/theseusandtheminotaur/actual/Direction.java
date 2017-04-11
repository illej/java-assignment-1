package nz.ac.ara.ayreye.theseusandtheminotaur.actual;

public enum Direction implements Runnable {

	UP(0, -1) {
		public void run() {
			System.out.println( "going up");
		}
	},
	
	RIGHT(1, 0) {
		public void run() {
			System.out.println( "heading right");
		}
	},
	
	DOWN(0, 1) {
		public void run() {
			System.out.println( "down we go");
		}
	},
	
	LEFT(-1, 0) {
		public void run() {
			System.out.println( "turning left");
		}
		
	};

	
	public int xAdjust;
	public int yAdjust;
	
	private Direction(int x, int y) {
		xAdjust = x;
		yAdjust = y;
	}
	
	@Override
	public String toString(){
		// only capitalize the first letter
		String s = super.toString();
		s =  s.substring(0, 1) + s.substring(1).toLowerCase();
		s = s + " " + xAdjust + "," + yAdjust;
		return s;
	}
	
}
