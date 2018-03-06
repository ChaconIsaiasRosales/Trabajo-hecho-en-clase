package cr.ac.unadeca.todoisaias.database.modies;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import cr.ac.unadeca.todoisaias.database.TodoDatabase;

/**
 * Created by Isaias Chacon on 05/02/2018.
 */
@Table(database = TodoDatabase.class)

public class TodoTable extends BaseModel {

    @PrimaryKey(autoincrement = true)

    public long id;

    @Column
    public String nombre;

    @Column
    public String actividad;

    @Column
    public int estado;
}
