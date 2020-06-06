export class Shop {
    
	shop_id: number;
	name: string;
	address: string;
	email: string;
	phone: string;
	is_active: string;
    admin_id: number;
    
    constructor(
    shop_id: number,
	name: string,
	address: string,
	email: string,
	phone: string,
	is_active: string,
    admin_id: number,
    ) {
    this.shop_id = shop_id;
	this.name = name;
	this.address = address;
	this.email = email;
	this.phone = phone;
	this.is_active = is_active;
    this.admin_id = admin_id;
    }
}
