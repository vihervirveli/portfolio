require('express-async-errors');
const express = require('express');
const path = require('path');
const albums = require('./routes/albums');
const connectMongoDB = require('./db/mongodb');
require('dotenv').config();
const app = express();
app.use(express.json());
app.use('/api/albums', albums);
app.use(express.static('./public'));
const {errorHandler} = require('./middleware/errorHandler');
app.use(errorHandler);
const PORT = 5001;

app.get('/', (req, res) => {
    res.sendFile(path.resolve(__dirname, './public/index.html'));
});



const start = async () => {
    try {
        const dbURL = process.env.CONN_STRING;
        await connectMongoDB(dbURL);
    
        app.listen(PORT, () => console.log(`Server listening on port ${PORT}`));
    } catch (error) {
        console.log(error);
    }
};

start();