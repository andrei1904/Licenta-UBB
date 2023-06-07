let array = ['1', '3', '4', '2', '3', '1', '5', '4', '6', '5', '2', '6'];
let selectedValues = [];
let selectedIds = [];
let selectedValuesCount = 0;


function newGame() {
    for (let i = 0; i < array.length; i++) {
        $("#board").append('<div class="innerDiv" id="tile' + i + '" onclick="turn(this,\'' + array[i] + '\')"></div>');
    }
}

function turn(tile, value) {
    if (tile.innerHTML === "" && selectedValues.length < 2) {
        tile.innerHTML = value;
        if (selectedValues.length === 0) {
            selectedValues.push(value);
            selectedIds.push(tile.id);
        }
        else if (selectedValues.length === 1) {
            selectedValues.push(value);
            selectedIds.push(tile.id);
            if (selectedValues[0] === selectedValues[1]) {
                selectedValuesCount += 2;
                selectedValues = [];
                selectedIds = [];

                if (selectedValuesCount === array.length) {
                    alert("You won!");
                }
            }
            else {
                function turnBack() {
                    let tile1 = $(document).find("#" + selectedIds[0] + "");
                    let tile2 = $(document).find("#" + selectedIds[1] + "");

                    tile1.html("");
                    tile2.html("");

                    selectedValues = [];
                    selectedIds = [];
                }

                setTimeout(turnBack, 500);
            }
        }
    }
}