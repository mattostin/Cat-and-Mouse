import java.util.*;

public class City {
    public static final int WIDTH = 80;
    public static final int HEIGHT = 80;

    public List<Creature> creatures;
    public Queue<Creature> creaturesToAdd;
    private Random rand;
    private int roundCount;

    public City(Random rand, int numMice, int numCats, int numZombieCats) {
        this.rand = rand;
        this.roundCount = 0;
        this.creatures = new LinkedList<Creature>();
        this.creaturesToAdd = new LinkedList<Creature>();

        for (int i = 0; i < numMice; i++) addMouse();
        for (int i = 0; i < numCats; i++) addCat();
        addNewCreatures();
    }

    public int numCreatures() {
        return creatures.size();
    }

    public void addMouse() {
        creaturesToAdd.add(new Mouse(rand.nextInt(HEIGHT), rand.nextInt(WIDTH), this, rand));
    }

    public void addCat() {
        creaturesToAdd.add(new Cat(rand.nextInt(HEIGHT), rand.nextInt(WIDTH), this, rand));
    }

    public void addNewCreatures() {
        while (!creaturesToAdd.isEmpty()) {
            creatures.add(creaturesToAdd.remove());
        }
    }

    public boolean isValidPosition(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public void simulate() {
        roundCount++;

        for (Creature c : creatures) {
            c.step();
        }

        for (Creature c : creatures) {
            c.takeAction();
        }

        LinkedList<Creature> deadCreatures = new LinkedList<Creature>();
        for (Creature c : creatures) {
            if (c.isDead()) {
                deadCreatures.add(c);
            }
        }

        for (Creature c : deadCreatures) {
            creatures.remove(c);
        }

        addNewCreatures();

        for (Creature c : creatures) {
            System.out.println(c);
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int numMice = 20;
        int numCats = 10;
        int numZombieCats = 5;

        City city = new City(rand, numMice, numCats, numZombieCats);

        int numRounds = 1000;
        for (int round = 0; round < numRounds; round++) {
            city.simulate();
        }
    }
}
