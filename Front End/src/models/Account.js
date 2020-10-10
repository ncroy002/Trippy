
//this is the account object from the backend
export class Account {
    email;
    password;
    firstName;
    lastName;

    constructor(email, password, firstName, lastName){
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
};