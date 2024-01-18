/* eslint-disable prettier/prettier */
import React, {useState, useCallback, Alert} from 'react';
import {Text, Button, View, Image, StyleSheet} from 'react-native';
import YoutubePlayer from "react-native-youtube-iframe";

const VideoScreen = props => {
  // const [playing, setPlaying] = useState(false);
  const videoId = props.route.params.url;

  // const togglePlaying = useCallback(() => {
  //   setPlaying((prev) => !prev);
  // }, []);
  return (
    <View style={{flex: 1}}>
       
     <YoutubePlayer
        height={300}
        videoId={videoId}
        
      />
      {/* <Button title={playing ? "pause" : "play"} onPress={togglePlaying} /> */}
    </View>
  );
};

export default VideoScreen;
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
    vid: {
      alignSelf: 'stretch', height: 300
    },
  });
