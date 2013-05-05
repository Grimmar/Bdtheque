function FileUploadCtrl($scope) {
    var uploadButton = document.getElementById("uploadButton");

    uploadButton.addEventListener("click", function() {
        document.getElementById('fileInput').click();
    });

    $scope.setFile = function(element) {
        $scope.$apply(function() {
            $scope.file = element.files[0];
        });

    };

}