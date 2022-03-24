package ok.suxrob.YouTube;

public class YouTubeDTO {
    private String VideoTitle;
    private AllFormats[] AllFormats;

    public String getVideoTitle() {
        return VideoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        VideoTitle = videoTitle;
    }

    public AllFormats[] getAllFormats() {
        return AllFormats;
    }

    public void setAllFormats(AllFormats[] allFormats) {
        AllFormats = allFormats;
    }
}
