function makeEditable(id) {

    var element = document.getElementById(id);

    element.setAttribute("class", "table-warning");

    document.getElementById('saveBtn' + id).style.visibility = "visible";
    document.getElementById('editBtn' + id).style.visibility = "collapse";

    var inputs = document.getElementsByClassName('inp' + id);
    for (i in inputs) {
        inputs[i].disabled = false;
        inputs[i].style.display = "inline";
    }

}