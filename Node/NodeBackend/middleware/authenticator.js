var jwt = require('jsonwebtoken');
const { APIError } = require('./errorHandler');
const authenticator = (req, res, next) => {
    const authHeader = req.headers.authorization;
    if (!authHeader || !authHeader.startsWith('Bearer')){
        throw new APIError('No token in header', 401);
    }
    const token = authHeader.split(' ')[1];
    try{
        const decoded = jwt.verify(token, process.env.ACCESS_TOKEN_SECRET);
        const { _id, username} = decoded;
        req.user = {_id, username};
        next();
    }
    catch (error){
        throw new APIError(`Not authorized ${error}`, 401);
    }
    
};

module.exports = authenticator;