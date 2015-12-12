package plk.trainer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

/**
 * Created by Dmitry on 08.12.2015.
 */
public class EditXML {

    static int id = 0;//Временно
    static DocumentBuilderFactory icFactory;
    static DocumentBuilder icBuilder;
    static Document doc;

    static void addElement(Program program){
        try {
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse("programs.xml");

            Element programs = (Element) doc.getElementsByTagName("programs")
                    .item(0);

            Element newPerson = createProgram(program);

            programs.appendChild(newPerson);

            TransformerFactory transformerFactory = TransformerFactory
                    .newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("programs.xml"));
            transformer.transform(source, result);
        }catch(Exception e){

        }
    }

    static Element createProgram(Program program)
    {
        Element pr = doc.createElement("program");
        pr.setAttribute("id", Integer.toString(id));
        pr.setAttribute("name", program.Name);
        for(int i = 0; i < program.Exercises.length; ++i)
        {
            Element day = doc.createElement("day");
            for(int j = 0; j < program.Exercises[i].length; ++j)
            {
                Element exercise = doc.createElement("exercise");
                exercise.setAttribute("id", Integer.toString(program.Exercises[i][j].ExerciseId));
                exercise.setAttribute("times", Integer.toString(program.Exercises[i][j].Times));
                exercise.setAttribute("repeats", Integer.toString(program.Exercises[i][j].Repeats));
                day.appendChild(exercise);
            }

            pr.appendChild(day);
        }
        return pr;
    }

    static List<Program> parsePrograms(String path) {
        try {
            List<Program> out = new ArrayList<Program>();
            icFactory = DocumentBuilderFactory.newInstance();
            icBuilder = icFactory.newDocumentBuilder();
            doc = icBuilder.parse(path + ".xml");
            NodeList nList = doc.getElementsByTagName("program");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Element eElement = (Element)nList.item(temp);
                String name = eElement.getAttribute("name");

                NodeList days = eElement.getChildNodes();

                ProgramEntry[][] exer = new ProgramEntry[days.getLength()][];

                for (int day = 0; day < days.getLength(); day++) {
                    NodeList exercises = days.item(day).getChildNodes();
                    ProgramEntry[] entry = new ProgramEntry[exercises.getLength()];
                    for (int exercise = 0; exercise < days.getLength(); exercise++) {
                        Element ex = (Element)exercises.item(temp);
                        int id = Integer.parseInt(ex.getAttribute("id"));
                        int times = Integer.parseInt(ex.getAttribute("times"));
                        int repeats = Integer.parseInt(ex.getAttribute("repeats"));
                        entry[exercise] = new ProgramEntry(id, times, repeats);
                    }
                    exer[day] = entry;
                }
                out.add(new Program(name, exer));
            }
            return out;
        }catch(Exception e){
            return null;
        }
    }

}
