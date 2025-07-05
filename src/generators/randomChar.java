package generators;

public class randomChar {
    public static void main(String[] args) {
        System.out.println(generateName());

        String[] statNames = {"STR", "DEX", "CON", "INT", "WIS", "CHA"};
        int[] statValues = new int[6];
        int[] statMods = new int[6];

        for (int i = 0; i < 6; i++) {
            statValues[i] = statRoll();
            statMods[i] = (statValues[i] - 10) / 2;
        }

        // Find indices of best and worst stats
        int bestStatIndex = 0;
        int worstStatIndex = 0;
        for (int i = 1; i < 6; i++) {
            if (statValues[i] > statValues[bestStatIndex]) {
                bestStatIndex = i;
            }
            if (statValues[i] < statValues[worstStatIndex]) {
                worstStatIndex = i;
            }
        }

        String[][] qualities = {
            {"Beefy", "Muscular", "Slight", "Frail"},
            {"Nimble", "Quick", "Slow", "Clumsy"},
            {"Resolute", "Stout", "Weak", "Sickly"},
            {"Razor-Sharp", "Bright", "Dim", "Slow"},
            {"Deepened", "Aware", "Aloof", "Unaware"},
            {"Magnetic", "Charming", "Unpleasant", "Unlikeable"}
        };
        
        // Direct mapping for quality index
        int bestQualityIndex = (statMods[bestStatIndex] <= -2) ? 3 : (statMods[bestStatIndex] <= 0) ? 2 : (statMods[bestStatIndex] <= 2) ? 1 : 0;
        int worstQualityIndex = (statMods[worstStatIndex] <= -2) ? 3 : (statMods[worstStatIndex] <= 0) ? 2 : (statMods[worstStatIndex] <= 2) ? 1 : 0;
        
        System.out.println(qualities[bestStatIndex][bestQualityIndex] + ", " + qualities[worstStatIndex][worstQualityIndex]);
        
        String[] classes = {"Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};
        String[] keyStats = {"STR", "CHA", "WIS", "WIS", "STR", "DEX", "STR", "DEX", "DEX", "CHA", "CHA", "INT"};
        int[] keyStatRatings = new int[12];

        for (int i = 0; i < classes.length; i++) {
            keyStatRatings[i] = statValues[matchIndex(statNames, keyStats[i])];
        }

        // Select character class using weighted random selection with keyStatRatings as weights
        String charClass = weightedRandomSelect(classes, keyStatRatings);

        int classQuality = Math.max(statMods[matchIndex(statNames, keyStats[matchIndex(classes, charClass)])], 1);
        int reverseQuality = Math.max(-statMods[worstStatIndex], 0);
        int level = multiRoll(4, classQuality) - multiRoll(4, reverseQuality);
        level = Math.max(level, 1);
        level = Math.min(level, 20);

        System.out.println("Level " + level + " " + charClass);

        //index of spell slots equals spell level
        int[] spellSlots = { 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[][] spellSlotReference = {
            {2, 0, 0, 0, 0, 0, 0, 0, 0},   // 1st level
            {3, 0, 0, 0, 0, 0, 0, 0, 0},   // 2nd level
            {4, 2, 0, 0, 0, 0, 0, 0, 0},   // 3rd level
            {4, 3, 0, 0, 0, 0, 0, 0, 0},   // 4th level
            {4, 3, 2, 0, 0, 0, 0, 0, 0},   // 5th level
            {4, 3, 3, 0, 0, 0, 0, 0, 0},   // 6th level
            {4, 3, 3, 1, 0, 0, 0, 0, 0},   // 7th level
            {4, 3, 3, 2, 0, 0, 0, 0, 0},   // 8th level
            {4, 3, 3, 3, 1, 0, 0, 0, 0},   // 9th level
            {4, 3, 3, 3, 2, 0, 0, 0, 0},   // 10th level
            {4, 3, 3, 3, 2, 1, 0, 0, 0},   // 11th level
            {4, 3, 3, 3, 2, 1, 0, 0, 0},   // 12th level
            {4, 3, 3, 3, 2, 1, 1, 0, 0},   // 13th level
            {4, 3, 3, 3, 2, 1, 1, 0, 0},   // 14th level
            {4, 3, 3, 3, 2, 1, 1, 1, 0},   // 15th level
            {4, 3, 3, 3, 2, 1, 1, 1, 0},   // 16th level
            {4, 3, 3, 3, 2, 1, 1, 1, 1},   // 17th level
            {4, 3, 3, 3, 3, 1, 1, 1, 1},   // 18th level
            {4, 3, 3, 3, 3, 2, 1, 1, 1},   // 19th level
            {4, 3, 3, 3, 3, 2, 2, 1, 1}    // 20th level
        };
        if (charClass.equals("Bard") || charClass.equals("Cleric") || charClass.equals("Druid") || charClass.equals("Sorcerer") || charClass.equals("Wizard")) {
            for (int i = 0; i < spellSlots.length; i++) {
                spellSlots[i] = spellSlotReference[level][i];
            }
        } else if ((charClass.equals("Paladin") || charClass.equals("Ranger")) && (level > 1)) {
            for (int i = 0; i < spellSlots.length; i++) {
                spellSlots[i] = spellSlotReference[Math.round(level/2)][i];
            }
        }

        int proficiencyBonus = 2 + (level - 1) / 4;

        String subClass = "";
        int subClassIndex = roll(2);
        int armorClass = 10;
        int hitPoints = 0;
        String basicAttack = "";
        String features = "";
        String actions = "";
        String bonusActions = "";
        String reactions = "";

        if (charClass.equals("Barbarian")) {
            if (subClassIndex == 1) subClass = "Path of the Aether Transformation";
            else subClass = "Path of the Wronged";

            int rageDamage = 2;
            String rageDescription = "Rage: Basic rage benefits. ";

            if (level >= 2) {
                features += "Danger Sense: Can't be surprised, advantage on DEX saves.\n";
                features += "Reckless Attack: Can gain advantage on attacks but suffers advantage on hits until next turn.\n";
            }
            if (level >= 3) {
                if (subClassIndex == 1) {
                    rageDescription += "Immune to poison and force. Immune to poisoned condition. Gain d10 claw weapon. ";
                }
                else {
                    reactions += "Outrage: On damage while raging, gain " + (level/2) + " temp HP and move half speed to the target.\n";
                    reactions += "Outrage: On damage, rage.\n";
                }
            }
            if (level >= 4) {
                abilityImprovement(statValues, 0, 2);
                updateModifiers(statValues, statMods);
                rageDamage++;
            }
            if (level >= 5) {
                features += "Multiattack: Can attack twice.\n";
            }
            if (level >= 6) {
                if (subClassIndex == 1) {
                    rageDescription += "Can choose between +1d6 poison damage, +2 AC, or magic resistance. ";
                }
                else {
                    features += "On a saving throw, gain a grudge die. Expend the grudge die to add 1d6 to an attack roll.\n";
                }
            }
            if (level >= 7) {
                features += "Feral Instinct: Advantage on initiative rolls.\n";
                rageDescription += "Move up to half speed on activation.\n";
            }
            if (level >= 8) {
                abilityImprovement(statValues, 0, 2);
                updateModifiers(statValues, statMods);
            }
            if (level >= 9) {
                features += "Brutal Strike: Can forgo advantage to deal an extra 1d10 damage and either push or reduce speed by 15 ft.\n";
                rageDamage++;
            }
            if (level >= 10) {
                if (subClassIndex == 1) {
                    features += "Call of the Aether: Immune to aether poisoning. Has full swim speed. Can cast detect magic.\n";
                }
                else {
                    reactions += "Defiant Outburst: Once per day on hitting 0 HP, grant self and allies 1d12 HP.\n";
                }
            }
            if (level >= 11) {
                features += "Relentless Rage: On hitting 0 HP while raging, on a DC 10 CON save, regain " + (level * 2) + " HP. Save increases by 5 each time.\n";
            }
            if (level >= 12) {
                abilityImprovement(statValues, 0, 2);
                updateModifiers(statValues, statMods);
            }
            if (level >= 13) {
                features += "Improved Brutal Strike: Can also give the target save disadvantage or -5 AC on next attack.\n";
            }
            if (level >= 14) {
                if (subClassIndex == 1) {
                    rageDescription += "Rage claws are now 2d12 weapons. Can use two features form Deep-Infused Rage.\n";
                }
                else {
                    reactions += "Suffer No Insult: On damage or saving throw, gain advantage, fright immunity, and reaction attacks on the attacker.\n";
                }
            }
            if (level >= 15) {
                features += "Persistent Rage: Regain rage uses on initiative once per day. Rage does not need extending.\n";
            }
            if (level >= 16) {
                abilityImprovement(statValues, 0, 2);
                updateModifiers(statValues, statMods);
                rageDamage++;
            }
            if (level >= 17) {
                features += "Improved Brutal Strike: Now deals 2d10 bonus damage and can choose two features.\n";
            }
            if (level >= 18) {
                features += "Indomitable Might: STR is floor for STR checks and saves.\n";
            }
            if (level >= 19) {
                features += "Epic Booon: Choose one that is appropriate.\n";
            }
            if (level >= 20) {
                statValues[0] += 4;
                statValues[2] += 4;
                updateModifiers(statValues, statMods);
            }
            bonusActions = rageDescription + "\n";
            hitPoints = 12 + ((level - 1) * (7 + statMods[2]));
            armorClass = 10 + statMods[1] + statMods[2];
            basicAttack = "Greataxe: +" + (statMods[0] + proficiencyBonus) + " to hit, 1d12 + " + (statMods[0] + rageDamage) + " slashing";
        }
        else if (charClass.equals("Bard")) {

        }
        else if (charClass.equals("Cleric")) {
        }
        else if (charClass.equals("Druid")) {
        }
        else if (charClass.equals("Fighter")) {
        }
        else if (charClass.equals("Monk")) {
        }
        else if (charClass.equals("Paladin")) {
        }
        else if (charClass.equals("Ranger")) {
        }
        else if (charClass.equals("Rogue")) {
        }
        else if (charClass.equals("Sorcerer")) {
        }
        else if (charClass.equals("Warlock")) {
        }
        else if (charClass.equals("Wizard")) {
        }

        System.out.println(subClass);
        System.out.println("Hit Points: " + hitPoints);
        System.out.println("Armor Class: " + armorClass);
        System.out.println("Basic Attack: " + basicAttack);
        System.out.println("Features: " + features);
        System.out.println("Bonus Actions: " + bonusActions);
        System.out.println("Actions: " + actions);
        System.out.println("Reactions: " + reactions);
    }

    public static String generateName() {
        String name = "";
        
        String[] firstName = {
            "Akira", "Alberto", "Alexis", "Angelina", "Antonio", "Anzu", "Asha", "Asteth", "Aurelia", "Avery",
            "Aziza", "Benecio", "Bethany", "Blanca", "Botan", "Bradley", "Cai", "Cassandra", "Cent", "Chapawee",
            "Corinna", "Daisuke", "Darius", "Davy", "Dawn", "Dominique", "Dudley", "Edward", "Eleanor", "Elizabeth",
            "Erica", "Esteban", "Eva", "Fabrizio", "Falco", "Fawkes", "Forrest", "Frederic", "Garrett", "Genevieve",
            "Greta", "Hector", "Hippolyte", "Hira", "Hudson", "Isabella", "Ivelisse", "Jack", "Jade", "James",
            "Jan", "Jim", "Jocelyn", "Johnny", "Karla", "Kenji", "Kingsley", "Kwasi", "Largo", "Leonardo",
            "Lin", "Ludley", "Lukas", "Mabel", "Marco", "Mei Ling", "Mika", "Nadia", "Olivia", "Omen",
            "Othenio", "Philip", "Phoebe", "Pierre", "Rei", "Ren", "Renato", "Rin", "Rose", "Ruth",
            "Samorix", "Sara", "Seymour", "Skah", "Tashunka", "Tatonga", "Timoteo", "Tristan", "Ugo", "Valentin",
            "Veronica", "Waylon", "Will", "Yamileth", "Yanira", "Yusuf", "Z'Taan", "Zaunder", "Zonta"
        };
        
        String[] nickName = {
            "\"Big Guns\"", "\"Bilgewater\"", "\"Biscuits\"", "\"Bishop\"", "\"Black Blade\"", "\"Black Jack\"", "\"Blessed\"", "\"Blueblood\"", "\"Bombshell\"", "\"Broadside\"",
            "\"Calico\"", "\"Cannonball\"", "\"Cat\"", "\"Chopper\"", "\"Compass\"", "\"Crowseye\"", "\"Cups\"", "\"Cutlass\"", "\"Cutthroat\"", "\"Deadeye\"",
            "\"Diabolito\"", "\"Doctor\"", "\"Dread\"", "\"Drowned\"", "\"Entrails\"", "\"Executioner\"", "\"Fearsome\"", "\"Fishguts\"", "\"Five Fathoms\"", "\"Flogger\"",
            "\"Four Fingers\"", "\"Freebooter\"", "\"Gibbet\"", "\"Gold-Tooth\"", "\"Goldpiece\"", "\"Grog\"", "\"Hangman\"", "\"Harpoon\"", "\"Hook\"", "\"Hornswaggle\"",
            "\"Howler\"", "\"Jolly\"", "\"Keelhaul\"", "\"King Killer\"", "\"Kraken\"", "\"Landlubber\"", "\"Massacre\"", "\"Matey\"", "\"Maverick\"", "\"Menace\"",
            "\"Meteorite\"", "\"Miracle\"", "\"Old Briney\"", "\"Old Red\"", "\"Old Salt\"", "\"One-Ear\"", "\"Patches\"", "\"Peg\"", "\"Pinky\"", "\"Plank Walker\"",
            "\"Poopdeck\"", "\"Rascal\"", "\"Reaper\"", "\"Red Gills\"", "\"Redhands\"", "\"Roaring\"", "\"Rumrunner\"", "\"Rusty\"", "\"Sawbones\"", "\"Scallywag\"",
            "\"Scurvy\"", "\"Scuttlebutt\"", "\"Seadog\"", "\"Seagull\"", "\"Shark Bait\"", "\"Sharky\"", "\"Silver\"", "\"Slug Guts\"", "\"Smokey\"", "\"Spice\"",
            "\"Spyglass\"", "\"Storm\"", "\"Swabs\"", "\"Tadpole\"", "\"Tar\"", "\"Temple\"", "\"The Anchor\"", "\"The Keg\"", "\"The Kid\"", "\"The Sage\"",
            "\"The Scourge\"", "\"Three Blades\"", "\"Thunder\"", "\"Timbers\"", "\"Trove\"", "\"Vengeance\"", "\"Whale\"", "\"Whitebeard\"", "\"Widow-maker\""
        };
        
        String[] lastName = {
            "Adur", "Argento", "Asaju", "Bahr", "Barbieri", "Barbossa", "Bashar", "Blanco", "Bligh", "Bouchard",
            "Chafulumisa", "Chamberlain", "Collins", "Cook", "Costa", "Da Rocha", "de Argo", "de Armas", "de Graaf", "de la Marck",
            "de Leon", "Djawadi", "Fletcher", "Ford", "Gamwhich", "Geld", "Gibbs", "Gilbert", "Gorlami", "Graves",
            "Hakim", "Hawkins", "He", "Hongshui", "Humboldt", "Jie", "Jones", "Kageyama", "King", "Kotaro",
            "Le Chien", "Le Grande", "Levasseur", "Marchetti", "Marin", "Marino", "Masondo", "Matsumoto", "Melville", "Mitford",
            "Mitsurugi", "Mlambo", "Morgan", "Nelson", "Nemo", "Oceanrun", "Odili", "Oska", "Otundu", "Pauzer",
            "Pesci", "Pesqueira", "Picardo", "Preuss", "Qian", "Quadros", "Rackham", "Rana", "Rauschenberger", "Rex",
            "Riva", "Rogers", "Sanches", "Sargeant", "Sasaki", "Seward", "Silver", "Smith", "Souza", "Sparrow",
            "Spinoza", "Stanley", "Swan", "Thorn", "Turner", "Uberti", "Verga", "von Fleet", "von Holtz", "Waveborn",
            "Windshadow", "Xiong", "Yamada", "Yamaguchi", "Yan", "Zhanzheng", "Zhao", "Ziegler", "Zollman"
        };
        
        name = firstName[roll(firstName.length) - 1];
        if (roll(10) < 4) {
            name += " " + nickName[roll(nickName.length) - 1];
        }
        name += " " + lastName[roll(lastName.length) - 1];

        return name;
    }

    public static String generateBoons(int rank) {
        String[] boons = {
            "Aether Assassin", "Aether-Immune", "Avast Me Hearties!", "Beast Chaser", 
            "Blow the Man Down!", "Butcher of the Seas", "Cannonward Shield", "Champion of the Unknown",
            "Do-or-die", "Fire in the Hole!", "Grog Waterer", "Intimidating Demeanor",
            "Lucky Looter", "Merciful Demeanor", "Never Say Die", "Pack Rat",
            "Recruiter", "Rigrunner", "Sharp Spotter", "Skilled Mentor",
            "Tactical Genius", "Wind-Kissed"
        };
        
        int[] thresholds = {4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88};
        
        // Determine number of boons based on rank
        int numBoons = 0;
        if (rank >= 1) numBoons = 1;
        if (rank >= 3) numBoons = 2;
        
        if (numBoons == 0) {
            return "";
        }
        
        // Generate first boon
        String firstBoon = stringSelect(roll(100), thresholds, boons);
        
        if (numBoons == 1) {
            return firstBoon;
        }
        
        // Generate second boon (ensure it's different from the first)
        String secondBoon;
        do {
            secondBoon = stringSelect(roll(100), thresholds, boons);
        } while (secondBoon.equals(firstBoon));
        
        return firstBoon + ", " + secondBoon;
    }

    private static int roll(int size) {
        return (int) (Math.random() * size) + 1;
    }

    private static int multiRoll(int size, int num) {
        int total = 0;
        for (int i = 0; i < num; i++) {
            total += roll(size);
        }
        return total;
    }

    private static int statRoll() {
        int lowest = 100;
        int total = 0;
        for (int i = 0; i < 4; i++) {
            int roll = roll(6);
            total += roll;
            if (roll < lowest) {
                lowest = roll;
            }
        }
        return total - lowest;
    }

    private static String stringSelect(int num, int[] thresholds, String[] results) {
        for (int i = 0; i < thresholds.length; i++) {
            if (num <= thresholds[i]) {
                return results[i];
            }
        }
        return results[results.length - 1];
    }

    private static int matchIndex(String[] array, String toFind) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(toFind)) return i;
        }
        return -1;
    }

    private static String weightedRandomSelect(String[] items, int[] weights) {
        // Calculate total weight
        int totalWeight = 0;
        for (int weight : weights) {
            totalWeight += weight;
        }
        
        // Generate random number between 1 and total weight
        int randomValue = roll(totalWeight);
        
        // Find the item corresponding to the random value
        int currentWeight = 0;
        for (int i = 0; i < items.length; i++) {
            currentWeight += weights[i];
            if (randomValue <= currentWeight) {
                return items[i];
            }
        }
        
        // Fallback to last item (shouldn't reach here if weights are valid)
        return items[items.length - 1];
    }

    private static void abilityImprovement(int[] abilities, int firstIndex, int secondIndex) {
        int increases = 2;

        // Prioritize first index, apply up to two increases if possible
        int canIncreaseFirst = Math.min(20 - abilities[firstIndex], increases);
        abilities[firstIndex] += canIncreaseFirst;
        increases -= canIncreaseFirst;

        // Then second index (if not the same as first)
        if (increases > 0 && secondIndex != firstIndex && abilities[secondIndex] < 20) {
            abilities[secondIndex]++;
            increases--;
        }

        // If still increases left, randomly increment an element with value 18 or below
        while (increases > 0) {
            // Collect indices with value <= 18
            java.util.List<Integer> candidates = new java.util.ArrayList<>();
            for (int i = 0; i < abilities.length; i++) {
                if (abilities[i] <= 18) {
                    candidates.add(i);
                }
            }
            if (candidates.isEmpty()) break; // No valid candidates

            int randomIdx = candidates.get(roll(candidates.size()) - 1);
            abilities[randomIdx]++;
            increases--;
        }
    }

    private static void updateModifiers(int[] abilities, int[] modifiers) {
        for (int i = 0; i < abilities.length; i++) {
            modifiers[i] = (abilities[i] - 10) / 2;
        }
    }
}
