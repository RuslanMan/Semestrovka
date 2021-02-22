package models;

import java.sql.Date;

public class Operator extends User{
    boolean isOperator = true;

    public Operator(String login) {
        super(login);
    }

    public Operator(String login, String password, String email) {
        super(login, password, email);
    }

    public boolean isOperator() {
        return isOperator;
    }
}
