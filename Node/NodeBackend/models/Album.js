const mongoose = require('mongoose');
const { Schema } = mongoose;

const AlbumSchema = new mongoose.Schema({
    artist: {type:String, required: [true, 'An artist name must be provided'],
        trim: true, 
        maxLength:[30, 'Input only max 30 characters']},
    title: {type:String, required: [true, 'An album title must be provided'],
        trim: true, 
        maxLength:[30, 'Input only max 30 characters']},
    year: {type: Number, min:[1900, 'Year must be higher than 1900'],
        max:[new Date().getFullYear(), 'Year must be either current year or earlier'] 
    },
    genre: {type:String, required: [true, "A genre's name must be provided"],
        trim: true, 
        maxLength:[30, 'Input only max 30 characters']},
    tracks: {type: Number, min: [0, 'Track count must be more than 0']},
    owners: [{type: Schema.Types.ObjectId, ref: 'User'}]
});
  
const Album = mongoose.model('Album', AlbumSchema);
module.exports = Album;

//year: {type: Number, validate: {
//     validator: function(value) {
//         const currentYear = new Date().getFullYear();
//         return 1900 <= value && value <= currentYear;
//     },
//     message: 'Year must be between 1900 and current year'
// }}