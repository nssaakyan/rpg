public class Battle extends Thread {

    static int goldAdd, experienceAdd;

    public static void battle(Characters monster, Player player) throws InterruptedException {
        while (player.life >= 0 && monster.life >= 0) {
            Thread.sleep(500);
            player.life -= monster.attack(monster.agility);
            if (player.life > 0) {
                System.out.println("У " + player.name + " здоровья осталось " + player.life + ".");
            } else {
                System.out.println(player.name + " Убит!. Игра закончена.");
                System.exit(0);
            }
            Thread.sleep(500);
            monster.life -= player.attack(player.agility);
            if (monster.life > 0) {
                System.out.println("У " + monster.nameOfClass() + monster.name +
                        " здоровья осталось " + monster.life + ".");
            } else {
                System.out.println(monster.nameOfClass() + monster.name + " Убит!");
                if (monster.nameOfClass().equals("Гоблин ")) {
                    goldAdd = 50;
                    experienceAdd = 10;
                } else {
                    goldAdd = 30;
                    experienceAdd = 5;
                }
                player.gold += goldAdd;
                player.experience += experienceAdd;
                System.out.println("Получено золото: " + goldAdd + ", получен опыт: " + experienceAdd +
                        ".");
                System.out.println("Золото: " + player.gold + ", опыт: " + player.experience +
                        ", здоровье: " + player.life + ".");
                break;
            }
        }
    }

    @Override
    public void run() {
        super.run();
    }
}
