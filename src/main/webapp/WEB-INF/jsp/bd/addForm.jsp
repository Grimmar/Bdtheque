<c:out value="${requestScope.form.result}" />
<h2>Saisie manuelle des données</h2>
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
            <input type="text" name="parution" class="datepicked" value="<c:out value="${requestScope.bd.parution}"/>" placeholder="Date de parution" ng-model="parution" datepicker/>
        </div>

        <div>
            <c:if test="${!empty requestScope.form.errors['creationDate']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['creationDate']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="creationDate">Date de création</label>
            <input type="text" class="datepicked" name="creationDate" value="<c:out value="${requestScope.bd.creationDate}"/>" placeholder="Date de création" ng-model="creation" datepicker/>
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
        <div class="full-size">
            <c:if test="${!empty requestScope.form.errors['resume']}">
                <ul>
                    <c:forEach items="${requestScope.form.errors['resume']}" var="error">
                        <li><c:out value="${error}" /></li>
                        </c:forEach>    
                </ul>
            </c:if>
            <label for="resume">Résumé</label>
            <textarea name="resume" value="<c:out value="${requestScope.bd.resume}"/>" placeholder="Résumé du tome"></textarea>
        </div>
    </fieldset>
    <fieldset>
        <legend>Informations complémentaires</legend>
        <!--@XmlElement(required = true)
        protected ScenaristesType scenaristes;
        @XmlElement(required = true)
        protected DessinateursType dessinateurs;
        @XmlElement(required = true)
        protected ColoristesType coloristes;
        protected LettragesType lettrages;
        protected EncragesType encrages;
        protected TomeType tome;-->
    </fieldset>
    <input ng-disabled="uploadForm.$invalid" type="submit" value="Ajouter la bd"/>
</form>
