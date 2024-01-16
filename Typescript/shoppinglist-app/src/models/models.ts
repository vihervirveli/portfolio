//The type I feed into the database
export interface NewItem {

  user: string | null |undefined;
  email: string | null | undefined;
  title: string;
  brand: string;
  checked: boolean;
  store: string;
  price: number;
}

//The type that comes from the database
export interface Item {

    user: string;
    email: string;
    title: string;
    brand: string;
    checked: boolean;
    store: string;
    price: number;
    id: string;
  }