package au.edu.swin.csk.KinderApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


/**
 *
 * Test class used for constructing inserting data to db for testing
 * @Author  Fahad Alhamed 747234x
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
        addEvidChild();
        addLOEvidence();

    }

    public void addGroup() {
        k.InsertIntoGroupTable("Koala");
        k.InsertIntoGroupTable("Wombat");
        k.InsertIntoGroupTable("4YO");
        k.InsertIntoGroupTable("3YO");
    }

    public void addActivity() {
        k.InsertIntoActivityTable("Cooking", "cookies", "baked");
        k.InsertIntoActivityTable("Art", "cookies", "baked");
        k.InsertIntoActivityTable("Sport", "cookies", "baked");
        k.InsertIntoActivityTable("Drawing", "cookies", "baked");
        k.InsertIntoActivityTable("Swimming", "cookies", "baked");
    }

    public void addEvidenceData(){

        k.InsertIntoEvidenceTable("18/12/2012", "this is comment", 3, "Cooking", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable("18/02/2012", "this is comment", 3, "Swimming", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable("3/5/2012", "this is comment", 3, "Cooking", "img001", null, null, null);
        k.InsertIntoEvidenceTable("2/4/2012", "this is comment", 3, "Art", "img001", null, null, null);
        k.InsertIntoEvidenceTable("22/12/2012", "this is comment", 1, "Sport", "img001", null, null, null);
        k.InsertIntoEvidenceTable("18/3/2012", "this is comment", 3, "Drawing", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable("18/12/2012", "this is comment", 2, "Cooking", "img001", null, null, null);
        k.InsertIntoEvidenceTable("18/02/2012", "this is comment", 4, "Swimming", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable("3/5/2012", "this is comment", 3, "Cooking", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 2, "Drawing", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "Cooking", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "Cooking", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "thumbWombat20151016_212000.jpg", null, null, null);
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 2, "Drawing", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "Cooking", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "Cooking", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", null, null, null);
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing", "img001", null, null, null);

    }

    public void addChildList() {
        k.InsertIntoChildTable("Jack", "A", "male", 3);
        k.InsertIntoChildTable("John", "M", "male", 2);
        k.InsertIntoChildTable("Chris", "T", "male", 3);
        k.InsertIntoChildTable("Tim", "K", "male", 1);
        k.InsertIntoChildTable("James", "Y", "male", 1);
        k.InsertIntoChildTable("Tim", "R", "male", 3);
        k.InsertIntoChildTable("Andy", "N", "male", 1);
        k.InsertIntoChildTable("Warren", "M", "male", 2);
        k.InsertIntoChildTable("Tommy", "T", "male", 2);
        k.InsertIntoChildTable("Rocky", "K", "male", 3);
    }

    public void addEvidChild() {
        //child id , evidence id
        k.InsertIntoEvidenceChildTable(1, 1);
        k.InsertIntoEvidenceChildTable(1, 2);
        k.InsertIntoEvidenceChildTable(1, 3);
        k.InsertIntoEvidenceChildTable(1, 4);
        k.InsertIntoEvidenceChildTable(2, 7);
        k.InsertIntoEvidenceChildTable(3, 1);
        k.InsertIntoEvidenceChildTable(3, 2);
        k.InsertIntoEvidenceChildTable(3, 3);
        k.InsertIntoEvidenceChildTable(3, 4);
        k.InsertIntoEvidenceChildTable(4, 8);
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

    public void addLOEvidence() {
        k.InsertIntoEvidenceLOutcomeTable(1, 1.2);
        k.InsertIntoEvidenceLOutcomeTable(1, 1.3);
        k.InsertIntoEvidenceLOutcomeTable(1, 1.4);
        k.InsertIntoEvidenceLOutcomeTable(1, 1.5);
        k.InsertIntoEvidenceLOutcomeTable(2, 2.1);
        k.InsertIntoEvidenceLOutcomeTable(3, 3.2);
        k.InsertIntoEvidenceLOutcomeTable(3, 3.3);
        k.InsertIntoEvidenceLOutcomeTable(4, 4.1);
        k.InsertIntoEvidenceLOutcomeTable(4, 4.2);
        k.InsertIntoEvidenceLOutcomeTable(6, 1.2);
        k.InsertIntoEvidenceLOutcomeTable(7, 1.3);
        k.InsertIntoEvidenceLOutcomeTable(8, 1.4);
        k.InsertIntoEvidenceLOutcomeTable(9, 1.5);
        k.InsertIntoEvidenceLOutcomeTable(10, 2.1);
        k.InsertIntoEvidenceLOutcomeTable(11, 3.2);
        k.InsertIntoEvidenceLOutcomeTable(12, 3.3);
        k.InsertIntoEvidenceLOutcomeTable(13, 4.1);
        k.InsertIntoEvidenceLOutcomeTable(14, 4.2);
    }

    public void deleteAll(){

        k.dropAll(db);
    }

}
