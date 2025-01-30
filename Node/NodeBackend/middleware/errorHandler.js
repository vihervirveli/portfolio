class APIError extends Error {
    constructor(message, statusCode){
        super(message);
        this.statusCode = statusCode;
    }
}

const createAPIError = (msg, statusCode) => {
    return new APIError(msg, statusCode);
};

// eslint-disable-next-line no-unused-vars
const errorHandler = (err, req, res, next) => {
    // return next(createAPIError(err.message, res.statusCode));
    if (err instanceof APIError){
        return res.status(err.statusCode).json({ msg: err.message });
    }
    return res.status(500).json({msg: 'There was an error, please try again'});
    
};

module.exports = {APIError, createAPIError, errorHandler};