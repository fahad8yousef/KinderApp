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



    TestDB( KinderDBCon k) {
        this.k = k;
        add();
    }

    public void add() {
        addChildList();
        addActivity();
        addLoCode();
        addGroup();
        addEvidenceData();

    }

    public void addGroup() {
        k.InsertIntoGroupTable(1, "Koala");
        k.InsertIntoGroupTable(2, "Wombat");
        k.InsertIntoGroupTable(3, "4YO");
    }

    public void addChildList() {

        k.InsertIntoChildTable(1, "Jack", "A", "male", 1);
        k.InsertIntoChildTable(2, "John" , "M" , "male" , 2);
        k.InsertIntoChildTable(3, "Chris" , "T" , "male" , 3);
        k.InsertIntoChildTable(4, "Tim", "K", "male", 1);
        k.InsertIntoChildTable(5, "James" , "Y" , "male" , 1);
        k.InsertIntoChildTable(6, "Tim" , "R" , "male" , 3);
        k.InsertIntoChildTable(7, "Andy", "N", "male", 1);
        k.InsertIntoChildTable(8, "Warren" , "M" , "male" , 2);
        k.InsertIntoChildTable(9, "Tommy" , "T" , "male" , 2);
        k.InsertIntoChildTable(10, "Rocky", "K", "male", 3);
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

    public void addEvidenceData(){

        k.InsertIntoEvidenceTable(1, "18/12/2012", "this is comment", 1, "cooking");
        k.InsertIntoEvidenceTable(2, "18/02/2012", "this is comment", 2, "Swimming");
        k.InsertIntoEvidenceTable(3, "3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable(4, "2/4/2012", "this is comment", 3, "Art");
        k.InsertIntoEvidenceTable(5, "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable(6, "18/3/2012", "this is comment", 2, "Drawing");
        k.InsertIntoEvidenceTable(7, "18/12/2012", "this is comment", 1, "cooking");
        k.InsertIntoEvidenceTable(8, "18/02/2012", "this is comment", 4, "Swimming");
        k.InsertIntoEvidenceTable(9, "3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable(10, "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable(11, "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable(12, "18/3/2012", "this is comment", 2, "Drawing");
        k.InsertIntoEvidenceTable(13, "18/12/2012", "this is comment", 4, "cooking");
        k.InsertIntoEvidenceTable(14, "18/02/2012", "this is comment", 2, "Swimming");
        k.InsertIntoEvidenceTable(15, "3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable(16, "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable(17, "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable(18, "18/3/2012", "this is comment", 4, "Drawing");
        k.InsertIntoEvidenceTable(19, "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable(20, "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable(21, "18/3/2012", "this is comment", 2, "Drawing");
        k.InsertIntoEvidenceTable(22, "18/12/2012", "this is comment", 4, "cooking");
        k.InsertIntoEvidenceTable(23, "18/02/2012", "this is comment", 2, "Swimming");
        k.InsertIntoEvidenceTable(24, "3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable(25, "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable(26, "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable(27, "18/3/2012", "this is comment", 4, "Drawing");

    }

    public void deleteAll(){

        k.dropAll(db);
    }

}
