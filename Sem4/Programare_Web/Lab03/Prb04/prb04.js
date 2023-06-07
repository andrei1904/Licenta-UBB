function register(tableID, number) {
    var table = document.getElementById(tableID);
    var cells = table.getElementsByTagName('td');
    var i;
    for (i = 0; i < cells.length; i++) {
        cells[i].addEventListener("click", function () {
            clickedItem(this, number);
        });
    }

    var cells = table.getElementsByTagName('th');
    var i;
    for (i = 0; i < cells.length; i++) {
        cells[i].addEventListener("click", function () {
            clickedItem(this, number);
        });
    }
}

function clickedItem(table, numba) {
    sortTable(table.parentNode.parentNode, table.cellIndex, numba);
}

function compare(x, y) {
    if (/^[a-zA-Z]+$/.test(x) && /^[a-zA-Z]+$/.test(y)) {
        if (x > y)
            return true;
    } else {
        if (Number(x) > Number(y)) return true;
    }
    return false;
}

function sortTable(table, n, number) {
    var rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
    switching = true;
    // Set the sorting direction to ascending:
    dir = "asc";
    /* Make a loop that will continue until
    no switching has been done: */
    while (switching) {
        // Start by saying: no switching is done:
        switching = false;
        rows = table.getElementsByTagName("TR");
        /* Loop through all table rows (except the
        first, which contains table headers): */
        for (i = 1; i < (rows.length - 1); i++) {
            // Start by saying there should be no switching:
            shouldSwitch = false;
            /* Get the two elements you want to compare,
            one from current row and one from the next: */
            x = rows[i].getElementsByTagName("TD")[n];
            y = rows[i + 1].getElementsByTagName("TD")[n];
            /* Check if the two rows should switch place,
            based on the direction, asc or desc: */
            if (dir == "asc") {
                if (!number) {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (compare(x.innerHTML, y.innerHTML)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            } else if (dir == "desc") {
                if (!number) {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else {
                    if (compare(y.innerHTML, x.innerHTML)) {
                        shouldSwitch = true;
                        break;
                    }
                }
            }
        }
        if (shouldSwitch) {
            /* If a switch has been marked, make the switch
            and mark that a switch has been done: */
            rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
            switching = true;
            // Each time a switch is done, increase this count by 1:
            switchcount++;
        } else {
            /* If no switching has been done AND the direction is "asc",
            set the direction to "desc" and run the while loop again. */
            if (switchcount == 0 && dir == "asc") {
                dir = "desc";
                switching = true;
            }
        }
    }
}