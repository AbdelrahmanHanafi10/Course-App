package com.example.course;

import androidx.appcompat.app.AppCompatActivity;

//import  com.example.course.MainActivity;

//import android.media.Image;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.course.model.Course;
import com.example.course.model.Course_Database;

import java.util.ArrayList;

public class CoursesActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //MainActivity main=new MainActivity();


        Course_Database db=new Course_Database(this);
        //String course_data= db.insert("Course","Details","Teacher");
        ArrayList <Course> data=db.getdata();
        //Toast.makeText(this,data.get(1).getId()+"",Toast.LENGTH_LONG).show();

        //String course_data=db.insert(main.CourseName.getText().toString(),main.Description.getText().toString(),main.TeacherName.getText().toString());
        //Toast.makeText(this,course_data ,Toast.LENGTH_LONG).show();


        //final String [] courses={"English","Graphic Design","Art","Flutter","Android Development","Web Development"};
        //int []images={R.mipmap.course1,};
        //ArrayList<item> itemArrayList = new ArrayList<item>();
       /* int k=0;
        while (k<=courses.length) {
            itemArrayList.add(new item(courses[k], images[k]));
            k++;
        }/*/

        ListView list_View=findViewById(R.id.listview);
        Add a =new Add(data);
        list_View.setAdapter(a);


        //ArrayAdapter arrayAdapter= new ArrayAdapter(this,R.layout.item_list,R.id.row,courses);
        //list_View.setAdapter(arrayAdapter);

        /*list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(CoursesActivity.this, courses [position]+ " Details",Toast.LENGTH_LONG).show();
            }
        });*/

        list_View.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent =new Intent(CoursesActivity.this,detail_admin.class);
                intent.putExtra("name",data.get(i).getName());
                intent.putExtra("teachername",data.get(i).getTeacherName());
                intent.putExtra("description",data.get(i).getDescription());
                intent.putExtra("id",data.get(i).getId());
                startActivity(intent);
            }
        });

        Button b=(Button) findViewById(R.id.go_to_add_course);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CoursesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }

     class Add extends BaseAdapter{
        ArrayList<Course>l=new ArrayList<Course>();
        public Add(ArrayList<Course>v){
            this.l=v;

        }

        @Override
        public int getCount() {
            return l.size();
        }

        @Override
        public Object getItem(int position) {
            return l.get(position).getName();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            LayoutInflater ly= getLayoutInflater();
            View view1=ly.inflate(R.layout.item_list,null);
            TextView tt=(TextView)view1.findViewById(R.id.row);
            TextView tt1=(TextView)view1.findViewById(R.id.row1);
            //ImageView imag=(ImageView)view1.findViewById(R.id.img);
            tt.setText(l.get(position).getName().toString());
            tt1.setText(l.get(position).getTeacherName().toString());
            //imag.setImageResource(R.mipmap.course_image);

            return view1;
        }
    }





}