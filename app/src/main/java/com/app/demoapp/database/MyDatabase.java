package com.app.demoapp.database;


import com.app.demoapp.dao.UserDao;
import com.app.demoapp.model.User;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 2)
public abstract class MyDatabase extends RoomDatabase {


    public abstract UserDao userDao();


}
