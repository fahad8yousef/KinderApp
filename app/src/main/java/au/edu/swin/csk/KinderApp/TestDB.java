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
        //delete();
        //k.getChildNameByID("1");
    }

    public void add() {
        addChildList();
        addActivity();
        addLoCode();
        addGroup();
        addEvidenceData();
        addEvidChild();
        addLOEvidence();
        addLoutcome();
    }

    public void addGroup() {
        k.InsertIntoGroupTable("4YO Koala");
        k.InsertIntoGroupTable("4YO Red");
        k.InsertIntoGroupTable("3YO Wombat");
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

        k.InsertIntoEvidenceTable("18/12/2012", "this is comment", 3, "Cooking", "thumbWombat20151016_212000.jpg", "false", "", "");
        k.InsertIntoEvidenceTable("18/02/2012", "this is comment", 3, "Swimming", "thumbWombat20151016_212000.jpg", "false", "", "");
        k.InsertIntoEvidenceTable("3/5/2012", "this is comment", 3, "Cooking", "img001", "false", "", "");
        k.InsertIntoEvidenceTable("2/4/2012", "this is comment", 3, "Art", "img001", "false", "", "");
        k.InsertIntoEvidenceTable("22/12/2012", "this is comment", 1, "Sport", "img001", "false", "", "");
        k.InsertIntoEvidenceTable("18/3/2012", "this is comment", 3, "Drawing", "thumbWombat20151016_212000.jpg", "", "", "");
        k.InsertIntoEvidenceTable("18/12/2012", "this is comment", 2, "Cooking", "img001", "", "", "");
        k.InsertIntoEvidenceTable("18/02/2012", "this is comment", 4, "Swimming", "", "", "", "");
        k.InsertIntoEvidenceTable("3/5/2012", "this is comment", 3, "Cooking", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 2, "Drawing", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "Cooking", "", "", "", "");
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "Cooking", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 2, "Drawing", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "Cooking", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "Cooking", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "Cooking", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 1, "Drawing", "img001", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 1, "Art", "", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 1, "Drawing", "", "", "", "");
        k.InsertIntoEvidenceTable( "18/12/2012", "this is comment", 4, "Cooking", "", "", "", "");
        k.InsertIntoEvidenceTable( "18/02/2012", "this is comment", 2, "Swimming", "", "", "", "");
        k.InsertIntoEvidenceTable( "3/5/2012", "this is comment", 3, "Cooking", "", "", "", "");
        k.InsertIntoEvidenceTable( "2/4/2012", "this is comment", 4, "Art", "", "", "", "");
        k.InsertIntoEvidenceTable( "22/12/2012", "this is comment", 1, "Sport", "", "", "", "");
        k.InsertIntoEvidenceTable( "18/3/2012", "this is comment", 4, "Drawing", "", "", "", "");

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

    public void addLoutcome() {
        k.InsertIntoLOUTCOMETable("1.1.1","build secure attachment with one and then...");
        k.InsertIntoLOUTCOMETable("2.1.1","use effective routines to help make predicted...");
        k.InsertIntoLOUTCOMETable("3.1.1","sense and respond to a feeling of belonging");
        k.InsertIntoLOUTCOMETable("4.1.1","communicate their needs for comfort and assistance");
        k.InsertIntoLOUTCOMETable("5.1.1","establish and maintain respectful, trusting...");
        k.InsertIntoLOUTCOMETable("6.1.1","openly express their feelings and ideas in their...");
        k.InsertIntoLOUTCOMETable("7.1.1","respond to ideas and suggestions from others");
        k.InsertIntoLOUTCOMETable("8.1.1","express a wide range of emotions, thoughts...");
        k.InsertIntoLOUTCOMETable("9.1.1","engage in and contribute to shared play experiences");
        k.InsertIntoLOUTCOMETable("10.1.1","increasingly cooperate and work collaboratively...");
        k.InsertIntoLOUTCOMETable("11.1.1","demonstrate an increasing capacity for self-regu...");
        k.InsertIntoLOUTCOMETable("12.1.1","share aspects of their culture with other children and ...");
        k.InsertIntoLOUTCOMETable("13.1.1","build secure attachment with one and then more...");
        k.InsertIntoLOUTCOMETable("14.1.1","develop their social and cultural heritage...");
        k.InsertIntoLOUTCOMETable("15.1.1","explore aspects of identity through role-p...");
        k.InsertIntoLOUTCOMETable("16.1.1","respond to ideas and suggestions from oth...");
    }

    public void addLOEvidence() {
        k.InsertIntoEvidenceLOutcomeTable(1, "1.1.1");
        k.InsertIntoEvidenceLOutcomeTable(1, "2.1.1");
        k.InsertIntoEvidenceLOutcomeTable(1, "3.1.1");
        k.InsertIntoEvidenceLOutcomeTable(1, "4.1.1");
        k.InsertIntoEvidenceLOutcomeTable(2, "5.1.1");
        k.InsertIntoEvidenceLOutcomeTable(2, "4.1.1");
        k.InsertIntoEvidenceLOutcomeTable(3, "5.1.1");
        k.InsertIntoEvidenceLOutcomeTable(3, "8.1.1");
        k.InsertIntoEvidenceLOutcomeTable(1, "9.1.1");
        k.InsertIntoEvidenceLOutcomeTable(3, "7.1.1");
        k.InsertIntoEvidenceLOutcomeTable(4, "10.1.1");

    }

    public void delete(){

        k.deleteEvidenceByID("1");
    }
}
