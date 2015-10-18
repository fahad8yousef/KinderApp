package au.edu.swin.csk.KinderApp;

/**
 * Created by Subzero on 14/09/2015.
 * @Author Fahad Alhamed
 * This class construct database and create tables
 * Contains methods for inserting data
 * Contains methods for getting data
 *
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

public class KinderDBCon {

    private static final  String TAG="App/ KinderDBCon";

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NAME_GROUPID = "groupID";
    public static final String KEY_NAME_GROUPNAME = "groupName";
    public static final String KEY_NAME_CHILDID = "childID";
    public static final String KEY_NAME_CHILDFIRSTNAME = "childFirstName";
    public static final String KEY_NAME_CHILDSURNAME = "childSurName";
    public static final String KEY_NAME_CHILDGENDER = "childGender";
    public static final String KEY_NAME_ACTIVITYNAME = "activityName";
    public static final String KEY_NAME_ACTIVITYTYPE = "activityType";
    public static final String KEY_NAME_ACTIVITYDESCRIPTION = "activityDescription";
    public static final String KEY_NAME_EvidenceCODE = "EvidenceCode";
    public static final String KEY_NAME_EvidenceDATE = "EvidenceDate";
    public static final String KEY_NAME_EvidenceCOMMENT = "EvidenceComment";
    public static final String KEY_NAME_PHOTOFILENAME = "photoFilename";
    public static final String KEY_NAME_LOCODE = "loCode";
    public static final String KEY_NAME_LOCDESCRIPTION = "locDescription";
    public static final String KEY_NAME_LOUTCOMECODE = "loutcomeCode";
    public static final String KEY_NAME_LOUTCOMEEvidence = "loutcomeEvidence";
    public static final String KEY_NAME_COMPLETIONSTATUS = "completionStatus";
    public static final String KEY_NAME_CHILDCHECKBOX = "childCheckBox";
    public static final String KEY_NAME_LOCODECHECKBOX = "loCodeCheckBox";


    public static final String KEY_CREATE_TABLE = " CREATE TABLE IF NOT EXISTS ";
    public static final String KEY_DROP_TABLE = " DROP TABLE IF EXISTS ";
    public static final String KEY_PRIMARY_KEY = " PRIMARY KEY ";
    public static final String KEY_AUTOINCREMENT = "  ";
    public static final String KEY_CONSTRAINT = " CONSTRAINT ";
    public static final String KEY_FOREIGN_KEY = " FOREIGN KEY ";
    public static final String KEY_REFERENCES = " REFERENCES ";
    public static final String KEY_FK_NAME = " KEY ";
    public static final String KEY_COMMA = " , ";
    public static final String KEY_INVERTED_COMMA = " ; ";
    public static final String KEY_NOTNULL = " NOT NULL ";
    public static final String KEY_OPEN_PARENTHESIS = " ( ";
    public static final String KEY_CLOSE_PARENTHESIS = " ) ";
    public static final String KEY_SEMI_COLON = " ; ";
    public static final String KEY_INTEGER = " INTEGER ";
    public static final String KEY_TEXT = " TEXT ";
    public static final String KEY_REAL = " REAL ";
    public static final String KEY_DATETIME = " DATETIME ";


    private static final String DATABASE_NAME = "KinderGardenDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_TABLE_GROUP = "GroupName";
    private static final String DATABASE_TABLE_CHILD = "Child";
    private static final String DATABASE_TABLE_ACTIVITY = "Activity";
    private static final String DATABASE_TABLE_EVIDENCE = "Evidence";
    private static final String DATABASE_TABLE_EVIDENCECHILD = "EvidenceChild";
    private static final String DATABASE_TABLE_PHOTO = "Photo";
    private static final String DATABASE_TABLE_LOCODE = "LOCode";
    private static final String DATABASE_TABLE_LOUTCOME = "LOutcome";
    private static final String DATABASE_TABLE_EVIDENCELOUTCOME = "EvidenceLOutcome";

    // Defining the Forign Keys
    // has to be filled later
    //

    private DbHelper _dbHelper;
    private final Context _context;
    private SQLiteDatabase _db;


    // DbHelperClass
    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }


        // OnCreate method will create the database tables
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(
                    // Creating Group Table

                    KEY_CREATE_TABLE + DATABASE_TABLE_GROUP + KEY_OPEN_PARENTHESIS + KEY_NAME_GROUPID + KEY_INTEGER + KEY_AUTOINCREMENT + KEY_COMMA +
                            KEY_NAME_GROUPNAME + KEY_TEXT + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_GROUPID + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
            db.execSQL(
                    // Creating Child Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_CHILD + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_CHILDID + KEY_INTEGER + KEY_AUTOINCREMENT + KEY_COMMA +
                            KEY_NAME_CHILDFIRSTNAME + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_CHILDSURNAME + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_CHILDGENDER + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_GROUPID + KEY_INTEGER + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "CHILD_FK_GROUPID" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_GROUPID + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_GROUP + KEY_OPEN_PARENTHESIS + KEY_NAME_GROUPID + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
            db.execSQL(
                    // Creating  Activity Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_ACTIVITY + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_ACTIVITYNAME + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_ACTIVITYTYPE + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_ACTIVITYDESCRIPTION + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_ACTIVITYNAME + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
            db.execSQL(
                    // Creating  Evidence Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_EVIDENCE + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_EvidenceCODE + KEY_INTEGER + KEY_AUTOINCREMENT + KEY_COMMA +
                            KEY_NAME_EvidenceDATE + KEY_DATETIME + KEY_COMMA +
                            KEY_NAME_EvidenceCOMMENT + KEY_TEXT + KEY_COMMA +
                            KEY_NAME_GROUPID + KEY_INTEGER + KEY_COMMA +
                            KEY_NAME_ACTIVITYNAME + KEY_TEXT + KEY_COMMA +
                            KEY_NAME_PHOTOFILENAME + KEY_TEXT + KEY_COMMA +
                            KEY_NAME_COMPLETIONSTATUS + KEY_TEXT + KEY_COMMA +
                            KEY_NAME_CHILDCHECKBOX + KEY_TEXT + KEY_COMMA +
                            KEY_NAME_LOCODECHECKBOX + KEY_TEXT + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceCODE + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "Evidence_FK_GROPID" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_GROUPID + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_GROUP + KEY_OPEN_PARENTHESIS + KEY_NAME_GROUPID + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "EVIDENCE_FK_ACTIVITYNAME" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_ACTIVITYNAME + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_ACTIVITY + KEY_OPEN_PARENTHESIS + KEY_NAME_ACTIVITYNAME + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );

            db.execSQL(
                    // Creating  EvidenceChild Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_EVIDENCECHILD + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_CHILDID + KEY_INTEGER + KEY_COMMA +
                            KEY_NAME_EvidenceCODE + KEY_INTEGER + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_COMMA + KEY_NAME_EvidenceCODE + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_CHILD + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_EVIDENCE + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceCODE + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );

            db.execSQL(
                    // Creating  LOCode Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_LOCODE + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_LOCODE + KEY_REAL + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_LOCDESCRIPTION + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_LOCODE + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
            db.execSQL(
                    // Creating  LOUTCOME Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_LOUTCOME + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_LOUTCOMECODE + KEY_REAL + KEY_NOTNULL + KEY_COMMA +
                            KEY_NAME_LOUTCOMEEvidence + KEY_TEXT + KEY_NOTNULL + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_LOUTCOMECODE + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
            db.execSQL(
                    // Creating  EvidenceLOUTCOME Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_EVIDENCELOUTCOME + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_EvidenceCODE + KEY_INTEGER + KEY_COMMA +
                            KEY_NAME_LOUTCOMECODE + KEY_REAL + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceCODE + KEY_COMMA + KEY_NAME_LOUTCOMECODE + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "EVIDENCELOUTCOME_FK_EvidenceCODE" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceCODE + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_PHOTO + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceCODE + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "EVIDENCELOUTCOME_FK_LOUTCOMECODE" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_LOUTCOMECODE + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_LOCODE + KEY_OPEN_PARENTHESIS + KEY_NAME_LOUTCOMECODE + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
          /*  try {
                CSVReader cs = new CSVReader("File_Path",this);
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_CHILD);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_ACTIVITY);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_GROUP);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_EVIDENCELOUTCOME);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_EVIDENCE);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_LOUTCOME);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_EVIDENCECHILD);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_LOCODE);
            db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_PHOTO);

            onCreate(db);
        }


    }
    //############################

    public KinderDBCon(Context context) {
        _context = context;
    }


    public KinderDBCon open() throws SQLException{
        _dbHelper = new DbHelper(_context);
        _db = _dbHelper.getWritableDatabase();

        return this;
    }

    public void close(){
        _dbHelper.close();
    }

    //#################################
    // Inserting methods for each table
    //#################################

    public long InsertIntoGroupTable(String _name)
    {
        ContentValues cv = new ContentValues();

        //cv.put(KEY_NAME_GROUPID,_groupID);
        cv.put(KEY_NAME_GROUPNAME, _name);


        return _db.insert(DATABASE_TABLE_GROUP,null,cv);
    }

    public long InsertIntoChildTable(String _childFirstName,String _childSurName,String _childGender,int _groupID)
    {
        ContentValues cv = new ContentValues();
        //cv.put(KEY_NAME_CHILDID,_childID);
        cv.put(KEY_NAME_CHILDFIRSTNAME, _childFirstName);
        cv.put(KEY_NAME_CHILDSURNAME, _childSurName);
        cv.put(KEY_NAME_CHILDGENDER, _childGender);
        cv.put(KEY_NAME_GROUPID,_groupID);

        return _db.insert(DATABASE_TABLE_CHILD,null,cv);
    }
    public long InsertIntoActivityTable(String _activityName,String _activityType,String _activityDescription)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_ACTIVITYNAME, _activityName);
        cv.put(KEY_NAME_ACTIVITYTYPE, _activityType);
        cv.put(KEY_NAME_ACTIVITYDESCRIPTION, _activityDescription);

        return _db.insert(DATABASE_TABLE_ACTIVITY,null,cv);
    }


    public Long InsertIntoEvidenceTable(String _EvidenceDate,String _EvidenceComment,int _groupID,String _activityName, String _photoFileName, String _completionStatus, String _childCheckBox, String _loCodeCheckBox) //Add extra columns
    {
        ContentValues cv = new ContentValues();
        //cv.put(KEY_NAME_EvidenceCODE,_EvidenceCode);
        cv.put(KEY_NAME_EvidenceDATE, _EvidenceDate);
        cv.put(KEY_NAME_EvidenceCOMMENT, _EvidenceComment);
        cv.put(KEY_NAME_GROUPID,_groupID);
        cv.put(KEY_NAME_ACTIVITYNAME, _activityName);
        cv.put(KEY_NAME_PHOTOFILENAME, _photoFileName);
        cv.put(KEY_NAME_COMPLETIONSTATUS, _completionStatus);
        cv.put(KEY_NAME_CHILDCHECKBOX, _childCheckBox);
        cv.put(KEY_NAME_LOCODECHECKBOX, _loCodeCheckBox);

        Long a;
//        Log.d(TAG, "evid code added Long = "+ s);
        return a = _db.insert(DATABASE_TABLE_EVIDENCE,null,cv);
    }

    public long InsertIntoEvidenceChildTable(int _childID,int _EvidenceCode)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_CHILDID,_childID);
        cv.put(KEY_NAME_EvidenceCODE,_EvidenceCode);

        return _db.insert(DATABASE_TABLE_EVIDENCECHILD,null,cv);
    }

    public long InsertIntoLOCodeTable(double _loCode,String _locDescription)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_LOCODE,_loCode);
        cv.put(KEY_NAME_LOCDESCRIPTION,_locDescription);

        return _db.insert(DATABASE_TABLE_LOCODE,null,cv);
    }

    public long InsertIntoLOUTCOMETable(double _loutcomeCode,String _loutcomeEvidence)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_LOUTCOMECODE,_loutcomeCode);
        cv.put(KEY_NAME_LOUTCOMEEvidence,_loutcomeEvidence);

        return _db.insert(DATABASE_TABLE_LOUTCOME,null,cv);
    }

    public long InsertIntoEvidenceLOutcomeTable(int _EvidenceCode,double _loutcomeCode)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_EvidenceCODE,_EvidenceCode);
        cv.put(KEY_NAME_LOUTCOMECODE,_loutcomeCode);

        return _db.insert(DATABASE_TABLE_EVIDENCELOUTCOME,null,cv);
    }
    //#######################################
    // End of Inserting methords to the table
    //#######################################


    //#############################################
    // Starting methord that read from the database
    //#############################################

    // getting data from group table
    public String getGroupData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {" * "};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_GROUP, columns, null, null, null, null, null);

        String result = "";
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);
        int iGroupName = c.getColumnIndex(KEY_NAME_GROUPNAME);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getInt(iGroupRowID) + " " + c.getString(iGroupName) + "\n";
        }

        return  result;
    }

    // getting data from Child table
    public String getChildData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_CHILDFIRSTNAME,KEY_NAME_CHILDSURNAME,KEY_NAME_CHILDGENDER,KEY_NAME_GROUPID};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_CHILD,columns,null,null,null,null,null);

        String result = "";
        int iChildRowID = c.getColumnIndex(KEY_NAME_CHILDID);
        int iChildFirstName = c.getColumnIndex(KEY_NAME_CHILDFIRSTNAME);
        int iChildSurName = c.getColumnIndex(KEY_NAME_CHILDSURNAME);
        int iChildGender = c.getColumnIndex(KEY_NAME_CHILDGENDER);
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getInt(iChildRowID) + " " + c.getString(iChildFirstName)  + " " + c.getString(iChildSurName) + " " + c.getString(iChildGender) + " " + c.getInt(iGroupRowID) + "\n";
        }
        //Log.d(TAG, result);

        return  result;
    }

    // getting data from Activity table
    public String getActivityData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_ACTIVITYNAME,KEY_NAME_ACTIVITYTYPE,KEY_NAME_ACTIVITYDESCRIPTION};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_ACTIVITY, columns, null, null, null, null, null);

        String result = "";
        int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);
        int iActivityType = c.getColumnIndex(KEY_NAME_ACTIVITYTYPE);
        int iActivityDescription = c.getColumnIndex(KEY_NAME_ACTIVITYDESCRIPTION);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getString(iActivityName)  + " " + c.getString(iActivityType) + " " + c.getString(iActivityDescription) + "\n";
        }

        return  result;
    }

    // getting data from Evidence table
    public ArrayList<String> getEvidenceData(String evidID)
    {
        // Creating a string array to store result from database before passing
        //String [] columns = new String[] {KEY_NAME_EvidenceCODE,KEY_NAME_EvidenceDATE,KEY_NAME_EvidenceCOMMENT,KEY_NAME_GROUPID,KEY_NAME_ACTIVITYNAME};
        // Creating a cursor to iterate through db
        //Cursor c = _db.query(DATABASE_TABLE_EVIDENCE,columns,null,null,null,null,null);
        final String query = "SELECT * FROM Evidence WHERE EvidenceCode=\""+evidID+"\";\n" +"\n";
        Cursor c = _db.rawQuery(query, null);

        ArrayList<String> result = new ArrayList<String>();
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);
        int iEvidenceDate = c.getColumnIndex(KEY_NAME_EvidenceDATE);
        int iEvidenceComment = c.getColumnIndex(KEY_NAME_EvidenceCOMMENT);
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);
        int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);
        int iphotoFileName = c.getColumnIndex(KEY_NAME_PHOTOFILENAME);
        int iCompletionStatus = c.getColumnIndex(KEY_NAME_COMPLETIONSTATUS);
        int iChildCheckBox = c.getColumnIndex(KEY_NAME_CHILDCHECKBOX);
        int iLoCodeCheckBox = c.getColumnIndex(KEY_NAME_LOCODECHECKBOX);


        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result.add(c.getString(iEvidenceDate));
            result.add(c.getString(iEvidenceComment));
            result.add(c.getString(iActivityName));
            result.add(c.getString(iphotoFileName));
            result.add(c.getString(iChildCheckBox));
            result.add(c.getString(iLoCodeCheckBox));

        }


        return  result;
    }

    // getting data from EvidenceChild table
    public String getEvidenceChildData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_EvidenceCODE};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_EVIDENCECHILD, columns, null, null, null, null, null);

        String result = "";
        int iChildId = c.getColumnIndex(KEY_NAME_CHILDID);
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getInt(iChildId)  + " " + c.getInt(iEvidenceCode) + "\n";
        }

        return  result;
    }

    // getting data from LOCode table
    public String getLOCodeData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_LOCODE,KEY_NAME_LOCDESCRIPTION};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_LOCODE, columns, null, null, null, null, null);

        String result = "";
        int iLOCode = c.getColumnIndex(KEY_NAME_LOCODE);
        int iLOCDescription = c.getColumnIndex(KEY_NAME_LOCDESCRIPTION);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getDouble(iLOCode)  + " " + c.getInt(iLOCDescription) + "\n";
        }

        return  result;
    }

    // getting data from loutcome table
    public String getLOutcomeData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_LOUTCOMECODE,KEY_NAME_LOUTCOMEEvidence};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_LOUTCOME, columns, null, null, null, null, null);

        String result = "";
        int iLOUTCOMECode = c.getColumnIndex(KEY_NAME_LOUTCOMECODE);
        int iLOUTCOMEEvidence = c.getColumnIndex(KEY_NAME_LOUTCOMEEvidence);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getDouble(iLOUTCOMECode)  + " " + c.getString(iLOUTCOMEEvidence) + "\n";
        }

        return  result;
    }

    // getting data from EvidenceLOUTCOME table
    public String getEvidenceLOutcomeData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_EvidenceCODE,KEY_NAME_LOUTCOMECODE};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_LOUTCOME, columns, null, null, null, null, null);

        String result = "";

        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);
        int iLOutComeCode = c.getColumnIndex(KEY_NAME_LOUTCOMECODE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getInt(iEvidenceCode)  + " " + c.getDouble(iLOutComeCode) + "\n";
        }
        //Log.d(TAG, result);
        return  result;
    }

    // getting data from Evidence table
    public ArrayList<String> getEvidenceInfo(int groupID)
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_EvidenceCODE,KEY_NAME_EvidenceDATE,KEY_NAME_EvidenceCOMMENT,KEY_NAME_GROUPID,KEY_NAME_ACTIVITYNAME,KEY_NAME_PHOTOFILENAME, KEY_NAME_COMPLETIONSTATUS,KEY_NAME_CHILDCHECKBOX,KEY_NAME_LOCODECHECKBOX};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_EVIDENCE, columns, KEY_NAME_GROUPID + "=" + groupID, null, null, null, null);

        ArrayList<String> result = new ArrayList<String>();
        //String result = "";
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);
        int iEvidenceDate = c.getColumnIndex(KEY_NAME_EvidenceDATE);
        int iEvidenceComment = c.getColumnIndex(KEY_NAME_EvidenceCOMMENT);
        int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);
        int iPhotoName = c.getColumnIndex(KEY_NAME_PHOTOFILENAME);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result.add( c.getString(iEvidenceCode) + "|" + c.getString(iEvidenceDate) + "," + c.getString(iActivityName) + ":" + c.getString(iPhotoName));
        }
        return  result;
    }

    //to return only first and surname
    public ArrayList<String> getChildNames(int groupID)
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_CHILDFIRSTNAME,KEY_NAME_CHILDSURNAME,KEY_NAME_CHILDGENDER,KEY_NAME_GROUPID};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_CHILD, columns, KEY_NAME_GROUPID + "=" + groupID, null, null, null, null);

        ArrayList<String> result = new ArrayList<String>();

        int iChildRowID = c.getColumnIndex(KEY_NAME_CHILDID);
        int iChildFirstName = c.getColumnIndex(KEY_NAME_CHILDFIRSTNAME);
        int iChildSurName = c.getColumnIndex(KEY_NAME_CHILDSURNAME);
        int iChildGender = c.getColumnIndex(KEY_NAME_CHILDGENDER);
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result.add(c.getString(iChildFirstName) + "," + c.getString(iChildSurName));
        }

        return  result;
    }

    public String getChildIDByName(String fullName)
    {
        String firstName = fullName.substring(0, fullName.indexOf(" "));
        String lastName = fullName.substring(fullName.indexOf(" ")+1 , fullName.length());

        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_CHILDFIRSTNAME,KEY_NAME_CHILDSURNAME,KEY_NAME_CHILDGENDER,KEY_NAME_GROUPID};
        // Creating a cursor to iterate through db
        final String query = "SELECT childID, childFirstName From Child where Child.childFirstName=\""+firstName+"\" AND Child.childSurName=\""+lastName+"\";\n" +"\n";
        Cursor c = _db.rawQuery(query, null);
        String result = "";

        int iChildRowID = c.getColumnIndex(KEY_NAME_CHILDID);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result= c.getString(iChildRowID);
        }
        Log.d(TAG, "Fahad is the boss, heres the ID biaaatch ---> " + result);
        return  result;
    }

    public ArrayList<String> getAllChildNames(int groupID)
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_CHILDFIRSTNAME,KEY_NAME_CHILDSURNAME,KEY_NAME_CHILDGENDER,KEY_NAME_GROUPID};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_CHILD, columns, KEY_NAME_GROUPID + "=" + groupID, null, null, null, null);

        ArrayList<String> result = new ArrayList<String>();

        int iChildRowID = c.getColumnIndex(KEY_NAME_CHILDID);
        int iChildFirstName = c.getColumnIndex(KEY_NAME_CHILDFIRSTNAME);
        int iChildSurName = c.getColumnIndex(KEY_NAME_CHILDSURNAME);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result.add(c.getString(iChildFirstName) + " " + c.getString(iChildSurName));
        }

        return  result;
    }

    public ArrayList<String> getActivityNames()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_ACTIVITYNAME,KEY_NAME_ACTIVITYTYPE,KEY_NAME_ACTIVITYDESCRIPTION};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_ACTIVITY, columns, null, null, null, null, null);
        ArrayList<String> result = new ArrayList<String>();

        int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);
        int iActivityType = c.getColumnIndex(KEY_NAME_CHILDFIRSTNAME);
        int iActivityDescription = c.getColumnIndex(KEY_NAME_ACTIVITYDESCRIPTION);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result.add(c.getString(iActivityName));
        }

        return  result;
    }

    public ArrayList<Double> getLOCode()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_LOCODE,KEY_NAME_LOCDESCRIPTION};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_LOCODE, columns, null, null, null, null, null);
        ArrayList<Double> result = new ArrayList<Double>();

        int iLOCode = c.getColumnIndex(KEY_NAME_LOCODE);
        int iLOCDescription = c.getColumnIndex(KEY_NAME_LOCDESCRIPTION);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result.add(c.getDouble(iLOCode));
        }

        return  result;
    }

    public ArrayList<String> getEvidenceByChild(String firstName, String lastName) {

        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_EvidenceCODE};
        // Creating a cursor to iterate through db
        final String query = "SELECT Child.childFirstName, Child.ChildSurName, Child.childID, EvidenceChild.EvidenceCode FROM Child INNER JOIN EvidenceChild where Child.childID=EvidenceChild.childID AND Child.childFirstName=\""+firstName+"\" AND Child.childSurName=\""+lastName+"\";\n" +"\n";
        Cursor c = _db.rawQuery(query, null);
        //Cursor c = _db.query(DATABASE_TABLE_ACTIVITY, columns, null, null, null, null, null);
        ArrayList<String> result = new ArrayList<String>();

        int iChildCode = c.getColumnIndex(KEY_NAME_CHILDID);
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result.add(c.getString(iEvidenceCode));
        }

        Log.d(TAG, result.toString());
        return  result;
    }

    public ArrayList<String> getEvidenceByActivity(int groupID, String activity) {

        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_EvidenceCODE, KEY_NAME_ACTIVITYNAME};
        // Creating a cursor to iterate through db
        final String query = "SELECT EvidenceCode, ActivityName From Evidence WHERE ActivityName=\""+ activity +"\" AND groupID="+ groupID +";\n" +"\n";
        Cursor c = _db.rawQuery(query, null);
        //Cursor c = _db.query(DATABASE_TABLE_ACTIVITY, columns, null, null, null, null, null);
        ArrayList<String> result = new ArrayList<String>();

        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result.add(c.getString(iEvidenceCode));
        }

        Log.d(TAG, result.toString());
        return  result;
    }

    public ArrayList<String> getEvidenceByLoCode(Double loCode) {

        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_EvidenceCODE, KEY_NAME_LOUTCOMECODE};
        // Creating a cursor to iterate through db
        final String query = "SELECT EvidenceCode, loutcomeCode From EvidenceLOutcome WHERE loutcomeCode=\""+ loCode +"\";\n" +"\n";
        Cursor c = _db.rawQuery(query, null);
        //Cursor c = _db.query(DATABASE_TABLE_ACTIVITY, columns, null, null, null, null, null);
        ArrayList<String> result = new ArrayList<String>();

        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()) {

            result.add(c.getString(iEvidenceCode));
        }

        Log.d(TAG, result.toString());
        return  result;


    }

    public ArrayList<String> getEvidenceByID(String evidenceCode) {

        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_EvidenceCODE,KEY_NAME_EvidenceDATE,KEY_NAME_EvidenceCOMMENT,KEY_NAME_GROUPID,KEY_NAME_ACTIVITYNAME,KEY_NAME_PHOTOFILENAME, KEY_NAME_COMPLETIONSTATUS,KEY_NAME_CHILDCHECKBOX,KEY_NAME_LOCODECHECKBOX};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_EVIDENCE,columns,KEY_NAME_EvidenceCODE + "=" + evidenceCode,null,null,null,null);

        ArrayList<String> result = new ArrayList<String>();
        //String result = "";
        try {
            int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);
            int iEvidenceDate = c.getColumnIndex(KEY_NAME_EvidenceDATE);
            int iEvidenceComment = c.getColumnIndex(KEY_NAME_EvidenceCOMMENT);
            int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);
            int iPhotoName = c.getColumnIndex(KEY_NAME_PHOTOFILENAME);

            for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
            {
                result.add( c.getString(iEvidenceCode) + "|" + c.getString(iEvidenceDate) + "," + c.getString(iActivityName) + ":" + c.getString(iPhotoName) );
            }
        } finally {
            c.close();
        }
        return  result;
    }

    //deletes evidence data from all associated tables
    public boolean deleteEvidenceByID(String evidID)
    {
        Boolean a = _db.delete(DATABASE_TABLE_EVIDENCE, KEY_NAME_EvidenceCODE + "=" + evidID, null) > 0;
        Boolean b =_db.delete(DATABASE_TABLE_EVIDENCECHILD, KEY_NAME_EvidenceCODE + "=" + evidID, null) > 0;
        Boolean c =_db.delete(DATABASE_TABLE_EVIDENCELOUTCOME, KEY_NAME_EvidenceCODE + "=" + evidID, null) > 0;

        Boolean result;
        if (a && b && c){
            result = true;
        } else result = false;

        return result;
    }


    //for testing
    public void dropAll(SQLiteDatabase db) {
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_CHILD);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_ACTIVITY);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_GROUP);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_EVIDENCELOUTCOME);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_EVIDENCE);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_LOUTCOME);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_EVIDENCECHILD);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_LOCODE);
        db.execSQL(KEY_DROP_TABLE + DATABASE_TABLE_PHOTO);
    }

}
