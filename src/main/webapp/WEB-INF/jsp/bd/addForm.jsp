<c:out value="${requestScope.form.result}" />
<h2>Saisie manuelle des donn�es</h2>
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
            <label for="serie">S�rie</label>
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
            <label for="parution">Date de parution</label>
            <input type="text" name="parution" class="datepicked" required ng-init="parution='<c:out value="${requestScope.bd.parution}"/>'" placeholder="Date de parution" ng-model="parution" datepicker/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['creationDate']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['creationDate']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="creationDate">Date de cr�ation</label>
            <input type="text" class="datepicked" name="creationDate" ng-init="creationDate='<c:out value="${requestScope.bd.creationDate}"/>'" placeholder="Date de cr�ation" ng-model="creation" datepicker/>
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['depotLegal']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['depotLegal']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="depotLegal">Date de d�p�t l�gal</label>
            <input type="text" name="depotLegal" value="<c:out value="${requestScope.bd.depotLegal}"/>" placeholder="mm/aaaa ou jj/mm/aaaa"/>
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
            <input type="text" name="finImpression" value="<c:out value="${requestScope.bd.finImpression}"/>" placeholder="mm/aaaa ou jj/mm/aaaa"/>
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
            <input type="text" name="numero" value="<c:out value="${requestScope.bd.tome.numero}"/>" placeholder="Num�ro du tome"/>
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
            <label for="resume">R�sum�</label>
            <textarea name="resume" required placeholder="R�sum� du tome"><c:out value="${requestScope.bd.resume}"/></textarea>
        </div>
    </fieldset>
    <fieldset>
        <legend>Informations compl�mentaires</legend>
        <div>
            <c:if test="${!empty requestScope.form.errors['scenariste']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['scenariste']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label>Sc�naristes:</label>
            <input type="text" name="nom" ng-model="scenaristeLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="scenaristeFirstname" value="" placeholder="Pr�nom"/>
            <input type="button" value="add" ng-click="addScenariste();">
            <ul>
                <li ng-repeat="scenariste in scenaristes">
                    <p class="individu">{{scenariste.lastname}}&nbsp;{{scenariste.firstname}}</p>
                    <a href="" ng-click="deleteScenariste(scenariste)">X</a>
                </li>
            </ul>
            <input type="hidden" name="scenariste" ng-model="scenaristesString" value="{{scenaristesString}}"/>
            <br />
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['dessinateur']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['dessinateur']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label>Dessinateurs:</label>
            <input type="text" name="nom" ng-model="dessinateurLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="dessinateurFirstname" value="" placeholder="Pr�nom"/>
            <input type="button" value="add" ng-click="addDessinateur();">
            <ul>
                <li ng-repeat="des in dessinateurs">
                    <p class="individu">{{des.lastname}}&nbsp;{{des.firstname}}</p>
                    <a href="" ng-click="deleteDessinateur(des)">X</a>
                </li>
            </ul>
            <input type="hidden" name="dessinateur" ng-model="dessinateursString" value=""/>
            <br />
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['coloriste']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['coloriste']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label>Coloristes:</label>
            <input type="text" name="nom" ng-model="coloristeLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="coloristeFirstname" value="" placeholder="Pr�nom"/>
            <input type="button" value="add" ng-click="addColoriste();">
            <ul>
                <li ng-repeat="col in coloristes">
                    <p class="individu">{{col.lastname}}&nbsp;{{col.firstname}}</p>
                    <a href="" ng-click="coloristeColoriste(coloriste)">X</a>
                </li>
            </ul>
            <input type="hidden" name="coloriste" ng-model="coloristesString" />
            <br />
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['lettrage']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['lettrage']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label>Lettreurs:</label>
            <input type="text" name="nom" ng-model="lettreurLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="lettreurFirstname" value="" placeholder="Pr�nom"/>
            <input type="button" value="add" ng-click="addLettreur();">
            <ul>
                <li ng-repeat="lettreur in lettreurs">
                    <p class="individu">{{lettreur.lastname}}&nbsp;{{lettreur.firstname}}</p>
                    <a href="" ng-click="deleteLettreur(lettreur)">X</a>
                </li>
            </ul>
            <input type="hidden" name="lettrage" ng-model="lettreursString" />
            <br />
        </div>
        <div>
            <c:if test="${!empty requestScope.form.errors['encrages']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['encrages']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label>Encrage:</label>
            <input type="text" name="nom" ng-model="encreurLastname" value="" placeholder="Nom ou pseudo"/>
            <input type="text" name="prenom" ng-model="encreurFirstname" value="" placeholder="Pr�nom"/>
            <input type="button" value="add" ng-click="addEncreur();">
            <ul>
                <li ng-repeat="encreur in encreurs">
                    <p class="individu">{{encreur.lastname}}&nbsp;{{encreur.firstname}}</p>
                    <a href="" ng-click="deleteEncreur(encreur)">X</a>
                </li>
            </ul>
            <input type="hidden" name="encrage" ng-model="encreursString" />
            <br />
        </div>
    </fieldset>
    <input ng-disabled="uploadForm.$invalid" type="submit" value="Ajouter la bd"/>
</form>
