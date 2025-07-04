package generators;

public class randomShip {
    public static void main(String[] args) {
        int roll = roll(100);
        String faction = stringSelect(roll, new int[] {20, 60, 80, 100}, new String[] {"Ayrissian", "Independent", "Karelagne", "Pirate"});
        String prefix = stringSelect(roll, new int[] {20, 60, 80, 100}, new String[] {"AMS ", "", "INS ", ""});

        String[] firstNames = {
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "", "", "", "", "", "",
            "", "", "", "", "Aetherial ", "Alabaster ", "Ashen ", "Asteth's ", "Astral ", "Baneful ",
            "Blazing ", "Blind ", "Bloody ", "Booming ", "Bristling ", "Brutal ", "Cerulean ", "Copper ", "Cosmic ", "Creeping ",
            "Crimson ", "Cyrill's ", "Dawn ", "Deadly ", "Decorated ", "Dirty ", "Divine ", "Dreaded ", "Emerald ", "Emperor's ",
            "Fearful ", "Floating ", "Flying ", "Frigid ", "Frozen ", "Ghastly ", "Golden ", "Grim ", "Grinding ", "Howling ",
            "Imperial ", "Incandescent ", "Iridescent ", "Ironclad ", "Keen ", "King's ", "Lady's ", "Liar's ", "Lord's ", "Nimble ",
            "Noble ", "Painted ", "Pernicious ", "Qoz's ", "Queen's ", "Racing ", "Radiant ", "Raging ", "Rapid ", "Reaving ",
            "Rotting ", "Royal ", "Sable ", "Sapphire ", "Scarlet ", "Scintillating ", "Searing ", "Shimmering ", "Silver ", "Singing ",
            "Slippery ", "Stalwart ", "Starlight ", "Stellar ", "Surging ", "Swift ", "Troubled ", "Twilight ", "Unammon's ", "Vindictive "
        };

        String[] secondNames = {
            "Aegis", "Answer", "Apostate", "Banshee", "Bargain", "Blade", "Bounty", "Brig", "Chaos", "Chariot",
            "Chimera", "Comet", "Constitution", "Contract", "Covenant", "Crown", "Crystal", "Cutter", "Dagger", "Defeat",
            "Demon", "Destroyer", "Devil", "Discovery", "Doom", "Drake", "Dream", "Eagle", "Eel", "Endeavour",
            "Endurance", "Explorer", "Falcon", "Fancy", "Fin", "Flame", "Fortress", "Fortune", "Friend", "Frigate",
            "Galley", "Gift", "Glory", "Grace", "Greed", "Harpy", "Harrier", "Harvest", "Hauler", "Hind",
            "Honor", "Hulk", "Interceptor", "Jewel", "Judge", "Kraken", "Legend", "Lion", "Manticore", "March",
            "Marriage", "Meteor", "Mutiny", "Myth", "Pact", "Pearl", "Pioneer", "Predator", "Prison", "Promise",
            "Raider", "Ram", "Raptor", "Reason", "Remark", "Renegade", "Revelation", "Revenge", "Rover", "Sacrifice",
            "Schooner", "Serpent", "Shadow", "Shrike", "Siren", "Sloop", "Star", "Storm", "Temple", "Titan",
            "Torture", "Traitor", "Venture", "Victory", "Wanderer", "Warrant", "Wraith", "Wrath", "Wreck", "Wyrm"
        };
        
        String shipName = "The " + prefix + firstNames[roll(firstNames.length) - 1] + secondNames[roll(secondNames.length) - 1];
        System.out.println(shipName);


        double armament = doubleSelect(roll(100), new int[] {10, 20, 70, 85, 100}, new Double[] {0.0, 0.2, 0.5, 0.8, 1.0});
        String[] shipTypes = {"Sloop", "Schooner", "Brigantine", "Frigate", "Galleon", "Man o' War"};
        roll = roll(100);
        String shipType = stringSelect(roll, new int[] {25, 50, 70, 85, 95, 100}, shipTypes);

        System.out.println(faction + " " + shipType);

        // Determine crew size and loot
        int crewSize = 0, maxCrewSize = 0, loot = 0;
        if (shipType.equals("Sloop")) {
            crewSize = 8 + multiRoll(4,4);
            maxCrewSize = 24;
            loot = 100 * multiRoll(6, 3);
        } else if (shipType.equals("Schooner")) {
            crewSize = 13 + multiRoll(6, 3);
            maxCrewSize = 32;
            loot = 100 * multiRoll(6, 6);
        } else if (shipType.equals("Brigantine")) {
            crewSize = 16 + multiRoll(6, 4);
            maxCrewSize = 40;
            loot = 100 * multiRoll(6, 12);
        } else if (shipType.equals("Frigate")) {
            crewSize = 21 + multiRoll(6, 5);
            maxCrewSize = 52;
            loot = 1000 * multiRoll(8, 2);
        } else if (shipType.equals("Galleon")) {
            crewSize = 26 + multiRoll(6, 6);
            maxCrewSize = 64;
            loot = 1000 * multiRoll(8, 4);
        } else if (shipType.equals("Man o' War")) {
            crewSize = 42 + multiRoll(8, 7);
            maxCrewSize = 100;
            loot = 1000 * multiRoll(8, 8);
        }
        System.out.println("Crew Size: " + crewSize + " / " + maxCrewSize);
        //Merchant ships have double loot
        //Ayris is always merchant, Karelagne is 1/5 chance, Independent is 1/4 chance
        if ((faction.equals("Ayrissian") || ((faction.equals("Karelagne") && (roll(5) == 1)) || (faction.equals("Independent") && (roll(4) == 1))))) {
            loot *= 2;
        }
        System.out.println("Loot: " + loot + " gp");

        // Determine weapon slots
        int bowSlots = (int) (intSelect(roll, new int[] {25, 50, 70, 85, 95, 100}, new int[] {0, 0, 0, 0, 2, 2}) * armament);
        int portSlots = (int) (intSelect(roll, new int[] {25, 50, 70, 85, 95, 100}, new int[] {4, 5, 6, 8, 10, 12}) * armament);
        int starboardSlots = (int) (intSelect(roll, new int[] {25, 50, 70, 85, 95, 100}, new int[] {4, 5, 6, 8, 10, 12}) * armament);
        int sternSlots = (int) (intSelect(roll, new int[] {25, 50, 70, 85, 95, 100}, new int[] {0, 0, 0, 1, 2, 3}) * armament);

        boolean warship = shipType.equals("Frigate") || shipType.equals("Galleon") || shipType.equals("Man o' War");
        
        // Create weapon arrays based on slot counts
        String[] bowWeapons = new String[bowSlots];
        String[] portWeapons = new String[portSlots];
        String[] starboardWeapons = new String[starboardSlots];
        String[] sternWeapons = new String[sternSlots];
        
        // Fill arrays with empty strings initially
        for (int i = 0; i < bowWeapons.length; i++) bowWeapons[i] = "";
        for (int i = 0; i < portWeapons.length; i++) portWeapons[i] = "";
        for (int i = 0; i < starboardWeapons.length; i++) starboardWeapons[i] = "";
        for (int i = 0; i < sternWeapons.length; i++) sternWeapons[i] = "";
        
        // Weapon types based on ship type
        String[] weaponTypes;
        int[] weaponThresholds;
        
        if (warship) {
            // Warship weapons
            weaponTypes = new String[] {"Carronade", "Coilgun", "Culverin", "Falconet", "Flamethrower", "Long Gun", "Long Nine", "Saker", "Swivel Gun Quartet", "Wrath Cannon"};
            weaponThresholds = new int[] {12, 14, 26, 33, 41, 59, 71, 89, 98, 100};
            // Wrath Cannon is even more rare outside Karelagne vessels
            if (faction.equals("Karelagne") == false) {
                weaponThresholds[8] = 99;
            }
        } else {
            // Regular ship weapons
            weaponTypes = new String[] {"Culverin", "Falconet", "Flamethrower", "Saker", "Swivel Gun Quartet"};
            weaponThresholds = new int[] {20, 35, 45, 85, 100};
        }
        
        // Fill weapon arrays
        while (hasEmptySlots(bowWeapons) || hasEmptySlots(portWeapons) || hasEmptySlots(starboardWeapons) || hasEmptySlots(sternWeapons)) {
            // Select random weapon type
            String selectedWeapon = stringSelect(roll(100), weaponThresholds, weaponTypes);
            
            // Determine how many slots to fill (random percentage of empty slots)
            int totalEmptySlots = countEmptySlots(bowWeapons) + countEmptySlots(portWeapons) + countEmptySlots(starboardWeapons) + countEmptySlots(sternWeapons);
            if (totalEmptySlots == 0) break;
            
            int slotsToFill = Math.max(1, (int)(totalEmptySlots * (roll(100) / 100.0)));
            
            // Fill port and starboard equally if possible
            int portEmpty = countEmptySlots(portWeapons);
            int starboardEmpty = countEmptySlots(starboardWeapons);
            int minSideSlots = Math.min(portEmpty, starboardEmpty);
            
            if (minSideSlots > 0 && slotsToFill >= 2) {
                int sideSlotsToFill = Math.min(minSideSlots, slotsToFill / 2);
                slotsToFill -= fillSlots(portWeapons, selectedWeapon, sideSlotsToFill);
                slotsToFill -= fillSlots(starboardWeapons, selectedWeapon, sideSlotsToFill);
            }
            
            // Fill remaining slots
            if (slotsToFill > 0) {
                if (selectedWeapon.equals("Long Nine") == false) {
                    slotsToFill -= fillSlots(bowWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(bowWeapons)));
                    slotsToFill -= fillSlots(portWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(portWeapons)));
                }
                slotsToFill -= fillSlots(starboardWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(starboardWeapons)));
                slotsToFill -= fillSlots(sternWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(sternWeapons)));
            }
        }
        
        // Print weapon arrays as summary counts
        if (bowSlots > 0) {
            System.out.println("Bow Weapons: " + summarizeArray(bowWeapons));
        }
        System.out.println("Port Weapons: " + summarizeArray(portWeapons));
        System.out.println("Starboard Weapons: " + summarizeArray(starboardWeapons));
        if (sternSlots > 0) {
            System.out.println("Stern Weapons: " + summarizeArray(sternWeapons));
        }
        
        boolean specialAmmunition = false;
        boolean upgrade = false;

        roll = roll(100);
        if ((roll >= 76) && (roll <= 85)) specialAmmunition = true;
        if (roll >= 86) upgrade = true;
        if (roll >= 96) specialAmmunition = true;
        
        int specialAmmunitionCount, upgradeCount;
        String specialAmmunitionList = "";
        String upgradeList = "";
        
        if (specialAmmunition) {
            roll = roll(100);
            if (roll <= 50) specialAmmunitionCount = 1;
            else if (roll <= 85) specialAmmunitionCount = roll(4);
            else if (roll <= 95) specialAmmunitionCount = roll(6);
            else specialAmmunitionCount = multiRoll(4, 2);

            // Ammunition table
            String[] ammoTypes = {"Canister Shot", "Carcass", "Chainshot", "Grapeshot", "Hullbusters", "Langridge", "Salamanders"};
            int[] ammoThresholds = {15, 30, 45, 60, 70, 90, 100};
            String[] specialAmmunitionArray = new String[specialAmmunitionCount];
            for (int i = 0; i < specialAmmunitionCount; i++) {
                specialAmmunitionArray[i] = stringSelect(roll(100), ammoThresholds, ammoTypes);
            }
            specialAmmunitionList = summarizeArray(specialAmmunitionArray);
        }
        if (upgrade) {
            roll = roll(100);
            if (roll <= 50) upgradeCount = 1;
            else if (roll <= 85) upgradeCount = roll(4);
            else if (roll <= 95) upgradeCount = roll(6);
            else upgradeCount = multiRoll(4, 2);

            // Upgrade table
            String[] upgradeTypes = {
                "Astral-Oak Reinforcements", "Ayrissian Canvas Sails", "Bucket-Cutter", "Cannonward Shield Matrix", "Divine Lion Figurehead", "Drake's Wing Sails", "Fey-Touched Craftwood", "Imperial Ram", "Ironclad Plating", "Jawhook Ram",
                "Mage-Silk Sails", "Mithral Carpentry Tools", "Multiversal Orrery", "Refurbished Galley", "Silver Unicorn Figurehead", "Spellspun Rigging", "Stardrive Engine", "Voroaxinar's Wrath", "Wavetable Navigator", "Winged Victory Figurehead", "Witchcotton Hammocks"
            };
            int[] upgradeThresholds = {8, 13, 18, 20, 24, 28, 33, 42, 47, 52, 56, 61, 70, 74, 75, 80, 84, 85, 90, 95, 100};
            java.util.LinkedHashSet<String> upgradeSet = new java.util.LinkedHashSet<>();
            while (upgradeSet.size() < upgradeCount) {
                String upgradeStr = stringSelect(roll(100), upgradeThresholds, upgradeTypes);
                upgradeSet.add(upgradeStr);
            }
            upgradeList = String.join(", ", upgradeSet);
        }

        if (specialAmmunition) System.out.println("Special Ammunition: " + specialAmmunitionList);
        if (upgrade) System.out.println("Upgrades: " + upgradeList);

        int stationBonus = intSelect(indexMatch(shipType, shipTypes), new int[] {0, 1, 2, 3, 4, 5}, new int[] {0, 2, 4, 6, 8, 10});
        int filledStations = intSelect(roll(20)+stationBonus, new int[] {4, 14, 20, 24, 27, 29, 30}, new int[] {4, 5, 6, 7, 8, 9, 10});

        String[] officers = {"Captain", "First Mate", "Quartermaster", "Navigator", "Helmsperson", "Boatswain", "Cook", "Master Gunner", "Shipwright", "Surgeon"};
        
        // Replace officers in last 5 positions with blanks based on filledStations
        int officersToRemove = 10 - filledStations;
        String[] stationTypes = {"Boatswain", "Cook", "Master Gunner", "Shipwright", "Surgeon"};
        int[] stationThresholds = {4, 8, 12, 16, 20};
        
        for (int i = 0; i < officersToRemove; i++) {
            String stationToRemove = stringSelect(roll(20), stationThresholds, stationTypes);
            // Find and replace the station in the officers array
            for (int j = 5; j < officers.length; j++) {
                if (officers[j].equals(stationToRemove)) {
                    officers[j] = "";
                    break;
                }
            }
        }
        
        // If filledStations is 4, also remove one of the first five officers
        if (filledStations == 4) {
            String[] firstFiveStations = {"Captain", "First Mate", "Quartermaster", "Navigator", "Helmsperson"};
            int[] firstFiveThresholds = {1, 5, 10, 15, 20};
            String stationToRemove = stringSelect(roll(20), firstFiveThresholds, firstFiveStations);
            // Find and replace the station in the first five positions
            for (int j = 0; j < 5; j++) {
                if (officers[j].equals(stationToRemove)) {
                    officers[j] = "";
                    break;
                }
            }
        }

        int rankedAdjustment = intSelect(roll(20)+stationBonus, new int[] {1, 5, 9, 14, 19, 30}, new int[] {-4, -3, -2, -1, 0, 1});
        int rankedOfficers = filledStations + rankedAdjustment;
        
        // Select ranked officers and assign ranks
        String[] rankedOfficerNames = new String[rankedOfficers];
        int[] rankedOfficerRanks = new int[rankedOfficers];
        int rankedIndex = 0;
        
        // Always select Captain first (if not blank)
        if (!officers[0].equals("")) {
            rankedOfficerNames[rankedIndex] = officers[0];
            rankedOfficerRanks[rankedIndex] = intSelect(roll(20), new int[] {5, 13, 18, 19, 20}, new int[] {1, 2, 3, 4, 5});
            rankedIndex++;
        }
        
        // Randomly select from positions 1-4 (First Mate through Helmsperson)
        java.util.List<Integer> middlePositions = new java.util.ArrayList<>();
        for (int i = 1; i < 5; i++) {
            if (!officers[i].equals("")) {
                middlePositions.add(i);
            }
        }
        java.util.Collections.shuffle(middlePositions);
        for (int pos : middlePositions) {
            if (rankedIndex < rankedOfficers) {
                rankedOfficerNames[rankedIndex] = officers[pos];
                rankedOfficerRanks[rankedIndex] = intSelect(roll(20), new int[] {5, 13, 18, 19, 20}, new int[] {1, 2, 3, 4, 5});
                rankedIndex++;
            }
        }
        
        // Randomly select from positions 5-9 (Boatswain through Surgeon)
        java.util.List<Integer> lastPositions = new java.util.ArrayList<>();
        for (int i = 5; i < 10; i++) {
            if (!officers[i].equals("")) {
                lastPositions.add(i);
            }
        }
        java.util.Collections.shuffle(lastPositions);
        for (int pos : lastPositions) {
            if (rankedIndex < rankedOfficers) {
                rankedOfficerNames[rankedIndex] = officers[pos];
                rankedOfficerRanks[rankedIndex] = intSelect(roll(20), new int[] {5, 13, 18, 19, 20}, new int[] {1, 2, 3, 4, 5});
                rankedIndex++;
            }
        }
        
        // Print ranked officers
        System.out.println("Ranked Officers:");
        for (int i = 0; i < rankedIndex; i++) {
            System.out.print("  " + rankedOfficerNames[i] + ": " + randomChar.generateName());
            System.out.println(" (Rank " + rankedOfficerRanks[i] + ")");
            if (rankedOfficerRanks[i] >= 2) {
                System.out.println("  Boons: " + randomChar.generateBoons(rankedOfficerRanks[i]));
            }
        }
        
        // Print non-ranked officers
        System.out.println("Non-ranked Officers:");
        for (int i = 0; i < officers.length; i++) {
            if (!officers[i].equals("") && !java.util.Arrays.asList(rankedOfficerNames).contains(officers[i])) {
                System.out.println("  " + officers[i] + ": " + randomChar.generateName());
            }
        }
        
        // Print ranked non-officer if applicable
        if (rankedOfficers > filledStations) {
            System.out.println("1 ranked non-officer in the crew");
        }
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

    private static String stringSelect(int num, int[] thresholds, String[] results) {
        for (int i = 0; i < thresholds.length; i++) {
            if (num <= thresholds[i]) {
                return results[i];
            }
        }
        return results[results.length - 1];
    }

    private static Integer intSelect(int num, int[] thresholds, int[] results) {
        for (int i = 0; i < thresholds.length; i++) {
            if (num <= thresholds[i]) {
                return results[i];
            }
        }
        return results[results.length - 1];
    }

    private static Double doubleSelect(int num, int[] thresholds, Double[] results) {
        for (int i = 0; i < thresholds.length; i++) {
            if (num <= thresholds[i]) {
                return results[i];
            }
        }
        return results[results.length - 1];
    }
    
    private static boolean hasEmptySlots(String[] array) {
        for (String item : array) {
            if (item.equals("")) {
                return true;
            }
        }
        return false;
    }
    
    private static int countEmptySlots(String[] array) {
        int count = 0;
        for (String item : array) {
            if (item.equals("")) {
                count++;
            }
        }
        return count;
    }
    

    /*
     * Fills the first count empty slots with the weapon
     * Returns the number of slots filled
     */
    private static int fillSlots(String[] array, String weapon, int count) {
        int filled = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals("")) {
                array[i] = weapon;
                filled++;
            }
        }
        return filled;
    }

    private static String summarizeArray(String[] arr) {
        java.util.Map<String, Integer> counts = new java.util.LinkedHashMap<>();
        for (String s : arr) {
            if (s.equals("")) continue;
            counts.put(s, counts.getOrDefault(s, 0) + 1);
        }
        StringBuilder sb = new StringBuilder();
        for (java.util.Map.Entry<String, Integer> entry : counts.entrySet()) {
            if (sb.length() > 0) sb.append(", ");
            sb.append(entry.getValue()).append("x ").append(entry.getKey());
        }
        return sb.toString();
    }

    private static int indexMatch(String input, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(input)) {
                return i;
            }
        }
        return -1;
    }
}