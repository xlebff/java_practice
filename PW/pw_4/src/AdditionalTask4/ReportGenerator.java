package AdditionalTask4;

public class ReportGenerator {
    private String data;

    public ReportGenerator(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void generate(Exporter exporter) {
        exporter.export(this.data);
    }
}
