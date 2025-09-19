package AdditionalTask4;

public class XmlExporter implements Exporter {
    @Override
    public void export(String data) {
        System.out.println("<data>" + data + "</data>");
    }
}