function confirmation() {
    var result = true;
    if (!confirm("Are you sure?")) {
        result = false;
    }
    return result;
}