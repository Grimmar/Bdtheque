var app = angular.module('directives', []);

app.directive('datepicker', function() {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function(scope, element, attrs, ngModelCtrl) {
            $(function() {
                element.datepicker({
                    dateFormat: 'dd/mm/yy',
                    showOn: 'both',
                    buttonImage: "img/calendar.png",
                    showOtherMonths: true,
                    dayNamesMin: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                    onSelect: function(date) {
                        ngModelCtrl.$setViewValue(date);
                        scope.$apply();
                    }
                });
            });
        }
    };
});

app.directive("bnSlideShow", function() {
    function link($scope, element, attributes) {
        var expression = attributes.bnSlideShow;
        var duration = (attributes.slideShowDuration || "fast");
        if (!$scope.$eval(expression)) {
            element.hide();
        }

        $scope.$watch(expression, function(newValue, oldValue) {
            if (newValue === oldValue) {
                return;
            }

            if (newValue) {
                element.stop(true, true).slideDown(duration);
            } else {
                element.stop(true, true).slideUp(duration);
            }
        }
        );
    }
    return({
        link: link,
        restrict: "A"
    });

});
