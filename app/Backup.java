package com.example.rollextract;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.Object;

public class MainActivity extends AppCompatActivity {
    private Button Process;
    private String filename = "Chat.txt";
    private String f2 = "Attendence.txt";
    private String f3 = "Record.txt";
    private String filepath = "MyFileStorage";
    File myExternalFile;
    File myExternalFile1;
    File myExternalFile2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Process = findViewById(R.id.button);
    }
    public void onClick(View v) {
       Proceed();
    }
    public void onHTML(View v) {
        HTML();
    }
    void HTML() {
        try {


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    void Proceed() {
        try {
            String rollno;
          //  File d = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            myExternalFile = new File(getExternalFilesDir(filepath), filename);
            FileReader frx = new FileReader(myExternalFile);
            BufferedReader brx = new BufferedReader(frx);
            int tempindex = (int)brx.lines().count();
            frx.close();
            FileReader fr = new FileReader(myExternalFile);
            BufferedReader br = new BufferedReader(fr);
           // FileInputStream fis = new FileInputStream(myExternalFile);
           // DataInputStream in = new DataInputStream(fis);
           // BufferedReader brx = new BufferedReader(fr);
           // Scanner br = new Scanner(myExternalFile); br.hasNextLine() br.nextLine()
            String[] list = new String[tempindex];
            String sx;
            for (int ii = 0; ii < tempindex; ii++) {
                sx = br.readLine();
                list[ii] = sx;
            }
            fr.close();
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
                if (line.contains("25") || line.contains("26"))
                {
                    for (int i = 0; i < line.length(); i++)
                    {
                        if (line.charAt(i) == '2')
                        {
                            if (line.length() >= i + 4)
                            {
                                if (line.charAt(i + 1) == '5' || line.charAt(i + 1) == '6')
                                {
                                    if (!already)
                                    {
                                        rollno = line.substring(i, i + 4);
                                        rns[index] = rollno + "   " + line;
                                        evenextra[index] = Integer.parseInt(rollno);
                                        if (Iseven(rollno))
                                        {
                                            even[evenindex] = rollno + "   " + line;
                                            evenindex++;
                                        }
                                        else
                                        {
                                            odd[oddindex] = rollno + "   " + line;
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
            myExternalFile1 = new File(getExternalFilesDir(filepath), f2);
            //FileWriter fw = new FileWriter(myExternalFile1);
            FileOutputStream fos = new FileOutputStream(myExternalFile1);
            //BufferedWriter bw = new BufferedWriter(fw);
            String Dx;
            for (int iii = 0; iii < rns.length; iii++) {
                //
                Dx = rns[iii] + "\n";
                fos.write(Dx.getBytes());
                //bw.append(Dx);
                //bw.write(Dx);
                //bw.newLine();
            }
            fos.close();
            // remove empty
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
            String[] final_Keyword = new String[rns.length * 2];
            int finalindex = 0;
            final_Keyword[finalindex] = "Even Students (In ascending order)";
            final_Keyword[finalindex + 1] = "";
            finalindex = 2;
            for (String item : even)
            {
                final_Keyword[finalindex] = item;
                finalindex++;
            }
            final_Keyword[finalindex] = "";
            finalindex++;
            final_Keyword[finalindex] = "Odd Students (In ascending order)";
            finalindex++;
            final_Keyword[finalindex] = "";
            finalindex++;
            for (String item : odd)
            {
                final_Keyword[finalindex] = item;
                finalindex++;
            }
            final_Keyword[finalindex] = "";
            finalindex++;
            final_Keyword[finalindex] = "Even Students : " + even.length;
            finalindex++;
            final_Keyword[finalindex] = "Odd Students :  " + odd.length;
            finalindex++;
            ArrayList<String> templist2 = new ArrayList<>();
            for (String item : rns)
            {
                if (item != null)
                {
                    templist2.add(item);
                }
            }
            rns = templist2.toArray(new String[0]);
            //ArrayList<Integer> templist3 = new ArrayList<>();
            //for (int item : evenextra)
            //{
            //    if (item != 0)
            //    {
            //        templist3.add(item);
            //    }
            //}
            //evenextra = templist3.toArray(new Integer[0]);
            int fullattend = rns.length;
            final_Keyword[finalindex] = "Total Attendences  : " + fullattend + "   // Gross Attendence";
            finalindex++;
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
            File htmlTemplate = new File(getExternalFilesDir(filepath), "Format.html");;
            FileReader frr = new FileReader(htmlTemplate);
            String[] sb = new String[38];
            int sbindex = 0;
            try (BufferedReader brr = new BufferedReader(frr)) {
                String line;
                while ((line=brr.readLine())!=null) {
                    sb[sbindex] = line;
                    sbindex++;
                }
            }
            frr.close();
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
            sb[24] = evenbuilder.toString();
            sb[33] = oddsbuilder.toString();
            StringBuilder xcv = new StringBuilder();
            xcv.append("<h1>Total Attendence  : " + fullattend + "</h1>").append("\n");
            xcv.append("\n").append("\n");
            xcv.append("<h2 style=\"text-align:center\">Digital Attendence 1.0</h2>").append("\n");
            xcv.append("<h3 style=\"text-align:center\">Bcom III(B), PGGC-11, Chandigarh</h3>").append("\n");
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime now = LocalDateTime.now();
            xcv.append(" ").append("\n");
            xcv.append("<h2 style=\"text-align:center\">Generated on : " + dtf.format(now) + "</h2>").append("\n");
            sb[35] = xcv.toString();
            File Sheet = new File(getExternalFilesDir(filepath), "Sheet.html");
            FileOutputStream fos11 = new FileOutputStream(Sheet);
            String Dy;
            for (int m = 0; m < sb.length; m++) {
                Dy = sb[m] + "\n";
                fos11.write(Dy.getBytes());
            }
            fos11.close();
            //String[] special = (String[])Stream.of(evenextra).distinct().toArray();
            ArrayList<String> templist4 = new ArrayList<>();
            HashMap<Integer, Integer> hm = new HashMap<>();
            for (int iiiii = 0; iiiii < evenextra.length; iiiii++) {
                hm.put(evenextra[iiiii],iiiii);
                templist4.add(hm.keySet().toString());
            }
            //for (int x = 0; x < evenextra.length; x++) {
            //    for (int y = 0; y < x; y++) {
            //        if (evenextra[x] == evenextra[y])
            //            break;
            //        if (x == y)
            //            templist4.add(evenextra[x]);
            //    }
            //}
            int duplicatescount = fullattend - templist4.size();
            int netattendence = rns.length - duplicatescount;
            final_Keyword[finalindex] = "Duplicates found   : " + duplicatescount + "   // Attendence written multiple times";
            finalindex++;
            final_Keyword[finalindex] = "Actual Attendences : " + netattendence +   "   // Net Attendence";
            finalindex++;
            ArrayList<String> templist5 = new ArrayList<>();
            for (String item : final_Keyword)
            {
                if (item != null)
                {
                    templist5.add(item);
                }
            }
            final_Keyword = templist5.toArray(new String[0]);
            myExternalFile2 = new File(getExternalFilesDir(filepath), f3);
            FileOutputStream fos1 = new FileOutputStream(myExternalFile2);
            String Dxx;
            for (int iiii = 0; iiii < final_Keyword.length; iiii++) {
                Dxx = final_Keyword[iiii] + "\n";
                fos1.write(Dxx.getBytes());
            }
            fos1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
}