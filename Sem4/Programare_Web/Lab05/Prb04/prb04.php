<?php

$moves = $_GET['table'];

$array = explode(',', $moves);

if (checkColumns($array) != 1 or checkRows($array) != 1 or checkDiagonals($array) != 1)
    echo 'X';
else {
    $poz = mt_rand(0, 8);
    while ($array[$poz] != '')
        $poz = mt_rand(0, 8);
    $array[$poz] = 'O';
    if (checkColumns($array) == 1 and checkRows($array) == 1 and checkDiagonals($array) == 1)
        echo $poz;
    else {
        if (checkColumns($array) == 'X' or checkRows($array) == 'X' or checkDiagonals($array) == 'X')
            echo 'X';
        else {
            if (checkColumns($array) == 'O' or checkRows($array) == 'O' or checkDiagonals($array) == 'O')
                echo 'O' . $poz;
            else
                if (countMoves($array) == 1)
                    echo '-1';
        }
    }
}

function countMoves($moves)
{
    $movedMade = 0;
    for ($i = 0; $i < 9; $i++)
        if ($moves[$i] != '')
            $movedMade += 1;
    return 1;
}

function checkColumns($moves)
{
    for ($column = 0; $column < 3; $column++) {

        $firstCol = $moves[$column];

        if ($firstCol != '') {
            $ok = 1;
            for ($idx = $column; $idx < 9; $idx += 3) {
                if ($moves[$idx] != $firstCol) {
                    $ok = 0;
                    break;
                }
            }
            if ($ok == 1)
                return $firstCol;
        }

    }
    return 1;
}

function checkRows($moves)
{
    for ($row = 0; $row < 9; $row += 3) {
       $firstRow = $moves[$row];

        if ($firstRow == '') {
            return 1;
        } else {
            $ok = 1;
            for ($idx = $row; $idx < $row + 3; $idx += 1) {
                if ($moves[$idx] != $firstRow) {
                    $ok = 0;
                    break;
                }
            }
            if ($ok == 1)
                return $firstRow;
        }
    }
    return 1;
}

function checkDiagonals($moves)
{
    $diag = 0;
    $firstValue = $moves[$diag];
    if ($firstValue == '') {
        return 1;
    } else {
        $ok = 1;
        for ($idx = $diag; $idx < 9; $idx += 4) {
            if ($moves[$idx] != $firstValue) {
                $ok = 0;
                break;
            }
        }
        if ($ok == 1)
            return $firstValue;
    }

    $firstValue = $moves[2];
    if ($firstValue == '') {
        return 1;
    } else {
        $ok = 1;
        for ($idx = 2; $idx <= 9 - sqrt(9); $idx += sqrt(9) - 1) {
            if ($moves[$idx] != $firstValue) {
                $ok = 0;
                break;
            }
        }
        if ($ok == 1)
            return $firstValue;
    }

    if ($ok == 0)
        return 1;
    
    return 0;
}
