package cr.ac.unadeca.todoisaias.database;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.SQLiteType;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;

import cr.ac.unadeca.todoisaias.database.modies.TodoTable;

/**
 * Created by WiltonTuckerHart on 26/2/2018.
 */
@Migration(version = 3, database = TodoDatabase.class)
public class Migracion1 extends AlterTableMigration<TodoTable>{

    public Migracion1(Class<TodoTable> table) {
        super(table);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();
        addColumn(SQLiteType.INTEGER, "estado");
    }
}
