$(document).ready(function () {
    $("#list1").dblclick(function () {
        $("#list1 option:selected").remove().appendTo("#list2");
    });

    $("#list2").dblclick(function () {
        $("#list2 option:selected").remove().appendTo("#list1");
    });
});