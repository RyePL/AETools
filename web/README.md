# AETools Web Generator

A JavaScript/HTML implementation of the AETools character and ship generators, designed to be embedded in WordPress websites.

## Overview

This is a web-based version of the AETools Java application that includes:
- **randomChar**: Generates random D&D-style characters with stats, classes, and descriptions
- **randomShip**: Generates random fantasy ships with crews, weapons, and equipment

## Files

- `index.html` - Standalone web interface
- `styles.css` - Modern, responsive styling
- `generators.js` - Core generator logic (converted from Java)
- `app.js` - UI controller that connects interface to generators
- `WORDPRESS_INTEGRATION.md` - Complete guide for embedding in WordPress

## Quick Start

### Standalone Usage

1. Open `index.html` in any modern web browser
2. Select a generator from the dropdown (randomChar or randomShip)
3. Enter optional arguments (for randomChar: class names like "Wizard", "Fighter", etc.)
4. Click "Execute" to generate results

### WordPress Integration

See `WORDPRESS_INTEGRATION.md` for detailed instructions on embedding this tool into your WordPress website.

## Features

### randomChar Generator
- Generates character names with optional nicknames
- Rolls stats using 4d6 drop lowest method
- Assigns character class based on stats (or accepts manual override)
- Provides physical descriptions and personality traits
- Supports all 12 D&D classes: Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard

### randomShip Generator
- Creates unique ship names with prefixes
- Determines faction (Ayrissian, Independent, Karelagne, Pirate)
- Assigns ship type and size (Sloop to Man o' War)
- Generates crew complement and loot values
- Creates weapon loadouts for all ship facings
- Adds special ammunition and upgrades
- Generates named officers with ranks and abilities (boons)

## Browser Compatibility

Works on all modern browsers:
- Chrome 90+
- Firefox 88+
- Safari 14+
- Edge 90+
- Mobile browsers (iOS Safari, Chrome Mobile)

## Technical Details

### Conversion from Java

This JavaScript version maintains the same logic and algorithms as the original Java implementation:
- All random number generation uses `Math.random()`
- Dice rolling functions (`roll`, `multiRoll`) work identically
- Selection algorithms (`stringSelect`, `intSelect`, `doubleSelect`) preserved
- Name and data arrays are identical to the Java version

### No Dependencies

This implementation uses pure vanilla JavaScript with no external libraries or frameworks, making it:
- Lightweight (< 50KB total)
- Fast to load
- Easy to maintain
- Compatible with any WordPress theme

## Customization

### Changing Colors

Edit `styles.css` and search for:
- `#667eea` and `#764ba2` - Primary gradient colors
- `#2d3748` - Text color
- `#e2e8f0` - Border color

### Modifying Generator Logic

Edit `generators.js`:
- `RandomChar.main()` - Main character generation logic
- `RandomShip.main()` - Main ship generation logic
- Arrays at the top of each function contain names, items, etc.

### Adjusting Layout

Edit `index.html` or modify the classes in `styles.css`

## Development

The code is structured for easy maintenance:

```
web/
├── index.html          # Main interface
├── styles.css          # All styling
├── generators.js       # Core logic (RandomChar and RandomShip)
├── app.js             # UI controller
├── README.md          # This file
└── WORDPRESS_INTEGRATION.md  # WordPress setup guide
```

## Security

- All code runs client-side in the browser
- No data is transmitted to any server
- No cookies or tracking
- No user data collection
- Safe for any WordPress installation

## License

Same as the original AETools Java application.

## Credits

Converted from the original Java implementation to JavaScript for web compatibility.

## Support

For issues or questions:
1. Check `WORDPRESS_INTEGRATION.md` for WordPress-specific problems
2. Verify all files are uploaded correctly
3. Check browser console (F12) for JavaScript errors
4. Ensure file URLs are correct in your WordPress page

---

**Enjoy generating characters and ships!**

