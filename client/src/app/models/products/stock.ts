export class Stock {
    id:number;
	quantite:number;
	size:String;
	colour:String;
	product_id_product:number;
	product_shop_id_shop:number;
    product_Shop_Admin_idAdmin:number;
    
    constructor(
    id:number,
	quantite:number,
	size:String,
	colour:String,
	product_id_product:number,
	product_shop_id_shop:number,
	product_Shop_Admin_idAdmin:number,
    ){
        this.id = id;
	    this.quantite = quantite;
	    this.size = size;
	    this.colour = colour;
	    this.product_id_product = product_id_product;
	    this.product_shop_id_shop = product_shop_id_shop;
	    this.product_Shop_Admin_idAdmin = product_Shop_Admin_idAdmin;
    }
}
