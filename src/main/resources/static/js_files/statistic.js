var app = angular.module('plunker', ['nvd3']);

app.controller('MainCtrl', ['$scope', 'services', function ($scope, services) {
    services.getData().then(function successCb(data) {
        $scope.values = new Array();

        for (let i = 0; i < data.data.length; i++) {

            console.log(' data ', data.data);
            $scope.x = {
                "label": data.data[i].categorie,
                "value": data.data[i].count
            };
            $scope.values[i] = $scope.x;

            console.log(' $scope.tab.values[i] ', $scope.values[i]);
            console.log(' $scope.tab.values inside boucle', $scope.values);

        }


        console.log(' $scope.tab.values outside', $scope.values);
        $scope.data = _.map(data.data, function (prod) {


            console.log('prod', prod);

            return {
                key: prod.categorie,
                y: prod.count
            };
        });

    });

    $scope.options = {
        chart: {
            type: 'pieChart',
            height: 500,
            x: function (d) {
                return d.key;
            },
            y: function (d) {
                return d.y;
            },
            showLabels: true,
            duration: 500,
            labelThreshold: 0.01,
            labelSunbeamLayout: true,
            legend: {
                margin: {
                    top: 5,
                    right: 35,
                    bottom: 5,
                    left: 0
                }
            }
        }
    };

}])

    .factory('services', ['$http', function ($http) {
        var object = {};
        object.getData = function () {
            return $http.get('http://localhost:8080/countEventCategories');
        };
        return object;
    }]);


app.controller('MainCtrl2', ['$scope', 'services2', function ($scope, services) {
        $scope.options = {
            chart: {
                type: 'discreteBarChart',
                height: 450,
                margin: {
                    top: 20,
                    right: 20,
                    bottom: 50,
                    left: 55
                },
                x: function (d) {
                    return d.label;
                },
                y: function (d) {
                    return d.value + (1e-10);
                },
                showValues: true,
                valueFormat: function (d) {
                    return d3.format(',.4f')(d);
                },
                duration: 500,
                xAxis: {
                    axisLabel: 'Titre de événement'
                },
                yAxis: {
                    axisLabel: 'Nombre des participants',
                    axisLabelDistance: -10
                }
            }
        };
        services.getData().then(function successCb(data) {
            $scope.values = new Array();

            for (let i = 0; i < data.data.length; i++) {

                console.log(' data ', data.data);
                $scope.x = {
                    "label": data.data[i].titreEvt,
                    "value": data.data[i].nbParticipants
                };
                $scope.values[i] = $scope.x;

                console.log(' $scope.tab.values[i] ', $scope.values[i]);
                console.log(' $scope.tab.values inside boucle', $scope.values);

            }


            $scope.data = [
                {
                    key: "Cumulative Return",
                    values: $scope.values
                }
            ]
        });


    }]
).factory('services2', ['$http', function ($http) {
    var object = {};
    object.getData = function () {
        return $http.get('http://localhost:8080/countParticiByEvent');
    };
    return object;
}]);


app.controller('ParticipationByCateg', ['$scope', 'services3', function ($scope, services) {
    $scope.options = {
        chart: {
            type: 'pieChart',
            height: 450,
            donut: true,
            x: function (d) {
                return d.key;
            },
            y: function (d) {
                return d.y;
            },
            showLabels: true,

            pie: {
                startAngle: function (d) {
                    return d.startAngle / 2 - Math.PI / 2
                },
                endAngle: function (d) {
                    return d.endAngle / 2 - Math.PI / 2
                }
            },
            duration: 500,
            legend: {
                margin: {
                    top: 5,
                    right: 70,
                    bottom: 5,
                    left: 0
                }
            }
        }
    };
    services.getData().then(function successCb(data) {
        $scope.values = new Array();

        for (let i = 0; i < data.data.length; i++) {

            console.log(' data ', data.data);
            $scope.x = {
                "key": data.data[i].categorie,
                "y": data.data[i].count
            };
            $scope.values[i] = $scope.x;


        }


        $scope.data =$scope.values


    });

}]).factory('services3', ['$http', function ($http) {
    var object = {};
    object.getData = function () {
        return $http.get('http://localhost:8080/countParticiByCatEvent');
    };
    return object;
}]);
