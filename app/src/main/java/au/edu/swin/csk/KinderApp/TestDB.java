package au.edu.swin.csk.KinderApp;

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
        //addEvidChild();
        addEvidImage();
        addLOEvidence();

    }

    public void addGroup() {
        k.InsertIntoGroupTable( "Koala");
        k.InsertIntoGroupTable( "Wombat");
        k.InsertIntoGroupTable( "4YO");
        k.InsertIntoGroupTable( "3YO");
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

        k.InsertIntoEvidenceTable("18/12/2012", "this is comment", 1, "cooking");
        k.InsertIntoEvidenceTable("18/02/2012", "this is comment", 2, "Swimming");
        k.InsertIntoEvidenceTable("3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable("2/4/2012", "this is comment", 3, "Art");
        k.InsertIntoEvidenceTable("22/12/2012", "this is comment", 3, "Sport");
        k.InsertIntoEvidenceTable("18/3/2012", "this is comment", 3, "Drawing");
        k.InsertIntoEvidenceTable("18/12/2012", "this is comment", 1, "cooking");
        k.InsertIntoEvidenceTable("18/02/2012", "this is comment", 4, "Swimming");
        k.InsertIntoEvidenceTable("3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 2, "Drawing");
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "cooking");
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming");
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 2, "Drawing");
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "cooking");
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming");
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "cooking");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing");

    }

    public void addChildList() {
        k.InsertIntoChildTable("Jack", "A", "male", 1);
        k.InsertIntoChildTable("John" , "M" , "male" , 2);
        k.InsertIntoChildTable("Chris" , "T" , "male" , 3);
        k.InsertIntoChildTable("Tim", "K", "male", 1);
        k.InsertIntoChildTable("James" , "Y" , "male" , 1);
        k.InsertIntoChildTable("Tim", "R", "male", 3);
        k.InsertIntoChildTable("Andy", "N", "male", 1);
        k.InsertIntoChildTable("Warren", "M", "male", 2);
        k.InsertIntoChildTable("Tommy", "T", "male", 2);
        k.InsertIntoChildTable( "Rocky", "K", "male", 3);
    }

    public void addEvidChild() {
        //child id , evidence id
        k.InsertIntoEvidenceChildTable(1, 1);
        k.InsertIntoEvidenceChildTable(1, 2);
        k.InsertIntoEvidenceChildTable(1, 3);
        k.InsertIntoEvidenceChildTable(1, 1);
        k.InsertIntoEvidenceChildTable(2, 1);
        k.InsertIntoEvidenceChildTable(3, 3);
        k.InsertIntoEvidenceChildTable(3, 3);
        k.InsertIntoEvidenceChildTable(3, 4);
        k.InsertIntoEvidenceChildTable(3, 4);
        k.InsertIntoEvidenceChildTable(3, 5);
    }

    public void addEvidImage() {
        k.InsertIntoPhotoTable("img001", 1);
        k.InsertIntoPhotoTable("img002", 2);
        k.InsertIntoPhotoTable("img003", 3);
        k.InsertIntoPhotoTable("img004", 4);
        k.InsertIntoPhotoTable("img005", 5);
        k.InsertIntoPhotoTable("img006", 6);
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
    }


    public void deleteAll(){

        k.dropAll(db);
    }

}
