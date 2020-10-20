package itp341.lee.firebasedemoitp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import itp341.lee.firebasedemoitp.model.Note;

public class MainActivity extends AppCompatActivity {

    final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private ArrayAdapter<Note> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText editText = findViewById(R.id.editTextNote);
        final ListView listView = findViewById(R.id.listViewNotes);
        final Button button = findViewById(R.id.buttonSubmit);

       arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, new ArrayList<Note>());

        listView.setAdapter(arrayAdapter);

        db.collection("notes")
                .whereIn("completed", Arrays.asList(true, false))
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        List<Note> notes = new ArrayList<>();

                        for (DocumentSnapshot documentSnapshot: value.getDocuments()){
                            Note note = documentSnapshot.toObject(Note.class);
                            notes.add(note);
                        }

                        arrayAdapter.clear();
                        arrayAdapter.addAll(notes);
                        arrayAdapter.notifyDataSetChanged();
                    }
                });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String body = editText.getText().toString();
                final Note newNote = new Note();
                newNote.setBody(body);
                newNote.setCompleted(false);

                editText.setText("");

                db.collection("notes").add(newNote);
            }
        });
    }
}