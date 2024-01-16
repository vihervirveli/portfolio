import { useState, useEffect } from "react";
import { 
    collection, 
    getDocs,
    deleteDoc, 
    updateDoc,
    doc
  } from "firebase/firestore/lite";
import kmarket from "./../screenshots/kmarket.png";
import smarket from "./../screenshots/smarket.png";
import lidl from "./../screenshots/lidl.png";
import {db} from "../firebase";
import AddForm from "./AddForm";
import { Item } from "../models/models";
import ListItemDefault from "./ListItemDefault";

interface props {
user: string | null | undefined;
email: string | null |undefined; 
}

const ShoppingList: React.FC<props> = ({user, email}) => {
  //For regular use
  const [items, setItems] = useState<Item[]>([]); 
  const [loading, setLoading] = useState(true);
  //to set addMode and show the form for adding an item
  const [addMode, setAddMode] = useState<boolean>(false);
  
 
    useEffect(() => {
      const fetchData = async () => {
        // connect todos collection
        const itemsCol = collection(db, "items");
        
        const itemSnapshot = await getDocs(itemsCol);
        // todo text and id 
        // document id is unique, so it can be used with deleting todo
        let dbItems = itemSnapshot.docs.map(doc => {
          return  { 
            title: doc.data().title,
            id: doc.id,
            store: doc.data().store,
            price: doc.data().price,
            user: doc.data().user,
            email: doc.data().email,
            brand: doc.data().brand,
            checked: doc.data().checked
          };
        });

       
        // set states

        if(user){

        dbItems = dbItems.filter(item => {
            return item.user === user
        });
       
        setItems(dbItems);
        setLoading(false);
        
      }
    }
      // start loading data
      
      fetchData();
  
      
    },[user]); // called only once


    const handleAddMode = (onOrOff:boolean) => {
      setAddMode(onOrOff);
    }

 

    const removeItem = (itemId: string  ) => {
      // filter/remove item with id
      deleteDoc(doc(db, "items", itemId));
      // delete from items state and update state
      let filteredArray = items.filter(collectionItem => collectionItem.id !== itemId);
      setItems(filteredArray); 
      // handleTotalPrice();
    }


    
    const toggleCheckItem = async (editItem:Item) =>{
     let checkedNew:boolean = !editItem.checked; 
      //UPDATE DOCUMENT!
      const itemRef = doc(db, "items", editItem.id);
      await updateDoc(itemRef, {
        checked: checkedNew
      });
      editItem.checked = checkedNew;
    
      setItems (
        items.map((item) => {
            return item.id === editItem.id? editItem: item;
        })
    ); 
     
      
    }
  
    const totalPrice = items.reduce((total, elem) => total = total + elem.price, 0);
    const roundedTotal = Math.round(totalPrice*100)/100;

    const kstore = items.filter((elem) => elem.store === "K-Market");
    const sstore = items.filter((elem) => elem.store === "S-Market");
    const lstore = items.filter((elem) => elem.store === "Lidl");
    return (
      <div>
        <h3>Add new Item</h3>
      {addMode === true ?
      <AddForm 
      setItems={setItems}
      items={items}
      handleAddMode={handleAddMode}
      email={email}
      user={user}
      /> 
    :
    <button className="addModeOn" onClick={ () => handleAddMode(true)}>Add a new item</button>}
       
        <h3>The items in your shoppinglist</h3>
        <ul className="shoppinglist">
         { loading  && 
          <p>Loading...</p>
        }
        
       {/* {items.map(item => (
        
         <ListItemDefault  
         key={item.id} 
         item={item} 
         toggleCheckItem={toggleCheckItem} 
         removeItem={removeItem}
         setItems={setItems}
         items={items}
         />

        ))} */}
        {
          <div>
            <div className="storecontainer">
            <h4>K-Market</h4>
           <img className="store" src={kmarket} alt="k-market"/>
           </div>
            {
              kstore.map(item =>(
                <ListItemDefault  
                key={item.id} 
                item={item} 
                toggleCheckItem={toggleCheckItem} 
                removeItem={removeItem}
                setItems={setItems}
                items={items}
                />
                 
               ))}
          </div>
        }
      {
        <div>
           <div className="storecontainer">
        <h4>S-Market</h4>
        <img className="store" src={smarket} alt="s-market"/>
        </div>
        {
          sstore.map(item =>(
            <ListItemDefault  
            key={item.id} 
            item={item} 
            toggleCheckItem={toggleCheckItem} 
            removeItem={removeItem}
            setItems={setItems}
            items={items}
            />
             
           ))}
           </div>
      }
      {
         <div>
          <div className="lidlstorecontainer">
         <h4>Lidl</h4>
         <img className="lidl" src={lidl} alt="lidl"/>
         </div>
         {
           lstore.map(item =>(
             <ListItemDefault  
             key={item.id} 
             item={item} 
             toggleCheckItem={toggleCheckItem} 
             removeItem={removeItem}
             setItems={setItems}
             items={items}
             />
              
            ))}
            </div>
      }
  
      </ul> 
      <h3>The total sum of your items</h3>
      <div className="totalcontainer">
        <span className="total">{roundedTotal} €</span>
        </div>   
      </div>
    )  
  }

export default ShoppingList;
