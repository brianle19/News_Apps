package thaile.com.aiw_client_finalproject;

import java.io.Serializable;

/**
 * Created by Thai Le on 12/5/2016.
 */

public class UserObj implements Serializable {
    private int id;
    private String fullname;
    private String userName;
    private String passWord;
    private String dob;
    private String userAddress;
    private String userEmail;
    private String userPhone;

    public UserObj(int id, String fullname, String userName, String passWord, String dob, String userAddress, String userEmail,
                   String userPhone) {
        this.fullname = fullname;
        this.userName = userName;
        this.passWord = passWord;
        this.dob = dob;
        this.userAddress = userAddress;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
        this.id = id;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFullname() {
        return fullname;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getDob() {
        return dob;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }
}
