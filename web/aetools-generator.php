<?php
/*
Plugin Name: AETools Generator
Plugin URI: https://your-website.com
Description: Random character and ship generator for AETools - Generate D&D characters and fantasy ships
Version: 1.0.0
Author: Your Name
Author URI: https://your-website.com
License: GPL v2 or later
License URI: https://www.gnu.org/licenses/gpl-2.0.html
Text Domain: aetools-generator
*/

// Exit if accessed directly
if (!defined('ABSPATH')) {
    exit;
}

/**
 * Enqueue plugin styles and scripts
 */
function aetools_enqueue_scripts() {
    wp_enqueue_style(
        'aetools-style', 
        plugins_url('styles.css', __FILE__),
        array(),
        '1.0.0'
    );
    
    wp_enqueue_script(
        'aetools-generators', 
        plugins_url('generators.js', __FILE__),
        array(),
        '1.0.0',
        true
    );
    
    wp_enqueue_script(
        'aetools-app', 
        plugins_url('app.js', __FILE__),
        array('aetools-generators'),
        '1.0.0',
        true
    );
}

/**
 * Shortcode handler for [aetools]
 */
function aetools_generator_shortcode($atts) {
    // Enqueue our scripts and styles
    aetools_enqueue_scripts();
    
    // Parse shortcode attributes
    $atts = shortcode_atts(array(
        'title' => 'AETools Generator'
    ), $atts, 'aetools');
    
    // Start output buffering
    ob_start();
    
    // Include the template
    include plugin_dir_path(__FILE__) . 'template.php';
    
    // Return the buffered content
    return ob_get_clean();
}
add_shortcode('aetools', 'aetools_generator_shortcode');

/**
 * Add settings link on plugin page
 */
function aetools_plugin_settings_link($links) {
    $settings_link = '<a href="https://github.com/your-repo">Documentation</a>';
    array_unshift($links, $settings_link);
    return $links;
}
add_filter('plugin_action_links_' . plugin_basename(__FILE__), 'aetools_plugin_settings_link');

/**
 * Plugin activation hook
 */
function aetools_activate() {
    // Add any activation tasks here
    flush_rewrite_rules();
}
register_activation_hook(__FILE__, 'aetools_activate');

/**
 * Plugin deactivation hook
 */
function aetools_deactivate() {
    // Add any deactivation tasks here
    flush_rewrite_rules();
}
register_deactivation_hook(__FILE__, 'aetools_deactivate');

