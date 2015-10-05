package au.edu.swin.csk.KinderApp;

/**
 * Created by Subzero & Fahad Alhamed on 14/09/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String KEY_NAME_LOCODE = "locCode";
    public static final String KEY_NAME_LOCDESCRIPTION = "locDescription";
    public static final String KEY_NAME_LOUTCOMECODE = "loutcomeCode";
    public static final String KEY_NAME_LOUTCOMEEvidence = "loutcomeEvidence";

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
                            KEY_NAME_EvidenceDATE + KEY_INTEGER + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_COMMA + KEY_NAME_EvidenceDATE + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "EVIDENCECHILD_FK_GROPID" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_CHILD + KEY_OPEN_PARENTHESIS + KEY_NAME_CHILDID + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "EVIDENCECHILD_FK_ACTIVITYNAME" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceDATE + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_EVIDENCE + KEY_OPEN_PARENTHESIS + KEY_NAME_EvidenceDATE + KEY_CLOSE_PARENTHESIS +
                            KEY_CLOSE_PARENTHESIS + KEY_SEMI_COLON
            );
            db.execSQL(
                    // Creating  EvidenceChild Table
                    KEY_CREATE_TABLE + DATABASE_TABLE_PHOTO + KEY_OPEN_PARENTHESIS +
                            KEY_NAME_PHOTOFILENAME + KEY_TEXT + KEY_COMMA +
                            KEY_NAME_EvidenceCODE + KEY_INTEGER + KEY_COMMA +
                            KEY_PRIMARY_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_PHOTOFILENAME + KEY_CLOSE_PARENTHESIS + KEY_COMMA +
                            KEY_CONSTRAINT + "PHOTO_FK_EvidenceCODE" + KEY_FOREIGN_KEY + KEY_OPEN_PARENTHESIS + KEY_NAME_PHOTOFILENAME + KEY_CLOSE_PARENTHESIS +
                            KEY_REFERENCES + DATABASE_TABLE_EVIDENCE + KEY_OPEN_PARENTHESIS + KEY_NAME_PHOTOFILENAME + KEY_CLOSE_PARENTHESIS +
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

    public long InsertIntoGroupTable(int _groupID,String _name)
    {
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAME_GROUPID,_groupID);
        cv.put(KEY_NAME_GROUPNAME, _name);


        return _db.insert(DATABASE_TABLE_GROUP,null,cv);
    }

    public long InsertIntoChildTable(int _childID,String _childFirstName,String _childSurName,String _childGender,int _groupID)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_CHILDID,_childID);
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


    public long InsertIntoEvidenceTable(int _EvidenceCode,String _EvidenceDate,String _EvidenceComment,int _groupID,String _activityName)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_EvidenceCODE,_EvidenceCode);
        cv.put(KEY_NAME_EvidenceDATE, _EvidenceDate);
        cv.put(KEY_NAME_EvidenceCOMMENT, _EvidenceComment);
        cv.put(KEY_NAME_GROUPID,_groupID);
        cv.put(KEY_NAME_ACTIVITYNAME, _activityName);

        return _db.insert(DATABASE_TABLE_EVIDENCE,null,cv);
    }

    public long InsertIntoEvidenceChildTable(int _childID,int _EvidenceCode)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_CHILDID,_childID);
        cv.put(KEY_NAME_EvidenceCODE,_EvidenceCode);

        return _db.insert(DATABASE_TABLE_EVIDENCECHILD,null,cv);
    }

    public long InsertIntoPhotoTable(String _photoFilename,int _EvidenceCode)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_PHOTOFILENAME,_photoFilename);
        cv.put(KEY_NAME_EvidenceCODE,_EvidenceCode);

        return _db.insert(DATABASE_TABLE_PHOTO,null,cv);
    }

    public long InsertIntoLOCodeTable(double _locCode,String _locDescription)
    {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME_LOCODE,_locCode);
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
    public String getEvidenceData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_EvidenceCODE,KEY_NAME_EvidenceDATE,KEY_NAME_EvidenceCOMMENT,KEY_NAME_GROUPID,KEY_NAME_ACTIVITYNAME};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_EVIDENCE,columns,null,null,null,null,null);

        String result = "";
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);
        int iEvidenceDate = c.getColumnIndex(KEY_NAME_CHILDFIRSTNAME);
        int iEvidenceComment = c.getColumnIndex(KEY_NAME_ACTIVITYDESCRIPTION);
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);
        int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getInt(iEvidenceCode)  + " " + c.getString(iEvidenceDate) + " " + c.getString(iEvidenceComment) + " " + c.getInt(iGroupRowID)  + " " + c.getString(iActivityName) + "\n";
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

    // getting data from Photo table
    public String getPhotoData()
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_PHOTOFILENAME,KEY_NAME_EvidenceCODE};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_PHOTO, columns, null, null, null, null, null);

        String result = "";
        int iPhotoFIleName = c.getColumnIndex(KEY_NAME_PHOTOFILENAME);
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result = result + c.getString(iPhotoFIleName)  + " " + c.getInt(iEvidenceCode) + "\n";
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
        String [] columns = new String[] {KEY_NAME_EvidenceCODE,KEY_NAME_EvidenceDATE,KEY_NAME_EvidenceCOMMENT,KEY_NAME_GROUPID,KEY_NAME_ACTIVITYNAME};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_EVIDENCE,columns,KEY_NAME_GROUPID + "=" + groupID,null,null,null,null);

        ArrayList<String> result = new ArrayList<String>();
        //String result = "";
        int iEvidenceCode = c.getColumnIndex(KEY_NAME_EvidenceCODE);
        int iEvidenceDate = c.getColumnIndex(KEY_NAME_EvidenceDATE);
        int iEvidenceComment = c.getColumnIndex(KEY_NAME_EvidenceCOMMENT);
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);
        int iActivityName = c.getColumnIndex(KEY_NAME_ACTIVITYNAME);

        for (c.moveToFirst();!c.isAfterLast();c.moveToNext())
        {
            result.add( c.getString(iEvidenceDate) + "," + c.getString(iActivityName) );
        }
        return  result;
    }

    //testing to returne only first and surname
    public ArrayList<String> getChildNames(int groupID)
    {
        // Creating a string array to store result from database before passing
        String [] columns = new String[] {KEY_NAME_CHILDID,KEY_NAME_CHILDFIRSTNAME,KEY_NAME_CHILDSURNAME,KEY_NAME_CHILDGENDER,KEY_NAME_GROUPID};
        // Creating a cursor to iterate through db
        Cursor c = _db.query(DATABASE_TABLE_CHILD,columns,KEY_NAME_GROUPID + "=" + groupID ,null,null,null,null);

        ArrayList<String> result = new ArrayList<String>();

        int iChildRowID = c.getColumnIndex(KEY_NAME_CHILDID);
        int iChildFirstName = c.getColumnIndex(KEY_NAME_CHILDFIRSTNAME);
        int iChildSurName = c.getColumnIndex(KEY_NAME_CHILDSURNAME);
        int iChildGender = c.getColumnIndex(KEY_NAME_CHILDGENDER);
        int iGroupRowID = c.getColumnIndex(KEY_NAME_GROUPID);

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
