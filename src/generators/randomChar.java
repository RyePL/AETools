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
        System.out.println("Debug: " + statMods[bestStatIndex] + ", " + statMods[worstStatIndex]);
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
}
