function move(elem, selected, index) {
    let list1 = document.getElementById("list1");
    let list2 = document.getElementById("list2");
    let element = document.createElement("option");
    element.text = elem;

    if (selected === 1) {
        list1.remove(index);
        list2.add(element);
    }

    if (selected === 2) {
        list2.remove(index);
        list1.add(element);
    }
}