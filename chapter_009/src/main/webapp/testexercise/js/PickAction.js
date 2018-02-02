function pickAction(buttonName, entity) {
    if (buttonName === 'Create'.toLowerCase()) {
        if (entity === 'user') {
            window.location.assign("../testexercise/create?entity=user");
        } else if (entity === 'role') {
            window.location = "../testexercise/create?entity=role";
        } else if (entity === 'address') {
            window.location = "../testexercise/create?entity=address";
        } else if (entity === 'music') {
            window.location = "../testexercise/create?entity=music";
        }
    } else if (buttonName === 'read'.toLowerCase()) {
        window.location = "../testexercise/read";
    } else if (buttonName === 'update'.toLowerCase()) {
        window.location = "../testexercise/uodate";
    } else if (buttonName === 'delete'.toLowerCase()) {
        if (entity === 'user') {
            window.location.assign("../testexercise/delete?entity=user");
        } else if (entity === 'role') {
            window.location = "../testexercise/delete?entity=role";
        } else if (entity === 'address') {
            window.location = "../testexercise/delete?entity=address";
        } else if (entity === 'music') {
            window.location = "../testexercise/delete?entity=music";
        }
    } else if (buttonName === 'Logout'.toLowerCase()) {
        window.location = "../testexercise/logout";
    }
}