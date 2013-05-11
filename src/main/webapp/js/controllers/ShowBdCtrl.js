function ShowBdCtrl($scope) {
    $scope.areYouSure = function(event) {
        if (!confirm("Vous êtes sur le point de supprimer la ressource. Êtes-vous sûr?")) {
            event.preventDefault();
        }
    };

}