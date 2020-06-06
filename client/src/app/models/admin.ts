import { Shop } from './shop';

export class Admin {
    id: number;
    first_name: string;
    last_name: string;
    email: string;
    address: string;
    shops: Shop[];

    constructor(
        id : number,
        first_name : string,
        last_name : string,
        email : string,
        address : string,
        shops: Shop[]
    ) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address = address;
        this.shops = shops; 
    }
}
