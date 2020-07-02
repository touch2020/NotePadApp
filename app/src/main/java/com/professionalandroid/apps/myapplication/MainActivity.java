package com.professionalandroid.apps.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Serializable {



    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    Button create;

    List<Notepad> notepadList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        notepadList = new ArrayList<>();




        recyclerView = findViewById(R.id.recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerAdapter = new RecyclerAdapter(notepadList);
        recyclerView.setAdapter(recyclerAdapter);
        create = findViewById(R.id.create);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(MainActivity.this, EnterActivity.class);
                startActivityForResult(intent, 0);

            }

        });
        findViewById(R.id.re).setOnClickListener(new View.OnClickListener(){
            Intent intent = new Intent(MainActivity.this,MainActivity.class);

            @Override
            public void onClick(View view) {

                startActivity(intent);
                finish();
            }
        });


    }
    //
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("list", (Serializable) notepadList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0) {
            String strMain = data.getStringExtra("main");
            String strSub  = data.getStringExtra("sub");

            Notepad notepad = new Notepad(strMain, strSub, 0);
            recyclerAdapter.addItem(notepad);
            recyclerAdapter.notifyDataSetChanged();



        }
    }

    static class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder>{

        private List<Notepad> listdata;

        /*private AdapterView.OnItemClickListener mListener = null;

        public interface OnItemClickListener{
            void onItemClick(View view, int position);
        }
        public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
            this.mListener = listener;
        }*/

        public RecyclerAdapter(List<Notepad> listdata){
            this.listdata= listdata;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            Notepad notepad = listdata.get(i);


            itemViewHolder.maintext.setTag(notepad.getSeq());
            itemViewHolder.maintext.setText(notepad.getMaintext());
            itemViewHolder.subtext.setText(notepad.getSubtext());

            if(notepad.getIsdone() == 0) {
                itemViewHolder.img.setBackgroundColor(Color.LTGRAY);
            }else{
                itemViewHolder.img.setBackgroundColor(Color.GREEN);
            }
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public void addItem(Notepad notepad) {
            listdata.add(notepad);
        }

        public void removeItem(int position) {
            listdata.remove(position);
        }


        class ItemViewHolder extends RecyclerView.ViewHolder{
            private TextView maintext;
            private TextView subtext;
            private ImageView img;

            public ItemViewHolder(View itemView){
                super(itemView);

                maintext = itemView.findViewById(R.id.item_maintext);
                subtext = itemView.findViewById(R.id.item_subtext);
                img = itemView.findViewById(R.id.item_image);

                /*itemView.setOnLongClickListener(new View.OnLongClickListener(){
                    @Override
                    public boolean onLongClick(View view) {

                        int position = getAdapterPosition();
                        int seq = (int)maintext.getTag();

                        if(position != RecyclerView.NO_POSITION){
                            dbHelper.deleteNotepad(seq);
                            removeItem(position);
                            notifyDataSetChanged();
                        }
                        return false;
                    }
                });*/

                /*itemView.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION) {

                        }
                    }

                    }); */
                }

            }

        }

    }
