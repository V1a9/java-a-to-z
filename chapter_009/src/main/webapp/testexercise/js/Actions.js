function pickAction(buttonName) {
    if (buttonName === 'Create'.toLowerCase()) {
        window.alert('Create');
    } else if (buttonName === 'read'.toLowerCase()) {
        window.alert('Read');
    } else if (buttonName === 'update'.toLowerCase()) {
        window.alert('Update');
    } else if (buttonName === 'delete'.toLowerCase()) {
        window.alert('Delete');
    } else if (buttonName === 'Logout'.toLowerCase()) {
        window.location = "../testexercise/login";
    }
}