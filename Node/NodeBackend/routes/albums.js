const express = require('express');
const router = express.Router();
const logger = require('../middleware/logger');
const authenticator = require('../middleware/authenticator');
const userValidator = require('../middleware/userValidator');
const emailDuplicateFinder = require('../middleware/emailDuplicateFinder');
const {
    getAllAlbums,
    getOneAlbum,
    createAlbum,
    registerUser,
    updateAlbum,
    deleteAlbum,
    sortAlbumsBy,
    filterByReleaseYear,
    fieldFilter,
    searchBy,
    login
} = require('../controllers/albums');

router.get('/search', logger, searchBy); 
router.get('/fieldfilter', logger, fieldFilter); 
router.get('/yearfilter', logger, filterByReleaseYear);  
router.get('/sort', logger, sortAlbumsBy);
router.get('/', logger, getAllAlbums);
router.get('/:id', logger, authenticator, getOneAlbum);
router.post('/', logger, authenticator, createAlbum);
router.post('/register', logger, emailDuplicateFinder, userValidator, registerUser);
router.post('/login', logger, login);
router.put('/:id', logger, authenticator, updateAlbum);
router.delete('/:id', logger, authenticator, deleteAlbum);

module.exports = router;