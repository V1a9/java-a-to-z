function validateForm() {

    var inputs = document.getElementsByTagName("input");
    var i;
    for (i=0;i<inputs.length;i++) {
        if(inputs[i].value === ""){
            alert("Please enter " + inputs[i].name);
            return false;
        }
    }
    var select = document.getElementsByTagName("select");
    for (i=1;i<select.length;i++) {
        if(select[i].value === ""){
            alert("Please choose " + select[i].name);
            return false;
        }
    }
}