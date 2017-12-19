package e.user_pc.navigationdraw;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class List extends Fragment {

    ArrayList<String> notes = new ArrayList();
    ArrayAdapter<String> adapter;

    ArrayList<String> selectedNotes = new ArrayList();
    ListView notesList;
    SharedPreferences sPref;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        loadNote();


        // обработка установки и снятия отметки в списке
        notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                // получаем нажатый элемент
                String note = adapter.getItem(position);
                if (notesList.isItemChecked(position) == true) {
                    selectedNotes.add(note);
                } else {
                    selectedNotes.remove(note);
                }
            }
        });
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list,container,false);
    }


    public void add (View view) {
        EditText noteEditText = (EditText) findViewById(R.id.notes);
        String note = noteEditText.getText().toString();
        if (!note.isEmpty() && notes.contains(note) == false) {
            adapter.add(note);
            noteEditText.setText("");
            adapter.notifyDataSetChanged(); // обновление
          /*  Toast.makeText(this, "Note Added", Toast.LENGTH_SHORT).show();*/
            saveNote();
        }
    }


    public void remove(View view) {
        // очищаем строку ввода
        EditText noteEditText = (EditText) findViewById(R.id.notes);
        noteEditText.setText("");
        // получаем и удаляем выделенные элементы
        for (int i = 0; i < selectedNotes.size(); i++) {
            adapter.remove(selectedNotes.get(i));
        }
        // снимаем все ранее установленные отметки
        notesList.clearChoices();
        // очищаем массив выбраных объектов
        selectedNotes.clear();
        adapter.notifyDataSetChanged();//обновление
        /*Toast.makeText(this, "Note Removed", Toast.LENGTH_SHORT).show();*/
        saveNote();
    }

    public void saveNote() {
        SharedPreferences.Editor editor = sPref.edit();
        editor.clear();
        for (int i = 0; i < adapter.getCount(); ++i){
            editor.putString(String.valueOf(i), adapter.getItem(i));
        }
        editor.apply();
    }

    public void loadNote() {
        for (int i = 0; ; ++i){
            final String str = sPref.getString(String.valueOf(i), "");
            if (!str.equals("")){
                adapter.add(str);
            } else {
                break;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        saveNote();
    }
}
