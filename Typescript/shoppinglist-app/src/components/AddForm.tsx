import React, { useState } from "react";
import { Item, NewItem } from "../models/models";
import {
  collection,
  getDoc,
  addDoc,
  doc,
} from "firebase/firestore/lite";
import { db } from "../firebase";
interface props {
  // handleSubmit: (event: React.SyntheticEvent) => void ;
  handleAddMode: (onOrOff: boolean) => void;
  setItems: React.Dispatch<React.SetStateAction<Item[]>>;
  items: Item[];
  email: string | null | undefined;
  user: string | null | undefined;
}
const AddForm: React.FC<props> = ({
  handleAddMode,
  setItems,
  items,
  email,
  user,
}) => {
  ///For new items

  const [itemTitle, setItemTitle] = useState<string>("");
  const [itemStore, setStore] = useState<string>("");
  const [itemPrice, setPrice] = useState<string>("");
  const [itemBrand, setBrand] = useState<string>("");

  const clearFields = () => {
    setItemTitle("");
    setStore("");
    setPrice("");
    setBrand("");
  };

  const handlePrice = (priceOfItem: string) => {
    let newPrice = 0.0;
    try {
      const price: number = parseFloat(priceOfItem);
      newPrice = price;
    } catch (error) {
      console.error(error);
    }

    return newPrice;
  };

  const handleStoreChange = (event: React.FormEvent<HTMLSelectElement>) => {
    const elem = event.target as HTMLSelectElement;
    setStore(elem.value);
  };

  const handleSubmit = async (event: React.SyntheticEvent) => {
    // prevent normal submit event
    event.preventDefault();

    const price = handlePrice(itemPrice);

    // add item to items, Math.random() is used to generate "unique" ID...
    let newItem: NewItem = {
      title: itemTitle,
      brand: itemBrand,
      price: price,
      store: itemStore,
      email: email,
      user: user,
      checked: false, //for a new item, the checked value is always false
    };


    const docRef = await addDoc(collection(db, "items"), newItem);
    // get added doc id and set id to newItem
    const newId = docRef.id;
    const docRefGet = doc(db, "items", newId);
    const docSnap = await getDoc(docRefGet);
    const newData = docSnap.data();

    const dbItem: Item = {
      id: newId,
      title: newData?.title,
      brand: newData?.brand,
      price: newData?.price,
      store: newData?.store,
      email: newData?.email,
      user: newData?.user,
      checked: newData?.checked,
    };
    

    //newItem.id = docRef.id;
    // update states in App
    setItems([...items, dbItem]);
    // modify newItem text to ""

    clearFields();
  };

  return (
    <div className="formContainer">
      <form onSubmit={handleSubmit}>
        <label htmlFor="title">Item title</label>
        <input
          type="text"
          name="title"
          value={itemTitle}
          onChange={(event) => setItemTitle(event.target.value)}
          placeholder="Write a new item title here"
        />
        <label htmlFor="brand">Item brand</label>
        <input
          type="text"
          name="brand"
          value={itemBrand}
          onChange={(event) => setBrand(event.target.value)}
          placeholder="Write the brand of your new item here"
        />
        <label htmlFor="store">Item store</label>
        <div className="selector">
          <select value={itemStore} onChange={handleStoreChange}>
            <option disabled value="">
              {" "}
              -- select an option --{" "}
            </option>
            <option value="S-Market">S-Market</option>
            <option value="K-Market">K-Market</option>
            <option value="Lidl">Lidl</option>
          </select>
        </div>
        <label htmlFor="price">Item price</label>
        <input
          type="text"
          name="price"
          value={itemPrice}
          onChange={(event) => setPrice(event.target.value)}
          placeholder="Write the price of your new item here"
        />
        <input className="add" type="submit" value="Add" />
        <button className="cancelbtn" onClick={() => handleAddMode(false)}>
          Cancel
        </button>
      </form>
    </div>
  );
};

export default AddForm;
