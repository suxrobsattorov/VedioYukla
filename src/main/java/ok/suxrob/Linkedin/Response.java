package ok.suxrob.Linkedin;

import java.util.ArrayList;
import java.util.List;

public class Response {
    private List<Links> links=new ArrayList();

    public List<Links> getLinks() {
        return links;
    }

    public void setLinks(List<Links> links) {
        this.links = links;
    }
}
