package cr.ac.unadeca.todoisaias.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Toast;

import com.raizlabs.android.dbflow.sql.language.SQLite;

import org.sufficientlysecure.htmltextview.HtmlResImageGetter;

import java.util.List;

import cr.ac.unadeca.todoisaias.R;
import cr.ac.unadeca.todoisaias.database.modies.TodoTable;
import cr.ac.unadeca.todoisaias.database.modies.TodoTable_Table;
import cr.ac.unadeca.todoisaias.subclases.TodoViewHolder;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
    private static Context QuickContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuickContext = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actividad = new Intent(getApplicationContext(), FormularioActivity.class);
                actividad.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(actividad);

              //TodoTable actividad = new TodoTable();
                //actividad.nombre = "Juan Pablo";
                //actividad.actividad = "Limpiar el carro";
                //actividad.save();

                // TodoTable actividad1 = SQLite.select().from(TodoTable.class).querySingle();
                // actividad1.nombre ="Peter ";
                // actividad.save();
                // if(actividad1 !=null) {

                //Snackbar.make(view, "Se obtuvo la actividad " + actividad1.actividad + " Para el usuario "+ actividad1.nombre + " con id" + actividad1.id, Snackbar.LENGTH_LONG)
                // .setAction("Action", null).show();
                // }

                // delete from TodoTable where id id = 1;
                // SQLite.delete(TodoTable.class).where(TodoTable_Table.id.is((long)1)).execute();
                // SQLite.delete(TodoTable.class).execute();
                // SQLite.update(TodoTable.class).set(TodoTable_Table.actividad.eq("Limpiar la casa")).where(TodoTable_Table.id.is((long)1));
            }
        });

        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));

        List<TodoTable> info = SQLite.select().from(TodoTable.class).queryList();
        lista.setAdapter(new TodoAdapter(info));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            List<TodoTable> info = SQLite.select().from(TodoTable.class).queryList();
            lista.setAdapter(new TodoAdapter(info));
        }

        return super.onOptionsItemSelected(item);


    }



    public static class TodoAdapter extends RecyclerView.Adapter<TodoViewHolder> {
        private final List<TodoTable> listTodoTable;
        private final LayoutInflater inflater;

        public TodoAdapter(List<TodoTable> listTodoTables) {
            this.inflater = LayoutInflater.from(QuickContext);
            this.listTodoTable = listTodoTables;
        }

        @Override
        public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.odjecto, parent, false);
            return new TodoViewHolder(view);
        }

        public void animateTo(List<TodoTable> models) {
            applyAndAnimateRemovals(models);
            applyAndAnimateAdditions(models);
            applyAndAnimateMovedItems(models);
        }

        private void applyAndAnimateRemovals(List<TodoTable> newModels) {
            for (int i = listTodoTable.size() - 1; i >= 0; i--) {
                final TodoTable model = listTodoTable.get(i);
                if (!newModels.contains(model)) {
                    removeItem(i);
                }
            }
        }

        private void applyAndAnimateAdditions(List<TodoTable> newModels) {
            for (int i = 0, count = newModels.size(); i < count; i++) {
                final TodoTable model = newModels.get(i);
                if (!listTodoTable.contains(model)) {
                    addItem(i, model);
                }
            }
        }

        private void applyAndAnimateMovedItems(List<TodoTable> newModels) {
            for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
                final TodoTable model = newModels.get(toPosition);
                final int fromPosition = listTodoTable.indexOf(model);
                if (fromPosition >= 0 && fromPosition != toPosition) {
                    moveItem(fromPosition, toPosition);
                }
            }
        }

        public TodoTable removeItem(int position) {
            final TodoTable model = listTodoTable.remove(position);
            notifyItemRemoved(position);
            return model;
        }

        public void addItem(int position, TodoTable model) {
            listTodoTable.add(position, model);
            notifyItemInserted(position);
        }

        public void moveItem(int fromPosition, int toPosition) {
            final TodoTable model = listTodoTable.remove(fromPosition);
            listTodoTable.add(toPosition, model);
            notifyItemMoved(fromPosition, toPosition);
        }

        @Override
        public void onBindViewHolder(final TodoViewHolder holder, final int position) {
            final TodoTable current = listTodoTable.get(position);
            holder.html.setHtml(ActividadAString(current),
                    new HtmlResImageGetter(holder.html));
            holder.html.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    if(current.estado <2) {
                        current.estado = 2;
                    }else{
                        current.estado = 1;
                    }
                }
            });

            holder.borrar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    current.delete();
                    removeItem(position);
                    notifyDataSetChanged();
                }
            });
        }

        private String ActividadAString(TodoTable todo){
            String color ="#000000";
            if (todo.estado == 2){
                color = "#d620d6";
            }

            String html = "<a><big><b> <font color=\""+ color +"\"> " + todo.nombre +"<b></big>";
            html+= "<br>" + todo.actividad +"</a>";
            return html;
        }


        @Override
        public int getItemCount() {
            return listTodoTable.size();
        }
    }

}
