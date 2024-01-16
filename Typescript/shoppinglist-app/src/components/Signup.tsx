import React, {useState} from "react";
import { NavLink, useNavigate } from "react-router-dom";
import {  createUserWithEmailAndPassword  } from "firebase/auth";
import { auth } from "../firebase";


const Signup:React.FC = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState<string>("")
  const [password, setPassword] = useState<string>("");

  const onSubmit = async (e: { preventDefault: () => void; }) => {
    e.preventDefault()
   
    await createUserWithEmailAndPassword(auth, email, password)
      .then((userCredential) => {
          // Signed in
          const user = userCredential.user;
          console.log(user);
          navigate("/")
          // ...
      })
      .catch((error) => {
          const errorCode = error.code;
          const errorMessage = error.message;
          console.log(errorCode, errorMessage);
          // ..
      });

 
  }

    return ( <main >        
      <section>
          <div>
              <div>                  
                <h2>Signup page
                    </h2>                                                                           
                  <form>                                                                                            
                      <div>
                          <label htmlFor="email-address" className="lefttext">
                              Email address
                          </label>
                          <input
                              type="email"
                              value={email}
                              onChange={(e) => setEmail(e.target.value)}  
                              placeholder="Email address"                                
                              required/>
                      </div>

                      <div>
                          <label htmlFor="password" className="lefttext">
                              Password
                          </label>
                          <input
                              type="password"
                              value={password}
                              onChange={(e) => setPassword(e.target.value)} 
                                                               
                              placeholder="Password"              
                              required/>
                      </div>                                             
                      
                      <button className="btn"
                          type="submit" 
                          onClick={onSubmit}                        
                      >  
                          Sign up                                
                      </button>
                                                                   
                  </form>
                 
                  <p>
                      Already have an account?{' '}
                      <NavLink to="/" >
                          Sign in
                      </NavLink>
                  </p>                   
              </div>
          </div>
      </section>
  </main>)
  }

export default Signup;
