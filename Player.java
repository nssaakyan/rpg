import java.util.Random;

public class Player extends Characters {

    int gold = 0;
    int experience = 0;

    public Player(String name, int force, int agility) {
        super(name, force, agility);
        System.out.println("��� �������� ������. ");
        System.out.println("���: " + this.name + "; ����: " + this.force + "; ��������: " + this.agility + "; ��������: 100");
    }


}

class Skeleton extends Characters {

    public Skeleton(String name, int force, int agility) {
        super(name, force, agility);
        Random r = new Random();
        this.force = r.nextInt(5) + 5;
        this.agility = r.nextInt(5) + 10;
        System.out.println("������ " + name + " ���-�� �����; ����: " + this.force + "; ��������: " + this.agility + ".");
    }

    @Override
    public String nameOfClass() {
        return "������ ";
    }
}

class Goblin extends Characters {

    public Goblin(String name, int force, int agility) {
        super(name, force, agility);
        Random r = new Random();
        this.force = r.nextInt(5) + 10;
        this.agility = r.nextInt(5) + 10;
        System.out.println("������ " + name + " ���-�� �����; ����: " + this.force + "; ��������: " + this.agility + ".");
    }

    @Override
    public String nameOfClass() {
        return "������ ";
    }
}