import java.util.Random;

public abstract class Creature {
    // Constants for directions
    public final static int NORTH = 0;
    public final static int EAST = 1;
    public final static int SOUTH = 2;
    public final static int WEST = 3;
    public final static int NUM_DIRS = 4;
    public final static int[] DIRS = {NORTH, EAST, SOUTH, WEST};

    // Arrays to determine movement in each direction
    protected final int[] dirY = {-1, 0, 1, 0};
    protected final int[] dirX = {0, 1, 0, -1};

    // Constants for point colors
    public final static char LAB_BLACK = 'k';
    public final static char LAB_BLUE = 'b';
    public final static char LAB_RED = 'r';
    public final static char LAB_YELLOW = 'y';
    public final static char LAB_ORANGE = 'o';
    public final static char LAB_PINK = 'p';
    public final static char LAB_MAGENTA = 'm';
    public final static char LAB_CYAN = 'c';
    public final static char LAB_GREEN = 'g';
    public final static char LAB_GRAY = 'e';

    // Attributes for a creature
    private int dir;             // Current direction
    private GridPoint point;     // Current position
    protected char lab;          // Color label
    protected Random rand;       // Random number generator
    protected City city;         // Reference to the city
    protected boolean dead;     // Flag to indicate if the creature is dead
    protected int stepLen;      // Step length

    // Constructor for a creature
    public Creature(int x, int y, City cty, Random rnd) {
        point = new GridPoint(x, y);
        city = cty;
        rand = rnd;
        lab = LAB_GRAY;
        dir = rand.nextInt(NUM_DIRS);
        dead = false;
        stepLen = 1;
    }

    // Getter to check if the creature is dead
    public boolean isDead() {
        return dead;
    }

    // Getters for the current position
    public int getY() {
        return point.y;
    }

    public int getX() {
        return point.x;
    }

    // Get a copy of the current grid point
    public GridPoint getGridPoint() {
        return new GridPoint(point);
    }

    // Get the color label
    public char getLab() {
        return lab;
    }

    // Set the current direction
    public void setDir(int dir) {
        this.dir = dir;
    }

    // Get the current direction
    public int getDir() {
        return dir;
    }

    // Compute the distance to another creature
    public int dist(Creature c) {
        return point.dist(c.getGridPoint());
    }

    // Make a random turn
    public void randomTurn() {
        this.dir = rand.nextInt(4);
    }

    // Method to be overridden by subclasses for movement logic
    public void step() {
        // Implement the step logic for a generic creature
        // This method can be overridden by subclasses to define specific behavior
        int newX = (getX() + dirX[getDir()] * stepLen + city.WIDTH)%city.WIDTH;
        int newY = (getY() + dirY[getDir()] * stepLen + city.HEIGHT)%city.HEIGHT;

        point.x = newX;
        point.y = newY;
    }

    // Method to be overridden by subclasses for action logic
    public abstract void takeAction() ;
        // Implement the takeAction logic for a generic creature
        // This method can be overridden by subclasses to define specific behavior
    

    // Convert the creature's attributes to a string for output
    public String toString() {
        return "" + this.point.x + " " + this.point.y + " " + lab;
    }
}
