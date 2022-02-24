package com.clientlabs.smis;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Project: SMIS
 * Created by: Moses Mo
 * on 08,December,2021
 */
public class Constants {
    String uid, fname, lname, role, image, email, tel;
    Context ctx;
    SharedPreferences userinfo;
    SharedPreferences.Editor editor;


    public Constants(Context ctx, SharedPreferences userinfo) {
        this.ctx = ctx;
        this.userinfo = userinfo;
        this.editor = userinfo.edit();
        this.uid = "";
        this.fname = "";
        this.lname = "";
        this.role = "";
        this.image = "";
        this.email = "";
        this.tel = "";
    }

    public Constants(Context ctx, String tel, String uid, String fname, String lname, String role, String image, String email) {

        this.uid = uid;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.image = image;
        this.email = email;
        this.ctx = ctx;
        this.tel = tel;
        editor.putString("UID", uid);
        editor.putString("fname", fname);
        editor.putString("lname", lname);
        editor.putString("role", role);
        editor.putString("email", email);
        editor.putString("image", image);
        editor.putString("tel", tel);
        editor.apply();

    }

    Boolean getUser() {
        if (uid.length() < 1) {
            return false;
        } else {
            return true;
        }
    }


}
