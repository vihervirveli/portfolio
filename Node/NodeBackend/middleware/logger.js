const logger = (req, res, next) => {
    console.log('Method:', req.method);
    console.log('Url:  ', req.url);
    next();
};

module.exports = logger;