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
    } else if (buttonName === 'find'.toLowerCase()) {
        if (entity === 'role') {
            window.location = "../testexercise/find?entity=role";
        } else if (entity === 'address') {
            window.location = "../testexercise/find?entity=address";
        } else if (entity === 'music') {
            window.location = "../testexercise/find?entity=music";
        }
    } else if (buttonName === 'update'.toLowerCase()) {
        if (entity === 'role') {
            window.location = "../testexercise/update?entity=role";
        } else if (entity === 'address') {
            window.location = "../testexercise/update?entity=address";
        } else if (entity === 'music') {
            window.location = "../testexercise/update?entity=music";
        } else if (entity === 'user') {
            window.location = "../testexercise/update?entity=user";
        }
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