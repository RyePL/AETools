package generators;

import java.util.ArrayList;

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
            {"Resolute", "Stout", "Frail", "Sickly"},
            {"Razor-Sharp", "Bright", "Dim", "Slow"},
            {"Deepened", "Aware", "Aloof", "Unaware"},
            {"Magnetic", "Charming", "Unpleasant", "Unlikeable"}
        };
        
        String[] secondaryQualities = {
            "Eye patch", "Old", "Stylish", "Scarred", "Tattooed", "Bald", "Long hair", "Bearded", 
            "Clean-shaven", "Pierced", "Wrinkled", "Young", "Tanned", "Pale", "Freckled", 
            "Large", "Slender", "Tall", "Short", "Broad-shouldered", "Limping", "Missing finger"
        };
        
        // Direct mapping for quality index
        int bestQualityIndex = (statMods[bestStatIndex] <= -2) ? 3 : (statMods[bestStatIndex] <= 0) ? 2 : (statMods[bestStatIndex] <= 2) ? 1 : 0;
        int worstQualityIndex = (statMods[worstStatIndex] <= -2) ? 3 : (statMods[worstStatIndex] <= 0) ? 2 : (statMods[worstStatIndex] <= 2) ? 1 : 0;
        
        // Generate 1-3 random secondary qualities
        int numSecondaryQualities = roll(3); // 1-3 random items
        java.util.List<String> selectedSecondaryQualities = new java.util.ArrayList<>();
        java.util.List<Integer> usedIndices = new java.util.ArrayList<>();
        
        for (int i = 0; i < numSecondaryQualities; i++) {
            int index;
            do {
                index = roll(secondaryQualities.length) - 1;
            } while (usedIndices.contains(index));
            usedIndices.add(index);
            selectedSecondaryQualities.add(secondaryQualities[index]);
        }
        
        String secondaryQualitiesString = String.join(", ", selectedSecondaryQualities);
        System.out.println(qualities[bestStatIndex][bestQualityIndex] + ", " + qualities[worstStatIndex][worstQualityIndex] + ", " + secondaryQualitiesString);
        
        String[] classes = {"Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"};
        String[] keyStats = {"STR", "CHA", "WIS", "WIS", "STR", "DEX", "STR", "DEX", "DEX", "CHA", "CHA", "INT"};
        String [] secondaryStats = {"CON", "DEX", "STR", "DEX", "DEX", "WIS", "CHA", "WIS", "INT", "CON", "DEX", "CON"};

        int[] keyStatRatings = new int[12];

        for (int i = 0; i < classes.length; i++) {
            keyStatRatings[i] = statValues[matchIndex(statNames, keyStats[i])];
        }

        // Select character class using weighted random selection with keyStatRatings as weights
        String charClass = weightedRandomSelect(classes, keyStatRatings);

        // Determine level based on stats
        // "classQuality" is the modifier of the key stat for the class, floor 1
        // "reverseQuality" is the modifier of the character's worst stat, ceiling 0
        // level is a d4 for classQuality minus a d4 for reverseQuality
        // for example, a barbarian with STR 18 and a worst stat of 8 has a level of 4d4 - 1d4, total range 1-15
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
                spellSlots[i] = spellSlotReference[level - 1][i];
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
        String features = "";
        String actions = "";
        String bonusActions = "";
        String reactions = "";
        String spells = "";
        String spellDetails = "";
        String cantrips = "";

        // Ability improvements
        int classIndex = matchIndex(classes, charClass);
        if (level >= 4) {
            abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
            updateModifiers(statValues, statMods);
        }
        if (level >= 8) {
            abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
            updateModifiers(statValues, statMods);
        }
        if (level >= 12) {
            abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
            updateModifiers(statValues, statMods);
        }
        if (level >= 16) {
            abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
            updateModifiers(statValues, statMods);
        }

        if (charClass.equals("Barbarian")) {
            // Subclass assignment
            if (subClassIndex == 1) subClass = "Path of the Aether Transformation";
            else subClass = "Path of the Wronged";

            // Initialize rage variables
            int rageDamage = 2;
            String rageDescription = "Rage: Basic rage benefits. ";

            // Ability improvements and stat updates
            
            if (level >= 20) {
                statValues[0] += 4;
                statValues[2] += 4;
                updateModifiers(statValues, statMods);
            }

            // Rage damage increases
            if (level >= 4) rageDamage++;
            if (level >= 9) rageDamage++;
            if (level >= 16) rageDamage++;

            // Core features (no subclass branching)
            if (level >= 2) {
                features += "Danger Sense: Can't be surprised, advantage on DEX saves.\n";
                features += "Reckless Attack: Can gain advantage on attacks but suffers advantage on hits until next turn.\n";
            }
            if (level >= 5) {
                features += "Multiattack: Can attack twice.\n";
            }
            if (level >= 11) {
                features += "Relentless Rage: On hitting 0 HP while raging, on a DC 10 CON save, regain " + (level * 2) + " HP. Save increases by 5 each time.\n";
            }
            if (level >= 13) {
                features += "Improved Brutal Strike: Can also give the target save disadvantage or -5 AC on next attack.\n";
            }
            if (level >= 15) {
                features += "Persistent Rage: Regain rage uses on initiative once per day. Rage does not need extending.\n";
            }
            if (level >= 17) {
                features += "Improved Brutal Strike: Now deals 2d10 bonus damage and can choose two features.\n";
            }
            if (level >= 18) {
                features += "Indomitable Might: STR is floor for STR checks and saves.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Choose one that is appropriate.\n";
            }

            // Rage description modifications (core and subclass)
            if (level >= 7) {
                rageDescription += "Move up to half speed on activation.\n";
            }
            if (level >= 3) {
                if (subClassIndex == 1) {
                    rageDescription += "Immune to poison and force. Immune to poisoned condition. Gain d10 claw weapon. ";
                }
            }
            if (level >= 6) {
                if (subClassIndex == 1) {
                    rageDescription += "Can choose between +1d6 poison damage, +2 AC, or magic resistance. ";
                }
            }
            if (level >= 14) {
                if (subClassIndex == 1) {
                    rageDescription += "Rage claws are now 2d12 weapons. Can use two features form Deep-Infused Rage.\n";
                }
            }

            // Subclass-specific features
            if (level >= 6) {
                if (subClassIndex != 1) {
                    features += "On a saving throw, gain a grudge die. Expend the grudge die to add 1d6 to an attack roll.\n";
                }
            }
            if (level >= 10) {
                if (subClassIndex == 1) {
                    features += "Call of the Aether: Immune to aether poisoning. Has full swim speed. Can cast detect magic.\n";
                }
            }

            // Subclass-specific reactions
            if (level >= 3) {
                if (subClassIndex != 1) {
                    reactions += "Outrage: On damage while raging, gain " + (level/2) + " temp HP and move half speed to the target.\n";
                    reactions += "Outrage: On damage, rage.\n";
                }
            }
            if (level >= 10) {
                if (subClassIndex != 1) {
                    reactions += "Defiant Outburst: Once per day on hitting 0 HP, grant self and allies 1d12 HP.\n";
                }
            }
            if (level >= 14) {
                if (subClassIndex != 1) {
                    reactions += "Suffer No Insult: On damage or saving throw, gain advantage, fright immunity, and reaction attacks on the attacker.\n";
                }
            }

            // Final calculations
            bonusActions = rageDescription + "\n";
            hitPoints = 12 + ((level - 1) * (7 + statMods[2]));
            armorClass = 10 + statMods[1] + statMods[2];
            actions += "Greataxe: +" + (statMods[0] + proficiencyBonus) + " to hit, 1d12 + " + (statMods[0] + rageDamage) + " slashing\n";
        }
        else if (charClass.equals("Bard")) {
            if (subClassIndex == 1) subClass = "College of the Blade Dancer";
            else subClass = "College of Shantyfolk";
            String[] possibleCantrips = {"Vicious Mockery", "Minor Illusion", "Blade Ward", "Friends"};
            String[] possibleSpells = {"Bless", "Bane", "Dissonant Whispers", "Sleep", "Color Spray",
                                        "Cloud of Daggers", "Heat Metal",
                                        "Siren's Call", "Galvanizing Words", "Aetheric Communion",
                                        "Dimension Door", "Polymorph",
                                        "Awaken", "Mass Cure Wounds", "Planar Binding",
                                        "Guards and Wards",
                                        "Mirage Arcane",
                                        "Befuddlement",
                                        "Mass Dominate Person",
                                        "Seeming", "Irresistable Dance", "Regenerate"};
            
            // Features
            if (level >= 19) features += "Epic Boon: Choose an appropriate boon.\nWords of Creation: Can cast Power Word Heal and Power Word Kill and dual-target within 10 ft.\n";

            // Actions (none in table)
            // Bonus Actions
            String bardicDie = "d6";
            if (level >= 5) bardicDie = "d8";
            if (level >= 10) bardicDie = "d10";
            if (level >= 15) bardicDie = "d12";
            bonusActions += "Bardic Inspiration: Grant an ally a " + bardicDie + " to apply to a failed d20 roll.\n";
            
            // Reactions
            if (level >= 7) reactions += "Countercharm: When an ally fails a mental save, force a reroll.\n";

            // Subclass features
            if (level >= 3) {
                if (subClassIndex == 1) {
                    features += "Spinning Blades: Gain two-weapon fighting. Light finesse weapons gain the thrown property and return on throw.\n";
                    features += "Piercing Troubador: Learn the resonant blades cantrip.\n";
                }
                else {
                    features += "Chant of Glory: Allies with bardic inspiration gain CHA bonus to athletics and acrobatics. Max or min bardic rolls restore one use.\n";
                    features += "Chant of Success: Short resting with an ally gives them a bardic die.\n";
                }
            }
            if (level >= 6) {
                if (subClassIndex == 1) {
                    features += "Swift to Act: Can spend a bardic die on initiative. Gains advantage on first d20 roll of combat.\n";
                }
                else {
                    features += "Chant of Hope: Final bardic die always gives the highest result.\n";
                }
            }
            if (level >= 14) {
                if (subClassIndex == 1) {
                    bonusActions += "Cacophonous Song: Once per rest, after a throwing attack, deal 8d6 thunder damage (CON save) and deafen in 15 ft around target.\n";
                    features += "Transcendent Vocals: Performance checks involving singing floor at 10.\n";
                }
                else {
                    features += "Heartstrings: Inspiration uses increase by half proficiency. Unexpended bardic die are regained. Allies can expend bardic die to succeed on a death save.\n";
                }
            }

            hitPoints = 8 + ((level - 1) * (5 + statMods[2]));
            armorClass = 12 + statMods[1];
            if (subClassIndex != 1) armorClass = 10 + statMods[1] + statMods[5];

            // Cantrip progression
            int cantripMax = 2;
            if (level >= 4) cantripMax++;
            if (level >= 10) cantripMax++;
            for (int i = 0; i < cantripMax; i++) {
                cantrips += possibleCantrips[i];
                if (i < cantripMax - 1) cantrips += ", ";
            }

            // Bard prepared spells progression
            int[] bardPreparedSpellsByLevel = {0,4,5,6,7,9,10,11,12,14,15,16,16,17,17,18,18,19,20,21,22};
            int preparedSpells = bardPreparedSpellsByLevel[Math.min(level, 20)];
            // Select the first preparedSpells from possibleSpells in order
            java.util.List<String> spellList = new java.util.ArrayList<>();
            for (int i = 0; i < Math.min(preparedSpells, possibleSpells.length); i++) {
                spellList.add(possibleSpells[i]);
            }
            spells = String.join(", ", spellList);
            spellDetails = "Spell Attack: +" + (statMods[5] + proficiencyBonus) + ", Spell DC: " + (8 + statMods[5] + proficiencyBonus) + "\n";
            
        }
        else if (charClass.equals("Cleric")) {
            if (subClassIndex == 1) subClass = "Aether Domain";
            else subClass = "Tyranny Domain";
            int channelDivinityUses = 0;
            int divSparkDie = 0;
            String[] possibleCantrips = {"Thaumaturgy", "Light", "Sacred Flame", "Toll the Dead", "Spare the Dying "};
            String[] possibleSpells = {"Cure Wounds", "Healing Word", "Bless", "Bane", "Sanctuary",
                                        "Hold Person", "Enhance Ability",
                                        "Chronovoyance", "Galvanizing Words", "Aetheric Communion",
                                        "Death Ward", "Divination",
                                        "Soul Tide", "Mass Cure Wounds", "Raise Dead",
                                        "Harm",
                                        "Plane Shift",
                                        "Antimagic Field",
                                        "Gate",
                                        "Planar Binding", "True Seeing",  "Symbol"};

            String[] domainSpells = {};
            if (subClassIndex == 1) {
                domainSpells = new String[] {"Faerie Fire", "Unseen Servant", "Lesser Restoration", "Misty Step",
                                             "Hypnotic Pattern", "Spirit Guardians", "Conjure Aether Mephits", "Wall of Aether",
                                             "Conjure Aether Elementals", "Legend Lore"};
            }
            else {
                domainSpells = new String[] {"Charm Person", "Comprehend Languages", "Detect Thoughts", "Enthrall",
                                             "Fear", "Tongues", "Compulsion", "Dominate Beast", "Dominate Person", "Modify Memory"};
            }
            int numDomainSpells = 0;
            if (level >= 3) numDomainSpells += 4;
            if (level >= 5) numDomainSpells += 2;
            if (level >= 7) numDomainSpells += 2;
            if (level >= 9) numDomainSpells += 2;

            

            // Cantrip progression
            int cantripMax = 3;
            if (level >= 4) cantripMax++;
            if (level >= 10) cantripMax++;
            for (int i = 0; i < cantripMax; i++) {
                cantrips += possibleCantrips[i];
                if (i < cantripMax - 1) cantrips += ", ";
            }

            // Set Cleric resource values based on level
            if (level >= 18) {
                channelDivinityUses = 4;
                divSparkDie = 4;
            } else if (level >= 13) {
                channelDivinityUses = 3;
                divSparkDie = 3;
            } else if (level >= 7) {
                channelDivinityUses = 3;
                divSparkDie = 2;
            } else if (level >= 1) {
                channelDivinityUses = 2;
                divSparkDie = 1;
            }

            if (level >= 2) {
                features += "Channel Divinity: " + channelDivinityUses + " uses per day\n";
                actions += "Channel Divinity Divine Spark: Roll " + divSparkDie + "d8 and either heal or deal radiant or necrotic damage on a CON save.\n";
            }

            // Cleric feature progression
            if (level >= 1) {
                features += "Thaumaturge: Choose an extra cantrip\n";
            }
            if (level >= 6) {
                features += "Potent Spellcasting: Cantrips add WIS.\n";
            }
            if (level >= 10) {
                features += "Divine Intervention: Cast a level 5 or lower spell for free.\n";
            }
            if (level >= 13) {
                features += "Blessed Spellcasting: give double WIS THP on cantrip damage.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Choose an appropriate Epic Boon\n";
            }
            if (level == 20) {
                features += "Greater Divine Intervention: Can cast Wish with DI.\n";
            }

            // Cleric subclass features
            if (level >= 3) {
                if (subClassIndex == 1) {
                    features += "Aetheric Resilience: Gain the aether shroud cantrip. Can float in aether.\n";
                    reactions += "Surge of the Starlight Sea: Give a creature advantage on a spell save.\n";
                    actions += "Channel Divinity Aetherial Shroud: Target makes a DEX save against 2d6 + " + level + " force damage and restraint.\n";
                }
                else {
                    features += "Oppressive Smite: Creatures hit with weapon attacks can't take reactions.\n";
                    actions += "Channel Divinity Bonds of Tyranny: WIS creatures make a WIS throw or become incapacitated for 1 turn.\n";
                }
            }
            if (level >= 6) {
                if (subClassIndex == 1) {
                    actions += "Channel Divinity Manifest Consciousness: Cast Spirit Guardians that doubles as Detect Thoughts.\n";
                }
                else {
                    features += "Adamantine Mind: Resistant to psychic damage and immune to charm effects.\n";
                }
            }
            if (level >= 17) {
                if (subClassIndex == 1) {
                    features += "Limitless Surge: Surge of the Starlight Sea can be used three times per day.\n";
                }
                else {
                    features += "Inescapable Subjugation: Gain the Mass Dominate Person spell. Enemies have disadvantage on charm saves.\n";
                }
            }

            // Cleric prepared spells progression
            int[] clericPreparedSpellsByLevel = {0,4,5,6,7,9,10,11,12,14,15,16,16,17,17,18,18,19,20,21,22};
            int preparedSpells = clericPreparedSpellsByLevel[Math.min(level, 20)];
            // Select the first preparedSpells from possibleSpells in order
            java.util.List<String> spellList = new java.util.ArrayList<>();
            for (int i = 0; i < Math.min(preparedSpells, possibleSpells.length); i++) {
                spellList.add(possibleSpells[i]);
            }
            for (int i = 0; i < numDomainSpells; i++) {
                spellList.add(domainSpells[roll(domainSpells.length) - 1]);
            }
            spells = String.join(", ", spellList);
            spellDetails = "Spell Attack: +" + (statMods[4] + proficiencyBonus) + ", Spell DC: " + (8 + statMods[4] + proficiencyBonus) + "\n";
            
            // Hit Points and Armor Class
            hitPoints = 8 + ((level - 1) * (5 + statMods[2]));
            armorClass = 14 + Math.max(statMods[1], 2);
        }
        else if (charClass.equals("Druid")) {
            if (subClassIndex == 1) subClass = "Circle of the Feathered Stars";
            else subClass = "Circle of the Elements";
            String[] possibleCantrips = {"Peal of Thunder", "Druidcraft", "Primal Savagery", "Message"};
            String[] possibleSpells = {"Primal Squall", "Entangle", "Thunderwave", "Cure Wounds", "Detect Magic",
                                        "Aetheric Adaptation", "Summon Primordial Minion",
                                        "Conjure Aether Mephits", "Wall of Aether", "Sleet Storm",
                                        "Conjure Aether Elemental", "Fire Shield",
                                        "Soul Tide", "Maelstrom", "Insect Plague",
                                        "Hive Mind",
                                        "Create Elemental Portal",
                                        "Animal Shapes",
                                        "Foresight",
                                        "Contagion", "Investiture of Flame",  "Regenerate"};

            int wildShapeUses = 0;

            String[] featheredStarsSpells = {"Darkness", "Lesser Restoration", "Aetheric Communion", "Tongues",
                                            "Control Water", "Freedom of Movement", "Greater Restoration", "Telepathic Bond"};
            int numFeatheredStarsSpells = 0;
            if (level >= 3) numFeatheredStarsSpells += 2;
            if (level >= 5) numFeatheredStarsSpells += 2;
            if (level >= 7) numFeatheredStarsSpells += 2;
            if (level >= 9) numFeatheredStarsSpells += 2;

            // Druid progression table logic (grouped by variable)
            // wildShapeUses progression
            if (level >= 17) {
                wildShapeUses = 4;
            } else if (level >= 6) {
                wildShapeUses = 3;
            } else if (level >= 1) {
                wildShapeUses = 2;
            }

            // features progression
            if (level >= 1) {
                features += "Magician: Gain a bonus cantrip and add WIS to Arcana and Nature checks.\n";
            }
            if (level >= 4) {
                features += "Wild Resurgence: Can exchange spell slots for wildshape uses. Can exchange wildshape use for level 1 slot once per day.\n";
            }
            if (level >= 6) {
                features += "Elemental Fury: Add WIS to cantrips.\n";
            }
            if (level >= 15) {
                features += "Improved Elemental Fury: Cantrip range extended by 300 ft.\n";
            }
            if (level >= 17) {
                features += "Beast Spells: Can cast in beast form.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Gain an appropriate Epic Boon.\n";
            }
            if (level >= 20) {
                features += "Archdruid: Gain a wildshape use on initiative. Can convert wildshape uses into 2 levels of spell slots.\n";
            }

            // actions progression
            if (level >= 1) {
                actions += "Wild Shape (" + wildShapeUses + "/day): Change into a beast form\n";
            }

            // Druid subclass-specific features (Circle of the Elements)
            if (subClassIndex == 1) {
                if (level >= 2) {
                    bonusActions += "Power of the Ancient Guardians: Expend a wildshape use to raise AC to 16, gain blindsight and swim speed, and add 1d4 radiant to all attacks.\n";
                }
                if (level >= 5) {
                    features += "Mythical Aspect: Gain nonmagical physical resistance while PotAG is active. PotAG AC to 17 and damage to 1d6.\n";
                }
                if (level >= 9) {
                    reactions += "Sheltering Arms: Give disadvantage on an attack roll against a nearby ally. In PotAG form, ally also has resistance.\n";
                }
                if (level >= 10) {
                    features += "PotAG AC to 18 and damage to 1d8.\n";
                }
                if (level >= 13) {
                    actions += "Radiant Wrath: once per rest, enemies within 60 ft. make a CON throw against 4d6 radiant and blind.\n";
                }
                if (level >= 14) {
                    features += "PotAG AC to 19 and damage to 1d10.\n";
                }
            }
            else {
                if (level >= 1) {
                    features += "Voice of the Planes: Add an elemental or aether cantrip.\n";
                }
                if (level >= 2) {
                    actions += "Planar Conduit: Spend a wildshape use to deal 2d6 acid, cold, fire, lightning, or thunder damage in a 30 ft line. Can walk through it to teleport up to 90 ft., ending it.\n";
                    bonusActions += "Realign Conduit: When Planar Conduit is active, make a ranged spell attack for 1d6 + WIS.\n";
                }
                if (level >= 5) {
                    reactions += "Elemental Erosion: Up to WIS times, force a reroll on an elemental save.\n";
                }
                if (level >= 9) {
                    bonusActions += "Rolling Fog: Obscuring fog pours form portal, creatures choke on CON save. Maintain on bonus action.\n";
                }
                if (level >= 14) {
                    features += "Amplified Aether: Elemental cantrips deal max damage. Once per rest, elemental spells reroll 1s and 2s.\n";
                }
            }

            // Cantrip progression
            int cantripMax = 2;
            if (level >= 4) cantripMax++;
            if (level >= 10) cantripMax++;
            for (int i = 0; i < cantripMax; i++) {
                cantrips += possibleCantrips[i];
                if (i < cantripMax - 1) cantrips += ", ";
            }

            // Druid prepared spells progression
            int[] druidPreparedSpellsByLevel = {0,4,5,6,7,9,10,11,12,14,15,16,16,17,17,18,18,19,20,21,22};
            int preparedSpells = druidPreparedSpellsByLevel[Math.min(level, 20)];
            // Select the first preparedSpells from possibleSpells in order
            java.util.List<String> spellList = new java.util.ArrayList<>();
            for (int i = 0; i < Math.min(preparedSpells, possibleSpells.length); i++) {
                spellList.add(possibleSpells[i]);
            }
            for (int i = 0; i < numFeatheredStarsSpells; i++) {
                spellList.add(featheredStarsSpells[roll(featheredStarsSpells.length) - 1]);
            }
            spells = String.join(", ", spellList);
            spellDetails = "Spell Attack: +" + (statMods[4] + proficiencyBonus) + ", Spell DC: " + (8 + statMods[4] + proficiencyBonus) + "\n";
            
            // Hit Points and Armor Class
            hitPoints = 8 + ((level - 1) * (5 + statMods[2]));
            armorClass = 12 + Math.max(statMods[1], 2);
        }
        else if (charClass.equals("Fighter")) {
            if (subClassIndex == 1) subClass = "Battle Commander";
            else subClass = "Corsair";
            int secondWindUses = 2;
            int attacks = 1;

            // --- secondWindUses ---
            if (level >= 1) secondWindUses = 2;
            if (level >= 9) secondWindUses = 3;
            if (level >= 17) secondWindUses = 4;

            // --- attacks ---
            if (level >= 5) attacks = 2;
            if (level >= 11) attacks = 3;
            if (level >= 20) attacks = 4;

            // --- extra ability improvements ---
            if (level >= 6) {
                abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
                updateModifiers(statValues, statMods);
            }
            if (level >= 14) {
                abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
                updateModifiers(statValues, statMods);
            }

            // --- features ---
            if (level >= 2) features += "Action Surge: Gain an extra action.\n";
            if (level >= 4) features += "Tactical Shift: On SW, move half speed without opportunity attacks.\n";
            if (level >= 9) features += "Indomitable: Reroll a save with +level.\n";
            if (level >= 13) features += "Extra Indomitable use.\n";
            if (level >= 13) features += "Studied Attacks: On a miss, gain advantage on the next attack.\n";
            if (level >= 17) features += "Extra Action Surge and Indomitable use.\n";
            if (level >= 19) features += "Epic Boon: Choose an appropriate Epic Boon.\n";

            // --- bonusActions ---
            if (level >= 1) bonusActions += "Second Wind (" + secondWindUses + "/day): Gain 1d10 + " + level + " hit points.\n";

            // --- reactions ---
            if (level >= 2) reactions += "Tactical Mind: Expend SW to add 1d10 to skill check.\n";

            // --- basic actions ---
            if (attacks > 1) actions += "Multiattack: Make " + attacks + " attacks.\n";
            actions += "Longsword: +" + statMods[0] + " to hit, 1d8 + " + statMods[0] + " damage.\n";

            // Battle Commander subclass table features, actions, bonusActions, and reactions
            if (subClassIndex == 1) {
                // --- features ---
                if (level >= 3) features += "Direct the Attack: Swap initiative with an ally and give them one attack advantage.\n";
                if (level >= 3) actions += "Defensive Formation: Give self and allies physical resistance.\n";
                if (level >= 3) bonusActions += "Direct the Assault: Ally makes a melee attack.\n";
                if (level >= 3) reactions += "Fall back: When an ally takes damage, they move half speed without opportunity attacks.\n";
                if (level >= 7) features += "Inspiring Presence: Give allies + " + proficiencyBonus + " to WIS, INT, and CHA throws for 1 minute.\n";
                if (level >= 10) features += "Always at the Ready: Allies have initiative advantage.\n";
                if (level >= 15) features += "Focused Assault: On a hit, allies within 10 ft. have advantage against the target until next turn.\n";
                if (level >= 18) features += "Sudden Assault: Once per rest, forgo an attack to command allies to use their reaction to attack with advantage an enemy within reach, critting on 15+.\n";
            }
            else {
                // --- features ---
                if (level >= 3) features += "Reckoning Force: Attack frightened enemies with advantage. Gain advantage against fright effects.\n";
                if (level >= 3) actions += "Dreadful Shroud: Enemies within 15 ft make a DC " + (8 + statMods[0] + proficiencyBonus) + " INT throw or are frightened for 1 minute. " + statMods[0] + " uses.\n";
                if (level >= 3) bonusActions += "Demoralize: Make an intimidation check with a STR bonus against a CHA throw. On a failure, the target is frightened until the end of the next turn.\n";
                if (level >= 6) features += "Make Way: Opportunity attacks have disadvantage. Second Wind has an additional use.\n";
                if (level >= 9) features += "Unyielding Resolve: Immune to fright. Frightened creatures take +prof damage.\n";
                if (level >= 14) features += "Ruthless Determination: Can choose to automatically succeed with Indomitable.\n";
                if (level >= 18) reactions += "Give No Quarter: On a killing blow or crit, gain 20 THP, move half speed, and attack.\n";
            }

            // --- hit points ---
            hitPoints = 10 + ((level - 1) * (6 + statMods[2]));
            armorClass = 17;
            if (level >= 5) armorClass += 2;
            if (level >= 10) armorClass += 2;
        }
        else if (charClass.equals("Monk")) {
            if (subClassIndex == 1) subClass = "Way of the Ascetic";
            else subClass = "Way of the Conduit";
            int focusPoints = 0;
            String martialArts = "d6";
            int unarmoredMovement = 0;

            if (level > 1) focusPoints = level;

            if (level >= 5) martialArts = "d8";
            if (level >= 11) martialArts = "d10";
            if (level >= 17) martialArts = "d12";

            if (level >= 2) unarmoredMovement += 10;
            if (level >= 6) unarmoredMovement += 5;
            if (level >= 10) unarmoredMovement += 5;
            if (level >= 14) unarmoredMovement += 5;
            if (level >= 18) unarmoredMovement += 5;

            // --- martial arts ---
            if ((level >= 2) && (level < 10)) {
                bonusActions += "Flurry of Blows: Spend a focus point to make two unarmed attacks.\n";
                bonusActions += "Patient Defense: Disengage. Can spend a focus point to also Dodge.\n";
                bonusActions += "Step of the Wind: Dash. Can spend a focus point to also Disengage.\n";
            }
            else if (level >= 10) {
                bonusActions += "Flurry of Blows: Spend a focus point to make two unarmed attacks, or two points to make three.\n";
                bonusActions += "Patient Defense: Disengage. Can spend a focus point to also Dodge and gain 2 " + martialArts + " temp HP.\n";
                bonusActions += "Step of the Wind: Dash. Can spend a focus point to also Disengage and move a willing creature with you.\n";
            }

            // Monk class table features and bonusActions
            // --- features ---
            if (level >= 1) features += focusPoints + " focus points, recharge on short rest.\n";
            if (level >= 1) features += "Uncanny Metabolism: Once per day, regain all focus on initiative and ? Hit points.\n";
            if (level >= 4) features += "Slow Fall: Reduce fall damage by 5x level.\n";
            if (level >= 5) features += "Stunning Strike: On a hit, expend a focus point and target makes a CON save. Stunned on fail, half speed and one advantage attack on success.\n";
            if (level >= 6) features += "Empowered Strikes: Strikes can deal force damage.\n";
            if (level >= 7) features += "Evasion: Take no damage on successful DEX saves and half on failures.\n";
            if (level >= 9) features += "Acrobatic Movement: Can run up walls and across liquids.\n";
            if (level >= 10) features += "Self-Restoration: Can end one charmed, frightened, or poisoned condition at the end of a turn.\n";
            if (level >= 13) features += "Deflect Energy: Deflect Attacks works on any damage type.\n";
            if (level >= 14) features += "Disciplined Survivor: Grant proficiency on all saves. Can spend a focus point to reroll a save.\n";
            if (level >= 15) features += "Perfect Focus: If Uncanny Metabolism is used, set focus to 4.\n";
            if (level >= 18) features += "Superior Defense: Spend 3 focus to gain resistance to all damage except force.\n";
            if (level >= 19) features += "Epic Boon: Choose an appropriate Epic Boon.\n";

            // --- actions ---
            if (level >= 5) actions += "Multiattack: Make two attacks.\n";
            actions += "Unarmed Strike: +" + statMods[1] + " to hit, 1" + martialArts + " + " + statMods[1] + " damage.\n";

            // --- bonusActions ---
            if (level >= 1) bonusActions += "Martial Arts: Make an unarmed attack.\n";

            // --- reactions ---
            if (level >= 3) reactions += "Deflect Attacks: Reduce a physical attack by 1d10 + DEX + level. If it hits 0, can spend a focus point to deal 2d? Damage on a DEX save.\n";

            // Way of the Ascetic subclass table features, actions, bonusActions, and reactions
            if (subClassIndex == 1) {
                if (level >= 3) {
                    features += "Fragile Objects: Can expend a focus point to ignore an object's resistances on hit.\n";
                    features += "Construct Bane: Can expend a focus point on hitting a construct to strike a critical hit.\n";
                    reactions += "Ignore the Blade: Reduce physical damage by " + proficiencyBonus + ".\n";
                }
                if (level >= 6) actions += "Value in Utility: Spend a focus point to convert a weapon to wood and glass, or two points for a magical weapon.\n";
                if (level >= 11) bonusActions += "Insubstantial Matter: Spend a focus point to gain physical resistance and pass through solid matter for a turn.\n";
                if (level >= 17) {
                    bonusActions += "Polymorph: Spend 4 focus points to cast polymorph, mainting each round for one focus point.\n";
                    features += "There is no Spoon: Fragile Objects does not cost focus.\n";
                    features += "Repurpose Energy: Regain " + proficiencyBonus + " focus when destroying a construct.\n";
                }
            // Way of the Conduit subclass table features, actions, bonusActions, and reactions
            }
            else {
                if (level >= 3) {
                    bonusActions += "Asteth's Brand: Spend a focus point to gain cold and fire resistance, and ignore environmental exhaustion.\n";
                    bonusActions += "Cyrill's Guilt: Spend a focus point to take on another creature's negative condition.\n";
                }
                if (level >= 6) {
                    features += "Night's Flame: Spend 1-3 focus on hit to deal 2d6 fire damage per point spent. This damage repeats next turn unless the enemy spends their reaction.\n";
                    features += "Starry Zephyr: Spend 1-3 focus on hit to push 10 ft. per point spent and knock prone, dealing 1d6 damage per 10 ft. moved if an object or creature is struck.\n";
                }
                if (level >= 11) {
                    features += "Guidance of the Stars: On Night's Flame, the target makes a WIS save or is incapacitated on their turn. On Starry Zephyr, the target makes a WIS save or is restrained on their turn.\n";
                }
                if (level >= 17) {
                    features += "Astral Form: On Night's Flame or Starry Zephyr, gain one elemental immunity for 1 minute, 60 ft. truesight, fly speed, and opportunity attack immunity. Recharges on rest.\n";
                }
            }
            
            // Hit Points and Armor Class
            hitPoints = 8 + ((level - 1) * (5 + statMods[2]));
            armorClass = 10 + statMods[1] + statMods[4];
        }
        else if (charClass.equals("Paladin")) {
            if (subClassIndex == 1) subClass = "Oath of the Sovereign";
            else subClass = "Oath of the Drakes";

            String[] possibleSpells = {"Divine Smite", "Shield of Faith", "Detect Magic", "Divine Favor",
            "Zone of Truth", "Magic Weapon",
            "Galvanizing Words", "Revivify", "Crusader's Mantle",
            "Death Ward", "Find Greater Steed",
            "Greater Restoration", "Banishing Smite", "Destructive Wave"};

            String[] oathSpells;
            int channelDivinityUses = 0;

            int numOathSpells = 0;
            if (level >= 3) numOathSpells += 2;
            if (level >= 5) numOathSpells += 2;
            if (level >= 9) numOathSpells += 2;
            if (level >= 13) numOathSpells += 2;
            if (level >= 17) numOathSpells += 2;

            // Basic attack
            actions += "Mace: + " + statMods[0] + " to hit, 1d8 + " + statMods[0] + " damage.\n";

            // Level-based Paladin features, actions, and bonusActions
            // Group 1: channelDivinityUses changes
            if (level >= 2) {
                channelDivinityUses = 2;
            }
            if (level >= 11) {
                channelDivinityUses += 1;
            }
            
            // Group 2: features changes
            if (level >= 2) {
                features += "Blessed Warrior: Can cast Guidance and Spare the Dying at will.\n";
            }
            if (level >= 3) {
                features += "Channel Divinity uses: " + channelDivinityUses + " per day\n";
            }
            if (level >= 6) {
                features += "Aura of Protection: Allies within 10 ft. have a saving throw bonus of +CHA.\n";
            }
            if (level >= 9) {
                features += "Aura of Courage: Allies in the Aura of Protection are immune to fear.\n";
            }
            if (level >= 10) {
                features += "Radiant Strikes: Attacks 3 deal +1d8 radiant damage.\n";
            }
            if (level >= 13) {
                features += "Restoring Touch: Lay on Hands can remove most conditions.\n";
            }
            if (level >= 18) {
                features += "Aura Expansion: Aura of Protection extends 30 ft.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Choose an appropriate epic boon.\n";
            }
            
            // Group 3: actions changes
            if (level >= 5) {
                actions += "Multiattack: Attack twice.\n";
            }
            if (level >= 9) {
                actions += "Abjure Foes (CD): CHA creatures within 60 ft. make a WIS save or become frightened for 1 minute and must choose one between moving, action, and bonus action.\n";
            }
            
            // Group 4: bonusActions changes
            if (level >= 1) {
                bonusActions += "Lay on Hands: Restore hit points from a pool of 5 x level.\n";
            }
            if (level >= 2) {
                bonusActions += "Paladin's Smite (1/day): Cast Divine Smite without a spell slot.\n";
                bonusActions += "Divine Sense (CD): Sense celestials, fiends, and undead for 10 minutes.\n";
            }

            // Oath of the Sovereign subclass table features, actions, bonusActions, and reactions
            if (subClassIndex == 1) {
                oathSpells = new String[] {"Command", "Unseen Servant", "Find Steed", "Flame Blade",
                                            "Magic Circle", "Protection from Energy", "Guardian of Faith", "Resilient Sphere",
                                            "Geas", "Wall of Force"};

                // Group 1: features changes
                if (level >= 7) {
                    features += "Exemplar of the Realm: Charisma checks floor at 10. Allies in the Aura of Protection reduce physical 7 damage by CHA.\n";
                }
                if (level >= 15) {
                    features += "Glorious Strikes: Can reduce Divine Smite damage by 1d8 to deal 2d8 radiant and fright (CHA save) to enemies within 10 ft.\n";
                }
                
                // Group 2: actions changes
                if (level >= 3) {
                    actions += "Marshalling Order (CD): Choose up to CHA creatures within 30 ft. They can use their reaction to move up to their speed without opportunity attacks.\n";
                }
                
                // Group 3: reactions changes
                if (level >= 3) {
                    reactions += "Shield of the Realm (CD): When a creature within 30 ft. takes damage, give resistance then temporary HP equal to level.\n";
                }
                if (level >= 20) {
                    reactions += "Sovereign Mastery (1/day): For 1 minute, enemies ending their turn within 10 ft. make a CHA throw or become stunned.\n";
                }
            }
            else {
                oathSpells = new String[] {"Disguise Self", "Expeditious Retreat", "Arcanist's Magic Aura", "Alter Self",
                                           "Galvanizing Words", "Nondetection", "Aetheric Bedlam", "Greater Invisiblity",
                                           "Soul Tide", "Passwall"};

                // Group 1: features changes
                if (level >= 3) {
                    features += "Mariner's Mastery: Gain Deception proficiency. Divine sense can detect humanoids.\n";
                }
                if (level >= 6) {
                    features += "Aura of Unbound Steps: Allies in the Aura of Protection are unaffected by difficult terrain and their speed cannot be reduced by magic.\n";
                }
                
                // Group 2: actions changes
                if (level >= 14) {
                    actions += "Chainbreaker (CHA/day): Remove all physical and magical restraints on a creature.\n";
                }
                
                // Group 3: bonusActions changes
                if (level >= 3) {
                    bonusActions += "Veil of the Phantom Sea (CD): Up to CHA creatures within 30 ft. gain a +5 bonus to stealth checks and DEX saves.\n";
                }
                if (level >= 20) {
                    bonusActions += "Spirit of the Unfettered (1/day): For 1 minute, increase speed by 30, ignore opportunity attacks, immune to charm, and can hide or dodge as a bonus action.\n";
                }
            }
            // Paladin prepared spells progression
            int[] paladinPreparedSpellsByLevel = {2,3,4,5,6,6,7,7,9,9,10,10,11,11,12,12,14,14,15,15};
            int preparedSpells = paladinPreparedSpellsByLevel[Math.min(level, 20)];
            // Select the first preparedSpells from possibleSpells in order
            java.util.List<String> spellList = new java.util.ArrayList<>();
            for (int i = 0; i < Math.min(preparedSpells, possibleSpells.length); i++) {
                spellList.add(possibleSpells[i]);
            }
            for (int i = 0; i < numOathSpells; i++) {
                spellList.add(oathSpells[roll(oathSpells.length) - 1]);
            }
            spells = String.join(", ", spellList);
            spellDetails = "Spell Attack: +" + (statMods[5] + proficiencyBonus) + ", Spell DC: " + (8 + statMods[5] + proficiencyBonus) + "\n";
            
            // Hit Points and Armor Class
            hitPoints = 10 + ((level - 1) * (6 + statMods[2]));
            armorClass = 16;
            if (level >= 5) armorClass += 2;
            if (level >= 8) armorClass += 2;
        }
        else if (charClass.equals("Ranger")) {
            if (subClassIndex == 1) subClass = "Aether Prowler";
            else subClass = "Expanse Wayfinder";

            String[] possibleSpells = {"Hunter's Mark", "Alarm", "Cure Wounds", "Ensnaring Strike",
            "Cordon of Arrows", "Aetheric Adaptation",
            "Flame Arrows", "Elemental Adaptation", "Speak with Plants",
            "Freedom of Movement", "Grasping Vine",
            "Steel Wind Strike", "Conjure Volley", "Wrath of Nature"};

            int favoredEnemyUses = 2;
            String[] wardenSpells;
            int numWardenSpells = 0;
            if (level >= 3) numWardenSpells += 1;
            if (level >= 5) numWardenSpells += 1;
            if (level >= 9) numWardenSpells += 1;
            if (level >= 13) numWardenSpells += 1;
            if (level >= 17) numWardenSpells += 1;

            if (level >= 5) favoredEnemyUses += 1;
            if (level >= 9) favoredEnemyUses += 1;
            if (level >= 13) favoredEnemyUses += 1;
            if (level >= 17) favoredEnemyUses += 1;
            bonusActions += "Favored Enemy (" + favoredEnemyUses + "/day): Cast Hunter's Mark without a spell slot.\n";

            // Features progression
            if (level >= 2) {
                features += "Druidic Warrior: Learn the Mending and Gust cantrips.\n";
            }
            if (level >= 6) {
                features += "Roving: Increase speed by 10 ft., gain equal climb and swim speed.\n";
            }
            if (level >= 13) {
                features += "Relentless Hunter: Hunter's Mark ignores concentration saves.\n";
            }
            if (level >= 17) {
                features += "Precise Hunter: Advantage against enemies targeted by Hunter's Mark.\n";
            }
            if (level >= 18) {
                features += "Feral Senses: Blindsight up to 30 ft.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Choose an appropriate epic boon.\n";
            }
            if (level >= 20) {
                features += "Foe Slayer: Hunter's Mark uses d10 die instead of d6.\n";
            }

            // Actions progression
            if (level >= 5) {
                actions += "Multiattack: Attack twice.\n";
            }
            if (level >= 10) {
                actions += "Tireless (WIS/day): Gain 1d8 + WIS temporary HP.\n";
            }

            // Bonus Actions progression
            if (level >= 14) {
                bonusActions += "Nature's Veil (WIS/day): Become invisible until the end of the next turn.\n";
            }

            // Subclass 1: Aether Prowler
            if (subClassIndex == 1) {
                wardenSpells = new String[] {"Sleep", "Blur", "Blink", "Confusion", "Hold Monster"};
                
                // Aetherial Attack progression
                if (level >= 3) {
                    bonusActions += "Aetherial Attack: Imbue a weapon with aether for 1 minute. It deals +";
                    if (level >= 11) bonusActions += "2d6 psychic damage.\n";
                    else bonusActions += "1d6 psychic damage.\n";
                }

                // Features progression
                if (level >= 3) {
                    features += "Starlit Suffusion: Can breathe aether.\n";
                }
                if (level >= 7) {
                    features += "Astral Affinity: Movement speed is doubled in the aether or Aether Smog.\n";
                }
                if (level >= 11) {
                    features += "Empowered Miasma: Enemies in Aether Smog have disadvantage on spell saves.\n";
                }
                
                // Bonus Actions progression
                if (level >= 3) {
                    bonusActions += "Aether Smog: Create a 60 ft. emanation of harmless aether that heavily obscures for all other creatures for 1 minute.\n";
                }
                
                // Reactions progression
                if (level >= 15) {
                    reactions += "Arcane Absorption (prof/day): On spell damage, gain temporary HP equal to half the damage.\n";
                }
            }
            // Subclass 2: Expanse Wayfinder
            else {
                wardenSpells = new String[] {"Comprehend Languages", "Gust of Wind", "Tongues", "Locate Creature", "Legend Lore"};

                // Features progression
                if (level >= 3) {
                    features += "Corsair of the Aetherial Waves: Expertise in Athletics and Acrobatics. First weapon attack per round deals +1d6 damage.\n";
                }
                if (level >= 7) {
                    features += "Warp and Weave: After moving at least 5 ft, increase armor class by 1 and opportunity attack disadvantage until the next turn.\n";
                }
                if (level >= 11) {
                    features += "Taking the Plunge: Increase swim speed by 10 ft. Advantage on breath and aether poisoning saves.\n";
                }
                if (level >= 15) {
                    features += "Aether in the Blood: Immune to aether poisoning, swim speed increased by 10 ft. Can cast misty step at will while submerged.\n";
                }
            }

            // Ranger prepared spells progression
            int[] rangerPreparedSpellsByLevel = {2,3,4,5,6,6,7,7,9,9,10,10,11,11,12,12,14,14,15,15};
            int preparedSpells = rangerPreparedSpellsByLevel[Math.min(level, 20)];
            // Select the first preparedSpells from possibleSpells in order
            java.util.List<String> spellList = new java.util.ArrayList<>();
            for (int i = 0; i < Math.min(preparedSpells, possibleSpells.length); i++) {
                spellList.add(possibleSpells[i]);
            }
            for (int i = 0; i < numWardenSpells; i++) {
                spellList.add(wardenSpells[roll(wardenSpells.length) - 1]);
            }
            spells = String.join(", ", spellList);
            spellDetails = "Spell Attack: +" + (statMods[4] + proficiencyBonus) + ", Spell DC: " + (8 + statMods[4] + proficiencyBonus) + "\n";
            
            // Hit Points and Armor Class
            hitPoints = 10 + ((level - 1) * (6 + statMods[2]));
            armorClass = 12 + statMods[1];
        }
        else if (charClass.equals("Rogue")) {
            if (subClassIndex == 1) subClass = "Cloaked Blade";
            else subClass = "Veiled Guardian";

            int sneakAttackDie = Math.round(level / 2);
            features += "Sneak Attack: When attacking a target with advantage or within 5 ft. of an ally, deal +" + sneakAttackDie + " damage.\n";

            // Extra Ability Improvement
            if (level >= 10) {
                abilityImprovement(statValues, statNames, keyStats[classIndex], secondaryStats[classIndex]);
                updateModifiers(statValues, statMods);
            }

            // Cunning Strike progression
            String cunningStrike = "";
            if (level >= 5) {
                cunningStrike += "Cunning Strike: On sneak attack, spend sneak attack die on various effects.\n";
                cunningStrike += "    Poison (1d6): Target makes a CON save. On fail, poisoned for 1 minute.\n";
                cunningStrike += "    Trip (1d6): Target makes a DEX save. On fail, falls prone.\n";
                cunningStrike += "    Withdraw (1d6): Move half speed without provoking opportunity attacks.\n";
            }
            if (level >= 14) {
                cunningStrike += "    Daze (2d6): Target makes a CON save. On fail, it must choose between action or bonus action next turn.\n";
                cunningStrike += "    Knock Out (6d6): Target makes a CON save. On fail, it falls unconscious for 1 minute.\n";
                cunningStrike += "    Obscure (3d6): Target makes a DEX save. On fail, it is blinded until the end of its turn.\n";
            }

            // Features progression
            if (level >= 1) {
                features += "Expertise: Double proficiency in Stealth and Sleight of Hand.\n";
            }
            if (level >- 5) {
                features += cunningStrike;
            }
            if (level >= 6) {
                features += "Expertise: Double proficiency in Deception and Acrobatics.\n";
            }
            if (level >= 7) {
                features += "Evasion: Improve DEX saves by one degree.\n";
            }
            if (level >= 11) {
                features += "Improved Cunning Strike: Use two cunning strike options.\n";
            }
            if (level >= 15) {
                features += "Slippery Mind: Proficiency in WIS and CHA saves.\n";
            }
            if (level >= 18) {
                features += "Elusive: Immune to attack advantage.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Choose an appropriate epic boon.\n";
            }
            if (level >= 20) {
                features += "Stroke of Luck (1/rest): On failing a roll, instead roll a 20.\n";
            }

            // Bonus Actions progression
            if (level >= 1) {
                bonusActions += "Cunning Action: Dash, disengage, or hide.\n";
            }

            // Reactions progression
            if (level >= 5) {
                reactions += "Uncanny Dodge: Halve damage from an attack.\n";
            }

            // Subclass 1: Cloaked Blade
            if (subClassIndex == 1) {
                // Features progression
                if (level >= 1) {
                    features += "Deadly Performer: Gain Performance proficiency.\n";
                }
                if (level >= 8) {
                    features += "Fleeting Form (1/day): Cast the blur spell with no concentration or material components.\n";
                }
                if (level >= 13) {
                    features += "Cloak Dancer: Performance uses DEX.\n";
                }
                if (level >= 17) {
                    features += "Deadly Distraction: Once per turn when hitting with Distracting Step, the target makes a WIS save. On a failure, make another attack, which can benefit from Sneak Attack a second time.\n";
                }

                // Bonus Actions progression
                if (level >= 1) {
                    bonusActions += "Deceptive Flourish: Contest an acrobatics or performance check against an insight check. On a success, gain Sneak Attack this turn.\n";
                }

                // Reactions progression
                if (level >= 1) {
                    reactions += "Distracting Step (prof/day): After a disengage, make a melee attack with advantage. Opportunity attacks have disadvantage.\n";
                }
                if (level >= 15) {
                    reactions += "Flashy Dodge (prof/day): Make a performance check and treat it as AC. If the strike still hits, it does half damage.\n";
                }
            }
            // Subclass 2: Veiled Guardian
            else {
                int numGuardianDice = Math.round(sneakAttackDie / 2);

                // Features progression
                if (level >= 3) {
                    features += "Guardian Dice: Gain " + numGuardianDice + "d6 guardian dice per rest.\n";
                }
                if (level >= 9) {
                    features += "Whispercatcher: Advantage on Perception checks related to hearing.\n";
                }
                
                // Actions progression
                if (level >= 9) {
                    actions += "Umbral Aegis: Spend a guardian die to cast darkness. User can see through the darkness.\n";
                }
                if (level >= 13) {
                    actions += "Swift Extraction: Take the Dash action, and designate an ally within 30 ft. to move up to their speed without opportunity attacsk as a reaction.\n";
                }

                // Bonus Actions progression
                if (level >= 3) {
                    bonusActions += "Diverting Gambit: Spend a guardian die to give a target disadvantage on any attacks except against the caster.\n";
                }

                // Reactions progression
                if (level >= 3) {
                    reactions += "Vigilant Courtier " + statMods[1] + "/day: When a creature within 20 ft. makes an opportunity attack against an ally, move to it and make a melee attack.\n";
                    reactions += "Parrying Shade: Roll guardian dice to reduce damage against an adjacent ally by the result.\n";
                }
                if (level >= 17) {
                    reactions += "Defender's Last Stand: When an ally within 30 ft. is reduced to zero HP, spend a guardian die for them to gain 1d6 + " + level + " hit points.\n";
                }
            }

            // Hit points and armor class
            hitPoints = 8 + ((level - 1) * (5 + statMods[2]));
            armorClass = 12 + statMods[1];
        }
        else if (charClass.equals("Sorcerer")) {
            if (subClassIndex == 1) subClass = "Phantom Shrouded";
            else subClass = "Primordial Soul";

            String[] possibleCantrips = {"Firebolt", "Mind Sliver", "Light", "Message", "Blade Ward", "Minor Illusion"};
            String[] possibleSpells = {"Mage Armor", "Ice Knife", "Shield", "Sleep",
                                       "Starlight Cannonball", "Aetheric Adaptation", "Knock",
                                       "Siren's Call", "Haste", "Fireball",
                                       "Wall of Aether", "Storm Sphere",
                                       "Dominate Person", "Telekinesis", "Enervation",
                                       "Aetherial Rift",
                                       "Finger of Death",
                                       "Earthquake",
                                       "Mass Dominate Person",
                                       "Reverse Gravity", "Abi-Dalzim's Horrid Wilting", "Time Stop"};

            // Sorcery points and metamagic progression
            int sorceryPoints = 0;
            if (level >= 1) sorceryPoints = level;
            String metamagic = "";
            ArrayList<String> metamagicOptions = new ArrayList<>();
            metamagicOptions.add("  Careful Spell (1 point): Up to " + statMods[5] + " creatures in a spell's area take no damage.\n");
            metamagicOptions.add("  Distant Spell (1 point): Cast a touch spell at 30 ft. or double a spell's range.\n");
            metamagicOptions.add("  Empowered Spell (1 point): Reroll up to " + statMods[5] + " damage dice.\n");
            metamagicOptions.add("  Extended Spell (1 point): Double a spell's duration up to 24 hours.\n");
            metamagicOptions.add("  Heightened Spell (2 points): Target gains disadvantage on its saving throw.\n");
            metamagicOptions.add("  Seeking Spell (1 point): Reroll an attack roll. Can be used with other options.\n");
            metamagicOptions.add("  Subtle Spell (1 point): Cast with no components, except ones with cost.\n");
            metamagicOptions.add("  Transmuted Spell (1 point): Change a spell's damage type.\n");
            metamagicOptions.add("  Twinned Spell (1 point): For spells that upcast for additional targets, upcast once for free.\n");
            metamagic = "Gain " + sorceryPoints + " sorcery points per day. Points can be spent on these options:\n";
            metamagic += metamagicOptions.remove(roll(metamagicOptions.size()) - 1);
            metamagic += metamagicOptions.remove(roll(metamagicOptions.size()) - 1);
            if (level >= 10) metamagic += metamagicOptions.remove(roll(metamagicOptions.size()) - 1);
            if (level >= 17) metamagic += metamagicOptions.remove(roll(metamagicOptions.size()) - 1);

            // Features progression
            if (level >= 2) {
                features += metamagic;
                features += "Font of Magic: Burn spell slots for sorcery points equal to the slot's level.\n";
            }
            if (level >= 5) {
                features += "Sorcerous Restoration (1/day): On a long rest, regain up to " + (level / 2) + " sorcery points.\n";
            }
            if (level >= 7) {
                features += "Sorcery Incarnate: While Innate Sorcery is active, two metamagic options can be used per spell.\n";
            }
            if (level >= 19) {
                features += "Epic Boon: Choose an appropriate epic boon.\n";
            }
            if (level >= 20) {
                features += "Arcane Apotheosis: While Innate Sorcery is active, one metamagic option can be used for free each turn.\n";
            }

            // Bonus Actions progression
            bonusActions += "Innate Sorcery (2/day): Increase save DC by 1 and gain advantage on spell attacks for 1 minute.\n";
            if (level >= 2) {
                bonusActions += "Font of Sorcery: Spend sorcery points to gain spell slots at a rate of 2:1, 3:2, 5:3, 6:4, 7:5.\n";
            }
            if (level >= 7) {
                bonusActions += "Charged Sorcery: Spend 2 sorcery points to activate Innate Sorcery.\n";
            }

            String[] subClassSpells;
            int numSubClassSpells = 0;
            // Subclass 1: Phantom Shrouded
            if (subClassIndex == 1) {
                subClassSpells = new String[] {"Comprehend Langauges", "Unseen Servant",
                                               "Augury", "Spiritual Weapon",
                                               "Speak with Dead", "Spirit Guardians",
                                               "Banishment", "Guardian of Faith",
                                               "Legend Lore", "Soul Tide"};
                numSubClassSpells = 4;
                if (level >= 5) numSubClassSpells += 2;
                if (level >= 7) numSubClassSpells += 2;
                if (level >= 9) numSubClassSpells += 2;

                // Features progression
                int phantomDie = 4;
                if (level >= 5) phantomDie += 2;
                if (level >= 10) phantomDie += 2;
                if (level >= 15) phantomDie += 2;
                
                if (level >= 3) {
                    features += "Depths of Knowledge (1/rest): Add 1d" + phantomDie + " to an ability check. Recharge with a sorcery point.\n";
                }
                if (level >= 14) {
                    features += "Ride the Aetherial Tide: Once per turn when casting a spell, roll 1d10. If the result is higher than the spell's level, one metamagic option costs -1.\n";
                }

                // Actions progression
                if (level >= 18) {
                    actions += "Phantom Mantle: Gain resistance to all damage except force and gain +" + statMods[5] + " to AC. Recharge with 6 sorcery points.\n";
                }

                // Bonus Actions progression
                if (level >= 6) {
                    bonusActions += "Swirling Spirits: After casting a leveled spell, spend a sorcery point to target a creature to either gain 1d4 + " + statMods[5] + " temp HP or make a CHA throw, taking spell level + " + statMods[5] + " force damage and losing all speed on a failure.\n";
                }
            }
            // Subclass 2: Primordial Soul
            else {
                subClassSpells = new String[] {"Thunderwave (air)", "Entangle (earth)", "Burning Hands (fire)", "Fog Cloud (water)", "Primal Squall (aether)", "Summon Primordial Minion", "Plane Shift"};
                numSubClassSpells = 5;

                // Features progression
                if (level >= 3) {
                    features += "Primordial Magic: Pick an elemental affinity and gain the corresponding spell. Can learn druid spells.\n";
                }
                if (level >= 6) {
                    numSubClassSpells += 1;
                    features += "Environmental Legion: Summon Primordial Minion summons an additional elemental with that spell. Summoned elementals deal magical damage.\n";
                }
                if (level >= 14) {
                    numSubClassSpells += 1;
                    features += "Plane Hopper: Plane Shift can be cast once per day without a spell slot. It can be cast without material components, instead linking to a random elemental plane.\n";
                }

                // Bonus Actions progression
                if (level >= 18) {
                    bonusActions += "Whirlpool's Wrath: Spend 6 sorcery points to gain fly speed and emit a 15 ft aether cloud. This is difficult terrain moving towards the caster, disperses other clouds, and causes ranged attack disadvantage.\n";
                }

                // Reactions progression
                if (level >= 3) {
                    reactions += "Elemental Resilience: Reduce incoming elemental damage (type chosen on long rest) by 1d10 + " + (statMods[5] + level) + ".\n";
                }
            }

            int[] sorcererPreparedSpellsByLevel = {0,4,5,6,7,9,10,11,12,14,15,16,16,17,17,18,18,19,20,21,22};
            int preparedSpells = sorcererPreparedSpellsByLevel[Math.min(level, 20)];
            // Select the first preparedSpells from possibleSpells in order
            java.util.List<String> spellList = new java.util.ArrayList<>();
            for (int i = 0; i < Math.min(preparedSpells, possibleSpells.length); i++) {
                spellList.add(possibleSpells[i]);
            }
            for (int i = 0; i < numSubClassSpells; i++) {
                spellList.add(subClassSpells[roll(subClassSpells.length) - 1]);
            }
            spells = String.join(", ", spellList);
            spellDetails = "Spell Attack: +" + (statMods[5] + proficiencyBonus) + ", Spell DC: " + (8 + statMods[5] + proficiencyBonus) + "\n";
            
            // Cantrip progression
            int cantripMax = 4;
            if (level >= 4) cantripMax++;
            if (level >= 10) cantripMax++;
            for (int i = 0; i < cantripMax; i++) {
                cantrips += possibleCantrips[i];
                if (i < cantripMax - 1) cantrips += ", ";
            }

            // Hit Points and Armor Class
            hitPoints = 6 + ((level - 1) * (4 + statMods[2]));
            armorClass = 10 + statMods[1];
        }
        else if (charClass.equals("Warlock")) {
        }
        else if (charClass.equals("Wizard")) {
        }

        

        if (level >= 3)System.out.println(subClass);
        System.out.println("Hit Points: " + hitPoints);
        System.out.println("Armor Class: " + armorClass);
        System.out.println("Features: " + features);
        System.out.println("Bonus Actions: " + bonusActions);
        System.out.println("Actions: " + actions);
        System.out.println("Reactions: " + reactions);
        if (!spells.equals("")) {
            System.out.println("Spells: " + spells);
            System.out.println("Spell Details: " + spellDetails);

            // Print spell slots if any are nonzero
            if (!cantrips.equals("")) {
                System.out.println("Cantrips: " + cantrips);
            }
            String[] ordinal = {"1st", "2nd", "3rd", "4th", "5th", "6th", "7th", "8th", "9th"};
            StringBuilder slotLine = new StringBuilder();
            for (int i = 0; i < spellSlots.length; i++) {
                if (spellSlots[i] > 0) {
                    if (slotLine.length() > 0) slotLine.append("; ");
                    slotLine.append(ordinal[i]).append(" level: ").append(spellSlots[i]);
                }
            }
            if (slotLine.length() > 0) {
                System.out.println("Spell Slots: " + slotLine.toString());
            }
        }
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

    private static void abilityImprovement(int[] abilities, String[] statNames, String firstStat, String secondStat) {
        int increases = 2;
        
        // Find indices for the stat names
        int firstIndex = matchIndex(statNames, firstStat);
        int secondIndex = matchIndex(statNames, secondStat);
        
        // Prioritize first stat, apply up to two increases if possible
        int canIncreaseFirst = Math.min(20 - abilities[firstIndex], increases);
        abilities[firstIndex] += canIncreaseFirst;
        increases -= canIncreaseFirst;

        // Then second stat (if not the same as first)
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
