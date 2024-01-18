/* eslint-disable prettier/prettier */
import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import MovieListScreen from './MovieListScreen';
import MovieDetailScreen from './MovieDetailScreen';
import VideoScreen from './VideoScreen';
const Stack = createNativeStackNavigator();

function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen
          name="MoviesList"
          component={MovieListScreen}
          options={{ title: 'MovieList' }}
        />
        <Stack.Screen
        name="MovieDetails"
        component={MovieDetailScreen}
        options={{ title: 'MovieDetails' }}
        />
         <Stack.Screen
        name="Video"
        component={VideoScreen}
        options={{ title: 'Video' }}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default App;
