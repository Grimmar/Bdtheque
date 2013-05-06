<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:out value="${requestScope.form.result}" />
<h2>Charger un fichier XML</h2>

<div class="flash-info">Pour simplifier l'ajout de bande dessinée, vous avez la possibilité d'ajouter une bd en chargeant un fichier XML validé par notre fichier XSL.</div>
<form ng-controller="FileUploadCtrl" action="<c:url value="/upload" />" method="post"name="uploadForm" enctype="multipart/form-data" ng-app="directives">
    <fieldset>
        <legend>Fichier</legend>
        <c:if test="${!empty requestScope.form.errors}">
            <ul>
                <c:forEach items="${requestScope.form.errors['file']}" var="error">
                    <li><c:out value="${error}" /></li>
                    </c:forEach>    
            </ul>
        </c:if>
        <div class="file-input">
            <label for="file">Emplacement du fichier</label>
            <div>
                <input type="text" readonly="readonly" ng-model="file.name" required/>
                <input id="fileInput" type="file" name="file" class="inputFile" onkeydown="return false;"
                       onchange="angular.element(this).scope().setFile(this);" ng-model="file"/>
                <button id="uploadButton" value="" class="xml-upload-button" type="button">
                    <img src="<c:url value = "/img/xml_file.png"/>" alt="Fichier XML">
                </button>
            </div>
        </div>
        <input ng-disabled="uploadForm.$invalid" class="clear left" type="submit" value="Charger le fichier"/>
    </fieldset>
</form>