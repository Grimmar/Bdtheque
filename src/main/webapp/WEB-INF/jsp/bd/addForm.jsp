<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="deleteImage">
    <c:url value="/img/delete.png" />
</c:set>
<h2 class="main-title">Saisie manuelle des données</h2>
<form name="addForm" action="<c:url value="/add" />" method="post" ng-controller="AddBdCtrl" ng-app="directives">
    <c:if test="${!empty requestScope.form.errors}">
        <ul>
            <c:forEach items="${requestScope.form.errors}" var="errors">
                <c:forEach items="${errors.value}" var="error">
                    <li>
                        <c:out value="${error}" />
                    </li>
                </c:forEach>    
            </c:forEach>    
        </ul>
    </c:if>
    <fieldset>
        <legend>Informations principales</legend>
        <div>
            <label for="isbn">ISBN-10 ou ISBN-13</label>
            <input type="text" name="isbn" required="required" value="<c:out value="${requestScope.bd.isbn}"/>" placeholder="Identifant unique"/>
        </div>
        <div>
            <label for="titre">Titre</label>
            <input type="text" name="titre" required="required" value="<c:out value="${requestScope.bd.titre}"/>"  placeholder="Titre de la bd"/>
        </div>
        <div>
            <label for="planches">Planches</label>
            <input type="number" name="planches" required="required" value="<c:out value="${requestScope.bd.planches}"/>" placeholder="Nombre de pages"/>
        </div>
        <div>
            <label for="serie">Série</label>
            <input type="text" name="serie" value="<c:out value="${requestScope.bd.serie}"/>" placeholder="One-Shot"/>
        </div>
        <div>
            <label for="langue">Langue</label>
            <input type="text" name="langue" maxlength="2" value="<c:out value="${requestScope.bd.langue}"/>" placeholder="Langue (ex: FR, EN)"/>
        </div>
        <div>
            <label for="editeur">Editeur</label>
            <input type="text" name="editeur" value="<c:out value="${requestScope.bd.editeur}"/>" placeholder="Editeur de la bd"/>
        </div>
        <div>
            <label for="format">Format</label>
            <input type="text" name="format" value="<c:out value="${requestScope.bd.format}"/>" placeholder="Format"/>
        </div>
        <div>
            <label for="image">Image</label>
            <input type="url" name="image" value="<c:out value="${requestScope.bd.image}"/>" placeholder="Adresse de l'image" required/>
        </div>
        <div>
            <c:set var="parution">
                <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${requestScope.parution}" />
            </c:set>
            <label for="parution">Date de parution</label>
            <input type="text" name="parution" class="datepicked" required ng-init="parution='${parution}'" placeholder="Date de parution" ng-model="parution" datepicker/>
        </div>
        <div>
            <c:set var="creationDate">
                <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${requestScope.creationDate}" />
            </c:set>
            <label for="creationDate">Date de création</label>
            <input type="text" class="datepicked" name="creationDate" ng-init="creationDate='${creationDate}'" placeholder="Date de création" ng-model="creation" datepicker/>
        </div>
        <div>
            <label for="depotLegal">Date de dépôt légal</label>
            <input type="text" name="depotLegal" value="<c:out value="${requestScope.depotLegal}"/>" placeholder="mm/aaaa ou jj/mm/aaaa"/>
        </div>
        <div>
            <label for="finImpression">Date de fin d'impression</label>
            <input type="text" name="finImpression" value="<c:out value="${requestScope.finImpression}"/>" placeholder="mm/aaaa ou jj/mm/aaaa"/>
        </div>
        <div>
            <label for="numero">Tome</label>
            <input type="text" name="numero" value="<c:out value="${requestScope.bd.tome.numero}"/>" placeholder="Numéro du tome"/>
            <input type="text" name="informations" value="<c:out value="${requestScope.bd.tome.informations}"/>" placeholder="Ex: bis, ter"/>
        </div>
        <div class="full-size">
            <label for="resume">Résumé</label>
            <textarea name="resume" required placeholder="Résumé du tome"><c:out value="${requestScope.bd.resume}"/></textarea>
        </div>
    </fieldset>
    <fieldset>
        <legend>Informations complémentaires</legend>
        <div ng-init="setScenaristes('<c:out value="${requestScope.scenaristesString}');"/>">
            <label>Scénaristes:</label>
            <input type="text" name="nom" ng-model="scenaristeLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="scenaristeFirstname" value="" placeholder="Prénom"/>
            <input type="button" value="Ajouter" class="add-btn" ng-click="addScenariste();">
            <table class="individus" ng-show="scenaristes.length">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="scenariste in scenaristes">
                        <td>{{$index + 1}}</td>
                        <td>{{scenariste.lastname}}</td>
                        <td>{{scenariste.firstname}}</td>
                        <td>
                            <a href="" class="rmv-btn" ng-click="deleteScenariste(scenariste)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="scenariste" ng-model="scenaristesString" value="{{scenaristesString}}"/>
            <br />
        </div>
        <div ng-init="setDessinateurs('<c:out value="${requestScope.dessinateursString}');"/>">
            <label>Dessinateurs:</label>
            <input type="text" name="nom" ng-model="dessinateurLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="dessinateurFirstname" value="" placeholder="Prénom"/>
            <input type="button" value="Ajouter" class="add-btn" ng-click="addDessinateur();">
            <table class="individus" ng-show="dessinateurs.length">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="des in dessinateurs">
                        <td>{{$index + 1}}</td>
                        <td>{{des.lastname}}</td>
                        <td>{{des.firstname}}</td>
                        <td>
                            <a href="" class="rmv-btn" ng-click="deleteDessinateur(des)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="dessinateur" ng-model="dessinateursString" value="{{dessinateursString}}"/>
            <br />
        </div>
        <div ng-init="setColoristes('<c:out value="${requestScope.coloristesString}');"/>">
            <label>Coloristes:</label>
            <input type="text" name="nom" ng-model="coloristeLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="coloristeFirstname" value="" placeholder="Prénom"/>
            <input type="button" value="Ajouter" class="add-btn" ng-click="addColoriste();">
            <table class="individus" ng-show="coloristes.length">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="col in coloristes">
                        <td>{{$index + 1}}</td>
                        <td>{{col.lastname}}</td>
                        <td>{{col.firstname}}</td>
                        <td>
                            <a href="" class="rmv-btn" ng-click="deleteColoriste(col)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="coloriste" ng-model="coloristesString" value="{{coloristesString}}"/>
            <br />
        </div>
        <div ng-init="setLettreurs('<c:out value="${requestScope.lettreursString}');"/>">
            <label>Lettreurs:</label>
            <input type="text" name="nom" ng-model="lettreurLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="lettreurFirstname" value="" placeholder="Prénom"/>
            <input type="button" value="Ajouter" class="add-btn" ng-click="addLettreur();">
            <table class="individus" ng-show="lettreurs.length">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="lettreur in lettreurs">
                        <td>{{$index + 1}}</td>
                        <td>{{lettreur.lastname}}</td>
                        <td>{{lettreur.firstname}}</td>
                        <td>
                            <a href="" class="rmv-btn" ng-click="deleteLettreur(lettreur)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="lettrage" ng-model="lettreursString" value="{{lettreursString}}"/>
            <br />
        </div>
        <div ng-init="setEncreurs('<c:out value="${requestScope.encreursString}');"/>">
            <label>Encrage:</label>
            <input type="text" name="nom" ng-model="encreurLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="encreurFirstname" value="" placeholder="Prénom"/>
            <input type="button" value="Ajouter" class="add-btn" ng-click="addEncreur();">
            <table class="individus" ng-show="encreurs.length">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Nom</th>
                        <th>Prénom</th>
                        <th>&nbsp;</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="encreur in encreurs">
                        <td>{{$index + 1}}</td>
                        <td>{{encreur.lastname}}</td>
                        <td>{{encreur.firstname}}</td>
                        <td>
                            <a href="#delete" class="rmv-btn" ng-click="deleteEncreur(encreur)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="encrage" ng-model="encreursString" value="{{encreursString}}"/>
            <br />
        </div>
    </fieldset>
    <input class="btn" type="submit" value="Ajouter la bd"/>
</form>
