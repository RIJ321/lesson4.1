package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 20_000;
    public static int bossDamage = 120;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {1200, 1020, 930, 950, 2500, 900, 1300, 1100};
    public static int[] heroesDamage = {180, 160, 90, 0, 60, 100, 130, 140};
    public static String[] heroesClass = new String[]{"Liu Kang", "Cyrax", "Quan Chi", "Cleric", "Goro", "Johny Cage ", "Baraka ", "Raiden"};
    public static String[] heroesAttackType = {"Physical", "Magical", "Soul magic", "not changed", "Goro's", "Lucky", "Berserker", "Lightning"};
    public static int roundNumber = 0;
    public static int damageToGoro = bossDamage / 5;
    public static int gorosDPS = bossDamage - damageToGoro;


    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }


    public static void isBaraka() {
        for (int i = 0; i < 1; i++) {
            Random random = new Random();
            int randomDamage = random.nextInt(gorosDPS);
            boolean alwaysHit = true;
            int damageToBaraka = bossDamage-randomDamage;
            int barakasDamage = heroesDamage[6] + randomDamage;

            if (alwaysHit) {
                heroesHealth[6] = heroesHealth[6] - randomDamage;
                bossHealth = bossHealth - barakasDamage;

                System.out.println("\nBaraka hit: " + barakasDamage + ", " + "take: " + damageToBaraka);
                break;

            }
            if (heroesHealth[6] <= 0) {
                heroesHealth[6] = 0;
            }
            break;
        }
    }


    public static void cleric() {

        Random random = new Random();
        int healing = random.nextInt(80);
        for (int i = 0; i < heroesHealth.length; i++) {
            int randomHero = (int) Math.floor(Math.random() * heroesClass.length);

            if (heroesHealth[randomHero] < 100 && heroesHealth[3] > 0) {
                heroesHealth[randomHero] = heroesHealth[randomHero] + healing;
                System.out.println("\nCleric healed: " + heroesClass[randomHero] + " +" + healing + "HP.");
                break;
            }
        }

    }

    public static void isGoro() {
        for (int i = 0; i < heroesClass.length; i++) {
            if (heroesHealth[4] > 0) {
                heroesHealth[4] = heroesHealth[4] - damageToGoro;
            }
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = heroesHealth[i] = 0;
            } else if (heroesHealth[4] <= 0) {
                heroesHealth[4] = 0;
            }
        }
        System.out.println("Goro took damage:" + (damageToGoro * (heroesClass.length - 1)));
    }

    public static void lucky() {
        Random random = new Random();
        boolean cagesCoin = random.nextBoolean();
        if (!cagesCoin) {
            heroesHealth[5] = heroesHealth[5] - gorosDPS;
        }
    }


    public static void round() {
        roundNumber++;
        System.out.println("\n    ROUND: " + roundNumber + "\n     FIGHT!");

        defenseType();
        if (bossHealth > 0 && isRaiden()) {
            isBaraka();
            bossAttacks();
            lucky();
            isGoro();
        }
        heroesHits();
        cleric();
        printStatistics();
    }

    public static void defenseType() {
        Random random = new Random();
        int randomDefense = random.nextInt(heroesAttackType.length); // 0, 1, 2
        bossDefenceType = heroesAttackType[randomDefense];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("____________________________________");
            System.out.println("    You win.");
            return true;
        }
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("____________________________________");
            System.out.println("     DEFEAT");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("_______________________");
        System.out.println("Boss health: " + bossHealth + " [" + gorosDPS + "]\n");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesClass[i] + " health: "
                    + heroesHealth[i] + "  DPS:[" + heroesDamage[i] + "]");
        }
    }

    public static void bossAttacks() {

        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - gorosDPS;//
                }
            }
        }
    }


    public static void heroesHits() {
        for (int i = 0; i < heroesDamage.length; i++) {


            if (bossHealth > 0 && heroesHealth[i] > 0) {
                Random random = new Random();
                int critDamage = random.nextInt(5);

                if (bossDefenceType == heroesAttackType[i]) {


                    if (bossHealth - heroesDamage[i] * critDamage < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * critDamage - heroesDamage[i] + heroesDamage[6];
                    }
                    System.out.println("Critical damage: " + (heroesDamage[i] * critDamage));
                } else {
                    bossHealth = bossHealth - heroesDamage[i];
                }
            }
        }
    }


    public static boolean isRaiden() {
        Random random = new Random();
        boolean probality = random.nextBoolean();
        for (int i = 0; i < 1; i++) {

        }
        for (int i = 0; i < 1; i++) {
            if (probality) {
                bossDamage = 0;
                System.out.println("\nBoss is hits.");
            } else if (!probality) {
                bossDamage = gorosDPS;
                System.out.println("\nBoss is stunned.");
            } else if (bossHealth <= 0) {
                bossHealth = 0;
            }

        }
        return probality;
    }

}



/*ДЗ:
● Добавить 4-го игрока Medic, у которого есть способность лечить после каждого
раунда на N-ное количество единиц здоровья только одного из членов команды,
имеющего здоровье менее 100 единиц. Мертвых героев медик оживлять не может,
и лечит он до тех пор пока жив сам. Медик не участвует в бою, но получает урон от
Босса

ДЗ на сообразительность:
● Добавить n-го игрока, Golem, который имеет увеличенную жизнь но слабый удар.
Может принимать на себя 1/5 часть урона исходящего от босса по другим игрокам.
● Добавить n-го игрока, Lucky, имеет шанс уклонения от ударов босса.
● Добавить n-го игрока, Berserk, блокирует часть удара босса по себе и прибавляет
заблокированный урон к своему урону и возвращает его боссу
● Добавить n-го игрока, Thor, удар по боссу имеет шанс оглушить босса на 1 раунд,
вследствие чего босс пропускает 1 раунд и не наносит урон героям.
 */
