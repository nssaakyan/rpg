import java.util.Random;
import java.util.Scanner;

public class Game {

    public static void main(String[] args) throws InterruptedException {

        int force = 15;
        int agility = 15;
        int forceAdd;
        int agilityAdd;
        int countGoblin = 0;
        int countSkelleton = 0;
        int level = 1;
        String forceAddStr;


        // Создаём персонажа
        Scanner scanner = new Scanner(System.in);
        System.out.println("Начинаем игру.");
        System.out.println("Создание персонажа...");
        System.out.println("Введите имя");
        String name = scanner.nextLine().toUpperCase();
        System.out.println("Начальная сила: 15, начальная ловкость: 15");
        System.out.println("Распределите 15 дополнительных очков между силой и ловкостью персонажа");
        while (true) {
            System.out.println("Добавить к силе(0...15)");
            forceAddStr = scanner.nextLine();
            if (isNumeric(forceAddStr)) {
                forceAdd = Integer.parseInt(forceAddStr);
                if (forceAdd >= 0 && forceAdd <= 15) {
                    System.out.println("Добавилось к силе: " + forceAdd);
                    agilityAdd = 15 - forceAdd;
                    Thread.sleep(200);
                    System.out.println("Добавилось к ловкости: " + agilityAdd);
                    Thread.sleep(200);
                    break;
                } else {
                    System.out.println("Неправильный ввод. Введите число от 0 до 15");
                }
            } else {
                System.out.println("Неправильный ввод. Введите число от 0 до 15");
            }
        }
        force += forceAdd;
        agility += agilityAdd;
        Player player = new Player(name, force, agility);

        Thread.sleep(500);
        while (true) {
            if (chooseLocation(player) == 2) {
                Characters monster;
                Thread.sleep(500);
                Random r = new Random();
                int randMonster = r.nextInt(2);
                if (randMonster == 1) {
                    countGoblin +=1;
                    monster = new Goblin("Goblin" + countGoblin, force, agility);
                } else {
                    countSkelleton +=1;
                    monster = new Skeleton("Skelleton" + countSkelleton, force, agility);
                }
                Thread.sleep(500);
                Thread newBattle = new Battle();
                newBattle.start();
                Battle.battle(monster, player);
                newBattle.join();
                if (player.experience > 50*0.75*level) {
                    level +=1;
                    System.out.println("Поздравляем! Теперь вы игрок " + level + " уровня.");
                }
            }
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+?");
    }

    // Куда отправляемся?
    public static int chooseLocation(Player player) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String chooseStr;
        int choose;

        System.out.println();
        System.out.println();
        System.out.println("Куда хотите пойти?" + "\n" +
                "1. К торговцу" + "\n" +
                "2. В тёмный лес" + "\n" +
                "3. На выход");

        while (true) {
            System.out.println("Введите 1,2 или 3");
            chooseStr = scanner.nextLine();
            if (isNumeric(chooseStr)) {
                choose = Integer.parseInt(chooseStr);
                if (choose >= 1 && choose <= 3) {
                    break;
                } else {
                    System.out.println("Неправильный ввод. Введите число 1,2 или 3");
                }
            } else {
                System.out.println("Неправильный ввод. Введите число 1,2 или 3");
            }
        }

        switch (choose) {
            case 1:
                boolean isBuy = false;
                if (player.life < 100 && player.gold > 0) {
                    System.out.println("Можно купить зелье и поправить здоровье...");
                    int lifeToADD = 100 - player.life;
                    if (lifeToADD <= player.gold) {
                        player.life += lifeToADD;
                        player.gold -= lifeToADD;
                    } else {
                        player.life += player.gold;
                        player.gold = 0;
                    }
                    isBuy = true;
                    Thread.sleep(500);
                    System.out.println("Здоровье поправлено: " + player.life + ", золота осталось: " + player.gold);
                }
                if (player.life == 100 && !isBuy) System.out.println("Здоровье в норме.");
                if (player.gold == 0 && !isBuy) System.out.println("Нет денег для покупки зелья.");

                System.out.println("Куда хотите пойти?" + "\n" +
                        "2. В тёмный лес" + "\n" +
                        "3. На выход");
                while (true) {
                    System.out.println("Введите 2 или 3");
                    chooseStr = scanner.nextLine();
                    if (isNumeric(chooseStr)) {
                        choose = Integer.parseInt(chooseStr);
                        if (choose >= 2 && choose <= 3) {
                            break;
                        } else {
                            System.out.println("Неправильный ввод. Введите число 2 или 3");
                        }
                    } else {
                        System.out.println("Неправильный ввод. Введите число 2 или 3");
                    }
                }

                switch (choose) {
                    case 2:
                        System.out.println("Идём в лес!!!");
                        break;
                    case 3:
                        System.out.println("Игра закончена");
                        System.exit(0);
                        break;
                }
                break;
            case 2:
                System.out.println("Идём в лес!!!");
                break;
            case 3:
                System.out.println("Игра закончена");
                System.exit(0);
                break;
        }
        return choose;
    }
}
