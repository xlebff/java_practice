package AdditionalTask4;

public class JsonExporter implements Exporter {
    @Override
    public void export(String data) {
        System.out.println("{ \"data\": \"" + data + "\" }");
    }
}
