import React, { useState, useEffect } from "react";
import { signOut } from "firebase/auth";
import ShoppingList from "./ShoppingList";
import { onAuthStateChanged } from "firebase/auth";
import { auth } from "../firebase";
import { useNavigate } from "react-router-dom";

const Home: React.FC = () => {
  const navigate = useNavigate();
  const [user, setUser] = useState<string | null | undefined>();
  const [email, setEmail] = useState<string | null | undefined>();

  const handleLogout = () => {
    signOut(auth)
      .then(() => {
        // Sign-out successful.
        setUser("");
        setEmail("");
        navigate("/");
        console.log("Signed out successfully");
      })
      .catch((error) => {
        // An error happened.
      });
  };
  useEffect(() => {
    onAuthStateChanged(auth, (currentUser) => {
      if (currentUser) {
        // User is signed in, see docs for a list of available properties
        // https://firebase.google.com/docs/reference/js/firebase.User
        const uid = currentUser.uid;
        const emailaddress = currentUser.email;
        // ...

        setUser(uid);
        setEmail(emailaddress);
      } else {
        // User is signed out
        // ...
       
      }
    });
  }, []);


  return (
    <div>
      <ShoppingList user={user} email={email} />
      <button className="btn" onClick={handleLogout}>
        Logout
      </button>
    </div>
  );
};

export default Home;
