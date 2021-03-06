import { Product } from './products/product';

export class Shop {
    
	shop_id: number;
	name: string;
	address: string;
	email: string;
	phone: string;
	productList: Product[];
	reservations: any;
	is_active: string;
    admin_id: number;
    
    constructor(
    shop_id: number,
	name: string,
	address: string,
	email: string,
	phone: string,
	productList: Product[],
	reservations: any,
	is_active: string,
    admin_id: number,
    ) {
    this.shop_id = shop_id;
	this.name = name;
	this.address = address;
	this.email = email;
	this.phone = phone;
	this.productList = productList;
	this.reservations = reservations;
	this.is_active = is_active;
    this.admin_id = admin_id;
    }
}
