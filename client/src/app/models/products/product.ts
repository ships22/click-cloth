import { Categories } from './categories';
import { Stock } from './stock';

export class Product {
    id: number;
	name: string;
	description: string;
	price: number;
	image: any;
    discount: string;
    categories: Categories[];
    stocks: Stock[];
	shop_id_shop: number;
    shop_admin_id_admin: number;
    constructor(
        id: number,
        name: string,
        description: string,
        price: number,
        image: any,
        discount: string,
        shop_id_shop: number,
        shop_admin_id_admin: number,
    ) {
    this.id = id;
	this.name = name;
	this.description = description;
	this.price = price;
	this.image = image;
	this.discount = discount;
	this.shop_id_shop = shop_id_shop;
    this.shop_admin_id_admin = shop_admin_id_admin;
    }
}
