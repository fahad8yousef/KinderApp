package au.edu.swin.csk.KinderApp;

import java.util.List;

/**
 * Created by Chandima on 17/09/2015.
 * This class is to read through a CSV file and grab the data and will insert into the database
 */


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.ArrayList;
        import java.util.List;



public class CSVReader {
    InputStream inputStream;
    KinderDBCon db;

    // give the file path as the inputstream and the database instance to continue. Good to call this class in the KinderDBCon onCreate method
    public CSVReader(InputStream is,KinderDBCon _db) {

        this.inputStream = is;
        //Should pass the context
        db = _db;
    }

    public List<String[]> read() {
        List<String[]> resultList = new ArrayList<String[]>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",");
                resultList.add(row);
            }
            checkFile(resultList);
        } catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file:" + ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: " + e);
            }
        }
        return resultList;
    }

    private void checkFile(List<String[]> resultList) {
        // check the csv file name to populate data
        if(!(resultList.get(0)[0].isEmpty()))
        {
            // the file is not empty
            if((resultList.get(0)[0]).toUpperCase().contentEquals("ACTIVITY"))
            {
                addToActivity(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("CHILDREN"))
            {
                addToChildren(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("EVIDENCE"))
            {
                addToEvidence(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("GROUP"))
            {
                addToEvidence(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("EVIDENCECHILD"))
            {
                addToEvidenceChild(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("PHOTO"))
            {
                addToPhoto(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("LO CODE"))
            {
                addToEvidence(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("LOUTCOME"))
            {
                addToLOutCome(resultList);
            }else if((resultList.get(0)[0]).toUpperCase().contentEquals("EVIDENCELOUTCOME"))
            {
                addToEvidenceLOutcome(resultList);
            }
        }
    }

    private void addToEvidenceLOutcome(List<String[]> csvLine) {


        for(int i=1;i < csvLine.size() ;i++)
        {
            //db.InsertIntoEvidenceLOutcomeTable(Integer.valueOf(csvLine.get(i)[0]),Double.valueOf(csvLine.get(i)[1]));
        }
    }

    private void addToLOutCome(List<String[]> csvLine) {
        for(int i=1;i < csvLine.size() ;i++)
        {
            //db.InsertIntoLOUTCOMETable(Double.valueOf(csvLine.get(i)[0]),csvLine.get(i)[1]);
        }
    }

    private void addToPhoto(List<String[]> csvLine) {
        for(int i=1;i < csvLine.size() ;i++)
        {

        }
    }

    private void addToEvidenceChild(List<String[]> csvLine) {
        for(int i=1;i < csvLine.size() ;i++)
        {
            db.InsertIntoEvidenceChildTable(Integer.valueOf(csvLine.get(i)[0]),Integer.valueOf(csvLine.get(i)[1]));
        }
    }

    private void addToEvidence(List<String[]> csvLine) {
        for(int i=1;i < csvLine.size() ;i++)
        {
            //db.InsertIntoEvidenceTable(Integer.valueOf(csvLine.get(i)[0]),csvLine.get(i)[1],csvLine.get(i)[2],Integer.valueOf(csvLine.get(i)[3]),csvLine.get(i)[4]);
        }
    }

    private void addToChildren(List<String[]> csvLine) {
        for(int i=1;i < csvLine.size() ;i++)
        {
            //db.InsertIntoChildTable(Integer.valueOf(csvLine.get(i)[0]),csvLine.get(i)[1],csvLine.get(i)[2],csvLine.get(i)[3],Integer.valueOf(csvLine.get(i)[4]));
        }
    }

    private void addToActivity(List<String[]> csvLine) {
        for(int i=1;i < csvLine.size() ;i++)
        {

        }

    }
}