function AddBdCtrl($scope, $http) {
    $scope.isVisible = false;
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
    $scope.toggleText = "Montrer";
    $scope.searchContent = "";
    $scope.formData = {};

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
                result.push({lastname: ar[0], firstname: ar[1]});
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
        $scope.scenaristesString = v;
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
                $scope.scenaristes.push(i);
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
        $scope.dessinateursString = v;
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
        angular.forEach(old, function(i) {
            if (i.lastname !== dessinateur.lastname || i.firstname !== dessinateur.firstname) {
                $scope.dessinateurs.push(i);
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
        $scope.coloristesString = v;
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
        angular.forEach(old, function(i) {
            if (i.lastname !== coloriste.lastname || i.firstname !== coloriste.firstname) {
                $scope.coloristes.push(i);
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
        $scope.lettreursString = v;
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
        angular.forEach(old, function(i) {
            if (i.lastname !== lettreur.lastname || i.firstname !== lettreur.firstname) {
                $scope.lettreurs.push(i);
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
        $scope.encreursString = v;
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
        angular.forEach(old, function(i) {
            if (i.lastname !== encreur.lastname || i.firstname !== encreur.firstname) {
                $scope.encreurs.push(i);
                $scope.encreursString += separator + i.lastname + " " + (i.firstname === undefined ? "" : i.firstname);
                separator = ";";
            }
        });
    };

    $scope.toggle = function() {
        $scope.isVisible = !$scope.isVisible;
        if ($scope.isVisible) {
            $scope.toggleText = "Cacher";
        } else {
            $scope.toggleText = "Montrer";
        }
    };

    $scope.submit = function() {
        if ($scope.scenaristesString !== null && $scope.scenaristesString !== "") {
            $scope.formData.scenaristesString = $scope.scenaristesString;
        }
        if ($scope.dessinateursString !== null && $scope.dessinateursString !== "") {
            $scope.formData.dessinateursString = $scope.dessinateursString;
        }
        if ($scope.coloristesString !== null && $scope.coloristesString !== "") {
            $scope.formData.coloristesString = $scope.coloristesString;
        }
        if ($scope.lettreursString !== null && $scope.lettreursString !== "") {
            $scope.formData.lettreursString = $scope.lettreursString;
        }

        var url = document.getElementById("ajax-url").value + '/'
                + document.getElementById("current-page").value + '/';
        document.getElementById("serialized-form").value = $scope.formData;
        $http({
            url: url,
            method: "POST",
            data: $.param($scope.formData),
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        }).success(function(data, status, headers, config) {
            $scope.searchContent = data;
        }).error(function(data, status, headers, config) {
            $scope.searchContent = 'Veuillez nous excuser, une erreur à eu lieu';
        });
    };

}