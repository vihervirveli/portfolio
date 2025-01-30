//I misread e04ex2 instructions poorly and created this (working!) file to validate things on my end.
//I'm not deleting it since it's a perfectly working solution, but it won't be used in the exercises

const validator = (req, res, next) => {
    const artist = req.body.artist;
    const title = req.body.title;
    const year = req.body.year;
    const genre = req.body.genre;
    const tracks = req.body.tracks;

    const isValidArtist = artist.length > 0;
    const isValidTitle = title.length > 0;
    const isValidGenre = genre.length > 0;
    const isValidTrackCount = tracks > 0;
    const currentYear = new Date().getFullYear();
    const isValidYear = 1900 < year && year <= currentYear;
    
    const artistFail = 'Artist must be longer than 0 characters';
    const titleFail = 'Title must be longer than 0 characters';
    const genreFail = 'Genre must be longer than 0 characters';
    const trackFail = 'Number of tracks must be more than 0';
    const yearFail = 'Year must be between 1900 and this year';

    if (!isValidArtist || !isValidTitle || !isValidGenre || !isValidTrackCount || !isValidYear){
        let errorMsg = "Bad request";
        if(!isValidArtist) errorMsg += "\n"+artistFail;
        if(!isValidTitle) errorMsg += "\n"+titleFail;
        if(!genreFail) errorMsg += "\n"+genreFail;
        if(!isValidTrackCount) errorMsg += "\n"+trackFail;
        if(!isValidYear) errorMsg += "\n"+yearFail;       

        return res.status(400).json({ success: false, msg: errorMsg });
    }

    next();
};

module.exports = validator;