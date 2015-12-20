package plk.trainer;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class EditXML {

    static DocumentBuilderFactory icFactory;
    static DocumentBuilder icBuilder;
    static Document doc;

    static void initXML(File path){
        try {
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            Document newDoc = icBuilder.newDocument();
            Element rootElement = newDoc.createElement("programs");
            newDoc.appendChild(rootElement);
            DOMSource source = new DOMSource(newDoc);
            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);
        }
        catch(Exception ignored) {
            Log.e("hm", ignored.toString());
        }
    }


    static void addElement(Program program, File path){
        try {
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(path);

            Element programs = (Element) doc.getElementsByTagName("programs")
                    .item(0);

            Element newPerson = createProgram(program);

            programs.appendChild(newPerson);
            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);
        }
        catch(Exception ignored) {
            Log.e("hm", ignored.toString());
        }
    }

    static void deleteElement(int id, File path){
        try {
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(path);

            Element root = (Element)doc.getElementsByTagName("programs").item(0);
            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element) nList.item(temp);

                int pid = Integer.parseInt(eElement.getAttribute("id"));

                if (pid == id) {
                    root.removeChild(nList.item(temp));
                    break;
                }
            }

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(path);
            transformer.transform(source, result);
        }
        catch(Exception ignored) {
            Log.e("hm", ignored.toString());
        }
    }

    static Element createProgram(Program program)
    {
        Element pr = doc.createElement("program");
        pr.setAttribute("id", Integer.toString(Storage.MaxId));
        pr.setAttribute("name", program.Name);
        for(int i = 0; i < program.Exercises.length; ++i)
        {
            Element day = doc.createElement("day");
            for(int j = 0; j < program.Exercises[i].length; ++j)
            {
                Element exercise = doc.createElement("exercise");
                exercise.setAttribute("id", Integer.toString(program.Exercises[i][j].ExerciseId));
                exercise.setAttribute("repeats", program.Exercises[i][j].Repeats);
                day.appendChild(exercise);
            }

            pr.appendChild(day);
        }
        return pr;
    }



    static Dictionary<Integer,Program> parsePrograms(InputStream basePath, File custPath) {
        try {
            Dictionary<Integer, Program> out = new Hashtable<>();
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(basePath);
            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element)nList.item(temp);
                String name = eElement.getAttribute("name");
                int pid = Integer.parseInt(eElement.getAttribute("id"));

                NodeList days = eElement.getElementsByTagName("day");

                ProgramEntry[][] exer = new ProgramEntry[days.getLength()][];

                for (int day = 0; day < days.getLength(); day++) {
                    NodeList exercises = ((Element)days.item(day)).getElementsByTagName("exercise");
                    ProgramEntry[] entry = new ProgramEntry[exercises.getLength()];
                    for (int exercise = 0; exercise < exercises.getLength(); exercise++) {
                        Element ex = (Element)exercises.item(exercise);
                        int id = Integer.parseInt(ex.getAttribute("id"));
                        String repeats = ex.getAttribute("repeats");
                        entry[exercise] = new ProgramEntry(id, repeats);
                    }
                    exer[day] = entry;
                }
                out.put(pid, new Program(name, exer));
            }

            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(custPath);
            nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element)nList.item(temp);
                String name = eElement.getAttribute("name");
                Log.e("hm", name);
                int pid = Integer.parseInt(eElement.getAttribute("id"));

                if(pid > Storage.MaxId)
                {
                    Storage.MaxId = pid;
                }
                Log.e("hm", Integer.toString(pid));
                NodeList days = eElement.getElementsByTagName("day");

                ProgramEntry[][] exer = new ProgramEntry[days.getLength()][];

                for (int day = 0; day < days.getLength(); day++) {
                    NodeList exercises = ((Element)days.item(day)).getElementsByTagName("exercise");
                    ProgramEntry[] entry = new ProgramEntry[exercises.getLength()];
                    for (int exercise = 0; exercise < exercises.getLength(); exercise++) {
                        Element ex = (Element)exercises.item(exercise);
                        int id = Integer.parseInt(ex.getAttribute("id"));
                        String repeats = ex.getAttribute("repeats");
                        entry[exercise] = new ProgramEntry(id, repeats);
                    }
                    exer[day] = entry;
                }
                out.put(pid, new Program(name, exer));
            }

            return out;
        }
        catch(Exception e){
            Log.e("hm", e.toString());
            return null;
        }
    }

    static Dictionary<Integer,Exercise> parseExercises(InputStream path) {
        try {
            Dictionary<Integer, Exercise> out = new Hashtable<>();
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(path);
            NodeList exer = doc.getElementsByTagName("exercises");
            NodeList nList = ((Element)exer.item(0)).getElementsByTagName("exercise");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element)nList.item(temp);
                String name = eElement.getAttribute("name");
                int pid = Integer.parseInt(eElement.getAttribute("id"));
                String desc = eElement.getAttribute("description");
                String vid = eElement.getAttribute("video");
                String img = eElement.getAttribute("image");
                out.put(pid, new Exercise(name, desc, vid, img));
            }
            return out;
        }
        catch(Exception e){
            return null;
        }
    }

    static Dictionary<Integer,Question> parseQuestions(InputStream path) {
        try {
            Dictionary<Integer, Question> out = new Hashtable<>();
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(path);
            NodeList nList = doc.getElementsByTagName("question");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element)nList.item(temp);
                String name = eElement.getAttribute("text");
                int pid = Integer.parseInt(eElement.getAttribute("id"));

                NodeList ans = eElement.getElementsByTagName("ans");

                String[] anss = new String[ans.getLength()];

                for (int a = 0; a < ans.getLength(); a++) {
                    Element e = (Element)ans.item(a);
                    anss[a] = e.getAttribute("name");
                }
                out.put(pid, new Question(name, anss));
            }
            return out;
        }
        catch(Exception e){
            return null;
        }
    }

    static Program CreateCustomProgramBasedOnTest(InputStream path, String programName, File savePath)
    {
        try {
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(path);
            NodeList nList = doc.getElementsByTagName("question");

            List<List<ProgramEntry>> base = new ArrayList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Element eElement = (Element)nList.item(temp);
                int pid = Integer.parseInt(eElement.getAttribute("id"));
                NodeList ans = eElement.getElementsByTagName("ans");
                Element e = (Element)ans.item(Storage.TestQuestions.get(temp).CurrentAnswer);
                NodeList use_prog = e.getElementsByTagName("useprog");
                for (int a = 0; a < use_prog.getLength(); a++)
                {
                    String tag = ((Element)use_prog.item(a)).getAttribute("tag");
                    NodeList nList1 = doc.getElementsByTagName("base_prog");

                    for (int temp1 = 0; temp1 < nList1.getLength(); temp1++) {

                        Element eElement1 = (Element)nList1.item(temp1);
                        String name = eElement1.getAttribute("tag");
                        if (!name.equals(tag)) continue;

                        NodeList days = eElement1.getElementsByTagName("day");

                        List<List<ProgramEntry>> exer = new ArrayList<>();

                        for (int day = 0; day < days.getLength(); day++) {
                            NodeList exercises = ((Element)days.item(day)).getElementsByTagName("exercise");
                            List<ProgramEntry> entry = new ArrayList<>();
                            for (int exercise = 0; exercise < exercises.getLength(); exercise++) {
                                Element ex = (Element)exercises.item(exercise);
                                int id = Integer.parseInt(ex.getAttribute("id"));
                                String repeats = ex.getAttribute("repeats");
                                entry.add(new ProgramEntry(id, repeats));
                            }
                            exer.add(entry);
                        }
                        base = exer;
                    }
                }
                NodeList exclude = e.getElementsByTagName("exclude_exercise");
                for (int a = 0; a < exclude.getLength(); a++)
                {
                    String ids = ((Element)exclude.item(a)).getAttribute("id");
                    int idi = Integer.parseInt(ids);
                    for (int x = 0; x < base.size(); x++) {
                        for (int y = 0; y < base.get(x).size(); y++) {
                            if (base.get(x).get(y).ExerciseId == idi)
                            {
                                base.get(x).remove(y);
                                y--;
                            }
                        }
                    }
                }
            }
            ProgramEntry[][] pre = new ProgramEntry[base.size()][];
            for (int x = 0; x < base.size(); x++) {
                ProgramEntry[] pre1 = new ProgramEntry[base.get(x).size()];
                for (int y = 0; y < base.get(x).size(); y++) {
                    pre1[y] = base.get(x).get(y);
                }
                pre[x]=pre1;
            }
            Program out = new Program(programName, pre);
            addElement(out, savePath);
            return out;
        }
        catch(Exception e){
            return null;
        }
    }
}
