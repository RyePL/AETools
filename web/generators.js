// AETools Generators - JavaScript Implementation

// Utility Functions
function roll(size) {
    return Math.floor(Math.random() * size) + 1;
}

function multiRoll(size, num) {
    let total = 0;
    for (let i = 0; i < num; i++) {
        total += roll(size);
    }
    return total;
}

function stringSelect(num, thresholds, results) {
    for (let i = 0; i < thresholds.length; i++) {
        if (num <= thresholds[i]) {
            return results[i];
        }
    }
    return results[results.length - 1];
}

function intSelect(num, thresholds, results) {
    for (let i = 0; i < thresholds.length; i++) {
        if (num <= thresholds[i]) {
            return results[i];
        }
    }
    return results[results.length - 1];
}

function doubleSelect(num, thresholds, results) {
    for (let i = 0; i < thresholds.length; i++) {
        if (num <= thresholds[i]) {
            return results[i];
        }
    }
    return results[results.length - 1];
}

function indexMatch(input, array) {
    for (let i = 0; i < array.length; i++) {
        if (array[i] === input) {
            return i;
        }
    }
    return -1;
}

function hasEmptySlots(array) {
    return array.some(item => item === "");
}

function countEmptySlots(array) {
    return array.filter(item => item === "").length;
}

function fillSlots(array, weapon, count) {
    let filled = 0;
    for (let i = 0; i < array.length && filled < count; i++) {
        if (array[i] === "") {
            array[i] = weapon;
            filled++;
        }
    }
    return filled;
}

function summarizeArray(arr) {
    const counts = {};
    arr.forEach(item => {
        if (item !== "") {
            counts[item] = (counts[item] || 0) + 1;
        }
    });
    
    return Object.entries(counts)
        .map(([key, value]) => `${value}x ${key}`)
        .join(", ");
}

// RandomChar Functions
const RandomChar = {
    generateName: function() {
        const firstName = [
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
        ];
        
        const nickName = [
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
        ];
        
        const lastName = [
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
        ];
        
        let name = firstName[roll(firstName.length) - 1];
        if (roll(10) < 4) {
            name += " " + nickName[roll(nickName.length) - 1];
        }
        name += " " + lastName[roll(lastName.length) - 1];
        
        return name;
    },
    
    generateBoons: function(rank) {
        const boons = [
            "Aether Assassin", "Aether-Immune", "Avast Me Hearties!", "Beast Chaser", 
            "Blow the Man Down!", "Butcher of the Seas", "Cannonward Shield", "Champion of the Unknown",
            "Do-or-die", "Fire in the Hole!", "Grog Waterer", "Intimidating Demeanor",
            "Lucky Looter", "Merciful Demeanor", "Never Say Die", "Pack Rat",
            "Recruiter", "Rigrunner", "Sharp Spotter", "Skilled Mentor",
            "Tactical Genius", "Wind-Kissed"
        ];
        
        const thresholds = [4, 8, 12, 16, 20, 24, 28, 32, 36, 40, 44, 48, 52, 56, 60, 64, 68, 72, 76, 80, 84, 88];
        
        let numBoons = 0;
        if (rank >= 1) numBoons = 1;
        if (rank >= 3) numBoons = 2;
        
        if (numBoons === 0) {
            return "";
        }
        
        const firstBoon = stringSelect(roll(100), thresholds, boons);
        
        if (numBoons === 1) {
            return firstBoon;
        }
        
        let secondBoon;
        do {
            secondBoon = stringSelect(roll(100), thresholds, boons);
        } while (secondBoon === firstBoon);
        
        return firstBoon + ", " + secondBoon;
    },
    
    main: function(args) {
        let output = "";
        output += this.generateName() + "\n";
        
        const statNames = ["STR", "DEX", "CON", "INT", "WIS", "CHA"];
        const statValues = [];
        const statMods = [];
        
        function statRoll() {
            let lowest = 100;
            let total = 0;
            for (let i = 0; i < 4; i++) {
                const rollValue = roll(6);
                total += rollValue;
                if (rollValue < lowest) {
                    lowest = rollValue;
                }
            }
            return total - lowest;
        }
        
        for (let i = 0; i < 6; i++) {
            statValues[i] = statRoll();
            statMods[i] = Math.floor((statValues[i] - 10) / 2);
        }
        
        let bestStatIndex = 0;
        let worstStatIndex = 0;
        for (let i = 1; i < 6; i++) {
            if (statValues[i] > statValues[bestStatIndex]) {
                bestStatIndex = i;
            }
            if (statValues[i] < statValues[worstStatIndex]) {
                worstStatIndex = i;
            }
        }
        
        const qualities = [
            ["Beefy", "Muscular", "Slight", "Frail"],
            ["Nimble", "Quick", "Slow", "Clumsy"],
            ["Resolute", "Stout", "Frail", "Sickly"],
            ["Razor-Sharp", "Bright", "Dim", "Slow"],
            ["Deepened", "Aware", "Aloof", "Unaware"],
            ["Magnetic", "Charming", "Unpleasant", "Unlikeable"]
        ];
        
        const secondaryQualities = [
            "Eye patch", "Old", "Stylish", "Scarred", "Tattooed", "Bald", "Long hair", "Bearded", 
            "Clean-shaven", "Pierced", "Wrinkled", "Young", "Tanned", "Pale", "Freckled", 
            "Large", "Slender", "Tall", "Short", "Broad-shouldered", "Limping", "Missing finger"
        ];
        
        const bestQualityIndex = (statMods[bestStatIndex] <= -2) ? 3 : (statMods[bestStatIndex] <= 0) ? 2 : (statMods[bestStatIndex] <= 2) ? 1 : 0;
        const worstQualityIndex = (statMods[worstStatIndex] <= -2) ? 3 : (statMods[worstStatIndex] <= 0) ? 2 : (statMods[worstStatIndex] <= 2) ? 1 : 0;
        
        const numSecondaryQualities = roll(3);
        const selectedSecondaryQualities = [];
        const usedIndices = [];
        
        for (let i = 0; i < numSecondaryQualities; i++) {
            let index;
            do {
                index = roll(secondaryQualities.length) - 1;
            } while (usedIndices.includes(index));
            usedIndices.push(index);
            selectedSecondaryQualities.push(secondaryQualities[index]);
        }
        
        const secondaryQualitiesString = selectedSecondaryQualities.join(", ");
        output += qualities[bestStatIndex][bestQualityIndex] + ", " + qualities[worstStatIndex][worstQualityIndex] + ", " + secondaryQualitiesString + "\n";
        
        const classes = ["Barbarian", "Bard", "Cleric", "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorcerer", "Warlock", "Wizard"];
        const keyStats = ["STR", "CHA", "WIS", "WIS", "STR", "DEX", "STR", "DEX", "DEX", "CHA", "CHA", "INT"];
        const secondaryStats = ["CON", "DEX", "STR", "DEX", "DEX", "WIS", "CHA", "WIS", "INT", "CON", "DEX", "CON"];
        
        const keyStatRatings = [];
        
        function matchIndex(array, toFind) {
            for (let i = 0; i < array.length; i++) {
                if (array[i] === toFind) return i;
            }
            return -1;
        }
        
        for (let i = 0; i < classes.length; i++) {
            keyStatRatings[i] = statValues[matchIndex(statNames, keyStats[i])];
        }
        
        let charClass = null;
        let classOverridden = false;
        for (const arg of args) {
            for (const className of classes) {
                if (arg.toLowerCase() === className.toLowerCase()) {
                    charClass = className;
                    classOverridden = true;
                    break;
                }
            }
            if (charClass !== null) break;
        }
        
        function weightedRandomSelect(items, weights) {
            let totalWeight = 0;
            for (const weight of weights) {
                totalWeight += weight;
            }
            
            const randomValue = roll(totalWeight);
            
            let currentWeight = 0;
            for (let i = 0; i < items.length; i++) {
                currentWeight += weights[i];
                if (randomValue <= currentWeight) {
                    return items[i];
                }
            }
            
            return items[items.length - 1];
        }
        
        if (charClass === null) {
            charClass = weightedRandomSelect(classes, keyStatRatings);
        }
        
        if (classOverridden) {
            const classIndex = matchIndex(classes, charClass);
            const keyStat = keyStats[classIndex];
            const secondaryStat = secondaryStats[classIndex];
            
            const keyStatIndex = matchIndex(statNames, keyStat);
            const secondaryStatIndex = matchIndex(statNames, secondaryStat);
            
            const tempStats = [...statValues];
            tempStats.sort((a, b) => b - a);
            
            statValues[keyStatIndex] = tempStats[0];
            statValues[secondaryStatIndex] = tempStats[1];
            
            let tempIndex = 2;
            for (let i = 0; i < 6; i++) {
                if (i !== keyStatIndex && i !== secondaryStatIndex) {
                    statValues[i] = tempStats[tempIndex++];
                }
            }
            
            for (let i = 0; i < 6; i++) {
                statMods[i] = Math.floor((statValues[i] - 10) / 2);
            }
        }
        
        output += charClass + "\n";
        
        for (let i = 0; i < 6; i++) {
            output += statNames[i] + " " + statValues[i] + " (" + (statMods[i] >= 0 ? "+" : "") + statMods[i] + ")\n";
        }
        
        return output;
    }
};

// RandomShip Functions
const RandomShip = {
    main: function(args) {
        let result = "";
        
        let roll_val = roll(100);
        const faction = stringSelect(roll_val, [20, 60, 80, 100], ["Ayrissian", "Independent", "Karelagne", "Pirate"]);
        const prefix = stringSelect(roll_val, [20, 60, 80, 100], ["AMS ", "", "INS ", ""]);
        
        const firstNames = [
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
        ];
        
        const secondNames = [
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
        ];
        
        const shipName = "The " + prefix + firstNames[roll(firstNames.length) - 1] + secondNames[roll(secondNames.length) - 1];
        result += shipName + "\n";
        
        const armament = doubleSelect(roll(100), [10, 20, 70, 85, 100], [0.0, 0.2, 0.5, 0.8, 1.0]);
        const shipTypes = ["Sloop", "Schooner", "Brigantine", "Frigate", "Galleon", "Man o' War"];
        roll_val = roll(100);
        const shipType = stringSelect(roll_val, [25, 50, 70, 85, 95, 100], shipTypes);
        
        result += faction + " " + shipType + "\n";
        
        let crewSize = 0, maxCrewSize = 0, loot = 0;
        if (shipType === "Sloop") {
            crewSize = 8 + multiRoll(4, 4);
            maxCrewSize = 24;
            loot = 100 * multiRoll(6, 3);
        } else if (shipType === "Schooner") {
            crewSize = 13 + multiRoll(6, 3);
            maxCrewSize = 32;
            loot = 100 * multiRoll(6, 6);
        } else if (shipType === "Brigantine") {
            crewSize = 16 + multiRoll(6, 4);
            maxCrewSize = 40;
            loot = 100 * multiRoll(6, 12);
        } else if (shipType === "Frigate") {
            crewSize = 21 + multiRoll(6, 5);
            maxCrewSize = 52;
            loot = 1000 * multiRoll(8, 2);
        } else if (shipType === "Galleon") {
            crewSize = 26 + multiRoll(6, 6);
            maxCrewSize = 64;
            loot = 1000 * multiRoll(8, 4);
        } else if (shipType === "Man o' War") {
            crewSize = 42 + multiRoll(8, 7);
            maxCrewSize = 100;
            loot = 1000 * multiRoll(8, 8);
        }
        result += "Crew Size: " + crewSize + " / " + maxCrewSize + "\n";
        
        if ((faction === "Ayrissian" || ((faction === "Karelagne" && (roll(5) === 1)) || (faction === "Independent" && (roll(4) === 1))))) {
            loot *= 2;
        }
        result += "Loot: " + loot + " gp\n";
        
        let bowSlots = Math.floor(intSelect(roll_val, [25, 50, 70, 85, 95, 100], [0, 0, 0, 0, 2, 2]) * armament);
        let portSlots = Math.floor(intSelect(roll_val, [25, 50, 70, 85, 95, 100], [4, 5, 6, 8, 10, 12]) * armament);
        let starboardSlots = Math.floor(intSelect(roll_val, [25, 50, 70, 85, 95, 100], [4, 5, 6, 8, 10, 12]) * armament);
        let sternSlots = Math.floor(intSelect(roll_val, [25, 50, 70, 85, 95, 100], [0, 0, 0, 1, 2, 3]) * armament);
        
        const warship = shipType === "Frigate" || shipType === "Galleon" || shipType === "Man o' War";
        
        const bowWeapons = new Array(bowSlots).fill("");
        const portWeapons = new Array(portSlots).fill("");
        const starboardWeapons = new Array(starboardSlots).fill("");
        const sternWeapons = new Array(sternSlots).fill("");
        
        let weaponTypes, weaponThresholds;
        
        if (warship) {
            weaponTypes = ["Carronade", "Coilgun", "Culverin", "Falconet", "Flamethrower", "Long Gun", "Long Nine", "Saker", "Swivel Gun Quartet", "Wrath Cannon"];
            weaponThresholds = [12, 14, 26, 33, 41, 59, 71, 89, 98, 100];
            if (faction !== "Karelagne") {
                weaponThresholds[8] = 99;
            }
        } else {
            weaponTypes = ["Culverin", "Falconet", "Flamethrower", "Saker", "Swivel Gun Quartet"];
            weaponThresholds = [20, 35, 45, 85, 100];
        }
        
        while (hasEmptySlots(bowWeapons) || hasEmptySlots(portWeapons) || hasEmptySlots(starboardWeapons) || hasEmptySlots(sternWeapons)) {
            const selectedWeapon = stringSelect(roll(100), weaponThresholds, weaponTypes);
            
            const totalEmptySlots = countEmptySlots(bowWeapons) + countEmptySlots(portWeapons) + countEmptySlots(starboardWeapons) + countEmptySlots(sternWeapons);
            if (totalEmptySlots === 0) break;
            
            let slotsToFill = Math.max(1, Math.floor(totalEmptySlots * (roll(100) / 100.0)));
            
            const portEmpty = countEmptySlots(portWeapons);
            const starboardEmpty = countEmptySlots(starboardWeapons);
            const minSideSlots = Math.min(portEmpty, starboardEmpty);
            
            if (minSideSlots > 0 && slotsToFill >= 2 && selectedWeapon !== "Long Nine") {
                const sideSlotsToFill = Math.min(minSideSlots, Math.floor(slotsToFill / 2));
                slotsToFill -= fillSlots(portWeapons, selectedWeapon, sideSlotsToFill);
                slotsToFill -= fillSlots(starboardWeapons, selectedWeapon, sideSlotsToFill);
            }
            
            if (slotsToFill > 0) {
                if (selectedWeapon !== "Long Nine") {
                    slotsToFill -= fillSlots(bowWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(bowWeapons)));
                    slotsToFill -= fillSlots(portWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(portWeapons)));
                }
                slotsToFill -= fillSlots(starboardWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(starboardWeapons)));
                slotsToFill -= fillSlots(sternWeapons, selectedWeapon, Math.min(slotsToFill, countEmptySlots(sternWeapons)));
            }
        }
        
        if (bowSlots > 0) {
            result += "Bow Weapons: " + summarizeArray(bowWeapons) + "\n";
        }
        result += "Port Weapons: " + summarizeArray(portWeapons) + "\n";
        result += "Starboard Weapons: " + summarizeArray(starboardWeapons) + "\n";
        if (sternSlots > 0) {
            result += "Stern Weapons: " + summarizeArray(sternWeapons) + "\n";
        }
        
        let specialAmmunition = false;
        let upgrade = false;
        
        roll_val = roll(100);
        if ((roll_val >= 76) && (roll_val <= 85)) specialAmmunition = true;
        if (roll_val >= 86) upgrade = true;
        if (roll_val >= 96) specialAmmunition = true;
        
        let specialAmmunitionCount, upgradeCount;
        let specialAmmunitionList = "";
        let upgradeList = "";
        
        if (specialAmmunition) {
            roll_val = roll(100);
            if (roll_val <= 50) specialAmmunitionCount = 1;
            else if (roll_val <= 85) specialAmmunitionCount = roll(4);
            else if (roll_val <= 95) specialAmmunitionCount = roll(6);
            else specialAmmunitionCount = multiRoll(4, 2);
            
            const ammoTypes = ["Canister Shot", "Carcass", "Chainshot", "Grapeshot", "Hullbusters", "Langridge", "Salamanders"];
            const ammoThresholds = [15, 30, 45, 60, 70, 90, 100];
            const specialAmmunitionArray = [];
            for (let i = 0; i < specialAmmunitionCount; i++) {
                specialAmmunitionArray.push(stringSelect(roll(100), ammoThresholds, ammoTypes));
            }
            specialAmmunitionList = summarizeArray(specialAmmunitionArray);
        }
        
        if (upgrade) {
            roll_val = roll(100);
            if (roll_val <= 50) upgradeCount = 1;
            else if (roll_val <= 85) upgradeCount = roll(4);
            else if (roll_val <= 95) upgradeCount = roll(6);
            else upgradeCount = multiRoll(4, 2);
            
            const upgradeTypes = [
                "Astral-Oak Reinforcements", "Ayrissian Canvas Sails", "Bucket-Cutter", "Cannonward Shield Matrix", "Divine Lion Figurehead", "Drake's Wing Sails", "Fey-Touched Craftwood", "Imperial Ram", "Ironclad Plating", "Jawhook Ram",
                "Mage-Silk Sails", "Mithral Carpentry Tools", "Multiversal Orrery", "Refurbished Galley", "Silver Unicorn Figurehead", "Spellspun Rigging", "Stardrive Engine", "Voroaxinar's Wrath", "Wavetable Navigator", "Winged Victory Figurehead", "Witchcotton Hammocks"
            ];
            const upgradeThresholds = [8, 13, 18, 20, 24, 28, 33, 42, 47, 52, 56, 61, 70, 74, 75, 80, 84, 85, 90, 95, 100];
            const upgradeSet = new Set();
            while (upgradeSet.size < upgradeCount) {
                const upgradeStr = stringSelect(roll(100), upgradeThresholds, upgradeTypes);
                upgradeSet.add(upgradeStr);
            }
            upgradeList = Array.from(upgradeSet).join(", ");
        }
        
        if (specialAmmunition) result += "Special Ammunition: " + specialAmmunitionList + "\n";
        if (upgrade) result += "Upgrades: " + upgradeList + "\n";
        
        const stationBonus = intSelect(indexMatch(shipType, shipTypes), [0, 1, 2, 3, 4, 5], [0, 2, 4, 6, 8, 10]);
        const filledStations = intSelect(roll(20) + stationBonus, [4, 14, 20, 24, 27, 29, 30], [4, 5, 6, 7, 8, 9, 10]);
        
        const officers = ["Captain", "First Mate", "Quartermaster", "Navigator", "Helmsperson", "Boatswain", "Cook", "Master Gunner", "Shipwright", "Surgeon"];
        
        const officersToRemove = 10 - filledStations;
        const stationTypes = ["Boatswain", "Cook", "Master Gunner", "Shipwright", "Surgeon"];
        const stationThresholds = [4, 8, 12, 16, 20];
        
        for (let i = 0; i < officersToRemove; i++) {
            const stationToRemove = stringSelect(roll(20), stationThresholds, stationTypes);
            for (let j = 5; j < officers.length; j++) {
                if (officers[j] === stationToRemove) {
                    officers[j] = "";
                    break;
                }
            }
        }
        
        if (filledStations === 4) {
            const firstFiveStations = ["Captain", "First Mate", "Quartermaster", "Navigator", "Helmsperson"];
            const firstFiveThresholds = [1, 5, 10, 15, 20];
            const stationToRemove = stringSelect(roll(20), firstFiveThresholds, firstFiveStations);
            for (let j = 0; j < 5; j++) {
                if (officers[j] === stationToRemove) {
                    officers[j] = "";
                    break;
                }
            }
        }
        
        const rankedAdjustment = intSelect(roll(20) + stationBonus, [1, 5, 9, 14, 19, 30], [-4, -3, -2, -1, 0, 1]);
        const rankedOfficers = filledStations + rankedAdjustment;
        
        const rankedOfficerNames = [];
        const rankedOfficerRanks = [];
        
        if (officers[0] !== "") {
            rankedOfficerNames.push(officers[0]);
            rankedOfficerRanks.push(intSelect(roll(20), [5, 13, 18, 19, 20], [1, 2, 3, 4, 5]));
        }
        
        const middlePositions = [];
        for (let i = 1; i < 5; i++) {
            if (officers[i] !== "") {
                middlePositions.push(i);
            }
        }
        shuffleArray(middlePositions);
        for (const pos of middlePositions) {
            if (rankedOfficerNames.length < rankedOfficers) {
                rankedOfficerNames.push(officers[pos]);
                rankedOfficerRanks.push(intSelect(roll(20), [5, 13, 18, 19, 20], [1, 2, 3, 4, 5]));
            }
        }
        
        const lastPositions = [];
        for (let i = 5; i < 10; i++) {
            if (officers[i] !== "") {
                lastPositions.push(i);
            }
        }
        shuffleArray(lastPositions);
        for (const pos of lastPositions) {
            if (rankedOfficerNames.length < rankedOfficers) {
                rankedOfficerNames.push(officers[pos]);
                rankedOfficerRanks.push(intSelect(roll(20), [5, 13, 18, 19, 20], [1, 2, 3, 4, 5]));
            }
        }
        
        result += "Ranked Officers:\n";
        for (let i = 0; i < rankedOfficerNames.length; i++) {
            result += "  " + rankedOfficerNames[i] + ": " + RandomChar.generateName();
            result += " (Rank " + rankedOfficerRanks[i] + ")\n";
            if (rankedOfficerRanks[i] >= 2) {
                result += "  Boons: " + RandomChar.generateBoons(rankedOfficerRanks[i]) + "\n";
            }
        }
        
        result += "Non-ranked Officers:\n";
        for (let i = 0; i < officers.length; i++) {
            if (officers[i] !== "" && !rankedOfficerNames.includes(officers[i])) {
                result += "  " + officers[i] + ": " + RandomChar.generateName() + "\n";
            }
        }
        
        if (rankedOfficers > filledStations) {
            result += "1 ranked non-officer in the crew\n";
        }
        
        return result;
    }
};

function shuffleArray(array) {
    for (let i = array.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [array[i], array[j]] = [array[j], array[i]];
    }
}

