package AdditionalTask4;

public class Main {
    public static void main(String[] args) {
        JsonExporter jsonExporter = new JsonExporter();
        XmlExporter xmlExporter = new XmlExporter();

        ReportGenerator reportGenerator = new ReportGenerator("aboba");

        reportGenerator.generate(jsonExporter);
        System.out.println();
        reportGenerator.generate(xmlExporter);
    }
}
