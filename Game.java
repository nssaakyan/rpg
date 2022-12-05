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


        // ������ ���������
        Scanner scanner = new Scanner(System.in);
        System.out.println("�������� ����.");
        System.out.println("�������� ���������...");
        System.out.println("������� ���");
        String name = scanner.nextLine().toUpperCase();
        System.out.println("��������� ����: 15, ��������� ��������: 15");
        System.out.println("������������ 15 �������������� ����� ����� ����� � ��������� ���������");
        while (true) {
            System.out.println("�������� � ����(0...15)");
            forceAddStr = scanner.nextLine();
            if (isNumeric(forceAddStr)) {
                forceAdd = Integer.parseInt(forceAddStr);
                if (forceAdd >= 0 && forceAdd <= 15) {
                    System.out.println("���������� � ����: " + forceAdd);
                    agilityAdd = 15 - forceAdd;
                    Thread.sleep(200);
                    System.out.println("���������� � ��������: " + agilityAdd);
                    Thread.sleep(200);
                    break;
                } else {
                    System.out.println("������������ ����. ������� ����� �� 0 �� 15");
                }
            } else {
                System.out.println("������������ ����. ������� ����� �� 0 �� 15");
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
                    System.out.println("�����������! ������ �� ����� " + level + " ������.");
                }
            }
        }
    }

    public static boolean isNumeric(String str) {
        return str.matches("\\d+?");
    }

    // ���� ������������?
    public static int chooseLocation(Player player) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        String chooseStr;
        int choose;

        System.out.println();
        System.out.println();
        System.out.println("���� ������ �����?" + "\n" +
                "1. � ��������" + "\n" +
                "2. � ����� ���" + "\n" +
                "3. �� �����");

        while (true) {
            System.out.println("������� 1,2 ��� 3");
            chooseStr = scanner.nextLine();
            if (isNumeric(chooseStr)) {
                choose = Integer.parseInt(chooseStr);
                if (choose >= 1 && choose <= 3) {
                    break;
                } else {
                    System.out.println("������������ ����. ������� ����� 1,2 ��� 3");
                }
            } else {
                System.out.println("������������ ����. ������� ����� 1,2 ��� 3");
            }
        }

        switch (choose) {
            case 1:
                boolean isBuy = false;
                if (player.life < 100 && player.gold > 0) {
                    System.out.println("����� ������ ����� � ��������� ��������...");
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
                    System.out.println("�������� ����������: " + player.life + ", ������ ��������: " + player.gold);
                }
                if (player.life == 100 && !isBuy) System.out.println("�������� � �����.");
                if (player.gold == 0 && !isBuy) System.out.println("��� ����� ��� ������� �����.");

                System.out.println("���� ������ �����?" + "\n" +
                        "2. � ����� ���" + "\n" +
                        "3. �� �����");
                while (true) {
                    System.out.println("������� 2 ��� 3");
                    chooseStr = scanner.nextLine();
                    if (isNumeric(chooseStr)) {
                        choose = Integer.parseInt(chooseStr);
                        if (choose >= 2 && choose <= 3) {
                            break;
                        } else {
                            System.out.println("������������ ����. ������� ����� 2 ��� 3");
                        }
                    } else {
                        System.out.println("������������ ����. ������� ����� 2 ��� 3");
                    }
                }

                switch (choose) {
                    case 2:
                        System.out.println("��� � ���!!!");
                        break;
                    case 3:
                        System.out.println("���� ���������");
                        System.exit(0);
                        break;
                }
                break;
            case 2:
                System.out.println("��� � ���!!!");
                break;
            case 3:
                System.out.println("���� ���������");
                System.exit(0);
                break;
        }
        return choose;
    }
}
