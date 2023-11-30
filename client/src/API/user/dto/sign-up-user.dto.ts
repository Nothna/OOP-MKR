export class SignUpUserDto{
    password: string;
    email: string;
    nickname: string;
    constructor(password: string, email: string, nickname: string) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }
}