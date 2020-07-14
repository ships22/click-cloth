import { Categories } from './categories';
import { Stock } from './stock';

export class Product {
    id: number;
	name: string;
	description: string;
    productRef: string;
	price: number;
	image: any;
    discount: string;
    categories: Categories[];
    stock: Stock[];
	shop_id_shop: number;
    shop_admin_id_admin: number;
    constructor(
        id: number,
        name: string,
        description: string,
        productRef: string,
        price: number,
        image: any,
        discount: string,
        categories: Categories[],
        stock: Stock[],
    ) {
    this.id = id;
	this.name = name;
    this.description = description;
    this.productRef = productRef;
	this.price = price;
    this.image = image;
    this.categories = categories;
    this.stock = stock;
	this.discount = discount;
	
    }
}
