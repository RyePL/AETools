// App Controller - Connects UI to Generators

document.addEventListener('DOMContentLoaded', function() {
    const methodSelect = document.getElementById('methodSelect');
    const argsInput = document.getElementById('argsInput');
    const executeButton = document.getElementById('executeButton');
    const outputArea = document.getElementById('outputArea');
    
    // Execute button click handler
    executeButton.addEventListener('click', function() {
        try {
            const selectedMethod = methodSelect.value;
            const argsText = argsInput.value.trim();
            
            // Clear previous output
            outputArea.textContent = '';
            outputArea.classList.remove('error');
            
            // Parse arguments
            const arguments = argsText === '' ? [] : argsText.split(/\s+/);
            
            let result = '';
            
            try {
                // Execute the selected method
                if (selectedMethod === 'randomChar') {
                    result = RandomChar.main(arguments);
                } else if (selectedMethod === 'randomShip') {
                    result = RandomShip.main(arguments);
                }
                
                // Display results
                outputArea.textContent = result;
                
            } catch (ex) {
                outputArea.textContent = 'Error executing method: ' + ex.message + '\n' + 
                                       'Stack trace:\n' + ex.stack;
                outputArea.classList.add('error');
            }
            
        } catch (ex) {
            outputArea.textContent = 'Error: ' + ex.message + '\n' + 
                                   'Stack trace:\n' + ex.stack;
            outputArea.classList.add('error');
        }
    });
    
    // Allow Enter key in input field to execute
    argsInput.addEventListener('keypress', function(e) {
        if (e.key === 'Enter') {
            executeButton.click();
        }
    });
    
    // Update hint text based on selected method
    methodSelect.addEventListener('change', function() {
        const hint = argsInput.nextElementSibling;
        if (methodSelect.value === 'randomChar') {
            hint.textContent = 'For randomChar: try entering a class name (Barbarian, Bard, Cleric, Druid, Fighter, Monk, Paladin, Ranger, Rogue, Sorcerer, Warlock, Wizard)';
        } else {
            hint.textContent = 'For randomShip: no arguments needed (leave empty)';
        }
    });
});

