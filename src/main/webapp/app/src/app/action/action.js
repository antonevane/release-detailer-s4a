angular.module( 'ngBoilerplate.action', [
        'ui.router',
        'ngBoilerplate.release',
        'ngResource',
        'base64'
    ])
    .config(function($stateProvider) {
        $stateProvider.state('releaseActions', {
            url:'/releases/{rtn}/actions',
            views: {
                'main': {
                    templateUrl:'action/action.tpl.html',
                    controller:'ReleaseActionCtrl'
                }
            },
            resolve: {
                actions: function(actionService, $stateParams) {
                    return actionService.getActionsForRelease($stateParams.rtn);
                },
                release: function(releaseService, $stateParams) {
                    returnValue = releaseService.getMaReleaseByRtn($stateParams.rtn);
                    return returnValue;
                }
            },
            data : {
                pageTitle : "Release Actions"
            }
        })
        ;
    })
    .factory('actionService', function($resource) {
        var service = {};
        service.getActionsForRelease = function(releaseRtn) {
            var Actions = $resource("/release-detailer-s4a/rest/releases/ma/:paramReleaseRtn/actions");
            return Actions.get({paramReleaseRtn : releaseRtn}).$promise.then(function (data) {
                return data.actions;
            });
        };
        return service;
    })

//    .controller("ReleaseActionCtrl", function($scope, actionService) {
//        $scope.getMaActionsByRtn = function(rtn) {
//            // Get the $promise from the service to fetch the Action information using the RTN
//            actionService.getMaActionsByRtn(rtn).then(
//                function(data) {
//                    console.log("ReleaseActionCtrl: getMaActionsByRtn: data", data);
//                    $scope.actions = data;
//                    console.log("ReleaseActionCtrl: actions[0] = " + $scope.acitons[1]);
//                },
//                function() {
//                    console.log("Unable to get release action information for " + $scope.rtn);
//                    $scope.actions = null;
//                });
//
//        };
//    })
    .controller("ReleaseActionCtrl", function($scope, actionService, actions, release) {
        console.log("actions as argument to ReleaseActionCtrl controller: actions =  ", actions);
        console.log("release on scope in ReleaseActionCtrl controller: release.rtn = ", release.rtn);
        $scope.actions = actions;
        $scope.release = release;
    })
;