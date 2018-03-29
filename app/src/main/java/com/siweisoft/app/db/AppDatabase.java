package com.siweisoft.app.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public class AppDatabase {

  public static final int VERSION = 1;

  public static final String NAME = "AppDataBase";
}