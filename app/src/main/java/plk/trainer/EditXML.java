package plk.trainer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.File;

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

}
