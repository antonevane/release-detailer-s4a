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
                    pageTitle : "Search Releases",
                    rtn : ""
                }
            });
    })
    .factory('releaseService', function($resource) {
        var service = {};
        service.getMaReleaseById = function(releaseId) {
            alert("getMaReleaseById " + releaseId);
            var Release = $resource("/release-detailer-s4a/rest/releases/id/:paramReleaseId");
            return Release.get({paramReleaseId : releaseId}).$promise;
        };
        service.getMaReleaseByRtn = function(rtn) {
            console.log("rtn is", rtn);
            var Release = $resource("/release-detailer-s4a/rest/releases/ma/:paramRtn");
            return Release.get({paramRtn : rtn}).$promise;
        };
        return service;
    })

    .controller("ReleaseSearchCtrl", function($scope, releaseService) {
        $scope.getMaReleaseByRtn = function(rtn) {
            releaseService.getMaReleaseByRtn(rtn);
        };
    })
;