import java.util.Random;

public class Cat extends Creature {
    private int lastMeal; // Counter to track the number of moves since the last meal
    private int starve;   // Threshold for starving

    // Constructor for a cat
    public Cat(int x, int y, City cty, Random rnd) {
        super(x, y, cty, rnd);
        lab = LAB_YELLOW;
        lastMeal = 0;
        starve = 50; // Set the threshold for starving to 50 moves
    }

    @Override
    public void step() {
        if (lastMeal >= starve) {
            // If the cat hasn't eaten a mouse in 50 moves, it dies
            dead = true;
            return;
        }

        lastMeal++;

        if (rand.nextInt(100) < 5) {
            randomTurn();
        }

        // If the cat is chasing a mouse, move two spaces at a time
        // if (chasingMouse) {
        super.step();
        super.step();
    }

    @Override
    public void takeAction() {
        // Check if the cat is chasing a mouse
            Creature nearestMouse = findNearestMouse();

            if (nearestMouse != null) {
                int x = getX() - nearestMouse.getX();
                int y = getY() - nearestMouse.getY() ;

                if(x == 0 && y ==0){
                    nearestMouse.dead = true;
                    lastMeal = 0;
                } else {
                if(Math.abs(x) < Math.abs(y)) {
                    if (y > 0) {
                        setDir(NORTH);
                    } else {
                        setDir(SOUTH);
                    }
                } else {
                    if (x > 0) {
                        setDir(WEST);
                    } else {
                        setDir(EAST);
                    }
                }
                lab = LAB_CYAN;

            }
            } else {
                // If no mouse is found, stop chasing
                lab = LAB_YELLOW;
            }
        }

    // Find the nearest mouse
    private Creature findNearestMouse() {
        Creature nearestMouse = null;
        int nearestDistance = 20;

        for (Creature creature : city.creatures) {
            if(creature.dead){
                continue;
            }

            if (creature instanceof Mouse) {
                int distance = dist(creature);
                if (distance < nearestDistance) {
                    nearestMouse = creature;
                }
            }
        }

        return nearestMouse;
    }
}
