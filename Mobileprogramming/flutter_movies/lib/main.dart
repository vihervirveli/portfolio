import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:flutter/foundation.dart';
import 'dart:convert';
import 'package:youtube_player_flutter/youtube_player_flutter.dart';
import 'package:url_launcher/url_launcher.dart';

void main() {
  runApp(const MyApp());
}

class Video {
  final String key;
  final String name;

  const Video({ required this.key, required this.name});

  factory Video.fromJson(Map<String, dynamic> json){
    String key = json['key'];
    String name = json['name'];
    return Video(key: key,
    name: name,
    );
  }


}

class Movie {
  final int id;
  final String title;
  final String imageUrl;
  final String releaseDate;
  final String movieText;
  
  const Movie({
    required this.id,
    required this.title,
    required this.imageUrl,
    required this.releaseDate,
    required this.movieText,
  
  });

  factory Movie.fromJson(Map<String, dynamic> json){
    return Movie(id: json['id'] as int,
    title: json['title'],
    imageUrl: json['poster_path'],
    releaseDate: json['release_date'],
    movieText: json['overview']
    
    );
  }
}

class MovieDetails {
  final int id;
  final String title;
  final String imageUrl;
  final String releaseDate;
  final String movieText;
  final String homepage;
  final int runtime;
  final List<dynamic> genres;
  final List<Video> videos;

  const MovieDetails({
    required this.id,
    required this.title,
    required this.imageUrl,
    required this.releaseDate,
    required this.movieText,
    required this.homepage,
    required this.runtime,
    required this.genres,
    required this.videos
  });

  factory MovieDetails.fromJson(Map<String, dynamic> json){
    return MovieDetails(id: json['id'] as int,
    title: json['title'],
    imageUrl: json['backdrop_path'],
    releaseDate: json['release_date'],
    movieText: json['overview'],
    homepage: json['homepage'],
    runtime: json['runtime'],
    genres: (json['genres'] as List<dynamic>).map<String>((genre) => genre['name'] as String).toList(),
    videos: (json['videos']['results'] as List<dynamic>).map<Video>((video) => Video.fromJson(video)).toList()
    );
  }

  void printVideoIds(){
    for (Video video in videos){
      print(video.key);
    }
  }
}

String apiKey = "MY_API_KEY";
List<Movie> parseMovies(String responseBody) {
  final parsed = jsonDecode(responseBody)['results'].cast<Map<String, dynamic>>();
  return parsed.map<Movie>((json) => Movie.fromJson(json)).toList();
}

Future<List<Movie>> fetchMovies(http.Client client) async {

  final response =  await client.get(Uri.parse('https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}&append_to_response=videos'));
  return compute(parseMovies, response.body);
}



Future<MovieDetails> fetchSingleMovie(http.Client client, int id) async {
  final response = await client.get(Uri.parse('https://api.themoviedb.org/3/movie/$id?api_key=${apiKey}&append_to_response=videos'));
  if (response.statusCode == 200){
    return MovieDetails.fromJson(jsonDecode(response.body));
  }
 else {
  throw Exception('Failed to load movie ${response.statusCode}');
 }
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Movie App',
      theme: ThemeData(
     
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Movie App Home'),
    );
  }
}

class MyHomePage extends StatelessWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  Widget build(BuildContext context){
    return Scaffold(
      appBar: AppBar(
        title: Text(title),
      ),
    body: FutureBuilder<List<Movie>>(
      future: fetchMovies(http.Client()),
      builder: (context, snapshot) {
      if(snapshot.hasError){
        print('Error ${snapshot.error}');
        return const Center(
          
          child: Text('An error has occurred!'),
        );
      } else if (snapshot.hasData) {
        return MoviesList(movies: snapshot.data!);
      } else {
        return const Center(
          child: CircularProgressIndicator(),
        );
      }
    },
    ),
    );
  }

}

class MoviesList extends StatelessWidget {
  const MoviesList({super.key, required this.movies});
  final List<Movie> movies;
  final String IMAGEPATH = 'http://image.tmdb.org/t/p/w500';
  @override
  Widget build(BuildContext context){
    return ListView.builder(
      itemCount: movies.length,
      itemBuilder: (context, index){
        return GestureDetector( 
          onTap: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) => MovieDetailRoute(pageTitle: movies[index].title, movieId: movies[index].id)
            )
            );
          },
          child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: Row(children: [
            ClipRRect(borderRadius: BorderRadius.circular(12.0),
            child: Image.network(IMAGEPATH + movies[index].imageUrl, width: 80, height: 120)),
            const SizedBox(width: 15),
           Expanded(child: 
            Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Text(movies[index].title,
                style: const TextStyle(fontWeight: FontWeight.bold),
                ),
                Text(movies[index].releaseDate),
                Text(movies[index].movieText, maxLines: 3, overflow: TextOverflow.ellipsis,
                ),
              ],
              ),
           )
          ],
          ),
         ) );
      },
    );
  }
}



class MovieTrailerScreen extends StatelessWidget{
  final String pageTitle;
  final String videoId;
  MovieTrailerScreen({super.key, required this.pageTitle, required this.videoId});

   @override
  Widget build(BuildContext context){
    final YoutubePlayerController _controller = YoutubePlayerController(initialVideoId: videoId, flags: YoutubePlayerFlags(autoPlay: true, mute:false
    ),
    );
    return YoutubePlayerBuilder(
      player: YoutubePlayer(
        controller: _controller,
        ),
      builder: (context, player) {
        return Column(
          children: [player],
        );
      },
    );
}
}

class MovieDetailsScreen extends StatelessWidget {
  final MovieDetails movie;
  const MovieDetailsScreen({super.key, required this.movie});
  final String IMAGEPATH = 'http://image.tmdb.org/t/p/w500';

  
  @override
  Widget build(BuildContext context){
    if(movie != null) {
      // print('Moviedetailscreen: movie is not null: ${movie.title} ${movie.imageUrl} ${movie.releaseDate} ${movie.runtime} ${movie.homepage} ${movie.genres.join(' ')} ${movie.videos.map((video) => (video.name))} ${movie.movieText}');
    }
    return Scaffold(
      appBar: AppBar(
        title: Text(movie.title)      
      ),
      body: 
      // Expanded(
      //   child: 
        SingleChildScrollView(child: 
      Column(
      children: [
          ClipRRect(
          child: Image.network(IMAGEPATH + movie.imageUrl, width: double.infinity, height: 150,
          fit: BoxFit.fill
          )
      ),
     Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(crossAxisAlignment: CrossAxisAlignment.start,
          children: [

        Text(movie.title,
                style: const TextStyle(fontWeight: FontWeight.bold)),
        Text(movie.releaseDate),
        Text(movie.movieText),
        Text(
          'Genres: ${movie.genres.join(' ')}'
        ),
        Text('Runtime: ${movie.runtime} min'),
        RichText(
          text: TextSpan(
            children: [
              TextSpan(
                text: 'Homepage: ',
                style: TextStyle(color: Colors.black.withOpacity(1.0))
              ),
              WidgetSpan(
                child: GestureDetector(
                  onTap: () async {
                    String url = movie.homepage;
                    if(await canLaunchUrl(Uri.parse(url))){
                      await launchUrl(Uri.parse(url), 
                      // mode: LaunchMode.externalApplication
                      );
                    }
                  } 
                  // _launchURL(movie.homepage)
                  ,
                  child: Text(
                    movie.homepage, 
                    style: TextStyle(
                      color: Colors.blue.withOpacity(1.0))))
                             ,
          )
          ],
              )
              ),
              Text("Videos:"),
       Column(crossAxisAlignment: CrossAxisAlignment.start,children: movie.videos.map((video){
        return GestureDetector(
          onTap: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) => MovieTrailerScreen(pageTitle: video.name, videoId: video.key)
            )
            );
          },
          child: Text(video.name, style: TextStyle(color: Colors.blue.withOpacity(1.0)))
        );
       }).toList(),
       ), 
      ],)
          ),
    ]
      )
      
    
   )
    // )
     ); 
     
  }

}

class MovieDetailRoute extends StatelessWidget{
  final String pageTitle;
  final int movieId;
  MovieDetailRoute({super.key, required this.pageTitle, required this.movieId});

  @override
  Widget build(BuildContext context){
    return Scaffold(
      
      body: FutureBuilder<MovieDetails>(
        future: fetchSingleMovie(http.Client(), movieId),
        builder: (context, snapshot) {
        if(snapshot.hasError){
        print('Details Error ${snapshot.error}');
        return const Center(
          child: Text('An error has occurred!'),
        );
      } else if (snapshot.hasData) {
        print("Did we get data?");
        return MovieDetailsScreen(movie: snapshot.data!);
      } else {
        return const Center(
          child: CircularProgressIndicator(),
        );
      }
        }
        )
    );
  }
}