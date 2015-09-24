package au.edu.swin.csk.prototype;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 * Created by Fahad Alhamed on 22/09/15.
 */
public class TestDB {

    private int _EvidenceCode;
    private String _EvidenceDate;
    private String _EvidenceComment;
    private String _groupID;
    private String _activityName;
    KinderDBCon k;
    SQLiteDatabase db;
    Context context;



    TestDB(Context c) {
        this.context = c;
        //add();
    }

    public void add() {
        addChildList();
        addActivity();
        addLoCode();
    }

    public void addChildList() {

        k.InsertIntoChildTable(5, "Jack", "A", "male", 1);
        k.InsertIntoChildTable(6, "John" , "M" , "male" , 1);
        k.InsertIntoChildTable(7, "Chris" , "T" , "male" , 1);
        k.InsertIntoChildTable(8, "Tim", "K", "male", 1);

    }

    public void addActivity() {
        k.InsertIntoActivityTable("Cooking", "cookies", "baked");
        k.InsertIntoActivityTable("Art", "cookies", "baked");
        k.InsertIntoActivityTable("Sport", "cookies", "baked");
        k.InsertIntoActivityTable("Drawing", "cookies", "baked");
        k.InsertIntoActivityTable("Swimming", "cookies", "baked");

    }

    public void addLoCode() {
        k.InsertIntoLOCodeTable(1.1, "first");
        k.InsertIntoLOCodeTable(1.2, "first");
        k.InsertIntoLOCodeTable(1.3, "first");
        k.InsertIntoLOCodeTable(1.4, "first");
        k.InsertIntoLOCodeTable(1.5, "first");
        k.InsertIntoLOCodeTable(2.1, "first");
        k.InsertIntoLOCodeTable(2.2, "first");
        k.InsertIntoLOCodeTable(3.1, "first");
        k.InsertIntoLOCodeTable(3.2, "first");

    }

    public void addEvidenceCard(){

        k.InsertIntoEvidenceTable(222, "18/12/12", "this is comment", 222, "cooking");

    }

    public void deleteAll(){

        k.dropAll(db);

    }

}
