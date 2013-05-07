<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="deleteImage">
    <c:url value="/img/delete.png" />
</c:set>
<c:out value="${requestScope.form.result}" />
<h2 class="main-title">Saisie manuelle des données</h2>
<form action="<c:url value="/add" />" method="post" ng-controller="AddBdCtrl" ng-app="directives">
    <fieldset>
        <legend>Informations principales</legend>
        <div>
            <c:if test="${!empty requestScope.form.errors['isbn']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['isbn']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="isbn">ISBN-10 ou ISBN-13</label>
            <input type="text" name="isbn" required="required" value="<c:out value="${requestScope.bd.isbn}"/>" placeholder="Identifant unique"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['titre']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['titre']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="titre">Titre</label>
            <input type="text" name="titre" required="required" value="<c:out value="${requestScope.bd.titre}"/>"  placeholder="Titre de la bd"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['planches']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['planches']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="planches">Planches</label>
            <input type="number" name="planches" required="required" value="<c:out value="${requestScope.bd.planches}"/>" placeholder="Nombre de pages"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['serie']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['serie']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="serie">Série</label>
            <input type="text" name="serie" value="<c:out value="${requestScope.bd.serie}"/>" placeholder="One-Shot"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['langue']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['langue']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="langue">Langue</label>
            <input type="text" name="langue" maxlength="2" value="<c:out value="${requestScope.bd.langue}"/>" placeholder="Langue (ex: FR, EN)"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['editeur']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['editeur']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="editeur">Editeur</label>
            <input type="text" name="editeur" value="<c:out value="${requestScope.bd.editeur}"/>" placeholder="Editeur de la bd"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['format']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['format']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="format">Format</label>
            <input type="text" name="format" value="<c:out value="${requestScope.bd.format}"/>" placeholder="Format"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['image']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['image']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="image">Image</label>
            <input type="url" name="image" value="<c:out value="${requestScope.bd.image}"/>" placeholder="Adresse de l'image"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['parution']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['parution']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <c:set var="parution">
                <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${requestScope.parution}" />
            </c:set>
            <label for="parution">Date de parution</label>
            <input type="text" name="parution" class="datepicked" required ng-init="parution='${parution}'" placeholder="Date de parution" ng-model="parution" datepicker/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['creationDate']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['creationDate']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <c:set var="creationDate">
                <fmt:formatDate type="date" pattern="dd/MM/yyyy" value="${requestScope.creationDate}" />
            </c:set>
            <label for="creationDate">Date de création</label>
            <input type="text" class="datepicked" name="creationDate" ng-init="creationDate='${creationDate}'" placeholder="Date de création" ng-model="creation" datepicker/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['depotLegal']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['depotLegal']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="depotLegal">Date de dépôt légal</label>
            <input type="text" name="depotLegal" value="<c:out value="${requestScope.depotLegal}"/>" placeholder="mm/aaaa ou jj/mm/aaaa"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['finImpression']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['finImpression']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="finImpression">Date de fin d'impression</label>
            <input type="text" name="finImpression" value="<c:out value="${requestScope.finImpression}"/>" placeholder="mm/aaaa ou jj/mm/aaaa"/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['tome']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['tome']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="numero">Tome</label>
            <input type="text" name="numero" value="<c:out value="${requestScope.bd.tome.numero}"/>" placeholder="Numéro du tome"/>
            <input type="text" name="informations" value="<c:out value="${requestScope.bd.tome.informations}"/>" placeholder="Ex: bis, ter"/>
        </div>
        <div class="full-size">
            <c:if test="${!empty requestScope.form.errors['resume']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['resume']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="resume">Résumé</label>
            <textarea name="resume" required placeholder="Résumé du tome"><c:out value="${requestScope.bd.resume}"/></textarea>
        </div>
    </fieldset>
    <fieldset>
        <legend>Informations complémentaires</legend>
        <div ng-init="setScenaristes('<c:out value="${requestScope.scenaristesString}');"/>">
            <c:if test="${!empty requestScope.form.errors['scenariste']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['scenariste']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
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
                            <a href="#delete" class="rmv-btn" ng-click="deleteScenariste(scenariste)">
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
            <c:if test="${!empty requestScope.form.errors['dessinateur']}">
                <ul class="unstyled">
                    <c:forEach items="${requestScope.form.errors['dessinateur']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
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
                            <a href="#delete" class="rmv-btn" ng-click="deleteDessinateur(des)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="dessinateur" ng-model="dessinateursString" value=""/>
            <br />
        </div>
        <div ng-init="setColoristes('<c:out value="${requestScope.coloristesString}');"/>">
            <c:if test="${!empty requestScope.form.errors['coloriste']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['coloriste']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
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
                            <a href="#delete" class="rmv-btn" ng-click="deleteColoriste(col)"></a>
                            <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="coloriste" ng-model="coloristesString" />
            <br />
        </div>
        <div ng-init="setLettreurs('<c:out value="${requestScope.lettreursString}');"/>">
            <c:if test="${!empty requestScope.form.errors['lettrage']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['lettrage']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
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
                            <a href="#delete" class="rmv-btn" ng-click="deleteLettreur(lettreur)">
                                <img src="${deleteImage}" alt="Supprimer" />
                            </a>
                        </td>
                    </tr>
                </tbody>
            </table>
            <input type="hidden" name="lettrage" ng-model="lettreursString" />
            <br />
        </div>
        <div ng-init="setEncreurs('<c:out value="${requestScope.encreursString}');"/>">
            <c:if test="${!empty requestScope.form.errors['encrages']}">
                <c:forEach items="${requestScope.form.errors['encrages']}" var="error">
                    <li><c:out value="${error}" /></li>
                    </c:forEach>  
                </c:if>
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
            <input type="hidden" name="encrage" ng-model="encreursString" />
            <br />
        </div>
    </fieldset>
    <input ng-disabled="uploadForm.$invalid" type="submit" value="Ajouter la bd"/>
</form>
