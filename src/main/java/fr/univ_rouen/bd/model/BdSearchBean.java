package fr.univ_rouen.bd.model;

/**
 *
 * @author bissoqu1
 */
public class BdSearchBean {

    protected String titre;
    protected String editeur;
    protected String resume;
    protected ScenaristesType scenaristes;
    protected DessinateursType dessinateurs;
    protected ColoristesType coloristes;
    protected LettragesType lettrages;
    protected EncragesType encrages;
    protected String serie;
    protected String langue;

    public BdSearchBean() {
        titre = null;
        editeur = null;
        resume = null;
        serie = null;
        langue = null;
        scenaristes = new ScenaristesType();
        dessinateurs = new DessinateursType();
        coloristes = new ColoristesType();
        lettrages = new LettragesType();
        encrages = new EncragesType();
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public ScenaristesType getScenaristes() {
        return scenaristes;
    }

    public void setScenaristes(ScenaristesType scenaristes) {
        this.scenaristes = scenaristes;
    }

    public DessinateursType getDessinateurs() {
        return dessinateurs;
    }

    public void setDessinateurs(DessinateursType dessinateurs) {
        this.dessinateurs = dessinateurs;
    }

    public ColoristesType getColoristes() {
        return coloristes;
    }

    public void setColoristes(ColoristesType coloristes) {
        this.coloristes = coloristes;
    }

    public LettragesType getLettrages() {
        return lettrages;
    }

    public void setLettrages(LettragesType lettrages) {
        this.lettrages = lettrages;
    }

    public EncragesType getEncrages() {
        return encrages;
    }

    public void setEncrages(EncragesType encrages) {
        this.encrages = encrages;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getLangue() {
        return langue;
    }

    public void setLangue(String langue) {
        this.langue = langue;
    }
}
