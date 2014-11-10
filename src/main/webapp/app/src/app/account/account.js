angular.module( 'ngBoilerplate.account', [
    'ui.router',
        'ngResource'
])

.config(function($stateProvider) {
    $stateProvider.state('login', {
        url:'/login',
        views: {
            'main': {
                templateUrl:'account/login.tpl.html',
                controller:'LoginCtrl'
            }
        },
        data : { pageTitle : "Login" }
    })
        .state('register', {
            url:'/register',
                views: {
                'main': {
                    templateUrl:'account/register.tpl.html',
                    controller:'RegisterCtrl'
                }
            },
            data : { pageTitle : "Registration" }
        })
    ;
})
    .factory('sessionService', function() {
        var session = {};
        session.login = function(data) {
            alert("User logged in with credentials " + data.name + " and " + data.password);
            localStorage.setItem("session", data);
        };
        session.logout = function(data) {
            localStorage.removeItem("session");
        };
        session.isLoggedIn = function(data) {
            return localStorage.getItem("session") !== null;
        };
        return session;
    })
    .factory('accountService', function($resource) {
        var service = {};
        service.register = function(account, success, failure) {
            var Account = $resource("/release-detailer-s4a/rest/accounts");
            Account.save({}, account, success, failure);
        };
        service.userExists = function(account, success, failure) {
            var Account = $resource("/release-detailer-s4a/rest/accounts");
            var data = Account.get({name:account.name},
                function() {
                    var accounts = data.accounts;
                    if (accounts.length !== 0) {
                        success(accounts[0]);
                    } else {
                        failure();
                    }
                },
                failure
            );
        };
        return service;
    })
    .controller("LoginCtrl", function($scope, sessionService, $state, accountService) {
    $scope.login = function() {
        accountService.userExists($scope.account,
            function(account) {
                sessionService.login(account);
                $state.go("home");
            },
            function() {
                alert("User does not exist: " + $scope.account.name);
            }
        );
    };
})
    .controller("RegisterCtrl", function($scope, sessionService, $state, accountService) {
        $scope.register = function() {
            accountService.register($scope.account,
                function(returnedData) {
                    sessionService.login(returnedData);
                    $state.go("home");
                },
                function() {
                    alert("Error registering user");
                }
            );
        };
})
;