import java.util.Random;

public class Mouse extends Creature {
    private int rounds; // Round counter

    public Mouse(int x, int y, City cty, Random rand) {
        super(x, y, cty, rand);
        lab = LAB_BLUE;
        rounds = 0;
    }

    @Override
    public void step() {
        if (rounds == 22) {
            // After 22 rounds, a mouse dies
            dead = true;
            return;
        }

        // Mouse randomly changes direction 20% of the time
        if (rand.nextInt(10) < 2) {
            randomTurn();
        }

        super.step();

        // Update rounds after stepping


        rounds++;

    }

    @Override
    public void takeAction() {
        if (rounds == 20) {
            Mouse mouse = new Mouse(getX(), getY(), city, rand);
            // After 20 rounds, create a new baby mouse
            // mouse.lab = LAB_BLACK;
            city.creaturesToAdd.add(mouse);
        }
    }
}
