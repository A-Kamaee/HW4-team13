package DB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table FUND.
 */
public class fund {

    private Long id;
    private String rooz;
    private String jari;
    private String daramad;

    public fund() {
    }

    public fund(Long id) {
        this.id = id;
    }

    public fund(Long id, String rooz, String jari, String daramad) {
        this.id = id;
        this.rooz = rooz;
        this.jari = jari;
        this.daramad = daramad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRooz() {
        return rooz;
    }

    public void setRooz(String rooz) {
        this.rooz = rooz;
    }

    public String getJari() {
        return jari;
    }

    public void setJari(String jari) {
        this.jari = jari;
    }

    public String getDaramad() {
        return daramad;
    }

    public void setDaramad(String daramad) {
        this.daramad = daramad;
    }

}
