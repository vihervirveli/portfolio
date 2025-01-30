const mongoose = require('mongoose');
const { Schema } = mongoose;

const UserSchema = new mongoose.Schema({
    username: { type: String, required: [true, 'A username must be provided'] },
    name: {
        type: String,
        maxlength: [40, 'Name cannot be longer than 40 characters'],
        required: [true, "A name must be provided"]
    },
    email: {
        type:String,
        required: [true, "An email must be valid"]
    },
    passwordHash: {type:String, required: [true, "Must provide passwordHash"]},
    confirmation: {type: Boolean, required: [true, "Must confirm password"] },
    albums: [{ type: Schema.Types.ObjectId, ref: 'Album' }]
});
  
const User = mongoose.model('User', UserSchema);
module.exports = User;