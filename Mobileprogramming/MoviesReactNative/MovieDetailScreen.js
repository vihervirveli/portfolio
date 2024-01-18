/* eslint-disable prettier/prettier */
import React, {useEffect, useState} from 'react';
import axios from 'axios';
import {
  Text,
  View,
  Image,
  StyleSheet,
  TouchableHighlight,
} from 'react-native';
import { Link } from '@react-navigation/native';

export default function MovieDetailScreen(props) {
    const [movieDetails, setMovieDetails] = useState();
    const { route } = props;
    const { movie } = route.params;
    const id = movie.id;
    const API_KEY = "MY API KEY";
    const url = `https://api.themoviedb.org/3/movie/${id}?api_key=${API_KEY}&append_to_response=videos`
    let IMAGEPATH = 'http://image.tmdb.org/t/p/w500';
    let imageurl = IMAGEPATH + movie.backdrop_path;


    useEffect(() => {
      axios
        .get(url)
        .then(response => {
          // check console - a movie data should be visible there
          
          console.log(response.data);
          setMovieDetails(response.data);
        });
    }, [url]);


 

    if (movieDetails){
      const videosArray = movieDetails.videos.results;
      let movieVideos = videosArray.map(function(video,index){
        
        return (
          // <TouchableHighlight onPress={_ => itemPressed(index)}
          //             underlayColor="lightgray" key={index}>
          <Link style={styles.link} to={{screen: 'Video', params: { url: video.key}}} key={index}>{video.name}</Link>
        // </TouchableHighlight>
        );
      });
    return (
        <View>
          <Image source={{uri: imageurl}} style={styles.image}  />
          <Text style={styles.title}>{movieDetails.title}</Text>
          <Text style={styles.text}>{movieDetails.release_date}</Text>
          <Text style={styles.text}>{movieDetails.overview}</Text>
         <Text style={styles.text}>Genres: {movieDetails.genres.map(genre => genre.name).join(' ')}</Text>
          <Text style={styles.text}>Runtime: {movieDetails.runtime} min</Text>
          <Text style={styles.text}>Homepage: {movieDetails.homepage} </Text>
          <Text style={styles.text}>Videos:</Text>
          {
            movieVideos
          }
        </View>


      );
    }
    return null;
}
const styles = StyleSheet.create({
    image: {
      aspectRatio: 670 / 250,
    },
    title: {
      fontWeight: 'bold',
      fontSize: 15,
    },
    text: {
      fontSize: 12,
      flexWrap: 'wrap',
    },
    link: {
      color: 'blue',
    },
  });
