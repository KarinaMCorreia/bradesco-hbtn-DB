package entities;

import javax.persistence.*;

@Entity
public class MaterialCurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String urlMaterial;

    @OneToOne(mappedBy = "materialCurso")
    private Curso curso;

    public MaterialCurso() {
    }

    public Long getId() {
        return id;
    }

    public String getUrlMaterial() {
        return urlMaterial;
    }

    public void setUrlMaterial(String urlMaterial) {
        this.urlMaterial = urlMaterial;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}