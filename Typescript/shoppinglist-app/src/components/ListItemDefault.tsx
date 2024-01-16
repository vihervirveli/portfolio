import React, { useState } from "react";
import { Item } from "../models/models";
import {
  setDoc,
  doc,
} from "firebase/firestore/lite";
import { db } from "../firebase";

interface props {
  item: Item;
  toggleCheckItem: (editItem: Item) => void;
  removeItem: (itemId: string) => void;
  setItems: React.Dispatch<React.SetStateAction<Item[]>>;
  items: Item[];
}

interface chosen {
  editItemStore: string;
  setEditStore: React.Dispatch<React.SetStateAction<string>>;
}

const EditDropDown: React.FC<chosen> = ({ editItemStore, setEditStore }) => {
  const stores = ["S-Market", "K-Market", "Lidl"];
  const chosenStoreIndex = stores.indexOf(editItemStore);
  stores.push(...stores.splice(0, chosenStoreIndex));

  const handleEditStoreChange = (event: React.FormEvent<HTMLSelectElement>) => {
    const elem = event.target as HTMLSelectElement;
    setEditStore(elem.value);
  };

  const getOptions = () => {
    return stores.map((opt) => {
      return (
        <option key={opt} value={opt}>
          {opt}
        </option>
      );
    });
  };

  return (
    <div className="selector">
      <select value={editItemStore} onChange={handleEditStoreChange}>
        {getOptions()}
      </select>
    </div>
  );
};

const ListItemDefault: React.FC<props> = ({
  item,
  toggleCheckItem,
  removeItem,
  setItems,
  items,
}) => {
  //beginning of ListItemDefault
  const [editMode, setEditMode] = useState(false);
  //for editing use
  // const [editedItem, setEditItem] = useState<Item>();
  const [editItemTitle, setEditItemTitle] = useState<string>("");
  const [editItemStore, setEditStore] = useState<string>("");
  const [editItemPrice, setEditPrice] = useState<string>("");
  const [editItemBrand, setEditBrand] = useState<string>("");

  const handleEditPrice = () => {
    let newPrice = 0.0;
    try {
      const price: number = parseFloat(editItemPrice);
      newPrice = price;
    } catch (error) {
      console.error(error);
    }

    return newPrice;
  };

  const handleEditSubmit = async (event: React.SyntheticEvent) => {
    event.preventDefault();
    let itemBefore: Item | undefined = item;
    if (!itemBefore) {
      alert("You didn't choose anything to edit. Try again.");
      return;
    }

    let newPrice = handleEditPrice();
    if (editItemTitle.length > 0) {
      itemBefore.title = editItemTitle;
    }
    if (editItemBrand.length > 0) {
      itemBefore.brand = editItemBrand;
    }
    if (editItemStore.length > 0) {
      itemBefore.store = editItemStore;
    }
    if (newPrice > 0) {
      itemBefore.price = newPrice;
    }

    await setDoc(doc(db, "items", itemBefore.id), {
      title: itemBefore.title,
      store: itemBefore.store,
      brand: itemBefore.brand,
      price: itemBefore.price,
      checked: itemBefore.checked,
      user: itemBefore.user,
      email: itemBefore.email,
    });

    //actual doing part

    setItems(
      items.map((item) => {
        return item.id === itemBefore?.id ? itemBefore : item;
      })
    );
    //exit editMode
    clearEditFields();
    toggleEditMode(false);
  }; // handleEditSubmit

  const clearEditFields = () => {
    setEditItemTitle("");
    setEditBrand("");
    setEditStore("");
    setEditPrice("");
  };

  const toggleEditMode = (onOrOff: boolean) => {
    setEditMode((current) => !current);
    //to make sure the add fields don't go into the edit fields
    clearEditFields();

    setEditItemTitle(item.title);
    setEditBrand(item.brand);
    setEditStore(item.store);
    setEditPrice(item.price.toString());
  };

  let divClass;
  if (item.checked === true) {
    divClass = "defaultItemChecked";
  } else {
    divClass = "defaultItemUnchecked";
  }

  return (
    <li>
      {
        //if editMode is true, the app is in editing mode and it displays the editing form
        editMode ? (
          <div className="editForm">
            <h3 className="normal">Edit your item</h3>
            <form onSubmit={handleEditSubmit}>
              <label htmlFor="title">Item title</label>
              <input
                type="text"
                name="title"
                value={editItemTitle}
                onChange={(event) => setEditItemTitle(event.target.value)}
                placeholder={item.title}
              />
              <label htmlFor="brand">Item brand</label>
              <input
                type="text"
                name="brand"
                value={editItemBrand}
                onChange={(event) => setEditBrand(event.target.value)}
                placeholder={item.brand}
              />
              <label htmlFor="store">Item store</label>
              <EditDropDown
                editItemStore={editItemStore}
                setEditStore={setEditStore}
              />
              <label htmlFor="price">Item price</label>
              <input
                type="text"
                name="price"
                value={editItemPrice}
                onChange={(event) => setEditPrice(event.target.value)}
                placeholder={item.price.toString()}
              />
              <input
                className="editbtn"
                type="submit"
                value="Edit"
                onClick={handleEditSubmit}
              />
              <input
                className="cancelbtn"
                type="button"
                value="Cancel"
                onClick={() => toggleEditMode(false)}
              />
            </form>
          </div>
        ) : (
          //if the editMode is not on, the app displays the item and it's information normally
          <div className={divClass}>
            <div className="gridContainer">
              <div className="gridLeft">
                <div className="info">
                  {item.title} - {item.brand}{" "}
                  <span className="priceinfo">{item.price} €</span>
                </div>
              </div>
              <div className="gridRight">
                <div>
                  {" "}
                  {
                    <span
                      className={item.checked ? "checked" : "unchecked"}
                      onClick={() => toggleCheckItem(item)}
                    >
                      {" "}
                      {item.checked ? "Uncheck" : "Check"}{" "}
                    </span>
                  }
                  <span className="edit" onClick={() => toggleEditMode(true)}>
                    {" "}
                    Edit{" "}
                  </span>
                  <span className="remove" onClick={() => removeItem(item.id)}>
                    {" "}
                    Delete x{" "}
                  </span>
                </div>
              </div>
            </div>
          </div>
        )
      }
    </li>
  );
};

export default ListItemDefault;
