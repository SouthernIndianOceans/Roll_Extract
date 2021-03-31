package com.example.rollextract;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private Spinner Subjectslist;
    private Spinner Designslist;
    private String cachepath;
    File myExternalFile0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Subjectslist = findViewById(R.id.spinner);
        Designslist = findViewById(R.id.spinner1);
        //String[] sList = new String[] {"Accounts Bcom 1st"};
        //String[] sList = new String[]{"Experimental", "FMS", "Enterpreneur", "Indian Economy", "M.A.", "Tax", "GC"};
        String[] sList = new String[]{"Cost Management", "Advanced Accounting", "Auditing & Secretarial Practices", "Security Analysis & Portfolio Management", "Any other subject (Extra Slot)"};
        String[] designs = new String[] {"indexA1.html","indexA2.html","indexA3.html","indexA4.html","indexA5.html","indexA6.html","indexA7.html","indexA8.html","indexA9.html","indexA9.html"};
        ArrayAdapter<String> subL = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, sList);
        ArrayAdapter<String> subD = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, designs);
        subL.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        subD.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        Subjectslist.setAdapter(subL);
        Designslist.setAdapter(subD);
        Subjectslist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> Parenview, View siv, int pos, long id) {}
            @Override
            public void onNothingSelected(AdapterView<?> Patentview) {}
        });
        Designslist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> Parenview, View siv, int pos, long id) {}
            @Override
            public void onNothingSelected(AdapterView<?> Patentview) {}
        });
    }
    public void onStatement(View v) {
        Statement();
    }
    /*public void onHTML(View v) {
        HTML();
    }*/
    public void DesignLTS(View v) {
        LTS();
    }
    /*void HTML() {
        try {
            String rollno;
            EditText block = findViewById(R.id.editTextTextMultiLine);
            Button thisbutton = findViewById(R.id.button1);
            int tempindex = block.getLineCount();
            String[] list = new String[tempindex];
            String fulltext =  block.getText().toString();
            String[] strarr = fulltext.split(System.getProperty("line.separator"));
            String sx;
            for (int ii = 0; ii < strarr.length; ii++) {
                sx = strarr[ii];
                list[ii] = sx;
            }
            String[] rns =  new String[tempindex];
            String[] even = new String[tempindex];
            String[] odd =  new String[tempindex];
            int[] evenextra = new int[tempindex];
            int index = 0;
            int evenindex = 0;
            int oddindex = 0;
            boolean already;
            for (String line : list)
            {
                already = false;
                if (line.contains("23") || line.contains("24")) // 23 & 24 for 2nd
                {
                    for (int i = 0; i < line.length(); i++)
                    {
                        if (line.charAt(i) == '2')
                        {
                            if (line.length() >= i + 4)
                            {
                                if (line.charAt(i + 1) == '3' || line.charAt(i + 1) == '4') // 3,4 for 2nd
                                {
                                    if (!already)
                                    {
                                        rollno = line.substring(i, i + 4);
                                        rns[index] = rollno + "   " + line;
                                        evenextra[index] = Integer.parseInt(rollno);
                                        if (Iseven(rollno))
                                        {
                                            even[evenindex] = rollno + "   " + line.toUpperCase();
                                            evenindex++;
                                        }
                                        else
                                        {
                                            odd[oddindex] = rollno + "   " + line.toUpperCase();
                                            oddindex++;
                                        }
                                        index++;
                                        already = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            ArrayList<String> templist1 = new ArrayList<>();
            for (String item : even)
            {
                if (item != null)
                {
                    templist1.add(item);
                }
            }
            even = templist1.toArray(new String[0]);
            ArrayList<String> templist = new ArrayList<>();
            for (String item : odd)
            {
                if (item != null)
                {
                    templist.add(item);
                }
            }
            odd = templist.toArray(new String[0]);
            String tmp;
            for (int i = 0; i < even.length; i++)
            {
                for (int j = i + 1; j < even.length; j++)
                {
                    if (Integer.parseInt(even[j].substring(0, 4)) < Integer.parseInt(even[i].substring(0, 4)))
                    {
                        tmp = even[i];
                        even[i] = even[j];
                        even[j] = tmp;
                    }
                }
            }
            for (int i = 0; i < odd.length; i++)
            {
                for (int j = i + 1; j < odd.length; j++)
                {
                    if (Integer.parseInt(odd[j].substring(0, 4)) < Integer.parseInt(odd[i].substring(0, 4)))
                    {
                        tmp = odd[i];
                        odd[i] = odd[j];
                        odd[j] = tmp;
                    }
                }
            }
            ArrayList<String> templist2 = new ArrayList<>();
            for (String item : rns)
            {
                if (item != null)
                {
                    templist2.add(item);
                }
            }
            rns = templist2.toArray(new String[0]);
            ArrayList<Integer> duplicateindexes = new ArrayList<>();
            int qq, ww;
            for (int q = 0; q < even.length; q++) {
                qq = Integer.parseInt(even[q].substring(0,4));
                for (int w = 0; w < even.length; w++) {
                    ww = Integer.parseInt(even[w].substring(0,4));
                    if (qq == ww) {
                        if(q != w) {
                            duplicateindexes.add(w);
                        }
                    }
                }
            }
            ArrayList<Integer> duplicateindexesodd = new ArrayList<>();
            for (int q = 0; q < odd.length; q++) {
                qq = Integer.parseInt(odd[q].substring(0,4));
                for (int w = 0; w < odd.length; w++) {
                    ww = Integer.parseInt(odd[w].substring(0,4));
                    if (qq == ww) {
                        if(q != w) {
                            duplicateindexesodd.add(w);
                        }
                    }
                }
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("index.html")));
            String mline;
            int cindex = 0;
            String[] fullformat = new String[113];
            while ((mline = br.readLine()) != null) {
                fullformat[cindex] = mline;
                cindex++;
            }
            br.close();
            StringBuilder evenbuilder = new StringBuilder();
            int currenteveninderx = 1;
            for (String itemXY : even)
            {
                if (duplicateindexes.contains(currenteveninderx-1)) {
                    evenbuilder.append("<tr style=\"background-color:red; color:white\">").append("\n");
                }
                else {
                    evenbuilder.append("<tr>").append("\n");
                }
                evenbuilder.append("<th>" + currenteveninderx + ")</th>").append("\n");
                evenbuilder.append("<th>" + itemXY.substring(0,4)+ "</th>").append("\n");
                evenbuilder.append("<th>" + itemXY.substring(7)+ "</th>").append("\n");
                evenbuilder.append("</tr>").append("\n");
                currenteveninderx++;
            }
            currenteveninderx = 1;
            StringBuilder oddsbuilder = new StringBuilder();
            for (String itemXYZ : odd)
            {
                if (duplicateindexesodd.contains(currenteveninderx-1)) {
                    oddsbuilder.append("<tr style=\"background-color:red; color:white\">").append("\n");
                }
                else {
                    oddsbuilder.append("<tr>").append("\n");
                }
                oddsbuilder.append("<th>" + currenteveninderx + ")</th>").append("\n");
                oddsbuilder.append("<th>" + itemXYZ.substring(0,4)+ "</th>").append("\n");
                oddsbuilder.append("<th>" + itemXYZ.substring(7)+ "</th>").append("\n");
                oddsbuilder.append("</tr>").append("\n");
                currenteveninderx++;
            }
            int fullattend = rns.length;
            double percent = (even.length*100) / fullattend;
            double percent2 = 100 - percent;
            String evenpercent = String.valueOf(percent);
            String oddpercent = String.valueOf(percent2);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter dtx = DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' hh.mm.ss a");
            LocalDateTime now = LocalDateTime.now();
            fullformat[25] = dtf.format(now) + "</a></div>";
            fullformat[51] = evenbuilder.toString();
            fullformat[66] = oddsbuilder.toString();
            fullformat[83] = "Total Attendence : " + fullattend;
            fullformat[92] = "style=\"width: " + evenpercent + "%;\">";
            fullformat[96] = oddpercent;
            fullformat[99] = evenpercent;
            //fullformat[27] = dtf.format(now);
            //fullformat[53] = evenbuilder.toString();
            //fullformat[72] = oddsbuilder.toString();
            //fullformat[89] = fullattend + " (+ duplicates)";
            //fullformat[96] = evenpercent + "%;\">";
            //fullformat[100] = oddpercent;
            //fullformat[103] = evenpercent;
            Spinner x = findViewById(R.id.spinner);
            String selectivepath = x.getSelectedItem().toString();
            String recordpath = "Records"+'/'+selectivepath;
            File Sheet = new File(getExternalFilesDir(recordpath), dtx.format(now) + "_Sheet.html");
            FileOutputStream fos11 = new FileOutputStream(Sheet);
            String Dy;
            for (int m = 0; m < fullformat.length; m++) {
                Dy = fullformat[m] + "\n";
                fos11.write(Dy.getBytes());
            }
            fos11.close();
            cachepath = "Caches"+'/'+selectivepath;
            myExternalFile0 = new File(getExternalFilesDir(cachepath), "Cache.L1.txt");
            FileWriter frx = new FileWriter(myExternalFile0, true);
            BufferedWriter bw = new BufferedWriter(frx);
            StringBuilder fullLine = new StringBuilder();
            for (String line:even) {
                fullLine.append(line.substring(0,4)+';');
            }
            for (String line:odd) {
                fullLine.append(line.substring(0,4)+';');
            }
            bw.write(dtx.format(now) + ';' + fullLine);
            bw.newLine();
            bw.close();
            frx.close();
            thisbutton.setText("Done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
    public  void SpecialX(View v) {
        Special();
    }
    void Statement() {
        try {
            Button thisbutton = findViewById(R.id.button2);
            Spinner x = findViewById(R.id.spinner);
            cachepath = "Caches"+'/'+x.getSelectedItem().toString();
            File myCacheFile = new File(getExternalFilesDir(cachepath), "Cache.L1.txt");
            FileReader frx1 = new FileReader(myCacheFile);
            BufferedReader brx1 = new BufferedReader(frx1);
            String line;
            String[] lines = new String[(int)brx1.lines().count()];
            brx1.close();
            frx1.close();
            int linesindex = 0;
            FileReader frx = new FileReader(myCacheFile);
            BufferedReader brx = new BufferedReader(frx);
            while ((line = brx.readLine()) != null) {
                lines[linesindex] = line;
                linesindex++;
            }
            brx.close();
            frx.close();
            ArrayList<Integer> Rollnumbers = new ArrayList<>();
            ArrayList<String> Datesandtimes = new ArrayList<>();
            for (String currentline : lines) {
                if (currentline != null) {
                    Datesandtimes.add(ExtractDate(currentline));
                    Integer[] rollslist = ExtractRollnumbers(currentline);
                    for (int num : rollslist) {
                        if (!Rollnumbers.contains(num)) {
                            Rollnumbers.add(num);
                        }
                    }
                }
            }
            int tmp;
            for (int i = 0; i < Rollnumbers.size(); i++)
            {
                for (int j = i + 1; j < Rollnumbers.size(); j++)
                {
                    if (Rollnumbers.get(j) < Rollnumbers.get(i))
                    {
                        tmp = Rollnumbers.get(i);
                        Rollnumbers.set(i,Rollnumbers.get(j));
                        Rollnumbers.set(j,tmp);
                    }
                }
            }
            StringBuilder finalexport = new StringBuilder();
            StringBuilder exportx = new StringBuilder();
            exportx.append("<tr>").append("\n");
            exportx.append("<th>Roll Numbers</th>").append("\n");
            for (String currentdate : Datesandtimes) {
                exportx.append("<th>").append(currentdate).append("</th>").append("\n");
            }
            exportx.append("<th>Attendances</th>").append("\n");
            exportx.append("<th>Overall</th>").append("\n");
            exportx.append("</tr>").append("\n");
            finalexport.append(exportx.toString()).append("\n");
            int averagecount = 0;
            for (int rollnum : Rollnumbers) {
                int attendenccount = 0, totalcount =  Datesandtimes.size();
                StringBuilder cv = new StringBuilder();
                cv.append("<tr>").append("\n");
                cv.append("<th>").append(rollnum).append("</th>").append("\n");
                for (String myLine : lines) {
                    if (myLine.contains(String.valueOf(rollnum))) {
                        cv.append("<th style=\"background-color:green; color:white\">Present</th>").append("\n");
                        attendenccount++;
                    }
                    else {
                        cv.append("<th style=\"background-color:red; color:white\">Absent</th>").append("\n");
                    }
                }
                int percentScore = (attendenccount * 100) / totalcount;
                cv.append("<th>").append(attendenccount).append(" / ").append(totalcount).append(" (").append(percentScore).append("%)</th>").append("\n");
                if (percentScore == 100) {
                    cv.append("<th style=\"background-color:green; color:white\">Perfect</th>").append("\n");
                }
                if (percentScore > 59 && percentScore < 100) {
                    cv.append("<th style=\"background-color:orange; color:white\">Good</th>").append("\n");
                }
                if (percentScore > 33 && percentScore < 60) {
                    cv.append("<th style=\"background-color:yellow; color:black\">Average</th>").append("\n");
                }
                if (percentScore > 0 && percentScore < 34) {
                    cv.append("<th style=\"background-color:red; color:white\">Poor</th>").append("\n");
                }
                if (percentScore == 0) {
                    cv.append("<th>Zero</th>").append("\n");
                }
                cv.append("</tr>").append("\n");
                averagecount += attendenccount;
                finalexport.append(cv.toString());
            }
            int average = averagecount / Datesandtimes.size();
            finalexport.append("<h2>Average of ").append(Datesandtimes.size()).append(" days = ").append(average).append("</h2>").append("\n");
            BufferedReader brq = new BufferedReader(new InputStreamReader(getAssets().open("Sheet.html")));
            String mline;
            int cindex = 0;
            String[] fullformat = new String[23];
            while ((mline = brq.readLine()) != null) {
                fullformat[cindex] = mline;
                cindex++;
            }
            brq.close();
            fullformat[19] = finalexport.toString();
            File SheetS = new File(getExternalFilesDir(cachepath),  "Summary.html");
            FileOutputStream fos11 = new FileOutputStream(SheetS);
            String Dy;
            for (String s : fullformat) {
                Dy = s + "\n";
                fos11.write(Dy.getBytes());
            }
            fos11.close();
            thisbutton.setText("Done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void LTS() {
        try {
            EditText block = findViewById(R.id.editTextTextMultiLine);
            Button thisbutton = findViewById(R.id.button);
            int tempindex = block.getLineCount();
            String[] list = new String[tempindex];
            String fulltext =  block.getText().toString();
            String[] strarr = fulltext.split(Objects.requireNonNull(System.getProperty("line.separator")));
            String sx;
            for (int ii = 0; ii < strarr.length; ii++) {
                sx = strarr[ii];
                list[ii] = sx;
            }
            String rollno;
            ArrayList<String> even = new ArrayList<>();
            ArrayList<String> odd =  new ArrayList<>();
            String seriesA = "23";
            String seriesB = "24";
            String seriesC = "24";
            boolean already;
            for (String line : list)
            {
                already = false;
                if (line != null)
                {
                    boolean b = line.contains(seriesA) || line.contains(seriesB);
                    if (!Islogtext(line))
                    {
                        if (b) // 23 & 24 for 2nd
                        {
                            for (int i = 0; i < line.length(); i++)
                            {
                                if (line.charAt(i) == seriesA.charAt(0) || line.charAt(i) == seriesB.charAt(0) || line.charAt(i) == seriesC.charAt(0))
                                {
                                    if (line.length() >= i + 4)
                                    {
                                        if (line.charAt(i + 1) == seriesA.charAt(1) || line.charAt(i + 1) == seriesB.charAt(1) || line.charAt(i + 1) == seriesC.charAt(1)) // 3,4 for 2nd
                                        {
                                            if (IsNumeric(line.substring(i, i + 4)))
                                            {
                                                if (!already)
                                                {
                                                    rollno = line.substring(i, i + 4);
                                                    if (Iseven(rollno))
                                                    {
                                                        even.add(rollno + "   " + line.toUpperCase());
                                                    }
                                                    else
                                                    {
                                                        odd.add(rollno + "   " + line.toUpperCase());
                                                    }
                                                    already = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (!line.contains(" (privately):")) {
                            int getstindex = line.lastIndexOf(" to everyone:")+25;
                            String mynewline = line.substring(getstindex);
                            if (b) // 23 & 24 for 2nd
                            {
                                for (int i = 0; i < mynewline.length(); i++)
                                {
                                    if (mynewline.charAt(i) == seriesA.charAt(0) || mynewline.charAt(i) == seriesB.charAt(0) || mynewline.charAt(i) == seriesC.charAt(0))
                                    {
                                        if (mynewline.length() >= i + 4)
                                        {
                                            if (mynewline.charAt(i + 1) == seriesA.charAt(1) || mynewline.charAt(i + 1) == seriesB.charAt(1) || mynewline.charAt(i + 1) == seriesC.charAt(1)) // 3,4 for 2nd
                                            {
                                                if (IsNumeric(mynewline.substring(i, i + 4)))
                                                {
                                                    if (!already)
                                                    {
                                                        rollno = mynewline.substring(i, i + 4);
                                                        if (Iseven(rollno))
                                                        {
                                                            even.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        else
                                                        {
                                                            odd.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        already = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            int getstindex = line.lastIndexOf(" (privately):") + 25;
                            String mynewline = line.substring(getstindex);
                            if (b) // 23 & 24 for 2nd
                            {
                                for (int i = 0; i < mynewline.length(); i++)
                                {
                                    if (mynewline.charAt(i) == seriesA.charAt(0) || mynewline.charAt(i) == seriesB.charAt(0) || mynewline.charAt(i) == seriesC.charAt(0))
                                    {
                                        if (mynewline.length() >= i + 4)
                                        {
                                            if (mynewline.charAt(i + 1) == seriesA.charAt(1) || mynewline.charAt(i + 1) == seriesB.charAt(1) || mynewline.charAt(i + 1) == seriesC.charAt(1)) // 3,4 for 2nd
                                            {
                                                if (IsNumeric(mynewline.substring(i, i + 4)))
                                                {
                                                    if (!already)
                                                    {
                                                        rollno = mynewline.substring(i, i + 4);
                                                        if (Iseven(rollno))
                                                        {
                                                            even.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        else
                                                        {
                                                            odd.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        already = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            String tmp;
            for (int i = 0; i < even.size(); i++)
            {
                for (int j = i + 1; j < even.size(); j++)
                {
                    if (Integer.parseInt(even.get(j).substring(0, 4)) < Integer.parseInt(even.get(i).substring(0, 4)))
                    {
                        tmp = even.get(i);
                        even.set(i,even.get(j));
                        even.set(j,tmp);
                    }
                }
            }
            for (int i = 0; i < odd.size(); i++)
            {
                for (int j = i + 1; j < odd.size(); j++)
                {
                    if (Integer.parseInt(odd.get(j).substring(0, 4)) < Integer.parseInt(odd.get(i).substring(0, 4)))
                    {
                        tmp = odd.get(i);
                        odd.set(i,odd.get(j));
                        odd.set(j,tmp);
                    }
                }
            }
            ArrayList<String> Rollnumberseven = new ArrayList<>();
            ArrayList<String> XXXX1 = new ArrayList<>();
            for (String currentline : even) {
                String num = currentline.substring(0,4);
                String kuta = currentline.substring(7);
                if (!Rollnumberseven.contains(num)) {
                    Rollnumberseven.add(num);
                    XXXX1.add(ExtractName(kuta));
                }
            }
            ArrayList<String> Rollnumberodds = new ArrayList<>();
            ArrayList<String> XXXX2 = new ArrayList<>();
            for (String currentlinex : odd) {
                String num1 = currentlinex.substring(0,4);
                String kuta = currentlinex.substring(7);
                if (!Rollnumberodds.contains(num1)) {
                    Rollnumberodds.add(num1);
                    XXXX2.add(ExtractName(kuta));
                }
            }
            DateTimeFormatter dtg = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            DateTimeFormatter dtx = DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' hh.mm.ss a");
            LocalDateTime now = LocalDateTime.now();
            String selectivepath = Subjectslist.getSelectedItem().toString();
            cachepath = "Caches"+'/'+selectivepath;
            myExternalFile0 = new File(getExternalFilesDir(cachepath), "Cache.L1.txt");
            FileWriter frx = new FileWriter(myExternalFile0, true);
            BufferedWriter bw = new BufferedWriter(frx);
            StringBuilder fullLine = new StringBuilder();
            for (String line:even) {
                fullLine.append(line.substring(0, 4)).append(';');
            }
            for (String line:odd) {
                fullLine.append(line.substring(0, 4)).append(';');
            }
            bw.write(dtx.format(now) + ';' + fullLine);
            bw.newLine();
            bw.close();
            frx.close();
            File designfile = new File(getExternalFilesDir(cachepath), "Cache.L2.txt");
            int getindex;
            String gx;
            if (!designfile.exists()) {
                getindex = 0;
            }
            else {
                FileReader fr3 = new FileReader(designfile);
                BufferedReader br3 = new BufferedReader(fr3);
                String entry = br3.readLine();
                br3.close();
                fr3.close();
                getindex = Integer.parseInt(entry);
                if (getindex > 8) {
                    getindex = 0;
                }
                else {
                    getindex++;
                }
            }
            gx = getindex + "";
            FileWriter drw = new FileWriter(designfile);
            BufferedWriter dbw = new BufferedWriter(drw);
            dbw.write(gx);
            dbw.close();
            drw.close();
            Designslist.setSelection(getindex);
            String Designpath = Designslist.getSelectedItem().toString();
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open(Designpath)));
            String mline;
            int cindex = 0;
            String[] fullformat = new String[543];
            while ((mline = br.readLine()) != null) {
                fullformat[cindex] = mline;
                cindex++;
            }
            br.close();
            StringBuilder evenbuilder = new StringBuilder();
            int currenteveninderx = 1;
            for (String itemXY : Rollnumberseven)
            {
                int tempformatch = Rollnumberseven.indexOf(itemXY);
                evenbuilder.append("<tr>").append("\n");
                evenbuilder.append("<th>").append(currenteveninderx).append(")</th>").append("\n");
                evenbuilder.append("<th>").append(itemXY).append("</th>").append("\n");
                evenbuilder.append("<th>").append(XXXX1.get(tempformatch)).append("</th>").append("\n");
                evenbuilder.append("</tr>").append("\n");
                currenteveninderx++;
            }
            currenteveninderx = 1;
            StringBuilder oddsbuilder = new StringBuilder();
            for (String itemXYZ : Rollnumberodds)
            {
                int tempformatch = Rollnumberodds.indexOf(itemXYZ);
                oddsbuilder.append("<tr>").append("\n");
                oddsbuilder.append("<th>").append(currenteveninderx).append(")</th>").append("\n");
                oddsbuilder.append("<th>").append(itemXYZ).append("</th>").append("\n");
                oddsbuilder.append("<th>").append(XXXX2.get(tempformatch)).append("</th>").append("\n");
                oddsbuilder.append("</tr>").append("\n");
                currenteveninderx++;
            }
            int fullattend = Rollnumberseven.size() + Rollnumberodds.size();
            int totalper = (fullattend * 100) / 140;
            int evenonpt = (Rollnumberseven.size()*100) / 70;
            int oddsonpt = (Rollnumberodds.size()*100) / 70;
            fullformat[215] = "width: " + evenonpt + "%;";
            fullformat[219] = "width: " + oddsonpt + "%;";
            fullformat[242] = dtg.format(now);
            fullformat[268] = evenbuilder.toString();
            fullformat[283] = oddsbuilder.toString();
            fullformat[292] = fullattend + " / 140 ( " + totalper + " %)"; // 140 for bcom 2 (70-70)
            fullformat[296] = Rollnumberseven.size() + " / 70 ( " + evenonpt + " %)";
            fullformat[300] = evenonpt + "%";
            fullformat[305] = Rollnumberodds.size() + " / 70 ( " + oddsonpt + " %)";
            fullformat[309] = oddsonpt + "%";
            fullformat[317] = selectivepath;
            String recordpath = "Records"+'/'+selectivepath;
            File Sheet = new File(getExternalFilesDir(recordpath), dtx.format(now) + "_Sheet.html");
            FileOutputStream fos11 = new FileOutputStream(Sheet);
            String Dy;
            for (int m = 0; m < fullformat.length; m++) {
                Dy = fullformat[m] + "\n";
                fos11.write(Dy.getBytes());
            }
            fos11.close();
            thisbutton.setText("Done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void Special() {
        try {
            EditText block = findViewById(R.id.editTextTextMultiLine);
            Button thisbutton = findViewById(R.id.button3);
            int tempindex = block.getLineCount();
            String[] list = new String[tempindex];
            String fulltext =  block.getText().toString();
            String[] strarr = fulltext.split(Objects.requireNonNull(System.getProperty("line.separator")));
            String sx;
            for (int ii = 0; ii < strarr.length; ii++) {
                sx = strarr[ii];
                list[ii] = sx;
            }
            String rollno;
            ArrayList<String> even = new ArrayList<>();
            ArrayList<String> odd =  new ArrayList<>();
            String seriesA = "25";
            String seriesB = "26";
            boolean already;
            for (String line : list)
            {
                already = false;
                if (line != null)
                {
                    boolean b = line.contains(seriesA) || line.contains(seriesB);
                    if (!Islogtext(line))
                    {
                        if (b) // 23 & 24 for 2nd
                        {
                            for (int i = 0; i < line.length(); i++)
                            {
                                if (line.charAt(i) == seriesA.charAt(0) || line.charAt(i) == seriesB.charAt(0))
                                {
                                    if (line.length() >= i + 4)
                                    {
                                        if (line.charAt(i + 1) == seriesA.charAt(1) || line.charAt(i + 1) == seriesB.charAt(1)) // 3,4 for 2nd
                                        {
                                            if (IsNumeric(line.substring(i, i + 4)))
                                            {
                                                if (!already)
                                                {
                                                    rollno = line.substring(i, i + 4);
                                                    if (Iseven(rollno))
                                                    {
                                                        even.add(rollno + "   " + line.toUpperCase());
                                                    }
                                                    else
                                                    {
                                                        odd.add(rollno + "   " + line.toUpperCase());
                                                    }
                                                    already = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else {
                        if (!line.contains(" (privately):")) {
                            int getstindex = line.lastIndexOf(" to everyone:")+25;
                            String mynewline = line.substring(getstindex);
                            if (b) // 23 & 24 for 2nd
                            {
                                for (int i = 0; i < mynewline.length(); i++)
                                {
                                    if (mynewline.charAt(i) == seriesA.charAt(0) || mynewline.charAt(i) == seriesB.charAt(0))
                                    {
                                        if (mynewline.length() >= i + 4)
                                        {
                                            if (mynewline.charAt(i + 1) == seriesA.charAt(1) || mynewline.charAt(i + 1) == seriesB.charAt(1)) // 3,4 for 2nd
                                            {
                                                if (IsNumeric(mynewline.substring(i, i + 4)))
                                                {
                                                    if (!already)
                                                    {
                                                        rollno = mynewline.substring(i, i + 4);
                                                        if (Iseven(rollno))
                                                        {
                                                            even.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        else
                                                        {
                                                            odd.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        already = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else {
                            int getstindex = line.lastIndexOf(" (privately):") + 25;
                            String mynewline = line.substring(getstindex);
                            if (b) // 23 & 24 for 2nd
                            {
                                for (int i = 0; i < mynewline.length(); i++)
                                {
                                    if (mynewline.charAt(i) == seriesA.charAt(0) || mynewline.charAt(i) == seriesB.charAt(0))
                                    {
                                        if (mynewline.length() >= i + 4)
                                        {
                                            if (mynewline.charAt(i + 1) == seriesA.charAt(1) || mynewline.charAt(i + 1) == seriesB.charAt(1)) // 3,4 for 2nd
                                            {
                                                if (IsNumeric(mynewline.substring(i, i + 4)))
                                                {
                                                    if (!already)
                                                    {
                                                        rollno = mynewline.substring(i, i + 4);
                                                        if (Iseven(rollno))
                                                        {
                                                            even.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        else
                                                        {
                                                            odd.add(rollno + "   " + mynewline.toUpperCase());
                                                        }
                                                        already = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            String tmp;
            for (int i = 0; i < even.size(); i++)
            {
                for (int j = i + 1; j < even.size(); j++)
                {
                    if (Integer.parseInt(even.get(j).substring(0, 4)) < Integer.parseInt(even.get(i).substring(0, 4)))
                    {
                        tmp = even.get(i);
                        even.set(i,even.get(j));
                        even.set(j,tmp);
                    }
                }
            }
            for (int i = 0; i < odd.size(); i++)
            {
                for (int j = i + 1; j < odd.size(); j++)
                {
                    if (Integer.parseInt(odd.get(j).substring(0, 4)) < Integer.parseInt(odd.get(i).substring(0, 4)))
                    {
                        tmp = odd.get(i);
                        odd.set(i,odd.get(j));
                        odd.set(j,tmp);
                    }
                }
            }
            ArrayList<String> Rollnumberseven = new ArrayList<>();
            ArrayList<String> XXXX1 = new ArrayList<>();
            for (String currentline : even) {
                String num = currentline.substring(0,4);
                String kuta = currentline.substring(7);
                if (!Rollnumberseven.contains(num)) {
                    Rollnumberseven.add(num);
                    XXXX1.add(ExtractName(kuta));
                }
            }
            ArrayList<String> Rollnumberodds = new ArrayList<>();
            ArrayList<String> XXXX2 = new ArrayList<>();
            for (String currentlinex : odd) {
                String num1 = currentlinex.substring(0,4);
                String kuta = currentlinex.substring(7);
                if (!Rollnumberodds.contains(num1)) {
                    Rollnumberodds.add(num1);
                    XXXX2.add(ExtractName(kuta));
                }
            }
            DateTimeFormatter dtg = DateTimeFormatter.ofPattern("MMM dd, yyyy");
            DateTimeFormatter dtx = DateTimeFormatter.ofPattern("dd.MM.yyyy 'at' hh.mm.ss a");
            LocalDateTime now = LocalDateTime.now();
            String selectivepath = Subjectslist.getSelectedItem().toString();
            cachepath = "Caches"+'/'+selectivepath;
            myExternalFile0 = new File(getExternalFilesDir(cachepath), "Cache.L1.txt");
            FileWriter frx = new FileWriter(myExternalFile0, true);
            BufferedWriter bw = new BufferedWriter(frx);
            StringBuilder fullLine = new StringBuilder();
            for (String line:even) {
                fullLine.append(line.substring(0, 4)).append(';');
            }
            for (String line:odd) {
                fullLine.append(line.substring(0, 4)).append(';');
            }
            bw.write(dtx.format(now) + ';' + fullLine);
            bw.newLine();
            bw.close();
            frx.close();
            //File designfile = new File(getExternalFilesDir(cachepath), "Cache.L2.txt");
            //int getindex;
            //String gx;
            //if (!designfile.exists()) {
            //    getindex = 0;
            //}
            //else {
            //    FileReader fr3 = new FileReader(designfile);
            //    BufferedReader br3 = new BufferedReader(fr3);
            //    String entry = br3.readLine();
            //    br3.close();
            //    fr3.close();
            //    getindex = Integer.parseInt(entry);
            //    if (getindex > 8) {
            //        getindex = 0;
            //    }
            //    else {
            //        getindex++;
            //    }
            //}
            //gx = getindex + "";
            //FileWriter drw = new FileWriter(designfile);
            //BufferedWriter dbw = new BufferedWriter(drw);
            //dbw.write(gx);
            //dbw.close();
            //drw.close();
            //Designslist.setSelection(getindex);
            //String Designpath = Designslist.getSelectedItem().toString();
            BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("special2.html")));
            String mline;
            int cindex = 0;
            String[] fullformat = new String[471];
            while ((mline = br.readLine()) != null) {
                fullformat[cindex] = mline;
                cindex++;
            }
            br.close();
            StringBuilder evenbuilder = new StringBuilder();
            int currenteveninderx = 1;
            StringBuilder rns = new StringBuilder();
            StringBuilder rows = new StringBuilder();
            for (String itemXY : Rollnumberseven)
            {
                int tempformatch = Rollnumberseven.indexOf(itemXY);
                evenbuilder.append("<div class=\"student\" data-aos=\"fade-left\" data-aos-duration=\"3000\">");
                evenbuilder.append("<button class=\"ss\">").append(currenteveninderx).append("</button>");
                evenbuilder.append("<h1 class=\"r\">").append(itemXY).append("</h1>");
                evenbuilder.append("<p class=\"n\">").append(XXXX1.get(tempformatch)).append("</p>");
                evenbuilder.append("</div>");
                currenteveninderx++;
                rns.append(itemXY).append(",");
                rows.append('"').append(XXXX1.get(tempformatch)).append("\", ");
            }
            currenteveninderx = 1;
            StringBuilder oddsbuilder = new StringBuilder();
            for (String itemXYZ : Rollnumberodds)
            {
                int tempformatch = Rollnumberodds.indexOf(itemXYZ);
                oddsbuilder.append("<div class=\"student2\" data-aos=\"fade-right\" data-aos-duration=\"3000\">");
                oddsbuilder.append("<button class=\"ss\">").append(currenteveninderx).append("</button>");
                oddsbuilder.append("<h1 class=\"r\">").append(itemXYZ).append("</h1>");
                oddsbuilder.append("<p class=\"n\">").append(XXXX2.get(tempformatch)).append("</p>");
                oddsbuilder.append("</div>");
                currenteveninderx++;
                rns.append(itemXYZ).append(",");
                rows.append('"').append(XXXX2.get(tempformatch)).append("\", ");
            }
            int fullattend = Rollnumberseven.size() + Rollnumberodds.size();
            int totalper = (fullattend * 100) / 130;
            int evenonpt = (Rollnumberseven.size()*100) / 130;
            int oddsonpt = (Rollnumberodds.size()*100) / 130;
            int absentsonpt = 100 - totalper;
            int px = (360*evenonpt)/100;
            int py = ((360*oddsonpt)/100)+px;
            //fullformat[215] = "width: " + evenonpt + "%;";
            //fullformat[219] = "width: " + oddsonpt + "%;";
            fullformat[113] = "background-image: conic-gradient(#1e6f93 " + px + "deg, #d26c05 0 " + py + "deg, #7D1461 0);";
            //fullformat[377] = dtg.format(now);
            fullformat[362] = evenbuilder.toString();
            fullformat[364] = oddsbuilder.toString();
            fullformat[370] = evenonpt + " % - " + oddsonpt + " % - " + absentsonpt + " %";
            fullformat[374] = fullattend + " / 130 ( " + totalper + " %)"; // 140 for bcom 2 (70-70)
            fullformat[413] = "rows = [" + rows + "];";
            fullformat[414] = "rns = [" + rns + "];";
            //fullformat[300] = evenonpt + "%";
            //fullformat[305] = Rollnumberodds.size() + " / 65 ( " + oddsonpt + " %)";
            //fullformat[309] = oddsonpt + "%";
            //fullformat[317] = selectivepath;
            String recordpath = "Records"+'/'+selectivepath;
            File Sheet = new File(getExternalFilesDir(recordpath), dtx.format(now) + "_Sheet.html");
            FileWriter fos11 = new FileWriter(Sheet);
            BufferedWriter bfr11 = new BufferedWriter(fos11);
            String Dy = "";
            for (String s : fullformat) Dy += s;
            bfr11.write(Dy);
            bfr11.close();
            fos11.close();
            thisbutton.setText("Done...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Integer[] ExtractRollnumbers(String input) {
        int length = input.length() - 25;
        ArrayList<Integer> Rollnumbers = new ArrayList<>();
        int num;
        if (length > 4) {
            for (int x = 26; x < input.length(); x+=5) {
                num = Integer.parseInt(input.substring(x,x+4));
                if (!Rollnumbers.contains(num)) {
                    Rollnumbers.add(num);
                }
            }
        }
        return Rollnumbers.toArray(new Integer[0]);
    }
    private String ExtractDate(String input) {
        return input.substring(0,25);
    }
    private static boolean Iseven(String val)
    {
        try
        {
            int i = Integer.parseInt(val);
            return i % 2 == 0;
        }
        catch (RuntimeException ex)
        {
            System.out.println(ex.toString());
            throw ex;
        }
    }
    private boolean Islogtext(String str) {
        if (str.contains(" to everyone:")) {
            return true;
        }
        return str.contains(" (privately):");
    }
    private static boolean IsNumeric(String str) {
        if (str != null)
        {
            try
            {
                Integer.parseInt(str);
            }
            catch (NumberFormatException ex) {
                return false;
            }
        }
        return true;
    }
    private String ExtractName(String input) {
        //Character[] compare = new Character[] {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',' '};
        return input.replaceAll("[^a-zA-Z ]", "");
    }
}