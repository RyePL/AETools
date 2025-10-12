# AETools WordPress Plugin Setup

## Creating a WordPress Plugin (Most Professional Method)

This guide shows you how to create a complete WordPress plugin for AETools.

## Quick Setup (5 Steps)

### Step 1: Create Plugin Folder

Create a new folder on your computer named: `aetools-generator`

### Step 2: Copy Files

Copy these files into the `aetools-generator` folder:
- `aetools-generator.php` (main plugin file)
- `styles.css`
- `generators.js`
- `app.js`
- `template.php`

Your folder structure should look like:
```
aetools-generator/
├── aetools-generator.php
├── styles.css
├── generators.js
├── app.js
└── template.php
```

### Step 3: Create ZIP File

1. Right-click the `aetools-generator` folder
2. Select "Compress" or "Send to → Compressed folder" (Windows) / "Compress" (Mac)
3. This creates `aetools-generator.zip`

### Step 4: Upload to WordPress

1. Log into your WordPress admin panel
2. Go to **Plugins → Add New**
3. Click **Upload Plugin** button at the top
4. Click **Choose File** and select `aetools-generator.zip`
5. Click **Install Now**
6. Click **Activate Plugin**

### Step 5: Use the Plugin

Now you can add the generator to any page or post using:
```
[aetools]
```

Just type that shortcode in any WordPress page/post editor!

## Advanced Usage

### Shortcode with Custom Title

```
[aetools title="My Custom Title"]
```

### Add to WordPress Template

Add this to your theme's PHP files:
```php
<?php echo do_shortcode('[aetools]'); ?>
```

### Add to Widgets

Use the "Shortcode" widget or add this code to a Text widget:
```
[aetools]
```

## Plugin Features

✓ **Clean Installation**: Installs like any WordPress plugin
✓ **Easy Updates**: Just upload new version when needed
✓ **Reusable**: Use `[aetools]` shortcode anywhere
✓ **Safe**: Follows WordPress plugin standards
✓ **Professional**: Shows in plugin list with details

## Customizing the Plugin

### Change Plugin Information

Edit `aetools-generator.php` at the top:
```php
Plugin Name: Your Custom Name
Description: Your custom description
Author: Your Name
Author URI: https://your-website.com
```

### Modify Generator Display

Edit `template.php` to change the HTML structure

### Update Styles

Edit `styles.css` to change colors and appearance

### Modify Generator Logic

Edit `generators.js` to change how characters/ships are generated

## Updating the Plugin

When you make changes:

1. Modify the files in your `aetools-generator` folder
2. Increment version number in `aetools-generator.php`:
   ```php
   Version: 1.0.1  // Change this
   ```
3. Create new ZIP file
4. In WordPress: **Plugins → Add New → Upload Plugin**
5. Upload the new ZIP (it will replace the old version)
6. Activate if needed

## Troubleshooting

### Plugin doesn't appear after upload
- Make sure folder name is `aetools-generator`
- Verify `aetools-generator.php` is in the root of that folder
- Check that the PHP file starts with `<?php` and has the plugin header

### Shortcode shows as text instead of generator
- Make sure plugin is activated
- Try deactivating and reactivating the plugin
- Clear WordPress cache

### Styles don't load
- Check that `styles.css` is in the same folder as `aetools-generator.php`
- Clear browser cache (Ctrl+F5 or Cmd+Shift+R)
- Clear WordPress cache

### JavaScript doesn't work
- Open browser console (F12) and check for errors
- Verify `generators.js` and `app.js` are in the correct folder
- Make sure files are named exactly as shown (case-sensitive)

## File Checklist

Before creating the ZIP, verify you have:
- [ ] `aetools-generator.php` - Main plugin file
- [ ] `styles.css` - Stylesheet
- [ ] `generators.js` - Generator logic
- [ ] `app.js` - UI controller
- [ ] `template.php` - HTML template

## Benefits of Plugin Method

**vs Direct Embedding:**
- ✓ Cleaner code
- ✓ Reusable with shortcode
- ✓ Easier to update
- ✓ Portable to other sites

**vs Media Library Method:**
- ✓ All files in one package
- ✓ Version control
- ✓ Professional appearance
- ✓ Easy to share

**vs Custom HTML:**
- ✓ No URL updates needed
- ✓ Works after site migration
- ✓ Survives theme changes
- ✓ Cleaner admin interface

## Sharing Your Plugin

Want to share with others?

1. Create a README.txt file with instructions
2. Add screenshots to a `/assets` folder
3. Upload to GitHub or WordPress.org
4. Share the ZIP file

## Security Notes

This plugin:
- ✓ Checks for direct access (`ABSPATH`)
- ✓ Uses WordPress functions properly
- ✓ Escapes output where needed
- ✓ Follows WordPress coding standards
- ✓ No database interactions
- ✓ All processing is client-side

## Next Steps

After installation:
1. Go to any page/post
2. Add the shortcode: `[aetools]`
3. Preview/publish the page
4. Test the generator
5. Enjoy!

---

**That's it! You now have a professional WordPress plugin for AETools.** 🎉

