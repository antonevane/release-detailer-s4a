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
//            .state('viewRelease', {
//                url:'/releases/ma?rtn',
//                views: {
//                    'main': {
//                        templateUrl:'release/view-release.tpl.html',
//                        controller:'ViewReleaseCtrl'
//                    }
//                },
//                data: {
//                    pageTitle : "View Release"
//                }
//            })
        ;
    })
    .factory('releaseService', function($resource) {
        var service = {};
        service.getMaReleaseById = function(releaseId) {
            alert("getMaReleaseById " + releaseId);
            var Release = $resource("/release-detailer-s4a/rest/releases/id/:paramReleaseId");
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
                    $scope.releases = [data];
                },
                function() {
                    console.log("Unable to get release information for " + $scope.rtn);
                    $scope.releases = [];
                });
        };
        // Default value
//        $scope.rtn = "1-0012345";
    })
//    .controller("ViewReleaseCtrl", function($scope, releaseService) {
//        $scope.testString = "This is a test string";
//        console.log("ViewReleaseCtrl: scope", $scope);
//        console.log("release in scope: release", $scope.release);
//    })
;