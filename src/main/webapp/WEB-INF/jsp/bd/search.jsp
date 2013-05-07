<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value = "/css/search.css" />" type="text/css" media="screen"/>
        <c:set scope="request" var="angular" > <c:url value = "/js/vendor/angular.min.js" /> </c:set>
        <c:set scope="request" var="abbCtrl" > <c:url value = "/js/controllers/AddBdCtrl.js" /></c:set>
        <script type="text/javascript" src="${angular}"></script>
        <script type="text/javascript" src="${abbCtrl}"></script>
        <link rel="shortcut icon" href="<c:url value = "/img/favicon.ico" />"/>
        <title>Recherche</title>
    </head>

    <body>
        <c:import url="/WEB-INF/jsp/header.jsp"/>
        <section class="content">
            <div class="search">
                <form action="<c:url value="/search" />" method="post" name="searchForm" ng-controller="AddBdCtrl" ng-app="directives">
                    <div class="titre">
                        <label>Titre:</label><input type="text" name="search-titre" value="${requestScope.searchTitre}"/>
                    </div>
                    <div class="editeur">
                        <label>Editeur:</label><input type="text" name="search-editeur" value="${requestScope.searchEditeur}"/>
                    </div>
                    <div class="resume">
                        <label>Résumé:</label><input type="text" name="search-resume" value="${requestScope.searchResume}"/>
                    </div>
                    <div class="serie">
                        <label>Serie:</label><input type="text" name="search-serie" value="${requestScope.serie}"/>
                    </div>
                    <div class="langue">
                        <label>Langue:</label><input type="text" name="search-langue" value="${requestScope.langue}"/>
                    </div>
                    <div>
                        <label>Scénaristes:</label>
                        <input type="text" name="nom" ng-model="scenaristeLastname" value="" placeholder="Nom ou pseudo"/>
                        <input type="text" name="prenom" ng-model="scenaristeFirstname" value="" placeholder="Prénom"/>
                        <input type="button" value="Ajouter" class="add-btn" ng-click="addScenariste();">
                        <table ng-show="scenaristes.length">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Supprimer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="scenariste in scenaristes">
                                    <td>{{$index + 1}}</td>
                                    <td>{{scenariste.lastname}}</td>
                                    <td>{{scenariste.firstname}}</td>
                                    <td>
                                        <a href="" class="rmv-btn" ng-click="deleteScenariste(scenariste)">X</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="hidden" name="scenariste" ng-model="scenaristesString" value="{{scenaristesString}}"/>
                        <br />
                    </div>
                    <div>
                        <label>Dessinateurs:</label>
                        <input type="text" name="nom" ng-model="dessinateurLastname" value="" placeholder="Nom ou pseudo"/>
                        <input type="text" name="prenom" ng-model="dessinateurFirstname" value="" placeholder="Prénom"/>
                        <input type="button" value="Ajouter" class="add-btn" ng-click="addDessinateur();">
                        <table ng-show="dessinateurs.length">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="des in dessinateurs">
                                    <td>{{$index + 1}}</td>
                                    <td>{{des.lastname}}</td>
                                    <td>{{des.firstname}}</td>
                                    <td>
                                        <a href="" class="rmv-btn" ng-click="deleteDessinateur(des)">X</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="hidden" name="dessinateur" ng-model="dessinateursString" value="{{dessinateursString}}"/>
                        <br />
                    </div>
                    <div>
                        <label>Coloristes:</label>
                        <input type="text" name="nom" ng-model="coloristeLastname" value="" placeholder="Nom ou pseudo"/>
                        <input type="text" name="prenom" ng-model="coloristeFirstname" value="" placeholder="Prénom"/>
                        <input type="button" value="Ajouter" class="add-btn" ng-click="addColoriste();">
                        <table ng-show="coloristes.length">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="col in coloristes">
                                    <td>{{$index + 1}}</td>
                                    <td>{{col.lastname}}</td>
                                    <td>{{col.firstname}}</td>
                                    <td>
                                        <a href="" class="rmv-btn" ng-click="deleteColoriste(col)">X</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="hidden" name="coloriste" ng-model="coloristesString" />
                        <br />
                    </div>
                    <div>
                        <label>Lettreurs:</label>
                        <input type="text" name="nom" ng-model="lettreurLastname" value="" placeholder="Nom ou pseudo"/>
                        <input type="text" name="prenom" ng-model="lettreurFirstname" value="" placeholder="Prénom"/>
                        <input type="button" value="Ajouter" class="add-btn" ng-click="addLettreur();">
                        <table ng-show="lettreurs.length">
                            <thead>
                                <tr>
                                    <th>Id</th>
                                    <th>Nom</th>
                                    <th>Prénom</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr ng-repeat="lettreur in lettreurs">
                                    <td>{{$index + 1}}</td>
                                    <td>{{lettreur.lastname}}</td>
                                    <td>{{lettreur.firstname}}</td>
                                    <td>
                                        <a href="" class="rmv-btn" ng-click="deleteLettreur(lettreur)">X</a>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="hidden" name="lettrage" ng-model="lettreursString" />
                        <br />
                    </div>
                    <input type="submit" value="Rechercher"/>
                </form>
            </div>
            <div class="result-section">
                <table class="result" border="1">
                    <tr>
                        <th>Titre</th>
                        <th>Editeur</th>
                        <th>Scénaristes</th>
                        <th>Déssinateurs</th>
                        <th>Coloristes</th>
                        <th>Lettrages</th>
                        <th>Serie</th>
                        <th>Langue</th>
                    </tr>
                    <c:forEach items="${requestScope.searchBd}" var="bd"> 
                        <tr>
                            <td><c:out value="${bd.titre}" /></td>
                            <td><c:out value="${bd.editeur}" /></td>
                            <td>
                                <c:forEach var="individu" items="${bd.scenaristes.scenariste}">
                            <li>
                                <span><c:out value="${individu.prenom}" /></span>
                                &nbsp;
                                <span><c:out value="${individu.nom}"/></span>
                            </li>
                        </c:forEach>
                        </td> 
                        <td>
                            <c:forEach var="individu" items="${bd.dessinateurs.dessinateur}">
                            <li>
                                <span><c:out value="${individu.prenom}" /></span>
                                &nbsp;
                                <span><c:out value="${individu.nom}"/></span>
                            </li>
                        </c:forEach>
                        </td>
                        <td>
                            <c:forEach var="individu" items="${bd.coloristes.coloriste}">
                            <li>
                                <span><c:out value="${individu.prenom}" /></span>
                                &nbsp;
                                <span><c:out value="${individu.nom}"/></span>
                            </li>
                        </c:forEach>
                        </td> 
                        <td>
                            <c:forEach var="individu" items="${bd.lettrages.lettrage}">
                            <li>
                                <span><c:out value="${individu.prenom}" /></span>
                                &nbsp;
                                <span><c:out value="${individu.nom}"/></span>
                            </li>
                        </c:forEach>
                        </td>
                        <td><c:out value="${bd.serie}" /></td>
                        <td><c:out value="${bd.langue}" /></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </section>

        <c:import url="/WEB-INF/jsp/footer.jsp"/>
    </body>
</html>
