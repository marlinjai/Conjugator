public class Translation {
    private String detected_source_language;
    private String text;

    public Translation(String detected_source_language, String text) {
        this.detected_source_language = detected_source_language;
        this.text = text;
    }

    public String getDetectedSourceLanguage() {
        return detected_source_language;
    }

    public String getText() {
        return text;
    }
}