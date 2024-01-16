import "./App.css";
import Banner from "./components/Banner";
import Home from "./components/Home";
import Login from "./components/Login";
import Signup from "./components/Signup";
import { BrowserRouter as Router} from "react-router-dom";
import {Routes, Route} from "react-router-dom";
import React from "react";

function App() {
  return (
    <div className="App">
      <Banner/>
      <Router basename="wuipharkka/pg/">
  <div>
    <section>                              
        <Routes>
          <Route path="/home" element={<Home/>}/>
           <Route path="/signup" element={<Signup/>}/>
           <Route path="/" element={<Login/>}/>
        </Routes>                    
    </section>
  </div>
</Router>
    </div>
  );
}

export default App;
