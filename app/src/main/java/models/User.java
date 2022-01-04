package models;

public class User {
    private int _id;
    private String _name;
    private String _username;
    private String _email;
    private String _password;

    public User(){

    }
    public User(int _id, String _name, String _username, String _email, String _password){
        this._id = _id;
        this._name = _name;
        this._username = _username;
        this._email = _email;
        this._password = _password;
    }
    public User(String _name, String _username, String _email, String _password){
        this._name = _name;
        this._username = _username;
        this._email = _email;
        this._password = _password;
    }

    public User(String nama, String email) {
    }

    public User(String userName, String userEmail, String userPassword) {
    }

    public int get_id(){
        return _id;
    }
    public void set_id(int _id){
        this._id = _id;
    }

    public String get_name(){
        return _name;
    }
    public void set_name(String _name){
        this._name = _name;
    }

    public String get_username(){
        return _username;
    }
    public void set_username(String _username){
        this._username = _username;
    }

    public String get_email(){
        return _email;
    }
    public void set_email(String _email){
        this._email = _email;
    }

    public String get_password(){
        return _password;
    }
    public void set_password(String _password){
        this._password = _password;
    }
}