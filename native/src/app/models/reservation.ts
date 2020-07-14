import { Client } from './client';
import { Shop } from './shop';
import { Product } from './products/product';
import { Stock } from './products/stock';

export class Reservation {
    reservation_id: number;
	date_time: Date;
	reference: string;
	total: number;
	status: string;
    quantity: number;
    client: Client;
    shop: Shop;
    product: Product;

    constructor(
        reservation_id: number,
        date_time: Date,
        reference: string,
        total: number,
        status: string,
        quantity: number,
        client: Client,
        shop: Shop,
        product: Product
    ) {
        this.reservation_id = reservation_id;
        this.date_time = date_time;
        this.reference = reference;
        this.total = total;
        this.status = status;
        this.quantity = quantity;
        this.client = client;
        this.shop = shop;
        this.product = product;
    }
}
