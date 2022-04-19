package gov.in.cgstate.chips.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private FirebaseDatabase mDatabase;
    private DatabaseReference mDbRef;
    //private DatabaseReference mMemberRef;
    private Books mBooks;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mBookRV;
    private BookAdapter mBookAdapter;
    private List<Book> mBookList = new ArrayList<>();
    private FloatingActionButton mAddBookFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mBookRV = findViewById(R.id.rv_books);
        mAddBookFAB = findViewById(R.id.fab_add_book);
        mLayoutManager = new LinearLayoutManager(this);
        mBookRV.setLayoutManager(mLayoutManager);
        mBookAdapter = new BookAdapter(this, mBookList);
        mBookRV.setAdapter(mBookAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mDbRef = mDatabase.getReference();

        /*
        Book b = new Book();
        b.setId("MKJ105");
        b.setTitle("Empty");
        b.setAuthor("xyz");
        b.setPrice(1200.00);
        b.setRating(3);
        b.setPriceUnit("Rs");

        mDatabase.getReference("book").setValue(b);
         */

        mAddBookFAB.setOnClickListener( v -> {
            Book b = new Book();
            b.setId("MKJ107");
            b.setTitle("Empty");
            b.setAuthor("xyz");
            b.setPrice(1200.00);
            b.setRating(3);
            b.setPriceUnit("Rs");
            mBookList.add(b);
            DatabaseReference mMemberRef = mDbRef.child("books");
            DatabaseReference tlastMem = mMemberRef.push();
            tlastMem.setValue(b);
            //String id = m.getKey();
            //Map<String, Object> map = new HashMap<>();
            //map.put("books", mBookList);
            //mDbRef.setValue(map);
            Toast.makeText(getApplicationContext(), "Book added successfully", Toast.LENGTH_SHORT).show();
        });

        mDbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mBooks = snapshot.getValue(Books.class);
                if (mBooks.getBooks() != null && mBooks.getBooks().size()>0) {
                    mBookList = new ArrayList<>(mBooks.getBooks().values());
                    mBookAdapter.updateResult(mBookList);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}