const Album = require('../models/Album');
const User = require('../models/User');
var jwt = require('jsonwebtoken');
require('dotenv').config();
var bcrypt = require('bcryptjs');

const getAllAlbums = async (req, res) => {
    const albums = await Album.find({});
    res.status(200).json({ albums });
};


const getOneAlbum = async (req, res) => {
    const { id } = req.params;
    const album = await Album.findOne({_id: id});
    return res.status(200).json({album});
};


const filterByReleaseYear = async (req,res) => {
    const { year } = req.query;
    const albums = await Album.find({year: year});
    res.status(200).json({ albums });
    
};

const fieldFilter = async (req,res) => {
    const { fields } = req.query;
    const includedFields = fields.split(",");
    const includedFieldsWithoutId = [...includedFields, "-_id"];
    
    const albums = await Album.find({}).select(includedFieldsWithoutId);
    res.status(200).json({ albums });
    
};


const searchBy = async (req,res) => {
    const { pattern } = req.query;
    const albums = await Album.find({
        "$or": [
            {artist: {'$regex': pattern, '$options': 'i'}},
            {title: {'$regex': pattern, '$options': 'i'}}
        ]
    });
        
    res.status(200).json({ albums });
 
};

const createAlbum = async (req, res) => {
    const album = await Album.create(req.body);
    res.status(201).json({ album });

};

const updateAlbum = async (req, res) => {
    const { id } = req.params;
    const updatedData = req.body;
    const updatedAlbum = await Album.findByIdAndUpdate(id, updatedData,{
        new:true,
        runValidators:true
    });
    res.status(200).json({updatedAlbum});
      
};

const deleteAlbum = async (req, res) => {
    const { id } = req.params;
    const deletion = await Album.deleteOne({_id: id});
    const msgFromServer = `Album deleted! ${deletion}`;
    res.status(200).json({msg:msgFromServer});

};




const sortAlbumsBy = async (req,res) => {
    const { criteria, order } = req.query;
    const criteriaDict = {
        artist: 'artist',
        title: 'title',
        year: 'year',
        genre: 'genre',
        tracks: 'tracks',
    };

    if (!Object.prototype.hasOwnProperty.call(criteriaDict, criteria)){
        res.status(400).json({error: 'Invalid criteria'});
        return;
    }

    const fieldToSort = criteriaDict[criteria];
    const sortOrder = order === "desc" ? -1 : 1;
    
    let results = await Album.find({}).sort({[fieldToSort]: sortOrder});
    res.status(200).json(results);
    
};

const registerUser = async (req, res) => {
    //by now, validatorUser has failed the app
    //if the info isn't valid
    // const password = req.body.password;
    const salt = bcrypt.genSaltSync(10);
    const hashedPassword = bcrypt.hashSync(req.body.password, salt);
    var newUser = req.body;
    newUser.albums = [];
    newUser.passwordHash = hashedPassword;
    delete newUser.password;
    const user = await User.create(newUser);
    res.status(201).json({ user });
};

const login = async (req, res) => {
    const username = req.body.username;
    const password = req.body.password;
    
    const foundUser = await User.findOne({username: username});
    const isPasswordMatch = bcrypt.compareSync(password, foundUser.passwordHash);
    if(!isPasswordMatch){
        return res.status(401).json({msg: 'Invalid login information'});
    }
   
    const id = foundUser.id;
    const ACCESS_TOKEN_SECRET = process.env.ACCESS_TOKEN_SECRET;
    const token = jwt.sign({id, username}, ACCESS_TOKEN_SECRET,{expiresIn:'7d'});
    res.status(200).json({msg:'user login created', token});
};

module.exports = {
    deleteAlbum,
    createAlbum,
    updateAlbum,
    getAllAlbums,
    getOneAlbum,
    sortAlbumsBy,
    filterByReleaseYear,
    fieldFilter,
    searchBy,
    registerUser,
    login
};