const axios = require('axios');
const result = document.querySelector('.result');
const baseUrl = `http://localhost:5001/api`;
const fetchAlbums = async () => {
    try {
        const { data } = await axios.get(`${baseUrl}/albums`);

        const albums = data.data.map((album) => {
            return `<ul><li>Artist: ${album.artist}</li><li>Title: ${album.title}</li><li>Genre: ${album.genre}</li><li>Track count: ${album.tracks}</li></ul>`;
        });
        result.innerHTML = albums.join('');
    } catch (error) {
        console.log(error);
        result.innerHTML = `<div class="alert alert-danger">Could not fetch data</div>`;
    }
};
fetchAlbums();