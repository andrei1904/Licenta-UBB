$(document).ready(function () {
    $('#table2').on('click', 'th', function () {
        let index = $(this).index(),
            rows = [],
            thClass = $(this).hasClass('asc') ? 'desc' : 'asc';

        $('#table2 th').removeClass('asc desc');
        $(this).addClass(thClass);

        $('#table2 tbody tr').each(function (index, row) {
            rows.push($(row).detach());
        });

        rows.sort(function (a, b) {
            let aValue = $(a).find('td').eq(index).text(),
                bValue = $(b).find('td').eq(index).text();

            return aValue > bValue
                ? 1
                : aValue < bValue
                    ? -1
                    : 0;
        });

        if ($(this).hasClass('desc')) {
            rows.reverse();
        }

        $.each(rows, function (index, row) {
            $('#table2 tbody').append(row);
        });
    });

    $("#table1 tbody tr td:first-child").click(function () {
        let swapped = true;

        do {
            swapped = false;

            for (let c = 2; c <= $(this).parent().children().length - 1; c++) {
                let cellCurrValue = $(this).parent().children("td:nth-child(" + c + ")").text(),
                    cellNextValue = $(this).parent().children("td:nth-child(" + (c + 1) + ")").text();

                if ($.isNumeric(cellCurrValue))
                    cellCurrValue = parseInt(cellCurrValue);

                if ($.isNumeric(cellNextValue))
                    cellNextValue = parseInt(cellNextValue);

                if (!$(this).hasClass("sorted-asc") &&
                    cellCurrValue > cellNextValue) {
                    for (let r = 1; r <= $(this).parent().parent().children().length; r++) {
                        let temp = $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text();

                        $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text(
                            $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text());

                        $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text(temp);
                    }

                    swapped = true;
                }
                else if (!$(this).hasClass("sorted-desc") && $(this).hasClass("sorted-asc") &&
                    cellCurrValue < cellNextValue) {
                    for (let r = 1; r <= $(this).parent().parent().children().length; r++) {
                        let temp = $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text();

                        $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + (c + 1) + ")").text(
                            $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text());

                        $(this).parent().parent().children("tr:nth-child(" + r + ")").children("td:nth-child(" + c + ")").text(temp);
                    }

                    swapped = true;
                }
            }
        } while (swapped);

        if ($(this).hasClass("sorted-asc"))
            $(this).removeClass("sorted-asc").addClass("sorted-desc");
        else
            $(this).removeClass("sorted-desc").addClass("sorted-asc");
    });
})
;
