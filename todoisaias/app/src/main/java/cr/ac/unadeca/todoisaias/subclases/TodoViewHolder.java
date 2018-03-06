package cr.ac.unadeca.todoisaias.subclases;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import cr.ac.unadeca.todoisaias.R;

/**
 * Created by WiltonTuckerHart on 19/2/2018.
 */

public class TodoViewHolder extends RecyclerView.ViewHolder {

    public HtmlTextView html;
    public ImageView borrar;
    public TodoViewHolder(View itemView){
        super(itemView);
        html = itemView.findViewById(R.id.html_text);
        borrar = itemView.findViewById(R.id.delete);

    }

}
