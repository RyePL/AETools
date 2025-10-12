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

