package cr.ac.unadeca.todoisaias.database;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Isaias Chacon on 05/02/2018.
 */

@Database(name = TodoDatabase.NAME, version = TodoDatabase.VERSION)

public class TodoDatabase {

    public static final String NAME = "todoisaias";
    public static final int VERSION = 2;
}
