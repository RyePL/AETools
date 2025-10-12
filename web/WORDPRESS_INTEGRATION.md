# WordPress Integration Guide for AETools

This guide explains how to embed the AETools Generator into your WordPress website.

## Files Overview

You'll need these files:
- `index.html` - Main HTML interface
- `styles.css` - Styling
- `generators.js` - Generator logic (randomChar and randomShip)
- `app.js` - Controller that connects UI to generators

## Method 1: Direct File Embedding (Recommended)

This method allows you to embed the tool directly into any WordPress page or post.

### Step 1: Upload Files to WordPress

1. Log into your WordPress admin panel
2. Go to **Media > Add New**
3. Upload all three JavaScript/CSS files:
   - `styles.css`
   - `generators.js`
   - `app.js`
4. After uploading, click each file and copy its URL. You'll need these URLs.

### Step 2: Create a WordPress Page

1. Go to **Pages > Add New** (or edit an existing page)
2. Switch to the **Code Editor** (or **HTML** mode if using Classic Editor)
3. Paste the following HTML code:

```html
<!-- AETools Generator Styles -->
<link rel="stylesheet" href="YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/styles.css">

<!-- AETools Generator Container -->
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
                    <input 
                        type="text" 
                        id="argsInput" 
                        placeholder="Enter arguments separated by spaces (leave empty for no arguments)"
                    >
                    <small class="hint">For randomChar: try entering a class name (Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard)</small>
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

<!-- AETools Generator Scripts -->
<script src="YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/generators.js"></script>
<script src="YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/app.js"></script>
```

4. Replace `YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/` with the actual URLs you copied in Step 1
5. Click **Publish** or **Update**

## Method 2: Using a Custom HTML Block (Gutenberg Editor)

If you're using the WordPress Block Editor (Gutenberg):

1. Create or edit a page
2. Click the **+** button to add a new block
3. Search for and select **Custom HTML** block
4. Paste the HTML code from Method 1 (with correct file URLs)
5. Publish the page

## Method 3: Using a WordPress Plugin

You can also use plugins to make integration easier:

### Option A: Insert Headers and Footers Plugin

1. Install the **Insert Headers and Footers** plugin
2. Go to **Settings > Insert Headers and Footers**
3. In the **Footer** section, add:

```html
<script src="YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/generators.js"></script>
<script src="YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/app.js"></script>
```

4. Use the **Custom HTML** block or **Code Snippets** to add the HTML interface where you want it

### Option B: Create a Shortcode

Add this to your theme's `functions.php` file:

```php
function aetools_generator_shortcode() {
    $css_url = 'YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/styles.css';
    $gen_js_url = 'YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/generators.js';
    $app_js_url = 'YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/app.js';
    
    wp_enqueue_style('aetools-style', $css_url);
    wp_enqueue_script('aetools-generators', $gen_js_url, array(), '1.0', true);
    wp_enqueue_script('aetools-app', $app_js_url, array('aetools-generators'), '1.0', true);
    
    ob_start();
    ?>
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
                        <input 
                            type="text" 
                            id="argsInput" 
                            placeholder="Enter arguments separated by spaces (leave empty for no arguments)"
                        >
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
    <?php
    return ob_get_clean();
}
add_shortcode('aetools', 'aetools_generator_shortcode');
```

Then use the shortcode in any page or post:
```
[aetools]
```

## Method 4: Upload as a Custom Plugin

For a more professional integration:

1. Create a folder named `aetools-generator`
2. Inside that folder, create a file named `aetools-generator.php`:

```php
<?php
/*
Plugin Name: AETools Generator
Description: Random character and ship generator for AETools
Version: 1.0
Author: Your Name
*/

function aetools_enqueue_scripts() {
    wp_enqueue_style('aetools-style', plugins_url('styles.css', __FILE__));
    wp_enqueue_script('aetools-generators', plugins_url('generators.js', __FILE__), array(), '1.0', true);
    wp_enqueue_script('aetools-app', plugins_url('app.js', __FILE__), array('aetools-generators'), '1.0', true);
}

function aetools_generator_shortcode() {
    aetools_enqueue_scripts();
    
    ob_start();
    include plugin_dir_path(__FILE__) . 'template.php';
    return ob_get_clean();
}
add_shortcode('aetools', 'aetools_generator_shortcode');
```

3. Create `template.php` in the same folder with the HTML interface
4. Copy `styles.css`, `generators.js`, and `app.js` into the same folder
5. Zip the folder
6. Upload via **Plugins > Add New > Upload Plugin**
7. Activate the plugin
8. Use `[aetools]` shortcode anywhere

## Troubleshooting

### CSS Conflicts
If the styling looks broken, your WordPress theme might have conflicting styles. Try adding this to your Custom CSS:

```css
.aetools-container * {
    box-sizing: border-box;
}
```

### JavaScript Not Working
1. Check browser console for errors (F12 > Console tab)
2. Verify all file URLs are correct
3. Make sure files uploaded successfully
4. Try clearing WordPress cache

### Background Gradient Issue
If you want the gradient background only around the tool (not the whole page), modify the CSS:

```css
.aetools-container {
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    padding: 20px;
    border-radius: 12px;
}

.aetools-container .container {
    max-width: 800px;
    margin: 0 auto;
}
```

## Styling Customization

You can customize colors by editing `styles.css`:

- **Primary color**: Search for `#667eea` and `#764ba2` (gradient colors)
- **Text color**: Search for `#2d3748`
- **Border color**: Search for `#e2e8f0`

## Security Considerations

- All code runs client-side (in the user's browser)
- No data is sent to servers
- No database interactions
- Safe to use on any WordPress site

## Support

For issues or questions, refer to the original Java source code or contact the developer.

---

**Note**: Replace all instances of `YOUR_WORDPRESS_URL/wp-content/uploads/YEAR/MONTH/` with the actual paths to your uploaded files.

