<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value = "/css/search.css" />" type="text/css" media="screen"/>
        <c:set scope="request" var="jquery" > <c:url value = "/js/vendor/jquery-1.7.1.min.js" /> </c:set>
        <c:set scope="request" var="jqueryUI" > <c:url value = "/js/vendor/jquery-ui-1.8.18.custom.min.js" /> </c:set>
        <c:set scope="request" var="js3" > <c:url value = "/js/vendor/angular.min.js" /> </c:set>
        <c:set scope="request" var="js4" > <c:url value = "/js/app.js" /> </c:set>
        <c:set scope="request" var="js5" > <c:url value = "/js/controllers/AddBdCtrl.js" /></c:set>
        <script type="text/javascript" src="${jquery}"></script>
        <script type="text/javascript" src="${jqueryUI}"></script>
        <script type="text/javascript" src="${js3}"></script>
        <script type="text/javascript" src="${js4}"></script>
        <script type="text/javascript" src="${js5}"></script>
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />"/>
        <title>Recherche</title>
    </head>

    <body ng-controller="AddBdCtrl" ng-app="directives" >
        <c:set var="deleteImage">
            <c:url value="/img/delete.png" />
        </c:set>
        <c:import url="/WEB-INF/jsp/header.jsp"/>
        <c:if test="${!empty requestScope.notice}">
            <div class="flash-notice">${requestScope.notice}</div>
        </c:if>
        <c:if test="${!empty requestScope.error}">
            <div class="flash-error">${requestScope.error}</div>
        </c:if>
        <section class="content">
            <h2 class="main-title">Recherche</h2>
            <div class="search">
                <form method="post" name="searchForm" ng-submit="submit();">
                    <div class="search-left">
                        <div class="titre" ng-init="formData.searchTitre='${requestScope.searchTitre}'">
                            <label>Titre:</label><input type="text" ng-model="formData.searchTitre" name="searchTitre"/>
                        </div>
                        <div class="editeur"  ng-init="formData.searchEditeur='${requestScope.searchEditeur}'">
                            <label>Editeur:</label><input type="text" ng-model="formData.searchEditeur" name="searchEditeur"/>
                        </div>
                        <div class="resume" ng-init="formData.searchResume='${requestScope.searchResume}'">
                            <label>Résumé:</label><input type="text" ng-model="formData.searchResume" name="searchResume"/>
                        </div>
                    </div>
                    <div class="search-right">
                        <div class="serie" ng-init="formData.searchSerie='${requestScope.searchSerie}'">
                            <label>Serie:</label><input type="text" ng-model="formData.searchSerie" name="searchSerie" value="${requestScope.serie}"/>
                        </div>
                        <div class="langue" ng-init="formData.searchLangue='${requestScope.searchLangue}'">
                            <label>Langue:</label><input type="text" ng-model="formData.searchLangue" name="searchLangue" value="${requestScope.langue}"/>
                        </div>

                    </div>
                    <fieldset class="advanced">
                        <legend>Recherche avancée
                            [<a href="" ng-click="toggle();">{{toggleText}}</a>]
                        </legend>
                        <div bn-slide-show="isVisible" slide-show-duration="2000">
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
                                <input type="hidden" name="lettrage" ng-model="lettreursString" value="{{lettreursString}}" />
                                <br />
                            </div>
                        </div>
                    </fieldset>
                    <input type="submit" class="btn" value="Rechercher"/>
                </form>
            </div>
            <input type="hidden" id="serialized-form" />
            <input type="hidden" id="ajax-url" value="<c:url value="/search" />"/>
            <input type="hidden" id="current-page" value="<c:out value="${page}" />"/>

            <section id="search-content" ng-bind-html-unsafe="searchContent" ng-init="submit()">
                <c:import url="/WEB-INF/jsp/bd/searchResults.jsp" />
            </section>
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>