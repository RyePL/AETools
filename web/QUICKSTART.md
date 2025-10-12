# AETools WordPress - Quick Start Guide

## 🎯 Choose Your Method

Pick the method that best suits your needs:

| Method | Time | Difficulty | Best For |
|--------|------|------------|----------|
| **Plugin** | 5 min | ⭐ Easy | Everyone (Recommended!) |
| **Direct Embed** | 5 min | ⭐⭐ Moderate | Single page use |
| **Shortcode** | 10 min | ⭐⭐⭐ Advanced | Theme developers |

---

## 🔌 Method 1: WordPress Plugin (RECOMMENDED)

### What You Need
- `aetools-generator.zip` (create this from the plugin files)

### Steps
1. **Create the plugin folder** named `aetools-generator`
2. **Copy these 5 files** into it:
   - `aetools-generator.php`
   - `styles.css`
   - `generators.js`
   - `app.js`
   - `template.php`
3. **Zip the folder** (right-click → compress)
4. **WordPress Admin** → Plugins → Add New → Upload Plugin
5. **Select** your `aetools-generator.zip` file
6. **Click** Install Now → Activate
7. **Use** `[aetools]` shortcode anywhere!

**✅ Done! Now add `[aetools]` to any page.**

For detailed plugin instructions, see: `PLUGIN_SETUP.md`

---

## 📄 Method 2: Direct Embed (Simple)

### What You Need
- `styles.css`, `generators.js`, `app.js` files
- Copy/paste the HTML from `index.html`

### Steps
1. **Upload files** to WordPress Media Library:
   - Go to Media → Add New
   - Upload: `styles.css`, `generators.js`, `app.js`
   - Copy each file's URL
2. **Create/Edit a WordPress Page**
3. **Switch to Code Editor** (or HTML mode)
4. **Paste this** (replace URLs with your actual file URLs):

```html
<link rel="stylesheet" href="YOUR-SITE.com/wp-content/uploads/2025/10/styles.css">

<div class="aetools-container">
    <div class="container">
        <div class="main-panel">
            <h1>AETools Generator</h1>
            
            <div class="control-panel">
                <div class="form-group">
                    <label for="methodSelect">Select Method:</label>
                    <select id="methodSelect">
                        <option value="randomChar">randomChar.main</option>
                        <option value="randomShip">randomShip.main</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label for="argsInput">Arguments:</label>
                    <input type="text" id="argsInput" placeholder="Enter arguments separated by spaces">
                    <small class="hint">For randomChar: try entering a class name</small>
                </div>
                
                <button id="executeButton" class="btn-primary">Execute</button>
            </div>
            
            <div class="output-panel">
                <h2>Output:</h2>
                <div id="outputArea" class="output-area"></div>
            </div>
        </div>
    </div>
</div>

<script src="YOUR-SITE.com/wp-content/uploads/2025/10/generators.js"></script>
<script src="YOUR-SITE.com/wp-content/uploads/2025/10/app.js"></script>
```

5. **Replace** `YOUR-SITE.com/wp-content/uploads/2025/10/` with actual URLs
6. **Publish** the page

**✅ Done!**

For detailed embed instructions, see: `WORDPRESS_INTEGRATION.md`

---

## 🧪 Test Locally First

Before uploading to WordPress, test that everything works:

1. **Open** `index.html` in your web browser
2. **Select** randomChar or randomShip
3. **Click** Execute
4. **Verify** output appears

If it works locally, it'll work on WordPress!

---

## 📚 Documentation Index

- **QUICKSTART.md** ← You are here (fastest setup)
- **PLUGIN_SETUP.md** → Complete plugin installation guide
- **WORDPRESS_INTEGRATION.md** → All WordPress methods with examples
- **SETUP_SUMMARY.md** → Overview of all files and features
- **README.md** → Technical documentation

---

## 🎮 Using the Generator

### Random Character
1. Select: `randomChar.main`
2. Arguments (optional): Enter a class name
   - Example: `Wizard` or `Fighter` or `Bard`
   - Leave empty for random class
3. Click Execute
4. Get a complete character with stats!

### Random Ship
1. Select: `randomShip.main`
2. Arguments: Leave empty (not needed)
3. Click Execute
4. Get a complete ship with crew!

---

## ❓ Common Issues

### "Nothing happens when I click Execute"
- **Check**: Browser console (press F12 → Console tab)
- **Look for**: Red error messages
- **Common fix**: Verify file URLs are correct

### "Styling looks wrong"
- **Clear cache**: Ctrl+F5 (Windows) or Cmd+Shift+R (Mac)
- **Check**: CSS file URL is correct
- **Try**: Hard refresh the page

### "I see [aetools] as text instead of the generator"
- **Check**: Plugin is activated (if using plugin method)
- **Clear**: WordPress cache
- **Verify**: You're using the shortcode in a page/post, not in comments

### "Files won't upload to WordPress"
- **Check**: File sizes (should be small)
- **Verify**: File extensions (.css, .js, .php)
- **Try**: Upload one at a time
- **Increase**: Upload limit in WordPress settings if needed

---

## ⚡ Pro Tips

1. **Test locally** before uploading to WordPress
2. **Use the plugin method** - it's easiest to maintain
3. **Keep backups** of your files
4. **Clear cache** when you make changes
5. **Check browser console** (F12) if something breaks

---

## 🎨 Customization Quick Reference

Want to change something? Here's where:

| What to Change | Edit This File | Look For |
|----------------|---------------|----------|
| Colors | `styles.css` | `#667eea`, `#764ba2` |
| Ship names | `generators.js` | `firstNames`, `secondNames` arrays |
| Character names | `generators.js` | `firstName`, `lastName` arrays |
| Layout | `template.php` or `index.html` | HTML structure |
| Button text | `template.php` or `index.html` | `<button>` tags |

---

## 🚀 Ready to Deploy?

### Checklist Before Going Live

- [ ] Tested `index.html` locally
- [ ] Generators work correctly
- [ ] Chosen installation method (Plugin recommended)
- [ ] Have WordPress admin access
- [ ] Backed up current WordPress site
- [ ] Ready to upload files

### After Installation

- [ ] Test on WordPress page
- [ ] Try both generators
- [ ] Test on mobile device
- [ ] Clear WordPress cache
- [ ] Share with users!

---

## 🎯 Your Next Steps

### If you chose PLUGIN method:
→ Go to `PLUGIN_SETUP.md`

### If you chose DIRECT EMBED:
→ Go to `WORDPRESS_INTEGRATION.md`

### If you want TECHNICAL DETAILS:
→ Go to `README.md`

### If you want OVERVIEW:
→ Go to `SETUP_SUMMARY.md`

---

## 💬 Need Help?

1. Read the relevant documentation file above
2. Check browser console (F12) for errors
3. Verify all file URLs are correct
4. Try testing locally with `index.html`
5. Review the troubleshooting section in this guide

---

**Pick your method and get started! The plugin method is recommended for most users.** 🎉

Good luck, and happy generating! ⚔️🚢

