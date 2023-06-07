let table = [[3, 8, 9, 5], [7, 13, 6, 15], [10, " ", 14, 4], [2, 11, 1, 12]];
let zero = [2, 1];


function fill_table() {
    $('#1').html(table[0][0]);
    $('#2').html(table[0][1]);
    $('#3').html(table[0][2]);
    $('#4').html(table[0][3]);
    $('#5').html(table[1][0]);
    $('#6').html(table[1][1]);
    $('#7').html(table[1][2]);
    $('#8').html(table[1][3]);
    $('#9').html(table[2][0]);
    $('#10').html(table[2][1]);
    $('#11').html(table[2][2]);
    $('#12').html(table[2][3]);
    $('#13').html(table[3][0]);
    $('#14').html(table[3][1]);
    $('#15').html(table[3][2]);
    $('#16').html(table[3][3]);
}

$(document).ready(function () {

    fill_table();
    $(document).keydown(function (e) {
        switch (e.which) {
            case 37: {
                if (zero[1] !== 0) {
                    table[zero[0]][zero[1]] = table[zero[0]][zero[1] - 1];
                    table[zero[0]][zero[1] - 1] = " ";
                    zero[1]--;
                    fill_table();
                }
            }
                break;

            case 38: {
                if (zero[0] !== 0) {
                    table[zero[0]][zero[1]] = table[zero[0] - 1][zero[1]];
                    table[zero[0] - 1][zero[1]] = " ";
                    zero[0]--;
                    fill_table();
                }
            }
                break;

            case 39: {
                if (zero[1] !== 3) {
                    table[zero[0]][zero[1]] = table[zero[0]][zero[1] + 1];
                    table[zero[0]][zero[1] + 1] = " ";
                    zero[1]++;
                    fill_table();
                }
            }
                break;

            case 40: {
                if (zero[0] !== 3) {
                    table[zero[0]][zero[1]] = table[zero[0] + 1][zero[1]];
                    table[zero[0] + 1][zero[1]] = " ";
                    zero[0]++;
                    fill_table();
                }
            }
                break;

            default:
                return;
        }
        e.preventDefault();
    });
});