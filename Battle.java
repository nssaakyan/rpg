public class Battle extends Thread {

    static int goldAdd, experienceAdd;

    public static void battle(Characters monster, Player player) throws InterruptedException {
        while (player.life >= 0 && monster.life >= 0) {
            Thread.sleep(500);
            player.life -= monster.attack(monster.agility);
            if (player.life > 0) {
                System.out.println("� " + player.name + " �������� �������� " + player.life + ".");
            } else {
                System.out.println(player.name + " ����!. ���� ���������.");
                System.exit(0);
            }
            Thread.sleep(500);
            monster.life -= player.attack(player.agility);
            if (monster.life > 0) {
                System.out.println("� " + monster.nameOfClass() + monster.name +
                        " �������� �������� " + monster.life + ".");
            } else {
                System.out.println(monster.nameOfClass() + monster.name + " ����!");
                if (monster.nameOfClass().equals("������ ")) {
                    goldAdd = 50;
                    experienceAdd = 10;
                } else {
                    goldAdd = 30;
                    experienceAdd = 5;
                }
                player.gold += goldAdd;
                player.experience += experienceAdd;
                System.out.println("�������� ������: " + goldAdd + ", ������� ����: " + experienceAdd +
                        ".");
                System.out.println("������: " + player.gold + ", ����: " + player.experience +
                        ", ��������: " + player.life + ".");
                break;
            }
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
