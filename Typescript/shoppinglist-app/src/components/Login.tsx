import React, {useState} from "react";
import {  signInWithEmailAndPassword   } from "firebase/auth";
import { auth } from "../firebase";
import { NavLink, useNavigate } from "react-router-dom";

const Login:React.FC = () => {
  const navigate = useNavigate();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");

    const onLogin = (event: { preventDefault: () => void; }) => {
      event.preventDefault();
      signInWithEmailAndPassword(auth, email, password)
      .then((userCredential) => {
          // Signed in
          const user = userCredential.user;
          
          navigate("/home")
          
      })
      .catch((error) => {
          const errorCode = error.code;
          const errorMessage = error.message;
          console.log(errorCode, errorMessage)
      });
     
  }


    return (     <>
      <main >        
          <section>
              <div>                                            
                                 
                  <h2>Login page</h2>                   
                  <form>                                              
                      <div>
                          <label htmlFor="email-address" className="lefttext">
                              Email address
                          </label>
                          <input 
                              id="email-address"
                              name="email"
                              type="email"                                    
                                    
                                                                                    
                              placeholder="Email address"
                              onChange={(e)=>setEmail(e.target.value)}
                              required/>
                      </div>

                      <div>
                          <label htmlFor="password" className="lefttext">
                              Password
                          </label>
                          <input
                              id="password"
                              name="password"
                              type="password"                                    
                                                                                                
                              placeholder="Password"
                              
                              onChange={(e)=>setPassword(e.target.value)}
                              required/>
                      </div>
                                          
                      <div>
                          <button className="btn"                                    
                              onClick={onLogin}                                        
                          >      
                              Login                                                                  
                          </button>
                      </div>                               
                  </form>
                 
                  <p className="text-sm text-white text-center">
                      No account yet? {' '}
                      <NavLink to="/signup">
                          Sign up
                      </NavLink>
                  </p>
                                             
              </div>
          </section>
      </main>
  </>)
  }

export default Login;
