function validateCreateForm() {
    if (document.createForm.name.value === "") {
        alert('Please enter user name.');
        return false;
    }
    if (document.createForm.login.value === "") {
        alert('Please enter user login.');
        return false;
    }
    if (document.createForm.password.value === "") {
        alert('Please enter user password.');
        return false;
    }
    if (document.createForm.email.value === "") {
        alert('Please enter user email.');
        return false;
    }
    if (document.createForm.country.value === "") {
        alert('Please enter user country.');
        return false;
    }
    if (document.createForm.city.value === "") {
        alert('Please enter user city.');
        return false;
    }
    if (document.createForm.date.value === "") {
        alert('Please enter user create date.');
        return false;
    }
}