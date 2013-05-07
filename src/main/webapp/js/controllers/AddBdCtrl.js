function AddBdCtrl($scope) {
    $scope.scenaristes = [];
    $scope.dessinateurs = [];
    $scope.coloristes = [];
    $scope.encreurs = [];
    $scope.lettreurs = [];
    $scope.scenaristesString = null;
    $scope.dessinateursString = null;
    $scope.coloristesString = null;
    $scope.encreursString = null;
    $scope.lettreursString = null;

    $scope.isAlreadyIn = function(col, lastname, firstname) {
        angular.forEach(col, function(v) {
            if (v.lastname === lastname && v.firstname === firstname) {
                return true;
            }
        });
        return false;
    };

    $scope.isBlank = function(s) {
        return s === null || s === undefined || s === "";
    };

    $scope.isNotBlank = function(s) {
        return !$scope.isBlank(s);
    };

    $scope.display = function(s) {
        alert(s);
    };

    $scope.add = function(col, lastname, firstname, mandatory, exists) {
        var isIn = $scope.isAlreadyIn(col, lastname, firstname);
        if (!isIn && $scope.isNotBlank(lastname)) {
            if (firstname === undefined) {
                firstname = "";
            }
            col.push({lastname: lastname, firstname: firstname});
        } else if ($scope.isBlank(lastname)) {
            $scope.display(mandatory);
            return;
        } else {
            $scope.display(exists);
        }
    };

    $scope.splitIndividusString = function(string) {
        var tmp = string.split(";");
        var result = [];
        for (var i = 0; i < tmp.length; i++) {
            var ar = tmp[i].split(" ");
            if (ar.length > 1) {
                result.push({lastname: ar[0], firstname: ar[1].firstname});
            } else {
                result.push({lastname: ar[0], firstname: ""});
            }

        }
        return result;
    };

    $scope.setScenaristes = function(v) {
        if (v !== null && v !== undefined && v.trim() !== "") {
            var inds = $scope.splitIndividusString(v);
            angular.forEach(inds, function(i) {
                $scope.scenaristes.push(i);
            });
        }
    };

    $scope.addScenariste = function() {
        var mandatory = "Le nom ou pseudo du scénariste est obligatoire.";
        var exists = "Le scénariste que vous essayer d'ajouter est déjà dans la liste.";
        $scope.add($scope.scenaristes, $scope.scenaristeLastname,
                $scope.scenaristeFirstname, mandatory, exists);
        $scope.scenaristesString = '';
        var separator = "";
        angular.forEach($scope.scenaristes, function(i) {
            $scope.scenaristesString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
            separator = ";";
        });
        $scope.scenaristeLastname = '';
        $scope.scenaristeFirstname = '';
    };

    $scope.deleteScenariste = function(scenariste) {
        var old = $scope.scenaristes;
        $scope.scenaristes = [];
        $scope.scenaristesString = '';
        var separator = "";
        angular.forEach(old, function(i) {
            if (i.lastname !== scenariste.lastname || i.firstname !== scenariste.firstname) {
                col.push(i);
                $scope.scenaristesString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
                separator = ";";
            }
        });
    };

    $scope.setDessinateurs = function(v) {
        if (v !== null && v !== undefined && v.trim() !== "") {
            var inds = $scope.splitIndividusString(v);
            angular.forEach(inds, function(i) {
                $scope.dessinateurs.push(i);
            });
        }
    };

    $scope.addDessinateur = function() {
        var mandatory = "Le nom ou pseudo du dessinateur est obligatoire.";
        var exists = "Le dessinateur que vous essayer d'ajouter est déjà dans la liste.";
        $scope.add($scope.dessinateurs, $scope.dessinateurLastname,
                $scope.dessinateurFirstname, mandatory, exists);
        $scope.dessinateursString = '';
        var separator = "";
        angular.forEach($scope.dessinateurs, function(i) {
            $scope.dessinateursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
            separator = ";";
        });
        $scope.dessinateurLastname = '';
        $scope.dessinateurFirstname = '';
    };

    $scope.deleteDessinateur = function(dessinateur) {
        var old = $scope.dessinateurs;
        $scope.dessinateurs = [];
        $scope.dessinateursString = '';
        var separator = "";
        angular.forEach(old, function(d) {
            if (d.nom !== dessinateur.nom || d.prenom !== dessinateur.prenom) {
                $scope.dessinateurs.push(d);
                $scope.dessinateursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
                separator = ";";
            }
        });
    };

    $scope.setColoristes = function(v) {
        if (v !== null && v !== undefined && v.trim() !== "") {
            var inds = $scope.splitIndividusString(v);
            angular.forEach(inds, function(i) {
                $scope.coloristes.push(i);
            });
        }
    };

    $scope.addColoriste = function() {
        var mandatory = "Le nom ou pseudo du coloriste est obligatoire.";
        var exists = "Le coloriste que vous essayer d'ajouter est déjà dans la liste.";
        $scope.add($scope.coloristes, $scope.coloristeLastname,
                $scope.coloristeFirstname, mandatory, exists);
        $scope.coloristesString = '';
        var separator = "";
        angular.forEach($scope.coloristes, function(i) {
            $scope.coloristesString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
            separator = ";";
        });
        $scope.coloristeLastname = '';
        $scope.coloristeFirstname = '';
    };

    $scope.deleteColoriste = function(coloriste) {
        var old = $scope.coloristes;
        $scope.coloristes = [];
        $scope.coloristesString = '';
        var separator = "";
        angular.forEach(old, function(c) {
            if (c.nom !== coloriste.nom || c.prenom !== coloriste.prenom) {
                $scope.coloristes.push(c);
                $scope.coloristesString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
                separator = ";";
            }
        });
    };

    $scope.setLettreurs = function(v) {
        if (v !== null && v !== undefined && v.trim() !== "") {
            var inds = $scope.splitIndividusString(v);
            angular.forEach(inds, function(i) {
                $scope.lettreurs.push(i);
            });
        }
    };

    $scope.addLettreur = function() {
        var mandatory = "Le nom ou pseudo du lettreur est obligatoire.";
        var exists = "Le lettreur que vous essayer d'ajouter est déjà dans la liste.";
        $scope.add($scope.lettreurs, $scope.lettreurLastname,
                $scope.lettreurFirstname, mandatory, exists);
        $scope.lettreursString = '';
        var separator = "";
        angular.forEach($scope.lettreurs, function(i) {
            $scope.lettreursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
            separator = ";";
        });
        $scope.lettreurLastname = '';
        $scope.lettreurFirstname = '';
    };

    $scope.deleteLettreur = function(lettreur) {
        var old = $scope.lettreurs;
        $scope.lettreurs = [];
        $scope.lettreursString = '';
        var separator = "";
        angular.forEach(old, function(l) {
            if (l.nom !== lettreur.nom || l.prenom !== lettreur.prenom) {
                $scope.lettreurs.push(l);
                $scope.lettreursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
                separator = ";";
            }
        });
    };

    $scope.setEncreurs = function(v) {
        if (v !== null && v !== undefined && v.trim() !== "") {
            var inds = $scope.splitIndividusString(v);
            angular.forEach(inds, function(i) {
                $scope.encreurs.push(i);
            });
        }
    };

    $scope.addEncreur = function() {
        var mandatory = "Le nom ou pseudo de l'encreur est obligatoire.";
        var exists = "L'encreur que vous essayer d'ajouter est déjà dans la liste.";
        $scope.add($scope.encreurs, $scope.encreurLastname,
                $scope.encreurFirstname, mandatory, exists);
        $scope.encreursString = '';
        var separator = "";
        angular.forEach($scope.encreurs, function(i) {
            $scope.encreursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
            separator = ";";
        });
        $scope.encreurLastname = '';
        $scope.encreurFirstname = '';
    };

    $scope.deleteEncreur = function(encreur) {
        var old = $scope.encreurs;
        $scope.encreurs = [];
        $scope.encreursString = '';
        var separator = "";
        angular.forEach(old, function(e) {
            if (e.nom !== encreur.nom || e.prenom !== encreur.prenom) {
                $scope.encreurs.push(e);
                $scope.encreursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
                separator = ";";
            }
        });
    };

}