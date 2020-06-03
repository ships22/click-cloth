export class Admin {
    id: number;
    first_name: string;
    last_name: string;
    email: string;
    address: string;

    constructor(
        id : number,
        first_name : string,
        last_name : string,
        email : string,
        address : string
    ) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.address = address;
    }
}
