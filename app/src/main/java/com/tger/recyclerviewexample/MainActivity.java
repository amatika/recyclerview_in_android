package com.tger.recyclerviewexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.tger.recyclerviewexample.Adapter;
import com.tger.recyclerviewexample.ModelClass;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{

    RecyclerView mrecyclerView;
    LinearLayoutManager layoutManager;
    List<ModelClass>userList;
    Adapter adapter;
    ConstraintLayout cl;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initRecyclerView();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mrecyclerView);
    }

    private void initRecyclerView()
    {
        cl=findViewById(R.id.mainl);
        mrecyclerView=findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mrecyclerView.setLayoutManager(layoutManager);
        adapter=new Adapter(userList);
        mrecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void initData()
    {
        userList = new ArrayList<>();
        userList.add(new ModelClass(R.drawable.office1,"Anjali","How are you?","10:45 am","_______________________________________"));
        userList.add(new ModelClass(R.drawable.office2,"Brijesh","I am fine","15:08 pm","_______________________________________"));
        userList.add(new ModelClass(R.drawable.office3,"Sam","You Know?","1:02 am","_______________________________________"));
        userList.add(new ModelClass(R.drawable.office4,"Divya","How are you?","12:55 pm","_______________________________________"));
        userList.add(new ModelClass(R.drawable.office1,"Simran","This is Easy","13:50 am","_______________________________________"));
        userList.add(new ModelClass(R.drawable.office2,"Karan","I am Don","1:08 am","_______________________________________"));
        userList.add(new ModelClass(R.drawable.office3,"Sameer","You Know this?","4:02 am","_______________________________________"));

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT)
    {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            int position = viewHolder.getAdapterPosition();
            if (direction == ItemTouchHelper.LEFT)
            {
                userList.remove(position);
                adapter.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(cl, "Item deleted", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
            else
            {
               // final String item = userList.get(position);
                // Show dialog to update the item
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.edit_item_design, null);
                dialogBuilder.setView(dialogView);

                final EditText ed1 = dialogView.findViewById(R.id.message);
                ImageView img=dialogView.findViewById(R.id.pic);
                //img.setImage(userList.get(position).getImageview());
                ed1.setText(userList.get(position).getTextview2());

                dialogBuilder.setTitle("Edit Message");
                dialogBuilder.setPositiveButton("Save", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        EditText editTextItem = dialogView.findViewById(R.id.message);
                        String updatedItem = editTextItem.getText().toString().trim();
                        if (!updatedItem.isEmpty())
                        {
                            userList.get(position).setTextview2(updatedItem);
                            /*ModelClass mc=userList.get(position);
                            mc.setTextview2(updatedItem);
                            userList.set(position, mc);*/
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
            dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        adapter.notifyItemChanged(position);
                    }
                });
                AlertDialog alertDialog = dialogBuilder.create();
                alertDialog.show();
            }
        }
    };

}

