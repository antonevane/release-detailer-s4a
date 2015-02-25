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
                url:'/releases/details/{rtn}',
                views: {
                    'main': {
                        templateUrl:'release/details.tpl.html',
                        controller:'ReleaseDetailsCtrl'
                    }
                },
                resolve: {
                    release: function(releaseService, $stateParams) {
                        returnValue = releaseService.getMaReleaseByRtn($stateParams.rtn);
                        return returnValue;
                    }
                },
                data: {
                    pageTitle : "Release Details {{release.rtn}}"
                }
            })
            .state('releaseLocationSearch', {
                url:'/releases/locationSearch',
                views: {
                    'main': {
                        templateUrl:'release/location-search.tpl.html',
                        controller:'ReleaseSearchCtrl'
                    }
                },
                data : {
                    pageTitle : "Search Releases by Location"
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
        service.getMaReleasesByTown = function(town) {
            console.log("releaseService.getMaReleasesByTown() called: " + town);
            var Release = $resource("/release-detailer-s4a/rest/releases/ma/town/:paramTown");
            return Release.get({paramTown : town}).$promise;
        };

        return service;
    })
    .controller("ReleaseSearchCtrl", function($scope, releaseService) {
        $scope.getMaReleaseByRtn = function(rtn) {
            // Get the $promise from the service to fetch the release information using the RTN
            releaseService.getMaReleaseByRtn(rtn).then(
                function(data) {
                    console.log("ReleaseSearchCtrl: getMaReleaseByRtn: data", data);
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
                    $scope.release = data;
                },
                function() {
                    console.log("Unable to get release information for " + $scope.rtn);
                    $scope.release = null;
                });
        };
        $scope.getMaReleasesByTown = function(town) {
            console.log("ReleaseSearchCtrl: getMaReleasesByTown: ", town);
            releaseService.getMaReleasesByTown(town).then(
                function(data) {
                    console.log("SUCCESS: " + data);
                    $scope.releases = data.releases;
                    console.log("$scope.releases: " + $scope.releases);
                },
                function(error) {
                    console.log("FAILURE: " + error);
                }
            );

        };
    })
    .controller("ReleaseDetailsCtrl", function($scope, releaseService, release) {
        console.log("release as argument to controller: release = ", release);
        $scope.release = release;

    })
;