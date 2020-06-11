var app = angular.module('plunker', ['nvd3']);

app.controller('MainCtrl', ['$scope', 'services', function($scope, services) {

    services.getData().then(function successCb(data) {
        $scope.data = _.map(data.data, function(prod) {
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
            x: function(d){return d.key;},
            y: function(d){return d.y;},
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

    .factory('services', ['$http', function($http){
        var object = {};
        object.getData = function(){
            return $http.get('http://localhost:8080/countEventCategories');
        };
        return object;
    }]);

app.controller('MainCtrl2', function($scope) {
    $scope.options = {
        chart: {
            type: 'discreteBarChart',
            height: 450,
            margin : {
                top: 20,
                right: 20,
                bottom: 50,
                left: 55
            },
            x: function(d){return d.label;},
            y: function(d){return d.value + (1e-10);},
            showValues: true,
            valueFormat: function(d){
                return d3.format(',.4f')(d);
            },
            duration: 500,
            xAxis: {
                axisLabel: 'X Axis'
            },
            yAxis: {
                axisLabel: 'Y Axis',
                axisLabelDistance: -10
            }
        }
    };

    $scope.data = [
        {
            key: "Cumulative Return",
            values: [
                {
                    "label" : "A" ,
                    "value" : -29.765957771107
                } ,
                {
                    "label" : "B" ,
                    "value" : 0
                } ,
                {
                    "label" : "C" ,
                    "value" : 32.807804682612
                } ,
                {
                    "label" : "D" ,
                    "value" : 196.45946739256
                } ,
                {
                    "label" : "E" ,
                    "value" : 0.19434030906893
                } ,
                {
                    "label" : "F" ,
                    "value" : -98.079782601442
                } ,
                {
                    "label" : "G" ,
                    "value" : -13.925743130903
                } ,
                {
                    "label" : "H" ,
                    "value" : -5.1387322875705
                }
            ]
        }
    ]
});
