package com.android.atiqorin.habittracker;

import android.provider.BaseColumns;

/**
 * Created by atiqorin on 7/4/16.
 */
public final class DBContract {


    public static abstract class FeedEntry implements BaseColumns {
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_ACTIVITY = "contactsManager";
        public static final String KEY_ID = "id";
        public static final String KEY_ACTION = "Action";
        public static final String KEY_TIME = "time";
        public static  final String TABLE_NAME="userHabit";
    }
}
