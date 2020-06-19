export class Stock {
    id:number;
	quantite:number;
	size:String;
	colour:String;
    
    constructor(
    id:number,
	quantite:number,
	size:String,
	colour:String,
    ){
        this.id = id;
	    this.quantite = quantite;
	    this.size = size;
	    this.colour = colour;
    }
}
