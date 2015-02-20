angular.module( 'ngBoilerplate.dashboard',
        [
            'ui.router',
            'ngResource',
            'ngBoilerplate.account',
            'hateoas'
        ])

    .config(function($stateProvider) {
        $stateProvider.state('manageDashboards', {
            url:'/manage/dashboards?accountId',
            views: {
                'main': {
                    templateUrl:'dashboard/manage-dashboards.tpl.html',
                    controller:'ManageDashboardCtrl'
                }
            },
            resolve: {
                account: function(accountService, $stateParams) {
                    return accountService.getAccountById($stateParams.accountId);
                },
                dashboards: function(dashboardService, $stateParams) {
                    return dashboardService.getDashboardsForAccount($stateParams.accountId);
                }
            },
            data : { pageTitle : "Dashboards" }
        });
})
.factory('dashboardService', function($resource, $q) {
      var service = {};
      service.createDashboard = function(accountId, dashboardData) {
        var ReleaseDashboard = $resource("/release-detailer-s4a/rest/accounts/:paramAccountId/dashboards");
        return ReleaseDashboard.save({paramAccountId: accountId}, dashboardData).$promise;
      };
      service.getAllDashboards = function() {
        var ReleaseDashboard = $resource("/release-detailer-s4a/rest/dashboards");
        return ReleaseDashoard.get().$promise.then(function (data) {
            return data.dashboards;
        });
      };
      service.getDashboardsForAccount = function(accountId) {
          var deferred = $q.defer();
          var Account = $resource("/release-detailer-s4a/rest/accounts/:paramAccountId");
          Account.get({paramAccountId: accountId}, function(account) {
              var ReleaseDashboard = account.resource("dashboards");
              ReleaseDashboard.get(null,
                  function(data) {
                    deferred.resolve(data.dashboards);
                  },
                  function() {
                    deferred.reject();
                  }
              );
          });

          return deferred.promise;
      };
      return service;
})
.controller("ManageDashboardCtrl", function($scope, dashboards, account, dashboardService, $state) {
        $scope.name = account.name;
        $scope.dashboards = dashboards;
        $scope.createDashboard = function(dashboardName) {
            dashboardService.createDashboard(account.rid, {
                title : dashboardName
            }).then(function() {
                    $state.go("manageDashboards", { accountId : account.rid }, { reload : true});
                });
        };
    });
