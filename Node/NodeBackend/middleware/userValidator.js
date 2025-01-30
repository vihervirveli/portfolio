const userValidator = (req, res, next) => {
    const username = req.body.username;
    const email = req.body.email;
    const name = req.body.name;
    const password = req.body.password;
    const confirmation = req.body.confirmation;


    var validRegex = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    const isValidEmail = email.match(validRegex) == true;
    const isValidUsername = username.length > 0;
    const isValidName = name.length > 0;
    const isValidPassword = password.length > 8;
    const isValidConfirmation = confirmation == true;

    
    const usernameFail = 'Username must be longer than 0 characters';
    const nameFail = 'Name must be longer than 0 characters';
    const passwordFail = 'Password must be longer than 8 characters';
    const confirmationFail = 'Must confirm password';
    const emailFail = 'Email has to be valid';
    if (!isValidUsername || !isValidName || !isValidPassword || !isValidConfirmation || !isValidEmail ){
        let errorMsg = "Bad request";
        if(!isValidUsername) errorMsg += "\n"+usernameFail;
        if(!isValidName) errorMsg += "\n"+nameFail;
        if(!isValidPassword) errorMsg += "\n"+passwordFail;
        if(!isValidConfirmation) errorMsg += "\n"+confirmationFail;
        if(!isValidEmail) errorMsg += '\n'+emailFail;

        return res.status(400).json({ success: false, msg: errorMsg });
    }

    next();
};

module.exports = userValidator;