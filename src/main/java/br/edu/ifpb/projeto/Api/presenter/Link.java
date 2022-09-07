package br.edu.ifpb.projeto.Api.presenter;

import javax.ws.rs.core.UriBuilder;
import java.net.URI;

public abstract class Link {
    private String rel;
    private String href;
    public Link() {
    }
    public Link(String rel, String href) {
        this.rel = rel;
        this.href = href;
    }

    public abstract URI getUri();

    public abstract UriBuilder getUriBuilder();

    public String getRel() {
        return rel;
    }
    public void setRel(String rel) {
        this.rel = rel;
    }
    public String getHref() {
        return href;
    }
    public void setHref(String href) {
        this.href = href;
    }
}
