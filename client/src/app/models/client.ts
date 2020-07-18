export class Client {
  id: number;
  first_name: string;
  last_name: string;
  email: string;
  phone: string;
  zip_code: number;
  house_no: number;
  street: string;
  country: string;
  new_password: string;
  password_retyped: string;
  constructor(
    id: number,
    first_name: string,
    last_name: string,
    email: string,
    phone: string,
    zip_code: number,
    house_no: number,
    street: string,
    country: string,
    new_password: string,
    password_retyped: string
  ) {
    this.id = id;
    this.first_name = first_name;
    this.last_name = last_name;
    this.email = email;
    this.phone = phone;
    this.zip_code = zip_code;
    this.house_no = house_no;
    this.street = street;
    this.country = country;
    this.new_password = new_password;
    this.password_retyped = password_retyped;
  }
}
