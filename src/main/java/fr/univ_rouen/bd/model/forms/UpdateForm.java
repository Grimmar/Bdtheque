package fr.univ_rouen.bd.model.forms;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;

/**
 *
 * @author bissoqu1
 */
public class UpdateForm extends BdForm {
    private final String id;

    public UpdateForm(BdDao dao, String id) {
        super(dao);
        this.id = id;
    }

    @Override
    protected void update(Bd bd) {
        if (isValid()) {
            bd.setId(id);
            bdDao.update(bd);
            setResult("Succès lors de la modification de la bd " + bd.getTitre());
        } else {
            setResult("Echec lors de la modification de la bd " + bd.getTitre());
        }
    }

}
