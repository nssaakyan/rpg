import java.util.Random;

public class Characters {

    String name;
    int life;
    int force;
    int agility;

    public Characters(String name, int force, int agility) {
        this.name = name;
        this.force = force;
        this.agility = agility;
        this.life = 100;
    }

    public int attack(int agility) {
        Random r = new Random();
        int maxPower = r.nextInt(10);
        final int MAX_POWER = 8;
        int randAgility = r.nextInt(100);
        if (agility * 3 > randAgility) {
            if (maxPower == MAX_POWER) {
                System.out.println("Получена двойная сила удара!");
                System.out.println(name + " атакует с силой " + force * 2);
                return force * 2;
            }
            System.out.println(name + " атакует с силой " + force);
            return force;
        } else System.out.println(name + " промахнулся.");
        return 0;
    }

    public String nameOfClass() {
        return null;
    }
}

