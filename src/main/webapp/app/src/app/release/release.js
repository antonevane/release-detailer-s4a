angular.module( 'ngBoilerplate.release', [
        'ui.router',
        'ngResource',
        'base64'
    ])
    .config(function($stateProvider) {
        $stateProvider.state('releaseSearch', {
                url:'/releases/search',
                views: {
                    'main': {
                        templateUrl:'release/search.tpl.html',
                        controller:'ReleaseSearchCtrl'
                    }
                },
                data : {
                    pageTitle : "Search Releases"
                }
            })
            .state('releaseDetails', {
                url:'/releases/{rtn}',
                views: {
                    'main': {
                        templateUrl:'release/details.tpl.html',
                        controller:'ReleaseDetailsCtrl'
                    }
                },
                resolve: {
                    release: function(releaseService, $stateParams) {
                        console.log("releaseDetails.resolve: $stateParams.rtn = " + $stateParams.rtn);
                        returnValue = releaseService.getMaReleaseByRtn($stateParams.rtn);
                        console.log("releaseDetails.resolve: returnValue = ...");
                        console.log(returnValue);
                        return returnValue;
                    }
                },
                data: {
                    pageTitle : "Release Details {{release.rtn}}"
                }
            })
        ;
    })
    .factory('releaseService', function($resource) {
        var service = {};
        service.getMaReleaseById = function(releaseId) {
            var Release = $resource("/release-detailer-s4a/rest/releases/:paramReleaseId");
            return Release.get({paramReleaseId : releaseId}).$promise;
        };
        service.getMaReleaseByRtn = function(rtn, success, failure) {
            var Release = $resource("/release-detailer-s4a/rest/releases/ma/:paramRtn");
            return Release.get({paramRtn : rtn}).$promise;
        };
        return service;
    })

    .controller("ReleaseSearchCtrl", function($scope, releaseService) {
        $scope.getMaReleaseByRtn = function(rtn) {
            // Get the $promise from the service to fetch the release information using the RTN
            releaseService.getMaReleaseByRtn(rtn).then(
                function(data) {
                    console.log("ReleaseSearchCtrl: getMaReleaseByRtn: data", data);
                    // Convert to array of releases so we can use ng-repeat in the table
                    $scope.release = data;
                },
                function() {
                    console.log("Unable to get release information for " + $scope.rtn);
                    $scope.release = null;
                });

        };
        $scope.getMaReleaseById= function(rid) {
            // Get the $promise from the service to fetch the release information using the internal release ID
            releaseService.getMaReleaseById(rid).then(
                function(data) {
                    console.log("ReleaseSearchCtrl: getMaReleaseById: data", data);
                    // Convert to array of releases so we can use ng-repeat in the table
                    $scope.release = data;
                },
                function() {
                    console.log("Unable to get release information for " + $scope.rtn);
                    $scope.release = null;
                });
        };
        // Default value
//        $scope.rtn = "1-0012345";
    })
    .controller("ReleaseDetailsCtrl", function($scope, releaseService, release) {
        console.log("release as argument to controller: release = ", release);
        $scope.release = release;

    })
;