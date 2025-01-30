const User = require('../models/User');

const emailDuplicateFinder = (req, res, next) => {
    const newEmail = req.body.email;
    const otherUser =  User.findOne({email: newEmail});
    if (otherUser){
        return res.status(409).json({ success: false, msg: 'Email already exists in the database. Try logging in with your existing account' });
    }
    next();
};

module.exports = emailDuplicateFinder;