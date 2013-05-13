package fr.univ_rouen.bd.model.forms;

import fr.univ_rouen.bd.model.beans.Bd;
import fr.univ_rouen.bd.model.dao.BdDao;

/**
 *
 * @author bissoqu1
 */
public class AddForm extends BdForm {

    public AddForm(BdDao dao) {
        super(dao);
    }

    @Override
    protected void update(Bd bd) {
        if (isValid()) {
            bdDao.add(bd);
            setResult("Succès lors de l'ajout de la nouvelle bd.");
        } else {
            setResult("Echec lors de l'ajout de la nouvelle bd.");
        }
    }

}
